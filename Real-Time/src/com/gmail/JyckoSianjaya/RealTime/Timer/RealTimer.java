package com.gmail.JyckoSianjaya.RealTime.Timer;

import java.time.ZonedDateTime;

import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.JyckoSianjaya.RealTime.RealTime;
import com.gmail.JyckoSianjaya.RealTime.Storage.ConfigStorage;
import com.gmail.JyckoSianjaya.RealTime.Utils.Utility;

public class RealTimer {
	private static RealTimer instance;
	private static Boolean settedUp = false;
	private ConfigStorage cfg = ConfigStorage.getInstance();
	private BukkitTask timer;
	private int seconds = 0;
	private int globalseconds = 0;
	private int minutes = 0; 
	private int hours = 0;
	public static RealTimer setup() {
		if (settedUp) {
			return instance;
		}
		settedUp = true;
		instance = new RealTimer();
		return instance;
	}
	public void UpdateTime() {
		ZonedDateTime time = ZonedDateTime.now(cfg.getZoneid());
		hours = time.getHour();
		minutes = time.getMinute();
		seconds = time.getSecond();
	}
	private RealTimer() {
		loadRunnable();
	}
	public static RealTimer getInstance() {
		return instance;
	}
	private void loadRunnable() {
	new BukkitRunnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			seconds++;
			globalseconds++;
			if (seconds >= 60) {
				seconds -= 60;
				minutes++;
			}
			if (minutes >= 60) {
				hours++;
				minutes -= 60;
			}
			if (hours >= 24) {
				globalseconds = 0;
				hours = 0;
				seconds = 0;
				minutes = 0;
				Utility.sendConsole("&e&l[RealTimer] &aA new day has been started!");
			}
			
		}
	}.runTaskTimerAsynchronously(RealTime.getPlugin(), 20L, 20L);
	new BukkitRunnable() {

		@Override
		public void run() {
			for (World w : cfg.getEnabledWorlds()) {
				int clone = hours - 6;
				Long target = Long.valueOf("" + clone * 1000);
				w.setTime(target);
			}
		}
	}.runTaskTimer(RealTime.getPlugin(), cfg.getUpdate(), cfg.getUpdate());
	}
	public String getHour() {
		if (hours < 10) {
			return " " + hours;
		}
		return hours + "";
	}
	public String getMinute() {
		if (minutes < 10) {
			return " " + minutes;
		}
		return minutes + "";
	}
	public String getSecond() {
		if (seconds < 10) {
			return " " + seconds;
		}
		return seconds + "";
	}
	public int getGlobalSeconds() {
		return globalseconds;
	}
	public void setHours(int newhour) {
		this.hours = newhour;
	}
	public void setMinutes(int newminute) {
		minutes = newminute;
	}
	public void setSeconds(int newseconds) {
		seconds = newseconds;
	}
	public int getHours() {
		return hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getSeconds() {
		return seconds;
	}
}
