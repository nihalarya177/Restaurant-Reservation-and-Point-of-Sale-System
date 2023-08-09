import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author bharat
 * @version 1.0
 * @since 2021-11-12
 */


public class PrintMgr1 implements Print
{

    String dateType = " EEE dd/MM/yyyy hh:mm a";
    SimpleDateFormat date = new SimpleDateFormat(dateType);
    String dateHeader = date.format(new Date());
    static final String line = "------------------------------------------------------";
    static final String billHeader = ""
        +"                                                 \n"
        +"                  RESTAURANT NAME                \n"
        +"                  restaurant address             \n"
        +"                  telephone no.                  \n"
        +"                  check no.                      \n";

    static final String billFooter=""
        +"    Bill inclusive of 7% GST and 10% Service charge\n"
        +"         Member are entitled to a 5% discount      \n"
        +"                                                 \n"
        +"                       THANK YOU!                   \n";
    static final String ItemHeader = " QTY    ITEM                                   PRICE";

    /**
     * This method is used to print a formatted order invoice
     * for a Restaurant Reservation and Point of Sale system.
     * @param order is an Order object that specifies the order to be printed.
     * @param restaurant is a Restaurant object that specifies the Restaurant
     * 		  which the orders are obtained from. 
     */

    public void print(Order order, Restaurant restaurant) { 

        System.out.println( billHeader);
        System.out.println( dateHeader);
        System.out.println( " Table no. " + order.getTableId());
        System.out.println( line);
        System.out.println( ItemHeader);
        System.out.println( line);
        printOrderItems(order);
        printOrderPromotions(order);
        System.out.println( line);
        double subtotal = calculateSubtotal(order);
        double tax = calculateTaxes(subtotal);
        double discount = calculateDiscount(order, subtotal);
        System.out.printf("%-36s %s %-4.2f\n","", "SUBTOTAL:",subtotal );
        System.out.printf("%-39s %s %-4.2f\n","", "TAXES:", tax);
        System.out.printf("%-36s %s %-4.2f\n","", "DISCOUNT:",discount );
        System.out.println( line);
        System.out.printf("%-39s %s %-4.2f\n","", "TOTAL:",subtotal+tax-discount );
        System.out.println( line);
        System.out.println( line);
        System.out.println( billFooter);

        cancelReservation(order, restaurant);

        order.setIsComplete(true);
    }

    /**
     * This method is used to calculate the total revenue and print a 
     * formatted Sales Report for a Restaurant during a specified time period.
     * @param startDate is a Date object to indicate the date which the Sales report should start from.
     * @param endDate is a Date object to indicate the date which the Sales report should end.
     * @param restaurant is a Restaurant object that specifies the Restaurant
     * 		  which the orders are obtained from.
     */

    public void print(Date startDate, Date endDate, Restaurant restaurant) {    
        ArrayList<String> itemName = new ArrayList<String>();
        ArrayList<Integer> itemQty = new ArrayList<Integer>();
        ArrayList<Double> itemPrice = new ArrayList<Double>();
        double memberDiscount = 0;

        String dateStart = date.format(startDate);
        String dateEnd = date.format(endDate);

        System.out.println("SALES REPORT");
        System.out.println("From: " + dateStart);
        System.out.println("To  : " + dateEnd);
        System.out.println( line);
        System.out.println( ItemHeader);
        System.out.println( line);

        for(Order order :restaurant.getOrders()) 
        {
            Date orderDate = order.getDate();
            if(orderDate.compareTo(startDate)>=0 && orderDate.compareTo(endDate)<=0) {

                if (order.getOrderItems().size() != 0) 
                {
                    if(order.getIsMember()==true) {
                        memberDiscount += calculateDiscount(order, calculateSubtotal(order));
                    }
                    int i = 0;
                    for (Item item : order.getOrderItems()) 
                    {
                        if(itemName.contains(item.getName())) {
                            int qty = itemName.indexOf(item.getName());
                            itemQty.set(qty, ( itemQty.get(qty) + order.getQtyItems().get(i) ) );
                            itemPrice.set(qty, ( itemPrice.get(qty) + (order.getQtyItems().get(i) * item.getPrice()) ));
                        }
                        else {
                            itemName.add(item.getName());
                            itemQty.add(order.getQtyItems().get(i));
                            itemPrice.add(order.getQtyItems().get(i) * item.getPrice());
                        }

                        i++;
                    } 
                }
            }
        }

        for(Order order :restaurant.getOrders()) 
        {
            Date orderDate = order.getDate();
            if(orderDate.compareTo(startDate)>=0 && orderDate.compareTo(endDate)<=0) {

                if (order.getOrderItems().size() != 0) 
                {
                    int i = 0;
                    for (Promotion promo : order.getOrderPromotions()) 
                    {
                        if(itemName.contains(promo.getDesc())) {
                            int qty = itemName.indexOf(promo.getDesc());
                            itemQty.set(qty, ( itemQty.get(qty) + order.getQtyPromotions().get(i) ) );
                            itemPrice.set(qty, ( itemPrice.get(qty) + (order.getQtyPromotions().get(i) * promo.getPrice()) ));
                        }
                        else {
                            itemName.add(promo.getDesc());
                            itemQty.add(order.getQtyPromotions().get(i));
                            itemPrice.add(order.getQtyPromotions().get(i) * promo.getPrice());
                        }

                        i++;
                    } 
                }
            }
        }

        int i=0;
        for (String name : itemName) {
            System.out.printf(" %-4d %-40s %.2f\n", itemQty.get(i), name, itemPrice.get(i) );
            i++;
        }
        double total = 0;
        int j=0;
        while (itemPrice.size()>j) {
            total+=itemPrice.get(j);
            j++;
        }
        System.out.println( line);
        System.out.printf("%-39s %s %-4.2f\n","", "TOTAL:", total);
        System.out.printf("%-30s %s %-4.2f\n","", "TOTAL DISCOUNT:", memberDiscount);
        System.out.println( line);
        System.out.printf("%-31s %s %-4.2f\n","", "TOTAL REVENUE:", total-memberDiscount);
        System.out.println( line);
        System.out.println( line);

		
    }

    /**
     * This method is used by the print method to format all the individual
     * items in an order
     * @param order is an Order object that specifies the order to print items from.
     *
     */

    private static void printOrderItems(Order order) {
        if (order.getOrderItems().size() == 0) {
            System.out.println("");
        } else {
            int i = 0;
            for (Item item : order.getOrderItems()) {
                System.out.printf(" %-4d %-40s %.2f\n", order.getQtyItems().get(i), item.getName(), ( item.getPrice() * order.getQtyItems().get(i)) );
                i++;
            }
        }
    }

    /**
     * This method is used by the print method to format all the individual
     * promotions in an order
     * @param order is an Order object that specifies the order to print promotions from.
     */

    private static void printOrderPromotions(Order order) {
        if (order.getOrderPromotions().size() == 0) {
            System.out.println("");
        } else {
            int i = 0;
            for (Promotion promo : order.getOrderPromotions()) {
                System.out.printf(" %-4d %-40s %.2f\n", order.getQtyPromotions().get(i), promo.getDesc(), ( promo.getPrice() * order.getQtyPromotions().get(i) ));
                i++;
            }
        }
    }

    /**
     * This method is used to calculate a subtotal( before taxes ) for a given order.
     * @param order is an Order object that specifies the order to calculate subtotal for.
     * @return The subtotal( before taxes ) for the order passed to it.  
     */

    private static double calculateSubtotal(Order order) {
        double subtotal = 0;
        if (order.getOrderItems().size() == 0) {
            subtotal+=0;
        } 
        else {
            int i = 0;
            for (Item item : order.getOrderItems()) {
                subtotal += ( item.getPrice()* order.getQtyItems().get(i) );
                i++;
            }
        }

        if (order.getOrderPromotions().size() == 0) {
            subtotal+=0;
        } 
        else {
            int j=0;
            for (Promotion promo : order.getOrderPromotions()) {
                subtotal += promo.getPrice()* order.getQtyPromotions().get(j);
                j++;
            }
        }

        return subtotal;

    }

    /**
     * This method calculates a fixed tax for a value passed to it
     * @param value is a double that taxes are calculated for.
     * @return The total taxes for the given numerical value passed to it 
     */

    private static double calculateTaxes(double value ) {
        double taxes = (value * (1+GST) * (1+SERVICECHARGE));
        return taxes-value;
    }

    /**
     * This method checks whether the customer placing the order has a membership
     * calculates the fixed discount they receive accordingly 
     * @param order an Order object that specifies the order to calculate discount for.
     * @param value is a double which is the value to calculate a discount for
     * @return The total discount for a given numerical value passed to it
     */

    private static double calculateDiscount(Order order, double value) {
        if (order.getIsMember()==false) {
            return 0;
        }
        else {
            return value*MEMBERDISCOUNT;
        }

    }

    /**
     * This method is used to free a table when an order invoice is printed.
     * @param order an Order object that specifies the order. 
     * @param restaurant is a Restaurant object.
     */

    private void cancelReservation(Order order, Restaurant restaurant) {

        int table_id = order.getTableId();
        ArrayList<Table> arrTables = restaurant.getTables();
        int x = 0;
        for (Table table : arrTables) {
            if(table.getTableId() == table_id) {
            	table.setIsReserved(false);
                arrTables.set(x, table);
            	ArrayList<Reservation> arrReservation =  table.getReservation();
            	for(int x1 = 0; x1< arrReservation.size(); x1++){
                    Reservation reservation = arrReservation.get(x1);
                    Date reservedDate = reservation.getReserveDate();
                    Date reservedTime = reservation.getReserveTime();
            	Calendar cal = Calendar.getInstance(); // creates calendar
                cal.setTime(reservedDate);
                Calendar calTime = Calendar.getInstance();
                calTime.setTime(reservedTime);  
                cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                Date reservationDateTime = cal.getTime();
                Date currentDateTime = new Date();
                if(((reservationDateTime.before(currentDateTime)))){
                	reservation.removeReservation();
                }
                }
                
            }
            x++;
        }
        restaurant.setTables(arrTables);
    }


}
