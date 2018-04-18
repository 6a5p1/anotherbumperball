
public class BallE extends Entitate{
	public BallE(String sprite, int x, int y){
		super(sprite,x,y);
	}
	public void move(long delta){
			x+=dx*delta/1000;
			y+=dy*delta/1000;
			if(dx>0)
				dx--;
			else if(dx<0)
				dx++;
			if(dy>0)
				dy--;
			else if(dy<0)
				dy++;
			
		if(x<5 || x>Game.REZX-30){
			dx=-dx;
		}
		if(y<5 || y>Game.REZY-30)
			dy=-dy;
	}
	public void addSpeed(int speed){
		dx=500;//+=speed;
		dy=500;//+=speed;
		System.out.print("FF");
	}
	public void collidedWith(Entitate other) {
	}
}
