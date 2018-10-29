package DesignPattern;

public class PathInfo {
	private static PathInfo pi = new PathInfo();
	
	private String path = "C:\\Users\\KITRI\\Desktop\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\cp\\img\\";
	public PathInfo() {
		
	}
	
	public static PathInfo getInstance() {
		return pi;
	}
	
	public String getPath() {
		return path;
	}
}
