package PROJECT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLImportExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/payroll_db"; // replace with your database URL
        String user = "root"; // replace with your database username
        String password = "R@Kr#57gh"; // replace with your database password

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");

            String query = "SELECT * FROM your_table"; // replace with your table name
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id"); // replace with your column name
                String name = resultSet.getString("name"); // replace with your column name
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

