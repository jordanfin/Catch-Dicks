package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	//Screen
	int WIDTH,HEIGHT;

	//render
	SpriteBatch batch;
	Texture background;
	Texture scoreScreen;

	//Actores
	Player player;
	Dick enemy;

	//lista que contiene todos los enemigos.
	ArrayList enemyList;
	int cantEnemys;
	int aux;

	//Textos
	BitmapFont score;
	CharSequence scoreText;
	Long myScore;

	//Sonidos
	Sound eat;

	//HandleInput
	MyInputProcessor inputProcessor;

	//fin del juego
	boolean endGame;

	//Stage
	Stage stage;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		//player
		player  = new Player ("nadina.jpg");
		//fondo
		background = new Texture ("uncoma.gif");
		scoreScreen = new Texture("backgroundscore.png");

		//puntaje
		score = new BitmapFont();
		myScore = 0L;
		scoreText ="SCORE: "+myScore;
		//vida
		aux = 1;
		//enemigos
		cantEnemys = 5;
		enemyList = new ArrayList();
		//carga los enemigos
		initializeEnemys(enemyList);
		//inicializa las vidas
		player.initializeLives();
		//sonidos
		eat = Gdx.audio.newSound(Gdx.files.internal("eat.mp3"));

		//bandera para que termine el juego.
		endGame = false;
		//input
		inputProcessor = new MyInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);


	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.3f, 0.4f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		//dibujo el fondo
		batch.draw(background, (WIDTH * 0.5f) - (background.getWidth() * 0.5f) + 100, (HEIGHT * 0.5f) - (background.getHeight() * 0.5f) + 100, 550, 550);
		//Cada jugador se renderiza a si mismo y sus vidas

		if (!endGame){
		//Render Dicks
		renderDicks();
		//dibujo las vidas
		player.render(batch);
		//dibujo el score
		score.draw(batch, scoreText, WIDTH - 128, 32);

		//actualiza el estado del mundo
		update();

		}
		else {
			scoreScreen();
		}
		batch.end();

	}

	public void update(){
		//controla las colisiones
		//checkOverlapColission();
		checkLifeStatus();
		scoreText="SCORE: "+myScore;
		if (player.livesList.isEmpty()){
			endGame();
		}
	}

	@Override
	public void dispose(){}

	public void checkOverlapColission(){
		//Metodo que detecta colisiones entre el jugador y el enemigo mediante overlap
			for (int i=1;i<enemyList.size();i++) {
				Dick enemy = ((Dick) enemyList.get(i));
					if (player.getBounds().overlaps(enemy.getBounds())) {
					eat.play();
					myScore += 10;
					enemy.resetRandomPosition();
				}

			}

	}

	public void checkClickColission(float x, float y){
		//Metodo que detecta la colision cuando el jugador clickea encima del enemigo.

		y = y*-1+Gdx.graphics.getHeight();
		for (int i=1;i<enemyList.size();i++) {
			Dick enemy = ((Dick) enemyList.get(i));

			if (enemy.getBounds().contains(x, y)){
				eat.play();
				myScore += 10;

				enemy.resetRandomPosition();

			}
		}

	}


	public void checkLifeStatus(){
		int index;

		for (int i =1; i< enemyList.size();i++){

			Dick enemy = ((Dick)enemyList.get(i));
				if (enemy.getY()+ enemy.getBounds().getHeight() < 0) {
					enemy.resetRandomPosition();
					try {
						index = player.livesList.size() - 1;
						if (!player.livesList.isEmpty()) {
						if (aux == 0){
							player.livesList.remove(index);
							aux = 1;
						}else{
							player.deleteHalfHeart(index);
							aux=0;
						}

						}
					}
					catch(Exception e){

					}
				}
		}
	}


	public void renderDicks(){
		for (int i=1; i<enemyList.size();i++) {
			enemy = ((Dick) enemyList.get(i));
			//dibujo el enemigo.
			enemy.render(batch);
			//actualizo el estado del enemigo
			enemy.update();

		}
	}

	public void initializeEnemys (ArrayList enemyList){
		//
		Dick enemy;
		for (int i = 0;i<=cantEnemys;i++){
			int dim = MathUtils.random(100,150);
			float vel = MathUtils.random(0.5f, 5);

			enemy = new Dick("dick.png",dim,dim, vel);
			enemy.setX(MathUtils.random(0, WIDTH - (enemy.getImg().getWidth()*0.5f)));
			enemy.setY(MathUtils.random(HEIGHT - 50, HEIGHT));

			enemyList.add(i, enemy);
		}
	}


	public void endGame(){

		endGame = true;
		destroyEnemys(enemyList);
		eat.dispose();


	}
	public void destroyEnemys(ArrayList list){
		int size = list.size();
		for (int i = 0;i<size;i++){
			try {
				list.remove(i);
			}catch(Exception e){}
		}
	}

	public void scoreScreen(){

		batch.draw(scoreScreen,50,50);
	}


}

