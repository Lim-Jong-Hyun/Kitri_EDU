package Interface;

import java.util.ArrayList;

import DataType.Account;

public interface AccountDao {
	public boolean insert(Account a);

	public boolean delete(Account a);

	public boolean update(Account a);

	public Account selectByMNum(Account a);

	public Account selectByMNum(int num);

	public ArrayList<Account> selectAll();

	public void delete(int m_num);
}