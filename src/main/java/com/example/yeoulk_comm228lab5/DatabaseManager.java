package com.example.yeoulk_comm228lab5;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DatabaseManager {
    private final String url = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private final String username = "COMP228_F24_soh_13";
    private final String password = "password0124";


    public void testConnection() {
        try {

            Class.forName("oracle.jdbc.OracleDriver");


            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                if (conn != null) {
                    System.out.println("Database connected successfully!");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database.");
            e.printStackTrace();
        }
    }


    public void updatePlayer(int playerId, String firstName, String lastName, String address,
                             String postalCode, String province, String phoneNumber) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String updateSql = "UPDATE COMP228_F24_SOH_13.YEOUL_KANG_PLAYER SET first_name = ?, last_name = ?, address = ?, postal_code = ?, province = ?, phone_number = ? WHERE player_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                stmt.setString(3, address);
                stmt.setString(4, postalCode);
                stmt.setString(5, province);
                stmt.setString(6, phoneNumber);
                stmt.setInt(7, playerId);

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Player updated successfully!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while updating player.");
            e.printStackTrace();
        }
    }


    public void savePlayer(String firstName, String lastName, String address, String postalCode,
                           String province, String phoneNumber) {
        try {
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String playerSql = "INSERT INTO COMP228_F24_SOH_13.YEOUL_KANG_PLAYER (first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql, Statement.RETURN_GENERATED_KEYS)) {
                    playerStmt.setString(1, firstName);
                    playerStmt.setString(2, lastName);
                    playerStmt.setString(3, address);
                    playerStmt.setString(4, postalCode);
                    playerStmt.setString(5, province);
                    playerStmt.setString(6, phoneNumber);
                    playerStmt.executeUpdate();


                    ResultSet keys = playerStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int playerId = keys.getInt(1);


                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while saving player or game data.");
            e.printStackTrace();
        }
    }


    public void displayPlayerData(DefaultTableModel tableModel) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String querySql = "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number "
                    + "FROM COMP228_F24_SOH_13.YEOUL_KANG_PLAYER p "
                    + "JOIN COMP228_F24_SOH_13.YEOUL_KANG_PLAYER_AND_GAME pg ON p.player_id = pg.player_id "
                    + "JOIN COMP228_F24_SOH_13.YEOUL_KANG_GAME g ON pg.game_id = g.game_id";
            try (PreparedStatement stmt = conn.prepareStatement(querySql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = {
                            rs.getInt("player_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("address"),
                            rs.getString("postal_code"),
                            rs.getString("province"),
                            rs.getString("phone_number"),

                    };
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while fetching player data.");
            e.printStackTrace();
        }
    }
}
