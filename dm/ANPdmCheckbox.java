package dm;

import javax.swing.JTextArea;

public class ANPdmCheckbox {

    public static void checkboxAction(JTextArea textArea, int row, int column, boolean isChecked) {
        // Append the message to the JTextArea
        textArea.append("Checkbox at row " + row + ", column " + column + " is now " + (isChecked ? "checked" : "unchecked") + "\n");
    }
}
