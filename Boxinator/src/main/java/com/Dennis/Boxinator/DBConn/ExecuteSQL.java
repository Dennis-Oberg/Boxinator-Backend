package com.Dennis.Boxinator.DBConn;

import com.Dennis.Boxinator.Model.Box;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExecuteSQL {

    private Connection conn;
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    public void checkConnection() {

        try {
            conn = SQLConnection.dbConnector();

        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException(String.format("Connection is null %s", nullPointerException.getMessage()));
        }

    }

    public void addBox(Box box) {
        checkConnection();

        try {

            conn = SQLConnection.dbConnector();

            preparedStatement =
                    conn.prepareStatement("INSERT INTO box(container_colour, country, box_name,shipping_cost, weight_in_kilo_grams )" +
                            " VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setString(1, box.getContainerColour());
            preparedStatement.setString(2, box.getCountry());
            preparedStatement.setString(3, box.getBoxName());
            preparedStatement.setDouble(4, box.getShippingCost());
            preparedStatement.setDouble(5, box.getWeightInKiloGrams());

            preparedStatement.execute();
            conn.close();

        } catch (SQLException | NullPointerException nullPointerException) {

            System.out.printf("Error %s", nullPointerException.getMessage());
        }
    }

    public List<Box> listboxes() {
        checkConnection();

        ArrayList<Box> boxArrayList = new ArrayList<>();

        try {
            conn = SQLConnection.dbConnector();
            String queryGetBoxes = "SELECT * from box";
            statement = conn.createStatement();

            resultSet = statement.executeQuery(queryGetBoxes);
            while (resultSet.next()) {
                boxArrayList.add(new Box(
                        resultSet.getLong("id"),
                        resultSet.getString("country"),
                        resultSet.getString("box_name"),
                        resultSet.getDouble("weight_in_kilo_grams"),
                        resultSet.getString("container_colour"),
                        resultSet.getDouble("shipping_cost")
                ));
            }
            conn.close();
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage() + " err");
        }
        return boxArrayList;
    }
}
