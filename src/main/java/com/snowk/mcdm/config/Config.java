package com.snowk.mcdm.config;

import com.snowk.mcdm.MCDM;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Config {
	
	public static void loadConfig(String ymlName) {
		String path = MCDM.snowkPlugin.getDataFolder().getAbsolutePath() + File.separator + ymlName;
		File file = new File(path);
	    if (!file.exists()) {
			MCDM.snowkPlugin.saveDefaultConfig();
	    }
		try {
			FileInputStream fileinputstream = new FileInputStream(file);
			YamlConfiguration config = new YamlConfiguration();
			config.load(new InputStreamReader(fileinputstream, StandardCharsets.UTF_8));
		} catch (InvalidConfigurationException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static double getDouble(String label) {
		return MCDM.snowkPlugin.getConfig().getDouble(label);
	}
	public static String getString(String label) {
		return MCDM.snowkPlugin.getConfig().getString(label, "").replaceAll("&", "§");
	}
	public static boolean getBoolean(String label) {
		return MCDM.snowkPlugin.getConfig().getBoolean(label);
	}
	public static List<String> getStringList(String label) {
		return MCDM.snowkPlugin.getConfig().getStringList(label);
	}
	public static List<Integer> getIntegerList(String label) {
		return MCDM.snowkPlugin.getConfig().getIntegerList(label);
	}
}
