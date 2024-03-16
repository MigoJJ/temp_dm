package dm;

import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class ANPdmCheckbox {

    public static void checkboxAction(JTextArea textArea, DefaultTableModel model, int row, int column, boolean isChecked) {
        // Append the message to the JTextArea about the checkbox status
        textArea.append("Checkbox at row " + row + ", column " + column + " is now " + (isChecked ? "checked" : "unchecked") + ".\n");
        
        // If the checkbox in column 1 is clicked, append a value from column 4 (index 3) of the same row to the JTextArea
        if (column == 1) { // Checks if the clicked column is 1
            // Corrected to access column 3 (fourth column) instead of 4
            Object columnValue = model.getValueAt(row, 3); // Gets the value from column 4 (index 3)
            textArea.append("Value in row " + row + ", column 4: " + columnValue + "\n");
        }
    }
}
