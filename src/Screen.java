import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class Screen extends JPanel implements Runnable {
	Thread gameLoop;
	boolean isRunning;
	final static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	final static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	final static int floor = (int)(screenHeight*.9);
	final static int gravity = 10;
	ArrayList<Character> sprites = new ArrayList<Character>();
	Flipper player = new Flipper(screenWidth/2, 0, 100, 100);
	public static void main(String[] args){
		new Texture();
		new Screen();
	}
	public Screen(){
		sprites.add(player);
		panel();
		keys();
		start();
	}
	void keys(){
		getInputMap().put(KeyStroke.getKeyStroke("A"), "A");
		getInputMap().put(KeyStroke.getKeyStroke("D"), "D");
		getInputMap().put(KeyStroke.getKeyStroke("W"), "W");
		
		getInputMap().put(KeyStroke.getKeyStroke("released A"), "rA");
		getInputMap().put(KeyStroke.getKeyStroke("released D"), "rD");

		getActionMap().put("A", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelo(-player.getSpeed());
			}
			
		});
		getActionMap().put("rA", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelo(0);
			}
			
		});
		getActionMap().put("D", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelo(player.getSpeed());
			}
			
		});
		getActionMap().put("rD", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setxVelo(0);
			}
			
		});
		getActionMap().put("W", new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				player.jump();
			}
			
		});
	}
	void panel(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	 synchronized void start(){
		gameLoop = new Thread(this);
		isRunning = true;
		gameLoop.start();
	}
	 synchronized void stop(){
		try{
			isRunning = false;
			gameLoop.join();
		}catch(Exception e) { } 
	}
	public void run(){
		while(isRunning){
			update();
			try{
				Thread.sleep(10);
			}catch(Exception e) { }
		}
	}
	void update(){
		moveSprites();
		gravity();
		repaint();
	}
	void gravity(){
		for(int index = 0; index < sprites.size(); index++){
			Character c = sprites.get(index);
			if(c.getY()+c.height<=floor){
				c.setyVelo(gravity);
				c.inAir = true;
			}else{
				c.setyVelo(0);
				c.inAir = false;
			}
		}
	}
	void moveSprites(){
		for(int index = 0; index < sprites.size(); index++){
			Character c = sprites.get(index);
			c.setX(c.getxVelo());
			c.setY(c.getyVelo());
		}
	}
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackDrop(g);
		drawCharacters(g);
	}
	void drawBackDrop(Graphics g){
		g.drawImage(Texture.city,0,0,screenWidth,screenHeight,null);
	}
	void drawCharacters(Graphics g){
		for(int index = 0; index < sprites.size(); index++){
			Character c = sprites.get(index);
			c.draw(g);
		}
	}
}
