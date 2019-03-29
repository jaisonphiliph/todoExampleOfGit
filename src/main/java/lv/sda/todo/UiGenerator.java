package lv.sda.todo;


import javax.swing.*;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.glass.ui.Cursor.setVisible;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import static java.awt.TextField.*;

public class UiGenerator {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton save = new JButton("Save");


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
        final ArrayList<JLabel> t = new ArrayList<JLabel>();

        addTodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                final JLabel n = new JLabel(todoField.getText());
                t.add(n);


                n.setBackground(Color.YELLOW);
                n.setForeground(Color.GREEN);
                n.setFont(new Font("", Font.BOLD, 20));
                n.setFont(new Font("", Font.ITALIC, 20));


                final JCheckBox delete = new JCheckBox();     //checkbox along with the text....
                delete.setBackground(Color.BLACK);
                n.setPreferredSize(new Dimension(400, 25));

                delete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (delete.isSelected()) {
                            n.setText("<html><body><span style='text-decoration: line-through;'>" + n.getText() + "</span></body></html>");
                        } else {
                            n.setText(n.getText().replace("<html><body><span style='text-decoration: line-through;'>", "")
                                    .replace("</span></body></html>", ""));

                        }
                        t.remove(n);
                    }
                });
                final JLabel remove = new JLabel("[X]");


                remove.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        panel.remove(n);
                        panel.remove(delete);
                        panel.remove(remove);
                        frame.validate();
                        panel.validate();
                        panel.updateUI();
                        t.remove(n);
                    }

                    public void mousePressed(MouseEvent e) {

                    }

                    public void mouseReleased(MouseEvent e) {

                    }

                    public void mouseEntered(MouseEvent e) {

                    }

                    public void mouseExited(MouseEvent e) {

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
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                FileWriter writer = null;
                try {
                    writer = new FileWriter("output.txt");
                    for (JLabel v : t) {
                        writer.write(v.getText() + "\n");
                    }
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });


        panel.add(todoField);
        panel.add(addTodo);
        panel.add(clear);
        panel.add(save);

    }
        private void loadData(JLabel addTodo)
        {
            try
            {
                FileReader fr = new FileReader("output.txt");
                BufferedReader br = new BufferedReader(fr);

                br.readLine();
                while(br != null)

                {

                    addTodo.setText(br.toString());

                }
            }
            catch (IOException e){}
        }


    }


