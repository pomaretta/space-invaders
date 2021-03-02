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
import javafx.scene.canvas.GraphicsContext;

/**
 * @author Carlos Pomares
 */

public class Image extends GameEntity {

    private javafx.scene.image.Image img;

    public Image(javafx.scene.image.Image image, double x, double y){
        img = image;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
    }

    public Image(javafx.scene.image.Image image, double x, double y, double w, double h){
        img = image;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        getVector2D().setMaxX(w);
        getVector2D().setMaxY(h);
    }

    @Override
    protected void start() {

    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        if(getVector2D().getMaxX() > 0 && getVector2D().getMaxY() > 0){
            g.drawImage(img,getVector2D().getPosX(),getVector2D().getPosY(),getVector2D().getMaxX(),getVector2D().getMaxY());
        } else {
            g.drawImage(img,getVector2D().getPosX(),getVector2D().getPosY());
        }
    }
}
