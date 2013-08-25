package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Builder implements CommandExecutor {
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA;
	
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
					
					case "accept":
						//Join the challenge TM
						if (sender.hasPermission("buildchallenge.builder")) {
							//Add player to builders.list
							List <String> builderList = plugin.datacore.getStringList("Initiators");
							builderList.add(sender.getName());
							plugin.datacore.set("Builders", builderList);
							
							//Save current location
							
							//teleport to lobby
							
							plugin.saveYamls();sender.sendMessage(AS(header + "Welcome to &fBuildChallenge!"));
						}else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					break;
						
					case "info":
						//view information on the current event
						if(sender.hasPermission("buildchallenge.builder")) {
							//Retrieve info and display to user
							List <String> infoList = plugin.datacore.getStringList("info");
								for (String currentList : infoList){
									sender.sendMessage(AS(header + "Current Event Information"));
									sender.sendMessage(currentList);
								}
						}else{
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					break;
						
					case "leave":
						if(sender.hasPermission("buildchallenge.builder")) {
							//remove player from buildersList 
							List <String> buildersList = plugin.datacore.getStringList("Builders");
							buildersList.remove(sender.getName());
							plugin.datacore.set("Builders", buildersList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "You have left the event."));
						}else{
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					break;
					
					case "winnings":
						
					break;
					
					}
					
				}
			}
		
		return true;
	}
	
	public String AS(String textToBeTranslated){
		
		textToBeTranslated = ChatColor.translateAlternateColorCodes('&', textToBeTranslated);
		return textToBeTranslated;
		
	}

}
