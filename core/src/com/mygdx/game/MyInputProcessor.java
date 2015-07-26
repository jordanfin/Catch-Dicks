package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Buly on 19/07/2015.
 */
public class MyInputProcessor implements InputProcessor {
    //private Player player;
    private MyGdxGame game;

    public MyInputProcessor (MyGdxGame game){
        super();
        this.game = game;
        //this.player = player;

    }
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                game.player.setLeftMove(true);
                break;
            case Input.Keys.RIGHT:
                game.player.setRightMove(true);
                break;

        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode){
            case Input.Keys.LEFT:
                game.player.setLeftMove(false);
                break;
            case Input.Keys.RIGHT:
                game.player.setRightMove(false);
                break;

        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        game.checkClickColission(screenX,screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            //player.touchDown(-1,-1);
        return false;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        game.player.dragMouse(screenX,screenY);

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
