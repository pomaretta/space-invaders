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
import Objects.GameScene;
import Objects.GameScript;
import javafx.scene.input.KeyEvent;
import prefabs.Alien;
import prefabs.Bullet;
import scenes.EntityType;
import scenes.PrimaryScene;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class AlienController extends GameScript {

    private Alien alien;
    private boolean damage = false;
    private int collisionCount = 0;
    private ArrayList<Bullet> bullets;
    private double defaultVelX = 0.2, defaultVelY = 0.2;
    private double velX = 0, velY = 0;

    private boolean left, right;
    private boolean uppper, bottom;

    private boolean firstLeft, firstRight,firstUpper,firstBottom;

    private double initialX, initialY;

    public AlienController(Alien alien){
        super(alien);
        this.alien = alien;
        bullets = new ArrayList<>();
    }

    @Override
    public void start() {

        initialX = alien.getVector2D().getPosX();
        initialY = alien.getVector2D().getPosY();

    }

    @Override
    public void update() {

        if(alien.getCollider().isCollision() && alien.getCollider().getCollisionFrom() == EntityType.PLAYER){
            collisionCount += 1;
            if(collisionCount == 5){
                alien.damage();
            }
        }

        if(alien.getLife() <= 0) {
            alien.getGameScene().getEntities().removeIf(tmp -> tmp.equals(entity));
        }

        if(!alien.getCollider().isCollision()){
            collisionCount = 0;
        }

        double playerX;
        playerX = PrimaryScene.player.getVector2D().getPosX();

        if(
                (alien.getVector2D().getPosX() - 50)
                >= (playerX - 100)
                &&
                (alien.getVector2D().getPosX() + alien.getVector2D().getMaxX() + 50)
                <= (playerX + (32 + 100))
        ){
            routine();
        }

        //right = true;
        //move();

    }

    private void routine(){

        double probability = Math.random();

        if(probability < 0.001){
            shot();
        }

    }

    private void shot(){
        bullets.add(new Bullet(entity.getVector2D().getPosX() + entity.getVector2D().getMaxX() - 15
                ,entity.getVector2D().getPosY() + entity.getVector2D().getMaxY() + 15, 2
                , EntityType.ALIEN
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

    private void move(){

        double x, y;

        if(right)
            velX = defaultVelX;

        if(left)
            velX = -defaultVelX;

        if(uppper)
            velY = -defaultVelY;

        if(bottom)
            velY = Math.abs(defaultVelY);

        velX += alien.getGameScene().getGameTime() / 10000;
        velY += alien.getGameScene().getGameTime() / 10000;

        // RIGHT
        if(alien.getVector2D().getPosX() - initialX >= 50){
            if(!firstRight){
                right = false;
                velX = 0;
                bottom = true;
                firstRight = true;
                firstLeft = false;
            }
            System.out.println(velX);
        }

        // LEFT
        if(initialX - alien.getVector2D().getPosX() >= 50){
            if(!firstLeft){
                left = false;
                velX = 0;
                uppper = true;
                firstLeft = true;
                firstRight = false;
            }
            //System.out.println("RIGHT");
        }

        // LOWER
        if(alien.getVector2D().getPosY() - initialY >= 50){
            if(!firstBottom){
                bottom = false;
                velY = 0;
                left = true;
                firstBottom = true;
                firstUpper = false;
            }
        }

        // UPPER
        if(initialY - alien.getVector2D().getPosY() >= 50){
            if(!firstUpper){
                uppper = false;
                velY = 0;
                right = true;
                firstUpper = true;
                firstBottom = false;
            }
        }

        x = alien.getVector2D().getPosX();
        y = alien.getVector2D().getPosY();

        x += velX;
        y += velY;

        alien.getVector2D().setPosX(x);
        alien.getVector2D().setPosY(y);

    }

    public Alien getAlien() {
        return alien;
    }

}
