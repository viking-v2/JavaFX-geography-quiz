import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Window5 extends Application {
    private Button nextQuestion = new Button("Neste spørsmål");
    //Window4 window_4 = new Window4();
    Builder builder = new Builder();
    private YouWon youWon = new YouWon();
    private YouLost youLost = new YouLost();

    public void start(Stage fifthStage) {

        HBox questionBox = Builder.instance.returnQuestion("Hvilket land i Afrika er størt i Arial?");

        //Button answer-options
        ArrayList<Button> buttonArray = Builder.instance.returnButtons(4, 90, 20);
        buttonArray.get(0).setText("Algerie");
        buttonArray.get(1).setText("Sudan");
        buttonArray.get(2).setText("Libya");
        buttonArray.get(3).setText("Niger");

        //Button array and player-health
        HBox buttonHolder = Builder.instance.returnHBox(buttonArray, Pos.CENTER, 30, 15);
        HBox lifes = Builder.instance.returnLifeHolder();

        //Adding both elements above in a VBox
        VBox centerBox = new VBox();
        centerBox.getChildren().add(buttonHolder);
        centerBox.getChildren().add(lifes);
        centerBox.setAlignment(Pos.CENTER);

        //Sets text for when question is answered
        HBox alignTextBoxBottom = new HBox();
        Text descriptionTextBottom = Builder.instance.returnText("", "Arial", 25, Color.WHITE, TextAlignment.CENTER);
        nextQuestion.setOpacity(0);
        alignTextBoxBottom.getChildren().add(nextQuestion);

        //Elements within this box will appear depending on the answer
        HBox bottomBox = new HBox();
        bottomBox.setPrefWidth(600);
        bottomBox.getChildren().add(descriptionTextBottom);
        bottomBox.getChildren().add(nextQuestion);

        //Sets the different elements in a borderpane
        BorderPane border = new BorderPane();
        border.setCenter(centerBox);
        border.setTop(questionBox);
        border.setBottom(bottomBox);
        border.setStyle("-fx-background-color: #336699;");

        //Eventhandlers
        for(int i = 0; i < buttonArray.size(); i++)
            if (buttonArray.get(i) == buttonArray.get(0)) {
                buttonArray.get(i).setOnAction(event -> {
                    //Sets output depending on what button user clicked on and makes it visible
                    descriptionTextBottom.setText("Dette svaret er riktig!");
                    descriptionTextBottom.setOpacity(1);
                    //Sets spacing
                    bottomBox.setSpacing(255);
                    border.setBottom(bottomBox);
                    //Disables buttons
                    IntStream.range(0, buttonArray.size()).forEach(j -> buttonArray.get(j).setDisable(true));

                    nextQuestion.setOpacity(1);
                    nextQuestion.setOnAction(event12 -> {
                        fifthStage.close();
                        youWon.start(new Stage());
                    });
                });
            }
            else{
                buttonArray.get(i).setOnAction(event -> {
                    //Sets output depending on what button user clicked on and makes it visible
                    descriptionTextBottom.setText("Dette svaret er feil!");
                    descriptionTextBottom.setOpacity(1);
                    //Sets spacing (Yes, yes i know harcoding)
                    bottomBox.setSpacing(277);
                    border.setBottom(bottomBox);
                    //Disables buttons
                    IntStream.range(0, buttonArray.size()).forEach(j -> buttonArray.get(j).setDisable(true));
                    lifes.getChildren().remove(0);

                    if(lifes.getChildren().isEmpty()){
                        fifthStage.close();
                        youLost.start(new Stage());
                    }

                    nextQuestion.setOpacity(1);
                    nextQuestion.setOnAction(event12 -> {
                        fifthStage.close();
                        youWon.start(new Stage());
                    });
                });
            }

        fifthStage.setScene(new Scene(border, 600, 300));
        fifthStage.setResizable(false);
        fifthStage.show();
    }
}


