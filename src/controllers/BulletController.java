package controllers;

/*

    Project     Space Invaders
    Package     controllers    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-01

    DESCRIPTION
    
*/

import Objects.GameEntity;
import Objects.GameScript;
import javafx.scene.input.KeyEvent;
import prefabs.Bullet;

import java.util.Iterator;

/**
 * @author Carlos Pomares
 */

public class BulletController extends GameScript {

    private double x,y, velY;
    private Bullet bullet;

    public BulletController(Bullet entity) {
        super(entity);
        bullet = entity;
    }

    public BulletController(Bullet entity, double direction) {
        super(entity);
        bullet = entity;
        velY = direction;
    }

    public BulletController(GameEntity entity, double direction) {
        super(entity);
        velY = direction;
    }

    @Override
    public void start() {
        x = bullet.getVector2D().getPosX();
        y = bullet.getVector2D().getPosY();
        if(velY == 0) {
            velY = -2;
        }
    }

    @Override
    public void update() {

        if(bullet.getCollider().isCollision() && bullet.getCollider().getCollisionTo() == bullet.target){
            bullet.getGameScene().getEntities().removeIf(tmp -> tmp.equals(bullet));
        }

        if(y <= bullet.getGameScene().getMinY()) {
            bullet.getGameScene().getEntities().removeIf(tmp -> tmp.equals(bullet));
        }

        if(y >= bullet.getGameScene().getMaxY()){
            bullet.getGameScene().getEntities().removeIf(tmp -> tmp.equals(bullet));
        }

        y += velY;

        bullet.getVector2D().setPosY(y);

    }

}
