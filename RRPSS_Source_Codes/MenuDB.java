import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent the menu of the restaurant.
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */

public class MenuDB {

    private static final String CSV_FILEPATH = ".\\MenuItemsDB.csv";
    
    /**
     * Arraylist to store the Items in the menu.
     */
    private ArrayList<Item> items = new ArrayList<Item>();

    /**
     * Arraylist to store the Promotions in the menu.
     */
    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();

    /**
     * CLass constructor that create a new Menu with Items and Promotions from csv file that store the menu data.
     */
    public MenuDB() {
        String line = "";
        String[] item_name = new String[50];
        String[] item_type = new String[50];
        String[] item_desc = new String[50];
        double[] item_price = new double[50];


        int totalNoOfitem = 0;
        int totalNoOfPromotion = 0;

        String[] item_detail;
        List<String[]> promotion_detail = new ArrayList<String[]>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(CSV_FILEPATH));
            line = br.readLine();
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                if (!line.contains("Promotion")) {
                    item_detail = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                    item_name[totalNoOfitem] = item_detail[1];
                    item_type[totalNoOfitem] = item_detail[2];
                    item_desc[totalNoOfitem] = item_detail[3];
                    item_price[totalNoOfitem] = Double.parseDouble(item_detail[4]);
                    totalNoOfitem++;
                } else {
                    promotion_detail.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"));
                    totalNoOfPromotion++;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < totalNoOfitem; i++) {
            this.items.add(new Item(ItemType.valueOf(item_type[i].toUpperCase()), item_name[i], item_desc[i], item_price[i]));
        }


        int itemcount = 0;
        for (int i = 0; i < totalNoOfPromotion; i++) {
            ArrayList<Item> temp = new ArrayList<Item>();
            for (int a = 0; a < totalNoOfitem; a++) {
                /*System.out.println("test");
                System.out.println(promotion_detail.get(i)[3].replaceAll("\\d","").trim());
                System.out.println(items.get(a).getName());
                System.out.println("test");*/
                if (promotion_detail.get(i)[3].replaceAll("\\d", "").trim().contains(this.items.get(a).getName())) {
                    temp.add(this.items.get(a));
                    itemcount++;
                }
            }
            this.promotions.add(new Promotion(temp, promotion_detail.get(i)[1], Double.parseDouble(promotion_detail.get(i)[4]), itemcount));
            itemcount = 0;

        }
    }

    /**
     * Update csv file that store the menu data with current Menu.
     * It writes the csv file with current Items and Promotions data in the Menu.
     */
    public void updateDB() {
        try (PrintWriter writer = new PrintWriter(CSV_FILEPATH)) {

            StringBuilder sb = new StringBuilder();
            sb.append("uid,name,category,description,price" + '\n');

            for(int i = 0 ; i < items.size(); i ++){

                        sb.append(String.valueOf(100+i) + ',' +
                        items.get(i).getName() + ',' +
                        items.get(i).getType().name() + ',' +
                        items.get(i).getDesc() + ',' +
                        String.valueOf(items.get(i).getPrice()));
                        sb.append('\n');
            }

            for(int j = 0 ; j < promotions.size(); j++){
                        sb.append(String.valueOf(200+j) + ',' +
                        promotions.get(j).getDesc() + ',' +
                        "Promotion" + ',');
                        sb.append("\"");
                        for(int k = 0 ; k< promotions.get(j).getItemCnt();k++){

                            sb.append(promotions.get(j).getArrItem().get(k).getName());
                            if(k != promotions.get(j).getItemCnt()-1)
                                sb.append(',');
                        }
                        sb.append("\"");
                        sb.append( ',' + String.valueOf(promotions.get(j).getPrice()) + ',');
                        sb.append('\n');
            }


            writer.write(sb.toString());

            writer.flush();
            writer.close();

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * Access the Items of this Menu.
     * @return this Menu's Items.
     */

    public ArrayList<Item> getItems() {
        return this.items;
    }

    /**
     * Access the Promotions of this Menu.
     * @return this Menu's Promotions.
     */

    public ArrayList<Promotion> getPromotions() {
        return this.promotions;
    }
}