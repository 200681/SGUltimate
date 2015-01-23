package net.vduuude.SurvivalGames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.vduuude.SurvivalGames.SurvivalGames;

public class Ping implements CommandExecutor {
	public static SurvivalGames plugin;
	public Ping(SurvivalGames instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		player.sendMessage(ChatColor.RED + "Your ping is " + getPing(player) + "ms!");
		return false;
	}
	public int getPing(Player p) {
		
		CraftPlayer cp = (CraftPlayer) p; 
		EntityPlayer ep = cp.getHandle();
		return ep.ping; 
		}

}
