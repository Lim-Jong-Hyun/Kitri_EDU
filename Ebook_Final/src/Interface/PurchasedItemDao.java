package Interface;

import java.util.ArrayList;

import DataType.PurchasedItem;

public interface PurchasedItemDao {
	public boolean insert(PurchasedItem pi);

	public boolean delete(PurchasedItem pi);

	public boolean update(PurchasedItem pi);

	public ArrayList<PurchasedItem> selectAll();

	public PurchasedItem selectByONum(int num);

	public ArrayList<PurchasedItem> selectByMNum(int num);

	public ArrayList<PurchasedItem> selectByUserPurchased(int num);

	public ArrayList<PurchasedItem> selectByPNum(int num);

	public ArrayList<PurchasedItem> selectByPPaid(boolean b);

	public ArrayList<PurchasedItem> selectByUserCart(int num);

	public boolean checkInsertCart(PurchasedItem pi);
}