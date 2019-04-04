package v10;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

class  Server
{
	ServerSocket ss ;
	int port =7000 ;
	final int MAX_PEOPLE = 4;

	CopyOnWriteArrayList<ServerM> cArr; //사람 인원 리스트
	boolean shutdown = true;
	Thread acceptThread;
	Rank r;

	boolean init(){
		cArr = new CopyOnWriteArrayList<ServerM>();
		try{
			ss = new ServerSocket(port);
			accept();
			return true;
		}catch(IOException ie){
			Setting.printLog("서버 열기 오류");
			return false;
		}
	}
	void accept(){

		if (ss == null){
			try{
				ss = new ServerSocket(port); //close 하면 객체 재활용 불가
			}catch(IOException ie){
				Setting.printLog("서버 열기 오류");
			}
		}

		acceptThread = new Thread(){ //acceptThread 초기화
			public void run(){
				try{
					Setting.printLog("대기중");
					Socket soc = ss.accept();   //락
					Setting.printLog("클라이언트 서버 접속");
					ServerM c = new ServerM(soc,Server.this);
					cArr.add(c);
					setIdx();
					c.chkReady(); // 들어올때  Ready체크

					if (cArr.size()<MAX_PEOPLE){
						accept();
					}else{
						Setting.printLog("꽉참");
						ss.close();
						ss=null; // 닫으면 다시열어야하기때문에 null
					}
				}
				catch (IOException ie){
					Setting.printLog("accept() 오류");
				}
			}
		};
		acceptThread.start();
	}
	int findIdx(String name){
		int idx =-1;
		for (ServerM sm : cArr){
			if (sm.name.equals(name)){
				idx = sm.idx;
				break;
			}
		}
		return idx;
	}
	void sendAll(Msg m){
		for (ServerM sm : cArr){
			sm.send(m);
		}
	}
	void sendIdx(Msg m,int idx){ //특정인한테 보내기
		ServerM sm = cArr.get(idx);
		sm.send(m);
	}
	void sendEnterMsg(String name){
		Msg m = new Msg(1);
		m.setContent(name+"님이 들어왔습니다.");
		sendAll(m);
	}
	void sendExitMsg(String name){
		Msg m = new Msg(1);
		m.setContent(name+"님이 나갔습니다.");
		sendAll(m);
	}
	void sendKickOutMsg(String name){
		Msg m = new Msg(1);
		m.setContent(name+"님이 강퇴되셨습니다.");
		sendAll(m);
	}
	void sendState(){
		Msg m = new Msg(5);

		//for에 for 쓰면 곱하기 4x4 16번 명령 수행
		//for 끝나고 다시 for 쓰면 4+4 8번 명령수행
		//서버에서는 불필요한 계산 방지 나머지 클라이언트에서 계산

		Object[][] iA = new Object[MAX_PEOPLE][4]; //추가만할거라 copy on write 쓸필요없음
		int i =0;
		for (ServerM sm : cArr){

			Object obj[]= new Object[4];
			obj[0]=sm.imageNum;
			obj[1]=sm.name;
			obj[2]=sm.ready;
			obj[3]=sm.idx;
			iA[i]=obj;
			i++;
		}
		m.setObj(iA);
		sendAll(m);
	}
	boolean chkInGame(){
		boolean InGame =false;
		for (ServerM sm : cArr){
			if (sm.InGame){
				InGame =true;
				break;
			}
		}
		return InGame;
	}
	void setIdx(){ //나가거나 들어올때
		int idx =0;
		Msg m = new Msg(8);
		for (ServerM sm : cArr){
			sm.idx = idx++;
			m.setIdx(sm.idx);
			sm.send(m);
		}
	}
	void closeAll(){
		for (ServerM sm : cArr){
			sm.close();
		}
		if (acceptThread!=null){
			try{
				ss.close();
			}
			catch (IOException ie){
			}
			acceptThread.interrupt();
		}
	}
	//게임만들기
	ArrayList<Integer> getGameArr(){
		Random r = new Random();
		ArrayList<Integer> list = new ArrayList<Integer>();
		while (true){
			int rand = r.nextInt(25)+1;
			boolean chk = true;
			for (int i :list){
				if (i==rand){
					chk =false;
					break;
				}
			}
			if (chk) list.add(rand);
			if (list.size()==25) break;
		}
		while (true){
			int rand = r.nextInt(25)+26;
			boolean chk = true;
			for (int i :list){
				if (i==rand){
					chk =false;
					break;
				}
			}
			if (chk) list.add(rand);
			if (list.size()==50) break;
		}
		//list  1~25 , 26~50
		return list;
	}
}
class ServerM{

	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Thread receiveThread;
	boolean receiveThreadShutdown = true;
	Server s;

	boolean ready = false;
	String name = "Host";
	String rname = "Host";
	int imageNum =0;
	boolean InGame = false;
	int idx = 0;

	ServerM(Socket soc, Server s){
		this.soc = soc;
		this.s = s;

		try{
			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());

			receiveThread = new Thread(){
				public void run(){
					while (receiveThreadShutdown){
						receive();
					}
				}
			};
			receiveThread.start();
		}
		catch (IOException ie){
			Setting.printLog("ServerM -> Object Stream");
		}
	}
	void receive(){
		try{
			Msg m = (Msg)ois.readObject();  //락
			checkMsg(m);
		}catch (ClassNotFoundException ce){
			Setting.printLog("Msg 클래스X");
		}
		catch (IOException ie){
			//나감
			exitGame();
		}
	}
	void exitGame(){
			Setting.printLog(name+"나감");
			s.cArr.remove(this);

			s.setIdx();
			chkReady();//나갈때 ready체크
			s.sendState();
			if (InGame){
				if (s.r ==null){
					s.r = new Rank(name,"99 : 99 : 99");
				}else{
					s.r.setValue(name,"99 : 99 : 99");
				}
				InGame =false;
				try{
					getResult();
				}
				catch (Exception e){
				}

			}
			s.sendExitMsg(name);
			s.accept();
			receiveThreadShutdown = false;
	}
	void checkMsg(Msg m){

		int type = m.getType();
		Setting.printLog("SR From "+name+" To Server Case "+type);

		switch (type){
		case 0: // 메세지 받음

			m.setName(name);
			s.sendAll(m);
			break;
		case 1:
			//이름체크
			rname = m.getName();
			imageNum = m.getImage();
			name = rname;
			int count =-1;
			boolean br = true;
			for (ServerM sm : s.cArr){
				if (sm.rname.equals(rname)) count++;
				if (count != 0){
					name = rname+"("+count+")";
					br = true;
					for (ServerM km : s.cArr){
						if (km ==this){
							continue;
						}
						if (km.name.equals(name)){
							br = false;
						}
					}
					if (br) break;
				}
			}
			Setting.printLog(rname +">>"+ name +"이름변경");

			Msg m1 = new Msg(7);
			m1.setName(name);
			send(m1);

			s.sendState();
			s.sendEnterMsg(name);
			break;
		case 2: //레디상태 받음
			setReady(m.getReady());
			chkReady();
			s.sendState();
			break;
		case 3: //게임시작 요청받음
			//모두에게 3번 보냄 게임시작
			if (!s.chkInGame()){

				Msg m3 = new Msg(3);
				m3.setUserGame(s.getGameArr());
				s.sendAll(m3);

				for (ServerM sm : s.cArr){
					sm.InGame = true;
					sm.ready =false;
				}

			}
			break;
		case 4: //게임 기록 받음
			if (InGame){
				if (s.r ==null){
					s.r = new Rank(name,m.getContent());
				}else{
					s.r.setValue(name,m.getContent());
				}

				InGame =false;

				try{
					getResult();
				}
				catch (Exception e){
				}
			}
			break;

		case 5: // 게임 실시간
			Msg m5 = new Msg(6);
			m5.setIdx(idx);
			for (ServerM sm : s.cArr){
				if (sm.InGame){
					sm.send(m5);
				}
			}

			break;

		case 6:
			//전체에 게임 뿌리기
			//gA = m.getUserGame();
		/*
			Msg m6 = new Msg();
			m6.setType(8);
			m6.setUserGame(gA);
			m6.setIdx(idx);
			s.sendAll(m6);
			*/
			break;
		}
	}
	////////////////////////////////////////////////
	/*
	2번보냄
	 Allready 상태 => 방장 시작창 활성화
	*/
	void chkReady(){
		if (!s.chkInGame()){
			boolean allready = true;
			if (s.cArr.size()>1){
				for (int i = 1;i<s.cArr.size() ;i++ ){
					ServerM sm = s.cArr.get(i);
					if (!sm.ready){
						allready =false;
						break;
					}
				}
				Setting.printLog("전체 레디 : " + allready);

				ServerM sm = s.cArr.get(0);
				Msg mm = new Msg(2);
				mm.setAllReady(allready);
				sm.send(mm);

			}
		}
	}
	void getResult() throws Exception{

		if (!s.chkInGame()){
			Setting.printLog("결과보냄");
			Msg mm = new Msg(4);
			mm.setResult(s.r.getResult());
			s.r=null;
			//모두에게 4번알림 게임 초기화
			s.sendAll(mm);
		}
	}
	void setReady(boolean ready){
		this.ready = ready;
	}
	synchronized void send(Msg m){
		Setting.printLog("SS From Server To "+name+" Case "+m.getType());
		try{
			oos.writeObject(m);
			oos.flush();
		}
		catch (IOException ie){
			Setting.printLog("보내기 오류");
		}
	}
	void close(){
		try{
			oos.close();
			ois.close();
			soc.close();
			receiveThreadShutdown = false;
		}
		catch (IOException ie){
			Setting.printLog("close");
		}
	}
}
class Rank{

	ArrayList<String[]> rankArr;

	Rank(String name,String time){
		rankArr = new ArrayList<String[]>();
		setValue(name,time);
	}
	void init(String name,String time){
		rankArr = new ArrayList<String[]>();
		setValue(name,time);
	}
	void setValue(String name,String time){
		String v[]= new String[2];
		v[0] = name;
		v[1] = time;
		if (rankArr.size()==0) rankArr.add(v);
		else{
			boolean kk = true;

			for (int i = 0;i<rankArr.size() ;i++ ){
				if (changeTime(rankArr.get(i)[1])>changeTime(time)){
					rankArr.add(i,v);
					kk = false;
					break;
				}
			}
			if (kk) rankArr.add(v);
		}


	}
	int changeTime(String time){

		int min = Integer.parseInt(time.substring(0,2).trim());
		int sec = Integer.parseInt(time.substring(5,7).trim());
		int mils = Integer.parseInt(time.substring(10,12).trim());
		return min*60*1000+sec*1000+mils*10;
	}
	int size(){
		return rankArr.size();
	}

	ArrayList<String> getResult(){
		ArrayList<String> rA = new ArrayList<String>();
		for (String v[] :rankArr){
			if (v[1].equals("99 : 99 : 99")){
				rA.add(v[0]+" >>   GAME OUT ");
			}else{
				rA.add(v[0]+" >> "+v[1]);
			}

		}
		return rA;
	}
}