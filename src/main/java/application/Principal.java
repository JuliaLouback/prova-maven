package application;

import controller.ControllerEndereco;
import controller.ControllerProfessor;
import controllerView.ControllerViewCadastroProfessor;
import dao.DaoEndereco;
import dao.DaoProfessor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor/CadastroProfessor.fxml"));

	        ControllerViewCadastroProfessor controller = new ControllerViewCadastroProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
	        
	        loader.setController(controller);
	        
	        AnchorPane pane = loader.load();
		        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Professor - Uni Universidade");
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
