import java.awt.Graphics;

public abstract class Character {
	int x;
	int y;
	int xVelo;
	int yVelo;
	int speed;
	int maxHealth;
	int health;
	int maxEnergy;
	int energy;
	int width;
	int height;
	Type t;
	Direction d;
	boolean inAir;
	public Character(int x, int y, int health, int energy, Type t){
		this.x = x;
		this.y = y;
		this.maxHealth = health;
		this.maxEnergy = energy;
		this.t = t;
		
		switch(t){
		case FLIPPER:
			width = 400;
			height = 400;
			break;
		}
		speed = 5;
		this.health = this.maxHealth;
		this.energy = this.maxEnergy;
		
		
	}
	 int getX() {
		return x;
	}
	 void setX(int x) {
		this.x += x;
	}
	 int getY() {
		return y;
	}
	 void setY(int y) {
		this.y += y;
	}
	 int getxVelo() {
		return xVelo;
	}
	 void setxVelo(int xVelo) {
		this.xVelo = xVelo;
		if(xVelo>0){
			d = Direction.RIGHT;
		}else if(xVelo<0){
			d = Direction.LEFT;
		}
	}
	 int getyVelo() {
		return yVelo;
	}
	 void setyVelo(int yVelo) {
		this.yVelo = yVelo;
	}
	 int getSpeed() {
		return speed;
	}
	 void setSpeed(int speed) {
		this.speed = speed;
	}
	 int getMaxHealth() {
		return maxHealth;
	}
	 void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	 int getHealth() {
		return health;
	}
	 void setHealth(int health) {
		this.health = health;
	}
	 int getMaxEnergy() {
		return maxEnergy;
	}
	 void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	 int getEnergy() {
		return energy;
	}
	 void setEnergy(int energy) {
		this.energy = energy;
	}
	 Type getT() {
		return t;
	}
	 void setT(Type t) {
		this.t = t;
	}
	abstract void draw(Graphics g);
	abstract void jump();
}
