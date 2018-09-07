package Interface;

import java.util.ArrayList;

import DataType.PurchasedItem;

public interface PurchasedItemService {
	public PurchasedItem selectByOnum(int num);

	public ArrayList<PurchasedItem> selectByPnum(int num);

	public ArrayList<PurchasedItem> selectByMnum(int num);

	public ArrayList<PurchasedItem> selectByPaid(boolean b);

	public ArrayList<PurchasedItem> allPurchasedInfo();

	public boolean addItem(PurchasedItem pi);

	public ArrayList<PurchasedItem> selectByUserCart(int num);

	public ArrayList<PurchasedItem> selectByUserPurchased(int num);

	public boolean deleteItem(PurchasedItem pi);

	public boolean edit(PurchasedItem pi);

	public boolean deleteCart(PurchasedItem pi);

	public boolean checkInsertCart(int m_num, int p_num);
}
