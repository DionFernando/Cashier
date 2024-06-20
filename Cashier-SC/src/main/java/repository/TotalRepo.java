package repository;

import db.DbConnection;
import model.Total;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TotalRepo {

    public void saveOrUpdateTotalSales(Total total) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO total_sales (id, total_sales_count, total_sales) VALUES (1, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE total_sales_count = total_sales_count + ?, total_sales = total_sales + ?"
            );
            preparedStatement.setInt(1, total.getTotalCount());
            preparedStatement.setDouble(2, total.getTotalSales());
            preparedStatement.setInt(3, total.getTotalCount());
            preparedStatement.setDouble(4, total.getTotalSales());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getSaleCount() {
        int totalSalesCount = 0;

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_sales_count FROM total_sales WHERE id = 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totalSalesCount = resultSet.getInt("total_sales_count");
            } else {
                System.err.println("No data found for id = 1");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred while retrieving total sales count.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected exception occurred while retrieving total sales count.");
            e.printStackTrace();
        }
        return totalSalesCount;
    }


    public double getSaleTotal() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_sales FROM total_sales WHERE id = 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double totalSales = resultSet.getDouble("total_sales");
                System.out.println(totalSales);
                return totalSales;
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
