package ServiceImpl;

import DataType.Account;
import DataType.Member;
import Interface.AccountDao;
import Interface.AccountService;

public class AccountServiceImpl implements AccountService {
	private AccountDao dao;

	public AccountServiceImpl(AccountDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean addAccount(Account a) {
		return dao.insert(a);
	}

	@Override
	public Account checkAccount(int num) {
		return dao.selectByMNum(num);
	}

	@Override
	public boolean dealAccount(Member m, int money) {
		Account a = new Account();
		a.setm_num(m.getM_num());
		a.setA_balance(money);
		return dao.update(a);
	}
	
	public boolean dealAccount(int money) {
		Account a = new Account();
		a.setA_balance(money);
		return dao.update(a);
	}
}