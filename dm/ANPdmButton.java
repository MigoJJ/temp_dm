package dm;

import java.awt.Component;	
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class ANPdmButton {

    /**
     * Creates and returns a JButton with the specified name. The button is configured
     * with an action listener that performs actions based on the button name.
     *
     * @param name     The text of the button.
     * @param textArea The JTextArea associated with some button actions.
     * @param table    The JTable whose model is involved in some button actions.
     * @return The configured JButton.
     */

    /**
     * Performs an action based on the button name. This method routes the action
     * to specific methods for handling.
     *
     * @param buttonName The name of the button which determines the action to perform.
     * @param textArea   The JTextArea to be manipulated by certain actions.
     * @param model      The TableModel of the JTable to be manipulated.
     */
	public static Component createButton(String name, JTextArea textArea, JTable table) {
        JButton button = new JButton(name);
        // Attach an action listener that performs actions based on the button name.
        button.addActionListener(e -> performAction(name, textArea, table.getModel(), ANPdm.frame));
        return button;
    }
	
	private static void performAction(String buttonName, JTextArea textArea, TableModel model, JFrame frame) {
        switch (buttonName) {
            case "Save":
                saveAction();
                break;
            case "Clear":
                clearAction(textArea, model);
                break;
            case "Copy":
                copyAction(textArea);
                break;
            case "Exit":
                exitAction(textArea, model, frame);
                break;
            case "SelectAll":
                selectAllAction(model);
                break;
            case "ClearAll":
                clearAllAction(model);
                break;
            default:
                System.out.println("Unknown button clicked: " + buttonName);
        }
    }

    // Implementation of the save action.
    private static void saveAction() {
        System.out.println("Save action performed");
    }

    // Clears the JTextArea and unchecks all checkboxes in the table model.
    private static void clearAction(JTextArea textArea, TableModel model) {
        textArea.setText("\n    < Autonomic Neuroapthy >\n");
        updateCheckboxes(model, false); // Uncheck all checkboxes
    }

 // Implementation of the copy action.
    private static void copyAction(JTextArea textArea) {
        System.out.println("Copy action performed");
        String text = textArea.getText(); // Get the text from the JTextArea
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null); // Set the text to the system clipboard
    }

    // Exits the application by disposing of the frame.
    private static void exitAction(JTextArea textArea, TableModel model, JFrame frame) {
        clearAction(textArea, model); // Optionally clear data before exiting
        System.exit(0);
    }

    // Implementation of the select all action.
    private static void selectAllAction(TableModel model) {
    	    updateCheckboxesall(model, true, 1); // Check all checkboxes in column 1
    	}


    // Implementation of the clear all action.
    private static void clearAllAction(TableModel model) {
        updateCheckboxes(model, false); // Uncheck all checkboxes
    }

    /**
     * Updates all checkboxes in a given table model to the specified state.
     *
     * @param model The TableModel containing the checkboxes to update.
     * @param state The desired state of the checkboxes (true for checked, false for unchecked).
     */
    private static void updateCheckboxes(TableModel model, boolean state) {
        if (model != null) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    if (model.getColumnClass(j) == Boolean.class) {
                        model.setValueAt(state, i, j);
                    }
                }
            }
        }
    }
 // Updates checkboxes in a given column to the specified state.
    private static void updateCheckboxesall(TableModel model, boolean state, int column) {
        if (model != null) {
            for (int i = 0; i < model.getRowCount(); i++) {
                if (model.getColumnClass(column) == Boolean.class) {
                    model.setValueAt(state, i, column);
                }
            }
        }
    }


}
