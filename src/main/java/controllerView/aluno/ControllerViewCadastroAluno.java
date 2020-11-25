package controllerView.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerAluno;
import controller.ControllerEndereco;
import controllerView.painel.ControllerViewPainel;
import dao.DaoAluno;
import dao.DaoEndereco;
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
import entity.Aluno;
import entity.Endereco;

public class ControllerViewCadastroAluno implements Initializable{

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

    private ICrud controllerAluno;

    private ICrud controllerEndereco;
    
    private ShowAlert showAlert = new ShowAlert();
    
    private ValidacaoCPF validacaoCpf = new ValidacaoCPF();
    
    private ValidacaoEmail validacaoEmail = new ValidacaoEmail();
    
    private Aluno alunoEditar = new Aluno();
    
    public ControllerViewCadastroAluno(ICrud controllerAluno, ICrud controllerEndereco) {
    	this.controllerAluno = controllerAluno;
    	this.controllerEndereco = controllerEndereco;
    }
    
    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaAluno mudarTela = new ControllerViewListaAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
	    mudarTela.start(stage);
    }

    @FXML
    void Sair(ActionEvent event) {
    	if(showAlert.confirmationAlert("Logout", "Tem certeza que deseja sair do sistema?")) {
    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
    		stage.close();
    	}
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
		    	Aluno aluno = new Aluno();
		    
		    	Endereco  endereco 	= new Endereco();
		    	
		    	endereco.setCep(Cep.getText());
		    	endereco.setNumero(Integer.valueOf(Numero.getText()));
		    	endereco.setRua(Rua.getText());
		    	endereco.setBairro(Bairro.getText());
		    	endereco.setCidade(Cidade.getText());
		    	endereco.setEstado(Estado.getText());
			 
		    	aluno.setCPF(Cpf.getText());
		    	aluno.setNR(Integer.parseInt(NR.getText()));
		    	aluno.setNome(Nome.getText());
		    	aluno.setEmail(Email.getText());
		    	aluno.setCurso(Curso.getValue().toString());
		    	aluno.setData_nascimento(Data_nascimento.getValue());
				
				if(alunoEditar == null) {
					aluno.setId_endereco(((ControllerEndereco) controllerEndereco).adicionarLong(endereco));					
					aluno.setEndereco(endereco);
		    
			    	if(controllerAluno.adicionar(aluno)){
			    		showAlert.sucessoAlert("Aluno adicionado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaAluno mudarTela = new ControllerViewListaAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
					    mudarTela.start(stage);
			    	}
				} else {
					aluno.setId_endereco(alunoEditar.getId_endereco());
					endereco.setId_endereco(alunoEditar.getId_endereco());
					aluno.setEndereco(endereco);
		    
			    	if(controllerAluno.atualizar(aluno) && controllerEndereco.atualizar(endereco)) {
			    		showAlert.sucessoAlert("Aluno editado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaAluno mudarTela = new ControllerViewListaAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
					    mudarTela.start(stage);
			    	}
				}
    		} 
    		else {
        		showAlert.erroAlert("CPF e/ou E-mail inválidos!");
        	}
    	} 
    	else {
    		showAlert.validacaoAlert();
    	}
    }
    
    private boolean validacaoCampos() {
		
		if(Nome.getText().isEmpty() | Cpf.getText().isEmpty() | Email.getText().isEmpty() |
		   NR.getText().isEmpty() | Curso.getValue() == null |
		   Cep.getText().isEmpty() | Numero.getText().isEmpty() | Rua.getText().isEmpty() |
		   Bairro.getText().isEmpty() | Cidade.getText().isEmpty() | Estado.getText().isEmpty()) {
			return false;
		}	
		return true;
	}

    public void setLabelText(Aluno aluno) {
    	this.alunoEditar = aluno;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Curso.getItems().setAll(Cursos.values());
		
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
        
        if(alunoEditar != null) {
        	preencherCampos();
        }
	}
	
	private void preencherCampos() {
		Nome.setText(alunoEditar.getNome());
		Cpf.setText(alunoEditar.getCPF());
		NR.setText(String.valueOf(alunoEditar.getNR()));
		Data_nascimento.setValue(alunoEditar.getData_nascimento());
		Email.setText(alunoEditar.getEmail());
		Curso.setValue(Cursos.listarUm(alunoEditar.getCurso()));
		Cep.setText(alunoEditar.getEndereco().getCep());
		Numero.setText(String.valueOf(alunoEditar.getEndereco().getNumero()));
		Rua.setText(alunoEditar.getEndereco().getRua());
		Bairro.setText(alunoEditar.getEndereco().getBairro());
		Cidade.setText(alunoEditar.getEndereco().getCidade());
		Estado.setText(alunoEditar.getEndereco().getEstado());

		labelChange.setText("Editar");
		Cpf.setEditable(false);
		btnAdd.setText("Editar");
		
	}
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aluno/CadastroAluno.fxml"));
			ControllerViewCadastroAluno controller = new ControllerViewCadastroAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
	        loader.setController(controller);
            controller.setLabelText(null);

	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Aluno - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
