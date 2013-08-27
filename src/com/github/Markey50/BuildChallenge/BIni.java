package com.github.Markey50.BuildChallenge;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class BIni implements CommandExecutor {
	
	String[] menu = new String[] { ChatColor.GREEN + "| " + ChatColor.AQUA };
	
	String[] menu2 = new String[] { ChatColor.WHITE + "- " + ChatColor.AQUA };
	
	String[] defaultMessage = new String[]{
		ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.BLUE + "INITIATOR " + ChatColor.AQUA + "help commands",
		ChatColor.RED + "----------",
		menu + "/bini initiate " + menu2 + "Start a build challenge event",
		menu + "/bini invite <playername> " + menu2 + "Invite a player to the event",
		menu + "/bini invite all " + menu2 + "Invite all players to event",
		menu + "/bini settime <time> <s, m, h> " + menu2 + "Set time limit for build challenge",
		menu + "/bini start " + menu2 + "Officially start the challenge",
		menu + "/bini timechange <new time> <s, m , h> " + menu2 + "Set the timer to the time specified",
		menu + "/bini stop " + menu2 + "Force stop an event",
		menu + "/bini viewjudges " + menu2 + "See list of current judges",
		menu + "/bini invitejudge <player> " + menu2 + "Invite a player to judge",
		menu + "/bini kickjudge <player> " + menu2 + "Kick a player from the judge list",
		menu + "/bini viewvotes " + menu2 + "View the judges votes",
		menu + "/bini comment <cell number> <comment>" + menu2 + "Comment on a cell",
		menu + "/bini vote <cell number>" + menu2 + "Vote on a cell",
		menu + "/bini winners <first> <second> <third>" + menu2 + "Select event winners (by cell number)",
		menu + "/bini transfer <player> " + menu2 + "Transfer initiator role to another approved player",
		ChatColor.RED + "----------"
		};
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA;

	BuildChallenge plugin;
	public BIni(BuildChallenge instance) {
		plugin = instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//Develop argument commands for ./bini 
		if (cmd.getName().equalsIgnoreCase("bini")) {
			
			switch (args.length){
			//check for args length
			
			default:
				sender.sendMessage(defaultMessage);
			break;
			
			case 1: case 2: case 3:
				
				switch (args[0].toLowerCase()) {
				
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "help": case "?":
						
						if (args.length == 1) {
							sender.sendMessage(defaultMessage);
						break;
						}
					break;
					
					case "initiate":
						//Begins the process of starting an event ./bini initiate
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO Choose the arena 
							
							//Broadcast invitation
							Bukkit.getServer().broadcastMessage(AS(header + sender.getName() + "has begun a &fBuildChallenge&b! &e/builder accept &bto join the event!"));
							//TODO Then set timer for time specified. Default to default timer in config
							
							//TODO Then clear arena cells
							
						}else{
							sender.sendMessage(AS(header + "You are not a registered Initiator!"));
						}
					break;
					
					case "invite":
						//invite a player to participate in the event ./bini invite <playername>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//Send a message to specified player asking them to join ./bini invite <playername>
							Bukkit.getPlayer(args[1]).sendMessage(AS(header + "Don't be a square! Come join the fun at the build challenge! &e/build accept &bto join in!"));
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou specify a player name!"));
							}
						}else{
							sender.sendMessage(AS(header + "You are not a registered Initiator!"));
						}
					break;
					
					case "inviteall":
						//invite all players to participate in the event ./bini inviteall
						if(sender.hasPermission("buildchallenge.initiator")) {
							//Send a message to all players asking them to join
							Bukkit.getServer().broadcastMessage(AS(header + "The BuildChallenge is starting soon! Use &e/build accept &bto Join the Challenge!"));
						}else{
							sender.sendMessage(AS(header + "You are not a registered Initiator!"));
						}
					break;
					
					case "settime":
						//Set the timer to the specified time ./bini settime <time> <s, m, h>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO Set a timer to the specified time and pause the timer until start
							
							//TODO If no timer is set, then default to config time limit
							
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initiator!"));
						}
					break;
					
					case "start":
						//Officially start the event. ./bini start
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO teleport players to assigned cell number
							
							//TODO clear inventories for all players on the buildersList
							List <String> buildersList = (plugin.datacore.getStringList("Buildchallenge.List"));
							for (int i = 0; i < buildersList.size();) {
									Player p = (Player) sender;
									PlayerInventory inv = p.getInventory();
									inv.clear();
									ItemStack[] armourContents = {
											new ItemStack(Material.AIR),
											new ItemStack(Material.AIR),
											new ItemStack(Material.AIR),
											new ItemStack(Material.AIR),
									};
									inv.setArmorContents(armourContents);
							}
							
							//TODO start timer
							
							
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initiator!"));
						}
					break;
					
					case "timechange":
						//Reset the timer to the specified time ./bini timechange <time> <s, m ,h>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO Reset timer to specified time, automatically start timer with new time
							
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initiator!"));
						}
					break;
					
					case "stop":
						//force stop the event ./bini stop
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO stop timer, revoke build rights, survival inventory, teleport back to lobby
							
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initiator!"));
						}
					break;
					
					case "viewjudges":
						//view the list of current judges ./bini viewjudges
						if(sender.hasPermission("buildchallenge.initiator")) {
							//Retrieve judgeList and display to sender
							List <String> judgeList = plugin.datacore.getStringList("Judges");
								for (String currentList : judgeList){	
									sender.sendMessage(AS(header + currentList));
								}
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initator!"));
						}
					break;
					
					case "invitejudge":
						//invite a player to judge the current event ./bini invitejudge <playername>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//Send a message to a player inviting them to judge
							Bukkit.getPlayer(args[1]).sendMessage(AS(header + "Come help me please! The BuildChallenge needs a judge! &e/bjudge register &bto join!"));
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou Must specify a player name!"));
							}
						}else{
							sender.sendMessage(AS(header + "You are not a designated initiator!"));
						}
					break;
					
					case "kickjudge":
						//kick a player from the judge the current judge ./bini kickjudge <player>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//remove player name from judgeList
							List <String> judgeList = plugin.datacore.getStringList("Judges");
							judgeList.remove(args[1]);
							plugin.datacore.set("Judges", judgeList);
							plugin.saveYamls();
							sender.sendMessage(AS(header + "successfully &cremoved &b " + (args[1]) + "from judges for this event."));
							if (args.length == 0){
								sender.sendMessage(AS(header + "&cYou must specify a player name!"));
							}
						}else{
							sender.sendMessage(AS(header + "You are not a designated Initiator!"));
						}
					break;
					
					case "viewvotes":
						//view votes list ./bini viewvotes
						if(sender.hasPermission("buildchallenge.initiator")) {
							//Retrieve voteList
							List <String> voteList = plugin.datacore.getStringList("Votes");
							for (String currentList : voteList){	
								sender.sendMessage(AS(header + currentList));
							}
						}
					
					case "winners":
						//Choose the winners of the current event ./bini winners <player1> <player2> <player3>
						if(sender.hasPermission("buildchallenge.initiator")) {
							//TODO take winners 1, 2 and 3 and broadcast the winners in order
							
						}else{
							sender.sendMessage(AS(header + "You are not a designatd Initiator!"));
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