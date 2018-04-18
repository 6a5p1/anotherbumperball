
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class SpritesCollection {
	private static SpritesCollection single = new SpritesCollection();
	private HashMap<String, Sprite> texturi = new HashMap<String, Sprite>();

	public static SpritesCollection get() {
		return single;
	}
	
	public Sprite getTextura(String ref) {
		if (texturi.get(ref) != null) {
			return (Sprite) texturi.get(ref);
		}
		BufferedImage sursa = null;
		try {
			URL url = this.getClass().getClassLoader().getResource(ref);		
			if (url == null) {
				eroare("Nu s-a gasit locatia: "+ref);
			}
			sursa = ImageIO.read(url);
			
		} catch (Exception e) {
			eroare("Eroare la incarcare: "+ref);
		}
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sursa.getWidth(),sursa.getHeight(),Transparency.BITMASK);
		image.getGraphics().drawImage(sursa,0,0,null);

		Sprite textura = new Sprite(image);
		texturi.put(ref,textura);
		
		return textura;
	}
	
	private void eroare(String msg) {
		System.err.println(msg);
		System.exit(0);
	}
}