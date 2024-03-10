package itq.gato.algoritmos;

import javax.swing.JButton;

public class Facil {

	public static int[] analizarTablero(JButton[][] tablero)
	{
		for(int i = 0; i < 3; i++)
		{
			if(tablero[i][0].getText().equals(tablero[i][1].getText()) && !tablero[i][0].getText().equals("") && tablero[i][2].getText().equals(""))
			{
				return new int[] {i, 2};
			}
			else if(tablero[i][1].getText().equals(tablero[i][2].getText()) && !tablero[i][1].getText().equals("") && tablero[i][0].getText().equals(""))
			{
				return new int[] {i, 0};
			}
			else if(tablero[i][0].getText().equals(tablero[i][2].getText()) && !tablero[i][2].getText().equals("") && tablero[i][1].getText().equals(""))
			{
				return new int[] {i, 1};
			}
			else if(tablero[0][i].getText().equals(tablero[1][i].getText()) && !tablero[0][i].getText().equals("") && tablero[2][i].getText().equals(""))
			{
				return new int[] {2, i};
			}
			else if(tablero[1][i].getText().equals(tablero[2][i].getText()) && !tablero[1][i].getText().equals("") && tablero[0][i].getText().equals(""))
			{
				return new int[] {0, i};
			}
			else if(tablero[0][i].getText().equals(tablero[2][i].getText()) && !tablero[2][i].getText().equals("") && tablero[1][i].getText().equals(""))
			{
				return new int[] {1, i};
			}
		}
		
		if(tablero[0][0].getText().equals(tablero[1][1].getText()) && !tablero[0][0].getText().equals("") && tablero[2][2].getText().equals(""))
		{
			return new int[] {2, 2};
		}
		else if(tablero[0][0].getText().equals(tablero[2][2].getText()) && !tablero[2][2].getText().equals("") && tablero[1][1].getText().equals(""))
		{
			return new int[] {1, 1};
		}
		else if(tablero[1][1].getText().equals(tablero[2][2].getText()) && !tablero[1][1].getText().equals("") && tablero[0][0].getText().equals(""))
		{
			return new int[] {0, 0};
		}
		else if(tablero[0][2].getText().equals(tablero[2][0].getText()) && !tablero[2][0].getText().equals("") && tablero[1][1].getText().equals(""))
		{
			return new int[] {1, 1};
		}
		else if(tablero[0][2].getText().equals(tablero[1][1].getText()) && !tablero[0][2].getText().equals("") && tablero[2][0].getText().equals(""))
		{
			return new int[] {2, 0};
		}
		else if(tablero[1][1].getText().equals(tablero[2][0].getText()) && !tablero[1][1].getText().equals("") && tablero[0][2].getText().equals(""))
		{
			return new int[] {0, 2};
		}
		
		int i;
		int j;
		do
		{
			i = (int)(Math.random()*3);
			j = (int)(Math.random()*3);
		} while(!tablero[i][j].getText().equals(""));
		
		return new int[] {i, j};
	}
}
