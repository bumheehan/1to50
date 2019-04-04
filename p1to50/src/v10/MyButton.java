package v10;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class MyButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color hoverBackgroundColor;
	private Color pressedBackgroundColor;
	private boolean borderchk = true;

	MyButton(String text) {
		super(text);
		super.setContentAreaFilled(false);
		super.setFocusable(false);
		setFont(Setting.font_default);
		setBackground(Setting.TRANS_COLOR);
		addmouse();
	}
	/* 누르면 색바뀌는거
	@Override
	protected void paintComponent(Graphics g) {

		if (getModel().isPressed()) {
			g.setColor(pressedBackgroundColor);
		} else if (getModel().isRollover()) {
			g.setColor(hoverBackgroundColor);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}*/
	/* 라운드
	 @Override
     protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(15,15);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        //Draws the rounded opaque panel with borders.
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
     }*/
	void setBorderchk(boolean borderchk){
		this.borderchk = borderchk;

	}
	void addmouse(){
		addMouseListener(new MouseListener(){
			@Override
			public void mouseEntered(MouseEvent e)
			{
				Setting.Audio("./sound/On.wav");
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{}

			@Override
			public void mousePressed(MouseEvent e)
			{}

			@Override
			public void mouseReleased(MouseEvent e)
			{}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {

		if (getModel().isPressed()) {
			g.setColor(pressedBackgroundColor);
			roundRec(g,pressedBackgroundColor);

		} else if (getModel().isRollover()) {
			g.setColor(hoverBackgroundColor);
			roundRec(g,hoverBackgroundColor);

		} else {
			g.setColor(getBackground());
			roundRec(g,getBackground());
		}
		super.paintComponent(g);

	}
	void roundRec(Graphics g,Color color){
		if (borderchk){
			Dimension arcs = new Dimension(15,15); //라운드 크기같음
			int width = getWidth();
			int height = getHeight();
			float thickness = 5f; // 선 두께
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			graphics.setColor(color);
			graphics.fillRoundRect((int)(thickness/2), (int)(thickness/2), (int)(width-thickness), (int)(height-thickness), arcs.width, arcs.height);//paint background

			Stroke oldStroke = graphics.getStroke();
			graphics.setStroke(new BasicStroke(thickness));

			graphics.setColor(getForeground());
			graphics.drawRoundRect((int)(thickness/2), (int)(thickness/2), (int)(width-thickness), (int)(height-thickness), arcs.width, arcs.height);//paint border

			graphics.setStroke(oldStroke);

		}

	}



	@Override
	public void setContentAreaFilled(boolean b) {
	}

	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}

	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}

	public Color getPressedBackgroundColor() {
		return pressedBackgroundColor;
	}

	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}
}
