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

public class RollCommand implements Command {
	private final String HELP = "Usage: !roll\nRolls a 6-sided dice and returns the result. Add a number to roll more than once (1-100 times).\nFormat: !roll number_here";
	
	public boolean called(String commandArg, String extraArg, MessageReceivedEvent event) {
		if(commandArg.equals("roll")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		Random rng = new Random();
		int diceRoll;
		double total = 0, rollCount, average;
		
		//Check for help argument
		if(extraArg.equalsIgnoreCase("help")){
			help(event);
			return;
		}
		
		//Check for extra argument not existing, if so allow it to pass through the try-catch
		if(extraArg.equals("")) { extraArg = "1"; }
		
		//Check if extra argument is a number
		try{
		    rollCount = Integer.parseInt(extraArg);
		}catch(NumberFormatException e){
		    event.getTextChannel().sendMessage("Invalid argument. Type '!roll' or '!roll number_here' only.").queue();
		    return;
		}
		
		//Check if number is valid
		if(rollCount <= 0 || (rollCount > 100 && rollCount <= Integer.MAX_VALUE)){
		    event.getTextChannel().sendMessage("Invalid number. Enter a number 1-100 to roll that many times.").queue();;
		    return;
		}
		
		//If rollCount is 1 or if there is no specifying extra argument
		if(extraArg.equals("") || rollCount == 1){
		    diceRoll = rng.nextInt(6)+1;
		    event.getTextChannel().sendMessage("You rolled a..." + diceRoll+ ".").queue();
		    return;
		}
		
		//Otherwise, roll and calculate total/average
		for(int i = 0; i < rollCount; ++i){
		    diceRoll = rng.nextInt(6)+1;
		    total += diceRoll;
		}
		
		average = total / rollCount;
		event.getTextChannel().sendMessage("You rolled " + (int)rollCount + " times. **Total:** " + (int)total + " **Average:** " + (int)(average * 100)/100.0).queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the roll command on: " + time);
	}

}
