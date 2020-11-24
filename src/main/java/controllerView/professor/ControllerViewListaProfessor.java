package controllerView.professor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ControllerEndereco;
import controller.ControllerProfessor;
import controllerView.painel.ControllerViewPainel;
import dao.DaoEndereco;
import dao.DaoProfessor;
import entity.Professor;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.ShowAlert;

public class ControllerViewListaProfessor implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private TableView<Professor> tabela;

    @FXML
    private TableColumn<Professor, String> Nome;

    @FXML
    private TableColumn<Professor, String> Cpf;

    @FXML
    private TableColumn<Professor, String> Curso;
    
    @FXML
    private TableColumn<Professor, String> Materia;
    
    private ICrud controllerProfessor;
    
    private ShowAlert showAlert = new ShowAlert();

    public ControllerViewListaProfessor(ICrud controllerProfessor) {
    	this.controllerProfessor = controllerProfessor;
    }

    @FXML
    void AddProfessor(ActionEvent event) {
     	Stage stage = (Stage) btnAdd.getScene().getWindow(); 
	    ControllerViewCadastroProfessor mudarTela = new ControllerViewCadastroProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
	    mudarTela.start(stage);
    }
	
	 @FXML
	 void VoltarPainel(ActionEvent event) {
		Stage stage = (Stage) btnAdd.getScene().getWindow(); 
		ControllerViewPainel t = new ControllerViewPainel();
		t.start(stage);
	 }

	 @FXML
	 void Sair(ActionEvent event) {
			
	 }
	 
	 @Override
	 public void initialize(URL location, ResourceBundle resources) {
		Nome.setCellValueFactory(new PropertyValueFactory<Professor, String>("Nome"));
		Cpf.setCellValueFactory(new PropertyValueFactory<Professor, String>("CPF"));
		Curso.setCellValueFactory(new PropertyValueFactory<Professor, String>("Curso"));
		Materia.setCellValueFactory(new PropertyValueFactory<Professor, String>("Materia"));
		addButtonToTable();
	    addButtonExcluir();
		listar();
	}
	 
	public void listar() {
		ArrayList<Professor> listas = controllerProfessor.listar();
        ObservableList<Professor> lista = FXCollections.observableArrayList(listas);
	    tabela.setItems(lista);
	    tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	    
	}

	private void addButtonToTable() {
        TableColumn<Professor, Void> colBtn = new TableColumn("Editar");

        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
            @Override
            public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
                final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {

                    private final Button btn = new Button("Editar");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Professor professor = getTableView().getItems().get(getIndex());
                            
                            try {
	                       
	                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor/CadastroProfessor.fxml"));
	                    	    ControllerViewCadastroProfessor controller = new ControllerViewCadastroProfessor(new ControllerProfessor(new DaoProfessor()), new ControllerEndereco(new DaoEndereco()));
	                	        controller.setLabelText(professor);
	                    	    
	                    	    loader.setController(controller);
	                	        AnchorPane pane = loader.load();
	                	        
	                	        Stage stage = new Stage();
	                            stage.setScene(new Scene(pane));
	                            stage.setTitle("Editar Professor - Uni Universidade");
	                            stage.centerOnScreen();
	                            stage.getIcons().add(new Image("/img/universidade.png"));
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
        TableColumn<Professor, Void> colBtn = new TableColumn("Excluir");

        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
            @Override
            public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
                final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {

                    private final Button btn = new Button("Excluir");{
                        btn.setOnAction((ActionEvent event) -> {
                        	
                            Professor professor = getTableView().getItems().get(getIndex());
                            
                            if(showAlert.confirmationAlert("Excluir Professor", "Tem certeza que deseja excluir" +professor.getNome()+" ?")) {
                            	if(controllerProfessor.excluir(professor)) {
                            		showAlert.sucessoAlert("Professor excluído com sucesso!");
                            		listar();
                            	} else {
                            		showAlert.erroAlert("Erro ao excluir professor!");
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/professor/ListaProfessor.fxml"));
			ControllerViewListaProfessor controller = new ControllerViewListaProfessor(new ControllerProfessor(new DaoProfessor()));
	        loader.setController(controller);
	        AnchorPane pane = loader.load();
	        
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Lista Professores - Uni Universidade");
			primaryStage.centerOnScreen();
			primaryStage.getIcons().add(new Image("/img/universidade.png"));
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
