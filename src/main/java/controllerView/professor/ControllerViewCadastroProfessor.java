package controllerView.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerEndereco;
import controller.ControllerProfessor;
import controllerView.painel.ControllerViewPainel;
import dao.DaoEndereco;
import dao.DaoProfessor;
import entity.Professor;
import enumType.Cursos;
import enumType.Materias;
import interfaces.ICrud;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.BuscaCep;
import util.MaskFieldUtil;
import util.ShowAlert;
import util.ValidacaoCPF;
import util.ValidacaoEmail;
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
    
    private ICrud controllerProfessor;

    private ICrud controllerEndereco;
    
    private ShowAlert showAlert = new ShowAlert();
    
    private ValidacaoCPF validacaoCpf = new ValidacaoCPF();
    
    private ValidacaoEmail validacaoEmail = new ValidacaoEmail();
    
    private Professor professorEditar = new Professor();
    
    public ControllerViewCadastroProfessor(ICrud controllerProfessor, ICrud controllerEndereco) {
    	this.controllerProfessor = controllerProfessor;
    	this.controllerEndereco = controllerEndereco;
    }
    
    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaProfessor mudarTela = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
	    mudarTela.start(stage);
    }

    @FXML
    void Sair(ActionEvent event) {

    }

    @FXML
    void VoltarPainel(ActionEvent event) {
    	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewPainel mudarTela = new ControllerViewPainel();
	    mudarTela.start(stage);
    }

    @FXML
    void addProfessor(ActionEvent event) {
    	
    	if(validacaoCampos()) {
    		if(validacaoCpf.validaCpf(Cpf.getText()) && validacaoEmail.emailValido(Email.getText())) {
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
				professor.setData_nascimento(Data_nascimento.getValue());
				
				if(professorEditar == null) {
					professor.setId_endereco(((ControllerEndereco) controllerEndereco).adicionarLong(endereco));					
			    	professor.setEndereco(endereco);
		    
			    	if(controllerProfessor.adicionar(professor)){
			    		showAlert.sucessoAlert("Professor adicionado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaProfessor mudarTela = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
					    mudarTela.start(stage);
			    	}
				} else {
					professor.setId_endereco(professorEditar.getId_endereco());
					endereco.setId_endereco(professorEditar.getId_endereco());
			    	professor.setEndereco(endereco);
		    
			    	if(controllerProfessor.atualizar(professor) && controllerEndereco.atualizar(endereco)) {
			    		showAlert.sucessoAlert("Professor editado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaProfessor mudarTela = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
					    mudarTela.start(stage);
			    	}
				}
    		} 
    		else {
        		showAlert.erroAlert("CPF e/ou E-mail iválidos!");
        	}
    	} 
    	else {
    		showAlert.validacaoAlert();
    	}
    }
    
    private boolean validacaoCampos() {
		
		if(Nome.getText().isEmpty() | Cpf.getText().isEmpty() | Email.getText().isEmpty() |
		   NR.getText().isEmpty() | Curso.getValue() == null | Materia.getValue() == null |
		   Cep.getText().isEmpty() | Numero.getText().isEmpty() | Rua.getText().isEmpty() |
		   Bairro.getText().isEmpty() | Cidade.getText().isEmpty() | Estado.getText().isEmpty()) {
			return false;
		}	
		return true;
	}

    public void setLabelText(Professor professor) {
    	this.professorEditar = professor;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Curso.getItems().setAll(Cursos.values());
		Materia.getItems().setAll(Materias.values());
		
		Cep.focusedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		        if(!newValue) {
		        	if(Cep.getText().length() > 8) {
		        		Endereco endereco = new BuscaCep().buscarCep(Cep.getText());
		        		
		        	    if(endereco != null) {
		        	    	Rua.setText(endereco.getRua());
			  	            Bairro.setText(endereco.getBairro());
			  	            Cidade.setText(endereco.getEstado());
			  	            Estado.setText(endereco.getEstado());
		        	    } else {
		        	    	Rua.setText("");
			  	            Bairro.setText("");
			  	            Cidade.setText("");
			  	            Estado.setText("");
			  	            
			  	            showAlert.erroAlert("Cep não encontrado!");
		        	    }
		        	}
		        }
		    }
		});	
		
        MaskFieldUtil.cepField(this.Cep);
        MaskFieldUtil.cpfField(this.Cpf);
        
        if(professorEditar != null) {
        	preencherCampos();
        }
	}
	
	private void preencherCampos() {
		Nome.setText(professorEditar.getNome());
		Cpf.setText(professorEditar.getCPF());
		NR.setText(String.valueOf(professorEditar.getNR()));
		Data_nascimento.setValue(professorEditar.getData_nascimento());
		Email.setText(professorEditar.getEmail());
		Curso.setValue(Cursos.listarUm(professorEditar.getCurso()));
		Materia.setValue(Materias.listarUm(professorEditar.getMateria()));
		Cep.setText(professorEditar.getEndereco().getCep());
		Numero.setText(String.valueOf(professorEditar.getEndereco().getNumero()));
		Rua.setText(professorEditar.getEndereco().getRua());
		Bairro.setText(professorEditar.getEndereco().getBairro());
		Cidade.setText(professorEditar.getEndereco().getCidade());
		Estado.setText(professorEditar.getEndereco().getEstado());

		labelChange.setText("Editar Professor");
		Cpf.setEditable(false);
		btnAdd.setText("Editar");
		
	}
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor/CadastroProfessor.fxml"));
			ControllerViewCadastroProfessor controller = new ControllerViewCadastroProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
	        loader.setController(controller);
            controller.setLabelText(null);

	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Professor - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
