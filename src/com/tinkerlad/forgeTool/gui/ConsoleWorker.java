package com.tinkerlad.forgeTool.gui;

import com.tinkerlad.forgeTool.Commands;
import com.tinkerlad.forgeTool.Start;
import com.tinkerlad.forgeTool.files.mod.BuildGradle;
import com.tinkerlad.forgeTool.files.mod.java.MainModFile;
import com.tinkerlad.forgeTool.files.mod.java.block.BlockGenericFile;
import com.tinkerlad.forgeTool.files.mod.java.block.ModBlocksFile;
import com.tinkerlad.forgeTool.files.mod.java.config.ConfigFile;
import com.tinkerlad.forgeTool.files.mod.java.item.ItemGenericFile;
import com.tinkerlad.forgeTool.files.mod.java.item.ModItemsFile;
import com.tinkerlad.forgeTool.files.mod.java.proxies.ClientProxyFile;
import com.tinkerlad.forgeTool.files.mod.java.proxies.CommonProxyFile;
import com.tinkerlad.forgeTool.files.mod.java.tileentities.TileEntitiesFile;
import com.tinkerlad.forgeTool.files.mod.java.tileentities.TileGenericFile;
import com.tinkerlad.forgeTool.files.mod.resources.mcmodInfoFile;
import com.tinkerlad.forgeTool.utils.FileUtils;
import com.tinkerlad.forgeTool.utils.UnzipUtility;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import static com.tinkerlad.forgeTool.DataStorage.*;

/**
 * Created by Michael Brock on 12/21/2014.
 */
public class ConsoleWorker extends SwingWorker {

	private Console console;

	public ConsoleWorker(Console console) {
		this.console = console;
	}

	@Override
	protected Object doInBackground() throws Exception {
		createForgeProject();
		return null;
	}

	@Override
	protected void process(List chunks) {
		for (Object obj : chunks) {
			if (!(obj instanceof String)) { continue; }
			console.addLineToConsole((String) obj);
		}
	}

	public void publish(String string) {
		publish((Object) string);
	}

	private void createForgeProject() {

		setProgress(1);

		File baseDir = saveDirectory;
		File libsDir = new File(saveDirectory.getAbsolutePath() + "/libs");
		File devDir = new File(saveDirectory.getAbsolutePath() + "/" + modName);

		baseDir.mkdirs();
		publish("Created Base Directory");
		libsDir.mkdir();
		publish("Created Libs Directory");
		devDir.mkdir();
		publish("Created Dev Directory");

		publish("");


		//Finished with Directory creation, move onto downloading forge src zip

		setProgress(2);

		Date startDownload = new Date();

		try {

			URL url = getSrcURLFromBuild(selectedBuild);
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			FileOutputStream out = new FileOutputStream(libsDir.getAbsolutePath() + "/forge" + forgeVersion + ".zip");
			byte[] b = new byte[1024];
			int count;
			while ((count = in.read(b)) >= 0) {
				out.write(b, 0, count);
			}
			out.flush();
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Date finishDownload = new Date();

		publish("Download finished in " + Start.timeUtils.getDifference(startDownload, finishDownload));
		publish("");

		setProgress(3);

		//Download finished, time to unzip it


		try {
			UnzipUtility zipUtil = new UnzipUtility(this);
			zipUtil.unzip(libsDir.getAbsolutePath() + "/forge" + forgeVersion + ".zip", devDir.getAbsolutePath(), console);
		} catch (IOException e) {
			e.printStackTrace();
		}

		publish("");

		setProgress(4);
		//Finished Decompressing, now create Gradle Stuff

		createFile(new File(devDir.getAbsolutePath() + "/build.properties"), BuildGradle.getBuildProperties
				(forgeVersion, mcVersion));
		createFile(new File(devDir.getAbsolutePath() + "/build.gradle"), BuildGradle.getBuildGradle(basePackage, modID));

		//Gradle files setup correctly

		setProgress(5);

		//Gradle files in place now setup Decompiled Workspace

		executeCommand(Commands.FORGE_DECOMPILE, devDir);

		setProgress(6);


		//Finished forge setup, Now demolish examplemod

		File exampleModDir = new File(devDir.getAbsolutePath() + "/src/main/java/com");

		try {
			FileUtils.deleteRecursive(exampleModDir);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}


		File exampleModResources = new File(devDir.getAbsolutePath() + "/src/main/resources/mcmod.info");
		if (exampleModResources.exists()) {
			exampleModResources.delete();
		}

		setProgress(7);

		//Create Base Mod Files

		String basePackageDir = devDir.getAbsolutePath() + "/src/main/java/" + getBasePackagePathExtension();

		//MainModFile
		File mainModFile = new File(basePackageDir + "/" + modID + ".java");
		createFile(mainModFile, MainModFile.getMainModFile(modName, basePackage, modID));

		//Blocks Package
		File blockGenericFile = new File(basePackageDir + "/blocks/BlockGeneric.java");
		createFile(blockGenericFile, BlockGenericFile.getMainModFile(modName, basePackage, modID));

		File modBlocksFile = new File(basePackageDir + "/blocks/ModBlocks.java");
		createFile(modBlocksFile, ModBlocksFile.getMainModFile(modName, basePackage, modID));

		//Config Package
		File configFile = new File(basePackageDir + "/config/Config.java");
		createFile(configFile, ConfigFile.getMainModFile(modName, basePackage, modID));

		//Items Package
		File itemGenericFile = new File(basePackageDir + "/item/ItemGeneric.java");
		createFile(itemGenericFile, ItemGenericFile.getMainModFile(modName, basePackage, modID));

		File modItemsFile = new File(basePackageDir + "/item/ModItems.java");
		createFile(modItemsFile, ModItemsFile.getMainModFile(modName, basePackage, modID));

		//Proxies Package
		File commonProxyFile = new File(basePackageDir + "/proxies/CommonProxy.java");
		createFile(commonProxyFile, CommonProxyFile.getMainModFile(modName, basePackage, modID));

		File clientProxyFile = new File(basePackageDir + "/proxies/ClientProxy.java");
		createFile(clientProxyFile, ClientProxyFile.getMainModFile(modName, basePackage, modID));

		//TileEntities Package
		File tileEntitiesFile = new File(basePackageDir + "/tileentities/TileEntities.java");
		createFile(tileEntitiesFile, TileEntitiesFile.getMainModFile(modName, basePackage, modID));

		File tileGenericFile = new File(basePackageDir + "/tileentities/TileGeneric.java");
		createFile(tileEntitiesFile, TileGenericFile.getMainModFile(modName, basePackage, modID));

		setProgress(8);

		//*****************************Resources*************************************

		//mcmod.info

		File mcModFile = new File(devDir.getAbsolutePath() + "/src/main/resources/mcmod.info");
		createFile(mcModFile, mcmodInfoFile.getMainModFile(modName,basePackage,modID,description,author,credits));

		setProgress(9);


		switch (selectedIDE){
			case IDEA:
				executeCommand(Commands.IDEA_RUNS,devDir);
				break;
			case ECLIPSE:
				executeCommand(Commands.ECLIPSE_RUNS,devDir);
				break;
			default:
				break;
		}

		setProgress(10);

		publish("Finished Setting up project");

		JOptionPane.showMessageDialog(null,"Successfully set up " + modName + "! Enjoy","COMPLETE!!", JOptionPane.INFORMATION_MESSAGE);

	}

	private void executeCommand(String command, File directory) {
		try {
			String output;

			if (!directory.exists()) {
				directory.mkdirs();
			}

			Process process = Runtime.getRuntime().exec(command, null, new File(directory.getAbsolutePath() + "\\"));

			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));

			while ((output = in.readLine()) != null) {
				publish(output + System.lineSeparator());
			}
			in.close();
			publish(System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createFile(File file, String data) {

		file.mkdirs();
		if (file.exists()) {
			file.delete();
		}

		try {
			PrintWriter filePrinter = new PrintWriter(file);

			filePrinter.print(data);

			filePrinter.close();

			publish("Created " + file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
