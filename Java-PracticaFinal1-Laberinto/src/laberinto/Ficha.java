/*
 * Christian Gil Ledesma
 * https://www.youtube.com/watch?v=0sqlNnbweK0&ab_channel=MacximiliamKND
 */

package laberinto;

import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;


public class Ficha
{
	// VARIABLES
	public static final String FICHA = "ficha.png";
	private BufferedImage img;


	// CONSTRUCTOR
	public Ficha() {
		try {
			img = ImageIO.read(new File(FICHA));		// Cargamos la imagen del archivo png
		}
		catch (IOException e) {
			e.getMessage();
		}
	}


	// METODOS
	public void paintComponent(Graphics g, int x, int y) {
		g.drawImage(img, x + 18, y + 8, null);
	}

}
