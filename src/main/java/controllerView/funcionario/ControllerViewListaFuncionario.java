package controllerView.funcionario;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ControllerEndereco;
import controller.ControllerFuncionario;
import controller.ControllerProfessor;
import controllerView.painel.ControllerViewPainel;
import dao.DaoEndereco;
import dao.DaoFuncionario;
import dao.DaoProfessor;
import entity.Aluno;
import entity.Funcionario;
import entity.Professor;
import enumType.Cursos;
import enumType.Setores;
import interfaces.ICrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.ShowAlert;

public class ControllerViewListaFuncionario implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Funcionario> tabela;

    @FXML
    private TableColumn<Funcionario, String> Nome;

    @FXML
    private TableColumn<Funcionario, String> Cpf;

    @FXML
    private TableColumn<Funcionario, String> Cargo;
    
    @FXML
    private TableColumn<Funcionario, String> Setor;
   
    @FXML
    private ComboBox<Setores> TxtSetor;
    
    private ICrud controllerFuncionario;
    private ICrud controllerEndereco;
    
    private ShowAlert showAlert = new ShowAlert();

    public ControllerViewListaFuncionario(ICrud controllerFuncionario, ICrud controllerEndereco) {
    	this.controllerFuncionario = controllerFuncionario;
    	this.controllerEndereco = controllerEndereco;
    }

    @FXML
    void AddFuncionario(ActionEvent event) {
     	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewCadastroFuncionario mudarTela = new ControllerViewCadastroFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
	    mudarTela.start(stage);
    }
    
    @FXML
    void AddPesquisa(ActionEvent event) {
    	ArrayList<Funcionario> listas = controllerFuncionario.pesquisa(TxtSetor.getValue().toString());
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(listas);
	    tabela.setItems(lista);
    }
    
    @FXML
    void AddLimpar(ActionEvent event) {
    	TxtSetor.setValue(null);
    	listar();
    }
	
	
	 @FXML
	 void VoltarPainel(ActionEvent event) {
		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
		ControllerViewPainel t = new ControllerViewPainel();
		t.start(stage);
	 }

	 @FXML
	 void Sair(ActionEvent event) {
		if(showAlert.confirmationAlert("Logout", "Tem certeza que deseja sair do sistema?")) {
    		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
    		stage.close();
    	}
	 }
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("CPF"));
		Cargo.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Cargo"));
		Setor.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("Setor"));
		TxtSetor.getItems().setAll(Setores.values());

		addButtonToTable();
	    addButtonExcluir();
		listar();
	}
	 
	public void listar() {
		ArrayList<Funcionario> listas = controllerFuncionario.listar();
        ObservableList<Funcionario> lista = FXCollections.observableArrayList(listas);
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	    
	}

	private void addButtonToTable() {
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                        	Funcionario funcionario = getTableView().getItems().get(getIndex());
                            
                            try {
                            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/funcionario/CadastroFuncionario.fxml"));
                    			ControllerViewCadastroFuncionario controller = new ControllerViewCadastroFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));

	                            loader.setController(controller);
	                            controller.setLabelText(funcionario);
	                            AnchorPane pane = loader.load();
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(pane));
	                            stage.setTitle("Editar Funcionário - Uni Universidade");
	                            stage.centerOnScreen();
	                			stage.getIcons().add(new Image("/img/universidade.png"));
	                			stage.setResizable(false);
	                            stage.show();
	                            
	                            Stage stages = (Stage) btn.getScene().getWindow();
	                            stages.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
	                                                     
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color: #28A745;-fx-text-fill:#ffffff; "); 
                       
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
              
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        colBtn.setMinWidth(100);

        tabela.getColumns().add(colBtn);

    }
		
	private void addButtonExcluir() {
        TableColumn<Funcionario, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>> cellFactory = new Callback<TableColumn<Funcionario, Void>, TableCell<Funcionario, Void>>() {
            @Override
            public TableCell<Funcionario, Void> call(final TableColumn<Funcionario, Void> param) {
                final TableCell<Funcionario, Void> cell = new TableCell<Funcionario, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Funcionario funcionario = getTableView().getItems().get(getIndex());
                            
                            if(showAlert.confirmationAlert("Excluir Funcionário", "Tem certeza que deseja excluir " +funcionario.getNome()+" ?")) {
                            	if(controllerFuncionario.excluir(funcionario) && controllerEndereco.excluir(funcionario.getEndereco())) {
                            		showAlert.sucessoAlert("Funcionário excluído com sucesso!");
                            		listar();
                            	} else {
                            		showAlert.erroAlert("Erro ao excluir funcionário!");
                            	}	
                        	}                           
                        });
                       
                        btn.setPrefWidth(280);
                        btn.setStyle("-fx-background-color:#e04b59;-fx-text-fill:#ffffff;"); 
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        colBtn.setMinWidth(90);

        tabela.getColumns().add(colBtn);

    }
		
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/funcionario/ListaFuncionario.fxml"));
			ControllerViewListaFuncionario controller = new ControllerViewListaFuncionario(new ControllerFuncionario(new DaoFuncionario()), new ControllerEndereco(new DaoEndereco()));
	        loader.setController(controller);
	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Funcionários - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
