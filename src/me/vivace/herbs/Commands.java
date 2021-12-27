package me.vivace.herbs;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener, CommandExecutor {
	
	public String cmd1 = "����";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase(cmd1)) {
			
			if (!sender.isOp() || !(sender instanceof Player)) return false;
			if (args.length == 0) return false;
			
			Player admin = (Player) sender;
		
			if (args[0].equalsIgnoreCase("����")) {
				
				String message = "&f[ &6&l���� &f] &c&l���� ä�� ����&f�� ��Ŭ���� �ּ���!";
				message = message.replace("&", "��");
				
				admin.sendMessage(message);
				
				return true;
				
			} else if (args[0].equalsIgnoreCase("���")) {
				String s = "";
				
				for (Location l : Main.herbspots.keySet()) {
					s += l.toString() + "\n";
				}
				
				admin.sendMessage(s);
				
				return true;
			}
			
			return true;
		}
		
		return false;
	}
}
