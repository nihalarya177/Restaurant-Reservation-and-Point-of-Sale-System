import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Acts as an interface to the RRPSS to handle order-related functionalities
 * @author Ang Boon Leng
 * @version 1.0
 * @since 2021-11-12
 */
public class OrderBoundary {

    /**
     * the Print manager class to use, chosen in RRPSS
     */
    private Print p;
    private OrderMgr orderMgr;
    /**
     * Constructor
     * @param p the object of Print interface
     */
    public OrderBoundary (Print p) {
        this.p = p;
    }
    /**
     * This method asks the user to input a date
     * @return the date specified by the user
     */
    private Date inputDate() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy"); 
        String input;
        Date date;
        while(true){
            input = sc.nextLine();
            try {
                date = ft.parse(input); 
                System.out.println(date);
                return date;
            } catch (ParseException e) { 
                System.out.println("Please enter the date based off the format");
            }
        }
    }
    
    /**
     * This method asks the user for the specific order-related functionality and calls the appropriate method in Order control class
     * Functionalities include: create, show order; add, remove menu items from an order
     * @param restaurant the restaurant object to access tables, staffs, and orders
     * @param choice the choice of functionality entered by the user
     */
    public void callOrderMgr(Restaurant restaurant, int choice) {
        Scanner sc = new Scanner(System.in);
        orderMgr = new OrderMgr();
        ReservationMgr reservationMgr = new ReservationMgr();
        switch (choice) {
            case 3: // create order
            Date date = new Date();

            reservationMgr.showTableAvailability(restaurant);
            System.out.println("(enter 0 to quit)");
            System.out.print("Choose an available Table ID: ");
            int tableId;
            while (true) { // ensure user chooses table that is not reserved
                tableId = sc.nextInt();
                try {
                    if (tableId == 0) {
                        return;
                    }
                    if (!restaurant.getTables().get(tableId-1).getIsReserved()) {
                        break; 
                    }
                    System.out.print("Table has been reserved. Please try again: ");
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Invalid choice! Please try again: ");
                }
            }
            // set the chosen table to be reserved
            restaurant.getTables().get(tableId-1).setIsReserved(true);

            System.out.println("Choose Staff ID: ");
            int i=1;
            System.out.println("(0) Create new Staff");
            for (Staff staff : restaurant.getStaffs()) {
                System.out.printf("(%d) ID %d\n", i, staff.getEmployeeID());
                i++;
            }
            int staffId, inputId;
            while (true) {
                inputId = sc.nextInt() - 1;
                if (inputId == -1) {
                    sc.nextLine();
                    System.out.print("Enter Staff name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Staff gender: ");
                    String gender = sc.nextLine();

                    System.out.print("Enter Staff ID: ");
                    staffId = sc.nextInt();
                    while (true) {
                        try {
                            System.out.print("Enter Staff ID: ");
                            staffId = sc.nextInt();
                            sc.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.print("Invalid Staff ID. Please try again: ");
                            sc.nextLine(); // clears the buffer
                        }
                    }

                    System.out.print("Enter Staff job title: ");
                    String jobTitle = sc.nextLine();
                    restaurant.addStaff(name, gender, staffId, jobTitle);
                    System.out.printf("Staff ID %d created successfully\n\n", staffId);
                    break;
                }
                try {
                    staffId = restaurant.getStaffs().get(inputId).getEmployeeID();
                    break;
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Invalid choice! Please try again: ");
                }
            }

            System.out.println("Does customer have membership?\n" + "(1) Yes\n" + "(2) No");
            int yn;
            while (true) {
                yn = sc.nextInt();
                if (yn == 1 || yn == 2) {
                    break;
                }
                System.out.print("Invalid choice! Please try again: ");
            }
            boolean isMember = false;
            if (yn == 1) isMember = true;

            orderMgr.createOrder(restaurant, date, tableId, staffId, isMember);
            System.out.println("Order created successfully");
            return;

            case 4: // view order
            orderMgr.showOrder(restaurant);
            return;

            case 5:
            int option=0;
            while(option!=3){
                System.out.println("=================================\n" + "(1) Add menu items to order\n" + "(2) Remove menu items from order\n" + "(3) Quit\n"
                    + "=================================");
                option  = sc.nextInt();
                switch(option){
                    case 1:
                    // add item/promotion to order
                    orderMgr.addToOrder(restaurant);
                    break;
                    case 2:
                    // remove items from order
                    orderMgr.removeFromOrder(restaurant);
                    break;
                }
            }

        }
    }

    /**
     * This method asks the user for the specific print-related functionality and calls the appropriate method in Print control class
     * @param restaurant the restaurant object to be used by the control class
     * @param choice the choice of functionality entered by the user
     */
    public void callPrintMgr(Restaurant restaurant, int choice) {
        Scanner sc = new Scanner(System.in);
        orderMgr = new OrderMgr();
        switch (choice) {
            case 9:
            System.out.println("Please select an order: ");
            Order order;
            while (true) {
            	order = orderMgr.selectOrder(restaurant);
            	if (order == null) {
            		// System.out.println(">> No order created <<");
            		return;
            	}
            	else if (!order.getIsComplete()) break; // only allow user to choose uncompleted order
            	System.out.println("Order is already completed. Please try again.");
            }
            p.print(order, restaurant);
            return;

            case 10:
            System.out.println("Please enter the start date (dd-MM-yyyy): ");
            Date startDate = inputDate();
            System.out.println("Please enter the end date (dd-MM-yyyy): ");
            Date endDate = inputDate();
            p.print(startDate, endDate, restaurant);
            return;
        }
    }
}
