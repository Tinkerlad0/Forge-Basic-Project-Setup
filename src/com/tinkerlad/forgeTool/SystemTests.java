package com.tinkerlad.forgeTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michael Brock on 12/18/2014.
 */
public class SystemTests {

    public static String gradleVersion() {
        try {
            String output;

            Process process = Runtime.getRuntime().exec(Commands.GRADLE_VERSION);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((output = in.readLine()) != null) {
                if ((output.matches("Gradle.*"))) {
                    in.close();

                    String[] version;
                    version = output.split(" ");
                    return version[1];
                }
            }
            in.close();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String gradleJavaVersion() {
        try {
            String output;

            Process process = Runtime.getRuntime().exec(Commands.GRADLE_VERSION);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((output = in.readLine()) != null) {
                if ((output.matches("JVM.*"))) {
                    in.close();
                    return output.substring(14, 23);
                }
            }
            in.close();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String gitVersion() {
        try {
            String output;

            Process process = Runtime.getRuntime().exec(Commands.GIT_VERSION);

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

            if ((output = in.readLine()) != null) {
                String[] version;

                version = output.split(" ");
                return version[2];
            }
            in.close();
            return "";


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String javaVersion() {
        return System.getProperty("java.version");
    }

}
