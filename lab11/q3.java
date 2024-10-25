import javafx.application.Application;
import javafx.event.ActionEvent;  // For ActionEvent handling
import javafx.event.EventHandler; // For EventHandler class
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class q3 extends Application {
   public void start(Stage primaryStage) {
       // Create a GridPane layout
       GridPane gridPane = new GridPane();
       gridPane.setHgap(10);
       gridPane.setVgap(10); 


       Label welcomeLabel = new Label("Welcome");
       Label userNameLabel = new Label("User Name:");
       Label passwordLabel = new Label("Password:");
       Label welcomeMessage = new Label();


       TextField userNameField = new TextField();
       PasswordField passwordField = new PasswordField();


       Button signInButton = new Button("Sign in");


       signInButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent event) {
               String userName = userNameField.getText();
               welcomeMessage.setText("Welcome " + userName);
           }
       });
       gridPane.add(welcomeLabel, 0, 0);             
       gridPane.add(userNameLabel, 0, 1);            
       gridPane.add(userNameField, 1, 1);            
       gridPane.add(passwordLabel, 0, 2);           
       gridPane.add(passwordField, 1, 2);            
       gridPane.add(signInButton, 1, 3);           
       gridPane.add(welcomeMessage, 0, 4, 2, 1);
       Scene scene = new Scene(gridPane, 300, 200);
       primaryStage.setTitle("JavaFX Welcome");
       primaryStage.setScene(scene);
       primaryStage.show();
   }


   public static void main(String[] args) {
       launch(args);
   }
}
