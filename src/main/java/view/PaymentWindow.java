package view;

import use_case.checkout.CheckoutOutputData;
import entity.CartItemDisplay;
import entity.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentWindow extends JFrame {
    private final CheckoutOutputData outputData;
    private final User user;

    public PaymentWindow(CheckoutOutputData outputData, User user) {
        this.outputData = outputData;
        this.user = user;

        setTitle("Payment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        mainPanel.add(createOrderSummaryPanel(), BorderLayout.NORTH);
        mainPanel.add(createPaymentDetailsPanel(), BorderLayout.CENTER);
        mainPanel.add(createActionButtonsPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createOrderSummaryPanel() {
        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBorder(new TitledBorder("Order Summary"));

        String[] columnNames = {"Product", "Price", "Quantity", "Subtotal"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CartItemDisplay item : outputData.getCartItems()) {
            Object[] rowData = {
                    item.getProductName(),
                    String.format("$%.2f", item.getPrice()),
                    item.getQuantity(),
                    String.format("$%.2f", item.getSubtotal())
            };
            tableModel.addRow(rowData);
        }

        JTable orderTable = new JTable(tableModel);
        orderTable.setFillsViewportHeight(true);
        orderTable.setRowHeight(25);

        orderTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(80);
        orderTable.getColumnModel().getColumn(3).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(orderTable);
        summaryPanel.add(scrollPane, BorderLayout.CENTER);

        return summaryPanel;
    }

    private JPanel createPaymentDetailsPanel() {
        JPanel paymentPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        paymentPanel.setBorder(new TitledBorder("Payment Details"));

        // All calculations are already done in the use case,
        // Just need to display the pre-calculated values

        JLabel subtotalLabel = new JLabel(String.format("Subtotal: $%.2f", outputData.getSubtotal()));
        JLabel pointsLabel = new JLabel(String.format("Your Points: %d points", outputData.getUserPoints()));

        JLabel discountLabel = new JLabel(String.format("Points Discount: -$%.2f", outputData.getPointsDiscount()));
        discountLabel.setForeground(Color.PINK);

        JLabel totalLabel = new JLabel(String.format("Total After Discount: $%.2f", outputData.getTotalAfterDiscount()));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel balanceLabel = new JLabel(String.format("Current Balance: $%.2f", outputData.getUserBalance()));
        JLabel paymentLabel = new JLabel(String.format("Amount from Balance: $%.2f", outputData.getAmountFromBalance()));

        JLabel finalBalanceLabel = new JLabel();
        if (outputData.hasSufficientFunds()) {
            finalBalanceLabel.setText(String.format("Balance After Payment: $%.2f", outputData.getBalanceAfterPayment()));
            finalBalanceLabel.setForeground(Color.BLUE);
        } else {
            finalBalanceLabel.setText("Insufficient Funds - Balance will be negative!");
            finalBalanceLabel.setForeground(Color.RED);
            finalBalanceLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        }

        // Add components to panel
        paymentPanel.add(subtotalLabel);
        paymentPanel.add(pointsLabel);
        paymentPanel.add(discountLabel);
        paymentPanel.add(createSeparator());
        paymentPanel.add(totalLabel);
        paymentPanel.add(createSeparator());
        paymentPanel.add(balanceLabel);
        paymentPanel.add(paymentLabel);
        paymentPanel.add(finalBalanceLabel);

        return paymentPanel;
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GRAY);
        return separator;
    }

    private JPanel createActionButtonsPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Go back to order confirmation
                OrderConfirmationWindow orderWindow = new OrderConfirmationWindow(outputData, user);
                orderWindow.setVisible(true);
            }
        });

        JButton payButton = new JButton("Complete Payment");
        payButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        payButton.setBackground(new Color(34, 139, 34)); // Forest green
        payButton.setForeground(Color.WHITE);
        payButton.setFocusPainted(false);

        // Use pre-calculated value from outputData
        if (!outputData.hasSufficientFunds()) {
            payButton.setEnabled(false);
            payButton.setToolTipText("Insufficient funds to complete payment");
            payButton.setBackground(Color.GRAY);
        }

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });

        buttonPanel.add(cancelButton);
        buttonPanel.add(payButton);

        return buttonPanel;
    }

    // NEXT PR I WILL MOVE THIS
    // I JUST HAVEN'T DECIDED WHERE I WANT IT YET LOL
    private void processPayment() {
        if (outputData.hasSufficientFunds()) {
            // Process the payment using the pre-calculated values
            double amountPaid = outputData.getTotalAfterDiscount();
            int pointsUsed = (outputData.getUserPoints() / 1000) * 1000;

            // Update user balance and points
            user.removeBalance(amountPaid);
            user.removePointsBalance(pointsUsed);

            JOptionPane.showMessageDialog(this,
                    String.format("Payment successful!\n\n" +
                                    "Amount paid: $%.2f\n" +
                                    "Points used: %d\n" +
                                    "New balance: $%.2f\n" +
                                    "Remaining points: %d",
                            amountPaid, pointsUsed, user.getBalance(), user.getPointsBalance()),
                    "Payment Complete",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Insufficient funds to complete payment!",
                    "Payment Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}