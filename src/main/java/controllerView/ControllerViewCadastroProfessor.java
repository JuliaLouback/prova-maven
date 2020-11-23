package controllerView;

import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerEndereco;
import controller.ControllerProfessor;
import entity.Professor;
import enumType.Cursos;
import enumType.Materias;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.Endereco;

public class ControllerViewCadastroProfessor implements Initializable{

	@FXML
    private TextField Nome;

    @FXML
    private TextField Cpf;

    @FXML
    private TextField Email;

    @FXML
    private TextField NR;
    
    @FXML
    private TextField Cep;

    @FXML
    private TextField Rua;

    @FXML
    private TextField Numero;

    @FXML
    private TextField Bairro;

    @FXML
    private TextField Cidade;

    @FXML
    private TextField Estado;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Label labelChange;

    @FXML
    private DatePicker Data_nascimento;

    @FXML
    private ComboBox<Cursos> Curso;

    @FXML
    private ComboBox<Materias> Materia;
    
    private ControllerProfessor controllerProfessor;

    private ControllerEndereco controllerEndereco;
    
    public ControllerViewCadastroProfessor(ControllerProfessor controllerProfessor, ControllerEndereco controllerEndereco) {
    	this.controllerProfessor = controllerProfessor;
    	this.controllerEndereco = controllerEndereco;
    }
    
    
    @FXML
    void BackButton(ActionEvent event) {

    }

    @FXML
    void Sair(ActionEvent event) {

    }

    @FXML
    void VoltarPainel(ActionEvent event) {

    }

    @FXML
    void addFuncionario(ActionEvent event) {
    	
    	Professor professor = new Professor();
    	Endereco  endereco 	= new Endereco();
    	
    	endereco.setCep(Cep.getText());
    	endereco.setNumero(Integer.valueOf(Numero.getText()));
    	endereco.setRua(Rua.getText());
    	endereco.setBairro(Bairro.getText());
    	endereco.setCidade(Cidade.getText());
    	endereco.setEstado(Estado.getText());
	 
    	professor.setCPF(Cpf.getText());
    	professor.setNR(Integer.parseInt(NR.getText()));
    	professor.setNome(Nome.getText());
    	professor.setEmail(Email.getText());
    	professor.setCurso(Curso.getValue().toString());
    	professor.setMateria(Materia.getValue().toString());
    	professor.setId_endereco(controllerEndereco.adicionar(endereco));
		professor.setData_nascimento(Data_nascimento.getValue());
    	professor.setEndereco(endereco);
    
    	controllerProfessor.adicionar(professor);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Curso.getItems().setAll(Cursos.values());
		Materia.getItems().setAll(Materias.values());

	}

}
