package repository;

import db.DbConnection;
import model.Total;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TotalRepo {

    public void saveOrUpdateTotalSales(Total total) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO total_sales (id, total_sales_count, total_sales) VALUES (1, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE total_sales_count = total_sales_count + ?, total_sales = total_sales + ?"
            );
            preparedStatement.setObject(1, total.getTotalCount());
            preparedStatement.setObject(2, total.getTotalSales());
            preparedStatement.setObject(3, total.getTotalCount());
            preparedStatement.setObject(4, total.getTotalSales());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSaleCount() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_sales_count FROM total_sales WHERE id = 1");
            return preparedStatement.executeQuery().getInt(1);
        } catch (Exception e) {
            return 0;
        }
    }

    public double getSaleTotal() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT total_sales FROM total_sales WHERE id = 1");
            return preparedStatement.executeQuery().getDouble(1);
        } catch (Exception e) {
            return 0;
        }
    }
}
