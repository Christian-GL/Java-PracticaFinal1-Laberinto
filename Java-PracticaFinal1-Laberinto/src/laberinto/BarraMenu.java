/*
 * Christian Gil Ledesma
 * https://www.youtube.com/watch?v=0sqlNnbweK0&ab_channel=MacximiliamKND
 */

package laberinto;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class BarraMenu extends JMenuBar
{
	// VARIABLES
	private JMenu generalMenu;
	private JMenuItem abrirLaberinto, reiniciarFicha, salir;


	// CONSTRUCTOR
	public BarraMenu() {
		generalMenu = new JMenu("OPCIONES");

		abrirLaberinto = new JMenuItem();
		reiniciarFicha = new JMenuItem();
		salir = new JMenuItem();

		abrirLaberinto.setText("Abrir Laberinto");
		reiniciarFicha.setText("Reiniciar Ficha");
		salir.setText("Salir");

		generalMenu.add(abrirLaberinto);
		generalMenu.add(reiniciarFicha);
		generalMenu.add(salir);
		add(generalMenu);
	}


	// GETERS Y SETERS
	public JMenu getGeneralMenu() {
		return generalMenu;
	}
	public void setGeneralMenu(JMenu generalMenu) {
		this.generalMenu = generalMenu;
	}

	public JMenuItem getAbrirLaberinto() {
		return abrirLaberinto;
	}
	public void setAbrirLaberinto(JMenuItem abrirLaberinto) {
		this.abrirLaberinto = abrirLaberinto;
	}

	public JMenuItem getReiniciarFicha() {
		return reiniciarFicha;
	}
	public void setReiniciarFicha(JMenuItem reiniciarFicha) {
		this.reiniciarFicha = reiniciarFicha;
	}

	public JMenuItem getSalir() {
		return salir;
	}
	public void setSalir(JMenuItem salir) {
		this.salir = salir;
	}

}
