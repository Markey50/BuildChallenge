package com.github.Markey50.BuildChallenge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Builder implements CommandExecutor {
	
	String[] defaultMessage = new String[] {
		ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.DARK_GREEN + "BUILDER " + ChatColor.AQUA + "help commands",
		ChatColor.RED + "----------",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder accept " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Accept an invitation to a build challenge",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder decline " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Decline an invitation to a build challenge",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder info " + ChatColor.WHITE + "- " + ChatColor.AQUA + "View information on the current event",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder leave " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Leave the current event",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder seecomments " + ChatColor.WHITE + "- " + ChatColor.AQUA + "View the comments the judges have left you",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder winnings " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Collect your winnings!",
		ChatColor.RED + "----------"
		};

	BuildChallenge plugin;
	public Builder(BuildChallenge instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//TODO Add arguments for buildchallenge.admin tools
		
		if(cmd.getName().equalsIgnoreCase("builder")) {
			
			switch (args.length){
			//check for args length
			
			default:
				sender.sendMessage(defaultMessage);
			break;
			
			case 1: case 2:
				switch (args[0].toLowerCase()){
				
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "help": case "?":
						
						if (args.length == 1) {
							sender.sendMessage(defaultMessage);
						break;
						}
					break;
					
					}
					
				}
			}
			
		
		
		return true;
	}

}
