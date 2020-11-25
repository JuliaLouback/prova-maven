package controllerView.funcionario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.ControllerEndereco;
import controller.ControllerFuncionario;
import controller.ControllerProfessor;
import controllerView.painel.ControllerViewPainel;
import dao.DaoEndereco;
import dao.DaoFuncionario;
import dao.DaoProfessor;
import entity.Professor;
import enumType.Cargos;
import enumType.Cursos;
import enumType.Materias;
import enumType.Setores;
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
import entity.Funcionario;

public class ControllerViewCadastroFuncionario implements Initializable{

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
    private ComboBox<Cargos> Cargo;

    @FXML
    private ComboBox<Setores> Setor;
    
    private ICrud controllerFuncionario;

    private ICrud controllerEndereco;
    
    private ShowAlert showAlert = new ShowAlert();
    
    private ValidacaoCPF validacaoCpf = new ValidacaoCPF();
    
    private ValidacaoEmail validacaoEmail = new ValidacaoEmail();
    
    private Funcionario funcionarioEditar = new Funcionario();
    
    public ControllerViewCadastroFuncionario(ICrud controllerFuncionario, ICrud controllerEndereco) {
    	this.controllerFuncionario = controllerFuncionario;
    	this.controllerEndereco = controllerEndereco;
    }
    
    @FXML
    void BackButton(ActionEvent event) {
    	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewListaFuncionario mudarTela = new ControllerViewListaFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
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
    void addFuncionario(ActionEvent event) {
    	
    	if(validacaoCampos()) {
    		if(validacaoCpf.validaCpf(Cpf.getText()) && validacaoEmail.emailValido(Email.getText())) {
		    	Funcionario funcionario = new Funcionario();
		    
		    	Endereco  endereco 	= new Endereco();
		    	
		    	endereco.setCep(Cep.getText());
		    	endereco.setNumero(Integer.valueOf(Numero.getText()));
		    	endereco.setRua(Rua.getText());
		    	endereco.setBairro(Bairro.getText());
		    	endereco.setCidade(Cidade.getText());
		    	endereco.setEstado(Estado.getText());
			 
		    	funcionario.setCPF(Cpf.getText());
		    	funcionario.setNR(Integer.parseInt(NR.getText()));
		    	funcionario.setNome(Nome.getText());
		    	funcionario.setEmail(Email.getText());
		    	funcionario.setCargo(Cargo.getValue().toString());
		    	funcionario.setSetor(Setor.getValue().toString());
		    	funcionario.setData_nascimento(Data_nascimento.getValue());
				
				if(funcionarioEditar == null) {
					funcionario.setId_endereco(((ControllerEndereco) controllerEndereco).adicionarLong(endereco));					
					funcionario.setEndereco(endereco);
		    
			    	if(controllerFuncionario.adicionar(funcionario)){
			    		showAlert.sucessoAlert("Funcionário adicionado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaFuncionario mudarTela = new ControllerViewListaFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
					    mudarTela.start(stage);
			    	}
				} else {
					funcionario.setId_endereco(funcionarioEditar.getId_endereco());
					endereco.setId_endereco(funcionarioEditar.getId_endereco());
					funcionario.setEndereco(endereco);
		    
			    	if(controllerFuncionario.atualizar(funcionario) && controllerEndereco.atualizar(endereco)) {
			    		showAlert.sucessoAlert("Funcionário editado com sucesso!");

			    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
					    ControllerViewListaFuncionario mudarTela = new ControllerViewListaFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
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
		   NR.getText().isEmpty() | Cargo.getValue() == null | Setor.getValue() == null |
		   Cep.getText().isEmpty() | Numero.getText().isEmpty() | Rua.getText().isEmpty() |
		   Bairro.getText().isEmpty() | Cidade.getText().isEmpty() | Estado.getText().isEmpty()) {
			return false;
		}	
		return true;
	}

    public void setLabelText(Funcionario funcionario) {
    	this.funcionarioEditar = funcionario;
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Cargo.getItems().setAll(Cargos.values());
		Setor.getItems().setAll(Setores.values());
		
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
        
        if(funcionarioEditar != null) {
        	preencherCampos();
        }
	}
	
	private void preencherCampos() {
		Nome.setText(funcionarioEditar.getNome());
		Cpf.setText(funcionarioEditar.getCPF());
		NR.setText(String.valueOf(funcionarioEditar.getNR()));
		Data_nascimento.setValue(funcionarioEditar.getData_nascimento());
		Email.setText(funcionarioEditar.getEmail());
		Cargo.setValue(Cargos.listarUm(funcionarioEditar.getCargo()));
		Setor.setValue(Setores.listarUm(funcionarioEditar.getSetor()));
		Cep.setText(funcionarioEditar.getEndereco().getCep());
		Numero.setText(String.valueOf(funcionarioEditar.getEndereco().getNumero()));
		Rua.setText(funcionarioEditar.getEndereco().getRua());
		Bairro.setText(funcionarioEditar.getEndereco().getBairro());
		Cidade.setText(funcionarioEditar.getEndereco().getCidade());
		Estado.setText(funcionarioEditar.getEndereco().getEstado());

		labelChange.setText("Editar");
		Cpf.setEditable(false);
		btnAdd.setText("Editar");
		
	}
	
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/funcionario/CadastroFuncionario.fxml"));
			ControllerViewCadastroFuncionario controller = new ControllerViewCadastroFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
	        loader.setController(controller);
            controller.setLabelText(null);

	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cadastro Funcionário - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
