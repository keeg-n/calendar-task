import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel statusLabel;

    public LoginGUI() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Input panel
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        panel.add(loginButton);
        panel.add(signUpButton);

        add(panel, BorderLayout.CENTER);

        // Status message
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        // Button actions
        loginButton.addActionListener(e -> handleLogin());
        signUpButton.addActionListener(e -> handleSignUp());

        setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (UserManager.authenticate(username, password)) {
            statusLabel.setText("Login successful!");
            dispose(); // Close login window
            new TaskDashboardGUI(username); // Open dashboard
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (UserManager.register(username, password)) {
            statusLabel.setText("Account created! Please log in.");
        } else {
            statusLabel.setText("Username already exists.");
        }
    }
}
