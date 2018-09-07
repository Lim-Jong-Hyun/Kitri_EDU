package ServiceImpl;

import java.util.ArrayList;

import DataType.Item;
import Interface.ItemDao;
import Interface.ItemService;

public class ItemServiceImpl implements ItemService {
	private ItemDao dao;

	public ItemServiceImpl(ItemDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean addItem(Item i) {
		return dao.insert(i);
	}

	@Override
	public Item selectByPNum(Item i) {
		return dao.selectByPNum(i);
	}

	@Override
	public ArrayList<Item> searchByPName(Item i) {
		return dao.searchByPName(i);
	}

	@Override
	public ArrayList<Item> searchByPWriter(Item i) {
		return dao.searchByPWriter(i);
	}

	@Override
	public ArrayList<Item> searchByPGenre(Item i) {
		return dao.searchByPGenre(i);
	}

	@Override
	public ArrayList<Item> searchByPPrice(Item i) {
		return dao.searchByPPrice(i);
	}

	@Override
	public Item ownGetBook(String p_name) {
		// TODO Auto-generated method stub
		return dao.select(p_name);
	}

	@Override
	public void addShoppingBasket(Item s) {
		// TODO Auto-generated method stub
		dao.insert(s);
	}

	@Override
	public void delShoppingBasket(String p_name) {
		// TODO Auto-generated method stub
		dao.delete(p_name);
	}

	@Override
	public Item getShoppingBasket(String p_name) {
		// TODO Auto-generated method stub
		return dao.select(p_name);
	}

	@Override
	public ArrayList<Item> getAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public boolean add(Item i) {
		// TODO Auto-generated method stub
		return dao.insert(i);
	}

	@Override
	public boolean delete(int num) {
		// TODO Auto-generated method stub
		return dao.delete(num);
	}

	@Override
	public boolean edit(Item i) {
		// TODO Auto-generated method stub
		return dao.update(i);
	}

	@Override
	public Item selectByPNum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByPNum(num);
	}

	@Override
	public ArrayList<Item> selectByPName(String name) {
		// TODO Auto-generated method stub
		return dao.searchByPName(name);
	}

	@Override
	public ArrayList<Item> selectByPWriter(String writer) {
		// TODO Auto-generated method stub
		return dao.searchByPWriter(writer);
	}

	@Override
	public ArrayList<Item> selectByPGenre(String genre) {
		// TODO Auto-generated method stub
		return dao.searchByPGenre(genre);
	}

	@Override
	public ArrayList<Item> selectByPPrice(int price) {
		// TODO Auto-generated method stub
		return dao.searchByPPrice(price);
	}

	@Override
	public ArrayList<Item> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

}
