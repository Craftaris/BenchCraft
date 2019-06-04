package me.benchcraft.bcf;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.benchcraft.bcf.listeners.ListenerClass;

public class BenchCraft extends JavaPlugin
{
private File databaseFile;
private File configFile;
private FileConfiguration database;
private FileConfiguration config;

	/**
	 * Uruchomienie pluginu.
	 */

	public void onEnable() {
		
		new ListenerClass(this);
		registerDatabaseFiles();
		registerCommands();
		registerConfig();
		getConfig().options().copyDefaults(true);
		
	}
	
	/**
	 * Rejestracja komend - na razie brak.
	 */

	public void registerCommands() {

	}

	/**
	 * Rejestracja bazy danych - na razie z niej nie korzystamy.
	 */
	
	private void registerDatabaseFiles() {
		this.databaseFile = new File(getDataFolder(), "database.yml");
		this.database = YamlConfiguration.loadConfiguration(this.databaseFile);
		saveDatabase();
	}

	public void saveDatabase() {
		try {
			this.database.save(this.databaseFile);
		}
		catch (Exception localException) {
		}
	}
   
	public File getDatabaseFile() {
		return this.databaseFile;
	}
 
	public FileConfiguration getDatabase() {
		return this.database;
	}
	
	/**
	 * Rejestracja configu - na razie z niego nie korzystamy, ale w przyszłości być może będzie potrzebny.
	 */
   
	private void registerConfig() {
	    this.configFile = new File(getDataFolder(), "config.yml");
	    this.config = YamlConfiguration.loadConfiguration(this.configFile);
	    getConfig().options().copyDefaults(true);
		saveConfig();
	}
 }
