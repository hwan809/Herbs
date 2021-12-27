package me.vivace.herbs;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	
    private Commands commands = new Commands();
    public static HashMap<Location, Integer> herbspots = new HashMap<Location, Integer>();
	
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "HERBS ENABLED");
		getServer().getPluginManager().registerEvents(new Events(), this);
		getCommand(commands.cmd1).setExecutor(commands);
		getConfig().options().copyDefaults(true);
		
		herbSpot();
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "HERBS DISABLED");
		
//		getConfig().set("name", "");
//		saveConfig();
	}
	
	public void herbSpot() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for (Location l : herbspots.keySet()) {
					if (herbspots.get(l) != 0) {
						herbspots.put(l, herbspots.get(l) - 1);
						l.getWorld().spawnParticle(Particle.WATER_BUBBLE, l.clone().add(0.5, 1, 0.5), 10);
					}
				}
			}
			
		}.runTaskTimer(Main.getPlugin(Main.class), 1, 1);
	}
}
