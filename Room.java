import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Chris Compierchio
 * @version 3/29/24
 */

public class Room 
{
    private String description;
    private ArrayList<item> items;
    public String name;
    public Inventory inventory;
    private HashMap<String, Room> exits;        // stores exits of this room.
    item item;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * @param name The rooms name, used in referencing in other classes
     */
    public Room(String description, String name, int inventoryWeightLimit) 
    {
        this.description = description;
        this.name = name;
        this.inventory = new Inventory(inventoryWeightLimit);
        exits = new HashMap<>();
    }
    
     public void addItemToRoom(item item) {
        inventory.addItem(item);
    }
     public void removeItemFromRoom(item item) {
        inventory.removeItem(item);
    }
    public void displayRoomInventory() {
        System.out.println("You see...");
        inventory.displayRoomInventory();
        }
        public boolean roomContainsItem(item item) {
        return inventory.hasItem(item);
    }
     /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    public String getOfficeDescription()
    {
        return "You are " + description + ".\n" + "Exits: west";
    }
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
     public String getName() {
        return name;
    }
}

