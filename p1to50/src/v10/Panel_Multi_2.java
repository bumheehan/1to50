package v10;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

class Panel_Multi_2 extends BackPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel Panel_Timer;
	JPanel Panel_My_Game;
	MyButton B_Pause;
	MyButton B_Ready;
	MyButton B_Start;
	JLabel L_My_State;
	MyPanel L_My_Icon;
	JPanel Panel_Text;
	JTextPane ta;
	JScrollPane Jsp;
	JTextField tf;
	JPanel My_Panel;
	Panel_Multi_2_Handler pm2h ;
	MouseListener ml;

	boolean ready = false;
	boolean isBoss = false;

	MainFrame mf;
	Panel_Multi_2(MainFrame mf) {

		this.mf = mf;
		createComponent();
	}
	void setTime(String line){
		Panel_Timer.setText(line);
	}
	void createComponent(){

		setBorder(null);
		setLayout(null);
		setBounds(0,0,Setting.FRAME_WIDTH,Setting.FRAME_HEIGHT);

		pm2h = new Panel_Multi_2_Handler(this);

		My_Panel = new RoundedPanel();
		My_Panel.setBorder(null);
		My_Panel.setBackground(new Color(255,255,255,50));
		My_Panel.setForeground(new Color(255,255,255));
		My_Panel.setBounds(500, 30, 480, 640);
		add(My_Panel);
		My_Panel.setLayout(null);

		Panel_Timer = new JLabel("Timer");
		Panel_Timer.setBounds(10, 10, 370, Setting.ICON_HEIGHT);
		Panel_Timer.setHorizontalAlignment(SwingConstants.CENTER);
		Panel_Timer.setFont(Setting.font_timer_2);
		Panel_Timer.setForeground(Color.WHITE);
		My_Panel.add(Panel_Timer);

		Panel_My_Game = new JPanel();
		Panel_My_Game.setLayout(new BorderLayout());
		Panel_My_Game.setBorder(null);
		Panel_My_Game.setBounds(10, 90, 460, 460);
		Panel_My_Game.setVisible(false);


		My_Panel.add(Panel_My_Game);

		B_Pause = new MyButton("◀");
		B_Pause.setContentAreaFilled(false);
		B_Pause.setBounds(400, 10, Setting.ICON_WIDTH, Setting.ICON_HEIGHT);
		B_Pause.setFont(Setting.font3);
		B_Pause.addActionListener(pm2h);
		B_Pause.setBorder(null);
		B_Pause.setFocusPainted(false);
		B_Pause.setBackground(Setting.TRANS_COLOR);
		B_Pause.setForeground(Color.WHITE);
		B_Pause.setHoverBackgroundColor(new Color(255,255,255,100));
		My_Panel.add(B_Pause);

		L_My_State = new JLabel("New label");
		L_My_State.setForeground(Color.WHITE);
		L_My_State.setFont(Setting.font4);
		L_My_State.setBounds(100, 560, 150, Setting.ICON_HEIGHT);
		My_Panel.add(L_My_State);

		L_My_Icon = new MyPanel();
		L_My_Icon.setBounds(10, 560, Setting.ICON_WIDTH, Setting.ICON_HEIGHT);
		L_My_Icon.setBackground(Setting.TRANS_COLOR);
		My_Panel.add(L_My_Icon);

		Panel_Text = new JPanel();
		Panel_Text.setLayout(null);
		Panel_Text.setBounds(240, 370, 200, 300);
		add(Panel_Text);

		ta =new JTextPane();
		ta.setEditable(false);

		Jsp = new JScrollPane(ta);
		Jsp.setBounds(0, 0, 200, 270);
		//Jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Panel_Text.add(Jsp);

		tf = new JTextField();
		tf.setBounds(0, 270, 200, 30);
		tf.addActionListener(pm2h);
		tf.setForeground(Color.RED);
		Panel_Text.add(tf);

		//유저패널 넣기
		mf.u.addUserPanel(Setting.USERP1_X,Setting.USERP1_Y,Setting.USERP2_X,Setting.USERP2_Y,Setting.USERP3_X,Setting.USERP3_Y,this);

	}
	void reset(){
		Panel_Timer.setVisible(false);
		Panel_My_Game.setVisible(false);
		mf.u.hideUserPanel();
	}
	void start(){
		Panel_My_Game.add(mf.g.gp,BorderLayout.CENTER);
		Panel_Timer.setVisible(true);
		Panel_My_Game.setVisible(true);
		mf.u.showUserPanel();
	}
	void setMyName(String name){
		L_My_State.setText(name);
	}
	void init(boolean isBoss){

		this.isBoss = isBoss;
		ta.setText(""); // 처음 들어올때 텍스트창 초기화
		reset();

		////다시들어올때 이전에 만든ready start 버튼 있으면 지우기
		Component[] components = My_Panel.getComponents();
		for (Component component : components) {
			if (component==B_Ready) {
				My_Panel.remove(B_Ready);
			}else if (component==B_Start){
				My_Panel.remove(B_Start);
			}
		}
		Setting.printLog("Multi Panel Button Reset");

		if (isBoss){
			B_Start = new MyButton("Start");
			B_Start.setForeground(Color.BLUE);
			B_Start.setBackground(new Color(166,222,253));
			B_Start.setForeground(Color.BLACK);
			B_Start.setHoverBackgroundColor(new Color(0,0,0,100));
			B_Start.setContentAreaFilled(false);
			B_Start.addActionListener(pm2h);
			B_Start.setBounds(270, 560, 200, Setting.ICON_HEIGHT);
			B_Start.addMouseListener(ml);
			B_Start.setFocusPainted(false);
			B_Start.setBorder(null);
			My_Panel.add(B_Start);
			inactivateStart();

		}else{
			B_Ready = new MyButton("Ready");
			B_Ready.setBackground(new Color(255,111,132));
			B_Ready.setForeground(Color.BLACK);
			B_Ready.setHoverBackgroundColor(new Color(0,0,0,100));
			B_Ready.setContentAreaFilled(false);
			B_Ready.addActionListener(pm2h);
			B_Ready.addMouseListener(ml);
			B_Ready.setFocusPainted(false);
			B_Ready.setBorder(null);
			B_Ready.setBounds(270, 560, 200, Setting.ICON_HEIGHT);
			My_Panel.add(B_Ready);
		//////////////////////////////////////
		}

	}
	void activateReady(){
		if (!isBoss){
			B_Ready.setEnabled(true);
			ready = false;
			B_Ready.setBackground(new Color(255,111,132));
			mf.c.sendReady(ready);
			Setting.printLog("레디 활성");
		}
		Panel_My_Game.setVisible(false);
	}
	void inactivateReady(){
		B_Ready.setEnabled(false);
		Setting.printLog("레디 비활성");
	}
	void activateStart(){
		B_Start.setEnabled(true);
		Setting.printLog("시작 활성");
	}
	void inactivateStart(){
		B_Start.setEnabled(false);
		Setting.printLog("시작 비활성");
	}

	void appendChat(String text){
		appendChat(text,Color.BLACK);

	}
	void appendChat(String text, Color color) {
        StyledDocument doc = ta.getStyledDocument();

        Style style = ta.addStyle("Color Style", null);
        StyleConstants.setForeground(style, color);
        try {
            doc.insertString(doc.getLength(), text, style);
        }
        catch (BadLocationException e) {
            e.printStackTrace();
        }
		Jsp.getVerticalScrollBar().setValue(Jsp.getVerticalScrollBar().getMaximum());

    }


}

class Panel_Multi_2_Handler implements ActionListener
{
	Panel_Multi_2 pm2 ;
	Panel_Multi_2_Handler(Panel_Multi_2 pm2){
		this.pm2=pm2;
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();

		if(obj == pm2.B_Ready){ //클라이언트
			pm2.ready = !pm2.ready;
			if (pm2.ready){//레디
				pm2.B_Ready.setBackground(new Color(227,255,185));
				pm2.B_Ready.setHoverBackgroundColor(new Color(0,0,0,100));
				pm2.B_Ready.setText("Set");
				pm2.mf.c.sendReady(pm2.ready);


			}else { //레디X
				pm2.B_Ready.setBackground(new Color(255,111,132));
				pm2.B_Ready.setHoverBackgroundColor(new Color(0,0,0,100));
				pm2.B_Ready.setText("Ready");
				pm2.mf.c.sendReady(pm2.ready);
			}
		}else if (obj == pm2.B_Start){ //방장버튼
			pm2.mf.c.sendGameStart();

		}else if (obj == pm2.tf){
			pm2.mf.c.sendChat(pm2.tf.getText());
			pm2.tf.setText("");
		}else if (obj ==pm2.B_Pause){
			pm2.mf.g.gameStop();
			try{
				if (pm2.mf.s!=null){
					pm2.mf.c.close();
					pm2.mf.s.closeAll();
					pm2.mf.s= null;
					Setting.printLog("서버종료");
				}else{
					pm2.mf.c.close();
					Setting.printLog("클라이언트 종료");
				}
			}
			catch (Exception ea){
			}
		}
	}
}