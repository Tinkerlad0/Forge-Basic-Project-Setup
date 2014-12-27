package com.tinkerlad.forgeTool.files.mod.proxies;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class CommonProxyFile {

    public static final String modFile =
            "package #PACKAGE#.proxies;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class CommonProxy {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public void registerRenderers() {" + System.lineSeparator() +
                    "        // Nothing here as the server doesn't render graphics or tileentities!" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    "}" + System.lineSeparator();

        public static String getMainModFile(String modName, String basePackage, String modID){
                String data = modFile;
                data = data.replaceAll("#MODID#" , modID);
                data = data.replaceAll("#PACKAGE#", basePackage);
                data = data.replaceAll("#MODNAME#", modName);

                return data;
        }
}
