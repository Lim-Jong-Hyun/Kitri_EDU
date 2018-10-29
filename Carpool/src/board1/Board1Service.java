package board1;

import java.util.ArrayList;

public interface Board1Service {
	
	public boolean add(Board1 b);

	public boolean edit(Board1 b);

	public Board1 getByNum(int num);

	public ArrayList<Board1> getByCate(String cate);

	public ArrayList<Board1> getByTime(String startTime);

	public ArrayList<Board1> getByStartPosi(String startPosi);

	public ArrayList<Board1> getByEndPosi(String endPosi);

	public ArrayList<Board1> getByType(int type);

	public ArrayList<Board1> getByPrice(int price);

	public ArrayList<Board1> getByWriter(String writer);

	public ArrayList<Board1> getAll();
	
	public boolean remove(int num);
	
	public boolean editPassenger(Board1 b);
	
	public boolean editDriver(Board1 b);

	public ArrayList<Board1> getByStartEnd(String start_posi, String end_posi);

	public ArrayList<Board1> getByDriver(String driver);

	public ArrayList<Board1> getByPassenger(String passenger);
}
