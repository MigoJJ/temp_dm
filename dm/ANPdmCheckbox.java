package dm;

import javax.swing.JTextArea;    
import javax.swing.table.DefaultTableModel;

public class ANPdmCheckbox {

    /**
     * Toggles the checkbox action and updates the JTextArea accordingly.
     * 
     * @param textArea The JTextArea to update.
     * @param model The table model to retrieve data from.
     * @param row The row of the toggled checkbox.
     * @param column The column of the toggled checkbox.
     * @param isChecked The new state of the checkbox.
     */
    public static void checkboxAction(JTextArea textArea, DefaultTableModel model, int row, int column, boolean isChecked) {
        if (isChecked) {
            // If checkbox is checked, append the corresponding message to JTextArea
            appendCheckboxStatus(textArea, model, row, column);
        } else {
            // If checkbox is unchecked, remove the specific message from JTextArea
            removeCheckboxStatus(textArea, model, row, column);
        }
    }
    
    /**
     * Appends the status of the checkbox to the JTextArea.
     * 
     * @param textArea The JTextArea to update.
     * @param model The table model to retrieve data from.
     * @param row The row of the toggled checkbox.
     * @param column The column of the toggled checkbox.
     */
    private static void appendCheckboxStatus(JTextArea textArea, DefaultTableModel model, int row, int column) {
        if (column == 1 || column == 2) {
            Object columnValue = model.getValueAt(row, 3); // Value from column 4
            String status = (column == 1) ? "    [ V ]  " : "    [ - ]  ";
            textArea.append(status + columnValue + "\n");
        }
    }
    
    /**
     * Removes a specific line based on column 4 value and checkbox column from JTextArea.
     * 
     * @param textArea The JTextArea to update.
     * @param model The table model to retrieve data from.
     * @param row The row of the toggled checkbox.
     * @param column The column of the toggled checkbox.
     */
    private static void removeCheckboxStatus(JTextArea textArea, DefaultTableModel model, int row, int column) {
        Object columnValue = model.getValueAt(row, 3); // Get the value from column 4
        String statusPrefix = (column == 1) ? "[ V ]  " : "[ - ]  ";
        String targetLine = statusPrefix + columnValue; // The line to be removed
        
        String[] lines = textArea.getText().split("\n");
        StringBuilder newText = new StringBuilder();
        boolean found = false; // Flag to indicate if the target line was found
        
        for (String line : lines) {
            if (!found && line.trim().equals(targetLine.trim())) {
                found = true; // Skip the first occurrence of the target line
            } else {
                newText.append(line).append("\n"); // Append all other lines
            }
        }
        
        textArea.setText(newText.toString().trim()); // Update JTextArea, maintain formatting
    }
}
