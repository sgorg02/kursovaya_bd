package com.example.tmp_kursovaya;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonOverview {
    public Label labelNameDepartment;
    public ButtonBar buttonBarContacts;
    public TableView tableViewSearch;

    private MainApp mainApp;

    @FXML
    private void initialize() {

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