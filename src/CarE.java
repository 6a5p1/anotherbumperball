
public class CarE extends Entitate{
	int playerA;
	public CarE(String ref, int x, int y, int playerA){
		super(ref,x,y);
		this.playerA=playerA;
		this.moveSpeed=150;
	}

	public void move(long delta) {
		if ((dx < 0) && (x < 10)) {
			return;
		}
		if ((dx > 0) && (x > Game.REZX-80)) {
			return;
		}
		if ((dy > 0) && (y > Game.REZY-120))
			return;
		if ((dy < 0) && (y < 10))
			return;
		super.move(delta);
	}
	public int getMoveSpeed(){
		return moveSpeed;
	}

	public void collidedWith(Entitate other) {	
		if(other instanceof BallE){
			((BallE) other).addSpeed(getMoveSpeed());
		}
	}
}
