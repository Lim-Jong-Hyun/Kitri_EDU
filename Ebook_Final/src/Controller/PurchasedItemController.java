package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DataType.Item;
import DataType.Member;
import DataType.PurchasedItem;
import DesignPattern.Cookie;
import Interface.MemberService;
import Interface.PurchasedItemService;

public class PurchasedItemController {
	private PurchasedItemService service;
	private Cookie ck;

	public PurchasedItemController(PurchasedItemService service) {
		this.service = service;
		ck = Cookie.getInstance();
	}

	public void allPurchasedList() {
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.allPurchasedInfo();

		if (list.size() == 0) {
			System.out.println("항목이 없습니다..");
		}
		
		for (PurchasedItem p : list) {
			System.out.println(p.mainString());
		}
	}

	public boolean userPurchasedList(Scanner sc, int num) {
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByUserPurchased(num);

		if (list.size() == 0) {
			System.out.println("구매내역이 없습니다.");
			return false;
		}

		for (PurchasedItem p : list) {
			System.out.println(p);
		}
		return true;
	}

	public void userCartList(Scanner sc, int num) {
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByUserCart(num);

		if (list.size() == 0) {
			System.out.println("구매내역이 없습니다.");
			return;
		}

		for (PurchasedItem p : list) {
			System.out.println(p);
		}
	}

	public void userDeleteCart(Scanner sc, int num) {
		ArrayList<PurchasedItem> tmp = service.selectByMnum(num);
		ArrayList<PurchasedItem> arr = new ArrayList<PurchasedItem>();
		for (PurchasedItem pi : tmp) {
			if (pi.isP_paid() == false) {
				arr.add(pi);
			}
		}
		for (PurchasedItem pi : arr) {
			System.out.println(pi);
		}
		System.out.print("삭제할 장바구니 제품번호 : ");
		int p_num = sc.nextInt();

		for (PurchasedItem pi : arr) {
			if (pi.getP_num() == p_num) {
				PurchasedItem pi2 = pi;
				if (service.deleteItem(pi2)) {
					System.out.println("삭제 성공");
					return;
				}
			}
		}

		System.out.println("삭제 실패");
	}

	public void purchasedList(Scanner sc) {

		System.out.print("사용자의 등록번호를 입력하세요 : ");
		int num = sc.nextInt();

		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByMnum(num);

		if (list.size() == 0) {
			System.out.println("구매내역이 없습니다.");
			return;
		}

		for (PurchasedItem p : list) {
			System.out.println(p.mainString());
		}
	}

	public void cartList(Scanner sc) {

		System.out.print("사용자의 등록번호를 입력하세요 : ");
		int num = sc.nextInt();

		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByMnum(num);

		if (list.size() == 0) {
			System.out.println("구매내역이 없습니다.");
			return;
		}

		for (PurchasedItem p : list) {
			System.out.println(p);
		}
	}

	public Member selectById(Scanner sc) {

		System.out.print("검색할 ID를 입력하세요 :");
		String id = sc.next();
		Member m = ((MemberService) service).selectById(id);

		if (m == null) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			return m;
		}
		return null;
	}

	public boolean addItem(Item i) {
		PurchasedItem pi = new PurchasedItem();
		pi.setP_num(i.getP_num());
		pi.setP_name(i.getP_name());
		pi.setm_num(ck.getCookieNum());

		return service.addItem(pi);
	}

	public ArrayList<PurchasedItem> CartList() {
		int mnum = ck.getCookieNum();
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByMnum(mnum);

		ArrayList<PurchasedItem> noPayList = new ArrayList<PurchasedItem>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isP_paid() == false) {
				noPayList.add(list.get(i));
			}
		}

		for (PurchasedItem p : noPayList) {
			System.out.println(p.toString3());
		}
		return noPayList;

	}

	public ArrayList<PurchasedItem> checkCartList() {
		int mnum = ck.getCookieNum();
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByMnum(mnum);

		ArrayList<PurchasedItem> noPayList = new ArrayList<PurchasedItem>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isP_paid() == false) {
				noPayList.add(list.get(i));
			}
		}
		return noPayList;

	}

	public ArrayList<Integer> selectPNumbyPaid2(Scanner sc) {
		// TODO Auto-generated method stub

		ArrayList<PurchasedItem> arr = userGetPurchasedItem(sc, ck.getCookieNum());
		ArrayList<Integer> pnums = new ArrayList<Integer>();
		System.out.println(arr.size());
		for (int i = 0; i < arr.size(); i++) {
			pnums.add(arr.get(i).getP_num());
		}

		return pnums;
	}

	public ArrayList<PurchasedItem> onlyCartList() {
		int mnum = ck.getCookieNum();
		ArrayList<PurchasedItem> list = new ArrayList<PurchasedItem>();
		list = service.selectByMnum(mnum);

		ArrayList<PurchasedItem> noPayList = new ArrayList<PurchasedItem>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isP_paid() == false) {
				noPayList.add(list.get(i));
			}
		}
		return noPayList;

	}

	public void deleteCart(Scanner sc) {

		ArrayList<PurchasedItem> list = CartList();
		System.out.print("장바구니에서 삭제할 책 번호를 입력하세요(0:나가기):");
		int pnum = sc.nextInt();
		if (pnum == 0) {
			return;
		}
		PurchasedItem p = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getP_num() == pnum) {
				p = list.get(i);
			}
		}
		if (p == null) {
			System.out.println("장바구니에 없는 책 번호입니다.");
		} else {

			boolean b = service.deleteCart(p);
			if (b == true) {
				System.out.println("삭제완료");
				return;
			} else {
				System.out.println("삭제실패");
			}

		}

	}

	public ArrayList<PurchasedItem> userGetPurchasedItem(Scanner sc, int cookieNum) {
		// TODO Auto-generated method stub
		ArrayList<PurchasedItem> arr = service.selectByUserPurchased(cookieNum);
		return arr;
	}

	public void pay(int pnum) {
		PurchasedItem pi = null;
		ArrayList<PurchasedItem> list = onlyCartList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getP_num() == pnum) {
				pi = list.get(i);
			}
		}
		pi.setP_paid(true);
		boolean b = service.edit(pi);
		if (b == true) {
			System.out.println("결제 성공\n");
		} else {
			System.out.println("결제 실패\n");
		}
	}

	public int cartPay(Scanner sc) {

		ArrayList<PurchasedItem> list = CartList();
		System.out.print("결제하실 책 번호를 입력해주세요(0 : 나가기) :");
		int pnum = sc.nextInt();
		if (pnum == 0) {
			return 0;
		}
		PurchasedItem p = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getP_num() == pnum) {
				p = list.get(i);
			}
		}
		if (p == null) {
			return -1;
		} else {
			return pnum;
		}

	}

	public int selectPNumbyPaid(Scanner sc) {
		// TODO Auto-generated method stub
		int cnt = 1;
		int num;
		ArrayList<PurchasedItem> arr = userGetPurchasedItem(sc, ck.getCookieNum());

		if (arr.isEmpty()) {
			System.out.println("구매한 책이 없습니다.");
			return 0;
		}

		for (PurchasedItem pi : arr) {
			System.out.println("[" + cnt + "번] : " + pi.subString());
			cnt++;
		}
		do {
			System.out.print("읽으실 책을 선택해 주세요 (0 : 나가기) : ");
			num = sc.nextInt();

			if (num == 0) {
				return 0;
			}

			if (num > arr.size() || num < 1) {
				System.out.println("잘못된 값입니다.");
			}
		} while (num > arr.size() || num < 0);

		PurchasedItem pi = arr.get(num - 1);

		return pi.getP_num();
	}

	public void userChooseItemMenu(Scanner sc, ArrayList<Item> arr) {
		// TODO Auto-generated method stub

		boolean flag = true;
		String str = "고르실 아이템을 선택하세요(0 : 나가기) : ";
		int menu;
		while (flag) {
			System.out.print(str);
			menu = sc.nextInt();

			if (menu == 0)
				return;

			else if ((menu > 0) && (menu <= arr.size())) {
				Item i = arr.get(menu - 1);
				if (!service.checkInsertCart(ck.getCookieNum(), i.getP_num())) {
					if (addItem(i)) {
						System.out.println("추가 성공");
						return;
					}
				}
				System.out.println("구매하였거나 장바구니에 있습니다.");
				return;
			}

			System.out.println("해당 제품이 없습니다. 다시 골라주세요.");
		}
	}

	public void userChooseItemMenu(Scanner sc, Item i) {
		// TODO Auto-generated method stub

		boolean flag = true;
		String str = "해당 책을 추가하시겠습니까? (Y/N) : ";
		String menu;
		while (flag) {
			System.out.print(str);
			menu = sc.next();

			if (menu.equals("N") || menu.equals("n"))
				return;

			else if (menu.equals("Y") || menu.equals("y")) {
				if (!service.checkInsertCart(ck.getCookieNum(), i.getP_num())) {
					if (addItem(i)) {
						System.out.println("추가 성공");
					}
				}
				System.out.println("구매하였거나 장바구니에 있습니다.");
				return;
			}

			System.out.println("해당 제품이 없습니다. 다시 골라주세요.");
		}
	}
}