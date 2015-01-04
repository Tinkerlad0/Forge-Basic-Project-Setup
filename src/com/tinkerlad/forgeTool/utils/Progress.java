package com.tinkerlad.forgeTool.utils;

/**
 * Created by Michael Brock on 12/21/2014.
 */
public class Progress {
    public static final String One = "Creating Basic Directories";
    public static final String Two = "Downloading Forge src Package";
    public static final String Three = "Unpacking Forge src Package";
    public static final String Four = "Creating Basic Gradle Setup";
    public static final String Five = "Setting up Decompiled Workspace";
    public static final String Six = "Removing examplemod";
    public static final String Seven = "Creating Base Mod";
    public static final String Eight = "Generating mcmod.info";
    public static final String Nine = "Creating files for IDE";
    public static final String Default = "Setting up basic Forge Project";
    public static final String Done = "Finished";
    public static final String Error = "Error";

    public static String getMessageForProgress(Integer progressNo) {
        if (progressNo == null) return Default;
        switch (progressNo) {
            case 1:
                return One;
            case 2:
                return Two;
            case 3:
                return Three;
            case 4:
                return Four;
            case 5:
                return Five;
            case 6:
                return Six;
            case 7:
                return Seven;
            case 8:
                return Eight;
            case 9:
                return Nine;
            case 10:
                return Done;
            case -1:
                return Error;
            default:
                return Default;
        }
    }
}
