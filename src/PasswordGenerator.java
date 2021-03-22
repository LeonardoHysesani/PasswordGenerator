import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class PasswordGenerator extends JFrame {
    //JComponents
    private JButton createButton;
    private JCheckBox uppercaseCheckBox;
    private JCheckBox lowercaseCheckBox;
    private JCheckBox numCheckBox;
    private JCheckBox specialCharsCheckBox;
    private JPanel mainPanel;
    private JTextField lengthTextField;
    private JTextField passwordTextField;
    private JTextArea recentPasswordsTextArea;
    private JButton copyButton;

    //Variables
    int passwordLength = -1;
    int checkboxesChecked = 0;
    StringBuilder selectedChars = new StringBuilder();
    int historyLength = 5;
    String[] recentPasswords = new String[historyLength];

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Create custom password");
        mainFrame.setContentPane(new PasswordGenerator().mainPanel);
        mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setSize(480, 240);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
    }

    public PasswordGenerator() {
        passwordTextField.setEditable(false);
        recentPasswordsTextArea.setEditable(false);
        for (int i = 0; i < historyLength; i++) {
            recentPasswords[i] = "";
        }

        //Button management
        createButton.addActionListener(e -> {
            //making sure password length is a valid input
            passwordLength = Integer.parseInt(lengthTextField.getText());
            try {
                //making sure at least one checkbox is selected an that length field is not empty
                if (checkboxesChecked > 0) {
                    recentPasswordsTextArea.setText("");
                    for (int i = historyLength-1; i > 0; i--) {
                        recentPasswords[i] = recentPasswords[i-1];
                        recentPasswordsTextArea.insert((char) 0x2022 + " " + recentPasswords[i] + "\n", 0);
                    }
                    recentPasswords[0] = passwordTextField.getText();
                    recentPasswordsTextArea.insert((char) 0x2022 + " " + recentPasswords[0] + "\n", 0);
                    //adding this to the end and not the beginning allows to keep one more password as the current one does not need to be displayed
                    passwordTextField.setText(Generator.create(selectedChars.toString(), passwordLength).toString());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Select characters please.");
                    //debugging
                    System.out.println("No checkbox selected.");
                }
            }
            catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Enter a valid length please.");
                //debugging
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
                selectedChars.delete(numStartIndex, numStartIndex + 10);
                checkboxesChecked -= 1;
            }
        });

        //copy to clipboard
        copyButton.addActionListener(e->{
            String myString = passwordTextField.getText();
            StringSelection stringSelection = new StringSelection(myString);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            JOptionPane.showMessageDialog(null, "Password copied to clipboard.");
        });
    }
}
