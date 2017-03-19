package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.3 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class QuoteCommand implements Command {
	public final String HELP = "Usage: !quote\nReturns a random quote from a Sprash member.";

	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("quote")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		Random rng = new Random();
		ArrayList<String> quotes = new ArrayList<String>();
		Scanner input = null;
		
		//Open file
		try{
			input = new Scanner(new File("C:\\Users\\Sean\\Desktop\\Programming\\Eclipse\\Discord Bot\\Quotes.txt"));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		//Delimiter set to quote to throw away start-of-file encoding
		input.useDelimiter("\"");
		input.next();
		
		//Delimiter set to comma, and add trimmed quotes
		input.useDelimiter("\\|");
		while(input.hasNext()){
			quotes.add(input.next().trim());
		}
		input.close();
		
		//Get random quote
		event.getTextChannel().sendMessage(quotes.get(rng.nextInt(quotes.size()))).queue();
	}

	public String help() {
		return HELP;
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
