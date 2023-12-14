/*
 * Christian Gil Ledesma
 * https://www.youtube.com/watch?v=0sqlNnbweK0&ab_channel=MacximiliamKND
 */

package laberinto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class Casilla
{
	// VARIABLES
	private Rectangle2D.Float rectangulo;
	private Color color;					// Color de fondo
	private int bordes[];					// Cadena de 1s en el orden: Norte, Este, Sur, Oeste
	private boolean ocupada;				// Indicativo de si la casilla contiene la ficha


	// CONSTRUCTOR
	public Casilla(Rectangle2D.Float rectangulo, Color color, int[] bordes) {
		this.rectangulo = rectangulo;
		this.color = color;
		this.bordes = bordes;
	}


	// METODOS
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.color);
		g2d.fill(this.rectangulo);

		// Bordes
		g2d.setColor(Color.BLACK);
		paintBorders(g2d, 4);

		if (this.ocupada) {					// Si esta casilla esta ocupada, pinta la ficha
			new Ficha().paintComponent(g2d, (int)this.rectangulo.getX(), (int)this.rectangulo.getY());
		}
	}

	private void paintBorders(Graphics2D g2d, int grosor) {
		int posX = (int)this.rectangulo.getX();
		int posY = (int)this.rectangulo.getY();
		int dimX = (int)this.rectangulo.getWidth();
		int dimY = (int)this.rectangulo.getHeight();

		if (this.bordes[0] == 1) {
			g2d.fillRect(posX, posY, dimX, dimY-(dimY-grosor));		// Borde Norte
		}
		if (this.bordes[1] == 1) {
			g2d.fillRect(posX+dimX-grosor, posY, grosor, dimY);		// Borde Este
		}
		if (this.bordes[2] == 1) {
			g2d.fillRect(posX, posY+(dimY-grosor), dimX, grosor);	// Borde Sur
		}
		if (this.bordes[3] == 1) {
			g2d.fillRect(posX, posY, grosor, dimY);					// Borde Oeste
		}
	}


	// GETERS Y SETERS
	public Rectangle2D.Float getRectangulo() {
		return rectangulo;
	}
	public void setRectangulo(Rectangle2D.Float rectangulo) {
		this.rectangulo = rectangulo;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public int[] getBordes() {
		return bordes;
	}
	public void setBordes(int[] bordes) {
		this.bordes = bordes;
	}

	public boolean isOcupada() {
		return ocupada;
	}
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

}
