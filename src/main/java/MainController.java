import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MainController {

    @FXML private StackPane tile00;
    @FXML private Label lab00;
    @FXML private StackPane tile01;
    @FXML private Label lab01;
    @FXML private StackPane tile02;
    @FXML private Label lab02;
    @FXML private StackPane tile10;
    @FXML private Label lab10;
    @FXML private StackPane tile11;
    @FXML private Label lab11;
    @FXML private StackPane tile12;
    @FXML private Label lab12;
    @FXML private StackPane tile20;
    @FXML private Label lab20;
    @FXML private StackPane tile21;
    @FXML private Label lab21;
    @FXML private StackPane tile22;
    @FXML private Label lab22;

    StackPane[][] tiles;
    Label[][] labels;
    int curPlayer , tileFilled = 0;

    public void initialize() {
        labels = new Label[][] {
                {lab00, lab01, lab02},
                {lab10, lab11, lab12},
                {lab20, lab21, lab22}
        };
        tiles = new StackPane[][] {
                {tile00, tile01, tile02},
                {tile10, tile11, tile12},
                {tile20, tile21, tile22}
        };

        curPlayer = 1;

        for (StackPane[] row: tiles) {
            for (StackPane tile: row) {
                tile.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                tile.setCursor(Cursor.HAND);
                setOnMouseClickOnTile(tile);
            }
        }
    }

    private void setOnMouseClickOnTile(StackPane tile) {
        tile.setOnMouseClicked(event -> {

            Label curLabel = (Label) tile.getChildren().get(0);

            if (curLabel.getText().equals("")) {

                tileFilled ++;

                if (curPlayer == 1) {
                    tile.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                    curLabel.setText("X");
                    checkGameState(); //it is important to check game state before changing player
                    curPlayer = 2;

                } else {
                    tile.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
                    curLabel.setText("O");
                    checkGameState();
                    curPlayer = 1;
                }
            }
        });
    }

    private void checkGameState() {
        if (isWon()) {
            showAlert('w');
        } else if (tileFilled == 9) {
            showAlert('d');
        }
    }

    private void showAlert(char mode) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"", ButtonType.OK);
        String playerCode = (curPlayer == 1) ? "X" : "O";
        if (mode == 'w') {
            alert.setContentText("Player " + curPlayer + " with \"" + playerCode + "\"" + " has won.");
        } else if (mode == 'd') {
            alert.setContentText("No winner, Try Again.");
        }
        alert.showAndWait();
        restartGame();
    }

    private void restartGame() {
        for (StackPane[] row: tiles) {
            for (StackPane tile: row) {
                tile.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
        for (Label[] row: labels) {
            for (Label label: row) {
                label.setText("");
            }
        }
        curPlayer = 1;
        tileFilled = 0;
    }

    private boolean isWon() { //there can be a better way to do it by only checking the tile which is clicked but need to maintain the pos of that tile, but using simpler approach as we have only very limited cases
        return (lab00.getText().equals(lab01.getText()) && //for row 1
                lab00.getText().equals(lab02.getText()) &&
                !lab00.getText().equals("")) ||

                lab10.getText().equals(lab11.getText()) && //for row 2
                        lab10.getText().equals(lab12.getText()) &&
                        !lab10.getText().equals("") ||

                lab20.getText().equals(lab21.getText()) && //for row 3
                        lab20.getText().equals(lab22.getText()) &&
                        !lab20.getText().equals("") ||

                lab00.getText().equals(lab10.getText()) && //for col 1
                        lab00.getText().equals(lab20.getText()) &&
                        !lab00.getText().equals("") ||

                lab01.getText().equals(lab11.getText()) && //for col 2
                        lab01.getText().equals(lab21.getText()) &&
                        !lab01.getText().equals("") ||

                lab02.getText().equals(lab12.getText()) && //for col 3
                        lab02.getText().equals(lab22.getText()) &&
                        !lab02.getText().equals("") ||

                (!lab11.getText().equals("") && (( //diagonal check
                        lab00.getText().equals(lab11.getText()) &&
                                lab00.getText().equals(lab22.getText())
                ) || (
                        lab02.getText().equals(lab11.getText()) &&
                                lab02.getText().equals(lab20.getText())
                )));
    }
}
