import javax.swing.*;

public class PasswordGenerator extends JFrame {
    private JButton createButton;
    private JCheckBox uppercaseCheckBox;
    private JCheckBox lowercaseCheckBox;
    private JCheckBox numCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JTextField passwordTextField;

    int passwordLength = -1;
    int checkboxesChecked = 0;
    StringBuilder selectedChars = new StringBuilder();

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
        //Button management
        createButton.addActionListener(e -> {
            //making sure password length is a valid input
            try {
                passwordLength = Integer.parseInt(lengthTextField.getText());
                //making sure at least one checkbox is selected an that length field is not empty
                if (checkboxesChecked > 0) {
                    passwordTextField.setText(Generator.create(selectedChars.toString(), passwordLength).toString());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Select characters please.");
                }
            }
            catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Please enter a valid legth.");
                //debugging
                System.out.println(passwordLength);
                System.out.println("Length field contains character(s) or is empty.");
            }
        });

        //adding selected characters to the StringBuilder and removing unselected ones
        uppercaseCheckBox.addActionListener(e -> {
            if (uppercaseCheckBox.isSelected()) {
                selectedChars.insert(0, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                checkboxesChecked += 1;
            }
            else if (selectedChars.toString().contains("A")) {
                int uppercaseStartIndex = selectedChars.toString().indexOf("A");
                selectedChars.delete(uppercaseStartIndex, uppercaseStartIndex + 26);
                checkboxesChecked -= 1;
            }
        });
        lowercaseCheckBox.addActionListener(e -> {
            if (lowercaseCheckBox.isSelected()) {
                selectedChars.insert(0, "abcdefghijklmnopqrstuvwxyz");
                checkboxesChecked += 1;
            }
            else if (selectedChars.toString().contains("a")) {
                int lowercaseStartIndex = selectedChars.toString().indexOf("a");
                selectedChars.delete(lowercaseStartIndex, lowercaseStartIndex + 26);
                checkboxesChecked -= 1;
            }
        });
        specialCharsCheckBox.addActionListener(e -> {
            if (specialCharsCheckBox.isSelected()) {
                selectedChars.insert(0, "!@#$%^&*()_+-={}|[]\\<>?/");
                checkboxesChecked += 1;
            }
            else if (selectedChars.toString().contains("!")) {
                int uppercaseStartIndex = selectedChars.toString().indexOf("!");
                selectedChars.delete(uppercaseStartIndex, uppercaseStartIndex + 24);
                checkboxesChecked -= 1;
            }
        });
        numCheckBox.addActionListener(e -> {
            if (numCheckBox.isSelected()) {
                selectedChars.insert(0, "1234567890");
                checkboxesChecked += 1;
            }
            else if (selectedChars.toString().contains("1")) {
                int numStartIndex = selectedChars.toString().indexOf("1");
                selectedChars.delete(numStartIndex, numStartIndex + 9);
                checkboxesChecked -= 1;
            }
        });
    }
}
