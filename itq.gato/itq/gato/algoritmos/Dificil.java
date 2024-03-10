package itq.gato.algoritmos;

import javax.swing.JButton;

public class Dificil {

	public static int[] analizarTablero(JButton[][] tablero)
	{
		
		int[] movimiento = Facil.analizarTablero(tablero);
		return movimiento;
	}
}
