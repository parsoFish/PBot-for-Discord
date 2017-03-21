package com.projects.discordbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import commands.EightBallCommand;
import commands.ListCommand;
import commands.LogCommand;
import commands.OwnerCommand;
import commands.PingCommand;
import commands.QuoteCommand;
import commands.RolesCommand;
import commands.RollCommand;
import commands.ShutdownCommand;
import commands.UptimeCommand;
import commands.UsersCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class Main {
	
	private static JDA api;
	public static long UtcTimeInMillis;
	public static String BOT_TOKEN;
	public static final CommandParser parser = new CommandParser();
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static ArrayList<String> whitelist = new ArrayList<String>(), eightBallResponses = new ArrayList<String>(), list = new ArrayList<String>(), quotes = new ArrayList<String>();
	public static Scanner tokenStream = null, whitelistStream = null, eightBallStream = null, listStream = null, quoteStream = null;
	public static PrintWriter writer = null;
	public static File log;
	
	public static void main(String[] args){
	    
		//Open streams and create api object
	    openFiles();
	    
		try{
			api = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(BOT_TOKEN).buildBlocking();
			api.setAutoReconnect(true);
			api.getPresence().setGame(Game.of("v1.5 - !list for commands."));
			UtcTimeInMillis = System.currentTimeMillis() + 14400000;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//Startup tasks: add commands, populate lists, and log startup time
		startup(UtcTimeInMillis);
	}
	
	public static void handleCommand(CommandParser cmd) {
		if(commands.containsKey(cmd.commandArg)) {
			boolean safe = commands.get(cmd.commandArg).called(cmd.commandArg, cmd.extraArg, cmd.event);
			
			if(safe){
				commands.get(cmd.commandArg).action(cmd.commandArg, cmd.extraArg, cmd.event);
			}else{
				cmd.event.getTextChannel().sendMessage("Invalid command! Type !list for a list of available commands.").queue();
			}
		}
	}
	
	public static void openFiles(){
		try{
	        eightBallStream = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\8Ball.txt"));
	        listStream = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\DIscord Bot\\List.txt"));
	        tokenStream = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\Token.txt"));
	        whitelistStream = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\Whitelist.txt"));
	        quoteStream = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\Quotes.txt"));
	        log = new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\Log.txt");
	        writer = new PrintWriter(log);
	        BOT_TOKEN = tokenStream.next().trim();
	    }catch(FileNotFoundException e){
	        e.printStackTrace();
	    }
	}
	
	public static void startup(long UtcTimeInMillis) {
		addCommands();
		populateLists();
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy, 'at' h:mm:ss a 'UTC'");
		String time = sdf.format(UtcTimeInMillis);
		Main.updateLog("Bot started up on: " + time);
	}
	
	public static void addCommands() {
	    commands.put("8ball", new EightBallCommand());
		commands.put("list", new ListCommand());
		commands.put("log", new LogCommand());
		commands.put("owner", new OwnerCommand());
		commands.put("ping", new PingCommand());
		commands.put("quote", new QuoteCommand());
		commands.put("roles", new RolesCommand());
		commands.put("roll", new RollCommand());
		commands.put("shutdown", new ShutdownCommand());
		commands.put("uptime", new UptimeCommand());
		commands.put("users", new UsersCommand());
	}
	
	public static void populateLists() {
	    //set delimiters
		whitelistStream.useDelimiter(",");
		eightBallStream.useDelimiter("\\|");
		listStream.useDelimiter("\\|");
		quoteStream.useDelimiter("\\|");
		
		//8ball responses
	    while(eightBallStream.hasNext()){
	    	eightBallResponses.add(eightBallStream.next().trim());
	    }
	    
	    //Command list
	    while(listStream.hasNext()){
	    	list.add(listStream.next().trim());
	    }
	    
		//Channel whitelist
	    while(whitelistStream.hasNext()){
	        whitelist.add(whitelistStream.next().trim());
	    }
	    
	    //Quotes
	    while(quoteStream.hasNext()){
	    	quotes.add(quoteStream.next().trim());
	    }
	    
	    //Close streams
	    eightBallStream.close();
	    listStream.close();
	    whitelistStream.close();
	    quoteStream.close();
	}
	
	public static void updateLog(String event){
		writer.println(event);
		writer.flush();
	}
}
