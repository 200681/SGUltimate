package net.vduuude.SurvivalGames.commands;

import net.vduuude.SurvivalGames.GameState;
import net.vduuude.SurvivalGames.SurvivalGames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {
	public static SurvivalGames plugin;
	
	public Vote(SurvivalGames instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)  {
		Player player = (Player) sender;

		if(args.length==1) {
		if(plugin.getState()==GameState.Lobby) {
		plugin.votemanager.addVote(Integer.parseInt(args[0]), player);
		} else {
		}
	}
		
		if(args.length==0) {
			player.sendMessage(ChatColor.AQUA + "Voting Options:");
			if(plugin.votemanager.hmaps.get(1)!=null) {
				player.sendMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) + ChatColor.DARK_GRAY + " : " + ChatColor.GRAY + plugin.votemanager.hmaps.get(1) + " votes");
			} else {
				player.sendMessage(ChatColor.RED + "1. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(1) + ".name" ) +  ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

			}
			if(plugin.votemanager.hmaps.get(2)!=null) {
				player.sendMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY  + plugin.votemanager.hmaps.get(2) + " votes");
			} else {
				player.sendMessage(ChatColor.RED + "2. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(2) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

			}
			if(plugin.votemanager.hmaps.get(3)!=null) {
				player.sendMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +plugin.votemanager.hmaps.get(3) + " votes");
			} else {
				player.sendMessage(ChatColor.RED + "3. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(3) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + "0" + " votes");

			}
			if(plugin.votemanager.hmaps.get(4)!=null) {
				player.sendMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " +ChatColor.GRAY + plugin.votemanager.hmaps.get(4) + " votes");
			} else {
				player.sendMessage(ChatColor.RED + "4. " + plugin.getConfig().getString("Arenas." + plugin.votemanager.arenavotelist.get(4) + ".name" ) +ChatColor.DARK_GRAY + " : " + ChatColor.GRAY +"0" + " votes");

			}
		
	}
		return false;
	}
}
