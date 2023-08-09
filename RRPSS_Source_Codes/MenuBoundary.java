import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Boundary class for interacting with the Menu.
 * It accesses the functions of ItemMgr and PromotionMgr.
 * @see ItemMgr
 * @see PromotionMgr
 *
 *
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */
public class MenuBoundary {


    /**
     * Instantiate the Menu Item control class.
     */
    private ItemMgr itemMgr;

    /**
     * Instantiate the Menu Promotion control class.
     */
    private PromotionMgr promotionMgr;

    /**
     * Access the Menu Item control class.
       * To Display/Create/Update/Remove Items in the menu.
     * @see ItemMgr
     */
    public void callItemMgr(){
        itemMgr = new ItemMgr();
        int choice = 0;
        Scanner sc = new Scanner(System.in);

        int id_no;
        String desc,type,name;
        double price;
        boolean track = false;

        while(choice != 5){
            System.out.println("================================="+
                    "\n(1) View Menu\n" +
                    "(2) Create New Item\n" +
                    "(3) Update Item\n" +
                    "(4) Remove Item\n" +
                    "(5) Quit\n"
                    + "================================="
            );
            System.out.print("\nEnter Choice: ");
            choice= sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch(choice) {
                case 1:
                    System.out.println("MENU");
                    itemMgr.showItems();
                    
                    break;
                case 2:

                    System.out.println("Creating New Item...");
                    do {
                        try {
                            System.out.print("Enter Item Type: ");
                            type = sc.nextLine();
                            ItemType.valueOf(type.toUpperCase());
                            break;
                        } catch (Exception e) {
                            System.out.print("Invalid type.\n");
                        }
                    } while (true);

                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Item Desc: ");
                    desc = sc.nextLine();

                    do {
                        try {
                            System.out.print("Enter Item Price: ");
                            price = sc.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Please enter a valid number.\n");
                        }
                        sc.nextLine();
                    } while (true);

                    itemMgr.createItem(type, name, desc, price);
                    System.out.println("Item created and added to the menu");
                    break;

                case 3:
                    itemMgr.showItems();
                    System.out.println();


                    do{
                        try {
                            System.out.print("Enter Item No. to update: ");
                            id_no = sc.nextInt() - 1;
                            sc.nextLine();
                            itemMgr.getMenu().getItems().get(id_no);
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter a valid number.\n");
                        }
                    } while(true);

                    System.out.println("Updating the Item....");
                    System.out.print("Enter Item Name to be update: ");
                    name = sc.nextLine();
                    System.out.print("Enter Item Desc to be update: ");
                    desc = sc.nextLine();

                    do {
                        try {
                            System.out.print("Enter Item Price to be update: ");
                            price = sc.nextDouble();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Please enter a valid number. \n");
                        }
                        sc.nextLine();
                    } while (true);

                    itemMgr.updateItem(id_no,name,desc,price);
                    System.out.println("Item updated successfully");
                    break;


                case 4:
                    itemMgr.showItems();
                    System.out.println();


                    do {
                        try {
                            System.out.print("Enter Item No. to be removed: ");
                            id_no = sc.nextInt() - 1;
                            sc.nextLine();
                            itemMgr.getMenu().getItems().get(id_no);
                            break;
                        } catch (Exception e) {
                            System.out.print("Please enter a valid number. \n");
                        }
                    } while (true);

                    itemMgr.removeItem(id_no);
                    System.out.println("Selected Item has been deleted...");

                    break;
                case 5:
                    break;
                default:
                    System.out.println("PLease enter a valid choice");
            }

        }
    }

    /**
     * Access the Menu Promotion control class.
     * To Display/Create/Update/Remove Promotions in the menu.
     * @see PromotionMgr
     */
    public void callPromotionMgr() {
        promotionMgr = new PromotionMgr();
        itemMgr = new ItemMgr();

        int choice = 0;
        Scanner sc = new Scanner(System.in);


        int id_no;
        String desc;
        double price;

        while (choice != 5) {
            System.out.println("================================="+
                    "\n(1) View Promotion\n" +
                    "(2) Create New Promotion\n" +
                    "(3) Update Promotion\n" +
                    "(4) Remove Promotion\n" +
                    "(5) Quit\n"
                    + "================================="
            );
            System.out.print("\nEnter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    promotionMgr.showPromotions();
                    break;
                case 2:
                    System.out.println("Creating new Promotion...");
                    itemMgr.showItems();
                    System.out.println();

                    int itemCount;
                    int[] id = new int[20];
                    do {
                        try {
                            System.out.print("Enter Promotion Item Count: ");
                            itemCount = sc.nextInt();
                            break;

                        } catch (InputMismatchException e) {
                            System.out.print("Please enter a valid number. \n");
                        }
                        sc.nextLine();
                    } while (true);
                    
                    for (int i = 0; i < itemCount; i++) {

                        while(true) {
                            System.out.printf("Enter No. %d Item ID to include into the package: ", i+1);
                            id[i] = sc.nextInt() - 1;
                            sc.nextLine();
                            if(id[i] <itemMgr.getMenu().getItems().size()){
                                break;
                            }
                            else{
                                System.out.println("Enter the valid number.\n");
                            }
                        }
                    }

                    ArrayList<Item> temp_items = new ArrayList<Item>();
                    for (int i = 0; i < itemCount; i++) {
                        temp_items.add(itemMgr.getMenu().getItems().get(id[i]));
                    }

                    System.out.print("Enter Promotion description: ");
                    desc = sc.nextLine();

                    do {
                        try {
                            System.out.print("Enter Promotion Price: ");
                            price = sc.nextDouble();

                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Please enter a valid number. \n");
                        }
                        sc.nextLine();
                    } while (true);



                    promotionMgr.createPromotion(temp_items, desc, price, itemCount);
                    System.out.println("Promotion created and added to the menu");
                    break;
                case 3:
                    promotionMgr.showPromotions();
                    System.out.println();

                    while(true) {
                        System.out.print("Enter Promotion No. to update: ");
                        id_no = sc.nextInt() - 1;
                        sc.nextLine();
                        if (id_no < itemMgr.getMenu().getItems().size()) {
                            break;
                        } else {
                            System.out.println("Enter a valid number.\n");
                        }
                    }
                        System.out.println("Updating the promotion...");


                        do {
                            try {
                                System.out.print("Enter Promotion Item Count: ");
                                itemCount = sc.nextInt();
                                break;

                            } catch (InputMismatchException e) {
                                System.out.print("Please enter a valid number. \n");
                            }
                            sc.nextLine();
                        } while (true);

                        int[] id2 = new int[20];
                        
                        System.out.println();
                        itemMgr.showItems();
                        System.out.println();

                        for (int i = 0; i < itemCount; i++) {
                            
                            while (true) {
                                System.out.printf("Enter No. %d Item ID to include into the package: ", i + 1);
                                id2[i] = sc.nextInt() - 1;
                                sc.nextLine();
                                if (id2[i] < itemMgr.getMenu().getItems().size()) {
                                    break;
                                } else {
                                    System.out.println("Enter a valid number.\n");
                                }
                            }

                        }

                        ArrayList<Item> temp_items2 = new ArrayList<Item>();
                        for (int i = 0; i < itemCount; i++) {
                            temp_items2.add(itemMgr.getMenu().getItems().get(id2[i]));
                        }

                        System.out.print("Enter Promotion description: ");
                        desc = sc.nextLine();
                        do {
                            try {
                                System.out.print("Enter Promotion Price: ");
                                price = sc.nextDouble();

                                break;
                            } catch (InputMismatchException e) {
                                System.out.print("Please enter a valid number. \n");
                            }
                            sc.nextLine();

                        } while (true);

                        promotionMgr.updatePromotion(temp_items2, desc, price, itemCount, id_no);
                        System.out.println("Promotion has been updated");
                        break;
                        case 4:
                            promotionMgr.showPromotions();
                            System.out.print("\nEnter Promotion No. to be removed: ");

                            do {
                                try {
                                    //System.out.print("Enter Promotion No. to be removed: ");
                                    id_no = sc.nextInt() - 1;
                                    sc.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.print("Please enter a valid number. \n");
                                }
                            } while (true);

                            promotionMgr.removePromotion(id_no);
                            System.out.println("Selected promotion has been deleted...");
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Please enter a valid choice");

            }
        }
    }
}