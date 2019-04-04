package v10;
import javax.swing.*;
import java.awt.*;
import  javax.sound.sampled.*;    //import the sun.audio package
import  java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class  Setting
{

	static final int MAX_PEOPLE = 4;
	static final int MAX_COUNT = 50;

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static final int WIDTH = (int)screenSize.getWidth();
	static final int HEIGHT = (int)screenSize.getHeight();

	static final int USERP1_X = 20;
	static final int USERP1_Y = 30;

	static final int USERP2_X = 240;
	static final int USERP2_Y = 30;

	static final int USERP3_X = 20;
	static final int USERP3_Y = 370;

	static final int BUTTON_WIDTH = 150;
	static final int BUTTON_HEIGHT = 70;

	static final int LOGO_WIDTH = 700;
	static final int LOGO_HEIGHT = 161;

	static final int LABEL_WIDTH = 150;
	static final int LABEL_HEIGHT = 50;

	static final int FRAME_WIDTH = 1000;
	static final int FRAME_HEIGHT = 700;

	static final int RANK_FRAME_WIDTH = 500;
	static final int RANK_FRAME_HEIGHT = 300;

	static final int GAME_WIDTH = 500;
	static final int GAME_HEIGHT = 500;

	static final int USER_GAME_WIDTH = 200;
	static final int USER_GAME_HEIGHT = 200;

	static final int ICON_WIDTH = 70;
	static final int ICON_HEIGHT = 70;

	static final Color TRANS_COLOR = new Color(0,0,0,0);

	static Random rand = new Random();
	static final int RED = rand.nextInt(155)+51;
	static final int GREEN = rand.nextInt(155)+51;
	static final int BLUE = rand.nextInt(155)+51;

	static final Font font_default = new Font("a전자시계", Font.PLAIN, 20);
	static final Font font1 = new Font("a전자시계", Font.PLAIN, 200);
	static final Font font2 = new Font("a전자시계", Font.PLAIN, 15);
	static final Font font3 = new Font("a전자시계", Font.PLAIN, 50);
	static final Font font4 = new Font("a전자시계", Font.PLAIN, 30);
	static final Font font_timer_1 = new Font("Digital Dismay", Font.PLAIN, 60);
	static final Font font_timer_2 = new Font("Digital Dismay", Font.PLAIN, 40);

	static final ImageIcon ICON_0 = new ImageIcon("./image/c0.png");
	static final ImageIcon ICON_1 = new ImageIcon("./image/c1.png");
	static final ImageIcon ICON_2 = new ImageIcon("./image/c2.png");
	static final ImageIcon ICON_3 = new ImageIcon("./image/c3.png");
	static final ImageIcon ICON_4 = new ImageIcon("./image/c4.png");
	static final ImageIcon ICON_5 = new ImageIcon("./image/c5.png");
	static final ImageIcon ICON_NULL = new ImageIcon("./image/cnull.png");
	static final ImageIcon LOGO = new ImageIcon("./image/logo.png");

	static void iconSelect(MyPanel jl,int IconN){
		switch (IconN){
		case 1:
			jl.setImage(Setting.ICON_0);
			break;
		case 2:
			jl.setImage(Setting.ICON_1);
			break;
		case 3:
			jl.setImage(Setting.ICON_2);
			break;
		case 4:
			jl.setImage(Setting.ICON_3);
			break;
		case 5:
			jl.setImage(Setting.ICON_4);
			break;
		default:
			jl.setImage(Setting.ICON_5);

		}

	}

	static void Fade(JPanel jp1,JPanel jp2,MainFrame mf){
		Setting.Audio("./sound/back.wav");
		FadeOutPanel fop = new FadeOutPanel();
		fop.setBounds(0, 0, Setting.FRAME_WIDTH, Setting.FRAME_HEIGHT);
		jp1.add(fop,0);

		FadeInPanel fip = new FadeInPanel();
		fip.setBounds(0, 0, Setting.FRAME_WIDTH, Setting.FRAME_HEIGHT);
		jp2.add(fip,0);

		jp1.revalidate();
		jp2.revalidate();



		ButtonAllEnabled(jp1,false);
		ButtonAllEnabled(jp2,false);
		new Thread(){
			public void run(){
				while (true){
					try{
						Thread.sleep(1);
					}catch(InterruptedException ie2){
					}
					fop.getParent().repaint();
					if (fop.getAlpha()==254) break;
				}
				String chName = "";
				if (jp2==mf.psp){
					chName = "psp";
				}else if (jp2==mf.pmain){
					chName = "pmain";
				}else if (jp2==mf.pm1){
					chName = "pm1";
				}else{
					chName = "pm2";
				}
				mf.changePanel(chName);

				while (true){
					try{
						Thread.sleep(1);
					}catch(InterruptedException ie2){
					}
					fip.getParent().repaint();
					if (fip.getAlpha()==1) break;
				}

				jp1.remove(fop);
				jp2.remove(fip);
				ButtonAllEnabled(jp1,true);
				ButtonAllEnabled(jp2,true);
			}
		}.start();
	}

	static void Audio(String fName){

		new Thread() {
			public void run() {
				Clip clip=null;
				try {
					clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fName));
					clip.open(inputStream);
					clip.start();


				} catch (Exception e) {
					System.err.println(e.getMessage());
				}

				while (true){
					try{
						Thread.sleep(10);
					}catch(InterruptedException ie2){
					}
					if (clip!=null && !clip.isRunning()){
						clip.close();
						break;
					}


				}

			}
		}.start();



	}
	static void ButtonAllEnabled(JPanel jp,boolean chk){
		 Component[] components = jp.getComponents();

		for (Component component : components) {
			if (component instanceof JButton) {
				JButton jb = (JButton)component;
				jb.setEnabled(chk);
			}
		}
	}


	static void startEffect(JPanel jp,MainFrame mf){

		new Thread (){
			public void run(){
				mf.re= true;// 리페인트시작
				try{
					Thread.sleep(2000);
				}
				catch (InterruptedException ie){
					ie.printStackTrace();
				}
				BackPanel jjp = new BackPanel();
				jjp.setLayout(new BorderLayout());
				jjp.setBounds(0,0,Setting.FRAME_WIDTH,Setting.FRAME_HEIGHT);
				jjp.setBackground(new Color(255,255,255,100));
				jjp.setOpaque(true);

				JLabel jb = new JLabel();
				jb.setFont(font1);
				jb.setForeground(Color.WHITE);
				jb.setHorizontalAlignment(SwingConstants.CENTER);
				jjp.add(jb,BorderLayout.CENTER);
				jp.add(jjp,0);
				jb.setText("3");
				Setting.Audio("./sound/Three.wav");
				try{
					Thread.sleep(1200);
				}catch(InterruptedException ie2){
				}
				jb.setText("2");
				Setting.Audio("./sound/Two.wav");
				try{
					Thread.sleep(1200);
				}catch(InterruptedException ie2){
				}
				jb.setText("1");
				Setting.Audio("./sound/One.wav");
				try{
					Thread.sleep(1200);
				}catch(InterruptedException ie2){
				}
				jb.setText("START");
				Setting.Audio("./sound/Start.wav");
				try{
					Thread.sleep(500);
				}catch(InterruptedException ie2){
				}

				jp.remove(jjp);

				mf.re= false;// 리페인트시작
				mf.g.gameStart();
			}
		}.start();

	}

	static void printLog(String line){
		try{
			File f1 = new File("./Log");
			File f2 = new File("./Log/Log.txt");
			if (!f1.isDirectory()){
				f1.mkdir();
			}else{
				if (f2.isFile()){
					f2.delete();
				}
			}
			long time = System.currentTimeMillis();
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str = dayTime.format(new Date(time));

			@SuppressWarnings("resource")
			PrintWriter pw = new PrintWriter(new FileWriter("./Log/Log.txt", true));
			pw.println(str+" >> "+line);
			pw.flush();

		}
		catch (IOException ie){

		}

	}

}
