package com.tinkerlad.forgeTool.files.mod.config;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ConfigFile {
    
    public static final String modFile = 
            "package #PACKAGE#.config;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "import net.minecraftforge.common.config.Configuration;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "import java.io.File;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class Config {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public static int testInt = 80;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    private static Configuration config;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public static void preInit(File configurationFile) {" + System.lineSeparator() +
                    "        config = new Configuration(configurationFile);" + System.lineSeparator() +
                    "        loadConfig();" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public static void saveConfig() {" + System.lineSeparator() +
                    "        if (config != null) {" + System.lineSeparator() +
                    "            config.save();" + System.lineSeparator() +
                    "        }" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    public static void loadConfig() {" + System.lineSeparator() +
                    "        if (config != null) {" + System.lineSeparator() +
                    "            config.load();" + System.lineSeparator() +
                    System.lineSeparator() +
                    "            testInt = config.getInt(\"TestInt 1\", \"Category\", 80, 0, 5000, \"Comment\");" + System.lineSeparator() +
                    System.lineSeparator() +
                    "            saveConfig();" + System.lineSeparator() +
                    "        }" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    System.lineSeparator() +
                    "}\n";


    public static String getMainModFile(String modName, String basePackage, String modID){
        String data = modFile;
        data = data.replaceAll("#MODID#" , modID);
        data = data.replaceAll("#PACKAGE#", basePackage);
        data = data.replaceAll("#MODNAME#", modName);

        return data;
    }
}
