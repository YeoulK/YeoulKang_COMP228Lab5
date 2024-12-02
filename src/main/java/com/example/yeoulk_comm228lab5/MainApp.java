package com.example.yeoulk_comm228lab5;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.testConnection();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Player and Game Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 1000);

            PlayerPanel playerPanel = new PlayerPanel();
            frame.add(playerPanel);

            frame.setVisible(true);
        });
    }
}
