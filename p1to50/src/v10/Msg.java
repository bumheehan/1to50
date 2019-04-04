package v10;
import java.io.*;
import java.util.*;
class Msg implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type =0; // 0 메세지 , 1 시스템 , 2 ready//
	private String content;
	private String name;
	private boolean ready;
	private boolean allReady;
	private int count;
	private ArrayList<String> result;
	private ArrayList<Integer> gA;
	private int imageNum;
	private int idx;
	private Object[][] obj;
	private int mem;


	Msg(int type){
		this.type = type;
	}

	// client 0 채팅 2 ready 3 게임시작 4 게임종료 5 점수

	// server 0 채팅 2 allready 3 전체 게임시작 4 전체 게임종료 5전체 순위
	void setResult(ArrayList<String> result){
		this.result=result;
	}
	ArrayList<String> getResult(){
		return result;
	}

	void setType(int type){
		this.type=type;
	}
	int getType(){
		return type;
	}

	void setContent(String content){
		this.content=content;
	}
	String getContent(){
		return content;
	}
	void setName(String name){
		this.name=name;
	}
	String getName(){
		return name;
	}
	void setReady(boolean ready){
		this.ready=ready;
	}
	boolean getReady(){
		return ready;
	}
	void setAllReady(boolean allReady){
		this.allReady=allReady;
	}
	boolean getAllReady(){
		return allReady;
	}
	void setCount(int count){
		this.count=count;
	}
	int getCount(){
		return count;
	}
	void setImage(int imageNum){
		this.imageNum=imageNum;
	}
	int getImage(){
		return imageNum;
	}
	void setIdx(int idx){
		this.idx=idx;
	}
	int getIdx(){
		return idx;
	}
	void setObj(Object[][] obj){
		this.obj=obj;
	}
	Object[][] getObj(){
		return obj;
	}
	void setUserGame(ArrayList<Integer> gA){
		this.gA=gA;
	}
	ArrayList<Integer> getUserGame(){
		return gA;
	}
	int getMember(){
		return mem;
	}
	void setMember(int mem){
		this.mem=mem;
	}
}
