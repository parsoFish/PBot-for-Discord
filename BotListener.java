package com.projects.discordbot;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
			Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));
		}
	}
	
	public void onReady(ReadyEvent event){
		//Main.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());
	}
}
