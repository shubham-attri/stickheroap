package stickero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {



    public static void main(String[] args){
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader menuscreen = new FXMLLoader(MainClass.class.getResource("MainClass.fxml"));

        Scene menuscreenscene = new Scene(menuscreen.load(),400,600);
        stage.setTitle("MenuScreen");
        stage.setScene(menuscreenscene);
        stage.show();

    }
}
