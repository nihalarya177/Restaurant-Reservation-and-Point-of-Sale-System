import java.util.ArrayList;

/**
 * Control class for processing Promotions in the menu.
 * Responsible for Display/Create/Update/Remove Promotions in the menu.
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */

public class PromotionMgr {

    /**
     * Instantiate the Menu.
     */
    private MenuDB menu = new MenuDB();

    /**
     * Display all Promotions in the Menu.
     */
    public void showPromotions() {
        // TODO - implement PromotionMgr.showPromotion
        System.out.println("PROMOTIONS");
        System.out.printf("%-2s %-15s %-60s %-5s \n" , "ID", "Promotion", "Items", "Price");
        System.out.println("-- --------------- ------------------------------------------------------------ ----- ");
        for(int i=0;i<menu.getPromotions().size();i++){
            System.out. printf( "%-2d %-15s" , (i+1), menu.getPromotions().get(i).getDesc());
            //System.out.println(menu.promotions.get(i).getArrItem().size());
            //menu.promotions.get(i).getArrItem().forEach(str -> System.out.print(str + " "));
            String s = "";
            for(int j = 0 ; j<menu.getPromotions().get(i).getItemCnt();j++){
                s = s + menu.getPromotions().get(i).getArrItem().get(j).getName() + " , ";
            }
            int l = s.length();
            s = s.substring(0,l-2);

            System.out.printf(" %-60s %-5.2f ",s, menu.getPromotions().get(i).getPrice());
            System.out.println();
        }

    }

    /**
     * Create a new Promotion and add it to the Menu, also update the csv file to store the data.
     * @param items the new Promotion's Items Arraylist.
     * @param desc the new Promotion's name.
     * @param price the new Promotion's price.
     * @param itemCount the new Promotion's item count.
     * @see Promotion
     */
    public void createPromotion(ArrayList<Item> items, String desc,double price, int itemCount) {
        // TODO - implement PromotionMgr.createPromotion
        Promotion promotion = new Promotion(items, desc, price, itemCount);
        menu.getPromotions().add(promotion);
        menu.updateDB();

    }

    /**
     * Update a Promotion in the Menu, also update the csv file to store the data.
     * @param items the Promotion's Items Arraylist.
     * @param desc the Promotion's name.
     * @param price the Promotion's price.
     * @param itemCount the Promotion's item count.
     * @see Promotion
     */
    public void updatePromotion(ArrayList<Item> items,String desc,double price,int itemCount,int id_no) {
        // TODO - implement PromotionMgr.updatePromotion

        menu.getPromotions().get(id_no).setArrItem(items);
        menu.getPromotions().get(id_no).setDesc(desc);
        menu.getPromotions().get(id_no).setPrice(price);
        menu.getPromotions().get(id_no).setItemCnt(itemCount);
        menu.updateDB();

    }

    /**
     * Remove a Promotion from the Menu, also update the csv file to store the data.
     * @param id the Promotion's index in the menu.
     */
    public void removePromotion(int id) {
        // TODO - implement PromotionMgr.removePromotion
        menu.getPromotions().remove(id);
        menu.updateDB();

    }
}