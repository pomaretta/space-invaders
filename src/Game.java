/*

    Project     Space Invaders
        
    
    Version     1.0      
    Author      Carlos Pomares
    Date        2021-03-01

    DESCRIPTION
    
*/

import Manager.SceneManager;
import Objects.GameScene;
import Utilities.GameInfo;
import scenes.PrimaryScene;
import scenes.UserInterface;

/**
 * @author Carlos Pomares
 */

public class Game {

    public static GameScene gameUI = new UserInterface();
    public static GameScene primaryScene = new PrimaryScene();

    public static void main(String[] args) throws Exception {

        Singleton.Game.init(args);
        Singleton.Game.setGameInfo(new GameInfo("Space Invaders","1.0","Carlos Pomares"));
        Singleton.Game.getSceneManager().setSize(600,600);
        SceneManager.changeScene(
                Singleton.Game.getSceneManager()
                ,primaryScene
        );

        Singleton.Game.start();

    }

}
