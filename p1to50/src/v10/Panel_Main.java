package v10;
import java.awt.*;
import java.awt.event.*;

class Panel_Main extends BackPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MyButton b1,b2,b3;
	MainFrame mf;

	Panel_Main(MainFrame mf){
		this.mf= mf;
		setLayout(null);
		setBackground(new Color(45,45,45));

		MyPanel logo = new MyPanel();
		logo.setBounds(Setting.FRAME_WIDTH/2-Setting.LOGO_WIDTH/2, Setting.FRAME_HEIGHT/2-250,Setting.LOGO_WIDTH, Setting.LOGO_HEIGHT);
		logo.setImage(Setting.LOGO);
		logo.setBackground(Setting.TRANS_COLOR);
		add(logo);

		b1 = new MyButton("SINGLE");
		b1.setBorder(null);
		b1.setBackground(new Color(200,200,200,0));
		b1.setForeground(new Color(255,255,255));
		b1.setHoverBackgroundColor(new Color(255,255,255,100));
		b1.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2,Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		b1.setFocusPainted(false);

		b2 = new MyButton("MULTI");
		b2.setBorder(null);
		b2.setBackground(new Color(200,200,200,0));
		b2.setForeground(new Color(255,255,255));
		b2.setHoverBackgroundColor(new Color(255,255,255,100));
		b2.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2,  Setting.FRAME_HEIGHT/2+(Setting.BUTTON_HEIGHT+20),Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		b2.setFocusPainted(false);

		b3 = new MyButton("EXIT");
		b3.setBorder(null);
		b3.setBackground(new Color(200,200,200,0));
		b3.setForeground(new Color(255,255,255));
		b3.setBounds(Setting.FRAME_WIDTH/2-Setting.BUTTON_WIDTH/2, Setting.FRAME_HEIGHT/2+2*(Setting.BUTTON_HEIGHT+20),Setting.BUTTON_WIDTH, Setting.BUTTON_HEIGHT);
		b3.setHoverBackgroundColor(new Color(255,255,255,100));
		b3.setFocusPainted(false);

		add(b1); add(b2); add(b3);
		ActionListener listener = new Panel_Main_Handler(this);
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);

	}
}
class Panel_Main_Handler implements ActionListener
{
	Panel_Main pmain;
	Panel_Main_Handler(Panel_Main pmain){
		this.pmain = pmain;
	}
	public void actionPerformed(ActionEvent e){
		Object obj = e.getSource();
		if(obj == pmain.b1){
			Setting.Fade(pmain,pmain.mf.psp,pmain.mf);
			Setting.startEffect(pmain.mf.psp,pmain.mf);
		}else if(obj ==pmain.b2){
			Setting.Fade(pmain,pmain.mf.pm1,pmain.mf);
		}else{
			System.exit(0);
		}
	}
}
