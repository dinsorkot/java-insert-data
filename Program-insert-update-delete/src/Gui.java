import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Gui {
	static cn_db_mysql cdm = new cn_db_mysql();
    private JFrame frame;
    private JTabbedPane tabbedPane;

    // Fields for insertion tab
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dobField;
    private JTextField genderField;
    private JButton insertButton;

    // Fields for update tab
    private JTextField IdToSelectUpdate;
    private JTextField updateFirstNameField;
    private JTextField updateLastNameField;
    private JTextField updateDobField;
    private JTextField updateGenderField;
    private JButton updateButton;

    // Fields for delete tab
    private JTextField deleteIdUser;
    private JButton deleteButton;
    
    // Fields for search tab
    private JTextField searchIdUser;
    private JButton searchButton;
    private students stds = new students();
    public Gui() {
        frame = new JFrame("Student Information");
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        
        /*
        // Insert Tab
        JPanel insertPanel = new JPanel(new GridLayout(5, 2));
        insertPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        insertPanel.add(firstNameField);
        insertPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        insertPanel.add(lastNameField);
        insertPanel.add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        insertPanel.add(dobField);
        insertPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        insertPanel.add(genderField);
        insertButton = new JButton("Insert");
        insertPanel.add(insertButton);
		*/
        // Insert Tab
        JPanel insertPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        insertPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        firstNameField = new JTextField(15); // Specify a preferred width
        insertPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        insertPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        lastNameField = new JTextField(15); // Specify a preferred width
        insertPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        insertPanel.add(new JLabel("Date of Birth:"), gbc);
        gbc.gridx = 1;
        dobField = new JTextField(15); // Specify a preferred width
        insertPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        insertPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1;
        genderField = new JTextField(15); // Specify a preferred width
        insertPanel.add(genderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        insertButton = new JButton("Insert");
        insertButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        insertPanel.add(insertButton, gbc);
        
        // Update Tab
        /*
        JPanel updatePanel = new JPanel(new GridLayout(5, 4));
        updatePanel.add(new JLabel("ID (to update):"));
        IdToSelectUpdate = new JTextField();
        updatePanel.add(IdToSelectUpdate);
        updatePanel.add(new JLabel("New First Name:"));
        updateFirstNameField = new JTextField();
        updatePanel.add(updateFirstNameField);
        updatePanel.add(new JLabel("New Last Name:"));
        updateLastNameField = new JTextField();
        updatePanel.add(updateLastNameField);
        updatePanel.add(new JLabel("New Date of Birth:"));
        updateDobField = new JTextField();
        updatePanel.add(updateDobField);
        updatePanel.add(new JLabel("New Gender:"));
        updateGenderField = new JTextField();
        updatePanel.add(updateGenderField);
        updateButton = new JButton("Update");
        updatePanel.add(updateButton);
        */
        // Update Tab
        JPanel updatePanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        updatePanel.add(new JLabel("ID (to update):"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        IdToSelectUpdate = new JTextField(15);
        updatePanel.add(IdToSelectUpdate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        updatePanel.add(new JLabel("New First Name:"), gbc);
        gbc.gridx = 1;
        updateFirstNameField = new JTextField(15);
        updatePanel.add(updateFirstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        updatePanel.add(new JLabel("New Last Name:"), gbc);
        gbc.gridx = 1;
        updateLastNameField = new JTextField(15);
        updatePanel.add(updateLastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        updatePanel.add(new JLabel("New Date of Birth:"), gbc);
        gbc.gridx = 1;
        updateDobField = new JTextField(15);
        updatePanel.add(updateDobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        updatePanel.add(new JLabel("New Gender:"), gbc);
        gbc.gridx = 1;
        updateGenderField = new JTextField(15);
        updatePanel.add(updateGenderField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        updateButton = new JButton("Update");
        updatePanel.add(updateButton, gbc);

        
        /*
        // Delete Tab
        JPanel deletePanel = new JPanel(new GridLayout(5, 2));
        deletePanel.add(new JLabel("ID (to delete):"));
        deleteIdUser = new JTextField();
        deletePanel.add(deleteIdUser);
        deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);
        */
        // Delete Tab
        JPanel deletePanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        deletePanel.add(new JLabel("ID (to delete):"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        deleteIdUser = new JTextField(15); // Specify a preferred width
        deletePanel.add(deleteIdUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        deleteButton = new JButton("Delete");
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        deletePanel.add(deleteButton, gbc);
        
        // show Tab
        JPanel showPanel = new JPanel(new GridLayout(5, 2));
        try (Connection conn = cdm.getConnection()) {

            // Create a panel for displaying data
            showPanel = new JPanel(new BorderLayout());

            // Create and execute the SQL query
            String sql = "SELECT * FROM students";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            // Create a DefaultTableModel for the table
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Student ID");
            model.addColumn("First Name");
            model.addColumn("Last Name");
            model.addColumn("Gender");

            // Populate the table model with data from the database
            while (resultSet.next()) {
                String id = resultSet.getString("StudentID");
                String fname = resultSet.getString("FirstName");
                String lname = resultSet.getString("LastName");
                String gender = resultSet.getString("Gender");
                model.addRow(new Object[]{id, fname, lname, gender});
            }

            // Create a JTable with the table model
            JTable table = new JTable(model);

            // Set the table to fill the viewport height
            table.setFillsViewportHeight(true);

            // Create a JScrollPane to hold the table
            JScrollPane scrollPane = new JScrollPane(table);

            // Create a JPanel to hold the scroll pane
            JPanel tablePanel = new JPanel(new BorderLayout());

            // Add the scroll pane to the tablePanel in the center
            tablePanel.add(scrollPane, BorderLayout.CENTER);

            // Add the tablePanel to the showPanel
            showPanel.add(tablePanel, BorderLayout.CENTER);

            // Add the showPanel to the frame's center
            frame.add(showPanel, BorderLayout.CENTER);

            // Create a Refresh button and add it to the showPanel's SOUTH (bottom)
            JButton refreshButton = new JButton("Refresh");
            showPanel.add(refreshButton, BorderLayout.SOUTH);

            // Set the frame's layout to BorderLayout
            frame.setLayout(new BorderLayout());

            // Add an action listener to the Refresh button
            refreshButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    refreshTable(table);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
        // search Tab
        JPanel searchPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(new JLabel("ID (to search):"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        searchIdUser = new JTextField(15); // Specify a preferred width
        searchPanel.add(searchIdUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        searchButton = new JButton("search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        searchPanel.add(searchButton, gbc);
        
        tabbedPane.addTab("Insert", insertPanel);
        tabbedPane.addTab("Update", updatePanel);
        tabbedPane.addTab("Delete", deletePanel);
        tabbedPane.addTab("Show", showPanel);
        tabbedPane.addTab("Search", searchPanel);
        

        frame.add(tabbedPane, BorderLayout.CENTER);
        
        
        

        // Add action listeners for buttons in each tab (similar to your existing code)
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle insertion logic
                stds.setFirst_name(firstNameField.getText());
                stds.setLast_name(lastNameField.getText());
                stds.setDate_of_birth(dobField.getText());
                stds.setGender(genderField.getText());

                // Call the insertData method with the new student object
                String alert = stds.insertData(stds);
                JOptionPane.showMessageDialog(null, alert);
                
                firstNameField.setText("");
                lastNameField.setText("");
                dobField.setText("");
                genderField.setText("");
                
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle update logic
                stds.setStudent_id(IdToSelectUpdate.getText());
                stds.setFirst_name(updateFirstNameField.getText());
                stds.setLast_name(updateLastNameField.getText());
                stds.setDate_of_birth(updateDobField.getText());
                stds.setGender(updateGenderField.getText());

                String alert = stds.updateData(stds);
                JOptionPane.showMessageDialog(null, alert);
                
                IdToSelectUpdate.setText("");
                updateFirstNameField.setText("");
                updateLastNameField.setText("");
                updateDobField.setText("");
                updateGenderField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete logic
                stds.setStudent_id(deleteIdUser.getText());
                
                String alert = stds.deleteData(stds);
                JOptionPane.showMessageDialog(null, alert);
                
                deleteIdUser.setText("");
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete logic
                stds.setStudent_id(searchIdUser.getText());
                
                String alert = stds.selectData(stds);
                JOptionPane.showMessageDialog(null, alert);
                
                searchIdUser.setText("");
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
    }
    
    // Method to refresh the table in the Delete tab
    private void refreshTable(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table

        try (Connection conn = cdm.getConnection()) {
            String sql = "SELECT * FROM students";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery(); // Execute the query

            // Populate the table model with data
            while (resultSet.next()) {
                String id = resultSet.getString("StudentID");
                String fname = resultSet.getString("FirstName");
                String lname = resultSet.getString("LastName");
                String gender = resultSet.getString("Gender");

                model.addRow(new Object[]{id, fname, lname, gender});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
