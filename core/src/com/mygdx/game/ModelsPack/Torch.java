package com.mygdx.game.ModelsPack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by LucasRezende on 30/10/2017.
 */

public class Torch extends AbstractGameObject{

    public static final String TAG = Torch.class.getName();
    private TextureRegion regTexture;
    public boolean torch_On = false;

    public Torch(TextureRegion texture) {
        init(texture);
    }

    private void init(TextureRegion texture) {
        dimension.set(1, 3);
        origin.set(dimension.x/2,dimension.y/2);
        rotation = 0;
        bounds.set(0, 0, dimension.x, dimension.y); //seta caixa de colisao
        regTexture = texture;
    }

    public void switch_State(){
            if(torch_On)
                regTexture = Assets.getInstance().torch.torch_off;
            else
                regTexture = Assets.getInstance().torch.torch_on;
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
