package DB;

import com.example.tmp_kursovaya.Person;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    Statement statement = null;
    private String countries = null;
    private String address = null;
    private int id_address = 1;
    private int id_address_new = 0;


    public Connection getBdConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName; // путь к БД

        Class.forName("com.mysql.jdbc.Driver"); // драйвер

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass); // установка соеденение

        return dbConnection;
    }

    public void writeUpUser(String name, String data, String telephone,  int department, int post) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + ", " + Const.USER_DATA + ", " + Const.USER_TELEPHONE + ", " + Const.USER_POST + ", " + Const.USER_DEPARTMENT + ", " + Const.USER_ADDRESS + ")" +
                "VALUES(?,?,?,?,?,?)";

        id();
        //System.out.println("in writeUpUser = " + id_address_new);
        try {
            PreparedStatement prST = getBdConnection().prepareStatement(insert);
            prST.setString(1, name);
            prST.setString(2, data);
            prST.setString(3, telephone);
            prST.setString(4, String.valueOf(post));
            prST.setString(5, String.valueOf(department));
            prST.setString(6, String.valueOf(id_address_new));
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(name + " " + data + " " + telephone + " " + post + " " + department + " " + id_address_new);

    }

    public void id() throws SQLException, ClassNotFoundException {
        /*
        SELECT address.id_address
        FROM address
        ORDER BY address.id_address DESC
        LIMIT 1
        * */

        String select = "SELECT " + Const.ADDRESS_TABLE + "." + Const.ADDRESS_ID +
                        " FROM " + Const.ADDRESS_TABLE +
                        " ORDER BY " + Const.ADDRESS_TABLE + "." + Const.ADDRESS_ID + " DESC " +
                        " LIMIT 1";
        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            id_address_new = resultSet.getInt(Const.ADDRESS_ID);
            //System.out.println(id_address_new);
//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + countries);

        }
    }


    public void readCountries(ArrayList<String> tmp) throws SQLException, ClassNotFoundException { // запрос для списка стран


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

    public void readTown(ArrayList<String> tmp) throws SQLException, ClassNotFoundException { // запрос для списка городов
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

    public void readPost(ArrayList<String> tmp) throws SQLException, ClassNotFoundException { // запрос для списка должностей
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

    public void readDepartment(ArrayList<String> tmp) throws SQLException, ClassNotFoundException { // запрос для списка департамента
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

    public void readUser(ObservableList<Person> tmp) throws SQLException, ClassNotFoundException {  // запрос для получения пользывателя
        /*
        SELECT users.name, users.data, users.telephone, post.post, department.department FROM users
        JOIN post ON users.post = post.id_post
        JOIN department ON users.department = department.id_department;*/


        String select = "SELECT " +
                Const.USER_TABLE + "." + Const.USER_ADDRESS + ", " +
                Const.USER_TABLE + "." + Const.USER_NAME + ", " +
                Const.USER_TABLE + "." + Const.USER_DATA + ", " +
                Const.USER_TABLE + "." + Const.USER_TELEPHONE + ", " +
                Const.POST_TABLE + "." + Const.POST_POST + ", " +
                Const.DEPARTMENT_TABLE + "." + Const.DEPARTMENT_DEPARTMENT +
                " FROM " +  Const.USER_TABLE +
                " JOIN " + Const.POST_TABLE + " ON " + Const.USER_TABLE + "." + Const.USER_POST + " = " +  Const.POST_TABLE + "." + Const.POST_ID +
                " JOIN " + Const.DEPARTMENT_TABLE + " ON " + Const.USER_TABLE + "." + Const.DEPARTMENT_DEPARTMENT + " = " +  Const.DEPARTMENT_TABLE + "." + Const.DEPARTMENT_ID; // + " ORDER BY 1"

        ResultSet resultSet = getBdConnection().createStatement().executeQuery(select);
        while (resultSet.next()) {
            readAddress();
            //id_address++;
            String name = resultSet.getString(Const.USER_NAME);
            String date = resultSet.getString(Const.USER_DATA);
            int telephone = Integer.parseInt(resultSet.getString(Const.USER_TELEPHONE));
            String post = resultSet.getString(Const.POST_POST);
            String department = resultSet.getString(Const.DEPARTMENT_DEPARTMENT);
            //id_address = resultSet.getInt(Const.USER_ADDRESS);
            String line = name + " " + date + " " + telephone + " " + post + " " + department;
            tmp.add(new Person(name, date, telephone, department, post, countries, address));
            //System.out.println("in readUser id_address = " + id_address);
//            System.out.println("\n================\n");
//            System.out.println("id: " + id);
//            System.out.println("Countries: " + department);
        }
    }

    public void readAddress() throws SQLException, ClassNotFoundException { // запрос для получения адреса
        /*
        * SELECT address.street, address.house, town.town, countries.countrie FROM address
        JOIN town ON address.town = town.id_town
        JOIN countries ON address.countries = countries.id_countries
        WHERE address.id_address = '1';*/
        String select2 = "SELECT " +
                Const.ADDRESS_TABLE + "." + Const.ADDRESS_ID + ", " +
                Const.ADDRESS_TABLE + "." + Const.ADDRESS_STREET + ", " +
                Const.ADDRESS_TABLE + "." + Const.ADDRESS_HOUSE + ", " +
                Const.TOWN_TOWN + "." + Const.TOWN_TOWN + ", " +
                Const.COUNTRIES_TABLE + "." + Const.COUNTRIES_COUNTRIE +
                " FROM " + Const.ADDRESS_TABLE +
                " JOIN " + Const.TOWN_TABLE + " ON " + Const.ADDRESS_TABLE + "." + Const.ADDRESS_TOWN + " = " +  Const.TOWN_TABLE + "." + Const.TOWN_ID +
                " JOIN " + Const.COUNTRIES_TABLE + " ON " + Const.ADDRESS_TABLE + "." + Const.ADDRESS_COUNTRIES + " = " +  Const.COUNTRIES_TABLE + "." + Const.COUNTRIES_ID +
                " WHERE " + Const.ADDRESS_TABLE + "." + Const.ADDRESS_ID + " = " + "'" + id_address + "'";

        ResultSet resultSet2 = getBdConnection().createStatement().executeQuery(select2);
        while (resultSet2.next()) {
            id_address = resultSet2.getInt(Const.ADDRESS_ID);
            //System.out.println("in readAddress id_address = " + id_address);
            String town = resultSet2.getString(Const.TOWN_TOWN);
            int house = Integer.parseInt(resultSet2.getString(Const.ADDRESS_HOUSE));
            String street = resultSet2.getString(Const.ADDRESS_STREET);
            countries = resultSet2.getString(Const.COUNTRIES_COUNTRIE);
            address = town + ", " + street + ", " + house;
            id_address++;
            //System.out.println("in readAddress id_address after ++ = " + id_address);
        }

    }

    public void writeAddress(int countries, int town, String street, String house) {
        String insert = "INSERT INTO " + Const.ADDRESS_TABLE + "(" + Const.ADDRESS_COUNTRIES + ", " + Const.ADDRESS_TOWN + ", " + Const.ADDRESS_STREET + ", " + Const.ADDRESS_HOUSE + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prST = getBdConnection().prepareStatement(insert);
            prST.setString(1, String.valueOf(countries));
            prST.setString(2, String.valueOf(town));
            prST.setString(3, street);
            prST.setString(4, String.valueOf(house));
            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(countries + " " + town + " " + street + " " + house);
    }


}
