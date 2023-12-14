/*
 * Christian Gil Ledesma
 * https://www.youtube.com/watch?v=0sqlNnbweK0&ab_channel=MacximiliamKND
 */

package laberinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class Ventana extends JFrame
{
	// VARIABLES
	private Laberinto laberinto;
	private BarraMenu barraMenu;


	// CONSTRUCTOR
	public Ventana(String rutaFichero) {
		laberinto = new Laberinto(rutaFichero);			// Creamos un JPanel con todas las caracteristicas del laberinto
		barraMenu = new BarraMenu();					// Creamos una Barra de menu para las opciones del laberinto

		ajustarJFrame(laberinto, barraMenu);			// Aplicamos las propiedades necesarias para configurar el JFrame
		anyadirEventos();								// Anyadimos eventos a teclas, botones y opciones de menu
	}


	// METODOS
	private void ajustarJFrame(Laberinto laberinto, JMenuBar barraMenu) {
		setTitle("Laberinto");
		setJMenuBar(barraMenu);									// Anyadimos la barra de menu al JFrame
		getContentPane().add(laberinto);						// Anyadimos el laberinto al JFrame
		setSize(laberinto.getPreferredSize());
		pack();
		setResizable(false);
		setLocationRelativeTo(null);							// El JFrame sale en el centro de la pantalla
		setDefaultCloseOperation(Ventana.EXIT_ON_CLOSE);		// Cierra la Ventana al salir
		setLayout(null);
	}

	private void anyadirEventos() {

		// OPCIONES MENU --> Abrir Laberinto
		barraMenu.getAbrirLaberinto().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Abrir Laberinto");

				FileChooser fileChooser = new FileChooser();
				Laberinto lab2 = new Laberinto(fileChooser.getNameFile());

				// Actualizamos el laberinto con las opciones de un nuevo laberinto leido a traves del FileChooser
				laberinto.setFilas(lab2.getFilas());
				laberinto.setColumnas(lab2.getColumnas());
				laberinto.setPosSalidaX(lab2.getPosSalidaX());
				laberinto.setPosSalidaY(lab2.getPosSalidaY());
				laberinto.setPosFichaInicialX(lab2.getPosFichaInicialX());
				laberinto.setPosFichaInicialY(lab2.getPosFichaInicialY());
				laberinto.setPosFichaX(lab2.getPosFichaX());
				laberinto.setPosFichaY(lab2.getPosFichaY());
				laberinto.setCasillas(lab2.getCasillas());
				repaint();
			}
		});

		// OPCIONES MENU --> Reiniciar Ficha
		barraMenu.getReiniciarFicha().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				laberinto.reiniciarFicha();
			}
		}); 

		// OPCIONES MENU --> Salir
		barraMenu.getSalir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		// TECLAS --> W, A, S, D
		laberinto.setFocusable(true);
		laberinto.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){

				// Tecla "W"
				if(e.getKeyCode() == KeyEvent.VK_W) {
					System.out.println("W");
					laberinto.moverFicha("W");
					laberinto.comprobarVictoria();
				}

				// Tecla "S"
				if(e.getKeyCode() == KeyEvent.VK_S) {
					System.out.println("S");
					laberinto.moverFicha("S");
					laberinto.comprobarVictoria();
				}

				// Tecla "A"
				if(e.getKeyCode() == KeyEvent.VK_A) {
					System.out.println("A");
					laberinto.moverFicha("A");
					laberinto.comprobarVictoria();
				}

				// Tecla "D"
				if(e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("D");
					laberinto.moverFicha("D");
					laberinto.comprobarVictoria();
				}

				// Tecla "Esc"
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					System.out.println("Esc");
					System.exit(0);
				}
			}
			public void keyTyped(KeyEvent e){
				// No hara nada
			}
			public void keyReleased(KeyEvent e){
				// No hara nada
			}
		});
	}


	// MAIN 
	public static void main(String[] args)
	{
		final String rutaFichero = "maze2.txt";
		Ventana ventana = new Ventana(rutaFichero);
		ventana.setVisible(true);
	}

}
