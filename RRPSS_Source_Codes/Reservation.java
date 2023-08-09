

import java.util.Scanner;
import java.util.Date;
import java.util.*;
import java.text.*;
/**
 * Represents a reservation made by the user in a restaurant
 *
 * @author Samarth Agarwal
 * @version 1.0
 * @since 2021-12-11
 */
public class Reservation
{
    // instance variables - replace the example below with your own
    /**
     * The name of the customer
     */
    private String name;
    /**
     * The number of people in the party  
     * The contact of the user making the reservation
     */
    private int pax, contact;
    /**
     * The date at which the reservation is being made
     * The time at which the reservation is being made
     */
    private Date date, time;
    /**
     * Creates a reservation with empty name, new date, new time, pax and contact as 0
     */
    Reservation(){
        this.name = "";
        this.date = new Date();
        this.time = new Date();
        this.pax = 0;
        this.contact = 0;
    }
    /**
     * Creates a new reservation.
     * @param name name of the customer
     * @param contact the contact of the customer
     * @param date the date at which the reservation is being made
     * @param time the time at which the reservation is being made
     */
    Reservation(String name, int pax, int contact, Date date, Date time){
        this.name = name;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.contact = contact;
    }
    /**
     * Returns the name of the customer who made the reservation
     * @return name of customer
     */
    public String getName(){
        return name;
    }
    /**
     * Returns the contact of the customer who made the reservation
     * @return contact of the customer
     */
    public int getContact(){
        return contact;
    }
    /**
     * Returns the date of the reservation
     * @return date of reservaion
     */
    public Date getReserveDate(){
        return date;
    }
    /**
     * Returns the time of the reservation
     * @return time of reservation
     */
    public Date getReserveTime(){
        return time;
    }
    /**
     * Returns the number of people in the party
     * @return pax
     */
    public int getPax(){
        return pax;
    }
    /**
     * Removes the reservation made by the customer
     * sets name to empty string
     * sets date and time to null
     * sets pax and contact to 0
     */
    public void removeReservation(){
        this.name = "";
        this.date = null;
        this.time = null;
        this.pax = 0;
        this.contact = 0;
    }
}

