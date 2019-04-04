package v10;
import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;

	void setImage(ImageIcon imageIcon){
		image = imageIcon.getImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null){
			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}
}
