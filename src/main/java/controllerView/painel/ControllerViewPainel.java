package controllerView.painel;

import java.io.IOException;

import controller.ControllerAluno;
import controller.ControllerEndereco;
import controller.ControllerFuncionario;
import controller.ControllerProfessor;
import controllerView.aluno.ControllerViewListaAluno;
import controllerView.funcionario.ControllerViewListaFuncionario;
import controllerView.professor.ControllerViewCadastroProfessor;
import controllerView.professor.ControllerViewListaProfessor;
import dao.DaoAluno;
import dao.DaoEndereco;
import dao.DaoFuncionario;
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
import util.ShowAlert;

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
	    	if(new ShowAlert().confirmationAlert("Logout", "Tem certeza que deseja sair do sistema?")) {
	    		Stage stage = (Stage) btnAluno.getScene().getWindow(); 
	    		stage.close();
	    	}
	    }

	    @FXML
	    void btnIrAluno(ActionEvent event) {
	    	Stage stage = (Stage) btnAluno.getScene().getWindow(); 
		    ControllerViewListaAluno mudarTela = new ControllerViewListaAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
		    mudarTela.start(stage);
	    }

	    @FXML
	    void btnIrFuncionario(ActionEvent event) {
	    	Stage stage = (Stage) btnFuncionario.getScene().getWindow(); 
		    ControllerViewListaFuncionario mudarTela = new ControllerViewListaFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
		    mudarTela.start(stage);
	    }

	    @FXML
	    void btnIrProfessor(ActionEvent event) {
	    	Stage stage = (Stage) btnProfessor.getScene().getWindow(); 
		    ControllerViewListaProfessor mudarTela = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
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
