package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> dinosaurs = new ArrayList<>();
        String SQL = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed ASC";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dinosaurs.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dinosaurs;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

}
