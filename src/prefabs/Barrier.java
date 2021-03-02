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
import controllers.BarrierController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import scenes.EntityType;

/**
 * @author Carlos Pomares
 */

public class Barrier extends GameEntity {

    private javafx.scene.image.Image sprite = new Image(getClass().getResourceAsStream("/resources/sprites/barrier.png"));
    public double life;

    public Barrier(double x, double y, double w, double h, double life) {
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getVector2D().setMaxX(w);
        getVector2D().setMaxY(h);
        getCollider().setTag(EntityType.PLAYER);
        this.life = life;
    }

    @Override
    protected void start() {
        addScript(new BarrierController(this));
        getVector2D().setZ(10);
        setColliderActive(true);
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        g.drawImage(
                sprite
                ,getVector2D().getPosX()
                ,getVector2D().getPosY()
                ,getVector2D().getMaxX()
                ,getVector2D().getMaxY()
        );
    }

    public Image getSprite() {
        return sprite;
    }

}
