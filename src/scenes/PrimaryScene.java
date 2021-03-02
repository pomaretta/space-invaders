package scenes;

/*

    Project     Space Invaders
    Package     scenes    
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-01

    DESCRIPTION
    
*/

import Events.KeyListener;
import Objects.GameEntity;
import Objects.GameScene;
import Objects.MonoBehaviour;
import Physics.Collider;
import Physics.ColliderListener;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import prefabs.*;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class PrimaryScene extends GameScene implements KeyListener, ColliderListener {

    private Environment background;

    // ALIENS
    private ArrayList<Alien> aliens = new ArrayList<>();

    // BARRIERS
    private ArrayList<Barrier> barriers = new ArrayList<>();

    // Player
    public static Player player;

    @Override
    protected void init() {

        background = new Environment(Color.BLACK,0,0,getMaxX(),getMaxY(),-10);
        player = new Player(this.getMaxX() / 2 - 16,this.getMaxY() - (70 + 16), 32,32);

        generateAliens(30,8,80,100,getMaxX() - 20,150,16,16,1.0,AlienType.NORMAL);
        generateBarriers(4,120,getMaxY() - 160,getMaxX() - 150,32,32,3.0);

        aliens.forEach(this::addEntity);
        barriers.forEach(this::addEntity);
        addEntity(background);
        addEntity(player);


        listenKeys();
    }

    @Override
    protected void update(long l) {
        checkCollisions();
    }

    @Override
    public void listenKeys() {
        getCanvas().setFocusTraversable(true);
        getCanvas().setOnKeyReleased(e -> player.getScripts().forEach(script -> script.onKeyReleased(e)));
        getCanvas().setOnKeyPressed(e -> player.getScripts().forEach(script -> script.onKeyPressed(e)));
    }

    @Override
    public void checkCollisions() {
        Collider.checkCollisions(this);
    }

    private void generateAliens(int maxAliens, int aliensInRow, double initialX, double initialY, double maxX, double maxY, double w, double h, double life, AlienType type){

        double x = initialX , y = initialY;
        double currentX = initialX, currentY = initialY;
        int maxAliensPerRow = aliensInRow;

        double spacingX = maxX / maxAliensPerRow;
        double spacingY = maxY / (maxAliens / maxAliensPerRow);

        for (int i = 0; i < maxAliens; i++) {
            aliens.add(new Alien(
                    new Image(getClass().getResourceAsStream("/resources/sprites/alien-1.png"))
                    ,currentX
                    ,currentY
                    ,w
                    ,h
                    ,life
                    ,type
            ));
            if(currentX >= maxX - 100){
                currentX = initialX;
                currentY += spacingY;
            } else {
                currentX += spacingX;
            }
        }
    }

    private void generateBarriers(int maxBarriers, double inititalX, double y, double maxX, double w, double h, double life){

        double x = inititalX;
        double currentX = x;

        double spacingX = maxX / maxBarriers;

        for (int i = 0; i < maxBarriers; i++) {
            barriers.add(new Barrier(
               currentX,y,w,h,life
            ));
            currentX += spacingX;
        }

    }

}
