package com.eversong.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.eversong.game.controller.Eversong;
import com.eversong.game.model.BounceBall;
import com.eversong.game.model.Player;

/**
 * Created by jaclun on 7/10/2015.
 */
public class BounceBallView {

    private Sprite sprite;
    private Sprite shieldSprite;
    private Body body;
    private Texture shieldTexture;
    private Player player;

    public void createBody(World world) {
        sprite = new Sprite(new Texture("android/assets/bounce.png"));
        sprite.setPosition(0, 0);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set((sprite.getX() +sprite.getWidth()/2)/ Eversong.SCALE, (sprite.getY() + sprite.getHeight()/2)/Eversong.SCALE);

        body = world.createBody(bodyDef);

        //Create the body as a circle
        CircleShape shape = new CircleShape();

        shape.setRadius(sprite.getWidth()/(2* Eversong.SCALE));


        //Set physical attributes to the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 2f;
        fixtureDef.friction = 0f;
        //Make the ball bounce on other bodies
        fixtureDef.restitution = 1f;

        body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void renderBall(SpriteBatch batch) {
        batch.begin();
        batch.draw(sprite, sprite.getX(), sprite.getY(), sprite.getOriginX(), sprite.getOriginY(),
                sprite.getWidth(), sprite.getHeight(), sprite.getScaleX(), sprite.getScaleY(), sprite.getRotation());
        batch.end();
    }

    public void setPosition(BounceBall ball) {
        ball.setPosition(body.getPosition().x * Eversong.SCALE - sprite.getWidth() / 2, body.getPosition().y * Eversong.SCALE - sprite.getHeight() / 2);
        sprite.setPosition(ball.getX(), ball.getY());
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }
    public Body getBody() {
        return body;
    }
    public Sprite getSprite() {
        return sprite;
    }
}