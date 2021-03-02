package prefabs;

/*

    Project     Space Invaders
    Package     prefabs    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-01

    DESCRIPTION
    
*/

import Objects.GameEntity;
import controllers.AlienController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import scenes.EntityType;

/**
 * @author Carlos Pomares
 */

public class Alien extends GameEntity {

    private double life;
    private javafx.scene.image.Image sprite;
    private AlienType type;

    public Alien(Image sprite, double x, double y, double w, double h, double life, AlienType type){
        this.sprite = sprite;
        this.life = life;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getVector2D().setMaxX(w);
        getVector2D().setMaxY(h);
        this.type = type;
        getCollider().setTag(EntityType.ALIEN);
    }

    @Override
    protected void start() {
        setColliderActive(true);
        addScript(new AlienController(this));
    }

    public double getLife() {
        return life;
    }

    public void damage(){
        this.life -= 1.0;
    }

    public Image getSprite() {
        return sprite;
    }

    public AlienType getType() {
        return type;
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawImage(sprite,getVector2D().getPosX(),getVector2D().getPosY(),getVector2D().getMaxX(),getVector2D().getMaxY());
    }


}
