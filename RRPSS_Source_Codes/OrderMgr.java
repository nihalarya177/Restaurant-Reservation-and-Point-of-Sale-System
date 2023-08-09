import java.util.Date;
import java.util.Scanner;

/**
 * Manages the interaction between Order boundary class and Order entity class
 * @author Ang Boon Leng
 * @version 1.0
 * @since 2021-11-12
 */
public class OrderMgr {

	/**
	 * This method displays all created orders, sorted by creation date
	 * It also displays whether the order is completed
	 * @param restaurant the restaurant object to access the order history
	 */
	private void showBriefOrders(Restaurant restaurant) {
		int i = 1;
		for (Order order : restaurant.getOrders()) {
			System.out.printf("(%d): %s %d %s %s\n", i,"Table no.:", order.getTableId() ,   order.getDate(), order.getIsComplete() ? "[COMPLETED]" : "");
			i++;
		}
	}

	/**
	 * This method displays details of ordered items in an order
	 * Details shown are: Item type, name, and its quantity
	 * @param order the order object to access the ordered items
	 */
	private void showOrderItems(Order order) {
		System.out.println("\nItems ordered:");
		if (order.getOrderItems().size() == 0) {
			System.out.println(">> No items ordered <<");
		} else {
			int i = 1;
			for (Item item : order.getOrderItems()) {
				System.out.printf("%d. %-10s: %-15s x %d\n", i, item.getType(), item.getName(), order.getQtyItems().get(i - 1));
				i++;
			}
		}
	}

	/**
	 * This method displays details of ordered promotion packages in an order
	 * Details shown are: Promotion desc and its quantity
	 * @param order the order object to access the ordered promotion packages
	 */
	private void showOrderPromotions(Order order) {
		System.out.println("\nPromotions ordered:");
		if (order.getOrderPromotions().size() == 0) {
			System.out.println(">> No promotions ordered <<");
		} else {
			int i = 1;
			for (Promotion promo : order.getOrderPromotions()) {
				System.out.printf("%d. %-15s x %d\n", i, promo.getDesc(), order.getQtyPromotions().get(i - 1));
				i++;
			}
		}
	}

	/**
	 * This method asks the user to select an order
	 * @param restaurant the restaurant object to access order history
	 * @return selected order
	 */
	public Order selectOrder(Restaurant restaurant) {

		if (restaurant.getOrders().size() == 0) {
			System.out.println(">> No order created <<");
			return null;
		}

		System.out.println("(0): Quit");
		showBriefOrders(restaurant);

		// select order to view
		Scanner sc = new Scanner(System.in);
		int choice;
		Order selectedOrder;
		while (true) {
			choice = sc.nextInt();
			try {
				if (choice == 0) {
					return null;
				}
				selectedOrder = restaurant.getOrders().get(choice - 1);
				return selectedOrder;
			} catch (IndexOutOfBoundsException e) {
				System.out.print("Order number not available!\n" + "Please try again: ");
			}
		}
	}

	/**
	 * This method creates a new order and adds it to the order history of the restaurant
	 * @param restaurant the restaurant object to add a new order
	 * @param date the creation date of the order
	 * @param tableId the table ID that the customer will occupy
	 * @param staffId the staff ID of the staff who created this order
	 * @param isMember whether the customer has a membership of the restaurant
	 */
	public void createOrder(Restaurant restaurant, Date date, int tableId, int staffId, boolean isMember) {
		// call Order constructor
		Order order = new Order(date, tableId, staffId, isMember);
		// store order into Restaurant
		restaurant.addOrder(order);
	}

	/**
	 * This method displays details of an order selected by the user
	 * Details shown are: creation date, table ID, staff ID, membership, completed, items and promotion packages ordered
	 * @param restaurant the restaurant object to access the order and its details
	 */
	public void showOrder(Restaurant restaurant) { 

		System.out.println("Choose which order to view:");
		Order order = selectOrder(restaurant);
		if (order == null) {
			return;
		} 

		// print all details from this order
		System.out.println("Creation time: " + order.getDate());
		System.out.println("Table ID: " + order.getTableId());
		System.out.println("Staff ID: " + order.getStaffId());
		System.out.println("Membership: " + order.getIsMember());
		System.out.println("Completed: " + order.getIsComplete());
		showOrderItems(order);
		showOrderPromotions(order);
	}

	/**
	 * This method adds an item or promotion package and its quantity to an existing order
	 * @param restaurant the restaurant object to access an order
	 */
	public void addToOrder(Restaurant restaurant) {
		ItemMgr itemMgr = new ItemMgr();
		PromotionMgr promotionMgr = new PromotionMgr();

		// select an existing order
		System.out.println("Choose which order to add menu items:");
		Order order;
		while (true) {
			order = selectOrder(restaurant);
			if (order == null) {
				return;
			}
			else if (!order.getIsComplete()) break; // only allow user to choose uncompleted order
			System.out.println("Cannot add menu items to completed orders. Please try again.");
		}

		// choose whether to add item or promotion
		System.out.println("Choose which menu category to add from:\n" + "(1) Items\n" + "(2) Promotions");

		Scanner sc = new Scanner(System.in);
		int id, qty;
		while (true) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1: // add item to order
				itemMgr.showItems();
				System.out.println();
				System.out.print("Select Item No. to add to order: ");
				id = sc.nextInt() - 1;
				Item menuItem = itemMgr.getMenu().getItems().get(id);
				System.out.print("Enter quantity of items: ");
				qty = sc.nextInt();
				order.addItem(menuItem, qty);
				System.out.println("Item(s) added successfully");
				return;
			case 2: // add promo to order
				promotionMgr.showPromotions();
				System.out.println();
				System.out.print("Select Promotion No. to add to order: ");
				id = sc.nextInt() - 1;
				Promotion menuPromo = itemMgr.getMenu().getPromotions().get(id);
				System.out.print("Enter quantity of promotions: ");
				qty = sc.nextInt();
				order.addPromotion(menuPromo, qty);
				System.out.println("Promotion(s) added successfully");
				return;
			default:
				System.out.print("Menu category not available!\n" + "Please try again:");
			}
		}
	}

	/**
	 * This method removes an item or promotion package and its quantity from an existing order
	 * @param restaurant the restaurant object to access an order
	 */
	public void removeFromOrder(Restaurant restaurant) {

		System.out.println("Choose which order to remove menu items:");
		Order order;
		while (true) {
			order = selectOrder(restaurant);
			if (order == null) {
				return;
			}
			else if (!order.getIsComplete()) break; // only allow user to choose uncompleted order
			System.out.println("Cannot remove menu items from completed orders. Please try again.");
		}

		if (order.getOrderItems().size() == 0 && order.getOrderPromotions().size() == 0) {
			System.out.println(">> No menu items added <<");
			return;
		}
		// choose whether to remove item or promotion
		System.out.println("Choose which category of menu item to remove:\n" + "(1) Items\n" + "(2) Promotions");
		Scanner sc = new Scanner(System.in);
		int id, qty, orderedQty;
		while (true) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1: // remove item from order
				int numOrderItems = order.getOrderItems().size();
				if (numOrderItems == 0) {
					System.out.println("No items added! Please try again: ");
					break;
				}
				showOrderItems(order);
				System.out.println();
				System.out.print("Select Item No. to remove from order: ");
				while (true) {
					id = sc.nextInt() - 1;
					if (id >= 0 && id < numOrderItems) break;
					System.out.print("Invalid Item No.! Please try again: ");
				}
				
				orderedQty = order.getQtyItems().get(id);
				System.out.print("Enter quantity of items to remove: ");
				while (true) {
					qty = sc.nextInt();
					if (qty > 0 && qty <= orderedQty) break;
					System.out.print("Invalid quantity! Please try again: "); 
				}
				if (qty == orderedQty) { // remove entire item
					order.removeItem(id);
				} else {
					order.setQtyItem(id, (orderedQty - qty));
				}
				System.out.println("Item(s) removed successfully");
				return;
			case 2: // remove promo from order
				int numOrderPromos = order.getOrderPromotions().size();
				if (numOrderPromos == 0) {
					System.out.println("No promotions added! Please try again: ");
					break;
				}
				showOrderPromotions(order);
				System.out.println();
				System.out.print("Select Promotion No. to remove from order: ");
				while (true) {
					id = sc.nextInt() - 1;
					if (id >= 0 && id < numOrderPromos) break;
					System.out.print("Invalid Promotion No.! Please try again: ");
				}
				orderedQty = order.getQtyPromotions().get(id);
				System.out.print("Enter quantity of promotions to remove: ");
				while (true) {
					qty = sc.nextInt();
					if (qty > 0 && qty <= orderedQty) break;
					System.out.print("Invalid quantity! Please try again: "); 
				}
				if (qty == orderedQty) { // remove entire promo
					order.removePromotion(id);
				} else {
					order.setQtyPromotion(id, (orderedQty - qty));
				}
				System.out.println("Promotion(s) removed successfully");
				return;
			default:
				System.out.print("Menu category not available!\n" + "Please try again:");
			}
		}
	}
}