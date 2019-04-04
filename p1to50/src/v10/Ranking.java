package v10;

import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Ranking extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblRanking;

	ArrayList<String> rA;
	ArrayList<JLabel> rL1;
	ArrayList<JLabel> rL;	//기록

	Ranking(ArrayList<String> rA) {
		this.rA = rA;
		rL = new ArrayList<JLabel>();
		rL1 = new ArrayList<JLabel>();
		init();
		setFrame();
		setLabel();
		Setting.Audio("./sound/result.wav");
	}
	Ranking(String time){

		rL = new ArrayList<JLabel>();
		rL1 = new ArrayList<JLabel>();
		init();
		setFrame();

		for (int i = 0; i<rL1.size() ;i++ ){
			rL1.get(i).setVisible(true);
		}
		rL.get(0).setText("Single >> "+time);
		repaint();
		Setting.Audio("./sound/result.wav");

	}
	public static void main(String args[]){
		new Ranking("sdsd");
	}
	void init(){
		Font f1 =Setting.font_default;// 기록폰트
		Font f2 =Setting.font2; ///기록폰트
		JPanel mainp =new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g){

				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				   RenderingHints.VALUE_ANTIALIAS_ON);

				int w = getWidth();
				int h = getHeight();
				Color c1 = new Color (Setting.RED,Setting.GREEN,Setting.BLUE);
				Color c2 = Color.BLACK;
				GradientPaint gp  = new GradientPaint(0,0,c1,w,h,c2,true);
				g2d.setPaint(gp);
				g2d.fillRect(0+10,0+10,w-10,h-10);
				setBackground(Setting.TRANS_COLOR);

				super.paintComponent(g);


			}
		};


		mainp.setBorder(new LineBorder(new Color(255, 255, 255), 10, false));
		mainp.setLayout(null);
		mainp.addMouseListener(new MouseListener(){
			@Override
			public void mouseEntered(MouseEvent e)
			{}

			@Override
			public void mouseExited(MouseEvent e)
			{
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Ranking.this.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e)
			{}

			@Override
			public void mouseReleased(MouseEvent e)
			{}
		});
		setContentPane(mainp);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Setting.TRANS_COLOR);
		panel_1.setBounds(0, 0,Setting.RANK_FRAME_WIDTH, Setting.RANK_FRAME_HEIGHT/5);
		panel_1.setLayout(null);
		mainp.add(panel_1);

		lblRanking = new JLabel("Ranking");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setFont(f1);
		lblRanking.setBounds(0, 0,  2*Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		lblRanking.setForeground(Color.WHITE);
		panel_1.add(lblRanking);

		//////////////////////////////////////////////

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Setting.TRANS_COLOR);
		panel_2.setBounds(0, Setting.RANK_FRAME_HEIGHT/5,Setting.RANK_FRAME_WIDTH, Setting.RANK_FRAME_HEIGHT/5);
		mainp.add(panel_2);

		JLabel label = new JLabel("1\uC704");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(f1);
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		label.setVisible(false);
		panel_2.add(label);
		rL1.add(label);

		JLabel l1 = new JLabel("Guest >> 00 : 00 : 00");
		l1.setBounds( Setting.RANK_FRAME_WIDTH/5, 0, Setting.RANK_FRAME_WIDTH-Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		l1.setForeground(Color.WHITE);
		l1.setFont(f2);
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(l1);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Setting.TRANS_COLOR);
		panel_3.setBounds(0,2* Setting.RANK_FRAME_HEIGHT/5,Setting.RANK_FRAME_WIDTH, Setting.RANK_FRAME_HEIGHT/5);
		mainp.add(panel_3);

		JLabel label_1 = new JLabel("2\uC704");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(f1);
		label_1.setBounds(0, 0, Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		label_1.setForeground(Color.WHITE);
		label_1.setVisible(false);
		panel_3.add(label_1);
		rL1.add(label_1);

		JLabel l2 = new JLabel("");
		l2.setBounds( Setting.RANK_FRAME_WIDTH/5, 0, Setting.RANK_FRAME_WIDTH-Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		l2.setForeground(Color.WHITE);
		l2.setFont(f2);
		l2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(l2);

		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Setting.TRANS_COLOR);
		panel_4.setBounds(0, 3* Setting.RANK_FRAME_HEIGHT/5,Setting.RANK_FRAME_WIDTH, Setting.RANK_FRAME_HEIGHT/5);
		mainp.add(panel_4);

		JLabel label_2 = new JLabel("3\uC704");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(f1);
		label_2.setBounds(0, 0, Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		label_2.setForeground(Color.WHITE);
		label_2.setVisible(false);
		panel_4.add(label_2);
		rL1.add(label_2);

		JLabel l3 = new JLabel("");
		l3.setBounds( Setting.RANK_FRAME_WIDTH/5, 0, Setting.RANK_FRAME_WIDTH-Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		l3.setForeground(Color.WHITE);
		l3.setFont(f2);
		l3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(l3);

		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(Setting.TRANS_COLOR);
		panel_5.setBounds(0,4* Setting.RANK_FRAME_HEIGHT/5,Setting.RANK_FRAME_WIDTH, Setting.RANK_FRAME_HEIGHT/5);
		mainp.add(panel_5);

		JLabel label_3 = new JLabel("4\uC704");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(f1);
		label_3.setBounds(0, 0, Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		label_3.setForeground(Color.WHITE);
		label_3.setVisible(false);
		panel_5.add(label_3);
		rL1.add(label_3);

		JLabel l4 = new JLabel("");
		l4.setBounds( Setting.RANK_FRAME_WIDTH/5, 0, Setting.RANK_FRAME_WIDTH-Setting.RANK_FRAME_WIDTH/5, Setting.RANK_FRAME_HEIGHT/5);
		l4.setForeground(Color.WHITE);
		l4.setFont(f2);
		l4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(l4);


		rL.add(l1);
		rL.add(l2);
		rL.add(l3);
		rL.add(l4);


	}
	void setLabel(){
		for (int i = 0; i<rA.size() ;i++ ){
			rL.get(i).setText(rA.get(i));
			rL1.get(i).setVisible(true);
		}

	}
	void setFrame(){
		setTitle("S1");
		setUndecorated(true);
		setSize(Setting.RANK_FRAME_WIDTH,Setting.RANK_FRAME_HEIGHT);
		setLocation(Setting.WIDTH/2-Setting.RANK_FRAME_WIDTH/2,Setting.HEIGHT/2-Setting.RANK_FRAME_HEIGHT/2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
