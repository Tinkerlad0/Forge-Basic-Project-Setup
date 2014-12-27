package com.tinkerlad.forgeTool.files.mod.block;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ModBlocksFile {
    public static final String modFile =
            "package #PACKAGE#.block;" + System.lineSeparator() +
                    "" + System.lineSeparator() +
                    "public class ModBlocks {" + System.lineSeparator() +
                    "" + System.lineSeparator() +
                    "    public static void init() {" + System.lineSeparator() +
                    "" + System.lineSeparator() +
                    "        //TODO: Register all blocks here" + System.lineSeparator() +
                    "" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    "" + System.lineSeparator() +
                    "}" + System.lineSeparator();


        public static String getMainModFile(String modName, String basePackage, String modID){
                String data = modFile;
                data = data.replaceAll("#MODID#" , modID);
                data = data.replaceAll("#PACKAGE#", basePackage);
                data = data.replaceAll("#MODNAME#", modName);

                return data;
        }
    

}
