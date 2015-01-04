package com.tinkerlad.forgeTool.files.mod;

public class ModFile {
	public static String modFile = "";

	public static String getMainModFile(String modName, String basePackage, String modID) {
		String data = modFile;
		data = data.replaceAll("#MODID#", modID);
		data = data.replaceAll("#PACKAGE#", basePackage);
		data = data.replaceAll("#MODNAME#", modName);

		return data;
	}
}
