package itq.gato.clases;

import java.awt.Color;

import javax.swing.JButton;
import itq.gato.algoritmos.*;

public class Maquina extends Jugador {

	private JButton[][] tablero = new JButton[3][3];
	private int dificultad;
	
	public Maquina(String caracter, int dificultad) {
		super("Maquina", caracter);
		this.dificultad = dificultad;
	}

	public JButton[][] realizarJugada(JButton[][] tablero) {
		if(dificultad == 1)
		{
			int[] movimiento = Facil.analizarTablero(tablero);
			System.out.println("Maquina tira en ["+movimiento[0]+"]["+movimiento[1]+"]");
			tablero[movimiento[0]][movimiento[1]].setText(this.getCaracter());
			tablero[movimiento[0]][movimiento[1]].setForeground(new Color(102, 102, 102));
		} 
		else if(dificultad == 2)
		{
			int[] movimiento = Dificil.analizarTablero(tablero);
			System.out.println("Maquina tira en ["+movimiento[0]+"]["+movimiento[1]+"]");
			tablero[movimiento[0]][movimiento[1]].setText(this.getCaracter());
			tablero[movimiento[0]][movimiento[1]].setForeground(new Color(102, 102, 102));

		}
		
		return tablero;
	}

	public JButton[][] getTablero() {
		return tablero;
	}

	public void setTablero(JButton[][] tablero) {
		this.tablero = tablero;
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
}
