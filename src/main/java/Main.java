import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tic Tac Toe");
        stage.getIcons().add(new Image("icon.png"));

        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"));

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert root != null;
        stage.setScene(new Scene(root));
        stage.show();
    }
}
