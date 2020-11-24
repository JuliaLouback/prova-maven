package application;

import java.io.IOException;

import controllerView.painel.ControllerViewPainel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application{

	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/painel/Painel.fxml"));
	        ControllerViewPainel controller = new ControllerViewPainel();
	        loader.setController(controller);
	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Painel - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
