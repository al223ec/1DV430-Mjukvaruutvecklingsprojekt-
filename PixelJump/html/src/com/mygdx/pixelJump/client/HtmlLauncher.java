package com.mygdx.pixelJump.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.pixelJump.PixelJump;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(PixelJump.WIDTH, PixelJump.HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new PixelJump();
        }
}