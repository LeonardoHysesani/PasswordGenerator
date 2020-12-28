import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class PasswordGenerator extends JFrame {
    private JButton createButton;
    private JCheckBox UppercaseCheckBox;
    private JCheckBox lowercaseCheckBox;
    private JCheckBox numCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JTextField passwordTextField;

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Create your own password");
        mainFrame.setContentPane(new PasswordGenerator().mainPanel);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setSize(500, 200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
    }

    public PasswordGenerator() {
        StringBuilder selectedChars = new StringBuilder();
        AtomicBoolean charsHaveBeenSelected = new AtomicBoolean(false);
        //Button management
        createButton.addActionListener(e -> {
            //adding selected characters to the StringBuilder
            if (UppercaseCheckBox.isSelected()) {
                selectedChars.insert(0, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                charsHaveBeenSelected.set(true);
            }
            if (lowercaseCheckBox.isSelected()) {
                selectedChars.insert(0, "abcdefghijklmnopqrstuvwxyz");
                charsHaveBeenSelected.set(true);
            }
            if (numCheckBox.isSelected()) {
                selectedChars.insert(0, "1234567890");
                charsHaveBeenSelected.set(true);
            }
            if (specialCharsCheckBox.isSelected()) {
                selectedChars.insert(0, "!@#$%^&*()_+-={}|[]\\<>?/");
                charsHaveBeenSelected.set(true);
            }

            //length field cant be empty
            if (!lengthTextField.getText().equals("") && charsHaveBeenSelected.getAcquire()) {
                int passLength = Integer.parseInt(lengthTextField.getText());
                passwordTextField.setText(Generator.create(selectedChars.toString(), passLength).toString());
            }
            else {
                JOptionPane.showMessageDialog(null, "Give a password length and select characters please.");
            }
        });
    }
}
