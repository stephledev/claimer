package ch.claimer.client.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class Main extends Application {

	Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	
	public void start(Stage primaryStage) {
		
//		Font.loadFont(
//			      CustomFontApp.class.getResource("xxx.TTF").toExternalForm(), 
//			      10);

		primaryStage.setTitle("Mängelmanager");
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource(
				"view/Login.fxml")); // FXML File kann von myLoader geladen werden
		
		try {
			Pane pane = (Pane) myLoader.load(); // FXML File wird auf das login-Pane geladen
			Scene login = new Scene(pane);
			login.getStylesheets().add("http://fonts.googleapis.com/css?family=Roboto+Condensed");
			login.getStylesheets().add(
					getClass().getResource("claimer_styles.css").toExternalForm()); // CSS-File wird geladen
			primaryStage.setScene(login);
			primaryStage.show();

		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
