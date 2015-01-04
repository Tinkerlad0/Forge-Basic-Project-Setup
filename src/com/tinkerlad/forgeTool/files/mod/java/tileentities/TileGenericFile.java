package com.tinkerlad.forgeTool.files.mod.java.tileentities;

import com.tinkerlad.forgeTool.files.mod.ModFile;

public class TileGenericFile extends ModFile{
	public static final String modFile = 
			"package #PACKAGE#.tileentities;" + System.lineSeparator() +
					System.lineSeparator() +
					"import net.minecraft.tileentity.TileEntity;" + System.lineSeparator() +
					System.lineSeparator() +
					"public class TileGeneric extends TileEntity {" + System.lineSeparator() +
					"    public void markForUpdate() {" + System.lineSeparator() +
					"        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					"}\n";

}
