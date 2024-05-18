package org.kyrylo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Smartphone Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(1, 2));

        //create smartphones
        Smartphone smartphone1 = new Smartphone("Galaxy S23", "Samsung", 799.99);
        Smartphone smartphone2 = new Smartphone("iPhone 15", "Apple", 999.99);

        //create shopping cart
        ShoppingCart cart = new ShoppingCart();

        //left panel - Smartphone list
        JPanel leftPanel = new JPanel(new BorderLayout());
        DefaultListModel<String> smartphoneListModel = new DefaultListModel<>();
        smartphoneListModel.addElement(smartphone1.getModel() + " (" + smartphone1.getBrand() + ") - " + smartphone1.getPrice());
        smartphoneListModel.addElement(smartphone2.getModel() + " (" + smartphone2.getBrand() + ") - " + smartphone2.getPrice());
        JList<String> smartphoneList = new JList<>(smartphoneListModel);
        leftPanel.add(new JScrollPane(smartphoneList), BorderLayout.CENTER);

        //create "add to cart" interface
        JPanel leftBottomPanel = new JPanel();
        JTextField quantityField = new JTextField(3);
        JButton addButton = new JButton("Add to Cart");
        leftBottomPanel.add(new JLabel("Quantity:"));
        leftBottomPanel.add(quantityField);
        leftBottomPanel.add(addButton);
        leftPanel.add(leftBottomPanel, BorderLayout.SOUTH);

        //right panel - Shopping cart
        JPanel rightPanel = new JPanel(new BorderLayout());
        DefaultListModel<String> cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        rightPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);

        JButton orderButton = new JButton("Place Order");
        rightPanel.add(orderButton, BorderLayout.SOUTH);

        //add action listener for addButton
        addButton.addActionListener(e -> {
            int selectedIndex = smartphoneList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedSmartphone = smartphoneListModel.get(selectedIndex);
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "Quantity must be greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Smartphone smartphone = selectedIndex == 0 ? smartphone1 : smartphone2;
                cart.add(smartphone, quantity);

                cartListModel.clear();
                for (Map.Entry<Smartphone, Integer> entry : cart.getItems().entrySet()) {
                    cartListModel.addElement(entry.getKey().getModel() + " (" + entry.getKey().getBrand() + ") - " + entry.getValue());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a smartphone", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //add action listener for orderButton
        orderButton.addActionListener((ActionEvent e) -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                cart.getItems().clear();
                cartListModel.clear();
            }
        });

        // Add panels to frame
        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }
}
