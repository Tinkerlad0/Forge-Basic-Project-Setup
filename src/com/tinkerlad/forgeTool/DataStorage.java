package com.tinkerlad.forgeTool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michael Brock on 12/18/2014.
 */
public class DataStorage {

    public static final int IDEA = 1;
    public static final int ECLIPSE = 2;
    public static final int OTHER = 0;

    public static Set<Map.Entry<String, JsonElement>> versionData;
    public static JsonElement builds;
    public static int latestBuild;

    public static int selectedBuild;
    public static File saveDirectory;
    public static String author;
    public static String modID;
    public static String basePackage;
    public static String modName;
    public static String credits;
    public static String description;
    public static int selectedIDE;
    public static boolean gradleInstalled;

    public static String forgeVersion;
    public static String mcVersion;

    public static JsonElement getForgeVersionForString(String request) {
        if (request == null || request == "" || request == " ") return null;

        for (Map.Entry<String, JsonElement> entry : versionData) {
            if (entry.getKey() == request) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static Set<Map.Entry<String, JsonElement>> getVersionData() throws IOException {
        URL url = new URL(Start.versionURL);
        HttpURLConnection data = (HttpURLConnection) url.openConnection();
        data.connect();

        JsonReader reader = new JsonReader(new InputStreamReader((InputStream) data.getContent()));
        reader.setLenient(true);
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(reader);
        JsonObject object = element.getAsJsonObject();
        JsonObject number = object.getAsJsonObject("number");
        JsonObject promos = object.getAsJsonObject("promos");

        builds = number;

        Set<Map.Entry<String, JsonElement>> entries = promos.entrySet();
        versionData = entries;

        latestBuild = calcMaxBuildNo();

        return versionData;
    }

    private static int calcMaxBuildNo() {
        if (versionData == null) return -1;
        int buildNo = 1;
        int temp = 0;
        for (Map.Entry<String, JsonElement> entry : versionData) {
            if ((temp = entry.getValue().getAsInt()) > buildNo) {
                buildNo = temp;
            }
        }

        return buildNo;
    }

    public static URL getSrcURLFromBuild(int buildNo) throws MalformedURLException {
        //Target Example
        //http://files.minecraftforge.net/maven/net/minecraftforge/forge/1.7.10-10.13.2.1272/forge-1.7.10-10.13.2.1272-src.zip
        StringBuilder uri = new StringBuilder("http://files.minecraftforge.net/maven/net/minecraftforge/forge/");

        buildForgeVersionNo(buildNo);
        getMCVersion(buildNo);

        uri.append(forgeVersion);
        uri.append("/forge-" + forgeVersion + "-src.zip");

        String builtURI = "";

        for (int i = 0; i < uri.length(); i++) {
            Character c = uri.charAt(i);
            if (!(c.equals('\"'))) {
                builtURI += c;
            }
        }

        return new URL(builtURI);

    }

    private static String getMCVersion(int buildNo) {
        if (builds == null) return "";

        JsonObject build = ((JsonObject) builds).getAsJsonObject(String.valueOf(buildNo));
        StringBuilder builder = new StringBuilder((build.get("mcversion")).toString());

        String version = "";

        for (int i = 0; i < builder.length(); i++) {
            Character c = builder.charAt(i);
            if (!(c.equals('\"'))) {
                version += c;
            }
        }

        mcVersion = version;

        return version;
    }

    private static String buildForgeVersionNo(int buildNo) {
        if (builds == null) return "";

        JsonObject build = ((JsonObject) builds).getAsJsonObject(String.valueOf(buildNo));
        StringBuilder builder = new StringBuilder((build.get("mcversion") + "-" + build.get("version")));

        String builtVersion = "";

        for (int i = 0; i < builder.length(); i++) {
            Character c = builder.charAt(i);
            if (!(c.equals('\"'))) {
                builtVersion += c;
            }
        }

        forgeVersion = builtVersion;

        return builtVersion;
    }

    public static String getBasePackagePathExtension(){
        if (basePackage == null) return "";

        String pathExtension = basePackage.replace('.', '/');

        return  pathExtension;
    }

}
