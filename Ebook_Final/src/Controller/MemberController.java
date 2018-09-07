package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DataType.Member;
import DesignPattern.Cookie;
import Interface.MemberService;

public class MemberController {
	private MemberService service;
	private Cookie ck;

	public MemberController(MemberService service) {
		this.service = service;
		ck = Cookie.getInstance();
	}

	public boolean join(Scanner sc) {
		Member m = new Member();
		System.out.print("id:");
		m.setM_id(sc.next());
		System.out.print("pwd:");
		m.setM_pw(sc.next());
		System.out.print("name:");
		m.setM_name(sc.next());
		System.out.print("Email:");
		m.setM_email(sc.next());
		if (service.join(m)) {
			ck.setCookieId(m.getM_id());
			return true;
		} else {
			return false;
		}

	}

	public boolean login(Scanner sc) {
		Member m = new Member();

		System.out.print("id:");
		m.setM_id(sc.next());
		System.out.print("pwd:");
		m.setM_pw(sc.next());

		m = service.login(m);
		if (!(null == m)) {
			System.out.println("�α��� ����");
			ck.setCookieId(m.getM_id());
			ck.setCookieNum(m.getM_num());
			return true;
		} else {
			System.out.println("�α��� ����");
		}
		return false;
	}

	public boolean checkType() {
		if (service.selectById(ck.getCookieId()).isM_type()) {
			return true;
		} else {
			return false;
		}
	}

	public void logout() {
		if (ck.getCookieId().equals("")) {
			System.out.println("�α��� ����");
			return;
		}
		ck.setCookieId("");
	}

	public boolean myInfo() {
		if (ck.getCookieId().equals("")) {
			System.out.println("�α��� ����");
			return false;
		}
		Member m = service.getMyInfo(ck.getCookieId());
		System.out.println(m);
		return true;
	}

	public void editMyInfo(Scanner sc) {
		boolean flag = myInfo();
		if (flag) {
			Member m = new Member();
			System.out.print("new pwd:");
			m.setM_pw(sc.next());
			System.out.print("new email:");
			m.setM_email(sc.next());
			service.editMyInfo(m);
		}
	}

	public boolean out(Scanner sc) {
		System.out.print("Ż���Ͻðڽ��ϱ�? [Y/N] : ");
		String str = sc.next();
		if (str.equals("Y") || str.equals("y")) {
			if (service.out(ck.getCookieNum())) {
				System.out.println("Ż�� �Ϸ�");
				return true;
			}
		}
		System.out.println("Ż�� ���");
		return false;
	}

	public void printsearchByMNum(Scanner sc) {
		// ��Ʈ�ѷ� �˻� ���� �ʿ�
		ArrayList<Member> list = service.searchByMNum(0);
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void printsearchByMId(String m_id) {
		ArrayList<Member> list = service.searchByMId(m_id);
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void printsearchByMName(String m_name) {
		ArrayList<Member> list = service.searchByMName(m_name);
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void printsearchByMType(boolean m_type) {
		ArrayList<Member> list = service.searchByMType(m_type);
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void printsearchAll() {
		ArrayList<Member> list = service.searchAll();
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public void selectByName(Scanner sc) {
		System.out.print("�˻��� �̸��� �Է��ϼ��� :");
		String name = sc.next();
		ArrayList<Member> list = new ArrayList<Member>();
		list = service.selectByName(name);
		if (list.size() == 0) {
			System.out.println("�˻� ����� �����ϴ�.");
		} else {
			for (Member m : list) {
				System.out.println(m);
			}
		}
	}

	public void selectByDate(Scanner sc) {

		System.out.print("�˻��� ���� �Է��ϼ���(1-12) :");
		String month = sc.next();
		if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
				|| month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
			month = "0" + month;
		}

		ArrayList<Member> list = new ArrayList<Member>();
		list = service.selectByDate(month);
		if (list.size() == 0) {
			System.out.println("�˻� ����� �����ϴ�.");
		} else {
			for (Member m : list) {
				System.out.println(m);
			}
		}
	}

	public void selectById(Scanner sc) {
		System.out.print("�˻��� ID�� �Է��ϼ��� :");
		String id = sc.next();
		Member m = new Member();
		m = service.selectById(id);
		if (m == null) {
			System.out.println("�˻� ����� �����ϴ�.");
		} else {
			System.out.println(m);
		}
	}

	public void selectByNum(Scanner sc) {

		System.out.print("�˻��� ���Թ�ȣ�� �Է��ϼ��� :");
		int num = sc.nextInt();
		Member m = new Member();
		m = service.selectByNum(num);

		if (m == null) {
			System.out.println("�˻� ����� �����ϴ�.");
		} else {
			System.out.println(m);
		}
	}

	public void ShowAllUser() {
		ArrayList<Member> list = new ArrayList<Member>();
		list = service.alluserInfo();
		for (Member m : list) {
			System.out.println(m);
		}
	}

	public Member getMidByMName() {
		Member m = service.getMyInfo(ck.getCookieId());
		return m;
	}

	public Member getMemberByNum(int num) {
		Member m = service.selectByNum(num);
		return m;
	}

	public Member getMemberById(String id) {
		Member m = service.selectById(id);
		return m;
	}
}