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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Carlos Pomares
 */

public class Text extends GameEntity {

    private String text;
    private Color color;
    private Font font;

    public Text(String text, double x, double y) {
        this.text = text;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
    }

    public Text(String text, double x, double y, Color color) {
        this.text = text;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        this.color = color;
    }

    public Text(String text, double x, double y, double size) {
        this.text = text;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        this.font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/minecraft.ttf"),size);
    }

    public Text(String text,double x, double y,double size, Color color) {
        this.text = text;
        getVector2D().setPosX(x);
        getVector2D().setPosY(y);
        this.font = Font.loadFont(getClass().getResourceAsStream("/resources/fonts/minecraft.ttf"),size);
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void start() {

    }

    @Override
    public void render(GraphicsContext g) {
        super.render(g);
        if(color != null)
            g.setFill(color);
        g.setFont(font);
        g.fillText(text,getVector2D().getPosX(),getVector2D().getPosY());
    }

}
