package lv.sda.todo;


import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.Date;

import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import static java.awt.TextField.*;

public class UiGenerator {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    public void draw() {
        //panel.setBounds(0,0,200,200);
        panel.setLayout(new FlowLayout());
        frame.setBounds(0, 0, 600, 600);
        addTodoPanel();
        frame.add(panel);
        frame.setVisible(true);
        //frame.setLayout(new FlowLayout());

    }

    public void addTodoPanel() {
        final JTextField todoField = new JTextField(10);

        final JButton addTodo = new JButton("Add");
        final JButton clear = new JButton("clear");
        panel.setBackground(Color.RED);

        addTodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JLabel n = new JLabel(todoField.getText());


                n.setForeground(Color.GREEN);
                n.setFont(new Font("", Font.BOLD, 20));
                n.setFont(new Font("", Font.ITALIC, 20));




                final JCheckBox delete = new JCheckBox();     //checkbox along with the text....
                delete.setBackground(Color.BLACK);
                n.setPreferredSize(new Dimension(400, 25));

                final JCheckBox remove =new JCheckBox();
                
                remove.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        panel.remove(n);
                        panel.remove(delete);
                        panel.remove(remove);
                        frame.validate();
                        panel.validate();
                        panel.updateUI();
                    }
                });




                panel.add(n);
                panel.add(remove);
                panel.add(delete);
                todoField.setText("");

                frame.validate();
                clear.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (delete.isSelected()) {
                            panel.remove(n);
                            panel.remove(delete);
                            panel.remove(remove);
                            frame.validate();
                            panel.updateUI();

                           // System.out.println("ggdgd");
                        }

                    }
                });

            }

        });


        panel.add(todoField);
        panel.add(addTodo);
        panel.add(clear);



    }

}
