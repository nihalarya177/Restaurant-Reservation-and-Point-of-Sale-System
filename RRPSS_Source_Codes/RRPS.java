import java.util.*;
class RRPS{
    private static MenuBoundary menubound;
    private static OrderBoundary orderbound;
    private static ReservationBoundary resbound;
    public static void main(String args[]){ 
        Print printMgr = new PrintMgr1();
        menubound = new MenuBoundary();
        orderbound = new OrderBoundary(printMgr);
        resbound = new ReservationBoundary(); 
        Restaurant restaurant = new Restaurant();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(choice!=11){
            System.out.println("=======================================\n" + "(1)  View/Create/Update/Remove Menu Item\n" + "(2)  View/Create/Update/Remove Promotion\n"
                + "(3)  Create Order\n" + "(4)  View Order\n" + "(5)  Add/Remove Order Items To/From Order\n" + "(6)  Create Reservation Booking\n" + 
                "(7)  Check/Remove Reservation Booking\n" + "(8)  Check Table Availability\n" + "(9)  Print Order Invoice\n" + "(10) Print Sale Revenue Report by Period\n" + "(11) Quit\n"
                + "=======================================");
            System.out.print("Enter Choice: ");
            choice  = sc.nextInt();
            if(choice<1 || choice>11){
                System.out.println("Please select an option between 1-11");
                continue;
            }
            switch(choice){
                case 1:
                menubound.callItemMgr();
                break;
                case 2:
                menubound.callPromotionMgr();
                break;
                case 3: case 4: case 5:
                orderbound.callOrderMgr(restaurant, choice);
                break;
                case 6: case 7: case 8:
                resbound.callReservationMgr(restaurant, choice);
                break;
                case 9: case 10:
                orderbound.callPrintMgr(restaurant, choice);
                break;
            }
        }
    }
}