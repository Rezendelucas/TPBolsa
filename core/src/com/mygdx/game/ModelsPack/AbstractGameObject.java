package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by LucasRezende on 03/08/2017.
 */

public abstract class AbstractGameObject {
    //Atributos base
    public Body body;
    public Vector2 position;
    public Vector2 dimension;
    public Vector2 origin;
    public Vector2 scale;
    public float rotation;

    //atributos especiais
    public Vector2 velocity;
    public Vector2 terminalVelocity;
    public Vector2 friction;
    public Vector2 acceleration;
    public Rectangle bounds;

    public AbstractGameObject(){
        position = new Vector2(0,0);
        dimension = new Vector2(1,1);
        origin = new Vector2(0,0);
        scale = new Vector2(1,1);
        rotation = 0;
        velocity = new Vector2();
        terminalVelocity = new Vector2(1, 1);
        friction = new Vector2();
        acceleration = new Vector2();
        bounds = new Rectangle();
    }

    protected void updateMotionX (float delta) {
        if (velocity.x != 0) {
            if (velocity.x > 0) {//Atrito
                velocity.x = Math.max(velocity.x - friction.x * delta, 0);
            } else {
                velocity.x = Math.min(velocity.x + friction.x * delta, 0);
            }
        }
        //aceleraçao
        velocity.x += acceleration.x * delta;
        velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
    }

    protected void updateMotionY (float delta) {
        if (velocity.y != 0) {
            if (velocity.y > 0) {//Atrito
                velocity.y = Math.max(velocity.y - friction.y * delta, 0);
            } else {
                velocity.y = Math.min(velocity.y + friction.y * delta, 0);
            }
        }
        //aceleraçao
        velocity.y += acceleration.y * delta;
        velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
    }

    public void update(float delta){
        updateMotionX(delta);
        updateMotionY(delta);
        // Movimentação
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
    }

    public abstract void render(SpriteBatch batch);

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getDimension() {
        return dimension;
    }

    public void setDimension(Vector2 dimension) {
        this.dimension = dimension;
    }

    public Vector2 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector2 origin) {
        this.origin = origin;
    }

    public Vector2 getScale() {
        return scale;
    }

    public void setScale(Vector2 scale) {
        this.scale = scale;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getTerminalVelocity() {
        return terminalVelocity;
    }

    public void setTerminalVelocity(Vector2 terminalVelocity) {
        this.terminalVelocity = terminalVelocity;
    }

    public Vector2 getFriction() {
        return friction;
    }

    public void setFriction(Vector2 friction) {
        this.friction = friction;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
