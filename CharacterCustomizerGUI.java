import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CharacterCustomizerGUI extends JFrame {
    private final String username;
    private String selectedCharacter;
    private final JLabel previewLabel;
    private final JButton saveButton;

    private final String[] availableCharacters = {"Casual", "Knight", "Wizard", "Ninja", "Alien"};

    public CharacterCustomizerGUI(String username) {
        this.username = username;
        this.selectedCharacter = CharacterDataManager.loadCharacter(username);

        setTitle("Customize Your Character");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Character Selection Dropdown
        JComboBox<String> characterDropdown = new JComboBox<>(availableCharacters);
        characterDropdown.setSelectedItem(selectedCharacter);
        characterDropdown.addActionListener(e -> {
            selectedCharacter = (String) characterDropdown.getSelectedItem();
            updatePreview();
        });

        // Character Preview
        previewLabel = new JLabel("You selected: " + selectedCharacter, SwingConstants.CENTER);
        previewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        previewLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Save Button
        saveButton = new JButton("Save Character");
        saveButton.addActionListener(this::handleSave);

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(characterDropdown, BorderLayout.NORTH);
        centerPanel.add(previewLabel, BorderLayout.CENTER);
        centerPanel.add(saveButton, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void updatePreview() {
        previewLabel.setText("You selected: " + selectedCharacter);
    }

    private void handleSave(ActionEvent e) {
        CharacterDataManager.saveCharacter(username, selectedCharacter);
        JOptionPane.showMessageDialog(this, "Character saved successfully!", "Saved", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close after saving
    }
}
