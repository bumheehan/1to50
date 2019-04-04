package v10;
import javax.swing.*;
import java.awt.*;

class  RoundedLabel extends JLabel
{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		/** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = false;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = false;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(20, 20);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;

	RoundedLabel() {
       super();
       setOpaque(false);
   }

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int shadowGap = this.shadowGap;
		Color shadowColorA = new Color(shadowColor.getRed(),
			shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
		Graphics2D graphics = (Graphics2D) g;

       //Sets antialiasing if HQ.
		if (highQuality) {
		   graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		   RenderingHints.VALUE_ANTIALIAS_ON);
		}

       //Draws shadow borders if any.
		if (shady) {
		   graphics.setColor(shadowColorA);
		   graphics.fillRoundRect(
		   shadowOffset,// X position
		   shadowOffset,// Y position
		   width - strokeSize - shadowOffset, // width
		   height - strokeSize - shadowOffset, // height
		   arcs.width, arcs.height);// arc Dimension
		} else {
		   shadowGap = 1;
		}
		//클립먼저
		/*
		int xx[] = {0,40,80,120};
		int yy[] = {0,120,200,120};
		graphics.setClip(new Polygon(xx, yy ,4));
		*/

		//Draws the rounded opaque panel with borders.
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width - shadowGap,height - shadowGap, arcs.width, arcs.height);
		graphics.setColor(getForeground());
		graphics.setStroke(new BasicStroke(strokeSize));
		graphics.drawRoundRect(0, 0, width - shadowGap,height - shadowGap, arcs.width, arcs.height);
		//graphics.draw3DRect(0, 0, width, height, false);
		//graphics.drawString("ddd",10,20);
		//Sets strokes to default, is better.
		
		

		
		
		graphics.setStroke(new BasicStroke());
   }


}


