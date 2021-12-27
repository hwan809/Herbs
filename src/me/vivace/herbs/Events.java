package me.vivace.herbs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import net.md_5.bungee.api.ChatColor;

public class Events implements Listener {
	
	@EventHandler
	public void onClickedHerbSpot(PlayerInteractEvent e) {
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK 
				|| e.getHand() != EquipmentSlot.HAND) return;
		
		Block sand = e.getClickedBlock().getLocation().add(0, -1, 0).getBlock();
		if (e.getClickedBlock().getType() == Material.DEAD_BUSH 
				&& sand.getType() == Material.SAND) {
			Player player = e.getPlayer();
			
			if (player.isOp()) {
				if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.PRISMARINE_CRYSTALS)
				
				if (Main.herbspots.keySet().contains(e.getClickedBlock().getLocation())) {
					Main.herbspots.remove(e.getClickedBlock().getLocation());
					player.sendMessage(ChatColor.RED + "약재 채취 스팟을 삭제했습니다.");
				} else {
					String message = "&f[ &6&l경라원 &f] &e" + e.getClickedBlock().getLocation().toString() + "&f에 &c&l약재 채취 스팟&f 설정을 완료하였습니다!";
					message = message.replaceAll("&", "§");
					
					player.sendMessage(message);
					Main.herbspots.put(e.getClickedBlock().getLocation(), 0);
				}
			} else {
				if (Main.herbspots.keySet().contains(e.getClickedBlock().getLocation())) {
					int leftsec = Main.herbspots.get(e.getClickedBlock().getLocation());
					
					if (leftsec != 0) {
						String message = "&f[ &6&l경라원 &f] &f이런...! &c&l이미 누군가 재배한 약초&f입니다..! &e재생성까지 남은 시간은 &6&l<" + leftsec / 20 + ">초&f입니다!";
						message = message.replaceAll("&", "§");
						
						player.sendMessage(message);
					} else {
						Random r = new Random();
						
						String name = null;
						String rankname = null;
						int rank = 0;
						
						//get random name
						int rname = r.nextInt(100);
						
						if (rname == 0) {
							name = "§6§l산삼";
						} else if (1 <= rname && rname <= 3) {
							name = "§6인삼";
						} else if (4 <= rname && rname <= 10) {
							name = "§9더덕";
						} else if (11 <= rname && rname <= 22) {
							name = "§e도라지";
						} else if (23 <= rname && rname <= 42) {
							name = "§e둥글래";
						} else if (43 <= rname && rname <= 69) {
							name = "§7칡";
						} else if (70 <= rname && rname <= 99) {
							name = "§6감초";
						}
						
						//get random rank
						int rrank = r.nextInt(100);
						
						if (rrank == 0) {
							rankname = "§f[ §41등급 §f]";
							rank = 1;
						} else if (1 <= rrank && rrank <= 4) {
							rankname = "§f[ §c2등급 §f] ";
							rank = 2;
						} else if (5 <= rrank && rrank <= 14) {
							rankname = "§f[ §93등급 §f]";
							rank = 3;
						} else if (15 <= rrank && rrank <= 34) {
							rankname = "§f[ §64등급 §f]";
							rank = 4;
						} else if (35 <= rrank && rrank <= 59) {
							rankname = "§f[ §85등급 §f]";
							rank = 5;
						} else if (60 <= rrank && rrank <= 99) {
							rankname = "§f[ §76등급 §f]";
							rank = 6;
						}
						
						ItemStackManager herb = new ItemStackManager(Material.DEAD_BUSH, rankname + " " + name);
						
						herb.addLore("§c§l> §c§l<" + rank + ">등급§f짜리 §7§l<" + ChatColor.stripColor(name) + ">§f이다.");
						herb.addLore("§c§l> §3§l경라원§f으로 가서 약초를 판매하자!");
						
						player.getInventory().addItem(herb.getItemStack());
						
						player.sendMessage("§f[ §6§l경라원 §f] §c§l<" + rank + ">등급§f짜리 §7§l<" + ChatColor.stripColor(name) + ">§f을 재배하셨습니다!");
						
						Main.herbspots.put(e.getClickedBlock().getLocation(), 3600);
					}
				}
			}
		}
	}
}
