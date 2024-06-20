package repository;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepo {

    public void checkEmptyAndInsert() throws SQLException {
        //check total_per_item table
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM total_per_item");
        if (!preparedStatement.executeQuery().next()) {
            preparedStatement = connection.prepareStatement("INSERT INTO total_per_item(item_name, total) VALUES (?,?)");
            preparedStatement.setString(1, "Popcorn");
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO total_per_item(item_name, total) VALUES (?,?)");
            preparedStatement.setString(1, "Chips");
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO total_per_item(item_name, total) VALUES (?,?)");
            preparedStatement.setString(1, "Coke");
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO total_per_item(item_name, total) VALUES (?,?)");
            preparedStatement.setString(1, "Sprite");
            preparedStatement.setInt(2, 0);
            preparedStatement.executeUpdate();
        }
    }

    public double getItemIncome(String item) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT total FROM total_per_item WHERE item_name=?");
        preparedStatement.setString(1, item);
        ResultSet resultSet = preparedStatement.executeQuery();

        double income = 0.0;
        if (resultSet.next()) {
            income = resultSet.getDouble("total");
        }

        return income;
    }

}
