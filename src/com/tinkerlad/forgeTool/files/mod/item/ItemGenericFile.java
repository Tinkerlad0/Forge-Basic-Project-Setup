package com.tinkerlad.forgeTool.files.mod.item;

/**
 * Created by Michael Brock on 12/24/2014.
 */
public class ItemGenericFile {

    public static final String modFile =
            "package #PACKAGE#.item;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "import #PACKAGE#.#MODID#;" + System.lineSeparator() +
                    "import net.minecraft.item.Item;" + System.lineSeparator() +
                    System.lineSeparator() +
                    "public class ItemGeneric extends Item {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    protected ItemGeneric() {" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    @Override" + System.lineSeparator() +
                    "    public Item setUnlocalizedName(String name) {" + System.lineSeparator() +
                    "        return super.setUnlocalizedName(#MODID#.MODID + \":\" + name);" + System.lineSeparator() +
                    "    }" + System.lineSeparator() +
                    System.lineSeparator() +
                    "    @Override" + System.lineSeparator() +
                    "    public Item setTextureName(String texture) {" + System.lineSeparator() +
                    "        return super.setTextureName(#MODID#.MODID + \":\" + texture);" + System.lineSeparator() +
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
