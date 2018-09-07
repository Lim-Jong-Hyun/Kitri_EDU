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
		System.out.print("�����̸� : ");
		a.setA_name(sc.next());
		System.out.print("���¹�ȣ : ");
		a.setA_accountNum(sc.next());

		if (service.addAccount(a)) {
			System.out.println("���»��� ����");
		} else {
			System.out.println("���»��� ����");
		}
	}

	public void checkAccount(Scanner sc, int num) {
		Account a = service.checkAccount(num);
		System.out.println(a);
	}

	public void depositAccount(Scanner sc, Member m) {
		System.out.print("�Ա��� �ݾ� : ");
		int money = sc.nextInt();
		if (service.dealAccount(m, money)) {
			System.out.println("�Ա� ����");
		} else {
			System.out.println("�Ա� ����");
		}
	}

	public void withdrawAccount(Scanner sc, Member m) {
		System.out.print("����� �ݾ� : ");
		int money = sc.nextInt();
		if (service.checkAccount(m.getM_num()).getA_balance() >= money) {
			if (service.dealAccount(m, -money)) {
				System.out.println("��� ����");
				return;
			}
		}
		System.out.println("��� ����");
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