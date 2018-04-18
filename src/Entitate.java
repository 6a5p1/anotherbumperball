
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entitate {
	protected double x;
	protected double y;
	protected double dx=0;
	protected double dy=0;
	protected int moveSpeed = 50;
	protected Sprite textura;
	private Rectangle me = new Rectangle();
	private Rectangle him = new Rectangle();
	
	public Entitate(String ref,int x,int y) {
		this.textura = SpritesCollection.get().getTextura(ref);
		this.x = x;
		this.y = y;
	}
	
	public Entitate(int x,int y) {
		this.x = x;
		this.y = y;
	}
		
	public void schimbaTextura(String ref)
	{
		this.textura=SpritesCollection.get().getTextura(ref);
	}
		
	public void move(long delta) {
		x += (delta * dx) / 1000;
		y += (delta * dy) / 1000;
	}
	
	public void setHorizontalMovement(double dx) {
		this.dx = dx;
	}

	public void setVerticalMovement(double dy) {
		this.dy = dy;
	}
	
	public double getHorizontalMovement() {
		return dx;
	}

	public double getVerticalMovement() {
		return dy;
	}

	public void draw(Graphics g) {
		textura.draw(g,(int) x,(int) y);
	}

	public void doLogic() {
	}
	
	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}
	
	public boolean collidesWith(Entitate other) {
		me.setBounds((int) x,(int) y,textura.getWidth(),textura.getHeight());
		him.setBounds((int) other.x,(int) other.y,other.textura.getWidth(),other.textura.getHeight());
		return me.intersects(him);
	}
	
	public abstract void collidedWith(Entitate other);
}