/**********************************************************************************************
 * @file : Main.java
 * @description : Creates a list of items available in a store and prints a receipt based on
 *                which items are added to the cart. Exception handling included for missing
 *                items and invalid inputs.
 * @author : Ella Shipman
 * @date : January 28, 2025
 *********************************************************************************************/

import java.util.ArrayList;

public class Main {
    public void main(String[] args) {
        Item[] store = setUpStore();
        ArrayList<Item> currentCart = createCart(store, args);
        printReceiptOrder(currentCart);
        emptyCartReverseOrder(currentCart);
    }

    /*  --------------------------------------------------------------------------------------
     *   setUpSore - returns an array of Items that can be found in the store
     */
    public Item[] setUpStore() {
        //Create array and add elements
        Item[] itemsInStore = new Item[5];
        itemsInStore[0] = new Item("Merino Yarn", 23.50);
        itemsInStore[1] = new Item("Cotton Yarn", 7.50);
        itemsInStore[2] = new Item("Silk Yarn  ", 20.75);
        itemsInStore[3] = new Item("Bamboo Yarn", 13.50);
        itemsInStore[4] = new Item("Linen Yarn ", 10.25);

        //Return array
        return itemsInStore;
    }
    //  --------------------------------------------------------------------------------------

    /*  --------------------------------------------------------------------------------------
     *   createCart - returns an arraylist of Items currently in the cart
     *   itemsInStore : an array of the items available for purchase
     */
    public ArrayList<Item> createCart (Item[] itemsInStore, String[] args) {
        //Create  array list of cart items
        ArrayList<Item> cart = new ArrayList<>();

        //Check to see how many items will be added to cart
        int howManyTotalItems = -1;
        try {
            howManyTotalItems = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Your cart is empty!");
            System.exit(0);
        } catch (NumberFormatException e2) {
            System.out.println("You cannot have \"" + args[0] + "\" items in your cart.");
            System.exit(0);
        }
        if (args.length != (howManyTotalItems + 1)) {
            System.out.println("You are missing items from your cart!");
        }

        //Process and add items from args
        for (int i = 1; i <= howManyTotalItems; i++) {
            //Identify item
            int currentItem;
            try {
                currentItem = Integer.parseInt(args[i]);
                cart.add(itemsInStore[currentItem]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("The store does not have an item at index " + args[i] + ".");
            } catch (NumberFormatException e2) {
                System.out.println("\"" + args[i] + "\" is not an accepted integer.");
            }
        }

        //Return array list
        return cart;
    }
    //  --------------------------------------------------------------------------------------

    /*  --------------------------------------------------------------------------------------
     *   printReceiptOrder - prints a receipt of all Items, prices, subtotal, tax, and total
     *   cart : list of items currently in the cart
     */
    public void printReceiptOrder(ArrayList<Item> cart) {
        //Establishing values
        double subtotal = 0.0;
        double total;

        //Print header
        System.out.println("Receipt");
        System.out.println("=========================");
        System.out.println("Item                Price");

        //Print all items and add to subtotal
        for (int i = 0; i < cart.size(); i++) {
            System.out.print(cart.get(i).getItemName());
            System.out.print("         ");
            System.out.printf("%5.2f\n", cart.get(i).getItemPrice());

            subtotal += cart.get(i).getItemPrice();
        }
        System.out.println("=========================");

        //Calculate total
        total = subtotal * 1.05;

        //Printing subtotal, tax, and total
        System.out.print("(a) Subtotal: ");
        System.out.printf("%.2f\n", subtotal);
        System.out.println("(b) Sales Tax: 5%");
        System.out.print("(c) Total: ");
        System.out.printf("%.2f\n", total);
    }
    //  --------------------------------------------------------------------------------------

    /*  --------------------------------------------------------------------------------------
     *   emptyCartReverseOrder - Displays and removes all Items in the cart in reverse order
     *   cart : list of items currently in the cart
     */
    public void emptyCartReverseOrder(ArrayList<Item> cart) {
        System.out.println("Removing all items from the cart in reverse order...");

        for (int i = (cart.size() -1); i >= 0; i--) {
            System.out.println("Removing: " + cart.get(i).getItemName());
            cart.remove(i);
        }

        System.out.println("Your cart has been emptied.");
    }
    //  --------------------------------------------------------------------------------------
}