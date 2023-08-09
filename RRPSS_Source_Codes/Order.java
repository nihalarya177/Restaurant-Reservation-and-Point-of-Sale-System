import java.util.ArrayList;
import java.util.Date;

/**
 * Represents a customer's order that a user can create
 * @author Ang Boon Leng
 * @version 1.0
 * @since 2021-11-12
 */
public class Order {

	/**
	 * ArrayList of items ordered by the customer
	 */
	private ArrayList<Item> orderItems;

	/**
	 * ArrayList of Integer representing the quantity of corresponding items in orderItems
	 */
	private ArrayList<Integer> qtyItems;

	/**
	 * ArrayList of promotion packages ordered by the customer
	 */
	private ArrayList<Promotion> orderPromotions;

	/**
	 * ArrayList of Integer representing the quantity of corresponding promotion packages in orderPromotions
	 */
	private ArrayList<Integer> qtyPromotions;

	/**
	 * The creation date (and time) of this order.
	 * It will be the current time when the user chooses to create an order
	 */
	private Date date;

	/**
	 * The table ID that the customer will occupy
	 */
	private int tableId;

	/**
	 * The staff ID of the staff who created this order
	 */
	private int staffId;

	/**
	 * whether the customer has a membership of the restaurant
	 */
	private boolean isMember;

	/**
	 * whether the order is completed (paid)
	 */
	private boolean isComplete;
	

	/**
	 * Parameterised constructor, called upon order creation
	 * @param date The creation date (and time) of the order
	 * @param tableId The table ID that the customer will occupy
	 * @param staffId The staff ID of the staff who created this order
	 * @param isMember Whether the customer has a membership of the restaurant
	 */
	public Order(Date date, int tableId, int staffId, boolean isMember) {
		this.date = date;
		this.tableId = tableId;
		this.staffId = staffId;
		this.isMember = isMember;
		this.isComplete = false;
		this.orderItems = new ArrayList<Item>();
		this.qtyItems = new ArrayList<Integer>();
		this.orderPromotions = new ArrayList<Promotion>();
		this.qtyPromotions = new ArrayList<Integer>();
	}

	/**
	 * This method returns the ArrayList of items ordered
	 * @return the ArrayList of items ordered
	 */
	public ArrayList<Item> getOrderItems() {
		return this.orderItems;
	}

	/**
	 * This method adds an item to an existing order
	 * @param menuItem the item selected from menu
	 * @param qtyItem the quantity of the item selected
	 */
	public void addItem(Item menuItem, int qtyItem) {
		// note: menu_item is just a reference to an item in the MenuDB
		// we need to create a new Item that is stored in an Order object
		this.orderItems.add(menuItem);
		this.qtyItems.add(qtyItem);
	}

	/**
	 * This method removes an item and its corresponding quantity from an existing order
	 * @param id the index of the item to be removed
	 */
	public void removeItem(int id) {
		this.orderItems.remove(id);
		this.qtyItems.remove(id);
	}

	/**
	 * This method returns the ArrayList of quantity of corresponding items
	 * @return the ArrayList of quantity of items
	 */
	public ArrayList<Integer> getQtyItems() {
		return this.qtyItems;
	}

	/**
	 * This method changes the quantity of an ordered item
	 * @param id the index of the quantity of item to be modified
	 * @param newQty the new quantity to be set
	 */
	public void setQtyItem(int id, int newQty) { 
		this.qtyItems.set(id, newQty);
	}

	/**
	 * This method returns the ArrayList of promotion packages ordered
	 * @return returns the ArrayList of promotion packages ordered
	 */
	public ArrayList<Promotion> getOrderPromotions() {
		return this.orderPromotions;
	}

	/**
	 * This method adds a promotion package to an existing order
	 * @param menuPromo the promotion package selected from menu
	 * @param qtyPromo the quantity of the promotion package selected
	 */
	public void addPromotion(Promotion menuPromo, int qtyPromo) {
		this.orderPromotions.add(menuPromo);
		this.qtyPromotions.add(qtyPromo);
	}

	/**
	 * This method removes a promotion package and its corresponding quantity from an existing order
	 * @param id the index of the promotion package to be removed
	 */
	public void removePromotion(int id) {
		this.orderPromotions.remove(id);
		this.qtyPromotions.remove(id);
	}

	/**
	 * This method returns the ArrayList of quantity of corresponding promotion packages
	 * @return the ArrayList of quantity of promotion packages
	 */
	public ArrayList<Integer> getQtyPromotions() {
		return this.qtyPromotions;
	}
	
	/**
	 * This method changes the quantity of an ordered promotion package
	 * @param id the index of the quantity of promotion package to be modified
	 * @param newQty the new quantity to be set
	 */
	public void setQtyPromotion(int id, int newQty) {
		this.qtyPromotions.set(id, newQty);
	}

	/**
	 * This method returns the ID of the staff who created the order
	 * @return ID of the staff who created the order
	 */
	public int getStaffId() {
		return this.staffId;
	}

	/**
	 * This method returns whether the customer has a membership
	 * @return whether the customer has a membership
	 */
	public boolean getIsMember() {
		return this.isMember;
	}

	/**
	 * This method returns whether the order is completed (paid)
	 * @return whether the order is completed (paid)
	 */
	public boolean getIsComplete() {
		return this.isComplete;
	}

	/**
	 * This method modifies the completion state of an order
	 * @param isComplete the completion state of an order
	 */
	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	/**
	 * This method returns the creation date of the order
	 * @return the creation date of the order
	 */
	public Date getDate() { 
		return this.date;
	}

	/**
	 * This method returns the table ID that the customer will occupy
	 * @return the table ID that the customer will occupy
	 */
	public int getTableId() {
		return this.tableId;
	}
}