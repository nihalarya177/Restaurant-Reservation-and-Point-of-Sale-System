import java.util.ArrayList;

public class Restaurant {

	private final static int numCap2Tables = 5;
	private final static int numCap4Tables = 4;
	private final static int numCap6Tables = 3;
	private final static int numCap8Tables = 2;
	private final static int numCap10Tables = 1;

	/**
	 * History of all created orders
	 */
	private ArrayList<Order> orders;

	/**
	 * All current staff in the restaurant
	 */
	private ArrayList<Staff> staffs;

	/**
	 * All tables in the restaurant
	 */
	private ArrayList<Table> tables;

	/**
	 * Default constuctor, initialises current staffs and tables in the restaurant
	 */
	public Restaurant() {
		this.staffs = new ArrayList<Staff>();
		this.orders = new ArrayList<Order>();
		this.tables = new ArrayList<Table>();
		// initialise current staffs
		staffs.add(new Staff("John", "Male", 101, "Part-time"));
		staffs.add(new Staff("Jane", "Female", 102, "Full-time"));
		staffs.add(new Staff("Jeff", "Male", 103, "Manager"));
		// initialise tables
		int curNumTables=0;
		for (int i=1; i<=numCap2Tables; i++) {
			tables.add(new Table(2, curNumTables+1, false));
			curNumTables++;
		}
		for (int i=1; i<=numCap4Tables; i++) {
			tables.add(new Table(4, curNumTables+1, false));
			curNumTables++;
		}
		for (int i=1; i<=numCap6Tables; i++) {
			tables.add(new Table(6, curNumTables+1, false));
			curNumTables++;
		}
		for (int i=1; i<=numCap8Tables; i++) {
			tables.add(new Table(8, curNumTables+1, false));
			curNumTables++;
		}
		for (int i=1; i<=numCap10Tables; i++) {
			tables.add(new Table(10, curNumTables+1, false));
			curNumTables++;
		}
	}

	/**
	 * This method adds an order to the order history
	 * @param order the order object to be added to the order history 
	 */
	public void addOrder(Order order) {
		orders.add(order);
	}

	/**
	 * This method return the ArrayList of orders in the restaurant
	 * @return the ArrayList of orders in the restaurant
	 */
	public ArrayList<Order> getOrders() {
		return this.orders;
	}

	/**
	 * This method adds a new Staff to the staffs in the restaurant
	 * @param name the staff's name
	 * @param gender the staff's gender
	 * @param employeeId the staff's ID
	 * @param jobTitle the staff's job title
	 */
	public void addStaff(String name, String gender, int employeeId, String jobTitle) {
		staffs.add(new Staff(name, gender, employeeId, jobTitle));
	}

	/**
	 * This method return the ArrayList of staffs in the restaurant
	 * @return the ArrayList of staffs in the restaurant
	 */
	public ArrayList<Staff> getStaffs() {
		return this.staffs;
	}

	/**
	 * This method returns the ArrayList of tables in the restaurant
	 * @return the ArrayList of tables in the restaurant
	 */
	public ArrayList<Table> getTables() {
		return this.tables;
	}

	/**
	 * This method sets the tables in the restaurant
	 * @param tables the new ArrayList of tables
	 */
	public void setTables(ArrayList<Table> tables) {
		this.tables = tables;
	}
}