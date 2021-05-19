package h8.chikey.controller;

import h8.chikey.dao.DAO;
import h8.chikey.daoimpl.ProductDAOIMPL;
import h8.chikey.model.Product;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Locale;

public class MainController {

        @FXML
        private TableView<Product> tableView;

        @FXML
        private TableColumn<Product, Integer> columnID;

        @FXML
        private TableColumn<Product, String> columnTitlte;

        @FXML
        private TableColumn<Product, Double> columnCost;

        @FXML
        private TableColumn<Product, String> columnIsActive;

        @FXML
        private TableColumn<Product, String> columnManufacturer;

        @FXML
        private TextField txtSearch;

        @FXML
        private ComboBox<?> comboBox;

        @FXML
        private Button buttonCreate;

        @FXML
        private Button buttonUpdate;

        @FXML
        private Button buttonDelete;

        private Product product;
        private SessionFactory factory = new Configuration().configure().buildSessionFactory();
        private ObservableList<Product> listProduct = FXCollections.observableArrayList();

        @FXML
      void initialize(){
            setItems();
            selectItems();
            buttonDelete.setOnAction(actionEvent -> {
                deleteItems(product);
                clearScrean();
            });
            search();
        }

      void setItems(){
            DAO<Product,Integer> dao = new ProductDAOIMPL(factory);
            listProduct.addAll(dao.readAll());

            columnID.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getProductID()));
            columnTitlte.setCellValueFactory(p-> new SimpleObjectProperty<>(p.getValue().getProductTitle()));
            columnCost.setCellValueFactory(p-> new SimpleObjectProperty<>(p.getValue().getProductCost()));
            columnIsActive.setCellValueFactory(p-> {
                if(p.getValue().getProductIsActive()==1){
                    return new SimpleObjectProperty<>("Active");
                } else return new SimpleObjectProperty<>("NoActive");
            });
            columnManufacturer.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getManufacturer().getManufacturerName()));

            tableView.setItems(listProduct);

        }
      public void selectItems(){
            tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, product1, t1) -> {
               product=t1;
            });
        }


      void deleteItems(Product product){
            DAO<Product,Integer> dao = new ProductDAOIMPL(factory);
            dao.delete(product);
      }
      void clearScrean(){
            listProduct.clear();
            initialize();
      }

    public void search(){
        txtSearch.textProperty().addListener((observableValue, s, t1) -> {
            FilteredList<Product> filteredList = new FilteredList<>(listProduct,product1 -> {
                if(t1==null||t1.isEmpty()){
                    return true;
                }if (product1.getProductTitle().contains(t1.toLowerCase())){
                    return true;
                }else return false;
            });
            tableView.setItems(filteredList);
        });
    }

    public void refresh(ActionEvent actionEvent) {
        clearScrean();
        tableView.setItems(listProduct);
    }

    @FXML
    void createItems(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Create.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BeautyShop Create");
        stage.setScene(new Scene(root));
        stage.show();
        clearScrean();
    }

    @FXML
    void updateItems(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Update.fxml"));
        Stage stage = new Stage();
        stage.setTitle("BeautyShop Create");
        stage.setScene(new Scene(root));
        stage.show();
        clearScrean();
    }




}
