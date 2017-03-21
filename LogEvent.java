package com.projects.discordbot;

/*
 * PBot v1.5 by Phoenix
 * For use in Discord server "The Cute Squad" only
 */

public class LogEvent {
	private String event;
	
	public LogEvent(){
		event = "";
	}
	
	public LogEvent(String event){
		setEvent(event);
	}
	
	public String getEvent(){
		return event;
	}
	
	public void setEvent(String event){
		this.event = event;
	}
}
