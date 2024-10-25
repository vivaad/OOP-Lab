import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class q2 extends Application {
   public void start(Stage primaryStage) {
       Label instructionLabel = new Label("Enter an integer: ");
       TextField inputField = new TextField();
       Button calculateButton = new Button("Show Multiplication Table");
       Label resultLabel = new Label();


       calculateButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               try {
                   // Getting the input value from the TextField
                   int number = Integer.parseInt(inputField.getText());
                   String result = "Multiplication Table for " + number + ":\n";
                   for (int i = 1; i <= 10; i++) {
                       result += number + " * " + i + " = " + (number * i) + "\n";
                   }
                   resultLabel.setText(result);
               } catch (NumberFormatException ex) {
                   // Handling the case where the user doesn't enter a valid integer
                   resultLabel.setText("Please enter a valid integer.");
               }
           }
       });


       FlowPane flowPane = new FlowPane();
       flowPane.setHgap(10);    
       flowPane.setVgap(10);    
       flowPane.getChildren().addAll(instructionLabel, inputField, calculateButton, resultLabel);


       Scene scene = new Scene(flowPane, 400, 300);    
       primaryStage.setTitle("Multiplication Table App");
       primaryStage.setScene(scene);
       primaryStage.show();
   }


   public static void main(String[] args) {
       launch(args);  
   }
}
