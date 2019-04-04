package v10;
import javax.swing.*;
import java.awt.*;

class  RoundedPanelNotB extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Dimension arcs = new Dimension(20, 20);

	RoundedPanelNotB() {
       super();
	   setBackground(Setting.TRANS_COLOR);
       setOpaque(false);
   }

	@Override
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		   RenderingHints.VALUE_ANTIALIAS_ON);

       graphics.setColor(getBackground());
       graphics.fillRoundRect(0, 0, width ,
       height, arcs.width, arcs.height);

	   setBackground(Setting.TRANS_COLOR);
	   super.paintComponent(g);
   }


}


