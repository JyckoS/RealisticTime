package com.gmail.JyckoSianjaya.RealTime;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.JyckoSianjaya.RealTime.Utils.Utility;

public class RealTime extends JavaPlugin {
	private static RealTime instance;
	private ClassBanker cb;
	@Override
	public void onEnable() {
		logConsole();
		instance = this;
		new Metrics(this);
		loadFiles();
		loadClasses();
		this.getCommand("realtime").setExecutor(RealCommands.getInstance());
	}
	public static RealTime getPlugin() {
		return instance;
	}
	@Override
	public void onDisable() {
	}
	private void logConsole() {
		PluginDescriptionFile pdf = this.getDescription();
		Utility.sendConsole("       &3&l/&b&l------------------&3&l\\");
		Utility.sendConsole("            &b&lRealTimer");
		Utility.sendConsole("          &fRunning V.&e" + pdf.getVersion());
		Utility.sendConsole("       &3&l\\&b&l------------------&3&l/");

	}
	private void loadFiles() {
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
	}
	private void loadClasses() {
		cb = ClassBanker.setUp();
	}

}
