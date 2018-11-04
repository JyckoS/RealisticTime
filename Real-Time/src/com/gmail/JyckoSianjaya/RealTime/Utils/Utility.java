package com.gmail.JyckoSianjaya.RealTime.Utils;

import java.lang.reflect.Constructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public final class Utility {
	int time;
	int ctime;
	private Utility() {}
	public static Class<?> getClass(String classname) {
		String servversion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + servversion + "." + classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); 
		} 
		return null;
	  }

	public static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", new Class[] { getClass("Packet") }).invoke(playerConnection, new Object[] { packet });
		} catch (Exception e) {
			e.printStackTrace();
	   }
	}

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {


	    try
	    {
	      if (title != null) {
	        title = TransColor(title);
	        Object e = getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
	        Object chatTitle = getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
	        Constructor titleConstructor = getClass("PacketPlayOutTitle").getConstructor(new Class[] { getClass("PacketPlayOutTitle").getDeclaredClasses()[0], getClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	        Object titlePacket = titleConstructor.newInstance(new Object[] { e, chatTitle, fadeIn, stay, fadeOut });
	        sendPacket(player, titlePacket);

	        e = getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
	        chatTitle = getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
	        titleConstructor = getClass("PacketPlayOutTitle").getConstructor(new Class[] { getClass("PacketPlayOutTitle").getDeclaredClasses()[0], getClass("IChatBaseComponent") });
	        titlePacket = titleConstructor.newInstance(new Object[] { e, chatTitle });
	        sendPacket(player, titlePacket);
	      }

	      if (subtitle != null) {
	        subtitle = TransColor(subtitle);
	        Object e = getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
	        Object chatSubtitle = getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + title + "\"}" });
	        Constructor subtitleConstructor = getClass("PacketPlayOutTitle").getConstructor(new Class[] { getClass("PacketPlayOutTitle").getDeclaredClasses()[0], getClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	        Object subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
	        sendPacket(player, subtitlePacket);

	        e = getClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
	        chatSubtitle = getClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
	        subtitleConstructor = getClass("PacketPlayOutTitle").getConstructor(new Class[] { getClass("PacketPlayOutTitle").getDeclaredClasses()[0], getClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
	        subtitlePacket = subtitleConstructor.newInstance(new Object[] { e, chatSubtitle, fadeIn, stay, fadeOut });
	        sendPacket(player, subtitlePacket);
	      }
	    } catch (Exception var11) {
	      var11.printStackTrace();
	    }

	}
	public static void sendMsg(Player b, String msg) {
		b.sendMessage(TransColor(msg));
	}
	public static void sendMsg(CommandSender b, String msg) {
		b.sendMessage(TransColor(msg));
	}
	public static void broadcast(String msg) {
		Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
	}
	public static void sendActionBar(Player b, String ActionBar) {
		b.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', ActionBar)));
	}
	public static void sendConsole(String msg) {
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', msg));;
	}
	public static String TransColor(String c) {
		return ChatColor.translateAlternateColorCodes('&', c);
	}
	public static List<String> TransColor(List<String> strlist) {
		for (int x = 0; x < strlist.size(); x++) {
			strlist.set(x, TransColor(strlist.get(x)));
		}
		return strlist;
	}
	private static int mines(int integer) {
		return integer - 20;
	}
	public static void PlaySoundAt(World w, Location p, Sound s, Float vol, Float pit) {
		w.playSound(p, s, vol, pit);;
	}
	public static void PlaySound(Player p, Sound s, Float vol, Float pit) {
		p.playSound(p.getLocation(), s, vol, pit);
	}
	public static ArrayList<Player> near(Entity loc, int radius) {
		ArrayList<Player> nearby = new ArrayList<>();
		for (Entity e : loc.getNearbyEntities(radius, radius, radius)) {
			if (e instanceof Player) {
				nearby.add((Player) e);
			}
		}
		return nearby;
	}
	public static void PlayParticle(World world, Location loc, Effect particle, int count) {
		world.playEffect(loc, particle, count);
	}
	public static String normalizeTime(int seconds) {
		int sec = seconds;
		int min = 0;
		int hour = 0;
		int day = 0;
		while (sec >= 60) {
			min+=1;
			sec-=60;
		}
		while (min >= 60) {
			hour+=1;
			min-=60;
		}
		while (hour >= 24) {
			day+=1;
			hour-=24;
		}
		if (sec == 0 && min == 0 && hour == 0 && day == 0) {
			return "&a&lZERO!";
		}
		if (min == 0 && hour == 0 && day == 0) {
			return sec + " Seconds";
		}
		if (hour == 0 && day == 0 && min > 0) {
			return min + " Minutes " + sec + " Seconds"; 
		}
		if (day == 0 && hour > 0) {
			return hour + " Hours " + min + " Minutes " + sec + " Seconds";
		}
		if (day > 0) {
			return day + " Days " + hour + " Hours " + min + " Minutes " + sec + " Seconds";
		}
		return "&a&lZERO!";
	}

}
