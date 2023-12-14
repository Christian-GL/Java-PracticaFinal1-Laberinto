package laberinto;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class FileChooser extends JFileChooser
{
	// VARIABLES
	private int seleccion;
	private File file;
	private String nameFile;


	// CONSTRUCTOR
	public FileChooser() {
		seleccion = showOpenDialog(null);

		// ACEPTAR
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			file = getSelectedFile();
			nameFile = file.getName();
		}
	}


	// GETERS Y SETERS
	public int getSeleccion() {
		return seleccion;
	}
	public void setSeleccion(int seleccion) {
		this.seleccion = seleccion;
	}

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public String getNameFile() {
		return nameFile;
	}
	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

}
