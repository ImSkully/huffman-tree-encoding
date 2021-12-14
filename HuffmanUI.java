/**
 * Graphical Interface which allows for usage of the Huffman encoding and decoding functions.
 * 
 * @author Skully (https://github.com/ImSkully)
 * @email contact@skully.tech
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HuffmanUI extends JFrame implements ActionListener {
    // Initialize global variables.
    private JTextArea inputArea;
    private JButton actionButton;
    private JLabel headerLabel;
    private boolean currentlyEncoding = true; // Declares whether the user is currently encoding or not.
    private static TreeNode theTree;

    // Main interface builder function.
    private HuffmanUI() {
        super("Huffman Encoding");

        // Main interface panel.
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Encoding input text area.
        inputArea = new JTextArea(2, 10);
        inputArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputArea, BorderLayout.CENTER);

        // Encode button.
        actionButton = new JButton("Encode");
        actionButton.addActionListener(this);
        mainPanel.add(actionButton, BorderLayout.SOUTH);

        // Header label.
        headerLabel = new JLabel("Input text to encode:");
        headerLabel.setBorder(new EmptyBorder(10, 10, 10, 0));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Create a menu bar and set it to the interface.
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Create a JMenu with menu items, attached action listeners to each menu item.
        JMenu banksMenu = new JMenu("Functions");
        JMenuItem optionEncode = new JMenuItem("Encode");
        optionEncode.addActionListener(this);
        banksMenu.add(optionEncode);

        JMenuItem optionDecode = new JMenuItem("Decode");
        optionDecode.addActionListener(this);
        banksMenu.add(optionDecode);

        menuBar.add(banksMenu); // Add the menu to the menu bar.

        // Interface settings.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 300);
        setVisible(true);
    }

    // Action performer class to handle interface actions.
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == actionButton) {
            String textInput = inputArea.getText();
            textInput = textInput.toUpperCase(); // Get the text in the input area and capitalize.
            String result;
            if (currentlyEncoding) // If the user is in encoding mode.
            {
                if (!textInput.matches("[A-Z]+")) // Check using regex to see if the user has put anything other than
                                                  // capital letters A-Z.
                {
                    // If they have, display an error.
                    headerLabel.setText("ERROR: Only letters A-Z can be used!");
                    return; // Do not proceed any further.
                }
                // Input has been sanitized, encode the string.
                result = HuffmanTree.encodeString(textInput, theTree);
            } else {
                if (!textInput.matches("[0-1]+")) // Check using regex to see if the user has put anything other than
                                                  // the number 0-1.
                {
                    // If they have, display an error.
                    headerLabel.setText("ERROR: Only the numbers 0 and 1 can be used!");
                    return; // Do not proceed any further.
                }
                // Input has been sanitized, decode the string.
                result = HuffmanTree.decodeString(textInput, theTree);
            }

            inputArea.setText(result); // Set the result into the text area.
        }
        // If source is a menuItem, the user is switching modes.
        else if (e.getSource() instanceof JMenuItem) {
            // If Encode was clicked and the user is not already encoding.
            if (((JMenuItem) e.getSource()).getText().equals("Encode") && !currentlyEncoding) {
                // Adjust text on interface to now match encoding.
                actionButton.setText("Encode");
                headerLabel.setText("Input text to encode:");
                currentlyEncoding = !currentlyEncoding; // Flip the encoding variable.
            }
            // If Decode was clicked and the user is in encoding mode.
            else if (((JMenuItem) e.getSource()).getText().equals("Decode") && currentlyEncoding) {
                // Adjust text on interface to now match decoding.
                actionButton.setText("Decode");
                headerLabel.setText("Input text to decode:");
                currentlyEncoding = !currentlyEncoding; // Flip the encoding variable.
            }
        }
    }

    // Main method to invoke the interface.
    public static void main(String[] args) {
        centerWindow(new HuffmanUI()); // Invoke the interface and call centerWindow() to center the UI.

        QueueReferenceBased frequencyTable = HuffmanTree.getPopulatedList(); // Create and populate a new frequency
                                                                             // table.
        theTree = HuffmanTree.generateTree(frequencyTable); // Generate the binary tree.
    }

    /**
     * Function to set the specified frame's position to just slightly above center
     * screen.
     * 
     * @param frame The JFrame to center.
     */
    private static void centerWindow(Window frame) {
        int offsetY = 300; // Y axis offset to raise the interface slightly.
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); // Get user's screen dimensions.
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2); // Divide by 2 to get the center X.
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2); // Divide by 2 to get the center Y.

        // Set the frame location to the center coordinates and taking into account the
        // Y offset.
        frame.setLocation(x, y - offsetY);
    }
}