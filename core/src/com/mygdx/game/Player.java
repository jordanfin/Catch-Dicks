package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Buly on 19/07/2015.
 */
public class Player {

    //posicion en el mundo
    private float x,y;
    //private Vector2 playerpos;

    private float touchX,touchY;

    //texturas utilizadas
    private Texture img;
    private Texture heart;
    private Texture halfHeart;

    //boolean leftMove,rightMove;

    //limites de la imagen
    private Rectangle bounds;

    //vidas
    private int lives;
    ArrayList livesList;
    //puntaje
    private long score;

    //ShapeRenderer shape = new ShapeRenderer();

    public Player (String ruta){
        this.x = 250;
        this.y = 250;
        this.lives = 5;
        this.score = 0;
        this.livesList = new ArrayList();
        this.img =  new Texture(ruta);


    }

    public void render(SpriteBatch batch){

        batch.draw(this.getImg(), this.getX(), this.getY());
        renderLives(batch);
        update();
        //shape.begin(ShapeRenderer.ShapeType.Line);
       // shape.rect(bounds.getX(),bounds.getY(),bounds.getWidth(),bounds.getHeight());
       // shape.end();
    }

    public void renderLives(SpriteBatch batch){
        float x =32;
        float y =32;
        for (int i=0; i< livesList.size(); i++){

            Texture heart = ((Texture)livesList.get(i));
            batch.draw(heart,x,y);
            x+=32;
        }
    }

    public void update(){

        //bounds.setX(this.x);
        //bounds.setY(this.y);
        //playerpos.set(x,y);
    }

    public void setLeftMove(boolean t){
        //if(rightMove && t) rightMove = false;
        //leftMove = t;
    }

    public void setRightMove(boolean t){
        //if(leftMove && t) leftMove = false;
       // rightMove = t;
    }

    public void dragMouse(int x, int y){
        this.x = x-this.getImg().getWidth()*0.5f;
        this.y = y*-1+Gdx.graphics.getHeight()- this.getImg().getHeight()*0.5f;

    }

    public float getTouchX() {return touchX;}

    public void setTouchX(float touchX) {
        this.touchX = touchX;
    }

    public float getTouchY() {
        return touchY;
    }

    public void setTouchY(float touchY) {
        this.touchY = touchY;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {this.img = img;}

    public float getY() {return y;}

    public void setY(float y) {this.y = y;}

    public float getX() {return x;}

    public void setX(float x) {this.x = x;}

    public int getLives() {return lives;}

    public void setLives(int lives) {this.lives = lives;}

    public long getScore() {return score;}

    public void setScore(long score) {this.score = score;}

    public Rectangle getBounds(){
        bounds = new Rectangle(this.x,this.y,img.getWidth(),img.getHeight());
        return bounds;
    }

    public void resetTouch(){
        this.touchX=0;
        this.touchY=0;
    }

    public void initializeLives(){
        for(int i = 0; i<this.lives; i++){
            heart = new Texture("vidacompleta.png");
            livesList.add(heart);
        }
    }

    public void deleteHalfHeart(int index){
        halfHeart = new Texture("mediavida.png");
        livesList.set(index,halfHeart);
    }

}






