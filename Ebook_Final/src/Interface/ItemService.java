package Interface;

import java.util.ArrayList;

import DataType.Item;

public interface ItemService {
	public boolean addItem(Item i);

	public Item selectByPNum(Item i);

	public ArrayList<Item> searchByPName(Item i);

	public ArrayList<Item> searchByPWriter(Item i);

	public ArrayList<Item> searchByPGenre(Item i);

	public ArrayList<Item> searchByPPrice(Item i);

	public boolean add(Item i);

	public boolean delete(int num);

	public boolean edit(Item i);

	public Item selectByPNum(int num);

	public Item ownGetBook(String p_name);

	public ArrayList<Item> getAll();

	public void addShoppingBasket(Item s);

	public void delShoppingBasket(String p_name);

	public Item getShoppingBasket(String p_name);

	public ArrayList<Item> selectByPName(String name);

	public ArrayList<Item> selectByPWriter(String writer);

	public ArrayList<Item> selectByPGenre(String genre);

	public ArrayList<Item> selectByPPrice(int price);

	public ArrayList<Item> selectAll();
}
