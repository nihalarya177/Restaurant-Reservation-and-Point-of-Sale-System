

import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.text.*;
/**
 * Calls ReservationMgr with the choice inputted by the user
 *
 * @author Samarth Agarwal
 * @version 1.0
 * @since 2021-11-12
 */
public class ReservationBoundary
{
    // instance variables - replace the example below with your own
    private ReservationMgr reservationMgr;

    /**
     * Calls appropriate methods in ReservationMgr based off the input made
     * by the user
     * @param restaurant The restaurant object being used with the tables instantiated
     * @param choice The number inputted by the user
     */
    public void callReservationMgr(Restaurant restaurant, int choice)
    {
        // initialise instance variables
        reservationMgr = new ReservationMgr();
        Scanner sc = new Scanner(System.in);
        switch(choice) {
            case 6: // create reservation
                //sc.nextLine();
                System.out.println("Enter customer's name: ");
                String custName = sc.nextLine();
                int contact;
                do {
                    try {
                        System.out.println("Enter contact: ");
                        contact = sc.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Enter number(s) for contact");
                    }
                    sc.nextLine(); // clears the buffer
                } while (true);            
                int pax;
                do {
                    try {
                        System.out.println("Enter pax: ");
                        pax = sc.nextInt();
                        if(pax>10){
                            System.out.println("Larget table can support 10 pax, please enter a number <=10");
                            System.out.println("Enter pax: ");
                            pax = sc.nextInt();
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Enter number(s) for pax");
                    }
                    sc.nextLine(); // clears the buffer
                } while (true);    
                sc.nextLine();
                SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy"); 
                System.out.println("Please enter the date (dd-MM-yyyy): ");
                String input = sc.nextLine();
                Date date;
                while(true){
                    try {
                        date = ft.parse(input); 
                        Calendar c = Calendar.getInstance();
                        c.setTime(ft.parse(input));
                        c.add(Calendar.DATE, 1);  // number of days to add
                        Date dt = (c.getTime());  // dt is now the new date
                        while(true){
                            if(dt.before(new Date())){
                                System.out.println("Please enter a date in the future, format: (dd-MM-yyyy): ");
                                input = sc.nextLine();
                                date = ft.parse(input); 
                                c = Calendar.getInstance();
                                c.setTime(ft.parse(input));
                                c.add(Calendar.DATE, 1);  // number of days to add
                                dt = (c.getTime());  // dt is now the new date 
                            }else{
                                break;
                            }
                        }
                        break;
                    } catch (ParseException e) { 
                        System.out.println("Please enter the date based off the format");
                        input = sc.nextLine();
                    }
                }
                System.out.println("Please enter the time (h:mm a) where a is pm/am: ");
                String inputTime = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");; 
                Date time;
                while(true){
                    try {
                        time = sdf.parse(inputTime); 
                        Calendar cal = Calendar.getInstance(); // creates calendar
                        cal.setTime(date);
                        Calendar calTime = Calendar.getInstance();
                        calTime.setTime(time);               // sets calendar time/date
                        cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                        cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                        Date newTime = cal.getTime();
                        if(newTime.before(new Date())){
                            System.out.println("Please enter a time in the future, format: (h:mm a) where a is pm/am: ");
                            inputTime = sc.nextLine();
                            time = sdf.parse(inputTime); 
                            cal = Calendar.getInstance(); // creates calendar
                            cal.setTime(date);
                            calTime = Calendar.getInstance();
                            calTime.setTime(time);               // sets calendar time/date
                            cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                            cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                            newTime = cal.getTime();
                        }else{
                            break;
                        }
                        break;
                    } catch (ParseException e) { 
                        System.out.println("Please enter the time based off the format given hh:mm aa");
                        inputTime = sc.nextLine();
                    }
                }
                reservationMgr.createReservation(restaurant, custName, pax, contact,date, time);
                break;
            case 7: // check/remove
                int option = 0;
                while(option!=3){
                    System.out.println("=================================\n"+
                        "(1) Check Reservation Booking\n" +
                        "(2) Remove Reservation Booking\n" +
                        "(3) Quit\n"
                        + "================================="
                    );
                    option = sc.nextInt();
                    switch(option){
                        case 1:
                            sc.nextLine();
                            System.out.println("Enter customer's name");
                            custName = sc.nextLine();
                            reservationMgr.showReservation(restaurant, custName);
                            break;
                        case 2: 
                            sc.nextLine();
                            System.out.println("Enter customer's name");
                            custName = sc.nextLine();
                            reservationMgr.removeReservation(restaurant, custName);
                            break;
                    }
                }
                break;

            case 8: 
                reservationMgr.showTableAvailability(restaurant);
                break;
        }
    }
}

