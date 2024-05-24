package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.plaf.ColorUIResource;
import java.io.File;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;


//file name







public class Dashboard extends JFrame{

    private ArrayList<ProductRecord> productRecords;
    private JPanel productListPanel;
    private String currentFilterStatus = "All";
    private JLabel arrivingCardLabel;
    private JLabel storedCardLabel;
    private JLabel shippingCardLabel;
    private JPanel summaryCardPanel;
    
//white
Color defaultBackgroundColor = new Color(251, 251, 251);

Color defaultTextColor = new Color(12, 116, 137);

Color selectedBackgroundColor = new Color(12, 116, 137);

//white
Color selectedTextColor = new Color(251, 251, 251);


public class BottomShadowBorder implements Border {
    private int thickness;
    private Color shadowColor;
    private Insets insets;

    public BottomShadowBorder(int thickness, Color shadowColor) {
        this.thickness = thickness;
        this.shadowColor = shadowColor;
        this.insets = new Insets(0, 0, thickness, 0); // Only bottom insets
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(shadowColor);

        // Draw bottom shadow
        g2.fillRect(x, y + height - thickness, width, thickness);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}

   Border roundedBorder = new Border() {
	   
	   
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.gray);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(x, y, width - 1, height - 1, 20, 15); // Adjust the rounding radius as needed
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(1, 1, 1, 1); // Adjust the insets as needed
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        };
 




    //file name
    public Dashboard() {
        // Initialize product records list
	UIManager.put("Panel.background",defaultBackgroundColor);            
	UIManager.put("ToggleButton.select", new ColorUIResource(selectedBackgroundColor));
	UIManager.put("ToggleButton.foreground", new ColorUIResource(defaultTextColor));
	UIManager.put("ToggleButton.background", new ColorUIResource(defaultBackgroundColor));
        
        
        // Create the reportsButton with the Comfortaa font
        Font comfortaaFont = null;
        try {
            comfortaaFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Comfortaa-Bold.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
       
        
        
 // Initialize product records list
        productRecords = new ArrayList<>();

        // Set up the main frame
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
       // Change this to the color you want
        

        setLayout(null);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setBounds(20, 20, 500, 150);
        navPanel.setOpaque(false);
        this.getContentPane().setBackground(defaultBackgroundColor);
      

        // Sorting Buttons
    String[] sortOptions = {"Arriving", "Stored", "Shipped"};
  // Define the default and selected colors
JLabel sortByLabel = new JLabel("Sort by: ");
Font resizedComfortaaFont = comfortaaFont.deriveFont(22f);
sortByLabel.setFont(resizedComfortaaFont); // Set the font size to 16
sortByLabel.setBounds(50, 20, 100, 30);
add(sortByLabel);

Color defaultBackgroundColor = new Color(251, 251, 251);
Color defaultTextColor = new Color(12, 116, 137);
Color selectedBackgroundColor = new Color(12, 116, 137);
Color selectedTextColor = new Color(251, 251, 251);

// Initialize buttons
JButton arrivingButton = new JButton(sortOptions[0]);
  if (comfortaaFont != null) {
            arrivingButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            arrivingButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
  
    arrivingButton.setBorder(roundedBorder);    

  
  
arrivingButton.setBackground(defaultBackgroundColor); // Set the default background color
arrivingButton.setForeground(defaultTextColor); // Set the default text color
arrivingButton.setBounds(150, 20, 150, 30);
arrivingButton.setFocusPainted(false); // Disable focus border

JButton storedButton = new JButton(sortOptions[1]);
 if (comfortaaFont != null) {
            storedButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            storedButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
 
     storedButton.setBorder(roundedBorder);    

storedButton.setBackground(defaultBackgroundColor); // Set the default background color
storedButton.setForeground(defaultTextColor); // Set the default text color
storedButton.setBounds(325, 20, 150, 30);
storedButton.setFocusPainted(false); // Disable focus border

JButton shippingButton = new JButton(sortOptions[2]);
 if (comfortaaFont != null) {
            shippingButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            shippingButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
 
 shippingButton.setBorder(roundedBorder);    

 
shippingButton.setBackground(defaultBackgroundColor); // Set the default background color
shippingButton.setForeground(defaultTextColor); // Set the default text color
shippingButton.setBounds(500, 20, 150, 30);
shippingButton.setFocusPainted(false); // Disable focus border

// Add action listeners to buttons
arrivingButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Reset other buttons to default colors
        storedButton.setBackground(defaultBackgroundColor);
        storedButton.setForeground(defaultTextColor);
        shippingButton.setBackground(defaultBackgroundColor);
        shippingButton.setForeground(defaultTextColor);

        // Toggle colors for arrivingButton
        if (arrivingButton.getBackground().equals(defaultBackgroundColor)) {
            arrivingButton.setBackground(selectedBackgroundColor);
            arrivingButton.setForeground(selectedTextColor);
        } else {
            arrivingButton.setBackground(defaultBackgroundColor);
            arrivingButton.setForeground(defaultTextColor);
        }
    }
});

storedButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Reset other buttons to default colors
        arrivingButton.setBackground(defaultBackgroundColor);
        arrivingButton.setForeground(defaultTextColor);
        shippingButton.setBackground(defaultBackgroundColor);
        shippingButton.setForeground(defaultTextColor);

        // Toggle colors for storedButton
        if (storedButton.getBackground().equals(defaultBackgroundColor)) {
            storedButton.setBackground(selectedBackgroundColor);
            storedButton.setForeground(selectedTextColor);
        } else {
            storedButton.setBackground(defaultBackgroundColor);
            storedButton.setForeground(defaultTextColor);
        }
    }
});

shippingButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Reset other buttons to default colors
        arrivingButton.setBackground(defaultBackgroundColor);
        arrivingButton.setForeground(defaultTextColor);
        storedButton.setBackground(defaultBackgroundColor);
        storedButton.setForeground(defaultTextColor);

        // Toggle colors for shippingButton
        if (shippingButton.getBackground().equals(defaultBackgroundColor)) {
            shippingButton.setBackground(selectedBackgroundColor);
            shippingButton.setForeground(selectedTextColor);
        } else {
            shippingButton.setBackground(defaultBackgroundColor);
            shippingButton.setForeground(defaultTextColor);
        }
    }
});

// Add buttons to the panel or frame
add(arrivingButton);
add(storedButton);
add(shippingButton);

   
    
    // Add action listeners for sorting buttons
    arrivingButton.addActionListener(e -> {
        currentFilterStatus = "Arriving";
        updateProductListPanel();
    });

    storedButton.addActionListener(e -> {
        currentFilterStatus = "Stored";
        updateProductListPanel();
    });

    shippingButton.addActionListener(e -> {
        currentFilterStatus = "Shipped";
        updateProductListPanel();
    });
        JTextField searchField = new JTextField("");
        searchField.setBorder(roundedBorder);
        
        searchField.setFont(comfortaaFont);
        searchField.setBounds(700, 20, 400, 30);
        add(searchField); 
     
        JButton addButton = new JButton("+ Add");
        addButton.setFont(comfortaaFont);
        addButton.setBounds(1120, 20, 100, 30);
          if (comfortaaFont != null) {
            addButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            addButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        addButton.setFocusPainted(false); // Disable focus border

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
        add(addButton);

        // Product List Panel
      productListPanel = new JPanel();
      
     
productListPanel.setLayout(new BoxLayout(productListPanel, BoxLayout.Y_AXIS));
JScrollPane scrollPane = new JScrollPane(productListPanel);

scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Set an empty border
scrollPane.setBounds(100, 70, 1150, 500); // Set the bounds of the scroll pane

scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Set vertical scrollbar policy
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Set horizontal scrollbar policy  

// Get the vertical scrollbar
JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
// Get the horizontal scrollbar
JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();

// Set custom unit and block increments for vertical scrollbar
verticalScrollBar.setUnitIncrement(20); // Adjust this value as needed
verticalScrollBar.setBlockIncrement(100); // Adjust this value as needed

// Set custom unit and block increments for horizontal scrollbar
horizontalScrollBar.setUnitIncrement(20); // Adjust this value as needed
horizontalScrollBar.setBlockIncrement(100); // Adjust this value as needed

add(scrollPane); // Add the scroll pane to the container


               
        


    }
    //Sumamary cards panel
  private JLabel createSummaryCard(String text) {    
    JLabel label = new JLabel("<html><div style='text-align: center;'><br>"
                              + "<span style='font-size:16px;font-weight:bold;'>" + text + "</span><br>"
                              + "</div></html>");

    // Load and set the custom font
    Font comfortaaFont = null;
    try {
        comfortaaFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Comfortaa-Bold.ttf")).deriveFont(14f);
    } catch (FontFormatException | IOException e) {
        e.printStackTrace();
        // Set a fallback font in case the custom font fails to load
        comfortaaFont = new Font("SansSerif", Font.PLAIN, 14);
    }
    
    // Resize the font
    Font resizedComfortaaFont = comfortaaFont.deriveFont(11f);
    label.setFont(resizedComfortaaFont);

    // Set border, background, and other properties
    Border roundedBorder = new Border() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.gray);
                g2.setStroke(new BasicStroke(1));
                g2.drawRoundRect(x, y, width - 1, height - 1, 20, 15); // Adjust the rounding radius as needed
                g2.dispose();
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(1, 1, 1, 1); // Adjust the insets as needed
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
        };
    label.setBorder(roundedBorder);
    label.setOpaque(true);
    Color defaultBackgroundColor = new Color(251, 251, 251);
    label.setBackground(defaultBackgroundColor);

    // Set preferred size
    label.setPreferredSize(new Dimension(150, 100));

    return label;
}




    private void showInputFrame(ProductRecord record) {
        // Create a new frame for input
        
          Font comfortaaFont = null;
        try {
            comfortaaFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Comfortaa-Regular.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        
        
        
        JFrame inputFrame = new JFrame("Add Product");
        inputFrame.setSize(400, 400); // Adjust size as needed
        inputFrame.setLocationRelativeTo(null); // Center the frame on the screen
        inputFrame.getContentPane().setBackground(selectedBackgroundColor);
        inputFrame.setLocationRelativeTo(null); // Center the frame on the screen
        inputFrame.setUndecorated(true); // Remove window decorations

        // Add shadow border
        inputFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        // Input panel for product details
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Color backgroundColor = (defaultBackgroundColor);

        // Create label and text field for product name
        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField();
        nameLabel.setFont(comfortaaFont);
        nameField.setFont(comfortaaFont);
        nameField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); // Only bottom border
        nameField.setBackground(backgroundColor);

        // Create label for status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(comfortaaFont);

        // Create panel for status buttons
        JPanel statusButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create buttons for Arriving, Stored, and Shipped
        JToggleButton arrivingButton = new JToggleButton("Arriving");
          if (comfortaaFont != null) {
            arrivingButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            arrivingButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        arrivingButton.setPreferredSize(new Dimension(90, 30)); // Set button size
        arrivingButton.setFocusPainted(false); // Disable focus border
       

        JToggleButton storedButton = new JToggleButton("Stored");
          if (comfortaaFont != null) {
            storedButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            storedButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
        storedButton.setPreferredSize(new Dimension(90, 30)); // Set button size
        storedButton.setFocusPainted(false); // Disable focus border



        JToggleButton shippingButton = new JToggleButton("Ship");
         if (comfortaaFont != null) {
            shippingButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            shippingButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }     
        shippingButton.setPreferredSize(new Dimension(90, 30)); // Set button size
        shippingButton.setFocusPainted(false); // Disable focus border   
        
                 
     
        
        
        // Panel to hold dynamic input fields
        JPanel dynamicPanel = new JPanel(new GridBagLayout());
        dynamicPanel.setBorder(roundedBorder);    

   

        // Additional fields for Arriving status
        JLabel originLabel = new JLabel("Origin ");
        JTextField originField = new JTextField();
        originLabel.setFont(comfortaaFont);
        originField.setFont(comfortaaFont);
        originField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        originField.setBackground(backgroundColor);

        JLabel conditionLabel = new JLabel("Condition");
        JTextField conditionField = new JTextField();
        conditionLabel.setFont(comfortaaFont);
        conditionField.setFont(comfortaaFont);
        conditionField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        conditionField.setBackground(backgroundColor);

        JLabel arrivalDateLabel = new JLabel("Arrival Date and Time");
        JTextField arrivalDateField = new JTextField();
        arrivalDateField.setFont(comfortaaFont);
        arrivalDateLabel.setFont(comfortaaFont);
        arrivalDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        arrivalDateField.setBackground(backgroundColor);

        // Additional fields for Stored status
        JLabel storedLocationLabel = new JLabel("Shelf Location");
        JTextField storedLocationField = new JTextField();
        storedLocationField.setFont(comfortaaFont);
        storedLocationLabel.setFont(comfortaaFont);
        storedLocationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        storedLocationField.setBackground(backgroundColor);

        JLabel storedDateLabel = new JLabel("Date and Time Stored");
        JTextField storedDateField = new JTextField();
        storedDateField.setFont(comfortaaFont);
        storedDateLabel.setFont(comfortaaFont);
        storedDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        storedDateField.setBackground(backgroundColor);

        // Additional fields for Shipped status
        JLabel destinationLabel = new JLabel("Destination");
        JTextField destinationField = new JTextField();
        destinationLabel.setFont(comfortaaFont);
        destinationField.setFont(comfortaaFont);
        destinationField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        destinationField.setBackground(backgroundColor);

        JLabel courierLabel = new JLabel("Courier");
        JTextField courierField = new JTextField();
        courierLabel.setFont(comfortaaFont);
        courierField.setFont(comfortaaFont);
        courierField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        courierField.setBackground(backgroundColor);

        JLabel shippedDateLabel = new JLabel("Date and Time Shipped");
        JTextField shippedDateField = new JTextField();
        shippedDateField.setFont(comfortaaFont);
        shippedDateLabel.setFont(comfortaaFont);
        shippedDateField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK)); // Only bottom border
        shippedDateField.setBackground(backgroundColor);

        // Hide the dynamic panel by default
        dynamicPanel.setVisible(true);
    
        
      shippingButton.setBackground(defaultBackgroundColor);
      arrivingButton.setBackground(defaultBackgroundColor);
      storedButton.setBackground(defaultBackgroundColor);
     
      shippingButton.setForeground(defaultTextColor);
      arrivingButton.setForeground(defaultTextColor);
      storedButton.setForeground(defaultTextColor); 
 
        
        // Add action listener to arrivingButton
arrivingButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {     
        // Toggle background and text colors   
    arrivingButton.setBackground(arrivingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    arrivingButton.setForeground(arrivingButton.isSelected() ? selectedTextColor : defaultTextColor);

    storedButton.setBackground(storedButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    storedButton.setForeground(storedButton.isSelected() ? selectedTextColor : defaultTextColor);

    shippingButton.setBackground(shippingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    shippingButton.setForeground(shippingButton.isSelected() ? selectedTextColor : defaultTextColor);
       
    dynamicPanel.setBackground(defaultBackgroundColor);

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

// Add action listener to storedButton
storedButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
   
              // Set dynamicPanel visibility
    arrivingButton.setBackground(arrivingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    arrivingButton.setForeground(arrivingButton.isSelected() ? selectedTextColor : defaultTextColor);

    storedButton.setBackground(storedButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    storedButton.setForeground(storedButton.isSelected() ? selectedTextColor : defaultTextColor);

    shippingButton.setBackground(shippingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    shippingButton.setForeground(shippingButton.isSelected() ? selectedTextColor : defaultTextColor);
   
    dynamicPanel.setBackground(defaultBackgroundColor);
    
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

// Add action listener to shippingButton
shippingButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {  
        // Set dynamicPanel visibility
    arrivingButton.setBackground(arrivingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    arrivingButton.setForeground(arrivingButton.isSelected() ? selectedTextColor : defaultTextColor);

    storedButton.setBackground(storedButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    storedButton.setForeground(storedButton.isSelected() ? selectedTextColor : defaultTextColor);

    shippingButton.setBackground(shippingButton.isSelected() ? selectedBackgroundColor : defaultBackgroundColor);
    shippingButton.setForeground(shippingButton.isSelected() ? selectedTextColor : defaultTextColor);
           
    dynamicPanel.setBackground(defaultBackgroundColor);
    
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

     // ButtonGroup to ensure only one status can be selected at a time
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(arrivingButton);
        statusGroup.add(storedButton);
        statusGroup.add(shippingButton);
        
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
        saveButton.setFocusPainted(false); // Disable focus border
        
             
        
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
            details = "Origin: " + originField.getText() + ", Condition:  " + conditionField.getText() + ", Arrival Date: " + arrivalDateField.getText();
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
        cancelButton.setFocusPainted(false); // Disable focus border
        
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFrame.dispose(); // Close the input frame without saving
            }
        });
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        inputFrame.setLayout(new BorderLayout());
        // Add input panel to the frame
        inputFrame.add(inputPanel, BorderLayout.CENTER);
        // Add button panel to the frame
        inputFrame.add(buttonPanel, BorderLayout.SOUTH);

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
    
      Font comfortaaFont = null;
        try {
            comfortaaFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/fonts/Comfortaa-Bold.ttf")).deriveFont(14f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        } 
             
   productListPanel.removeAll();
    
    for (ProductRecord record : productRecords) {
        if (currentFilterStatus.equals("All") || record.getStatus().equals(currentFilterStatus)) {
            JPanel productPanel = new JPanel();
            productPanel.setLayout(null);
            productPanel.setPreferredSize(new Dimension(100, 120));
            productPanel.setBorder(roundedBorder);    

            JLabel productNameLabel = new JLabel("Product Name:     " + record.getProductName());
            productNameLabel.setFont(comfortaaFont);
            productNameLabel.setBounds(10, 10, 300, 20);
            productPanel.add(productNameLabel);

            
                    
            JLabel statusLabel = new JLabel("Status:   " + record.getStatus());
            statusLabel.setFont(comfortaaFont);
            statusLabel.setBounds(30, 40, 150, 20);
            productPanel.add(statusLabel);

            JLabel detailsLabel = new JLabel(record.getDetails());
            detailsLabel.setFont(comfortaaFont);
            detailsLabel.setBounds(200, 40, 600, 20);
            productPanel.add(detailsLabel);

            
           JButton editButton = new JButton("Edit");
            editButton.setBounds(870, 25, 120, 30);
            editButton.setFocusPainted(false); // Disable focus border
            
        if (comfortaaFont != null) {
            editButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            editButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
editButton.setBackground(new Color(251, 251, 251)); // Set the default background color
editButton.setForeground(new Color(12, 116, 137)); // Set the default text color
editButton.setFocusPainted(false); // Disable focus border

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showInputFrame(record);
                }
            });
            productPanel.add(editButton);

            JButton deleteButton = new JButton("Delete");
            deleteButton.setBounds(1000, 25, 120, 30);
            deleteButton.setFocusPainted(false); // Disable focus border
                    if (comfortaaFont != null) {
            deleteButton.setFont(comfortaaFont);
        } else {
            // Fallback to a default font if Comfortaa is not loaded
            deleteButton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        }
deleteButton.setBackground(selectedBackgroundColor); // Set the default background color
deleteButton.setForeground(selectedTextColor); // Set the default text color
deleteButton.setFocusPainted(false); // Disable focus border


      deleteButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JDialog dialog = new JDialog();
        
        // Set the background color
        dialog.getContentPane().setBackground(Color.gray);
        
        
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
        
        
    }
    productListPanel.revalidate();
    productListPanel.repaint();  
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                                     //filel name
            Dashboard ui = new Dashboard();
            ui.setVisible(true);
        });  
        
       
    }
}

// Separate class to store product records
class ProductRecord {
    private String productName;
    private String status;
    private String details;

    public ProductRecord(String productName, String status, String details) {
        this.productName = productName;
        this.status = status;
        this.details = details;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}










