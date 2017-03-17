package com.projects.discordbot;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	public void onMessageReceived(MessageReceivedEvent event){
		if(event.getTextChannel().getId().equals("273989023108825090") || event.getTextChannel().getId().equals("291145108953497601")){
			if(event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
				Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));
			}
		}
	}
}
