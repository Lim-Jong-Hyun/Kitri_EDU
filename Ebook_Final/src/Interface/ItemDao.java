package Interface;

import java.util.ArrayList;

import DataType.Item;

public interface ItemDao {
	public boolean insert(Item i);

	public boolean delete(String p_name);

	public boolean update(Item i);

	public Item select(String p_name);

	public Item selectByPNum(Item i);

	public ArrayList<Item> searchByPName(Item i);

	public ArrayList<Item> searchByPWriter(Item i);

	public ArrayList<Item> searchByPGenre(Item i);

	public ArrayList<Item> searchByPPrice(Item i);

	public boolean delete(int num);

	public Item selectByPNum(int num);

	public ArrayList<Item> searchByPName(String name);

	public ArrayList<Item> searchByPWriter(String writer);

	public ArrayList<Item> searchByPGenre(String grnre);

	public ArrayList<Item> searchByPPrice(int price);

	public ArrayList<Item> selectAll();
}