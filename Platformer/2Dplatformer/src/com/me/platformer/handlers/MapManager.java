package com.me.platformer.handlers;

import static com.me.platformer.handlers.B2DVars.PPM;

import java.util.Iterator;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class MapManager {
	private World world;
	private Array<Body> bodies = new Array<Body>(); 
	private TiledMap tileMap; 
	private OrthogonalTiledMapRenderer tmr; 

	private String path; 
	
	public MapManager(World world, String path){
		this.world = world; 
		this.path = path; 
		createTiles(); 
	}
	
	public void render() {
		tmr.render(); 
	}
	
	private void createTiles(){
		//Load map
		tileMap = new TmxMapLoader().load(path); 
		createLayer(tileMap.getLayers().get("collision"), B2DVars.BIT_GROUND);		
		createLayer(tileMap.getLayers().get("goal"), B2DVars.BIT_GROUND);			
	}
	
	private void createLayer(MapLayer layer, short bits){
		FixtureDef fDef = new FixtureDef();
		fDef.isSensor = false; 
		
		MapObjects objects = layer.getObjects(); 
		Iterator<MapObject> objectIt = objects.iterator(); 
		boolean isGoal = false; 
		
		while(objectIt.hasNext()){
			MapObject object = objectIt.next(); 
			
			Shape shape = null; 
			BodyDef bdef = new BodyDef();
			bdef.type = BodyType.StaticBody;
			
			if(object instanceof RectangleMapObject){
				RectangleMapObject rectangle = (RectangleMapObject)object; 
				shape = getRectangle(rectangle);
			}else if(object instanceof PolygonMapObject){
				shape = getPolygon((PolygonMapObject)object);
			}else if(object instanceof PolylineMapObject){
				shape = getPolyline((PolylineMapObject)object); 
			}else if(object instanceof CircleMapObject){
				shape = getCircle((CircleMapObject)object);
			}else if(object instanceof EllipseMapObject){
				shape = getEllipseMapObject((EllipseMapObject)object);
				isGoal = true; 
			}
			if(shape == null){
				System.out.println("Fail"); 
				continue; 
			}
			fDef.shape = shape; 
			fDef.filter.categoryBits = bits; 
			
			Body body = world.createBody(bdef); 
			if(isGoal){
				body.createFixture(fDef).setUserData("goal");
			}else{
				body.createFixture(fDef); 
			}
			bodies.add(body); 
			fDef.shape = null; 
			shape.dispose(); 
		}
	}
	
	private Shape getEllipseMapObject(EllipseMapObject object) {
		//Vet inte hur jag ska lösa denna
		Ellipse ellipse = object.getEllipse();
		if(ellipse.height == ellipse.width){
			CircleShape circleShape = new CircleShape(); 
			circleShape.setRadius((ellipse.width/2) /PPM); 
			circleShape.setPosition(new Vector2((ellipse.x + ellipse.width/2) /PPM, (ellipse.y + ellipse.width/2) / PPM));
			return circleShape; 
		}
		return null;
	}

	private Shape getCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle(); 
		CircleShape circleShape = new CircleShape(); 
		
		circleShape.setRadius(circle.radius/PPM); 
		circleShape.setPosition(new Vector2(circle.x /PPM, circle.y / PPM));

		return circleShape;
	}

	private Shape getPolyline(PolylineMapObject object) {
		float[] vertices = object.getPolyline().getTransformedVertices(); 
		Vector2[] worldVertices = new Vector2[vertices.length/2]; 
		
		for(int i =0;i < vertices.length/2; i++){
			worldVertices[i] = new Vector2(); 
			worldVertices[i].x = vertices[i * 2]/PPM;
			worldVertices[i].y = vertices[i * 2 + 1]/PPM; 
		}
		
		ChainShape chain = new ChainShape(); 
		chain.createChain(worldVertices); 
		return chain;
	}

	private Shape getPolygon(PolygonMapObject object) {
		PolygonShape polygon = new PolygonShape();
		float[] vertices = object.getPolygon().getTransformedVertices();
		float[] worldVertices = new float[vertices.length]; 
		
		for(int i = 0; i < vertices.length; i++){
			worldVertices[i] = vertices[i]/PPM; 
		}
		
		polygon.set(worldVertices); 
		return polygon;
	}

	private Shape getRectangle(RectangleMapObject rectangleObject){
		 Rectangle rectangle = rectangleObject.getRectangle(); 
		 PolygonShape polygon = new PolygonShape(); 
		 
		 Vector2 size = new Vector2(
				(rectangle.x + rectangle.width * 0.5f) /PPM,
				(rectangle.y + rectangle.height * 0.5f) /PPM);
		 
		 polygon.setAsBox(
				rectangle.width * 0.5f /PPM, 
		 		rectangle.height * 0.5f /PPM, 
		 		size,
		 		0.0f);
		 
		 return polygon; 
	}	
		
	public void destroyBodies(){
		for(Body body : bodies){
			world.destroyBody(body); 
		}
		bodies.clear(); 
	}

	public TiledMap getTiledMap() {
		return tileMap;
	}
}
