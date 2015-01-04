package com.tinkerlad.forgeTool.files.mod.java.proxies;

import com.tinkerlad.forgeTool.files.mod.ModFile;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ClientProxyFile extends ModFile {

    public static final String modFile =
            "package #PACKAGE#.proxies;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class ClientProxy extends CommonProxy {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    @Override" + System.lineSeparator() +
                    "    public void registerRenderers() {" + System.lineSeparator() +
                    System.lineSeparator() +
                    System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    "}";

}
