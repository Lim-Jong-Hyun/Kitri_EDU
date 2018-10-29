package board2;

import java.util.ArrayList;

public interface Board2Service {
	
	public boolean add(Board2 b);

	public boolean edit(Board2 b);

	public Board2 gettByNum(int num);

	public ArrayList<Board2> getByType(int type);
	
	public ArrayList<Board2> getByWriter(String writer);

	public ArrayList<Board2> getAll();
	
	public boolean remove(int num);
}
