package com.tinkerlad.forgeTool.gui;

import com.google.gson.JsonElement;
import com.tinkerlad.forgeTool.Start;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static com.tinkerlad.forgeTool.DataStorage.*;

/**
 * Created by Michael Brock on 12/18/2014.
 */
public class Main extends JFrame implements ActionListener {
    public JLabel Heading;
    public JProgressBar pbarBottom;
    public JLabel gitInformation;
    public JLabel lblGradle;
    public JLabel lblGit;
    public JLabel gradleInformation;
    public JLabel javaInformation;
    public JLabel lblJava;
    public JLabel lblForge;
    public JComboBox forgeVersionSelect;
    public JLabel lblIDE;
    public JPanel panelIDE;
    public JPanel panelForge;
    public JPanel panelJVM;
    public JPanel panelGradle;
    public JPanel panelGit;
    public JToolBar tbBottom;
    public JPanel panelMain;
    public JRadioButton radOther;
    public JRadioButton radEclipse;
    public JRadioButton radIDEA;
    public JPanel panelLocation;
    public JButton btnLocation;
    public JTextField txtLocation;
    public JTextField forgeBuild;
    public JPanel panelProject;
    public JLabel lblProject;
    public JTextField txtPackage;
    public JLabel lblPackage;
    public JLabel lblModID;
    public JTextField txtModID;
    public JLabel lblName;
    public JTextField txtName;
    public JLabel lblDesc;
    public JTextArea txtDesc;
    public JLabel lblAuthor;
    public JTextField txtAuthor;
    public JLabel lblCredits;
    public JTextArea txtCredits;
    public JButton reScanButton;
    public JButton createProjectButton;

    public Main() {
        setContentPane(panelMain);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);

        forgeVersionSelect.addActionListener(this);
        btnLocation.addActionListener(this);
        reScanButton.addActionListener(this);

        createProjectButton.addActionListener(this);

        txtCredits.setBorder(BorderFactory.createEtchedBorder());
        txtDesc.setBorder(BorderFactory.createEtchedBorder());

        PlainDocument forgeBuildDocument = new PlainDocument();
        forgeBuildDocument.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(off, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }

            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.replace(off, len, str.replaceAll("\\D++", ""), attr);  // remove non-digits
            }
        });
        forgeBuild.setDocument(forgeBuildDocument);

        PlainDocument modIDDocument = new PlainDocument();
        modIDDocument.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int off, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.insertString(off, str.toLowerCase(), attr);  // make lower case
            }

            @Override
            public void replace(FilterBypass fb, int off, int len, String str, AttributeSet attr)
                    throws BadLocationException {
                fb.replace(off, len, str.toLowerCase(), attr);  // make lower case
            }
        });
        txtModID.setDocument(modIDDocument);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
        this.setLocation(x, y);

        setVisible(true);
    }

    public static void errorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == forgeVersionSelect) {
            handleVersionSelectEvent(e);
        } else if (e.getSource() == btnLocation) {
            handleBtnLocationEvent(e);
        } else if (e.getSource() == reScanButton) {
            handleBtnReScanEvent(e);
        } else if (e.getSource() == createProjectButton) {
            if (!handleBtnCreateEvent(e)) {
                pbarBottom.setIndeterminate(false);
            }
        }
    }

    private void handleVersionSelectEvent(ActionEvent e) {
        if (forgeVersionSelect.getSelectedItem() == null) return;
        String version = forgeVersionSelect.getSelectedItem().toString();
        JsonElement forgeInfo = getForgeVersionForString(version);
        if (forgeInfo != null) {
            forgeBuild.setText(forgeInfo.toString());
        }
    }

    private void handleBtnLocationEvent(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fileChooser.showSaveDialog(this);

        if (returnVal == fileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            if (directory.isDirectory() && directory.listFiles().length == 0) {
                //safe as Directory is Empty
                txtLocation.setText(directory.getAbsolutePath());
                saveDirectory = directory;
            } else {
                errorMessage("Directory must be empty");
            }
        }
    }

    private void handleBtnReScanEvent(ActionEvent e) {
        pbarBottom.setIndeterminate(true);
        forgeVersionSelect.removeAllItems();
        forgeVersionSelect.addItem(" ");
        try {
            versionData = getVersionData();
            for (Map.Entry entry : versionData) {
                forgeVersionSelect.addItem(entry.getKey());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            errorMessage("Error retrieving Forge Version JSON, Check your internet connection.");
        }

        pbarBottom.setIndeterminate(false);

    }

    private boolean handleBtnCreateEvent(ActionEvent e) {
        //First Check that we have all the data we need

        Start.consoleGui = new Console();

        pbarBottom.setIndeterminate(true);

        if (forgeBuild.getText() == null || forgeBuild.getText().isEmpty()) {
            errorMessage("Please specify a forge build number");
            return false;
        }

        if (Integer.parseInt(forgeBuild.getText()) < 1 || Integer.parseInt(forgeBuild.getText()) > latestBuild) {
            errorMessage("Please specify a forge build number greater than 1 and less than " + latestBuild);
            return false;
        }

        if (saveDirectory == null) {
            errorMessage("Please specify a valid location to save your development files");
            return false;
        } else if (!saveDirectory.canWrite()) {
            errorMessage("Do not have write permissions for the selected location");
        }

        if (txtAuthor.getText() == null || txtAuthor.getText().isEmpty()) {
            errorMessage("Please specify the author of the mod");
            return false;
        }

        if (txtPackage.getText() == null || txtPackage.getText().isEmpty()) {
            errorMessage("Please specify the base package");
            return false;
        } else {
            if (!txtPackage.getText().matches("^[a-zA-Z_\\$][\\w\\$]*(?:\\.[a-zA-Z_\\$][\\w\\$]*)*$")) {
                errorMessage("Please enter a valid package complying with Java specifications");
                return false;
            }
        }

        if (txtModID.getText() == null || txtModID.getText().isEmpty()) {
            errorMessage("Please specify a Mod ID");
            return false;
        }

        if (txtName.getText() == null || txtName.getText().isEmpty()) {
            errorMessage("Please specify a Mod Name");
            return false;
        }

        //If we got this far then we have all the data we need :)
        pbarBottom.setValue(0);
        pbarBottom.setIndeterminate(false);

        selectedBuild = Integer.parseInt(forgeBuild.getText());
        author = txtAuthor.getText();
        modID = txtModID.getText();
        basePackage = txtPackage.getText();
        modName = txtName.getText();
        credits = txtCredits.getText();
        description = txtDesc.getText();
        if (radIDEA.isSelected()) {
            selectedIDE = IDEA;
        } else if (radEclipse.isSelected()) {
            selectedIDE = ECLIPSE;
        } else {
            selectedIDE = OTHER;
        }

        gradleInstalled = !gradleInformation.getText().matches("Gradle Not Installed!");

        return true;
    }

    public void setDebugParams() {
        txtAuthor.setText("Tinkerlad");
        txtModID.setText("tnktesting");
        txtCredits.setText("Testing the credits");
        txtDesc.setText("This is the Description");
        txtPackage.setText("com.tinkerlad.test");
        txtName.setText("Testing Mod");
    }
}
