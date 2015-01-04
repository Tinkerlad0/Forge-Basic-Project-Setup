package com.tinkerlad.forgeTool.files.mod.java.tileentities;

import com.tinkerlad.forgeTool.files.mod.ModFile;

public class TileEntitiesFile extends ModFile {

	public static final String modFile =
			"package #PACKAGE#.tileentities;" + System.lineSeparator() +
					System.lineSeparator() +
					"import cpw.mods.fml.common.registry.GameRegistry;" + System.lineSeparator() +
					System.lineSeparator() +
					"public class TileEntities {" + System.lineSeparator() +
					"    public static void init() {" + System.lineSeparator() +
					"        //Register all TileEntities here" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					"}\n";

}
