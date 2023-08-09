

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;  
import java.util.ArrayList;

/**
 * Manages the Reservations made in the Restaurant
 * A restaruant has multipls tables which can have multiple reservations
 *
 * @author SamarthAgarwal
 * @version 1.0
 * @since 2021-11-12
 */
public class ReservationMgr
{
    /**
     * Creates a reservation and adds it to the first available table.
     * pax should be smaller than or equal to table capacity
     * if a table has reservations, it is only available if the new reservation
     * is atleast 2 hours after the previous reservations 
     * @param restaurant The restaurant object being used with the tables instantiated
     * @param custName The customer name for whom the reservation is being made
     * @param pax number of people in the party
     * @param contact The contact of the customer
     * @param reservedDate the date at which the table will be occupied
     * @param reservedTime the time at which the table will be occupied
     */
    public void createReservation(Restaurant restaurant, String custName,int pax, int contact, Date reservedDate, Date reservedTime)
    {
        // initialise instance variables
        Reservation reservationObj;
        if(getTableAvailability(restaurant,  reservedDate, reservedTime, pax) != null){
            reservationObj = new Reservation(custName, pax, contact, reservedDate, reservedTime);
            ArrayList<Table> arrTables = restaurant.getTables();
            Table table = getTableAvailability(restaurant,  reservedDate, reservedTime, pax);
            //table.setIsReserved(true);
            ArrayList<Reservation> arrReservations = table.getReservation();
            arrReservations.add(reservationObj);
            table.setReservation(arrReservations);
            for(int i = 0; i < arrTables.size(); i++){
                if(arrTables.get(i).getTableId() == table.getTableId())
                    arrTables.set(i,table);
            }
            restaurant.setTables(arrTables);
            System.out.println("Reservation created successfully");
        }
        else{
            System.out.println("Tables are full/reserved cannot create reservation");
        }

    }
    /**
     * Shows the reservation made by the customer
     * @param restaurant The restaurant object being used with the tables instantiated
     * @param custName The customer name for whom the reservation is being shown
     */
    public void showReservation(Restaurant restaurant, String custName)
    {
        // put your code here
        ArrayList<Table> arrTables = restaurant.getTables();
        for(int x = 0; x<arrTables.size(); x++){
            ArrayList<Reservation> arrReservations = arrTables.get(x).getReservation();
            for(int i = 0; i < arrReservations.size(); i++){
                if((arrReservations.get(i).getName()).toLowerCase().equals(custName.toLowerCase())){
                    System.out.println("Reservation found!");
                    Reservation customer = arrReservations.get(i);
                    SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy"); 
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                    String date = ft.format(customer.getReserveDate());
                    String time = sdf.format(customer.getReserveTime());
                    System.out.println("Details are as follows\n"+ 
                        "name: " + customer.getName() + "\ncontact: " + customer.getContact() + "\npax: " + customer.getPax() + "\nDate: " + date + "\nTime: " + time);
                    return;
                }
            }
        }
        System.out.println("Reservation not found!");
    }
    /**
     * Removes the reservation made by the customer
     * @param restaurant The restaurant object being used with the tables instantiated
     * @param custName The customer name for whom the reservation is being removed
     */
    public void removeReservation(Restaurant restaurant, String custName){
        ArrayList<Table> arrTables = restaurant.getTables();
        for(int x = 0; x<arrTables.size(); x++){
            ArrayList<Reservation> arrReservations = arrTables.get(x).getReservation();
            for(int i = 0; i<arrReservations.size(); i++){
                if((arrReservations.get(i).getName()).toLowerCase().equals(custName.toLowerCase()) ){

                    arrReservations.get(i).removeReservation();
                    arrReservations.remove(i);
                    arrTables.get(x).setReservation(arrReservations);
                    System.out.println("Reservation removed!");
                    restaurant.setTables(arrTables);
                    return;
                }
            }
        }
         System.out.println("Reservation not found");
        
    }
    /**
     * Shows the tables and the reservations, if any, at that table two hours from current time
     * @param restaurant The restaurant object being used with the tables instantiated
     */
    public void showTableAvailability(Restaurant restaurant){
        ArrayList<Table> arrTables = restaurant.getTables();
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date());               // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 2);      // adds one hour
        Date datePlusTwo = cal.getTime();      // returns new date object plus one hour
        int countTablesReserved = 0;

        System.out.println("Tables Availability are as follows:");
        for(int i = 0; i<arrTables.size(); i++){
            ArrayList<Reservation> arrReservation = arrTables.get(i).getReservation();
            if(arrReservation.size()==0){
            	if(arrTables.get(i).getIsReserved()== true)
                	System.out.println("Order has been placed for " + "Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
            	else
                System.out.println("Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
            }
            else{
                for(int x = 0; x< arrReservation.size(); x++){
                    Reservation reservation = arrReservation.get(x);
                    Date reservedDate = reservation.getReserveDate();
                    Date reservedTime = reservation.getReserveTime();
                    if(reservedTime==null) {
                        if(arrTables.get(i).getIsReserved()== true)
                        	System.out.println("Order has been placed for " + "Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
                        else
                        System.out.println("Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
                    }
                    else{
                        cal = Calendar.getInstance(); // creates calendar
                        cal.setTime(reservedDate);
                        Calendar calTime = Calendar.getInstance();
                        calTime.setTime(reservedTime);  
                        cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                        cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                        Date reservationDateTime = cal.getTime();
                        cal.add(Calendar.HOUR_OF_DAY, 2);      // adds one hour
                        Date resPlusTwo = cal.getTime();      // returns new date object plus one hour
                        Date currentDateTime = new Date();
                        String custName= reservation.getName();
                        if(arrTables.get(i).getIsReserved()== true)
                        	System.out.println("Order has been placed for " + "Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
                        else if(resPlusTwo.before(currentDateTime)  || resPlusTwo.equals(currentDateTime)) {
                        	System.out.println("Reservation Removed for Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
                        	removeReservation(restaurant, reservation.getName());
                        }
                        else if(!((reservationDateTime.after(currentDateTime) && reservationDateTime.before(datePlusTwo)))){
                        	
                            System.out.println("Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap());
                        }
                        
                        else{ 
                            System.out.println("Table id: "+ arrTables.get(i).getTableId() + " Table capacity: " + arrTables.get(i).getSeatingCap() + " Table reserved for " + reservationDateTime + " for " + custName);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("No other tables are empty");
    }
    /**
     * Checs which tables have seating capacity greater than the pax of the
     * people at the party and checks whether there is an existing reservation
     * two hours within that time frame
     * @param restaurant The restaurant object being used with the tables instantiated
     * @param reservationDate The date at which the reservation will be made
     * @param reservationTime the time at which the reservation will be made
     * @param pax the number of people in the party
     * @return Table the table at which the reservation will be made
     */
    private Table getTableAvailability(Restaurant restaurant, Date reservationDate, Date reservationTime, int pax){
        ArrayList<Table> arrTables = restaurant.getTables();
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(reservationDate);
        Calendar calTime = Calendar.getInstance();
        calTime.setTime(reservationTime);  
        cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
        cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
        
        Date reservationDateTime = cal.getTime();
        cal.add(Calendar.HOUR_OF_DAY, 2);      // adds one hour
        
        Date resDatePlusTwo = cal.getTime(); 
        for(int i = 0; i<arrTables.size(); i++){
        	if(arrTables.get(i).getIsReserved() == false) {
            ArrayList<Reservation> arrReservation = arrTables.get(i).getReservation();
            if(arrReservation.size()==0 && pax <=  arrTables.get(i).getSeatingCap()){

                return arrTables.get(i);
            }
            for(int x = 0; x< arrReservation.size(); x++){
                Reservation reservation = arrReservation.get(x);
                Date reservedDate = reservation.getReserveDate();
                Date reservedTime = reservation.getReserveTime();
                
                if(reservedTime==null && pax <=  arrTables.get(i).getSeatingCap()){
                    return arrTables.get(i);
                }
                else{
                    cal = Calendar.getInstance(); // creates calendar
                    cal.setTime(reservedDate);
                    calTime.setTime(reservedTime);               // sets calendar time/date
                    cal.add(Calendar.HOUR_OF_DAY, calTime.get(Calendar.HOUR_OF_DAY));
                    cal.add(Calendar.MINUTE, calTime.get(Calendar.MINUTE));
                    reservedTime = cal.getTime();
                    cal.add(Calendar.HOUR_OF_DAY, 2);      // adds one hour
                    
                    Date datePlusTwo = cal.getTime(); 
                    if(pax <=  arrTables.get(i).getSeatingCap())
                    	//The change to and may break code!!
                        if(!((reservationDateTime.after(reservedTime)||reservationDateTime.equals(reservedTime))  && (reservationDateTime.before(datePlusTwo)||reservationDateTime.equals(datePlusTwo) && (resDatePlusTwo.before(datePlusTwo)||resDatePlusTwo.equals(datePlusTwo))))){
                            return arrTables.get(i);
                        }
                }
                }
            }
        }
        System.out.println("No Tables Available");
        return null;
    }
}
