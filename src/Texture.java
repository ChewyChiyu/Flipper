import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public final class Texture {
	public static BufferedImage[] flipper = new BufferedImage[4];
	public static BufferedImage city;
	public Texture(){
		load();
	}
	void load(){
		try{
			BufferedImage spriteSheetFlipper = ImageIO.read(getClass().getResource("/imgs/Little.png"));
			city = ImageIO.read(getClass().getResource("/imgs/cityBackDrop.jpg"));
			int xBuffer = 0;
			int yBuffer = 0;
			for(int index = 0; index < flipper.length; index++){
				int width;
				if(index<2)
					width = 256;
				else{
					xBuffer+=15;
					width = 230;
				}
				flipper[index] = spriteSheetFlipper.getSubimage(xBuffer, yBuffer, width, 287);
				xBuffer+=width;
			}
		}catch(Exception e) { System.err.println("out of bounds");}
	}
}
