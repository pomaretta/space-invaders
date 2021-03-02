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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import prefabs.Bullet;
import scenes.EntityType;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class PlayerController extends GameScript {

    private ArrayList<KeyCode> keyCodes;
    private ArrayList<Bullet> bullets;
    private double x,velX;
    private double velocity = 2;
    private int shoots;
    private long time;
    private long timeElapsed;
    private long timeBetweenShoots = 1;
    private int playerLife = 1;
    private int collisionCount = 0;

    public PlayerController(GameEntity entity) {
        super(entity);
        keyCodes = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    @Override
    public void start() {
        x = entity.getVector2D().getPosX();
    }

    @Override
    public void update() {

        x += velX;

        entity.getVector2D().setPosX(x);

        if(entity.getCollider().isCollision() && entity.getCollider().getCollisionFrom() == EntityType.ALIEN){
            collisionCount += 1;
            if(collisionCount == 5){
                damage();
            }
        }

        if(playerLife <= 0){
            entity.getGameScene().getEntities().removeIf(tmp -> tmp.equals(entity));
        }

    }

    @Override
    public void onKeyPressed(KeyEvent keyEvent) {

        keyCodes.add(keyEvent.getCode());

        keyCodes.forEach(key -> {

            if(key == KeyCode.A){
                velX = -velocity;
            }

            if(key == KeyCode.D){
                velX = velocity;
            }

            if(key == KeyCode.SPACE){
                this.timeElapsed = entity.getGameScene().getGameTime() - this.time;
                if(timeElapsed > timeBetweenShoots && playerLife > 0){
                    shot();
                }
            }

        });

    }

    private void damage(){
        this.playerLife--;
        collisionCount = 0;
    }

    private void shot(){
        this.time = entity.getGameScene().getGameTime();
        shoots += 1;
        bullets.add(new Bullet(entity.getVector2D().getPosX() + 18
                ,entity.getVector2D().getPosY() - 25
                ,EntityType.PLAYER
        ));
        updateBullets();
    }

    private void updateBullets(){
        for(Bullet bullet : bullets){
            entity.getGameScene().addEntity(bullet);
            bullet.init();
        }
        bullets.clear();
    }

    @Override
    public void onKeyReleased(KeyEvent keyEvent) {
        keyCodes.clear();
        velX = 0;
    }

    @Override
    public void onKeyTyped(KeyEvent keyEvent) {

    }



}
