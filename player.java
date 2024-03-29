import java.util.ArrayList;
/**
 * specifically stores data relating to the player's inventory
 *
 * @author Chris Compierchio
 * @version 3/29/24
 */
public class player
{
    // instance variables - replace the example below with your own
    
    private Inventory inventory;

    /**
     * Constructor for objects of class player
     */
    public player(int inventoryWeightLimit)
    {
        this.inventory = new Inventory(inventoryWeightLimit);
    }
    public void addItemToPlayer(item item) {
        inventory.addItem(item);
    }
     public void removeItemFromPlayer(item item) {
        inventory.removeItem(item);
    }
}
