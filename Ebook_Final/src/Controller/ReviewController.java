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
			System.out.println("������ å�� �����ϴ�.");
			return;
		}

		for (PurchasedItem pi : arr) {
			System.out.println("[" + cnt + " ��] : " + pi.toString2());
			cnt++;
		}
		do {
			System.out.print("���並 �ۼ��� ��ȣ�� �Է��� �ּ��� (0 : ������) : ");
			sel = sc.nextInt();

			if (sel == 0) {
				return;
			}

			
			if(sel > arr.size() || sel < 0) {
				System.out.println("�߸��� ���Դϴ�.");
			}
		} while (sel > arr.size() || sel < 0);
		
		if (service.getReviewState(arr.get(sel - 1).getP_num(), m_num)) {
			System.out.println("�̹� �ۼ��� å�Դϴ�.");
			return;
		}

		Review r = new Review();

		r.setM_num(m_num);
		r.setP_num(arr.get(sel - 1).getP_num());

		while (r_score < 1 || r_score > 5) {
			System.out.print("������ �Է����ּ���(1 ~ 5) : ");
			r_score = sc.nextInt();
		}

		r.setR_score(r_score);
		sc.nextLine();
		System.out.println("å�� ���� �������� �Է��� �ּ���");
		System.out.print("�Է� : ");
		r.setR_contents(sc.nextLine());
		//sc.nextLine();
		if (service.insertReview(r)) {
			System.out.println("���� �ۼ� ����");
		} else {
			System.out.println("���� �ۼ� ����");
		}
	}

	public void deleteReview(Scanner sc, int m_num) {
		// TODO Auto-generated method stub

		int cnt = 1;
		int sel = -1;
		ArrayList<Review> arr;
		arr = service.selectByMNum(m_num);

		if (arr.isEmpty()) {
			System.out.println("�ۼ��Ͻ� ���䰡 �����ϴ�.");
			return;
		}

		for (Review pi : arr) {
			System.out.println("[" + cnt + " ��] : " + pi.toSubString2());
			cnt++;
		}

		do {
			System.out.print("�����Ͻ� �����ȣ�� �������ּ��� (0 : ������) : ");
			sel = sc.nextInt();
			if (sel == 0) {
				return;
			}
		} while (sel > arr.size() || sel < 0);
				
		Review r = arr.get(sel - 1);

		if (service.deleteReview(r)) {
			System.out.println("���� ���� ����");
		} else {
			System.out.println("���� ���� ����");
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

	// �����ȣ�� �����ϸ� �ش��ϴ� pnum ��ȯ
	public int choiceReivewPnum(int rnum) {
		Review r = null;
		r = service.selectByRNum(rnum);
		return r.getP_num();
	}

	// �����ȣ �����ϸ� review��ü ��ȯ
	public Review selectByRnum(int rnum) {
		Review r = service.selectByRNum(rnum);
		return r;
	}
}
