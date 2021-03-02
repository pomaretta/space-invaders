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
import Singleton.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import prefabs.Environment;
import prefabs.Text;

import java.util.ArrayList;

/**
 * @author Carlos Pomares
 */

public class UserInterface extends GameScene implements KeyListener {

    // BG
    private GameEntity background;

    // LOGO
    private GameEntity logo;
    private Image logoImage = new Image(getClass().getResourceAsStream("/resources/sprites/space-invaders-logo.png"));

    // OPTIONS
    private Text startGame;
    private Text options;
    private Text exit;

    private String[] optionsTexts = {"Start","Options","Exit"};
    private int selected;
    private ArrayList<KeyCode> keyCodes;

    @Override
    protected void init() {

        // BG
        background = new Environment(Color.BLACK,0,0,getMaxX(),getMaxY(),-10);

        // LOGO
        logo = new prefabs.Image(logoImage,getMaxX() / 2 - 150,getMaxY() / 2 - 250,300,150);

        // OPTIONS
        startGame = new Text("Start",getMaxX() / 2 - 35,getMaxY() / 2,28,Color.WHITE);
        options = new Text("Options",getMaxX() / 2 - 45,getMaxY() / 2 + 60,28,Color.WHITE);
        exit = new Text("Exit",getMaxX() / 2 - 25,getMaxY() / 2 + 120,28,Color.WHITE);

        keyCodes = new ArrayList<>();

        addEntity(logo);
        addEntity(background);
        addEntity(startGame);
        addEntity(options);
        addEntity(exit);

        listenKeys();
    }

    @Override
    protected void update(long l) {

        if(selected == 0){
            clearSelections();
            startGame.setText(setSelection(optionsTexts[0],startGame,-25));
        } else if (selected == 1){
            clearSelections();
            options.setText(setSelection(optionsTexts[1],options,-10));
        } else if (selected == 2){
            clearSelections();
            exit.setText(setSelection(optionsTexts[2],exit,-5));
        }

    }

    @Override
    public void listenKeys() {
        getCanvas().setFocusTraversable(true);

        getCanvas().setOnKeyReleased(e -> keyCodes.clear());
        getCanvas().setOnKeyPressed(e -> {
            keyCodes.add(e.getCode());
            for (KeyCode keyCode : keyCodes) {

                if(keyCode == KeyCode.W){
                    if(selected == 0){
                        selected = 2;
                    } else {
                        selected -= 1;
                    }
                }

                if(keyCode == KeyCode.S){
                    if(selected == 2){
                        selected = 0;
                    } else {
                        selected += 1;
                    }
                }

                if(keyCode == KeyCode.ENTER){
                    redirectOptions();
                }

            }
        });

    }

    private String setSelection(String option, GameEntity gameEntity, double resize){
        return String.format(">> %s <<",option);
    }

    private void clearSelections(){
        this.startGame.setText(this.optionsTexts[0]);
        this.options.setText(this.optionsTexts[1]);
        this.exit.setText(this.optionsTexts[2]);
    }

    private void redirectOptions(){

        if(selected == 0)
            gameHandler();

        if(selected == 1)
            optionsHandler();

        if(selected == 2)
            exitHandler();

    }

    private void gameHandler(){
        System.out.println("GAME HANDLER");
    }

    private void optionsHandler(){
        System.out.println("OPTIONS HANDLER");
    }

    private void exitHandler(){
        Platform.exit();
    }

}
