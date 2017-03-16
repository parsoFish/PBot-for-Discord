package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class UptimeCommand implements Command {
	private final String HELP = "Usage: !uptime\nDisplays the time the bot started up, and how much time has passed since then.";
	
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		logEvent(event);
		long currentUtcTimeInMillis = System.currentTimeMillis() + 14400000;
		long elapsedTime = currentUtcTimeInMillis - Main.UtcTimeInMillis;
		event.getTextChannel().sendMessage("Bot has been up for: " + elapsedTime/3600000 + " hours, " + elapsedTime/60000 + " minutes, and " + elapsedTime/1000 + " seconds.").queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the uptime command on: " + time);
	}

}
