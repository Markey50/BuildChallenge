package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.Location;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Polygonal2DSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.foundation.World;

public class BAdmin implements CommandExecutor {
	
	String[] menu = new String[] { ChatColor.GREEN + "| " + ChatColor.AQUA  };

	String[] menu2 = new String[] { ChatColor.WHITE + "- " + ChatColor.AQUA };
	
	String[] defaultMessage = new String[] {
		ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.RED + "ADMIN " + ChatColor.AQUA + "help commands",
		ChatColor.RED + "----------",
		menu + "/badmin arenacreate " + menu2 + "Walks through the steps of creating an arena",
		menu + "/badmin setinitiator " + menu2 + "Add a player as a designated event initiator",
		menu + "/badmin reminitiator " + menu2 + "Removes a player from the designated event initiator list",
		ChatColor.RED + "----------"
	};
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA;

	BuildChallenge plugin;
	public BAdmin(BuildChallenge instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Check for arguments and display help menu if no arguments are matched
		if(cmd.getName().equalsIgnoreCase("badmin")) {
			
			switch (args.length){
			//check for args length
			
			default: 
				sender.sendMessage(defaultMessage);
			break;
			
			case 1: case 2: case 3:
				
				switch (args[0].toLowerCase()){
					
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "help": case "?":
						
						if (args.length == 1){
							sender.sendMessage(defaultMessage);
						break;
						}
						
					break;
					
					case "arenacreate":
						//walk user through the steps of creating an arena ./badmin arenacreate
						if (!(sender instanceof Player)) {
							sender.sendMessage(AS(header + "&cThis command can only be run by a player!"));
						} else {
							if(sender.hasPermission("buildchallege.admin")) {
								//TODO Make this arena shit work
								
									//TODO input the amount of cells desired
								
									//TODO Receive input from WorldEdit on size of cell
									WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
									Selection selection = WorldEditPlugin.getSelection(Player);
									
									if (selection != null) {
										World world = selection.getWorld();
										Location min = selection.getMinimumPoint();
										Location max = selection.getMaximumPoint();
										
										//TODO Use WorldGaurd to define region
										
									} else if (selection instanceof CuboidSelection) {
										CuboidSelection cuboid = (CuboidSelection) selection;
										
										//TODO Use WorldGuard to define region
										
									} else if (selection instanceof Polygonal2DSelection) {
										Polygonal2DSelection polygon = (Polygonal2DSelection) selection;
										List<BlockVector2D>points = getNativePoints();
										
										for (BlockVector2D point : points) {
											double x = point.getX();
											double z = point.getZ();
											
											//TODO Use WorldGuard to define region
											
										}
									} else {
										sender.sendMessage(AS("&cYou must select a valid region!"));
									}
								
									//TODO Set teleport point in center of cell
								
									//TODO Define a lobby area
								
									//TODO Name the arena
									
							}else {
								sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));							
							}
							break;
						}
						
					case "setinitiator":
						//sets a user as an authorized initiator ./badmin setinitiator <playername>
						if (sender.hasPermission("buildchallenge.admin")) {
							List <String> initiatorList = plugin.datacore.getStringList("Initiators");
							initiatorList.add(args[1]);
							plugin.datacore.set("Initiators", initiatorList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "&bSuccessfully &aadded &b" + (args[1]) + " to initiator list."));
							//TODO Add buildchallenge.initiator permission to specified player
							
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou must specify a player name!"));
							}
						}else {	
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
						
					break;
						
					case "reminitiator":
						//remove a user from the authorized initiator list ./badmin reminitiator <playername>
						if (sender.hasPermission("buildchallenge.admin")) {
							//Remove a name from initiatorList
							List <String> initiatorList = plugin.datacore.getStringList("Initiators");
							initiatorList.remove(args[1]);
							plugin.datacore.set("Initiators", initiatorList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "Successfuly &cremoved &b " + (args[1]) + "from initiator list."));
							//TODO Remove buildchallenge.initiator permission from specified player
							
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou must specify a player name!"));
							}
						}else {
							sender.sendMessage(AS(header + "&cYou do not have permission to do this!"));
						}
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

