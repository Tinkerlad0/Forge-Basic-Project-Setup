package com.tinkerlad.forgeTool.files.mod.java.item;

import com.tinkerlad.forgeTool.files.mod.ModFile;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ModItemsFile  extends ModFile {

    public static final String modFile =
            "package #PACKAGE#.item;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "import cpw.mods.fml.common.registry.GameRegistry;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class ModItems {" + System.lineSeparator() +
                    System.lineSeparator() +
                    System.lineSeparator() +
                    "    public static void init() {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "        //TODO: Init all Items here" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    "}" + System.lineSeparator();

}
