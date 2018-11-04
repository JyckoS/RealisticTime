package com.gmail.JyckoSianjaya.RealTime;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.gmail.JyckoSianjaya.RealTime.Storage.ConfigStorage;
import com.gmail.JyckoSianjaya.RealTime.Timer.RealTimer;
import com.gmail.JyckoSianjaya.RealTime.Utils.Utility;

public class RealCommands implements CommandExecutor {
	private static RealCommands instance;
	private ConfigStorage cfg = ConfigStorage.getInstance();
	private RealTimer timer = RealTimer.getInstance();
	private RealCommands() {
	}
	public static RealCommands getInstance() {
		if (instance == null) {
			instance = new RealCommands();
		}
		return instance;
	}
	@Override
	public boolean onCommand(CommandSender send, Command cmd, String label, String[] args) {
		redoCommand(send, cmd, args);
		return true;

	}
	private void redoCommand(CommandSender send, Command cmd, String[] args) {
		if (!send.hasPermission("timer.admin") && !send.hasPermission("timer.check")) {
			Utility.sendMsg(send, "&c&lOops! &7You can't check the time!");
			return;
		}
		if (!send.hasPermission("timer.admin")) {
			String ctime = timer.getHour() + ":" + timer.getMinute();
			for (String str : cfg.getMessage()) {
				str = str.replaceAll("%TIME%", ctime);
				Utility.sendMsg(send, str);
			}
			return;
		}
		if (args.length == 0) {
			String ver = RealTime.getPlugin().getDescription().getVersion();
			Utility.sendMsg(send, "      &b&lReal&f&lTime &7by &oGober &7using &7V.&f" + ver);
			Utility.sendMsg(send, "&f         Use &e\"/help\" &for commands.");
			return;
		}
		switch (args[0]) {
		case "time":
		case "check":
			String ctime = timer.getHour() + ":" + timer.getMinute();
			for (String str : cfg.getMessage()) {
				str = str.replaceAll("%TIME%", ctime);
				Utility.sendMsg(send, str);
			}
			return;
		case "reload":
			try {
				RealTime.getPlugin().reloadConfig();
				cfg.reloadConfig();
				timer.UpdateTime();
			} catch (Exception e) {
				e.printStackTrace();
				Utility.sendMsg(send, "&cAn error occured! Try to check your config.yml if everything is set correctly. If you're confused what you're wrong at, feel free to contact me on Spigot: Gober");
				return;
			}
			Utility.sendMsg(send, "&b&lReal&f&lTime &8> &aSuccessfully reloaded Config!");
			return;
		case "help":
		default:
			Utility.sendMsg(send, "&b&lR&f&lT &7Commands -");
			Utility.sendMsg(send, " &8> &7/realtime &ftime");
			Utility.sendMsg(send, " &8> &7/realtime &creload");
			Utility.sendMsg(send, " &8> &7/realtime &eset &7<Min/Sec/Hour> <Integer>");
			return;
		case "set":
			if (args.length < 3) { //                  0         0        1             2
				Utility.sendMsg(send, "&7Please do &f/realtime set &e<Min/Sec/Hour> &9<Integer>");
				return;
			}
			try {
				Integer.valueOf(args[2]);
			} catch (NumberFormatException e) {
				Utility.sendMsg(send, "&cThat's not a number.");
				return;
			}
			int target = Integer.valueOf(args[2]);
			String wanted = args[1];
			switch (wanted.toLowerCase()) {
			default:
				 Utility.sendMsg(send, "&7Use either &fMinute &7/ &fSecond &7/ &fHour&7!");
			case "min":
			case "minute":
			case "m":
				timer.setMinutes(target);
				Utility.sendMsg(send, "&b&lReal&f&lTime &8> &7Successfully changed the &fMinute &7to &f" + target);
				return;
			case "h":
			case "hour":
			case "hours":
				timer.setHours(target);
				Utility.sendMsg(send, "&b&lReal&f&lTime &8> &7Successfully changed the &fHour &7to &f" + target);
				return;
			case "sec":
			case "second":
			case "s":
				timer.setSeconds(target);
				Utility.sendMsg(send, "&b&lReal&f&lTime &8> &7Successfully changed the &fSeconds &7to &f" + target);
				return;
			}
		}
		
	}
}
