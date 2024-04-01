import java.util.ArrayList;

/**
 * The Inventory class creates an inventory for players and rooms, allowing addition, removal, and usage of items.
 * Players can add, drop, and use items, as well as take items from rooms.
 * 
 * This class keeps track of the items in the inventory, calculates the total weight of the items, and provides
 * methods to add, remove, and check for items in the inventory.
 * 
 * This class also provides methods to find items by name and display the contents of the inventory.
 * 
 * It interacts with the Game class to access methods for checking items.
 * 
 * @author Chris Compierchio
 * @version 3/29/24
 */
public class Inventory {
    
    /** The list of items in the inventory */
    private ArrayList<item> items;
    
    /** The weight limit for the inventory */
    public int weightLimit;
    
    /** The total weight of the items in the inventory */
    public int totalWeight = 0;
    
    /**
     * Constructs an Inventory object with the specified weight limit.
     * 
     * @param weightLimit The weight limit for the inventory
     */
    public Inventory(int weightLimit) {
        this.items = new ArrayList<>();
        this.weightLimit = weightLimit;
    }
    
    /**
     * Calculates the total weight of the items in the inventory.
     * 
     * @return The total weight of the items in the inventory
     */
    public int calculateTotalWeight() {
        /** The total weight of the items in the inventory */
        totalWeight = 0;
        for (item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
    
    /**
     * Adds an item to the inventory if the total weight is within the weight limit.
     * 
     * @param item The item to be added to the inventory
     */
    public void addItem(item item) {
        if (weightLimit >= totalWeight) {
            items.add(item);
            String itemName = Game.checkItem(item); // Access Game method
            System.out.println("- " + itemName + " added to inventory.");
        }
    }
    /**
     * Adds an item to the inventory if the total weight is within the weight limit. only to be used when adding items to rooms
     * 
     * @param item The item to be added to the inventory
     */
    public void addItemToRoom(item item) {
        if (weightLimit >= totalWeight) {
            items.add(item);
            String itemName = Game.checkItem(item); // Access Game method
            
        }
    }
    /**
     * Removes an item from the inventory.
     * 
     * @param item The item to be removed from the inventory
     */
    public void removeItem(item item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }
    /**
     * Removes an item from the inventory. only to be used when removing items from rooms
     * 
     * @param item The item to be removed from the inventory
     */
    public void removeItemFromRoom(item item) {
        if (items.contains(item)) {
            items.remove(item);
        } else {
            System.out.println("Item is not in room.");
        }
    }
    /**
     * Checks if the inventory contains a specific item.
     * 
     * @param item The item to be checked in the inventory
     * @return True if the item is found in the inventory, false otherwise
     */
    public boolean hasItem(item item) {
        return items.contains(item);
    }
    
    /**
     * Finds an item in the inventory by its name.
     * 
     * @param itemName The name of the item to be found
     * @return The item found, or null if not found
     */
    public item findItemByName(String itemName) {
        for (item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null; // Item not found
    }
    
    /**
     * Displays the contents of the room's inventory.
     */
    public void displayRoomInventory() {
        if (items.isEmpty()) {
            System.out.println("Nothing!");
        } else {
            for (item item : items) {
                String itemName = Game.checkItem(item); // Access Game method
                System.out.println("- " + itemName);
            }
        }
    }
    
    /**
     * Displays the contents of the player's inventory.
     */
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (item item : items) {
                String itemName = Game.checkItem(item); // Access Game method
                System.out.println("- " + itemName);
            }
        }
    }
}
