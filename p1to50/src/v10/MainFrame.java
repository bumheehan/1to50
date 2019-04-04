package v10;

import java.awt.*;
import javax.swing.*;


class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Game g  = new Game(this);
	User u = new User(this);
	Panel_Multi_1 pm1 =new Panel_Multi_1(this);
	Panel_Multi_2 pm2= new Panel_Multi_2(this);
	Panel_Main pmain = new Panel_Main(this);
	Panel_Single psp = new Panel_Single(this);
	CardLayout card = new CardLayout();
	JPanel CardP;
	boolean re ;
	Server s ;
	Client c ;


	MainFrame(){
		setFrame();
		changePanel("pmain");  ///
		new Thread(){
			public void run(){
				while (true){
					if (re){
						Container jp = MainFrame.this.getContentPane();
						jp.revalidate();
						jp.repaint();
					}else{
						try{
							Thread.sleep(10);
						}catch(InterruptedException ie2){
						}
					}
				}
			}
		}.start();
	}
	void changePanel(String panelName){
		card.show(CardP,panelName);
	}
	void refresh(){
		invalidate();
		validate();
	}
	void setFrame(){
		CardP = new JPanel();
		CardP.setLayout(card);
		CardP.add(pmain,"pmain");
		CardP.add(psp,"psp");
		CardP.add(pm1,"pm1");
		CardP.add(pm2,"pm2");
		setContentPane(CardP);

		setTitle("S1");
		setUndecorated(true);
		setSize(Setting.FRAME_WIDTH,Setting.FRAME_HEIGHT);
		setLocation(Setting.WIDTH/2-Setting.FRAME_WIDTH/2,Setting.HEIGHT/2-Setting.FRAME_HEIGHT/2);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
