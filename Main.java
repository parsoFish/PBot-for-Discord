package com.projects.discordbot;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import commands.ListCommand;
import commands.LogCommand;
import commands.OwnerCommand;
import commands.PingCommand;
import commands.RolesCommand;
import commands.RollCommand;
import commands.ShutdownCommand;
import commands.UptimeCommand;
import commands.UsersCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main {
	
	private static JDA api;
	public static String log = "";
	public static long UtcTimeInMillis;
	public static final String BOT_TOKEN = "MjkxMTIyOTY4NjUyODA4MTkz.C6nvKw.MljyPLS6rc9P0V6munTQXEGn7e4";
	public static final CommandParser parser = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	
	public static void main(String[] args){
		try{
			api = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(BOT_TOKEN).buildBlocking();
			api.setAutoReconnect(true);
			UtcTimeInMillis = System.currentTimeMillis() + 14400000;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Startup tasks: add commands and log startup time
		startup(UtcTimeInMillis);
	}
	
	public static void handleCommand(CommandParser cmd) {
		if(commands.containsKey(cmd.commandArg)) {
			boolean safe = commands.get(cmd.commandArg).called(cmd.commandArg, cmd.extraArg);
			
			if(safe){
				commands.get(cmd.commandArg).action(cmd.commandArg, cmd.extraArg, cmd.event);
			}else{
				cmd.event.getTextChannel().sendMessage("Invalid command! Type !list for a list of available commands.").queue();
			}
		}
	}
	
	public static void updateLog(String event){
		log += "\n" + event;
	}
	
	public static void startup(long UtcTimeInMillis) {
		addCommands();
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy, 'at' h:mm:ss a 'UTC'");
		String time = sdf.format(UtcTimeInMillis);
		Main.updateLog("Bot started up on: " + time);
	}
	
	public static void addCommands() {
		commands.put("list", new ListCommand());
		commands.put("log", new LogCommand());
		commands.put("owner", new OwnerCommand());
		commands.put("ping", new PingCommand());
		commands.put("roles", new RolesCommand());
		commands.put("roll", new RollCommand());
		commands.put("shutdown", new ShutdownCommand());
		commands.put("uptime", new UptimeCommand());
		commands.put("users", new UsersCommand());
	}
}
