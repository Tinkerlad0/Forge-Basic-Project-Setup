package com.tinkerlad.forgeTool.files.mod.java;

public class MainModFile {
	private static final String modFile =
			"package #PACKAGE#;" + System.lineSeparator() +
					System.lineSeparator() +
					"import #PACKAGE#.block.ModBlocks;" + System.lineSeparator() +
					"import #PACKAGE#.config.Config;" + System.lineSeparator() +
					"import #PACKAGE#.item.ModItems;" + System.lineSeparator() +
					"import #PACKAGE#.proxies.CommonProxy;" + System.lineSeparator() +
					"import #PACKAGE#.recipe.Recipes;" + System.lineSeparator() +
					"import #PACKAGE#.tileentities.TileEntites;" + System.lineSeparator() +
					"import cpw.mods.fml.common.Mod;" + System.lineSeparator() +
					"import cpw.mods.fml.common.SidedProxy;" + System.lineSeparator() +
					"import cpw.mods.fml.common.event.FMLInitializationEvent;" + System.lineSeparator() +
					"import cpw.mods.fml.common.event.FMLPostInitializationEvent;" + System.lineSeparator() +
					"import cpw.mods.fml.common.event.FMLPreInitializationEvent;" + System.lineSeparator() +
					"import cpw.mods.fml.common.event.FMLServerStartingEvent;" + System.lineSeparator() +
					"import cpw.mods.fml.common.network.NetworkRegistry;" + System.lineSeparator() +
					"import cpw.mods.fml.relauncher.Side;" + System.lineSeparator() +
					"import cpw.mods.fml.relauncher.SideOnly;" + System.lineSeparator() +
					"import net.minecraft.creativetab.CreativeTabs;" + System.lineSeparator() +
					"import net.minecraft.item.Item;" + System.lineSeparator() +
					System.lineSeparator() +
					"import java.util.Random;" + System.lineSeparator() +
					System.lineSeparator() +
					"import static cpw.mods.fml.common.Mod.EventHandler;" + System.lineSeparator() +
					"import static cpw.mods.fml.common.Mod.Instance;" + System.lineSeparator() +
					System.lineSeparator() +
					"@Mod(modid = #MODID#.MODID, name = \"#MODNAME#\", version = \"#VERSION#\")" + System.lineSeparator() +
					"public class #MODID# {" + System.lineSeparator() +
					"    public static final String MODID = \"#MODID#\";" + System.lineSeparator() +
					System.lineSeparator() +
					"    @Instance(MODID)" + System.lineSeparator() +
					"    public static Conduits instance;" + System.lineSeparator() +
					System.lineSeparator() +
					"    public static Random RANDOM = new Random();" + System.lineSeparator() +
					System.lineSeparator() +
					"    @SidedProxy(clientSide = \"#PACKAGE#.proxies.ClientProxy\", serverSide = \"#PACKAGE#.proxies.CommonProxy\")" + System.lineSeparator() +
					"    public static CommonProxy proxy;" + System.lineSeparator() +
					System.lineSeparator() +
					"    @EventHandler" + System.lineSeparator() +
					"    public void preInit(FMLPreInitializationEvent event) {" + System.lineSeparator() +
					System.lineSeparator() +
					"        ModBlocks.init();" + System.lineSeparator() +
					"        ModItems.init();" + System.lineSeparator() +
					"        TileEntites.init();" + System.lineSeparator() +
					"        Recipes.init();" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					System.lineSeparator() +
					"    @EventHandler" + System.lineSeparator() +
					"    public void init(FMLInitializationEvent event) {" + System.lineSeparator() +
					"        proxy.registerRenderers();" + System.lineSeparator() +
					"    }" + System.lineSeparator() +
					System.lineSeparator() +
					"    @EventHandler" + System.lineSeparator() +
					"    public void postInit(FMLPostInitializationEvent event) {" + System.lineSeparator() +
					System.lineSeparator() +
					"    }" + System.lineSeparator() +
					"}";


	public static String getMainModFile(String modName, String basePackage, String modID){
		String data = modFile;
		data = data.replaceAll("#MODID#" , modID);
		data = data.replaceAll("#PACKAGE#", basePackage);
		data = data.replaceAll("#MODNAME#", modName);

		return data;
	}
}
