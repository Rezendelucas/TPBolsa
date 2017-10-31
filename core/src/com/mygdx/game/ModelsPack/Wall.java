package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LucasRezende on 12/09/2017.
 */

public class Wall extends AbstractGameObject {

    public static final String TAG = Wall.class.getName();
    private TextureRegion regTexture;
    private Boolean solidObject = true;


    public Wall(TextureRegion texture) {
        init(texture);
    }

    public void init (TextureRegion texture) {
        dimension.set(1, 1);
        origin.set(dimension.x/2,dimension.y/2);
        rotation = 0;
        bounds.set(0, 0, dimension.x, dimension.y); //seta caixa de colisao
        regTexture = texture;
    }

    @Override
    public void render(SpriteBatch batch) {
        TextureRegion reg = null;
        // Draw image
        reg = regTexture;
        batch.draw(reg.getTexture(),
                getPosition().x , getPosition().y,
                origin.x, origin.y,
                dimension.x, dimension.y,
                scale.x, scale.y,
                rotation,
                reg.getRegionX(), reg.getRegionY(),
                reg.getRegionWidth(), reg.getRegionHeight(),
                false, false);
    }
}
