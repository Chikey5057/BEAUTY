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

public class CreateController {

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

    SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @FXML
    void initialize() {

        ObservableList<Manufacturer> manufacturerList = FXCollections.observableArrayList();
        DAO<Manufacturer,Integer> manufacturerDAO = new ManufacturerDAOIMPL(factory);
        manufacturerList.addAll(manufacturerDAO.readAll());
        comboManufacturer.setItems(manufacturerList);
        ObservableList<String> activeList = FXCollections.observableArrayList("Active","Noactive");
        comboActive.setItems(activeList);

    }

    @FXML
    void createItem(ActionEvent event) {
        DAO<Product,Integer> dao = new ProductDAOIMPL(factory);
        Product product = new Product();
        product.setProductTitle(txtTitle.getText());
        product.setProductCost(Double.parseDouble(txtCost.getText()));
        product.setManufacturer(comboManufacturer.getValue());
        if(comboActive.getValue().equals("Active")){
            product.setProductIsActive(1);
        }else if (comboActive.getValue().equals("Noactive")){
            product.setProductIsActive(0);
        }
        dao.create(product);
    }
}