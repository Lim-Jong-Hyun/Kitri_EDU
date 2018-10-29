package board1;

import java.util.ArrayList;

public interface Board1Dao {

	public boolean insert(Board1 b);

	public boolean update(Board1 b);

	public Board1 selectByNum(int num);

	public ArrayList<Board1> selectByType(int type);

	public ArrayList<Board1> selectByCate(String cate);

	public ArrayList<Board1> selectByTime(String startTime);

	public ArrayList<Board1> selectByStartPosi(String startPosi);

	public ArrayList<Board1> selectByEndPosi(String endPosi);

	public ArrayList<Board1> selectByPrice(int price);

	public ArrayList<Board1> selectByWriter(String writer);

	public ArrayList<Board1> selectAll();

	public boolean updatePassenger(Board1 b);

	public boolean updateDriver(Board1 b);
	
	public boolean delete(int num);

	public ArrayList<Board1> selectByStartEnd(String start_posi, String end_posi);

	public ArrayList<Board1> selectByPassenger(String passenger);

	public ArrayList<Board1> selectByDriver(String driver);

}
