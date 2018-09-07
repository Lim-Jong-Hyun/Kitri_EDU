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

	// 회원가입 및 로그인 메뉴
	public void run(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          KITRI E-BOOK STORE                           |");
			System.out.println("|                                                                       |");
			System.out.println("|                            1. 로그인                                  |");
			System.out.println("|                            2. 회원가입                                |");
			System.out.println("|                            0. 프로그램 종료                           |");
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

	// 유저메뉴
	public void userMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 책 리스트                                 |");
			System.out.println("|                          2. 장바구니                                  |");
			System.out.println("|                          3. 소지한 책                                 |");
			System.out.println("|                          4. 계좌 관리                                 |");
			System.out.println("|                          5. 정보 변경                                 |");
			System.out.println("|                          6. 회원탈퇴                                  |");
			System.out.println("|                          0. 로그아웃                                  |");
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
					System.out.println("계좌 잔고가 남아서 탈퇴가 불가능합니다.");
				}
				break;
			case 0:
				ck.setCookieId("");
				ck.setCookieNum(0);
				flag = false;
				break;
			default:
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 구매한 책 메뉴
	public void userInvenMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 책 읽기                                   |");
			System.out.println("|                          2. 책 리뷰쓰기                               |");
			System.out.println("|                          3. 내 리뷰 삭제하기                          |");
			System.out.println("|                          0. 이전 메뉴                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 책 리스트 메뉴
	public void userItemMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          USER MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 책 검색                                   |");
			System.out.println("|                          2. 책 리뷰보기                               |");
			System.out.println("|                          0. 이전 메뉴                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 장바구니 메뉴
	public void userCartMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          CART MENU                                    |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 장바구니 리스트                           |");
			System.out.println("|                          2. 장바구니 삭제                             |");
			System.out.println("|                          3. 결제 하기                                 |");
			System.out.println("|                          0. 이전 메뉴                                 |");
			System.out.println("|                                                                       |");
			System.out.println("+-----------------------------------------------------------------------+");
			String str = ck.getCookieId() + "$>";
			System.out.print(str);
			menu = sc.nextInt();
			switch (menu) {
			case 1:
				if (pc.CartList().isEmpty()) {
					System.out.println("장바구니가 비었습니다.");
				}
				break;
			case 2:
				if (!pc.checkCartList().isEmpty()) {
					pc.deleteCart(sc);
				} else {
					System.out.println("장바구니가 비었습니다.");
				}
				break;
			case 3:
				if (!pc.checkCartList().isEmpty()) {
					userPayCartMenu(sc);
				} else {
					System.out.println("장바구니가 비었습니다.");
				}
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 리뷰 메뉴
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
			System.out.print("세부내용을 보실 리뷰를 선택하세요 (0 : 나가기) : ");
			int rnum = sc.nextInt();
			r = rc.selectByRnum(rnum);
			if (rnum == 0) {
				return;
			} else {
				if (r == null) {
					System.out.println("없는 리뷰번호입니다.");
				} else {
					System.out.println("========선택하신 책의 리뷰입니다=======");
					System.out.println(r.toSubString3() + "\n");

				}
			}
		}
	}

	// 유저 : 계좌관리 메뉴
	public void userAccountMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ACCOUNT MENU                                 |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 계좌 확인                                 |");
			System.out.println("|                          2. 입금                                      |");
			System.out.println("|                          3. 출금                                      |");
			System.out.println("|                          0. 이전 메뉴                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 책검색 메뉴
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
			System.out.println("|                          1. 제품번호 검색                             |");
			System.out.println("|                          2. 이름 검색                                 |");
			System.out.println("|                          3. 저자 검색                                 |");
			System.out.println("|                          4. 가격 검색                                 |");
			System.out.println("|                          5. 장르 검색                                 |");
			System.out.println("|                          6. 전체 보기                                 |");
			System.out.println("|                          0. 이전 메뉴                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 관리자 메뉴
	public void adminMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MENU                                   |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 책 관리                                   |");
			System.out.println("|                          2. 계좌 관리                                 |");
			System.out.println("|                          3. 사용자 관리                               |");
			System.out.println("|                          0. 로그 아웃                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 관리자 : 책관리 메뉴
	public void adminItemMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN BOOK MENU                              |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 책 목록보기                               |");
			System.out.println("|                          2. 책 추가하기                               |");
			System.out.println("|                          3. 책 수정하기                               |");
			System.out.println("|                          4. 책 삭제하기                               |");
			System.out.println("|                          0. 뒤로가기                                  |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 관리자 : 계좌 관리 메뉴
	public void adminAccountMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN ACCOUNT MENU                           |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 계좌 확인                                 |");
			System.out.println("|                          2. 입금                                      |");
			System.out.println("|                          3. 출금                                      |");
			System.out.println("|                          0. 이전 메뉴                                 |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 관리자 : 사용자 관리메뉴
	public void adminMemberMenu(Scanner sc) {
		boolean flag = true;
		int menu;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MEMBER MENU                            |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 전체 사용자 보기                          |");
			System.out.println("|                          2. 사용자 검색                               |");
			System.out.println("|                          3. 사용자 구매 내역                          |");
			System.out.println("|                          4. 전체 구매 내역                            |");
			System.out.println("|                          0. 뒤로가기                                  |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 관리자 : 사용자 검색 메뉴
	public void adminSearchMemberMenu(Scanner sc) {
		int menu;
		boolean flag = true;
		while (flag) {
			System.out.println("+-----------------------------------------------------------------------+");
			System.out.println("|                                                                       |");
			System.out.println("|                          ADMIN MEMBER MENU                            |");
			System.out.println("|                                                                       |");
			System.out.println("|                          1. 가입번호로 검색                           |");
			System.out.println("|                          2. ID로 검색                                 |");
			System.out.println("|                          3. 이름으로 검색                             |");
			System.out.println("|                          0. 뒤로가기                                  |");
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
				System.out.println("적절한 값을 입력하세요");
				break;
			}
		}
	}

	// 유저 : 결제하는 기능메뉴
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
				System.out.println("장바구니에 없는 책 번호입니다.");
			} else {
				i = ic.selectByPnum(pnum);
				if (i.getP_price() > a.getA_balance()) {
					System.out.println("잔액이 부족하여 결제 실패");
					break;
				} else {
					ac.withdrawByBook(ck.getCookieNum(), i.getP_price());
					pc.pay(pnum);
				}
			}
		}
	}
}