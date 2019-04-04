package v10;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.regex.Pattern;
class Panel_Multi_1 extends BackPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyButton b1,b2,b3;// b1= 생성, b2 = 참가 , b3 =뒤로가기
	JLabel l1 ,l2; // ip ,name
	JTextField tf, tf1; // ip text필드 name
	MainFrame mf ;

	MyButton jb1;
	MyButton jb2;
	MyPanel jl;
	int imageNum ;


	Panel_Multi_1(MainFrame mf) {

		this.mf = mf;

		setBounds(0, 0, 1000, 700);
		setBorder(null);
		setLayout(null);

		b1 = new MyButton("Create");
		b1.setBorder(null);
		b1.setFocusPainted(false);
		b1.setBackground(new Color(200,200,200,0));
		b1.setForeground(new Color(255,255,255));
		b1.setHoverBackgroundColor(new Color(255,255,255,100));
		b1.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2, Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		add(b1);

		b2 = new MyButton("Join");
		b2.setBorder(null);
		b2.setFocusPainted(false);
		b2.setBackground(new Color(200,200,200,0));
		b2.setForeground(new Color(255,255,255));
		b2.setHoverBackgroundColor(new Color(255,255,255,100));
		b2.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2+(Setting.BUTTON_HEIGHT+20),  Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		add(b2);

		b3 = new MyButton("Back");
		b3.setBorder(null);
		b3.setFocusPainted(false);
		b3.setBackground(new Color(200,200,200,0));
		b3.setForeground(new Color(255,255,255));
		b3.setHoverBackgroundColor(new Color(255,255,255,100));
		b3.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2+2*(Setting.BUTTON_HEIGHT+20), Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		add(b3);
//////////////////////////////////////////////////아이피 창
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(2,2,0,0));
		jp2.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH, Setting.FRAME_HEIGHT/2-250, Setting.LABEL_WIDTH*2, Setting.LABEL_HEIGHT);
		//jp2.setOpaque(true);
		jp2.setBackground(Setting.TRANS_COLOR);
		JLabel l1 = new JLabel("IP address");
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(Setting.font2);
		l1.setForeground(Color.WHITE);
		//l1.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2-300, Setting.LABEL_WIDTH, Setting.LABEL_HEIGHT);
		jp2.add(l1);


		tf = new JTextField();
		tf.setText("203.236.209.195");
		//tf.setText("127.0.0.1");
		tf.setBackground(Setting.TRANS_COLOR);
		tf.setFont(Setting.font2);
		tf.setForeground(Color.WHITE);
		tf.setBorder(null);


		//tf.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2-250, Setting.LABEL_WIDTH, Setting.LABEL_HEIGHT);
		jp2.add(tf);

		JLabel l2 = new JLabel("Nick Name");
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		l2.setFont(Setting.font2);
		l2.setForeground(Color.WHITE);
		//l2.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2-200, Setting.LABEL_WIDTH, Setting.LABEL_HEIGHT);
		jp2.add(l2);


		tf1 = new JTextField();
		tf1.setText("Guest");
		tf1.setBackground(Setting.TRANS_COLOR);
		tf1.setFont(Setting.font2);
		tf1.setForeground(Color.WHITE);
		tf1.setBorder(null);

		//tf1.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2-150, Setting.LABEL_WIDTH, Setting.LABEL_HEIGHT);

		tf.setCaretColor(Setting.TRANS_COLOR);
		tf1.setCaretColor(Setting.TRANS_COLOR);
		jp2.add(tf1);
		add(jp2);
///////////////////////////////////////////케릭터창
		JPanel jp = new JPanel();
		jp.setLayout(null);
		jp.setBackground(Setting.TRANS_COLOR);

		jb1 = new MyButton("▶");
		jb2 = new MyButton("◀");
		jb1.setBorder(null);
		jb2.setBorder(null);
		//jb1.setBorderchk(false);
		jb1.setBackground(Setting.TRANS_COLOR);
		jb1.setForeground(new Color(255,255,255));
		jb1.setHoverBackgroundColor(new Color(255,255,255,100));
		//jb2.setBorderchk(false);
		jb2.setBackground(Setting.TRANS_COLOR);
		jb2.setForeground(new Color(255,255,255));
		jb2.setHoverBackgroundColor(new Color(255,255,255,100));

		jl = new MyPanel();
		jl.setBackground(Setting.TRANS_COLOR);
		Setting.iconSelect(jl,imageNum);


		jb1.setBounds(Setting.LABEL_WIDTH*2/3, 0, Setting.LABEL_WIDTH/3, Setting.LABEL_HEIGHT);
		jb2.setBounds(0, 0, Setting.LABEL_WIDTH/3, Setting.LABEL_HEIGHT);
		jl.setBounds(Setting.LABEL_WIDTH*1/3, 0, Setting.LABEL_WIDTH/3, Setting.LABEL_HEIGHT);

		jp.add(jb1,BorderLayout.EAST);
		jp.add(jb2,BorderLayout.WEST);
		jp.add(jl,BorderLayout.CENTER);



		jp.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2-150, Setting.LABEL_WIDTH, Setting.LABEL_HEIGHT);
		add(jp);
////////////////////////////////////////////////////////////////////// repaint 쓰레드

		MouseListener ml =new MouseListener(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				Panel_Multi_1.this.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				Panel_Multi_1.this.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{

			}

			@Override
			public void mousePressed(MouseEvent e)
			{
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				Panel_Multi_1.this.repaint();
			}
		};
		///////////////////////////////////////////////////////////////////
		KeyListener kl = new KeyListener(){
			public void keyPressed(KeyEvent ke)
			{
				  if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
					{
						//code to execute if backspace is pressed
					}

					if(ke.getKeyCode() == KeyEvent.VK_ESCAPE)
					{
						//code to execute if escape is pressed
					}
			}

			public void	keyReleased(KeyEvent e)
			{
			}

			public void	keyTyped(KeyEvent e)
			{
				Panel_Multi_1.this.repaint();
			}
		};
		tf.addMouseListener(ml);
		tf1.addMouseListener(ml);
		tf.addKeyListener(kl);
		tf1.addKeyListener(kl);
		jb1.addMouseListener(ml);
		jb2.addMouseListener(ml);

		ActionListener listener = new Panel_Multi_1_Handler(this);
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		jb1.addActionListener(listener);
		jb2.addActionListener(listener);

	}
	void changePanel(){
	}
}
class Panel_Multi_1_Handler implements ActionListener
{
	Panel_Multi_1 pm1;
	Panel_Multi_1_Handler(Panel_Multi_1 pm1){
		this.pm1 = pm1;
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == pm1.b1){
			//  방장
			pm1.mf.s = new Server();
			if (pm1.mf.s.init()){	
				pm1.mf.c = new Client(pm1.mf);
				pm1.mf.c.setHost("127.0.0.1",pm1.tf1.getText(),pm1.imageNum); // 방장 클라이언트
				if (pm1.mf.c.init()){ //연결되면 들어감
					pm1.mf.pm2.init(true); //방장 설정
					pm1.mf.g.setMulti(true); //멀티게임
					Setting.iconSelect(pm1.mf.pm2.L_My_Icon,pm1.imageNum);
					Setting.Fade(pm1,pm1.mf.pm2,pm1.mf);
					pm1.mf.re =true;
				}
			}else{
				//이미 서버가 열려있어요 창띄우셈
			}


		}else if(obj ==pm1.b2){

			String ipAddr = pm1.tf.getText();
			String validIp = "^([1-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}$";

			if (!Pattern.matches(validIp, ipAddr )) {

			//return "IP주소 " + ipAddr + " 가 올바르지 않습니다.";
			}else{
			  //참가

				pm1.mf.c = new Client(pm1.mf);
				pm1.mf.c.setHost(pm1.tf.getText(),pm1.tf1.getText(),pm1.imageNum); // 참가주소
				if (pm1.mf.c.init()){
					pm1.mf.pm2.init(false);
					pm1.mf.g.setMulti(true); //멀티게임
					Setting.iconSelect(pm1.mf.pm2.L_My_Icon,pm1.imageNum);
					Setting.Fade(pm1,pm1.mf.pm2,pm1.mf);
				}else{
					//서버에 접속할수 없어요 창
				}


			}
		}else if (obj ==pm1.b3){
			Setting.Fade(pm1,pm1.mf.pmain,pm1.mf);
		}else if (obj ==pm1.jb1){
			if (pm1.imageNum==5){
				pm1.imageNum =0;
			}else{
				pm1.imageNum++;
			}
			Setting.iconSelect(pm1.jl,pm1.imageNum);
			//pm1.repaint();

		}else if (obj ==pm1.jb2){
			if (pm1.imageNum==0){
				pm1.imageNum =5;
			}else{
				pm1.imageNum--;
			}
			Setting.iconSelect(pm1.jl,pm1.imageNum);
			//pm1.repaint();
		}

	}
}
