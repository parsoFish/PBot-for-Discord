package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class QuoteCommand implements Command {
	public final String HELP = "Usage: !quote\nReturns a random quote from a Sprash member.";

	public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
		if(commandArg.equals("quote")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		Random rng = new Random();
		
		if(extraArg.equalsIgnoreCase("help")){
        	help(event);
        	return;
        }
		
		event.getTextChannel().sendMessage(Main.quotes.get(rng.nextInt(Main.quotes.size()))).queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the quote command on: " + time);
	}

}
