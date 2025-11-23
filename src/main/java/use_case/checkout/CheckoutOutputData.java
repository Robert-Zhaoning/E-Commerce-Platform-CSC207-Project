package use_case.checkout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.util.Map;

import entity.*;


/**
 * Order Confirmation Window that displays user details, billing address,
 * cart items, and total price
 */
public class CheckoutOutputData extends JFrame {
    private final String username;
    private final String email;
    private final String billingAddress;
    private final List<entity.CartItemDisplay> cartItems;
    private final double totalPrice;
    private final int totalItems;

    public CheckoutOutputData(String username, String email, String billingAddress,
                              List<entity.CartItemDisplay> cartItems, double totalPrice, int totalItems) {
        this.username = username;
        this.email = email;
        this.billingAddress = billingAddress;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getBillingAddress() { return billingAddress; }
    public List<entity.CartItemDisplay> getCartItems() { return cartItems; }
    public double getTotalPrice() { return totalPrice; }
    public int getTotalItems() { return totalItems; }

    // Add this main method to your CheckoutOutputData class
//    public static void main(String[] args) {
//        // Create sample cart items
//        List<CartItemDisplay> cartItems = Arrays.asList(
//                new CartItemDisplay("Wireless Bluetooth Headphones", 79.99, 1, 79.99),
//                new CartItemDisplay("USB-C Charging Cable", 19.99, 2, 39.98),
//                new CartItemDisplay("Phone Case - Black", 14.99, 1, 14.99),
//                new CartItemDisplay("Screen Protector", 9.99, 3, 29.97),
//                new CartItemDisplay("Laptop Stand", 49.99, 1, 49.99)
//        );
//
//        double totalPrice = 79.99 + 39.98 + 14.99 + 29.97 + 49.99; // 214.92
//        int totalItems = 1 + 2 + 1 + 3 + 1; // 8 items
//
//        // Create and display the window
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            CheckoutOutputData window = new CheckoutOutputData(
//                    "john_doe123",
//                    "john.doe@example.com",
//                    "123 Main Street, Apt 4B\nNew York, NY 10001\nUnited States",
//                    cartItems,
//                    totalPrice,
//                    totalItems
//            );
//            window.setVisible(true);
//        });
//    }
}

