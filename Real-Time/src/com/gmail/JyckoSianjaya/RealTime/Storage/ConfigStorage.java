package com.gmail.JyckoSianjaya.RealTime.Storage;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.gmail.JyckoSianjaya.RealTime.RealTime;
import com.gmail.JyckoSianjaya.RealTime.Utils.Utility;

public class ConfigStorage {
	private static ConfigStorage instance;
	private ZoneId tz;
	private ArrayList<World> enabledworlds = new ArrayList<World>();
	private long updateInterval = 100;
	private List<String> timemsg;
	private ConfigStorage() {
		reloadConfig();
	}
	public static ConfigStorage getInstance () {
		if (instance == null) {
			instance = new ConfigStorage();
		}
		return instance;
	}
	public void reloadConfig() {
		FileConfiguration cfg = RealTime.getPlugin().getConfig();
		String tm = cfg.getString("time_zone");
		tz = ZoneId.of(tm);
		Utility.sendConsole("&7Using the Time from: &f" + tz.getId());
		List<String> worlds = cfg.getStringList("enabled_worlds");
		for (String str : worlds) {
			try {
			if (Bukkit.getWorld(str) == null) {
				continue;
			}
			} catch (NullPointerException e) {
		}
			World w = Bukkit.getWorld(str);
			enabledworlds.add(w);
		}
		int updates = (int) cfg.getDouble("update.interval_seconds") * 20;
		updateInterval = Long.valueOf(updates + "");
		timemsg = Utility.TransColor(cfg.getStringList("time_message"));
	}
	public ZoneId getZoneid() {
		return tz;
	}
	public ArrayList<World> getEnabledWorlds() {
		return enabledworlds;
	}
	public long getUpdate() {
		return updateInterval;
	}
	public List<String> getMessage() {
		return timemsg;
	}
	
}
