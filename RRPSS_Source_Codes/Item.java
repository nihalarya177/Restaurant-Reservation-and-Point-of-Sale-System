/**
 * Represent a Item object in the Menu.
 * @author Liew Kok Leong
 * @version 1.0
 * @since 2021-11-10
 */

public class Item {
	/**
	 * The category type of the item.
	 */
	private ItemType type;
	/**
	 * The name of the item.
	 */
	private String name;
	/**
	 * The description of the item.
	 */
	private String desc;
	/**
	 * The price of the item.
	 */
	private double price;

	/**
	 * Create a new Item with given category type , name , description , price
	 * @param type This Item's category type.
	 * @param name This Item's name.
	 * @param desc This Item's description.
	 * @param price This Item's price.
	 */
	public Item(ItemType type, String name, String desc, double price) {
		// TODO - implement Item.Item
		this.type = type;
		this.name = name;
		this.desc = desc;
		this.price = price;
	}

	/**
	 * Gets the category type of this item.
	 * @return this Item's category type.
	 */
	public ItemType getType() {

		return this.type;
	}

	/**
	 * Change the category type of this Item.
	 * @param type This Item's category type.
	 */
	public void setType(ItemType type) {

		this.type = type;
	}

	/**
	 * Gets the name of this item.
	 * @return this Item's name.
	 */

	public String getName() {

		return this.name;
	}

	/**
	 * Change the name of this Item.
	 * @param name This Item's name.
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Gets the description of this item.
	 * @return this Item's description.
	 */

	public String getDesc() {

		return this.desc;
	}

	/**
	 * Change the description of this Item.
	 * @param desc this Item's description.
	 */

	public void setDesc(String desc) {

		this.desc = desc;
	}

	/**
	 * Gets the price of this item.
	 * @return this Item's price.
	 */

	public double getPrice() {

		return this.price;
	}

	/**
	 * Change the price of this Item.
	 * @param price this Item's price.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}