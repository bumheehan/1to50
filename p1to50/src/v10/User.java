package v10;
import java.awt.*;
import javax.swing.*;
import java.util.*;

class User
{
	ArrayList<JLabel> User_State;
	ArrayList<MyPanel> User_Icon;
	ArrayList<JPanel> User_Panel;
	ArrayList<JPanel> User_Game_Panel;
	ArrayList<ArrayList<JLabel>> User_Game_Label;
	ArrayList<Iterator<Integer>> User_Iterator;

	int userCount = 0;

	MainFrame mf;

	User(MainFrame mf) {
		this.mf = mf;
		init();
	}

	void init(){
		User_State=new ArrayList<JLabel>();
		User_Icon = new ArrayList<MyPanel> ();
		User_Game_Panel= new ArrayList<JPanel>();
		User_Panel= new ArrayList<JPanel>();
		User_Game_Label = new ArrayList<ArrayList<JLabel>>();

		for (int i = 0 ; i < Setting.MAX_PEOPLE-1 ;i++ ){ // 유저는 max에서 -1 값
			RoundedPanel panel = new RoundedPanel();
			panel.setLayout(null);
			//panel.setBounds(x, y, 200, 300);
			panel.setBackground(Setting.TRANS_COLOR);
			panel.setForeground(Color.WHITE);

			RoundedPanelNotB panel_game = new RoundedPanelNotB();
			panel_game.setBounds(0, 0, Setting.USER_GAME_WIDTH, Setting.USER_GAME_HEIGHT);
			panel_game.setLayout(new GridLayout(5,5,0,0));
			panel_game.setBorder(null);
			panel.add(panel_game);

			//기본값
			MyPanel l_Icon = new MyPanel();
			l_Icon.setBounds(15, 215, Setting.ICON_WIDTH, Setting.ICON_HEIGHT);
			l_Icon.setBackground(Setting.TRANS_COLOR);
			panel.add(l_Icon);

			//기본값
			JLabel l_state = new JLabel();
			l_state.setBounds(100, 215, Setting.ICON_WIDTH+15, Setting.ICON_HEIGHT);
			l_state.setForeground(Color.WHITE);
			l_state.setFont(Setting.font2);

			panel.add(l_state);

			User_Panel.add(panel);
			User_State.add(l_state);
			User_Icon.add(l_Icon);
			User_Game_Panel.add(panel_game);
			User_Game_Label.add(createUserGamePanel(panel_game));
		}

	}
	void addUserPanel(int x0, int y0,int x1, int y1, int x2, int y2,JPanel jp){
		JPanel p0 = User_Panel.get(0);
		JPanel p1 = User_Panel.get(1);
		JPanel p2 = User_Panel.get(2);
		p0.setBounds(x0, y0, 200, 300);
		p1.setBounds(x1, y1, 200, 300);
		p2.setBounds(x2, y2, 200, 300);
		jp.add(p0);
		jp.add(p1);
		jp.add(p2);



	}
	/// 유저 25개 게임패널 만들기
	ArrayList<JLabel> createUserGamePanel(JPanel jp){

		ArrayList<JLabel> JA = new ArrayList<JLabel>();
		for (int i = 0 ;i<25 ;i++ ){
			JLabel lab = new JLabel();
			lab.setSize(new Dimension(30, 30));
			lab.setBorder(null);
			lab.setHorizontalAlignment(SwingConstants.CENTER);
			lab.setForeground(Color.WHITE);
			lab.setFont(Setting.font2);
			jp.add(lab);
			JA.add(lab);
		}
		return JA;
	}

	//전체 초기화 -> 기본레이블 입력 -> 게임중이면 텍스트입력
	void setUser(Msg m){
		Object[][] obj = m.getObj();
		userCount=0;
		int j =0;
		for (int i =0; i<obj.length ;i++){
			if (i == mf.c.idx){
				continue;
			}else{
				Object[] oA = obj[i];
				MyPanel l_Icon= User_Icon.get(j);
				JPanel panel_game =User_Game_Panel.get(j);
				JLabel l_state = User_State.get(j);
				JPanel userp = User_Panel.get(j);
				ArrayList<JLabel> ug=User_Game_Label.get(j);
				if (oA[0]== null){
					//초기화
					l_state.setText("");
					userp.setForeground(Color.WHITE);
					l_Icon.setImage(Setting.ICON_NULL);
					// 수정
					for (JLabel jl : ug){
						jl.setText("");
					}
					panel_game.setVisible(false);
				}else{
					userCount++;
					int imageNum =(Integer)oA[0];
					String name = (String)oA[1];
					boolean isReady = (boolean)oA[2];
					int idx =  (Integer)oA[3];
					//유저이름
					l_state.setText(name);
					if (idx == 0){//방장이면 파랑색
							userp.setForeground(new Color(80,100,200));
					}else{
						if (isReady){
							userp.setForeground(new Color(227,255,185));
						}else{
							userp.setForeground(new Color(255,111,132));
						}
					}
					Setting.iconSelect(l_Icon,imageNum);
				}
				j++;
			}
		}
	}

	// gA 받으면 유저패널에 넣고 gI 만들기
	void setGame(){
		User_Iterator = new ArrayList<Iterator<Integer>>();
		for (int i =0 ;i<userCount ; i++){
			User_Iterator.add(mf.g.gA.iterator());
			ArrayList<JLabel> ug = User_Game_Label.get(i);
			Iterator<Integer> ir = User_Iterator.get(i);
			for(JLabel jb : ug){
				if (ir.hasNext()){
					jb.setText(ir.next()+"");
				}else{
					jb.setText("");
				}
			}
		}
	}

	//겜끝나면 유저 패널 숨기기
	void hideUserPanel(){
		for (int i =0 ;i<Setting.MAX_PEOPLE-1 ; i++){
			JPanel jp = User_Game_Panel.get(i);
			jp.setVisible(false);
		}
	}
	void showUserPanel(){
		for (int i =0 ;i<userCount ; i++){
			JPanel jp = User_Game_Panel.get(i);
			jp.setVisible(true);
		}
	}

	/// 유저 실시간 업데이트
	void updateRealTime(int idx){

		if (idx != mf.c.idx){
			if (idx>mf.c.idx){
				idx--;
			}
			Iterator<Integer> Ir= User_Iterator.get(idx);
			ArrayList<JLabel> ug=User_Game_Label.get(idx);
			int jbidx =0;
			int count = 0;

			for (int i = 0 ;i<ug.size() ;i++ ){
				JLabel jb = ug.get(i);
				int k = -1;
				try{
					k = Integer.parseInt(jb.getText());
				}
				catch (Exception e){
				}
				if (k!=-1){
					if (count ==0 ){
						count = k;
						jbidx = i;
					}else if (count>k){
						count = k;
						jbidx = i;
					}
				}

			}

			Setting.printLog("count : "+count+" idx :"+jbidx);
			if (Ir.hasNext()){
				ug.get(jbidx).setText(Ir.next()+"");
			}else{
				ug.get(jbidx).setText("");
			}
		}

		///?
		/*
		for (int i = 0; i <text.size() ; i++ ){
			String line = text.get(i);
			User_Game_Label.get(idx).get(i).setText(line);
		}
		*/
	}
}
