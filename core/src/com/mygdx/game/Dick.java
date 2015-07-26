package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;


/**
 * Created by Buly on 19/07/2015.
 */
public class Dick {

    //posicion del objeto en el mundo
    private float x, y;

    //velocidad
    private float velocity;
    private float velMin = 0.5f;
    private float velMax = 5;

    //textura que utiliza
    private Texture img;

    //dimensiones de la pantalla
    private int screenH = Gdx.graphics.getHeight();
    private int screenW = Gdx.graphics.getWidth();

    //ancho y alto de la imagen
    private int imgHeight;
    private int imgWidth;

    //limites de la imagen(para colisiones)
    private Rectangle bounds;

    ShapeRenderer shape = new ShapeRenderer();



    public Dick(String ruta, int w, int h, float v) {
        this.x = 0;
        this.y = 0;
        this.velocity = v;
        this.imgWidth = w;
        this.imgHeight = h;

        img = new Texture(ruta);
        bounds = new Rectangle(this.x,this.y,w,h);

    }

    public void render(SpriteBatch batch){
        //dibujo la imagen
        batch.draw(img, this.x, this.y, this.imgWidth, this.imgHeight);

        //shape.begin(ShapeRenderer.ShapeType.Line);
        //shape.rect(this.x,this.y,imgWidth,imgHeight);
        //shape.end();
    }

    public void update() {

        bounds.setX(this.x);
        bounds.setY(this.y);
        move();
        //checkWorldBounds();


    }

    public void move() {

        this.y -= velocity;

    }

    public void resetRandomPosition() {
        this.setX(MathUtils.random(0, screenW - (this.getImg().getWidth() * 0.5f)));
        this.setY(MathUtils.random(screenH - 50, screenH));
        this.velocity = MathUtils.random(velMin,velMax);

    }

    public boolean checkColissionWithPlayer(float x, float y){
        boolean collide = false;

        if (this.getBounds().contains(x,y)){
            collide = true;
        }

        return collide;

    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public Rectangle getBounds(){
        bounds = new Rectangle(this.x,this.y,this.imgWidth,this.imgHeight);
        return bounds;}

}