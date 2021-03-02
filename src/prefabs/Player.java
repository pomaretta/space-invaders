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
import Objects.GameScript;
import controllers.PlayerController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import scenes.EntityType;

/**
 * @author Carlos Pomares
 */

public class Player extends GameEntity {

    private Image sprite;
    public GameScript playerController = new PlayerController(this);

    public Player(double x, double y, double w, double h){
        sprite = new Image(getClass().getResourceAsStream("/resources/sprites/player.png"));
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getVector2D().setMaxX(w);
        getVector2D().setMaxY(h);
        getCollider().setTag(EntityType.PLAYER);
    }

    @Override
    protected void start() {
        addScript(playerController);
        getVector2D().setZ(10);
        setColliderActive(true);
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawImage(sprite
                ,getVector2D().getPosX()
                ,getVector2D().getPosY()
                ,getVector2D().getMaxX()
                ,getVector2D().getMaxY());
    }

    public Image getSprite(){
        return sprite;
    }

}
