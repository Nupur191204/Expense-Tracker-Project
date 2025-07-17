import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Expense {
    String category;
    double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String toString() {
        return category + ": ₹" + amount;
    }
}

public class ExpenseTrackerApp {
    private static ArrayList<Expense> expenses = new ArrayList<>();
    private static JTextArea outputArea;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Expense Tracker");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        JLabel catLabel = new JLabel("Category:");
        JTextField catField = new JTextField(20);

        
        JLabel amtLabel = new JLabel("Amount:");
        JTextField amtField = new JTextField(10);

        
        JButton addButton = new JButton("Add Expense");
        JButton viewButton = new JButton("View All");
        JButton totalButton = new JButton("Total");

        
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(catLabel);
        inputPanel.add(catField);
        inputPanel.add(amtLabel);
        inputPanel.add(amtField);
        inputPanel.add(addButton);
        inputPanel.add(viewButton);

        
        JPanel outputPanel = new JPanel();
        outputPanel.add(new JScrollPane(outputArea));
        outputPanel.add(totalButton);

       
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);

        
        addButton.addActionListener(e -> {
            String category = catField.getText();
            String amountStr = amtField.getText();

            try {
                double amount = Double.parseDouble(amountStr);
                expenses.add(new Expense(category, amount));
                outputArea.append("Added: " + category + " ₹" + amount + "\n");
                catField.setText("");
                amtField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number for amount.");
            }
        });

        
        viewButton.addActionListener(e -> {
            outputArea.setText("All Expenses:\n");
            for (Expense ex : expenses) {
                outputArea.append(ex.toString() + "\n");
            }
        });

        
        totalButton.addActionListener(e -> {
            double total = 0;
            for (Expense ex : expenses) {
                total += ex.amount;
            }
            outputArea.append("Total Spent: ₹" + total + "\n");
        });

        frame.setVisible(true);
    }
}