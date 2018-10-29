package board2;

import java.util.ArrayList;

public interface Board2Dao {

	public boolean insert(Board2 b);

	public boolean update(Board2 b);

	public Board2 selectByNum(int num);
	
	public ArrayList<Board2> selectByType(int type);

	public ArrayList<Board2> selectByWriter(String writer);

	public ArrayList<Board2> selectAll();
	
	public boolean delete(int num);

}
