/**
 * 
 */

function Data(kind,ylink,title,contents, pwd){
	this.kind=kind;
	this.ylink =ylink;
	this.title = title;
	this.contents = contents;
	this.pwd =pwd;
	
	var d = new Date();
	var y = d.getYear() + 1900;
	var mon = d.getMonth() + 1;
	var date = d.getDate();
	var t = d.getHours();
	var m = d.getMinutes();
	var s = d.getSeconds();
	var timeStr = y + "/" + mon + "/" + date ;
	timeStr += "(" + t + ":" + m + ":" + s + ")<br/>";
	this.w_date = timeStr;
	
}