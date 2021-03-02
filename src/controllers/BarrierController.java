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
import prefabs.Barrier;
import scenes.EntityType;

/**
 * @author Carlos Pomares
 */

public class BarrierController extends GameScript {

    private Barrier barrier;
    private int collisionCount;

    public BarrierController(Barrier entity) {
        super(entity);
        barrier = entity;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

        System.out.println(this.barrier.life);

        if(entity.getCollider().isCollision() && entity.getCollider().getCollisionFrom() == EntityType.ALIEN){
            collisionCount++;
            if(collisionCount == 5){
                damage();
            }
        }

        if(this.barrier.life <= 0){
            entity.getGameScene().getEntities().removeIf(tmp -> tmp.equals(entity));
        }

    }

    private void damage(){
        this.barrier.life--;
        collisionCount = 0;
    }

}
