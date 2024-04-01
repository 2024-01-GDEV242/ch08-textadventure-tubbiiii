import java.util.ArrayList;

/**
 * The Player class represents a player in the game, specifically storing data relating to the player's inventory.
 * It provides methods to add and remove items from the player's inventory, display the inventory, and find items by name.
 * 
 * This class also includes a player-specific inventory and a weight limit for the inventory.
 * 
 * @author Chris Compierchio
 * @version 3/29/24
 */
public class player {
    
    /** The player's inventory */
    public Inventory inventory;
    
    /** The total weight of the player's items */
    public int totalWeight;
    
    /** The weight limit for the player's inventory */
    private int inventoryWeightLimit;
    
    /** The list of items currently owned by the player */
    private ArrayList<item> items;
    
    /**
     * Constructs a Player object with the specified inventory weight limit.
     * 
     * @param inventoryWeightLimit The weight limit for the player's inventory
     */
    public player(int inventoryWeightLimit) {
        this.inventory = new Inventory(inventoryWeightLimit);
        this.inventoryWeightLimit = inventoryWeightLimit;
    }
    
    /**
     * Adds an item to the player's inventory if the total weight of the inventory is within the weight limit.
     * 
     * @param item The item to be added to the player's inventory
     * @return True if the item was successfully added, false otherwise
     */
    public boolean addItemToPlayer(item item) {
        if (inventoryWeightLimit > inventory.totalWeight) {
            inventory.addItem(item);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Removes an item from the player's inventory.
     * 
     * @param item The item to be removed from the player's inventory
     */
    public void removeItemFromPlayer(item item) {
        inventory.removeItem(item);
    }
    
    /**
     * Displays the player's inventory.
     */
    public void displayPlayerInventory() {
        inventory.displayInventory();
    }
    
    /**
     * Finds an item in the player's inventory by its name.
     * 
     * @return The item found, or null if not found
     */
    public item findItemByName(String itemName) {
        return inventory.findItemByName(itemName);
    }
    
    /**
     * Retrieves the weight limit for the player's inventory.
     * 
     * @return The weight limit for the player's inventory
     */
    public int getPlayerWeightLimit() {
        return inventoryWeightLimit;
    }
}
