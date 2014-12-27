package com.tinkerlad.forgeTool.files.mod.item;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ModItemsFile {

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

    public static String getMainModFile(String modName, String basePackage, String modID) {
        String data = modFile;
        data = data.replaceAll("#MODID#", modID);
        data = data.replaceAll("#PACKAGE#", basePackage);
        data = data.replaceAll("#MODNAME#", modName);

        return data;
    }
}
