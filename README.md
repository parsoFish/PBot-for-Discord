# PBot-for-Discord v1.5
Discord bot created by Phoenix for general usage.

Currently only used in "The Cute Squad" Discord server for general commands.

**Current list of commands:**<br />
!8ball - Responds with an 8-ball type response.<br />
!list - Returns the list of available commands.<br />
!log - **Admin Only** Sends a PM of events that have occurred since the bot's startup.<br />
!owner - Returns the nickname of the owner of the server.<br />
!ping - Returns Pong! if successful, as well as the time the ping request was sent.<br />
!quote - Returns a random quote from a Sprash member.<br />
!roles - Returns a list of the roles in this server, as well as a count of how many people are assigned each role.<br />
!roll - Rolls a 6-sided dice and returns the result. Add a number to roll more than once (1-100 times).<br />
!shutdown - **Admin Only** Shuts down the bot and PMs the event log to the sender.<br />
!uptime - Displays how much time has passed since the bot last started up.<br />
!users - Returns the number of users in the server.<br />
<br />
<br />
# **Technical overview of the bot's code**

Via a listener class, each message sent in #bot-stuff or #testing-bot is picked up if it begins with !. The message is then parsed and compared with the list of commands known to the bot. If it matches, the action() method of the command is called and the command is ran. 
<br />
<br />
<br />
Request any commands you feel like, I get a block of creativity very often and need suggestions on where to go next :3
