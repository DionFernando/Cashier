package repository;

import db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SpendRepo {
    public void expenseDeductAndTotalSaleUpdate(double expense) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            ExpenseRepo expenseRepo = new ExpenseRepo();
            boolean isExpenseDeducted = expenseRepo.isExpenseDeducted(expense);

            if (isExpenseDeducted) {
                TotalRepo totalRepo = new TotalRepo();
                totalRepo.updateTotalSale(expense);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
