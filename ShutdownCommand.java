package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ShutdownCommand implements Command {
	private final String HELP = "Usage: !shutdown\nShuts down the bot and PMs the event log to the sender. Will only work for admins!";
	
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	public void action(String[] args, MessageReceivedEvent event) {
		logEvent(event);
		if(event.getMember().isOwner()){
			event.getTextChannel().sendTyping().queue();
			event.getTextChannel().sendMessage("Yes sir! Shutting down.").queue();
			event.getAuthor().openPrivateChannel().queue(m -> m.sendMessage(Main.log).queue());
			event.getJDA().shutdown();
		}else{
			event.getTextChannel().sendTyping().queue();
			event.getTextChannel().sendMessage("Nope! Not an admin.").queue();
		}
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
		Main.updateLog(event.getAuthor().getName() + " accessed the shutdown command on: " + time);
	}

}
