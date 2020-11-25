package controllerView.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ControllerAluno;
import controller.ControllerEndereco;
import controller.ControllerAluno;
import controllerView.painel.ControllerViewPainel;
import dao.DaoEndereco;
import dao.DaoAluno;
import entity.Aluno;
import entity.Professor;
import enumType.Cursos;
import entity.Aluno;
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

public class ControllerViewListaAluno implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Aluno> tabela;

    @FXML
    private TableColumn<Aluno, String> Nome;

    @FXML
    private TableColumn<Aluno, String> Cpf;

    @FXML
    private TableColumn<Aluno, String> Curso;
    
    @FXML
    private TableColumn<Aluno, String> NR;
    
    @FXML
    private ComboBox<Cursos> TxtCurso;
    
    private ICrud controllerAluno;
    private ICrud controllerEndereco;
    
    private ShowAlert showAlert = new ShowAlert();

    public ControllerViewListaAluno(ICrud controllerAluno, ICrud controllerEndereco) {
    	this.controllerAluno = controllerAluno;
    	this.controllerEndereco = controllerEndereco;
    }

    @FXML
    void AddAluno(ActionEvent event) {
     	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewCadastroAluno mudarTela = new ControllerViewCadastroAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
	    mudarTela.start(stage);
    }
	
    @FXML
    void AddPesquisa(ActionEvent event) {
    	ArrayList<Aluno> listas = controllerAluno.pesquisa(TxtCurso.getValue().toString());
        ObservableList<Aluno> lista = FXCollections.observableArrayList(listas);
	    tabela.setItems(lista);
    }
    
    @FXML
    void AddLimpar(ActionEvent event) {
    	TxtCurso.setValue(null);
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
		Nome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Aluno, String>("CPF"));
		Curso.setCellValueFactory(new PropertyValueFactory<Aluno, String>("Curso"));
		NR.setCellValueFactory(new PropertyValueFactory<Aluno, String>("NR"));
		TxtCurso.getItems().setAll(Cursos.values());

		addButtonToTable();
	    addButtonExcluir();
		listar();
	}
	 
	public void listar() {
		ArrayList<Aluno> listas = controllerAluno.listar();
        ObservableList<Aluno> lista = FXCollections.observableArrayList(listas);
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	    
	}

	private void addButtonToTable() {
        TableColumn<Aluno, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>> cellFactory = new Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>>() {
            @Override
            public TableCell<Aluno, Void> call(final TableColumn<Aluno, Void> param) {
                final TableCell<Aluno, Void> cell = new TableCell<Aluno, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Aluno aluno = getTableView().getItems().get(getIndex());
                            
                            try {
                            	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/aluno/CadastroAluno.fxml"));
                    			ControllerViewCadastroAluno controller = new ControllerViewCadastroAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));

	                            loader.setController(controller);
	                            controller.setLabelText(aluno);
	                            AnchorPane pane = loader.load();
	                    
	                            Stage stage = new Stage();
	                            stage.setScene(new Scene(pane));
	                            stage.setTitle("Editar Aluno - Uni Universidade");
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
        TableColumn<Aluno, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>> cellFactory = new Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>>() {
            @Override
            public TableCell<Aluno, Void> call(final TableColumn<Aluno, Void> param) {
                final TableCell<Aluno, Void> cell = new TableCell<Aluno, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Aluno aluno = getTableView().getItems().get(getIndex());
                            
                            if(showAlert.confirmationAlert("Excluir Aluno", "Tem certeza que deseja excluir " +aluno.getNome()+" ?")) {
                            	if(controllerAluno.excluir(aluno) && controllerEndereco.excluir(aluno.getEndereco())) {
                            		showAlert.sucessoAlert("Aluno excluído com sucesso!");
                            		listar();
                            	} else {
                            		showAlert.erroAlert("Erro ao excluir Aluno!");
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Aluno/ListaAluno.fxml"));
			ControllerViewListaAluno controller = new ControllerViewListaAluno(new ControllerAluno(new DaoAluno()), new ControllerEndereco(new DaoEndereco()));
	        loader.setController(controller);
	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Alunos - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
