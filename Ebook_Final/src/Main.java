import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in, "euc-kr");
		Menu m = new Menu();
		m.run(sc);
		sc.close();
	}
}