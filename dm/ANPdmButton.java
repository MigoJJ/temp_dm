package dm;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ANPdmButton {

    public static JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ANPdmBE_West(name); // Call the method with the button's name
            }
        });
        return button;
    }

    public static void ANPdmBE_West(String buttonName) {
        // Example functionality: print the button name
        System.out.println(buttonName + " button clicked");
        // You can add different functionalities here based on the buttonName
    }
}
