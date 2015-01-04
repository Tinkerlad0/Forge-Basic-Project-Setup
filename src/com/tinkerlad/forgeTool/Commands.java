package com.tinkerlad.forgeTool;

/**
 * Created by Michael Brock on 12/20/2014.
 */
public class Commands {

    public static final String GRADLE_VERSION = "cmd.exe /c gradle -v";
    public static final String GIT_VERSION = "cmd.exe /c git --version";
    public static final String FORGE_DECOMPILE = "cmd.exe /c gradle setupDecompWorkspace";
    public static final String IDEA_RUNS = "cmd.exe /c gradle idea";
    public static final String ECLIPSE_RUNS = "cmd.exe /c gradle eclipse";
}
