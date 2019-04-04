package v10;
import javax.swing.*;
import java.awt.*;

class  FadeInPanel extends JPanel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int Alpha = 255;

	FadeInPanel() {
       super();
	   setOpaque(false);
   }
   int getAlpha(){
	   return Alpha;
   }
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Alpha>1){
			Alpha-=2;
			int width = Setting.FRAME_WIDTH;
			int height = Setting.FRAME_HEIGHT;
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
			Color c = new Color(0,0,0,Alpha);
			graphics.setColor(c);
			graphics.fillRect(0,0,width,height);
		}else{
		}	
   }
}


