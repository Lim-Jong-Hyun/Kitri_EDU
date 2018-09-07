package Controller;

import java.util.Scanner;
import DataType.Account;
import DataType.Member;
import Interface.AccountService;

public class AccountController {
	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}

	public void addAccount(Scanner sc, Member m) {
		Account a = new Account();
		a.setm_num(m.getM_num());
		System.out.print("은행이름 : ");
		a.setA_name(sc.next());
		System.out.print("계좌번호 : ");
		a.setA_accountNum(sc.next());

		if (service.addAccount(a)) {
			System.out.println("계좌생성 성공");
		} else {
			System.out.println("계좌생성 실패");
		}
	}

	public void checkAccount(Scanner sc, int num) {
		Account a = service.checkAccount(num);
		System.out.println(a);
	}

	public void depositAccount(Scanner sc, Member m) {
		System.out.print("입금할 금액 : ");
		int money = sc.nextInt();
		if (service.dealAccount(m, money)) {
			System.out.println("입금 성공");
		} else {
			System.out.println("입금 실패");
		}
	}

	public void withdrawAccount(Scanner sc, Member m) {
		System.out.print("출금할 금액 : ");
		int money = sc.nextInt();
		if (service.checkAccount(m.getM_num()).getA_balance() >= money) {
			if (service.dealAccount(m, -money)) {
				System.out.println("출금 성공");
				return;
			}
		}
		System.out.println("출금 실패");
	}
	
	public void withdrawByBook(int m_num, int money) {
		Member m = new Member();
		Member admin = new Member();
		m.setM_num(m_num);
		admin.setM_num(1);
		service.dealAccount(m, -money);
		service.dealAccount(admin, money);
	}
	
	
	public Account checkAccount(int num) {
		Account a = service.checkAccount(num);
		return a;
	}
}