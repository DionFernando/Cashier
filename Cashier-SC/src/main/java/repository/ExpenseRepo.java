package repository;

import db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseRepo {
    public boolean isExpenseDeducted(double expense) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO expense(expense) VALUES (?)");
        preparedStatement.setDouble(1, expense);
        return preparedStatement.executeUpdate() > 0;
    }

    public double getExpenseTotal() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(expense) FROM expense");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble(1);
        } else {
            return 0.0; // return a default value if there are no rows in the ResultSet
        }
    }
}
