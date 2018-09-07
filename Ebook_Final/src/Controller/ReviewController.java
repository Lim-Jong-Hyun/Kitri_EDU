package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import DataType.Item;
import DataType.PurchasedItem;
import DataType.Review;
import Interface.ReviewService;

public class ReviewController {
	private ReviewService service;

	public ReviewController(ReviewService service) {
		this.service = service;
	}

	public void writeReview(Scanner sc, ArrayList<PurchasedItem> arr, int m_num) {
		// TODO Auto-generated method stub

		int cnt = 1;
		int r_score = 0;
		int sel;

		if (arr.isEmpty()) {
			System.out.println("구매한 책이 없습니다.");
			return;
		}

		for (PurchasedItem pi : arr) {
			System.out.println("[" + cnt + " 번] : " + pi.toString2());
			cnt++;
		}
		do {
			System.out.print("리뷰를 작성할 번호를 입력해 주세요 (0 : 나가기) : ");
			sel = sc.nextInt();

			if (sel == 0) {
				return;
			}

			
			if(sel > arr.size() || sel < 0) {
				System.out.println("잘못된 값입니다.");
			}
		} while (sel > arr.size() || sel < 0);
		
		if (service.getReviewState(arr.get(sel - 1).getP_num(), m_num)) {
			System.out.println("이미 작성한 책입니다.");
			return;
		}

		Review r = new Review();

		r.setM_num(m_num);
		r.setP_num(arr.get(sel - 1).getP_num());

		while (r_score < 1 || r_score > 5) {
			System.out.print("별점을 입력해주세요(1 ~ 5) : ");
			r_score = sc.nextInt();
		}

		r.setR_score(r_score);
		sc.nextLine();
		System.out.println("책에 대한 한줄평을 입력해 주세요");
		System.out.print("입력 : ");
		r.setR_contents(sc.nextLine());
		//sc.nextLine();
		if (service.insertReview(r)) {
			System.out.println("리뷰 작성 성공");
		} else {
			System.out.println("리뷰 작성 실패");
		}
	}

	public void deleteReview(Scanner sc, int m_num) {
		// TODO Auto-generated method stub

		int cnt = 1;
		int sel = -1;
		ArrayList<Review> arr;
		arr = service.selectByMNum(m_num);

		if (arr.isEmpty()) {
			System.out.println("작성하신 리뷰가 없습니다.");
			return;
		}

		for (Review pi : arr) {
			System.out.println("[" + cnt + " 번] : " + pi.toSubString2());
			cnt++;
		}

		do {
			System.out.print("삭제하실 리뷰번호를 선택해주세요 (0 : 나가기) : ");
			sel = sc.nextInt();
			if (sel == 0) {
				return;
			}
		} while (sel > arr.size() || sel < 0);
				
		Review r = arr.get(sel - 1);

		if (service.deleteReview(r)) {
			System.out.println("리뷰 삭제 성공");
		} else {
			System.out.println("리뷰 삭제 실패");
		}
	}

	public void showAllList(Item i, int k) {
		ArrayList<Review> list = service.showAll();
		System.out.print(list.get(k).toSubString());
		System.out.println(i.toSubString());

	}

	public ArrayList<Integer> getPname() {
		ArrayList<Review> list = service.showAll();
		ArrayList<Integer> pnum = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			pnum.add(list.get(i).getP_num());
		}
		return pnum;
	}

	// 리뷰번호를 선택하면 해당하는 pnum 반환
	public int choiceReivewPnum(int rnum) {
		Review r = null;
		r = service.selectByRNum(rnum);
		return r.getP_num();
	}

	// 리뷰번호 선택하면 review객체 반환
	public Review selectByRnum(int rnum) {
		Review r = service.selectByRNum(rnum);
		return r;
	}
}
