//
package gui;

import javax.swing.*;

import model.ArrivingProduct;
import model.ProductRecord;
import sqldb.SQLCommandExecuter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

//file name
public class Dashboard_Final extends JFrame {
	
    private List<ProductRecord> productRecords;
    private JPanel productListPanel;
    
    public Dashboard_Final(List<ProductRecord> list) {   
    	
    	this.productRecords = list;
    	
    	// Set up the main frame
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        getContentPane().setLayout(null);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(224, 22, 200, 150);

        JButton reportsButton = new JButton("Reports");
        reportsButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        reportsButton.setBounds(0, 0, 180, 50);

        JButton dashboardButton = new JButton("Dashboard");
        dashboardButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        dashboardButton.setBounds(0, 60, 180, 50);

        navPanel.add(reportsButton);
        navPanel.add(dashboardButton);

        getContentPane().add(navPanel);

        
 /*
        // Capacity Indicator
        JLabel capacityLabel = new JLabel("Capacity");
        capacityLabel.setBounds(300, 20, 100, 20);
        getContentPane().add(capacityLabel);

        JPanel capacityPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.GRAY);
                g2d.fillArc(0, 0, 100, 100, 0, 360);
                g2d.setColor(new Color(54, 162, 235));
                g2d.fillArc(0, 0, 100, 100, 90, -216);
                g2d.setColor(Color.WHITE);
                g2d.fillOval(20, 20, 60, 60);
                g2d.setColor(Color.BLACK);
                g2d.drawString("60%", 35, 55);
            }
        };
        capacityPanel.setBounds(300, 50, 100, 100);
        getContentPane().add(capacityPanel);
        
 */     
        
/*
        // Summary Cards
        String[] summaries = {"Arriving 235", "Stored 235", "Shipping 323"};
        for (int i = 0; i < summaries.length; i++) {
            JPanel summaryPanel = new JPanel();
            summaryPanel.setLayout(null);
            summaryPanel.setBounds(450 + i * 150, 50, 120, 100);
            summaryPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            JLabel summaryLabel = new JLabel("<html>" + summaries[i].replace(" ", "<br>") + "</html>");
            summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            summaryLabel.setBounds(10, 20, 100, 60);

            summaryPanel.add(summaryLabel);
            getContentPane().add(summaryPanel);
        }
*/
        

        // Sorting Buttons
        String[] sortOptions = {"Arriving", "Stored", "Shipped"};
        for (int i = 0; i < sortOptions.length; i++) {
            JButton sortButton = new JButton(sortOptions[i]);
            sortButton.setBounds(20 + i * 110, 200, 100, 30);
            getContentPane().add(sortButton);
        }

        // Search Field
        JTextField searchField = new JTextField("");
        searchField.setBounds(608, 201, 200, 30);
        getContentPane().add(searchField);

        JButton addButton = new JButton("+ Add");
        addButton.setBounds(818, 200, 100, 30);

        // Add button action listener 
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputFrame(null);
            }
        });

        // Set button background color
        addButton.setBackground(new Color(12, 116, 137));
        
        
        // Set button text color to white
        addButton.setForeground(Color.WHITE);
        
        // Add the button to the frame
        getContentPane().add(addButton);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(253, 243, 698, 598);
        getContentPane().add(scrollPane);
                
        // Product List Panel
        productListPanel = new JPanel();
        scrollPane.setColumnHeaderView(productListPanel);
        productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
        
        updateProductListPanel();
        setVisible(true);
    }

    private void showInputFrame(ProductRecord record) {
        // Create a new frame for input
        JFrame inputFrame = new JFrame("Add Product");
        inputFrame.setSize(400, 400); // Adjust size as needed
        inputFrame.setLocationRelativeTo(null); // Center the frame on the screen
        inputFrame.setUndecorated(true); // Remove window decorations

        // Add shadow border
        inputFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        // Input panel for product details
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Color backgroundColor = new Color(251, 251, 251, 255);

        // Create label and text field for product name
        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        nameField.setBackground(backgroundColor);

        // Create label for status
        JLabel statusLabel = new JLabel("Status:");

        // Create panel for status buttons
        JPanel statusButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create buttons for Arriving, Stored, and Shipped
        JToggleButton arrivingButton = new JToggleButton("Arriving");
        arrivingButton.setPreferredSize(new Dimension(90, 30)); // Set button size

        JToggleButton storedButton = new JToggleButton("Stored");
        storedButton.setPreferredSize(new Dimension(90, 30)); // Set button size

        JToggleButton shippingButton = new JToggleButton("Shipped");
        shippingButton.setPreferredSize(new Dimension(90, 30)); // Set button size

        // ButtonGroup to ensure only one status can be selected at a time
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(arrivingButton);
        statusGroup.add(storedButton);
        statusGroup.add(shippingButton);

        // Panel to hold dynamic input fields
        JPanel dynamicPanel = new JPanel(new GridBagLayout());

        // Additional fields for Arriving status
        JLabel originLabel = new JLabel("Origin:");
        JTextField originField = new JTextField();
        originField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        originField.setBackground(backgroundColor);

        JLabel conditionLabel = new JLabel("Condition:");
        JTextField conditionField = new JTextField();
        conditionField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        conditionField.setBackground(backgroundColor);

        JLabel arrivalDateLabel = new JLabel("Arrival Date and Time:");
        JTextField arrivalDateField = new JTextField();
        arrivalDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        arrivalDateField.setBackground(backgroundColor);

        // Additional fields for Stored status
        JLabel storedLocationLabel = new JLabel("Shelf Location:");
        JTextField storedLocationField = new JTextField();
        storedLocationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        storedLocationField.setBackground(backgroundColor);

        JLabel storedDateLabel = new JLabel("Date and Time Stored:");
        JTextField storedDateField = new JTextField();
        storedDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        storedDateField.setBackground(backgroundColor);

        // Additional fields for Shipped status
        JLabel destinationLabel = new JLabel("Destination:");
        JTextField destinationField = new JTextField();
        destinationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        destinationField.setBackground(backgroundColor);

        JLabel courierLabel = new JLabel("Courrier:");
        JTextField courierField = new JTextField();
        courierField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        courierField.setBackground(backgroundColor);

        JLabel shippedDateLabel = new JLabel("Date and Time Shipped:");
        JTextField shippedDateField = new JTextField();
        shippedDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        shippedDateField.setBackground(backgroundColor);

        // Hide the dynamic panel by default
        dynamicPanel.setVisible(false);

        // Add action listeners to status buttons
        arrivingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamicPanel.removeAll();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                dynamicPanel.add(originLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(originField, gbc);

                gbc.gridy++;
                dynamicPanel.add(conditionLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(conditionField, gbc);

                gbc.gridy++;
                dynamicPanel.add(arrivalDateLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(arrivalDateField, gbc);

                dynamicPanel.setVisible(true);
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            }
        });

        storedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamicPanel.removeAll();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                dynamicPanel.add(storedLocationLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(storedLocationField, gbc);

                gbc.gridy++;
                dynamicPanel.add(storedDateLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(storedDateField, gbc);

                dynamicPanel.setVisible(true);
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            }
        });

        shippingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dynamicPanel.removeAll();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                dynamicPanel.add(destinationLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(destinationField, gbc);

                gbc.gridy++;
                dynamicPanel.add(courierLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(courierField, gbc);

                gbc.gridy++;
                dynamicPanel.add(shippedDateLabel, gbc);

                gbc.gridy++;
                dynamicPanel.add(shippedDateField, gbc);

                dynamicPanel.setVisible(true);
                dynamicPanel.revalidate();
                dynamicPanel.repaint();
            }
        });

        // Add buttons to the status button panel
        statusButtonPanel.add(arrivingButton);
        statusButtonPanel.add(storedButton);
        statusButtonPanel.add(shippingButton);

        // Set GridBagConstraints for inputPanel components
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        inputPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        inputPanel.add(statusLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(statusButtonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(dynamicPanel, gbc);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(12, 116, 137));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle save action
                String productName = nameField.getText();
                String status = arrivingButton.isSelected() ? "Arriving" :
                        storedButton.isSelected() ? "Stored" :
                                shippingButton.isSelected() ? "Shipped" : "";

                String details = "";

                if (status.equals("Arriving")) {
                    details = "Origin: " + originField.getText() + ", Condition: " + conditionField.getText() + ", Arrival Date: " + arrivalDateField.getText();
                } else if (status.equals("Stored")) {
                    details = "Shelf Location: " + storedLocationField.getText() + ", Date Stored: " + storedDateField.getText();
                } else if (status.equals("Shipped")) {
                    details = "Destination: " + destinationField.getText() + ", Courier: " + courierField.getText() + ", Date Shipped: " + shippedDateField.getText();
                }

                if (record == null) {
                    productRecords.add(new ProductRecord(productName, status, details));
                } else {
                    record.setProductName(productName);
                    record.setStatus(status);
                    record.setDetails(details);
                }
                updateProductListPanel();
                inputFrame.dispose(); // Close the input frame after saving
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(new Color(12, 116, 137));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFrame.dispose(); // Close the input frame without saving
            }
        });
       
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        inputFrame.getContentPane().setLayout(new BorderLayout());
        // Add input panel to the frame
        inputFrame.getContentPane().add(inputPanel, BorderLayout.CENTER);
        // Add button panel to the frame
        inputFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        inputFrame.setVisible(true); // Make the frame visible

        if (record != null) {
            nameField.setText(record.getProductName());
            String status = record.getStatus();
            if (status.equals("Arriving")) {
            	
                arrivingButton.doClick();
                String[] details = record.getDetails().split(", ");
                
                originField.setText(details[0].split(": ")[1]);
                conditionField.setText(details[1].split(": ")[1]);
                arrivalDateField.setText(details[2].split(": ")[1]);
                
            } else if (status.equals("Stored")) {
            	
                storedButton.doClick();
                String[] details = record.getDetails().split(", ");
                
                storedLocationField.setText(details[0].split(": ")[1]);
                storedDateField.setText(details[1].split(": ")[1]);
                
            } else if (status.equals("Shipped")) {
            	
                shippingButton.doClick();
                String[] details = record.getDetails().split(", ");
                
                destinationField.setText(details[0].split(": ")[1]);
                courierField.setText(details[1].split(": ")[1]);
                shippedDateField.setText(details[2].split(": ")[1]);
            }
        }
    }

	private void updateProductListPanel() {
	    productListPanel.removeAll();
	 
	    for (ProductRecord record : productRecords) {
	        JPanel productPanel = new JPanel();
	        productPanel.setLayout(null);
	        productPanel.setPreferredSize(new Dimension(680, 80));
	        productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	
	        JLabel productNameLabel = new JLabel("Product Name: " + record.getProductName());
	        productNameLabel.setBounds(10, 10, 300, 20);
	        productPanel.add(productNameLabel);
	
	        JLabel statusLabel = new JLabel("Status: " + record.getStatus());
	        statusLabel.setBounds(10, 40, 100, 20);
	        productPanel.add(statusLabel);
	
	        JLabel detailsLabel = new JLabel(record.getDetails());
	        detailsLabel.setBounds(150, 40, 400, 20);
	        productPanel.add(detailsLabel);
	
	        JButton editButton = new JButton("Edit");
	        editButton.setBounds(520, 25, 80, 30);
	        editButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                showInputFrame(record);
	            }
	        });
	        productPanel.add(editButton);
	
	        JButton deleteButton = new JButton("Delete");
	        deleteButton.setBounds(600, 25, 80, 30);
	        deleteButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                int response = JOptionPane.showConfirmDialog(
	                    null,
	                    "Are you sure you want to delete?",
	                    "Confirm Deletion",
	                    JOptionPane.YES_NO_OPTION,
	                    JOptionPane.WARNING_MESSAGE
	                );
	
	                if (response == JOptionPane.YES_OPTION) {
	                    productRecords.remove(record);
	                    updateProductListPanel();
	                }
	            }
	        });
	        productPanel.add(deleteButton);
	
	        productListPanel.add(productPanel);
	    }
	
	    productListPanel.revalidate();
	    productListPanel.repaint();
	}
	
}
	/*
	private List<ArrivingProduct> initializeArrivingProducts() {
    	try {
    		arrivingProducts = (ArrayList<ArrivingProduct>) sqlexecute.getArrivingProduct();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQL problem");
		}
    	return arrivingProducts;
    }
    
    public void setDbData(List<ArrivingProduct> list) {
    	for (ArrivingProduct record : list) {
    		productRecords.add(new ProductRecord(record.getProductName(),
    				record.getStatus(),
    				record.getDetails()
    				));
    	}
    }
    
    */
	
	
	/*
	public static void main(String[] args) {
		productRecords = new ArrayList<>();
		ProductRecord pr1 = new ProductRecord("productname", "Arriving", "Origin: asdasd, Condition: asdasd, Arrival Date: 123");
	    productRecords.add(pr1);
	    
	    ProductRecord pr2 = new ProductRecord("productname", "Arriving", "Origin: asdasd, Condition: asdasd, Arrival Date: 123");
	    productRecords.add(pr2);
		
	    SwingUtilities.invokeLater(() -> {
	        //filel name
	        Dashboard_Final ui = new Dashboard_Final();
	        ui.setVisible(true);
	    });
	    

	}
	
*/


// Separate class to store product records





