package DB;

import com.example.tmp_kursovaya.Person;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    Statement statement = null;


    public Connection getBdConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName; // путь к БД

        //System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.jdbc.Driver"); // драйвер

        //System.out.println("Creating database connection...");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass); // установка соеденение

        return dbConnection;
    }

    public void writeUpUser(String name, String data, String telephone, String post, String department, String address) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_TELEPHONE + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prST = getBdConnection().prepareStatement(insert);
            prST.setString(1, name);
            prST.setString(2, data);
            prST.setString(3, telephone);
            prST.setString(4, post);
            prST.setString(5, department);
            prST.setString(6, address);
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    public void readCountries(ArrayList<String> tmp) throws SQLException, ClassNotFoundException {

        //statement = dbConnection.createStatement();

        String select = "SELECT * FROM " + Const.COUNTRIES_TABLE;
        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            int id = resultSet.getInt(Const.COUNTRIES_ID);
            String countries = resultSet.getString(Const.COUNTRIES_COUNTRIE);
            String line = id + ". " + countries;
            tmp.add(line);

//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + countries);

        }
    }

    public void readTown(ArrayList<String> tmp) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.TOWN_TABLE;

        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            int id = resultSet.getInt(Const.TOWN_ID);
            String town = resultSet.getString(Const.TOWN_TOWN);
            String line = id + ". " + town;
            tmp.add(line);

//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + town);
        }
    }

    public void readPost(ArrayList<String> tmp) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.POST_TABLE;

        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            int id = resultSet.getInt(Const.POST_ID);
            String post = resultSet.getString(Const.POST_POST);
            String line = id + ". " + post;
            tmp.add(line);
//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + post);
        }
    }

    public void readDepartment(ArrayList<String> tmp) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.DEPARTMENT_TABLE;

        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            int id = resultSet.getInt(Const.DEPARTMENT_ID);
            String department = resultSet.getString(Const.DEPARTMENT_DEPARTMENT);
            String line = id + ". " + department;
            tmp.add(line);
//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + department);
        }
    }

    public void readUser(ObservableList<Person> tmp) throws SQLException, ClassNotFoundException {
        String select = "SELECT users.name, users.data, users.telephone, post.post, department.department FROM users\n" +
                " JOIN post ON users.post = post.id_post\n" +
                " JOIN department ON users.department = department.id_department;";
        /*
        SELECT users.name, users.data, users.telephone, post.post, department.department FROM users
        JOIN post ON users.post = post.id_post
        JOIN department ON users.department = department.id_department;*/

//        String select = "SELECT " +
//                Const.USER_TABLE + "." + Const.USER_NAME + ", " +
//                Const.USER_TABLE + "." + Const.USER_DATA + ", " +
//                Const.USER_TABLE + "." + Const.USER_TELEPHONE + ", " +
//                Const.POST_TABLE + "." + Const.POST_POST + ", " +
//                Const.DEPARTMENT_TABLE + "." + Const.DEPARTMENT_DEPARTMENT + ", " +
//                " FROM " +  Const.USER_TABLE +
//                " JOIN " + Const.POST_TABLE + " ON " + Const.USER_TABLE + "." + Const.USER_POST + " = " +  Const.POST_TABLE + "." + Const.POST_ID +
//                " JOIN " + Const.DEPARTMENT_TABLE + " ON " + Const.USER_TABLE + "." + Const.DEPARTMENT_DEPARTMENT + " = " +  Const.DEPARTMENT_TABLE + "." + Const.DEPARTMENT_DEPARTMENT;


        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        //ResultSet resultSet2 = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            //int id = resultSet.getInt(Const.USER_ID);
            String name = resultSet.getString(Const.USER_NAME);
            String date = resultSet.getString(Const.USER_DATA);
            int telephone = Integer.parseInt(resultSet.getString(Const.USER_TELEPHONE));
            String post = resultSet.getString(Const.POST_POST);
            String department = resultSet.getString(Const.DEPARTMENT_DEPARTMENT);
//            String countries;
//            String address = resultSet.getString(Const.USER_ADDRESS);
            String line = name + " " + date + " " + telephone + " " + post + " " + department;
            tmp.add(new Person(name, date, telephone, department, post, "dfbvxfc", "vsfdvcx"));
            System.out.println();


//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + department);
        }
    }

    public void writeAddress(String street, String house) {
        String insert = "INSERT INTO " + Const.ADDRESS_TABLE + "(" + Const.ADDRESS_STREET + "," + Const.ADDRESS_HOUSE + ")" + "VALUES(?,?)";

        try {
            PreparedStatement prST = getBdConnection().prepareStatement(insert);
            prST.setString(1, street);
            prST.setString(2, house);
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
