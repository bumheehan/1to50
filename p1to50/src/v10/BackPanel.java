package v10;
import javax.swing.*;
import java.awt.*;

class BackPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BackPanel(){
		setOpaque(false);
	}
	
	public void paintComponent(Graphics g){
			
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		   RenderingHints.VALUE_ANTIALIAS_ON);

		int w = getWidth();
		int h = getHeight();
		Color c1 = new Color (50,50,50);
		Color c2 = new Color(Setting.RED,Setting.GREEN,Setting.BLUE);
		GradientPaint gp  = new GradientPaint(0,0,c1,0,h,c2);
		g2d.setPaint(gp);
		g2d.fillRect(0,0,w,h);
		setBackground(Setting.TRANS_COLOR);
		
		super.paintComponent(g);
		
	}
}
