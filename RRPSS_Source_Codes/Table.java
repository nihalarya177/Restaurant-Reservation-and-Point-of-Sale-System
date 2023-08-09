

import java.util.ArrayList;
/**
 * Tables in the resteraunt 
 * Each table has an arraylist of reservations
 *
 * @author Samarth Agarwal
 * @version 1.10
 * @since 2021-12-11
 */
public class Table
{
    /**
     * Number of seats that the table
     * can hold
     */
    private int seatingCapacity;
    /**
     * The id (identifier) of the table
     */
    private int tableId;
    /**
     * Whether the table is reserved
     */
    private boolean isReserved;
    /**
     * The list of reservations
     */
    private ArrayList<Reservation> reservation;
    /**
     * Creates a table
     * @param seatingCapacity number of seats the table can hold
     * @param tableId id (identifier) of table
     * @param isReserved whether the table is reserved
     */
    public Table(int seatingCapacity, int tableId, boolean isReserved)
    {
        // initialise instance variables
        this.seatingCapacity = seatingCapacity;
        this.tableId = tableId;
        this.isReserved = isReserved;
        reservation = new ArrayList<Reservation>();
    }
    /**
     * Returns the seating capacity of the table
     * @return the number of people a table can hold
     */
    public int getSeatingCap(){
        return seatingCapacity;
    }
    /**
     * sets the seting capacity of the table
     * @param seatingCapacity sets the number of people 
     * who can sit on that table
     */
    public void setSeatingCap(int seatingCapacity){
        this.seatingCapacity = seatingCapacity;
    }
    /**
     *  Returns the id (identifier) of the table
     *  @return the identifer of the table
     */
    public int getTableId(){
        return tableId;
    }
    /**
     * Sets the id (identifier) of the table
     * @param tableId the id (identifier) of the table
     */
    public void setTableId(int tableId){
        this.tableId = tableId;
    }
    /**
     * Returns whether the table isReserved
     * @return whether the table isReserved
     */
    public boolean getIsReserved(){
        return isReserved;
    }
    /**
     * Sets the table to either reserved or not reserved
     * @param isReserved whether the table is reserved or not
     */
    public void setIsReserved(boolean isReserved){
        this.isReserved = isReserved;
    }
    /**
     * Returns the list of the reservations on that table
     * @return List of reservations
     */
    public ArrayList<Reservation> getReservation(){
        return reservation;
    }
    /**
     * Sets the list of reservations at the table
     * @param reservation the list of reservations
     */
    public void setReservation(ArrayList<Reservation> reservation){
        this.reservation = reservation;
    }
}
