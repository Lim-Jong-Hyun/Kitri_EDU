package ServiceImpl;

import java.util.ArrayList;

import DataType.PurchasedItem;
import Interface.PurchasedItemDao;
import Interface.PurchasedItemService;

public class PurchasedItemServiceImpl implements PurchasedItemService {
	private PurchasedItemDao dao;
	public PurchasedItemServiceImpl(PurchasedItemDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean addItem(PurchasedItem pi) {
		return dao.insert(pi);
	}
	
	@Override
	public boolean deleteItem(PurchasedItem pi) {
		return dao.delete(pi);
	}

	@Override
	public boolean edit(PurchasedItem pi) {
		// TODO Auto-generated method stub
		return dao.update(pi);
	}

	@Override
	public boolean deleteCart(PurchasedItem pi) {
		// TODO Auto-generated method stub
		return dao.delete(pi);
	}
	
	@Override
	public PurchasedItem selectByOnum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByONum(num);
	}

	@Override
	public ArrayList<PurchasedItem> selectByPnum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByPNum(num);
	}

	@Override
	public ArrayList<PurchasedItem> selectByMnum(int num) {
		// TODO Auto-generated method stub
		return dao.selectByMNum(num);
	}

	@Override
	public ArrayList<PurchasedItem> selectByPaid(boolean b) {
		// TODO Auto-generated method stub
		return dao.selectByPPaid(b);
	}

	@Override
	public ArrayList<PurchasedItem> allPurchasedInfo() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	@Override
	public ArrayList<PurchasedItem> selectByUserCart(int num){
		return dao.selectByUserCart(num);
	}
	
	@Override
	public ArrayList<PurchasedItem> selectByUserPurchased(int num){
		return dao.selectByUserPurchased(num);
	}

	@Override
	public boolean checkInsertCart(int m_num, int p_num) {
		// TODO Auto-generated method stub
		PurchasedItem pi = new PurchasedItem();
		pi.setm_num(m_num);
		pi.setP_num(p_num);
		return dao.checkInsertCart(pi);
	}
}
