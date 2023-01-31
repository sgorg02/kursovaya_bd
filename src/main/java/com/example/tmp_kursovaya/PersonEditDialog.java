package com.example.tmp_kursovaya;

import DB.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class PersonEditDialog {
    public TextField textFieldFIO;
    public TextField textFieldBirthday;
    public TextField textFieldMobilePhone;
    public TextField textFieldAddress;
    public TextField textFieldHouse;
    public Button buttonOk;
    public Button buttonCancel;
    public ChoiceBox<String> countriesChoiceBox;
    public ChoiceBox<String> postChoiceBox;
    public ChoiceBox<String> departmentChoiceBox;
    public ChoiceBox<String> townChoiceBox;
    private final ArrayList<String> countries = new ArrayList<>();//
    private final ArrayList<String> town = new ArrayList<>();
    private final ArrayList<String> post = new ArrayList<>();
    private final ArrayList<String> department = new ArrayList<>();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        textFieldFIO.setPromptText("Приклад: Иванов Иван Иванович");
        textFieldBirthday.setPromptText("Приклад: 24-11-1990");
        textFieldMobilePhone.setPromptText("Приклад: 38-066-404-44-44 або 380664044444");
        textFieldAddress.setPromptText("Приклад: вул.Електриків");
        textFieldHouse.setPromptText("Приклад: 50");

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.readDepartment(department);
        databaseHandler.readPost(post);
        databaseHandler.readCountries(countries);
        databaseHandler.readTown(town);

        //initCountriesAndTown();
        initChoiceBox();
    }
    private void initCountriesAndTown() {
        for (String tmp: department)
            System.out.println(tmp);
        for (String tmp: post)
            System.out.println(tmp);
        for (String tmp: countries)
            System.out.println(tmp);
        for (String tmp: town)
            System.out.println(tmp);


    }

    private void initChoiceBox() {
        departmentChoiceBox.getItems().addAll(department);
        postChoiceBox.getItems().addAll(post);
        countriesChoiceBox.getItems().addAll(countries);
        townChoiceBox.getItems().addAll(town);
    }
    public void handleOK(ActionEvent actionEvent) {

        int id_department = choiceIdChoiceBox(department, departmentChoiceBox);
        int id_post = choiceIdChoiceBox(post, postChoiceBox);
        int id_countries = choiceIdChoiceBox(countries, countriesChoiceBox);
        int id_town = choiceIdChoiceBox(town, townChoiceBox);

//        System.out.println("department: " + id_department);
//        System.out.println("post: " + id_post);
//        System.out.println("countries: " + id_countries);
//        System.out.println("town: " + id_town);

//        String countries_tmp = countriesChoiceBox.getValue();
//        int i = 1;
//        for (String tmp: countries) {
//            if (tmp.equals(countries_tmp))
//                System.out.println(tmp + i);
//            i++;
//        }

    }
    private int choiceIdChoiceBox(ArrayList<String> name, ChoiceBox<String > tmpBox) { // возвращаем id выбраного елемента из ChoiceBox
        String name_tmp = tmpBox.getValue();
        int i = 1;
        for (String tmp: name) {
            if (tmp.equals(name_tmp))
                return i;
            i++;
        }
        return 0;
    }

    public void handleCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonOk.getScene().getWindow();
        stage.close();
        //dialogStage.close();
    }
}
