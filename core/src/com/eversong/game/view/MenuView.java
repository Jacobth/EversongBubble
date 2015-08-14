package com.eversong.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.eversong.game.controller.Eversong;

/**
 * Created by jacobth on 2015-08-13.
 */
public class MenuView {

    private final SpriteBatch batch;
    private final Sprite background;
    private final Stage stage;
    private final OrthographicCamera camera;
    private final static int[] SCREEN_RESOLUTION = {Gdx.graphics.getWidth(), Gdx.graphics.getHeight()};
    private final static float DEFAULT_ALPHA = 1f;
    private BitmapFont font;


    private ImageButton playButton;

    public MenuView() {
        stage = new Stage();
        camera = new OrthographicCamera(SCREEN_RESOLUTION[0], SCREEN_RESOLUTION[1]);
        stage.getViewport().setCamera(camera);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("android/assets/test.fnt"));
        FileHandle backFileHandle = Gdx.files.internal("android/assets/background.png");
        Texture backgroundTexture = new Texture(backFileHandle);
        background = new Sprite(backgroundTexture);

        createPlay();
    }

    public void createPlay() {
        final Drawable playDrawable = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("android/assets/play.png"))));
        playButton = new ImageButton(playDrawable);
        playButton.setPosition(-playButton.getWidth() / 2, playButton.getHeight() / 2);
        playButton.setBounds(playButton.getX(), playButton.getY(), playButton.getWidth(), playButton.getHeight());
        stage.addActor(playButton);
    }

    public Stage getStage(){
        return stage;
    }

    public ImageButton getPlayButton(){
        return playButton;
    }

    public void update() {
        batch.begin();
        camera.update();
        batch.draw(background, -camera.viewportWidth / 2, -camera.viewportHeight / 2);
        draw();
        for(Actor a: stage.getActors()){
            a.draw(batch, DEFAULT_ALPHA);
        }
        batch.setProjectionMatrix(camera.combined);
        batch.end();
    }

    public void draw() {
        font.draw(batch, Eversong.highScore + "", 0 - font.getSpaceWidth()/2, 0 - font.getSpaceWidth());
    }
}
