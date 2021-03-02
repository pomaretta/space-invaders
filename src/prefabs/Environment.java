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
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * @author Carlos Pomares
 */

public class Environment extends GameEntity {

    private javafx.scene.image.Image image;
    private Color color;
    private double x,y,w,h;
    private int z;

    public Environment(Color color, double x, double y, double w, double h, int z) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.z = z;
    }

    public Environment(Image image, double x, double y, double w, double h, int z) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.z = z;
    }

    @Override
    protected void start() {
        getVector2D().setZ(z);
    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        if(image != null){
            g.drawImage(image,x,y,w,h);
        } else {
            g.setFill(color);
            g.fillRect(x,y,w,h);
        }
    }
}
