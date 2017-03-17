package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RollCommand implements Command {
	private final String HELP = "Usage: !roll\nRolls a 6-sided dice and returns the result.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("roll")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		Random rng = new Random();
		int diceRoll = rng.nextInt(6)+1;
		event.getTextChannel().sendTyping().queue();
		event.getTextChannel().sendMessage("You rolled a..." + diceRoll + "!").queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the roll command on: " + time);
	}

}
