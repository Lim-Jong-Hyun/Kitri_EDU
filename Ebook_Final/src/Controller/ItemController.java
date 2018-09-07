package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DataType.Item;
import Interface.ItemService;

public class ItemController {
	private ItemService service;

	public ItemController(ItemService service) {
		this.service = service;
	}

	public void addItem(Scanner sc) {
		System.out.print("å �̸� : ");
		String p_name = sc.next();
		System.out.print("å ���ǳ⵵(YYYY) : ");
		String p_public = sc.next();
		System.out.print("å ���� : ");
		String p_writer = sc.next();
		System.out.print("å �帣 : ");
		String p_genre = sc.next();
		System.out.print("å ���ǻ� : ");
		String p_publisher = sc.next();
		System.out.print("å ���ǳ⵵ : ");
		String p_summary = sc.next();
		System.out.print("å �����̸� : ");
		String p_fileName = sc.next();
		System.out.print("å ���� : ");
		int p_price = sc.nextInt();

		Item i = new Item(0, p_name, p_public, p_writer, p_genre, p_publisher, p_summary, p_fileName, p_price);
		if (service.addItem(i)) {
			System.out.println("å �߰� ����");
		} else {
			System.out.println("å �߰� ����");
		}
	}

	public void editItem(Scanner sc) {
		int num;
		Item i;
		String p_public;
		showAllList();
		do {
			System.out.print("������ å�� ��� ��ȣ�� �Է��ϼ��� (0 : ������) : ");
			num = sc.nextInt();
			i = service.selectByPNum(num);
			if (num == 0) {
				return;
			}
		} while (i == null);

		sc.nextLine();
		System.out.print("������ å �̸� : ");
		String p_name = sc.nextLine();
		do {
			System.out.print("������ å ���ǳ⵵(YYYY) : ");
			p_public = sc.nextLine();
		} while (p_public.length() != 4);
		System.out.print("������ å ���� : ");
		String p_writer = sc.nextLine();
		System.out.print("������ å �帣 : ");
		String p_genre = sc.nextLine();
		System.out.print("������ å ���ǻ� : ");
		String p_publisher = sc.nextLine();
		System.out.print("������ å ����(��) : ");
		int p_price = sc.nextInt();
		sc.nextLine();
		System.out.print("������ å ��� : ");
		String p_summary = sc.nextLine();
		
		i.setP_name(p_name);
		i.setP_public(p_public);
		i.setP_writer(p_writer);
		i.setP_genre(p_genre);
		i.setP_publisher(p_publisher);
		i.setP_summary(p_summary);
		i.setP_price(p_price);

		boolean b = service.edit(i);

		if (b == true) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("���� ����");
		}
	}

	public void deleteItem(Scanner sc) {
		showAllList();
		System.out.print("������ å�� ��� ��ȣ�� �Է��ϼ��� (0 : ������) : ");
		int num = sc.nextInt();
		if (service.delete(num)) {
			System.out.println("���� �Ϸ�");
		} else {
			System.out.println("�ش��ϴ� å�� �����ϴ�.");
		}
	}

	public void addItemByConsole(Scanner sc) {

		Item i = new Item();

		sc.nextLine();
		System.out.println("�ءء�å ������ /src/Book ������ �־�� �մϴ١ءء�");

		System.out.print("å �����̸� : ");
		String p_fileName = sc.nextLine();
		File file = new File("src/Book/" + p_fileName);

		if (file.isFile()) {
			System.out.println("������ Ȯ�εǾ����ϴ�.");
		} else {
			System.out.println("������ �����ϴ�. ����ȭ������ ���ư��ϴ�.");
			return;
		}
		System.out.print("å �̸� : ");
		String p_name = sc.nextLine();
		System.out.print("å ���ǳ⵵(YYYY) : ");
		String p_public = sc.nextLine();
		System.out.print("å ���� : ");
		String p_writer = sc.nextLine();
		System.out.print("å �帣 : ");
		String p_genre = sc.nextLine();
		System.out.print("å ���ǻ� : ");
		String p_publisher = sc.nextLine();
		System.out.print("å ����(��) : ");
		int p_price = sc.nextInt();
		sc.nextLine();
		System.out.print("������ å ��� : ");
		String p_summary = sc.nextLine();

		i.setP_name(p_name);
		i.setP_publisher(p_publisher);
		i.setP_public(p_public);
		i.setP_writer(p_writer);
		i.setP_genre(p_genre);
		i.setP_fileName(p_fileName);
		i.setP_summary(p_summary);
		i.setP_price(p_price);
		boolean b = service.add(i);

		if (b == true) {
			System.out.println("å �߰� �Ϸ�");
		} else {
			System.out.println("å �߰� ����");
		}

	}

	public void showAllList() {
		ArrayList<Item> list = new ArrayList<Item>();
		list = service.selectAll();

		for (Item i : list) {
			System.out.println(i);
		}
	}

	public Item searchByNum(Scanner sc) {
		System.out.print("å ��ȣ : ");
		int p_num = sc.nextInt();
		Item i = new Item();
		i.setP_num(p_num);
		i = service.selectByPNum(i);
		if (i != null) {
			System.out.print("[������ å] ");
			System.out.println(i.toSubString2());
			return i;
		} else {
			System.out.println("�ش� å�� �����ϴ�.");
			return null;
		}
	}

	public ArrayList<Item> searchByName(Scanner sc) {
		System.out.print("å �̸� : ");
		String p_name = sc.next();
		Item i = new Item();
		i.setP_name(p_name);
		ArrayList<Item> arr = new ArrayList<Item>();
		arr = service.searchByPName(i);
		if (!arr.isEmpty()) {
			int num = 1;
			for (Item tmp : arr) {
				System.out.print("[" + num + " ��] ");
				System.out.println(tmp.toSubString2());
				num++;
			}
		} else {
			System.out.println("�ش��ϴ� �̸��� ��ǰ�� �����ϴ�.");
		}
		return arr;
	}

	public ArrayList<Item> searchByWriter(Scanner sc) {
		System.out.print("å ���� : ");
		String p_writer = sc.next();
		Item i = new Item();
		i.setP_writer(p_writer);
		ArrayList<Item> arr = new ArrayList<Item>();
		arr = service.searchByPWriter(i);
		if (!arr.isEmpty()) {
			int num = 1;
			for (Item tmp : arr) {
				System.out.print("[" + num + " ��] ");
				System.out.println(tmp.toSubString2());
				num++;
			}
		} else {
			System.out.println("�ش��ϴ� ������ ��ǰ�� �����ϴ�.");
		}
		return arr;
	}

	public ArrayList<Item> searchByGenre(Scanner sc) {
		System.out.print("å �帣 : ");
		String p_genre = sc.next();
		Item i = new Item();
		i.setP_genre(p_genre);
		ArrayList<Item> arr = new ArrayList<Item>();
		arr = service.searchByPGenre(i);
		if (!arr.isEmpty()) {
			int num = 1;
			for (Item tmp : arr) {
				System.out.print("[" + num + " ��] ");
				System.out.println(tmp.toSubString2());
				num++;
			}
		} else {
			System.out.println("�ش��ϴ� �帣�� ��ǰ�� �����ϴ�.");
		}
		return arr;
	}

	public ArrayList<Item> searchByPrice(Scanner sc) {
		System.out.print("å ���� : ");
		int p_price = sc.nextInt();
		Item i = new Item();
		i.setP_price(p_price);
		ArrayList<Item> arr = new ArrayList<Item>();
		arr = service.searchByPPrice(i);
		if (!arr.isEmpty()) {
			int num = 1;
			for (Item tmp : arr) {
				System.out.print("[" + num + " ��] ");
				System.out.println(tmp.toSubString2());
				num++;
			}
		} else {
			System.out.println("�ش��ϴ� ������ ��ǰ�� �����ϴ�.");
		}
		return arr;
	}

	public void ownGetBook(Scanner sc) {
		System.out.println("�˻��� å����:");
		String p_name = sc.nextLine();
		Item g = service.ownGetBook(p_name);
		if (g == null) {
			System.out.println("���� å�̸�");
		} else {
			System.out.println(g);
		}
	}

	public ArrayList<Item> getAll() {
		ArrayList<Item> arr = service.getAll();
		int num = 1;
		if (!arr.isEmpty()) {
			for (Item tmp : arr) {
				System.out.print("[" + num + " ��] ");
				System.out.println(tmp.toSubString2());
				num++;
			}
		} else {
			System.out.println("å�� �����ϴ�.");
		}
		return arr;
	}

	public Item selectByPnum(int pnum) {
		Item i = service.selectByPNum(pnum);
		return i;
	}

	public void readItem(int num) {
		// TODO Auto-generated method stub
		Item i = service.selectByPNum(num);
		File f = new File("src/Book/" + i.getP_fileName());
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String str = null;

			System.out.println("");
			System.out.println("==================================================================");
			System.out.println("");
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}

			System.out.println("");
			System.out.println("==================================================================");
			System.out.println("");

			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
