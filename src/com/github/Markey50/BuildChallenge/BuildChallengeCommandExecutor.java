package com.github.Markey50.BuildChallenge;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BuildChallengeCommandExecutor implements CommandExecutor {
	
	String header = ChatColor.WHITE + "BuildChallenge " + ChatColor.DARK_BLUE + "// ";
	
	String[] defaultMessage = new String[]{
		ChatColor.WHITE + "Build Challege " + ChatColor.DARK_BLUE + "// " + ChatColor.AQUA + "Help Menu",
		ChatColor.RED + "----------",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/build help admin " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Admin help commands",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/build help initiator " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Event initiator help commands",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/build help judge " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Judge help commands",
		ChatColor.GREEN + "| " + ChatColor.AQUA + "/build help builder " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Player help commands",
		ChatColor.RED + "----------",
		ChatColor.DARK_GRAY + "BuildChallenge plugin coded by " + ChatColor.DARK_RED + "M" + ChatColor.GOLD + "a" + ChatColor.YELLOW + "r" + ChatColor.GREEN + "k" + ChatColor.AQUA + "e" + ChatColor.DARK_BLUE + "y" + ChatColor.DARK_PURPLE + "5" + ChatColor.RED + "0"
		};
	
	
	BuildChallenge plugin;
	public BuildChallengeCommandExecutor(BuildChallenge instance){
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("build")) {
			
			switch (args.length){
			// check for args length
			
			default:
				sender.sendMessage(defaultMessage);			
			break;
			
			case 1: case 2:
				// for length of 1/2
				switch (args[0].toLowerCase()){ // check for args after the base command
					
					default: 			
						sender.sendMessage(defaultMessage);
					break;
						
					case "help": case "?":
						
						if (args.length == 1){		
							sender.sendMessage(defaultMessage);
							break;
						}
					
					switch (args[1].toLowerCase()){
					
					default:
						sender.sendMessage(defaultMessage);
					break;
					
					case "admin":
					//displays admin help menu
						sender.sendMessage(new String[] {
								header + ChatColor.RED + "ADMIN " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/badmin arenacreate " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Walks through the steps of creating an arena",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/badmin setinitiator " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Add a player as a designated event initiator",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/badmin reminitiator " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Removes a player from the designated event initiator list",
								ChatColor.RED + "----------"
								});
					break;
					
					case "initiator":
					//displays initiator/moderator help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.BLUE + "INITIATOR " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini initiate " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Start a build challenge event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini invite <playername> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Invite a player to the event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini inviteall " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Invite all players to event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini settime <time> <s, m, h> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Set time limit for build challenge",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini start " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Officially start the challenge",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini timechange <new time> <s, m , h> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Set the timer to the time specified",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini stop " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Force stop an event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini viewjudges " + ChatColor.WHITE + "- " + ChatColor.AQUA + "See list of current judges",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini invitejudge <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Invite a player to judge",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini kickjudge <player> " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Kick a player from the judge list",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bini winners <first> <second> <third>" + ChatColor.WHITE + "- " + ChatColor.AQUA + "Select event winners (by cell number)",
								ChatColor.RED + "----------"
								});
						break;
						
					case "judge":
					//displays judges help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.GOLD + "INITIATOR " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge register " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Registers you as a judge for the current event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge resign " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Resign from being a judge for the current event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/bjudge vote <cell number> " + ChatColor.WHITE + "-  " + ChatColor.AQUA + "Vote for a cell",
								ChatColor.RED + "----------"
								});
					break;
					
					case "builder":
					//displays general builders help menu
						sender.sendMessage(new String[] {
								ChatColor.WHITE + "Build Challenge " + ChatColor.DARK_BLUE + "// " + ChatColor.DARK_GREEN + "BUILDER " + ChatColor.AQUA + "help commands",
								ChatColor.RED + "----------",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder accept " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Accept an invitation to a build challenge",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder decline " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Decline an invitation to a build challenge",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder info " + ChatColor.WHITE + "- " + ChatColor.AQUA + "View information on the current event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder leave " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Leave the current event",
								ChatColor.GREEN + "| " + ChatColor.AQUA + "/builder winnings " + ChatColor.WHITE + "- " + ChatColor.AQUA + "Collect your winnings!",
								ChatColor.RED + "----------"
								});
					break;
					}
				}
			break;
			}
		}
		return true;
	}
}
