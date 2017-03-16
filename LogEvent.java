package com.projects.discordbot;

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
