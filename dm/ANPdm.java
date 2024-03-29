package dm;

import java.awt.BorderLayout;		
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.*;

public class ANPdm {

    public static Object textArea;

	public static void main(String[] args) {
        initComponents();
    }

    public static void initComponents() {
        JFrame frame = new JFrame("ANPdm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

     // Set frame to open at coordinates (300, 300) on the screen
        frame.setLocation(300, 100);

       
     // Add JTextArea in the NORTH panel with a specific size
        JTextArea textArea = new JTextArea("Type something...");
        
        JScrollPane scrollPaneForTextArea = new JScrollPane(textArea);
        scrollPaneForTextArea.setPreferredSize(new Dimension(1000, 200)); // Set preferred size of the JScrollPane
        frame.add(scrollPaneForTextArea, BorderLayout.NORTH);


     // Add buttons in the WEST panel with a specific width using ANPdmButton class
        JPanel westPanel = new JPanel(new GridLayout(5, 1)); // Adjusted for 5 rows as per the example
        westPanel.setPreferredSize(new Dimension(300, westPanel.getPreferredSize().height));
        String[] buttonNames = {"Save", "Clear", "Copy", "Exit", "SelectAll", "ClearAll"}; // Add more names as needed

        for (String name : buttonNames) {
            westPanel.add(ANPdmButton.createButton(name));
        }
        frame.add(westPanel, BorderLayout.WEST);

     // Configure the main table and set its preferred size in a JScrollPane
        DefaultTableModel tableModel = createTableModel();
        JTable table = new JTable(tableModel);

        // Add a TableModelListener to listen for checkbox changes
        table.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column == 1 || column == 2) { // Assuming columns 1 and 2 are checkboxes
                    Boolean isChecked = (Boolean) table.getModel().getValueAt(row, column);
                    ANPdmCheckbox.checkboxAction(textArea, row, column, isChecked);
                }
            }
        });

        configureTable(table);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 330)); // Adjusted size
        frame.add(scrollPane, BorderLayout.CENTER);


     // Add 6 buttons in the SOUTH panel with increased height
        JPanel southPanel = new JPanel(new GridLayout(1, 10)); // 1 row, 6 columns
        for (int i = 1; i <= 10; i++) {
            JButton button = new JButton("Button " + (i + 10));
            // Increase button height to 50 pixels
            button.setPreferredSize(new Dimension(button.getPreferredSize().height, 45));
            southPanel.add(button);
        }
        frame.add(southPanel, BorderLayout.SOUTH);



        // Adjust frame size to fit its content
        frame.pack(); // Adjusts frame to just fit its components
        frame.setVisible(true);
    }

    private static DefaultTableModel createTableModel() {
        String[] columnNames = {"Column 1", "[ + ]", "[ - ]", "Items"};
        String[] defaultItems = ANPdmReturnString.getDefaultStrings();
        
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, defaultItems.length) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 1 || columnIndex == 2 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                // Optionally make only certain columns editable if desired
                return column != 0; // Example: makes column 0 (Column 1) non-editable
            }
        };

        // Indent each item with 4 spaces before adding it to the table
        for (int i = 0; i < defaultItems.length; i++) {
            String indentedItem = "    " + defaultItems[i]; // Prepend 4 spaces for indentation
            tableModel.setValueAt(indentedItem, i, 3); // Populate the "Items" column with indented strings
        }
        return tableModel;
    }

    private static void configureTable(JTable table) {
        table.setRowHeight(30);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);
        columnModel.getColumn(1).setPreferredWidth(5);
        columnModel.getColumn(2).setPreferredWidth(5);
        columnModel.getColumn(3).setPreferredWidth(500);

        // Optional: Set a custom cell renderer for styling
        // Uncomment and implement if needed
        // table.setDefaultRenderer(Object.class, new MyTableCellRenderer());
    }

    // Optional: Custom cell renderer class
    /*
    static class MyTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            // Customize cell appearance here
            return this;
        }
    }
    */
}
