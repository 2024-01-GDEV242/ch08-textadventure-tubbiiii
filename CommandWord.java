/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 *@author  Chris Compierchio
 * @version 3/29/24
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    GO("go"), QUIT("quit"), HELP("help"), HEALTH("health"), DAMAGE ("damage"),
    INVENTORY("inventory"), UNKNOWN("?"), TALK("talk"), USE("use"), LOOK("look"),
    TAKE("take"), DROP("drop"), EAT("eat");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
