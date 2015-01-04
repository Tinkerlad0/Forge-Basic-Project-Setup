package com.tinkerlad.forgeTool.files.mod.java.block;

import com.tinkerlad.forgeTool.files.mod.ModFile;

public class BlockGenericFile extends ModFile {

	private static final String modFile =
			"package #PACKAGE#.block;" + System.lineSeparator() +
					System.lineSeparator() +
					"import #PACKAGE#.#MODID#;" + System.lineSeparator() +
					"import net.minecraft.block.Block;" + System.lineSeparator() +
					"import net.minecraft.block.material.Material;" + System.lineSeparator() +
					System.lineSeparator() +
					"public class BlockGeneric extends Block {" + System.lineSeparator() +
					System.lineSeparator() +
					"    protected BlockGeneric(Material material) {" + System.lineSeparator() +
					"        super(material);" + System.lineSeparator() +
					"        this.setBlockName(\"default\");" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					System.lineSeparator() +
					"    #Override" + System.lineSeparator() +
					"    public Block setBlockName(String name) {" + System.lineSeparator() +
					"        return super.setBlockName(#MODID#.MODID + \":\" + name);" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					System.lineSeparator() +
					"    #Override" + System.lineSeparator() +
					"    public Block setBlockTextureName(String name) {" + System.lineSeparator() +
					"        return super.setBlockTextureName(#MODID#.MODID + \":\" + name);" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					"}\n";
}
