package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class ListCommand implements Command {
	private final String HELP = "Usage: !list\nReturns the list of available commands.\n";
	
	public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
		if(commandArg.equals("list")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		String output = "";
		
		if(extraArg.equalsIgnoreCase("help")){
        	help(event);
        	return;
        }
		
		for(int i = 0; i < Main.list.size(); ++i){
			output += "\n" + Main.list.get(i);
		}
		
		event.getTextChannel().sendMessage(output).queue();
	}

	public void help(MessageReceivedEvent event) {
		event.getTextChannel().sendMessage(HELP).queue();
	}

	public void executed(boolean success, MessageReceivedEvent event) {
		return;
	}

	public void logEvent(MessageReceivedEvent event) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("M/dd/yyyy, h:mm:ss a 'UTC'");
		OffsetDateTime messageTime = event.getMessage().getCreationTime();
		String time = messageTime.format(format);
		Main.updateLog(event.getAuthor().getName() + " accessed the list command on: " + time);
	}

}
