package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
		//Define cases for the ./builder command
		
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
					
					case "activate":
						
						if (sender.hasPermission("buildchallenge.activate")){							
							if (plugin.config.getBoolean("Buildchallenge.started")){

								sender.sendMessage(AS("The challenge has already started!"));
								break;
							}				
							plugin.config.set("Buildchallenge.started", true);
							break;							
						} else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					case "deactivate":
						
						if (sender.hasPermission("buildchallenge.activate")){							
							if (plugin.config.getBoolean("Buildchallenge.started") == false){

								sender.sendMessage(AS("The challenge has already been stopped!"));
								break;
							}				
							plugin.config.set("Buildchallenge.started", false);
							break;							
						} else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
					
					case "accept":
						//Join the challenge TM
					if (plugin.config.getBoolean("Buildchallenge.started")){
						if (sender.hasPermission("buildchallenge.builder")) {
							//Add player to builders.list
							List <String> builderList = plugin.datacore.getStringList("Initiators");
							builderList.add(sender.getName());
							plugin.datacore.set("Builders", builderList);
							plugin.saveYamls();
							
							//Save current location
							Player p2 = (Player)sender;
							plugin.config.set("Users." + p2 + ".BuildChallenge.World", p2.getLocation().getWorld());
							plugin.config.set("Users." + p2 + "BuildChallenge.X", p2.getLocation().getBlockX());
							plugin.config.set("Users." + p2 + "BuildChallenge.Y", p2.getLocation().getBlockY());
							plugin.config.set("Users." + p2 + "BuildChallenge.Z", p2.getLocation().getBlockZ());
							plugin.config.set("Users." + p2 + "BuildChallenge.Yaw", p2.getLocation().getYaw());
							plugin.config.set("Users." + p2 + "BuildChallenge.Pitch", p2.getLocation().getPitch());
							
							//Save current inventory and clear inventory
							Player p = (Player) sender;
							PlayerInventory inv = p.getInventory();
							ItemStack[] inven = inv.getContents();
							ItemStack[] armour = inv.getArmorContents();
							plugin.getConfig().set("Users." + p.getName() + ".inventory", inven);
							plugin.getConfig().set("Users" + p.getName() + ".armour", armour);
							inv.clear();
							ItemStack[] armourContents = { 
									new ItemStack(Material.AIR),
									new ItemStack(Material.AIR),
									new ItemStack(Material.AIR),
									new ItemStack(Material.AIR)
									};
							inv.setArmorContents(armourContents);
							
							//TODO Retrieve Lobby coords from arenas.yml and teleport player
							
							//TODO Assign cell number
							
							sender.sendMessage(AS(header + "Welcome to &fBuildChallenge!"));
						}else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
					} else {
						sender.sendMessage(AS(header + "&cThe challenge is not currently active!"));
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
						
					if (plugin.config.getBoolean("Buildchallenge.started")){
						if(sender.hasPermission("buildchallenge.builder")) {
							//remove player from buildersList 
							List <String> buildersList = plugin.datacore.getStringList("Builders");
							buildersList.remove(sender.getName());
							plugin.datacore.set("Builders", buildersList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "You have left the event."));
							
							//teleport player back
							Player p2 = (Player)sender;
							World returnWorld = Bukkit.getWorld(plugin.config.getString("Users." + p2 + ".BuildChallenge.World"));
							int X = plugin.config.getInt("Users." + p2 + "BuildChallenge.X");
							int Y = plugin.config.getInt("Users." + p2 + "BuildChallenge.Y");
							int Z = plugin.config.getInt("Users." + p2 + "BuildChallenge.Z");
							int Yaw = plugin.config.getInt("Users." + p2 + "BuildChallenge.Yaw");
							int Pitch = plugin.config.getInt("Users." + p2 + "BuildChallenge.Pitch");
							Location lastLocation = new Location(returnWorld, X, Y, Z, Yaw, Pitch);
							p2.teleport(lastLocation);						
						}else{
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
					} else {
						sender.sendMessage(AS(header + "&cThe challenge is not currently active!"));
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
