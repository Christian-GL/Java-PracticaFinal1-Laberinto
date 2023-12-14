/*
 * Christian Gil Ledesma
 * https://www.youtube.com/watch?v=0sqlNnbweK0&ab_channel=MacximiliamKND
 */

package laberinto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Laberinto extends JPanel
{
	// VARIABLES
	private final int DIMX = 600;
	private final int DIMY = 600;
	private int filas;
	private int columnas;
	private JOptionPane ventanaGanador;

	private Casilla[][] casillas;
	private int posSalidaX;
	private int posSalidaY;
	private int posFichaInicialX;
	private int posFichaInicialY;
	private int posFichaX;
	private int posFichaY;


	// CONSTRUCTOR
	public Laberinto(String rutaFichero) {
		try {
			// Asumimos que los ficheros NO vienen vacios y tienen bien estructurados los datos
			BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
			String linea;

			filas = Integer.parseInt(br.readLine());
			columnas = Integer.parseInt(br.readLine());
			int dimX = DIMX / columnas;
			int dimY = DIMY / filas;

			casillas = new Casilla[filas][columnas];
			int[] bordes;
			int posX = 0;
			int posY = 0;

			linea = br.readLine();
			for (int f=0; f<this.filas ; f++) {					// Bucle de filas
				posX = 0;										// Reseteamos a 0 la posX cada nueva fila
				for (int c=0 ; c<this.columnas ; c++) {			// Bucle de columnas (grupos de 4 caracteres)
					bordes = new int[4];						// Una posicion por cada pared de la casilla (en orden --> Norte, Este, Sur, Oeste)
					for (int n=0 ; n<4 ; n++) {					// Bucle que guarda los 4 caracteres de cada columna
						bordes[n] = Character.getNumericValue(linea.charAt((c*4)+n));		// c*4 para saber de que columna son las paredes
					}																		// +n para seleccionar la pared en cuestion entre esas 4
					casillas[f][c] = new Casilla(
							new Rectangle2D.Float(posX, posY, dimX, dimY),
							Color.LIGHT_GRAY,
							bordes);

					posX += dimX;								// Le sumamos a la posX una columna de distancia (en px)
				}
				posY += dimY;									// Al acabar una fila le sumamos a la posY una fila de distancia (en pc)
				linea = br.readLine();
			}
			this.posSalidaX = Integer.parseInt(linea);
			this.posSalidaY = Integer.parseInt(br.readLine())-1;			// -1 porque se indica la posicion ya fuera del laberinto
			casillas[posSalidaX][posSalidaY].setColor(Color.GREEN);

			br.close();

			posFichaInicialX = (int)(Math.random() * filas);				// La posicion inicial de la ficha es aleatoria
			posFichaInicialY = (int)(Math.random() * columnas);
			posFichaX = posFichaInicialX;
			posFichaY = posFichaInicialY;
			setFicha(posFichaX, posFichaY);
		}
		catch (FileNotFoundException e) {
			e.getMessage();
		}
		catch (IOException e) {
			e.getMessage();
		}
	}


	// METODOS
	public void moverFicha(String letra) {
		switch (letra) {
		case "W":
			if (casillas[posFichaX][posFichaY].getBordes()[0] == 0) {			// Borde Norte
				casillas[posFichaX][posFichaY].setOcupada(false);				// Marcamos la casilla actual como desocupada en cuando a la ficha
				casillas[posFichaX][posFichaY].paintComponent(getGraphics());	// La pintamos (sin ficha)
				casillas[posFichaX-1][posFichaY].setOcupada(true);				// Marcamos la casilla donde se ha movido la ficha como ocupada
				casillas[posFichaX-1][posFichaY].paintComponent(getGraphics());	// Pintamos la casilla (con ficha)
				posFichaX -= 1;
			}
			break;

		case "S":
			if (casillas[posFichaX][posFichaY].getBordes()[2] == 0) {			// Borde Sur
				casillas[posFichaX][posFichaY].setOcupada(false);
				casillas[posFichaX][posFichaY].paintComponent(getGraphics());
				casillas[posFichaX+1][posFichaY].setOcupada(true);
				casillas[posFichaX+1][posFichaY].paintComponent(getGraphics());
				posFichaX += 1;
			}
			break;

		case "A":
			if (casillas[posFichaX][posFichaY].getBordes()[3] == 0) {			// Borde Oeste
				casillas[posFichaX][posFichaY].setOcupada(false);
				casillas[posFichaX][posFichaY].paintComponent(getGraphics());
				casillas[posFichaX][posFichaY-1].setOcupada(true);
				casillas[posFichaX][posFichaY-1].paintComponent(getGraphics());
				posFichaY -= 1;
			}
			break;

		case "D":
			if (casillas[posFichaX][posFichaY].getBordes()[1] == 0) {			// Borde Este
				casillas[posFichaX][posFichaY].setOcupada(false);
				casillas[posFichaX][posFichaY].paintComponent(getGraphics());
				casillas[posFichaX][posFichaY+1].setOcupada(true);
				casillas[posFichaX][posFichaY+1].paintComponent(getGraphics());
				posFichaY += 1;
			}
			break;
		}
	}

	public void reiniciarFicha() {
		casillas[posFichaX][posFichaY].setOcupada(false);								// Marcamos la casilla actual como desocupada en cuando a la ficha
		casillas[posFichaX][posFichaY].paintComponent(getGraphics());					// La pintamos (sin ficha)
		casillas[posFichaInicialX][posFichaInicialY].setOcupada(true);					// Marcamos la posicion inicial de la ficha como 
		casillas[posFichaInicialX][posFichaInicialY].paintComponent(getGraphics());		// Pintamos la casilla inicial (con ficha)
		posFichaX = posFichaInicialX;													// Movemos la posicion X de la ficha
		posFichaY = posFichaInicialY;													// Movemos la posicion Y de la ficha
	}

	public void comprobarVictoria() {
		if (posFichaX == posSalidaX & posFichaY == posSalidaY) {
			System.out.println("VENATANA GANADOR");
			ventanaGanador = new JOptionPane();
			JOptionPane.showMessageDialog(null, "¡Has Ganado!");
			ventanaGanador.setSize(300, 200);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int f=0 ; f<this.filas ; f++) {			// Recorremos las filas y columnas del array de casillas y las pintamos
			for (int c=0; c<this.columnas; c++) {                
				casillas[f][c].paintComponent(g);
			}
		}
	}

	public void setFicha(int f, int c) {
		casillas[f][c].setOcupada(true);;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.DIMX, this.DIMY);
	}


	// GETERS Y SETERS
	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}
	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public int getPosSalidaX() {
		return posSalidaX;
	}
	public void setPosSalidaX(int posSalidaX) {
		this.posSalidaX = posSalidaX;
	}

	public int getPosSalidaY() {
		return posSalidaY;
	}
	public void setPosSalidaY(int posSalidaY) {
		this.posSalidaY = posSalidaY;
	}

	public int getPosFichaInicialX() {
		return posFichaInicialX;
	}
	public void setPosFichaInicialX(int posInicialFichaX) {
		this.posFichaInicialX = posInicialFichaX;
	}

	public int getPosFichaInicialY() {
		return posFichaInicialY;
	}
	public void setPosFichaInicialY(int posInicialFichaY) {
		this.posFichaInicialY = posInicialFichaY;
	}

	public int getPosFichaX() {
		return posFichaX;
	}
	public void setPosFichaX(int posFichaX) {
		this.posFichaX = posFichaX;
	}

	public int getPosFichaY() {
		return posFichaY;
	}
	public void setPosFichaY(int posFichaY) {
		this.posFichaY = posFichaY;
	}

	public int getDIMX() {
		return DIMX;
	}
	public int getDIMY() {
		return DIMY;
	}



	public JOptionPane getVentanaGanador() {
		return ventanaGanador;
	}


	public void setVentanaGanador(JOptionPane ventanaGanador) {
		this.ventanaGanador = ventanaGanador;
	}

}
