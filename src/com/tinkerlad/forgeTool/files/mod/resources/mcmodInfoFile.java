package com.tinkerlad.forgeTool.files.mod.resources;

import com.tinkerlad.forgeTool.files.mod.ModFile;

public class mcmodInfoFile extends ModFile {

	public static final String modFile =
			"[" + System.lineSeparator() +
					"{" + System.lineSeparator() +
					"  \"modid\": \"#MODID#\"," + System.lineSeparator() +
					"  \"name\": \"#MODNAME#\"," + System.lineSeparator() +
					"  \"description\": \"#DESCRIPTION#\"," + System.lineSeparator() +
					"  \"version\": \"${version}\"," + System.lineSeparator() +
					"  \"mcversion\": \"${mcversion}\"," + System.lineSeparator() +
					"  \"url\": \"\"," + System.lineSeparator() +
					"  \"updateUrl\": \"\"," + System.lineSeparator() +
					"  \"authorList\": [\"#AUTHOR#\"]," + System.lineSeparator() +
					"  \"credits\": \"#CREDITS#\"," + System.lineSeparator() +
					"  \"logoFile\": \"\"," + System.lineSeparator() +
					"  \"screenshots\": []," + System.lineSeparator() +
					"  \"dependencies\": []" + System.lineSeparator() +
					"}" + System.lineSeparator() +
					"]";

	public static String getMainModFile(String modName, String basePackage, String modID, String description, String
			                                                                                                          author, String credits) {
		String data = modFile;
		data = data.replaceAll("#MODID#", modID);
		data = data.replaceAll("#PACKAGE#", basePackage);
		data = data.replaceAll("#MODNAME#", modName);
		data = data.replaceAll("#DESCRIPTION#", description);
		data = data.replaceAll("#AUTHOR#", author);
		data = data.replaceAll("#CREDITS#", credits);


		return data;
	}
}
