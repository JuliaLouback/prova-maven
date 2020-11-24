package controllerView.painel;

import java.io.IOException;

import controller.ControllerEndereco;
import controller.ControllerProfessor;
import controllerView.professor.ControllerViewCadastroProfessor;
import controllerView.professor.ControllerViewListaProfessor;
import dao.DaoEndereco;
import dao.DaoProfessor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControllerViewPainel {
	  @FXML
	    private Button btnAluno;

	    @FXML
	    private Label NomeFunc;

	    @FXML
	    private Button btnProfessor;

	    @FXML
	    private Button btnFuncionario;

	    @FXML
	    void Sair(ActionEvent event) {

	    }

	    @FXML
	    void btnIrAluno(ActionEvent event) {

	    }

	    @FXML
	    void btnIrFuncionario(ActionEvent event) {

	    }

	    @FXML
	    void btnIrProfessor(ActionEvent event) {
	    	Stage stage = (Stage) btnAluno.getScene().getWindow(); 
		    ControllerViewListaProfessor mudarTela = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()));
		    mudarTela.start(stage);
	    }
	    
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
	    
}
