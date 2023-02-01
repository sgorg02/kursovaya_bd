package com.example.tmp_kursovaya;

import DB.DatabaseHandler;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class PersonOverview {
    public Label labelNameDepartment;
    public ButtonBar buttonBarContacts;
    public TableView<Person> tableViewSearch;
    public TableColumn<Person, String> FIOs;
    public TableColumn<Person, String> birthdays;
    public TableColumn<Person, Integer> phones;
    public TableColumn<Person, String> department;
    public TableColumn<Person, String> post;
    public TableColumn<Person, String> countries;
    public TableColumn<Person, String> address;

    public ObservableList<Person> list = FXCollections.observableArrayList();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        initTableView();


    }

    private void initTableView() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.readUser(list);

        FIOs.setCellValueFactory(new PropertyValueFactory<Person, String>("FIO"));
        birthdays.setCellValueFactory(new PropertyValueFactory<Person, String>("Date"));
        phones.setCellValueFactory(new PropertyValueFactory<Person, Integer>("phone"));
        department.setCellValueFactory(new PropertyValueFactory<Person, String>("department"));
        post.setCellValueFactory(new PropertyValueFactory<Person, String>("position"));
        countries.setCellValueFactory(new PropertyValueFactory<Person, String>("countries"));
        address.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));

        tableViewSearch.setItems(list);

    }

    public void handleNewPerson(ActionEvent actionEvent) {
        try {
            openScene("PersonEditDialog.fxml");
        } catch (IOException e){
            e.printStackTrace();
        }


//        Person tempPerson = new Person();
//        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
//        if (okClicked) {
//            //Departmens selectedPerson = tableViewDepartmens.getSelectionModel().getSelectedItem();
//            //selectedPerson.getContactList().add(tempPerson);
//        }
    }

    public void handleEditPerson(ActionEvent actionEvent) {
    }

    public void handleDeletePerson(ActionEvent actionEvent) {
    }


    private void openScene(String fileName) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fileName));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();


    }
}