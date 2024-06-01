import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BankSystemSwing extends JFrame implements ActionListener {
    private Map<Integer, Double> accounts = new HashMap<>();
    private JTextField accountField, amountField;
    private JLabel resultLabel;

    public BankSystemSwing() {
    
        accounts.put(123456, 1000.0);
        accounts.put(234567, 1500.0);
        accounts.put(345678, 2000.0);
        accounts.put(456789, 2500.0);
        accounts.put(567890, 3000.0);

        setTitle("Banking System");
        setSize(800, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        // Creating components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JLabel accountLabel = new JLabel("Enter Account Number:");
        accountField = new JTextField();
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(this);
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        JButton withdrawalButton = new JButton("Withdrawal");
        withdrawalButton.addActionListener(this);
        resultLabel = new JLabel("");

        // Adding components to the panel
        panel.add(accountLabel);
        panel.add(accountField);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawalButton);
        panel.add(resultLabel);

        // Adding panel to the frame
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check Balance")) {
            int accountNumber = Integer.parseInt(accountField.getText());
            if (accounts.containsKey(accountNumber)) {
                double balance = accounts.get(accountNumber);
                resultLabel.setText("Available Balance: $" + balance);
            } else {
                resultLabel.setText("Account not found!");
            }
        } else if (e.getActionCommand().equals("Deposit")) {
            int accountNumber = Integer.parseInt(accountField.getText());
            if (accounts.containsKey(accountNumber)) {
                double amount = Double.parseDouble(amountField.getText());
                double balance = accounts.get(accountNumber);
                balance += amount;
                accounts.put(accountNumber, balance);
                resultLabel.setText("Deposit successful! Updated balance: $" + balance);
            } else {
                resultLabel.setText("Account not found!");
            }
        } else if (e.getActionCommand().equals("Withdrawal")) {
            int accountNumber = Integer.parseInt(accountField.getText());
            if (accounts.containsKey(accountNumber)) {
                double amount = Double.parseDouble(amountField.getText());
                double balance = accounts.get(accountNumber);
                if (balance >= amount) {
                    balance -= amount;
                    accounts.put(accountNumber, balance);
                    resultLabel.setText("Transaction successful! Updated balance: $" + balance);
                } else {
                    resultLabel.setText("Insufficient balance!");
                }
            } else {
                resultLabel.setText("Account not found!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BankSystemSwing::new);
    }
}
