package com.github.Markey50.BuildChallenge;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildChallenge extends JavaPlugin {
	
File configFile;
File datacoreFile;
File arenasFile;
FileConfiguration config;
FileConfiguration datacore;
FileConfiguration arenas;

	public void onEnable(){
		getCommand("build").setExecutor(new BuildChallengeCommandExecutor(this));
		getCommand("badmin").setExecutor(new BAdmin(this));
		getCommand("bini").setExecutor(new BIni(this));
		getCommand("bjudge").setExecutor(new BJudge(this));
		getCommand("builder").setExecutor(new Builder(this));
		getLogger().log(Level.SEVERE, "BuildChallenge plugin activated!");
		configFile = new File(getDataFolder(), "config.yml");
		datacoreFile = new File (getDataFolder(), "datacore.yml");
		arenasFile = new File (getDataFolder(), "arenas.yml");
		
		try {
			
			firstRun();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		config = new YamlConfiguration();
		datacore = new YamlConfiguration();
		arenas = new YamlConfiguration();
		loadYamls();
		
		}

	public void saveYamls() {

	    try {
	        config.save(configFile);
	        datacore.save(datacoreFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


	public void loadYamls() {
	    try {
	        config.load(configFile);
	        datacore.load(datacoreFile);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private void firstRun() {
		if(!configFile.exists()){
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
		if(!datacoreFile.exists()){
			datacoreFile.getParentFile().mkdirs();
			copy(getResource("datacore.yml"), datacoreFile);
		}
		if(!arenasFile.exists()){
			configFile.getParentFile().mkdirs();
			copy(getResource("arenas.yml"), arenasFile);
		}
		
		
	}


	private void copy(InputStream in, File file) {
	    try {
	        OutputStream out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	            out.write(buf,0,len);
	        }
	        out.close();
	        in.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}


	public void onDisable(){
		getLogger().log(Level.SEVERE, "Build Challenge has been disabled!");
		}
}