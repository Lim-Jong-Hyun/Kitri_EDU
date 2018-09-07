import java.util.ArrayList;
import java.util.Scanner;
import Controller.AccountController;
import Controller.ItemController;
import Controller.MemberController;
import Controller.PurchasedItemController;
import Controller.ReviewController;
import DaoImpl.AccountDaoImpl;
import DaoImpl.ItemDaoImpl;
import DaoImpl.MemberDaoImpl;
import DaoImpl.PurchasedItemDaoImpl;
import DaoImpl.ReviewDaoImpl;
import DataType.Account;
import DataType.Item;
import DataType.Review;
import DesignPattern.Cookie;
import ServiceImpl.AccountServiceImpl;
import ServiceImpl.ItemServiceImpl;
import ServiceImpl.MemberServiceImpl;
import ServiceImpl.PurchasedItemServiceImpl;
import ServiceImpl.ReviewServiceImpl;

public class Menu {
	private AccountController ac;
	private ItemController ic;
	private MemberController mc;
	private PurchasedItemController pc;
	private ReviewController rc;
	private Cookie ck;

	public Menu() {
		this.ac = new AccountController(new AccountServiceImpl(new AccountDaoImpl()));
		this.ic = new ItemController(new ItemServiceImpl(new ItemDaoImpl()));
		this.mc = new MemberController(new MemberServiceImpl(new MemberDaoImpl()));
		this.pc = new PurchasedItemController(new PurchasedItemServiceImpl(new PurchasedItemDaoImpl()));
		this.rc = new ReviewController(new ReviewServiceImpl(new ReviewDaoImpl()));
		ck = Cookie.getInstance();
	}

	// ȸ������ �� �α��� �޴�
	public void run(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          KITRI E-BOOK STORE                           |");
			System.out.println("|                                                                       |");
			System.out.println("|                            1. �α���                                  |");
			System.out.println("|                            2. ȸ������                                |");
			System.out.println("|                            0. ���α׷� ����                           |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "Input$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				if (mc.login(sc)) {
					if (mc.checkType()) {
						adminMenu(sc);
					} else {
						userMenu(sc);
					}
				}
				break;
			case 2:
				if (mc.join(sc)) {
					ac.addAccount(sc, mc.getMemberById(ck.getCookieId()));
					ck.setCookieId("");
				}
				break;
			case 0:
				flag = false;
				break;
			}
		}
	}

	// �����޴�
	public void userMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. å ����Ʈ                                 |");
			System.out.println("|                          2. ��ٱ���                                  |");
			System.out.println("|                          3. ������ å                                 |");
			System.out.println("|                          4. ���� ����                                 |");
			System.out.println("|                          5. ���� ����                                 |");
			System.out.println("|                          6. ȸ��Ż��                                  |");
			System.out.println("|                          0. �α׾ƿ�                                  |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				userItemMenu(sc);
				break;
			case 2:
				userCartMenu(sc);
				break;
			case 3:
				userInvenMenu(sc);
				break;
			case 4:
				userAccountMenu(sc);
				break;
			case 5:
				mc.editMyInfo(sc);
				break;
			case 6:
				if (ac.checkAccount(ck.getCookieNum()).getA_balance() == 0) {
					if (mc.out(sc)) {
						ck.setCookieId("");
						ck.setCookieNum(0);
						flag = false;
					}
				} else {
					System.out.println("���� �ܰ� ���Ƽ� Ż�� �Ұ����մϴ�.");
				}
				break;
			case 0:
				ck.setCookieId("");
				ck.setCookieNum(0);
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : ������ å �޴�
	public void userInvenMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. å �б�                                   |");
			System.out.println("|                          2. å ���侲��                               |");
			System.out.println("|                          3. �� ���� �����ϱ�                          |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				int tmp = pc.selectPNumbyPaid(sc);
				if (0 != tmp)
					ic.readItem(tmp);
				break;
			case 2:
				rc.writeReview(sc, pc.userGetPurchasedItem(sc, ck.getCookieNum()), ck.getCookieNum());
				break;
			case 3:
				rc.deleteReview(sc, ck.getCookieNum());
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : å ����Ʈ �޴�
	public void userItemMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. å �˻�                                   |");
			System.out.println("|                          2. å ���亸��                               |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				userSearchItemMenu(sc);
				break;
			case 2:
				userReviewMenu(sc);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : ��ٱ��� �޴�
	public void userCartMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          CART MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ��ٱ��� ����Ʈ                           |");
			System.out.println("|                          2. ��ٱ��� ����                             |");
			System.out.println("|                          3. ���� �ϱ�                                 |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				if (pc.CartList().isEmpty()) {
					System.out.println("��ٱ��ϰ� ������ϴ�.");
				}
				break;
			case 2:
				if (!pc.checkCartList().isEmpty()) {
					pc.deleteCart(sc);
				} else {
					System.out.println("��ٱ��ϰ� ������ϴ�.");
				}
				break;
			case 3:
				if (!pc.checkCartList().isEmpty()) {
					userPayCartMenu(sc);
				} else {
					System.out.println("��ٱ��ϰ� ������ϴ�.");
				}
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : ���� �޴�
	public void userReviewMenu(Scanner sc) {
		boolean flag = true;
		Item i = null;
		Review r = null;
		ArrayList<Integer> pnums = rc.getPname();

		while (flag) {
			for (int k = 0; k < pnums.size(); k++) {
				i = ic.selectByPnum(pnums.get(k));
				rc.showAllList(i, k);
			}
			System.out.print("���γ����� ���� ���並 �����ϼ��� (0 : ������) : ");
			int rnum = sc.nextInt();
			r = rc.selectByRnum(rnum);
			if (rnum == 0) {
				return;
			} else {
				if (r == null) {
					System.out.println("���� �����ȣ�Դϴ�.");
				} else {
					System.out.println("========�����Ͻ� å�� �����Դϴ�=======");
					System.out.println(r.toSubString3() + "\n");

				}
			}
		}
	}

	// ���� : ���°��� �޴�
	public void userAccountMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ACCOUNT MENU                                 |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ���� Ȯ��                                 |");
			System.out.println("|                          2. �Ա�                                      |");
			System.out.println("|                          3. ���                                      |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				ac.checkAccount(sc, ck.getCookieNum());
				break;
			case 2:
				ac.depositAccount(sc, mc.getMemberByNum(ck.getCookieNum()));
				break;
			case 3:
				ac.withdrawAccount(sc, mc.getMemberByNum(ck.getCookieNum()));
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : å�˻� �޴�
	public void userSearchItemMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		ArrayList<Item> arr = null;
		Item i = null;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          BOOK MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ��ǰ��ȣ �˻�                             |");
			System.out.println("|                          2. �̸� �˻�                                 |");
			System.out.println("|                          3. ���� �˻�                                 |");
			System.out.println("|                          4. ���� �˻�                                 |");
			System.out.println("|                          5. �帣 �˻�                                 |");
			System.out.println("|                          6. ��ü ����                                 |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				i = ic.searchByNum(sc);
				if (i != null)
					pc.userChooseItemMenu(sc, i);
				break;
			case 2:
				arr = ic.searchByName(sc);
				if (!arr.isEmpty())
					pc.userChooseItemMenu(sc, arr);
				break;
			case 3:
				arr = ic.searchByWriter(sc);
				if (!arr.isEmpty())
					pc.userChooseItemMenu(sc, arr);
				break;
			case 4:
				arr = ic.searchByPrice(sc);
				if (!arr.isEmpty())
					pc.userChooseItemMenu(sc, arr);
				break;
			case 5:
				arr = ic.searchByGenre(sc);
				if (!arr.isEmpty())
					pc.userChooseItemMenu(sc, arr);
				break;
			case 6:
				arr = ic.getAll();
				if (!arr.isEmpty())
					pc.userChooseItemMenu(sc, arr);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ������ �޴�
	public void adminMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MENU                                   |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. å ����                                   |");
			System.out.println("|                          2. ���� ����                                 |");
			System.out.println("|                          3. ����� ����                               |");
			System.out.println("|                          0. �α� �ƿ�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "admin#>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				adminItemMenu(sc);
				break;
			case 2:
				adminAccountMenu(sc);
				break;
			case 3:
				adminMemberMenu(sc);
				break;
			case 0:
				ck.setCookieId("");
				ck.setCookieNum(0);
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ������ : å���� �޴�
	public void adminItemMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN BOOK MENU                              |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. å ��Ϻ���                               |");
			System.out.println("|                          2. å �߰��ϱ�                               |");
			System.out.println("|                          3. å �����ϱ�                               |");
			System.out.println("|                          4. å �����ϱ�                               |");
			System.out.println("|                          0. �ڷΰ���                                  |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "admin#>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				ic.showAllList();
				break;
			case 2:
				ic.addItemByConsole(sc);
				break;
			case 3:
				ic.editItem(sc);
				break;
			case 4:
				ic.deleteItem(sc);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ������ : ���� ���� �޴�
	public void adminAccountMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN ACCOUNT MENU                           |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ���� Ȯ��                                 |");
			System.out.println("|                          2. �Ա�                                      |");
			System.out.println("|                          3. ���                                      |");
			System.out.println("|                          0. ���� �޴�                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "admin#>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				ac.checkAccount(sc, ck.getCookieNum());
				break;
			case 2:
				ac.depositAccount(sc, mc.getMemberByNum(ck.getCookieNum()));
				break;
			case 3:
				ac.withdrawAccount(sc, mc.getMemberByNum(ck.getCookieNum()));
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ������ : ����� �����޴�
	public void adminMemberMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MEMBER MENU                            |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ��ü ����� ����                          |");
			System.out.println("|                          2. ����� �˻�                               |");
			System.out.println("|                          3. ����� ���� ����                          |");
			System.out.println("|                          4. ��ü ���� ����                            |");
			System.out.println("|                          0. �ڷΰ���                                  |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "admin#>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				mc.ShowAllUser();
				break;
			case 2:
				adminSearchMemberMenu(sc);
				break;
			case 3:
				pc.purchasedList(sc);
				break;
			case 4:
				pc.allPurchasedList();
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ������ : ����� �˻� �޴�
	public void adminSearchMemberMenu(Scanner sc) {
		int menu;
		boolean flag = true;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MEMBER MENU                            |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. ���Թ�ȣ�� �˻�                           |");
			System.out.println("|                          2. ID�� �˻�                                 |");
			System.out.println("|                          3. �̸����� �˻�                             |");
			System.out.println("|                          0. �ڷΰ���                                  |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = "admin#>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				mc.selectByNum(sc);
				break;
			case 2:
				mc.selectById(sc);
				break;
			case 3:
				mc.selectByName(sc);
				break;
			/*
			 * case 4: mc.selectByDate(sc); break;
			 */
			case 0:
				flag = false;
				break;
			default:
				System.out.println("������ ���� �Է��ϼ���");
				break;
			}
		}
	}

	// ���� : �����ϴ� ��ɸ޴�
	public void userPayCartMenu(Scanner sc) {
		boolean flag = true;
		int pnum;
		int login_num = ck.getCookieNum();
		Item i = null;
		Account a = ac.checkAccount(login_num);

		while (flag) {
			pnum = pc.cartPay(sc);
			if (pnum == 0) {
				break;
			} else if (pnum == -1) {
				System.out.println("��ٱ��Ͽ� ���� å ��ȣ�Դϴ�.");
			} else {
				i = ic.selectByPnum(pnum);
				if (i.getP_price() > a.getA_balance()) {
					System.out.println("�ܾ��� �����Ͽ� ���� ����");
					break;
				} else {
					ac.withdrawByBook(ck.getCookieNum(), i.getP_price());
					pc.pay(pnum);
				}
			}
		}
	}
}