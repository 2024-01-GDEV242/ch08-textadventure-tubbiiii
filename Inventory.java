import java.util.ArrayList;

/**
 * 
 * This class creates an inventory for the player and for rooms. Players can
 * add, drop, and use items. They can also take items from rooms.
 * 
 */

public class Inventory {
    private ArrayList<item> items;
    private int weightLimit;
    int totalWeight = 0;
    // Constructor
    public Inventory(int weightLimit) {
        this.items = new ArrayList<>();
    }
    
    // Method to get weight total
    public int calculateTotalWeight() {
        
        for (item item : items) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }
    
    // Method to add item to inventory
    public void addItem(item item) {
        if(weightLimit >= totalWeight){
            items.add(item);
            System.out.println(item + " added to inventory.");
        }
        if(weightLimit <= totalWeight){
            System.out.println(item + " is too heavy!.");
        }   
    }

    // Method to remove item from inventory
    public void removeItem(item item) {
        if (items.contains(item)) {
            items.remove(item);
            System.out.println(item + " removed from inventory.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    // Method to check if inventory contains an item
    public boolean hasItem(item item) {
        return items.contains(item);
    }

    // Method to display inventory
    public void displayRoomInventory() {
        if (items.isEmpty()) {
            System.out.println("nothing!");
        } else {
            for (item item : items) {
                System.out.println("a " + item.name);
            }
        }
    }
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (item item : items) {
                System.out.println("- " + item.name);
            }
        }
    }
}
