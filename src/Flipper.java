import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Timer;

public class Flipper extends Character {
	int motionIndex = 0;
	double angle = 0;
	public Flipper(int x , int y, int h, int e){
		super(x,y,h,e,Type.FLIPPER);
		Timer move = new Timer(64, a-> {
			if(xVelo!=0||yVelo!=0){
				incMotion();
			}
		});
		move.start();
	}
	void incMotion(){
		motionIndex++;
		if(motionIndex>Texture.flipper.length-2){
			motionIndex = 0;
		}
	}
	@Override
	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(inAir){
			g2d.translate(x+width/2, y+height/2); 
			g2d.rotate(angle);
			g2d.drawImage(Texture.flipper[motionIndex], -width/2, -height/2, width, height, null);	
		}else{
			g2d.translate(0, 0);
			g2d.drawImage(Texture.flipper[motionIndex], x, y, width, height, null);	
		}
	}
	@Override
	void jump() {
		if(!inAir){
		Thread jumping = new Thread(new Runnable(){
			public void run(){
				for(int index = 0; index < 40; index++){
					yVelo-=11;
					try{
						Thread.sleep(1);
					}catch(Exception e) {  }
				}
			}
		});
		jumping.start();
		Thread rotate = new Thread(new Runnable(){
			public void run(){
				for(int index = 0; index < 1120; index++){
					angle += (Math.PI/360);
					try{
						Thread.sleep(1);
					}catch(Exception e) { } 
				}
			}
		});
		rotate.start();
		}
	}
}
