package h8.chikey.controller;

import h8.chikey.dao.DAO;
import h8.chikey.daoimpl.ManufacturerDAOIMPL;
import h8.chikey.daoimpl.ProductDAOIMPL;
import h8.chikey.model.Manufacturer;
import h8.chikey.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateController {

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtCost;

    @FXML
    private ComboBox<Manufacturer> comboManufacturer;

    @FXML
    private Button buttonGO;

    @FXML
    private ComboBox<String> comboActive;

    private  Product product;
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private ObservableList<Manufacturer> listManufacturer = FXCollections.observableArrayList();
    ObservableList<String> listActive = FXCollections.observableArrayList("Active","NoActive");

    @FXML
    void initialize(){
        DAO<Manufacturer,Integer> dao = new ManufacturerDAOIMPL(factory);
        listManufacturer.addAll(dao.readAll());
        comboManufacturer.setItems(listManufacturer);
        comboActive.setItems(listActive);
    }
    @FXML
    void updateItem(ActionEvent actionEvent){
        DAO<Product,Integer> dao = new ProductDAOIMPL(factory);
        product.setManufacturer(comboManufacturer.getValue());
        if (comboActive.getValue().equals("Active")){ product.setProductIsActive(1);
        }else if (comboActive.getValue().equals("NoActive"))product.setProductIsActive(0);
        dao.update(product);
    }

    public void setData(Product product){
        this.product=product;
        DAO<Product,Integer> dao = new ProductDAOIMPL(factory);
        txtTitle.setText(product.getProductTitle());
        txtCost.setText(String.valueOf(product.getProductCost()));
    }
}