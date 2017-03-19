package commands;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.projects.discordbot.Command;
import com.projects.discordbot.Main;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * PBot v1.3 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class RolesCommand implements Command {
	private final String HELP = "Usage: !roles\nReturns a list of the roles in this server, as well as a count of how many people are assigned each role.";
	
	public boolean called(String commandArg, String extraArg) {
		if(commandArg.equals("roles")){
			return true;
		}
		return false;
	}

	public void action(String commandArg, String extraArg, MessageReceivedEvent event) {
		logEvent(event);
		List<Role> roles = event.getGuild().getRoles();
		List<Member> members = event.getGuild().getMembers();
		String outputMessage = "Roles:\n";
		for(int i = 0; i < roles.size()-1; ++i){
			int roleMemberCount = 0;
			Role currentRole = roles.get(i);
			for(int j = 0; j < members.size(); ++j){
				Member currentMember = members.get(j);
				if(currentMember.getRoles().contains(currentRole)) {
					++roleMemberCount;
				}
			}
			outputMessage += currentRole.getName() + ": " + roleMemberCount + "\n";
		}
		event.getTextChannel().sendTyping().queue();
		event.getTextChannel().sendMessage(outputMessage).queue();
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
		Main.updateLog(event.getAuthor().getName() + " accessed the roles command on: " + time);
	}

}
