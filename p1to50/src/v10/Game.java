package v10;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class  Game
{

	Iterator<Integer> gI;
	ArrayList<Integer> gA;
	MainFrame mf ;
	boolean shutdown = false; //타이머 멈추기
	Random r = new Random();
	boolean multi = false;
	JPanel gp ;
	JButton jbArr[][] = new JButton[5][5];// 1to50 버튼
	String timer ;
	int count = 1;
	boolean InGame = false;

	Game(MainFrame mf){
		this.mf = mf;
		gp = new JPanel();
		gp.setLayout(new GridLayout(5, 5, 0, 0));
		gp.setBackground(new Color(255,0,0,0));
		for (int i = 0; i<5 ; i++ ){
			for (int j = 0; j<5 ;j++ ){
				MyButton b1 = new MyButton("");
				b1.setSize(new Dimension(30, 30));
				b1.setFocusable(false);
				b1.setBorderPainted(false);
				b1.setBorder(null);
				b1.setForeground(Color.BLACK);
				b1.setBackground(Color.WHITE);
				b1.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						Object obj = e.getSource();
						JButton b = (JButton)obj;
						if (b.getText().equals(""+count)){
							//맞을 때
							Setting.Audio("./sound/correct.wav");
							b.setText("");
							if (gI.hasNext()){
								b.setText(gI.next()+"");
							}
							//보냄
							if (multi){
								mf.c.sendRealTime();
							}
							//끝냄
							if (count == Setting.MAX_COUNT){
								gameStop();
							}else{
								count++;
							}

						}else{
							Setting.Audio("./sound/wrong.wav");
						}
					}
				});
				b1.setFont(Setting.font3);
				b1.setPressedBackgroundColor(new Color(255,0,0));
				b1.setHoverBackgroundColor(new Color(0,255,0));
				gp.add(b1);
				jbArr[i][j] = b1;
			}
		}
		Setting.printLog("gp 완료");
	}
	void setGame(ArrayList<Integer> gA){ //멀티
		this.gA = gA;
		this.gI = gA.iterator();
		Setting.printLog("멀티 게임 세팅 완료");
		count =1;
		shutdown =true;
		setArr();

		//타이머실행
	}
	void setGame(){
		count =1;
		shutdown =true;

		ArrayList<Integer> gA = new ArrayList<Integer>();
		while (true){
			int rand = r.nextInt(25)+1;
			boolean chk = true;
			for (int i :gA){
				if (i==rand){
					chk =false;
					break;
				}
			}
			if (chk) gA.add(rand);
			if (gA.size()==25) break;
		}
		while (true){
			int rand = r.nextInt(25)+26;
			boolean chk = true;
			for (int i :gA){
				if (i==rand){
					chk =false;
					break;
				}
			}
			if (chk) gA.add(rand);
			if (gA.size()==50) break;
		}
		this.gI = gA.iterator();
		Setting.printLog("싱글 게임 세팅 완료");

		setArr();

		//타이머실행
	}
	void gameStart(){
		//타이머
		if (multi){
			mf.pm2.start();
			//타이머 시작
			timer(mf.pm2.Panel_Timer);
		}else{
			setGame();
			mf.psp.start(); //패널 보이기
			timer(mf.psp.timeLable);
		}
		InGame = true;
	}
	void gameStop(){

		if (InGame){
			if (multi) {
				if (count==Setting.MAX_COUNT){
					mf.c.sendGameStop(timer);
				}
			}else{
				mf.psp.reset();// 싱글 패널 리셋
				if (count == Setting.MAX_COUNT){ //게임끝나면 랭킹보여줌
					new Ranking(timer);
				}
				Setting.Fade(mf.psp,mf.pmain,mf);
			}
			while (gI.hasNext()){//gI 비우기
				gI.next();
			}
			setArr();//게임패널 초기화
			shutdown = false;// 타이머 종료
		}
		InGame = false;
	}
	void setArr(){
		for (int i = 0; i<5 ; i++ ){
			for (int j = 0; j<5 ;j++ ){
				if (gI.hasNext()){
					jbArr[i][j].setText(gI.next()+"");
				}else{
					jbArr[i][j].setText("");
				}
			}
		}
	}
	void setMulti(boolean multi){
		this.multi = multi;
	}
	void timer(JLabel lt){
		long ctime =System.currentTimeMillis();

		new Thread(){
			public void run(){
				while (shutdown){
					try{
						Thread.sleep(5);
					}
					catch (InterruptedException ie){
						ie.printStackTrace();
					}
					long ltime = (System.currentTimeMillis()-ctime); //1000 1초
					int min = (int)(ltime/60000);
					ltime -= min*60000;
					int sec = (int)(ltime/1000) ;
					ltime -= sec*1000;
					timer= String.format("%02d : %02d : %02d",min,sec,ltime/10);
					//timer = timer.replace("1"," 1");
					lt.setText(timer);
					if (min==2){
						gameStop();
					}
				}
			}
		}.start();
	}
}
