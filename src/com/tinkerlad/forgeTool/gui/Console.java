package com.tinkerlad.forgeTool.gui;

import com.tinkerlad.forgeTool.utils.Progress;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Michael Brock on 12/20/2014.
 */
public class Console extends JFrame implements ActionListener {
    public JPanel panelMain;
    public JButton btnBegin;
    public JProgressBar pbBottom;
    public JTextArea txtConsole;
    public JLabel lblTask;

    private ConsoleWorker worker;

    public Console() {
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);

        txtConsole.setBorder(BorderFactory.createEtchedBorder());

        btnBegin.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBegin) {
            worker = new ConsoleWorker(this);

            worker.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if (evt.getPropertyName().matches("progress")) {
                        pbBottom.setIndeterminate(false);
                        pbBottom.setValue((Integer) evt.getNewValue());

                        lblTask.setText(Progress.getMessageForProgress((Integer) evt.getNewValue()));
                    }
                }
            });

            worker.execute();
        }
    }


    public void addLineToConsole(String line) {
        txtConsole.append(line + System.lineSeparator());
    }

}
