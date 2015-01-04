package com.tinkerlad.forgeTool.files.mod.java.proxies;

import com.tinkerlad.forgeTool.files.mod.ModFile;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class CommonProxyFile extends ModFile {

    public static final String modFile =
            "package #PACKAGE#.proxies;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class CommonProxy {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public void registerRenderers() {" + System.lineSeparator() +
                    "        // Nothing here as the server doesn't render graphics or tileentities!" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    "}" + System.lineSeparator();

}
