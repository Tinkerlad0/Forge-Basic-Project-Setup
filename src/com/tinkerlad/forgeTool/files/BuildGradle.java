package com.tinkerlad.forgeTool.files;

/**
 * Created by Michael Brock on 12/21/2014.
 */
public class BuildGradle {

	private static final String buildGradle = "buildscript {" + System.lineSeparator() +
			                                          "    repositories {" + System.lineSeparator() +
			                                          "        mavenCentral()" + System.lineSeparator() +
			                                          "        maven {" + System.lineSeparator() +
			                                          "            name = \"forge\"" + System.lineSeparator() +
			                                          "            url = \"http://files.minecraftforge.net/maven\"" + System.lineSeparator() +
			                                          "        }" + System.lineSeparator() +
			                                          "        maven {" + System.lineSeparator() +
			                                          "            name = \"sonatype\"" + System.lineSeparator() +
			                                          "            url = \"https://oss.sonatype.org/content/repositories/snapshots/\"" + System.lineSeparator() +
			                                          "        }" + System.lineSeparator() +
			                                          "    }" + System.lineSeparator() +
			                                          "    dependencies {" + System.lineSeparator() +
			                                          "        classpath 'net.minecraftforge.gradle:ForgeGradle:1.2-SNAPSHOT'" + System.lineSeparator() +
			                                          "    }" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "apply plugin: 'forge'" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "version = mod_version" + System.lineSeparator() +
			                                          "group = \"@PACKAGE@\" // http://maven.apache.org/guides/mini/guide-naming-conventions.html" + System.lineSeparator() +
			                                          "archivesBaseName = \"@MODID@\"" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "minecraft {" + System.lineSeparator() +
			                                          "    version = forge_version" + System.lineSeparator() +
			                                          "    assetDir = \"eclipse/assets\"" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "dependencies {" + System.lineSeparator() +
			                                          "    // you may put jars on which you depend on in ./libs" + System.lineSeparator() +
			                                          "    // or you may define them like so.." + System.lineSeparator() +
			                                          "    //compile \"some.group:artifact:version:classifier\"" + System.lineSeparator() +
			                                          "    //compile \"some.group:artifact:version\"" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "    // real examples" + System.lineSeparator() +
			                                          "    //compile 'com.mod-buildcraft:buildcraft:6.0.8:dev'  // adds buildcraft to the dev env" + System.lineSeparator() +
			                                          "    //compile 'com.googlecode.efficient-java-matrix-library:ejml:0.24' // adds ejml to the dev env" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "    // for more info..." + System.lineSeparator() +
			                                          "    // http://www.gradle.org/docs/current/userguide/artifact_dependencies_tutorial.html" + System.lineSeparator() +
			                                          "    // http://www.gradle.org/docs/current/userguide/dependency_management.html" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "processResources" + System.lineSeparator() +
			                                          "        {" + System.lineSeparator() +
			                                          "            // this will ensure that this task is redone when the versions change." + System.lineSeparator() +
			                                          "            inputs.property \"version\", project.version" + System.lineSeparator() +
			                                          "            inputs.property \"mcversion\", project.minecraft.version" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "            // replace stuff in mcmod.info, nothing else" + System.lineSeparator() +
			                                          "            from(sourceSets.main.resources.srcDirs) {" + System.lineSeparator() +
			                                          "                include 'mcmod.info'" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "                // replace version and mcversion" + System.lineSeparator() +
			                                          "                expand 'version': project.version, 'mcversion': project.minecraft.version" + System.lineSeparator() +
			                                          "            }" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "            // copy everything else, thats not the mcmod.info" + System.lineSeparator() +
			                                          "            from(sourceSets.main.resources.srcDirs) {" + System.lineSeparator() +
			                                          "                exclude 'mcmod.info'" + System.lineSeparator() +
			                                          "            }" + System.lineSeparator() +
			                                          "        }" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "task deobfJar(type: Jar) {" + System.lineSeparator() +
			                                          "    from sourceSets.main.output" + System.lineSeparator() +
			                                          "    classifier = 'dev'" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "task release(type: Copy) {" + System.lineSeparator() +
			                                          "    def releaseDir = new File('build/release/' + mod_version)" + System.lineSeparator() +
			                                          "    releaseDir.mkdirs();" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "    from 'build/libs'" + System.lineSeparator() +
			                                          "    into releaseDir" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "task clearBuildDir(type: Delete) {" + System.lineSeparator() +
			                                          "    def dirName = \"build/libs\"" + System.lineSeparator() +
			                                          "    delete dirName" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "    doLast {" + System.lineSeparator() +
			                                          "        file(dirName).mkdirs()" + System.lineSeparator() +
			                                          "    }" + System.lineSeparator() +
			                                          "}" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "build.mustRunAfter clearBuildDir" + System.lineSeparator() +
			                                          "" + System.lineSeparator() +
			                                          "release.dependsOn clearBuildDir" + System.lineSeparator() +
			                                          "release.dependsOn build" + System.lineSeparator() +
			                                          "release.dependsOn deobfJar";

	private static final String buildProperties = "mod_version = 0.0.1" + System.lineSeparator() +
			                                              "minecraft_version = @MC_VERSION@" + System.lineSeparator() +
			                                              "forge_version = @FORGE_VERSION@";

	public static String getBuildGradle(String basePackage, String modid) {
		String data = buildGradle;

		data = data.replace("@PACKAGE@", basePackage);
		data = data.replace("@MODID@", modid);

		return data;
	}

	public static String getBuildProperties(String forgeVersion, String mcVersion){
		String data = buildProperties.replaceAll("@FORGE_VERSION@",forgeVersion);
		data = data.replaceAll("@MC_VERSION@", mcVersion);
		return data;
	}
}
