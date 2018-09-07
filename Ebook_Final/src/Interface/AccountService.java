package Interface;

import DataType.Account;
import DataType.Member;

public interface AccountService {
	public Account checkAccount(int num);

	public boolean dealAccount(Member m, int money);

	public boolean dealAccount(int money);

	public boolean addAccount(Account a);
}
