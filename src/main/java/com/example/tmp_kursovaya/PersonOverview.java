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

    ObservableList<Person> list = FXCollections.observableArrayList();

    private MainApp mainApp;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        //initTableView();
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.readUser(list);

//        FIOs.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        birthdays.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        phones.setCellValueFactory(new PropertyValueFactory<Person, Integer>(""));
//        department.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        post.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        countries.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        address.setCellValueFactory(new PropertyValueFactory<Person, String>(""));

        tableViewSearch.setItems(list);

    }

    private void initTableView() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.readUser(list);

//        FIOs.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        birthdays.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        phones.setCellValueFactory(new PropertyValueFactory<Person, Integer>(""));
//        department.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        post.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        countries.setCellValueFactory(new PropertyValueFactory<Person, String>(""));
//        address.setCellValueFactory(new PropertyValueFactory<Person, String>(""));

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

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        //tableViewDepartmens.setItems(mainApp.getDepartmens());

        //setTempDepartamens();
        //tableViewChoices.setItems(mainApp.getContacts());

//        for (int i = 0; i < mainApp.getDepartmens().size(); i++) {
//            for (int j = 0; j < mainApp.getDepartmens().get(i).getContactList().size(); j++) {
//                masterData.add(mainApp.getDepartmens().get(i).getContactList().get(j));
//            }
//        }
        //masterData.addAll(mainApp.getDepartmens());
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