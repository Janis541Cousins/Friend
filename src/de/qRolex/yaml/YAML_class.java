package de.qRolex.yaml;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class YAML_class {
	public void setStandart() {
		FileConfiguration cfg = getFileConfiguration();
		cfg.options().copyDefaults(true);
		cfg.addDefault("#FREUNDE WERDEN HIER ERSCHEINEN", "");
		try {
			cfg.save(getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static File getFile() {
		return new File("plugins/friend", "Friends.yml");
	}
	
	public static FileConfiguration getFileConfiguration() {
		return YamlConfiguration.loadConfiguration(getFile());
	}
}
