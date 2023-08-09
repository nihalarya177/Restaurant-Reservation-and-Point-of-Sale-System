/**
 * Control class for processing Items in the menu.
 * Responsible for Display/Create/Update/Remove Items in the menu.
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */
public class ItemMgr {

    /**
     * Instantiate the Menu.
     */
    private MenuDB menu = new MenuDB();

    /**
     * Display all Items in the Menu.
     */

    public void showItems() {
        System.out.println("ITEMS");
        System.out.printf("%-2s %-12s %-24s %-50s %-5s\n", "ID", "Item Type", "Item Name", "Item Description", "Price");
        System.out.println("-- ------------ ------------------------ -------------------------------------------------- -----");
        for(int i=0;i<menu.getItems().size();i++){
            System.out.printf( "%-2d %-12s %-24s %-50s %-5s \n" , (i+1), menu.getItems().get(i).getType(), menu.getItems().get(i).getName(),
                menu.getItems().get(i).getDesc() , menu.getItems().get(i).getPrice());
        }

    }

    /**
     * Create a new Item and add it to the Menu, also update the csv file to store the data.
     * @param type the new Item's category type.
     * @param name the new Item's name.
     * @param desc the new Item's description.
     * @param price the new Item's price.
     * @see Item
     */

    public void createItem(String type,String name,String desc,double price) {
        Item c_item = new Item(ItemType.valueOf(type.toUpperCase()),name,desc,price);
        for(int i = 0 ; i < menu.getItems().size(); i++){
            if (c_item.getType().equals(menu.getItems().get(i).getType())){
                menu.getItems().add(i,c_item);
                break;
            }
        }
        menu.updateDB();
    }

    /**
     * Update an Item in the Menu, also update the csv file to store the data.
     * @param id the Item's index.
     * @param name the Item's name.
     * @param desc the Item's description.
     * @param price the Item's price.
     * @see Item
     */

    public void updateItem(int id , String name ,String desc , double price){
        menu.getItems().get(id).setName(name);
        menu.getItems().get(id).setDesc(desc);
        menu.getItems().get(id).setPrice(price);
        menu.updateDB();

    }
    /**
     * Remove an Item from the Menu, also update the csv file to store the data.
     * @param id the Item's index in the menu.
     */
    public void removeItem(int id){
        menu.getItems().remove(id);
        menu.updateDB();

    }

    /**
     * Gets the menu.
     */
    public MenuDB getMenu() {
        return this.menu;
    }
}