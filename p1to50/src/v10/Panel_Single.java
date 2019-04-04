package v10;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Panel_Single extends BackPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel timeLable;
	MyButton pauseB ; // 퍼즈 버튼
	MainFrame mf;
	JPanel playPanel;
	JPanel effectPanel;
	JLabel timeL;

	public Panel_Single(MainFrame mf) {
		this.mf = mf;
		//Panel_SinglePlay_Handler gh = new Panel_SinglePlay_Handler(this);


		setSize(new Dimension(1000, 700));
		setBorder(null);
		setLayout(null);

		playPanel = new JPanel();
		playPanel.setBounds(Setting.FRAME_WIDTH/2-Setting.GAME_WIDTH/2,Setting.FRAME_HEIGHT/2-Setting.GAME_HEIGHT/2, Setting.GAME_WIDTH, Setting.GAME_HEIGHT);
		add(playPanel);
		playPanel.setBackground(Setting.TRANS_COLOR);
		playPanel.setLayout(new BorderLayout());

		pauseB = new MyButton("◀");
		pauseB.setFocusable(false);
		pauseB.setFont(Setting.font3);

		pauseB.setBorder(null);
		pauseB.setForeground(Color.WHITE);
		pauseB.setBounds(916, 10, 74, 74);
		pauseB.setPressedBackgroundColor(Color.WHITE);
		pauseB.setHoverBackgroundColor(new Color(255,255,255,100));
		pauseB.addActionListener(new Panel_Single_Handler(this));
		add(pauseB);

		timeLable = new JLabel("00 : 00 : 00");
		timeLable.setHorizontalAlignment(SwingConstants.CENTER);
		timeLable.setFont(Setting.font_timer_1);
		timeLable.setBackground(Setting.TRANS_COLOR);
		timeLable.setForeground(Color.WHITE);
		timeLable.setBounds(250, 10, 500, 74);
		add(timeLable);

		reset();
	}
	void setTime(String time){
		timeLable.setText(time);
	}
	void reset(){
		setTime("00 : 00 : 00");
		timeLable.setVisible(false);
		pauseB.setVisible(false);
		playPanel.setVisible(false);
	}
	void start(){
		playPanel.add(mf.g.gp,BorderLayout.CENTER);// 같은거 여러번 추가해도 한개컴포넌트만들어감
		timeLable.setVisible(true);
		pauseB.setVisible(true);
		playPanel.setVisible(true);

	}
}

class Panel_Single_Handler implements ActionListener{
	Panel_Single psp;

	Panel_Single_Handler(Panel_Single psp){
		this.psp = psp;

	}
	public void actionPerformed(ActionEvent e){
		psp.mf.g.gameStop();
		Setting.Fade(psp,psp.mf.pmain,psp.mf);
	}
}