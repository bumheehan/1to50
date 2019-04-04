package v10;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
class Client extends Thread
{

	SocketAddress socketAddress;
	Socket soc;
	String ip = "127.0.0.1";
	int port =7000;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	MainFrame mf;

	String name ="";
	int imagenum = 0;
	int idx ;
	boolean ready =false;
	boolean InGame = false;
	boolean receiveThreadShutdown = true;


	Client(MainFrame mf){
		this.mf = mf;
	}
	boolean init(){
		try{
			soc = new Socket();
			socketAddress= new InetSocketAddress(ip, port);
			soc.connect(socketAddress, 3000);
			Setting.printLog("ȣ��Ʈ�� ���� : "+soc.isConnected());
			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());
			sendName();
			start();
			return true;
		}
		catch (IOException ie){
			//����ȵ� mf ���� �ڷΰ��� ���ּ�
			Setting.printLog("ȣ��Ʈ�� ����ȵ�");
			try{
				soc.close();
			}
			catch (IOException iee){
				ie.printStackTrace();
			}
			return false;
		}
	}
	public void run(){
		while (receiveThreadShutdown){
			receive();
		}
	}
	void close(){
		receiveThreadShutdown =false;
		mf.g.setMulti(false);
		try{
			oos.close();
			ois.close();
			soc.close();
			receiveThreadShutdown= false;
		}
		catch (IOException ie){
			Setting.printLog("close");
		}
		Setting.Fade(mf.pm2,mf.pm1,mf);
	}
	void receive(){
		try{
				Msg m = (Msg)ois.readObject();  //��
				checkMsg(m);
		}catch (ClassNotFoundException ce){
			Setting.printLog("Msg Ŭ����X");
		}
		catch (IOException ie){
			//���� ����
			Setting.printLog("������ ������ �����߽��ϴ�.");

			close();
		}
	}
	void checkMsg(Msg m) {

		int type = m.getType();
		Setting.printLog("CR From Server To "+name + " Case "+type);
		switch (type){
		case 0:
			mf.pm2.appendChat(m.getName() +">>" +m.getContent()+"\n");
			break;
		case 1:
			mf.pm2.appendChat(m.getContent()+"\n",Color.RED);
			break;
		case 2: //���� Ȱ��ȭ
			if (m.getAllReady()){
				mf.pm2.activateStart();
			}else{
				mf.pm2.inactivateStart();
			}
			break;
		case 3: //����
			//��ư ��Ȱ��ȭ �� ���ӽ���
			if (mf.pm2.isBoss){
				mf.pm2.inactivateStart(); //�����̸� ��ư ��Ȱ��ȭ
			}else{
				mf.pm2.inactivateReady(); //Ŭ���̾�Ʈ�� ��ư ��Ȱ��ȭ
			}
			Setting.startEffect(mf.pm2,mf);
			mf.g.setGame(m.getUserGame());
			mf.u.setGame();
			break;
		case 4: //����
			//Ŭ���̾�Ʈ�� ��ư Ȱ��ȭ
			result(m.getResult());
			mf.pm2.reset(); //����, ���г� �� �����
			mf.pm2.activateReady();
			break;
		case 5://���� ����
			mf.u.setUser(m);
			break;
		case 6://�ǽð�����
			mf.u.updateRealTime(m.getIdx());
			break;
		case 7://�̸�����
			//�߰�
			name = m.getName();
			//���� �ʱ�ȭ
			mf.pm2.setMyName(name);
			break;
		case 8: //�ε��� ����
			idx = m.getIdx();
		}
	}

	void result(ArrayList<String> rA){
		new Ranking(rA);
	}
	void sendGameStart(){
		Msg m = new Msg(3);
		send(m);
	}
	void sendGameStop(String timer){
		Msg m = new Msg(4);
		m.setContent(timer);
		send(m);
	}
	void sendChat(String content){
		Msg m = new Msg(0);
		m.setContent(content);
		send(m);
	}
	void sendReady(boolean ready){
		this.ready = ready;
		Msg m = new Msg(2);
		m.setReady(ready);
		send(m);
	}
	void sendName(){
		Msg m = new Msg(1);
		m.setName(name);
		m.setImage(imagenum);
		send(m);
	}
	void sendRealTime(){
		Msg m = new Msg(5);
		m.setName(name);
		send(m);
	}
	synchronized void send(Msg m){
		Setting.printLog("CS From "+name + " To Server Case "+m.getType());

		try{
			oos.writeObject(m);
			oos.flush();
		}
		catch (IOException ie){
			//
		}
	}
	void setHost(String ip,String name,int imagenum){
		this.imagenum =imagenum;
		this.name =name;
		this.ip = ip;
	}
}
