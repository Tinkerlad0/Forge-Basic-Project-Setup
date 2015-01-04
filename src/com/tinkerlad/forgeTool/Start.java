package com.tinkerlad.forgeTool;

import com.tinkerlad.forgeTool.gui.Console;
import com.tinkerlad.forgeTool.gui.Main;
import com.tinkerlad.forgeTool.utils.DateTimeUtils;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by Michael Brock on 12/18/2014.
 */
public class Start {

    public static String versionURL = "http://files.minecraftforge.net/maven/net/minecraftforge/forge/json";
    public static Main gui;
    public static Console consoleGui;
    public static DateTimeUtils timeUtils;

    public static void main(String[] args) {

        timeUtils = new DateTimeUtils();

        gui = new Main();

        if (args != null){
            for (String str : args){
                if (str.matches("debug")){
                    gui.setDebugParams();
                }
            }
        }

        String javaVersion, gitVersion, gradleVersion, gradleJavaVersion;

        gradleVersion = SystemTests.gradleVersion();
        if (gradleVersion == "") gradleVersion = "Gradle Not Installed!";
        gui.gradleInformation.setText(gradleVersion);
        gui.pbarBottom.setValue(1);

        gradleJavaVersion = SystemTests.gradleJavaVersion();
        if (gradleVersion == "" || gradleVersion == "Gradle Not Installed!") gradleJavaVersion = "";
        gui.gradleInformation.setText(gradleVersion + " running on JVM: " + gradleJavaVersion);
        gui.pbarBottom.setValue(2);

        javaVersion = SystemTests.javaVersion();
        gui.javaInformation.setText(javaVersion);
        gui.pbarBottom.setValue(3);

        gitVersion = SystemTests.gitVersion();
        gui.gitInformation.setText(gitVersion);
        gui.pbarBottom.setValue(4);


        gui.forgeVersionSelect.addItem(" ");
        try {
            DataStorage.getVersionData();
            for (Map.Entry entry : DataStorage.versionData) {
                gui.forgeVersionSelect.addItem(entry.getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving Forge Version JSON, Check your internet connection.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        gui.pbarBottom.setValue(5);

        try {
            DataStorage.getSrcURLFromBuild(1272);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
