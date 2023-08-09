import java.util.ArrayList;

/**
 * Represent a Promotion in the Menu.
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */

public class Promotion {

	/**
	 * The Items Arraylist contained in the Promotion package .
	 */
	private ArrayList<Item> arrItem;

	/**
	 * The name of the Promotion.
	 */
	private String desc;

	/**
	 * The price of the Promotion.
	 */
	private double price;

	/**
	 * The total item count in the Promotion package.
	 */
	private int itemCnt;

	/**
	 * Create a new Promotions with given item arraylist , description , price
	 * @param arrItem This's Promotion's Items Arraylist.
	 * @param desc This Promotion's name.
	 * @param price This Promotion's price.
	 * @param itemCnt This Promotion's item count.
	 */
	public Promotion(ArrayList<Item> arrItem, String desc, double price, int itemCnt) {
		// TODO - implement Promotion.Promotion
		this.arrItem = arrItem;
		this.desc = desc;
		this.price = price;
		this.itemCnt = itemCnt;
	}

	/**
	 * Gets the Items Arraylist contained in the promotion package.
	 * @return this Promotion's Items ArrayList
	 */
	public ArrayList<Item> getArrItem() {

		return this.arrItem;
	}

	/**
	 * Change the Items contained in the promotion package.
	 * @param arrItem this Promotion's Items ArrayList
	 */
	public void setArrItem(ArrayList<Item> arrItem) {

		this.arrItem = arrItem;
	}

	/**
	 * Gets the name of the Promotion.
	 * @return this Promotion's name.
	 */
	public String getDesc() {
		return this.desc;
	}

	/**
	 * Change the name of the Promotion.
	 * @param desc this Promotion's name.
	 */
	public void setDesc(String desc) {

		this.desc = desc;
	}

	/**
	 * Gets the price of the Promotion.
	 * @return this Promotion's price.
	 */

	public double getPrice() {

		return this.price;
	}

	/**
	 * Change the price of the Promotion.
	 * @param price this Promotion's price.
	 */

	public void setPrice(double price) {

		this.price = price;
	}

	/**
	 * Gets the Items count in the Promotion.
	 * @return this Promotion's Items count.
	 */

	public int getItemCnt() {

		return this.itemCnt;
	}

	/**
	 * Change the Items count in the Promotion.
	 * @param itemCnt this Promotion's Items count.
	 */
	public void setItemCnt(int itemCnt) {

		this.itemCnt = itemCnt;
	}

}