package com.example.yeoulk_comm228lab5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class PlayerPanel extends JPanel {
    private JTextField playerIdField, firstNameField, lastNameField, addressField, postalCodeField, provinceField, phoneNumberField;
    private JButton saveButton, updateButton, displayButton;
    private JTable playerTable;
    private DefaultTableModel tableModel;

    public PlayerPanel() {
        setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Player ID:"), gbc);
        playerIdField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(playerIdField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("First Name:"), gbc);
        firstNameField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(firstNameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Last Name:"), gbc);
        lastNameField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(lastNameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Address:"), gbc);
        addressField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Postal Code:"), gbc);
        postalCodeField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(postalCodeField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 5;
        inputPanel.add(new JLabel("Province:"), gbc);
        provinceField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(provinceField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 6;
        inputPanel.add(new JLabel("Phone Number:"), gbc);
        phoneNumberField = new JTextField(15);
        gbc.gridx = 1;
        inputPanel.add(phoneNumberField, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        displayButton = new JButton("Display");
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);


        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("Player ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Postal Code");
        tableModel.addColumn("Province");
        tableModel.addColumn("Phone Number");


        playerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(playerTable);


        add(scrollPane, BorderLayout.CENTER);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String postalCode = postalCodeField.getText();
                String province = provinceField.getText();
                String phoneNumber = phoneNumberField.getText();


                DatabaseManager dbManager = new DatabaseManager();
                dbManager.savePlayer(firstName, lastName, address, postalCode, province, phoneNumber);
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int playerId = Integer.parseInt(playerIdField.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String address = addressField.getText();
                String postalCode = postalCodeField.getText();
                String province = provinceField.getText();
                String phoneNumber = phoneNumberField.getText();


                DatabaseManager dbManager = new DatabaseManager();
                dbManager.updatePlayer(playerId, firstName, lastName, address, postalCode, province, phoneNumber);
            }
        });


        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatabaseManager dbManager = new DatabaseManager();
                dbManager.displayPlayerData(tableModel);
            }
        });
    }
}
