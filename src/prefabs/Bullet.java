package prefabs;

/*

    Project     Space Invaders
    Package     prefabs    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-01

    DESCRIPTION
    
*/

import Objects.Entity;
import Objects.GameEntity;
import controllers.BulletController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import scenes.EntityType;

/**
 * @author Carlos Pomares
 */

public class Bullet extends GameEntity {

    public EntityType target = EntityType.BULLET;

    public Bullet(double x, double y){
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getCollider().setTag(target);
        addScript(new BulletController(this));
    }

    public Bullet(double x, double y, EntityType targetType){
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getCollider().setTag(targetType);
        addScript(new BulletController(this));
        target = targetType;
    }

    public Bullet(double x, double y, double direction){
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getCollider().setTag(target);
        addScript(new BulletController(this,direction));
    }

    public Bullet(double x, double y, double direction, EntityType targetType){
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getCollider().setTag(targetType);
        addScript(new BulletController(this,direction));
        target = targetType;
    }

    @Override
    protected void start() {
        getVector2D().setZ(5);
        setColliderActive(true);
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.setFill(Color.WHITE);
        g.fillRect(getVector2D().getPosX(),getVector2D().getPosY(),5,15);
    }

}
