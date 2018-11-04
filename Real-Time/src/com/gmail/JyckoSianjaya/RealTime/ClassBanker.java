package com.gmail.JyckoSianjaya.RealTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.gmail.JyckoSianjaya.RealTime.Storage.ConfigStorage;
import com.gmail.JyckoSianjaya.RealTime.Timer.RealTimer;

public class ClassBanker {
	private static ClassBanker instance;
	private static Boolean settedup = false;
	private RealCommands cmds;
	private ConfigStorage cfg;
	private RealTimer rt;
	public static ClassBanker setUp() {
		if (settedup) {
			return instance;
		}
		instance = new ClassBanker();
		return instance;
	}
	private ClassBanker() {
		loadClasses();
	}
	private static ClassBanker getInstance() {
		return instance;
	}
	private void loadClasses() {
		cfg = ConfigStorage.getInstance();
		rt = RealTimer.setup();
		cmds = RealCommands.getInstance();
		ZoneId zone = cfg.getZoneid();
		ZonedDateTime time = ZonedDateTime.now(zone);
		rt.setHours(time.getHour());
		rt.setMinutes(time.getMinute());
		rt.setSeconds(time.getSecond());
	}
}
