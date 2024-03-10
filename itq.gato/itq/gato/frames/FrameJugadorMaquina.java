package itq.gato.frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

import itq.gato.clases.*;
import itq.gato.main.*;

@SuppressWarnings("serial")
public class FrameJugadorMaquina extends FrameGato {

	private int dificultad;
	
	public FrameJugadorMaquina()
	{
		super();
		this.setTitle("Partida multijugador");
		
		// Creamos el objeto jugador
		Jugador jugador = new Jugador(JOptionPane.showInputDialog(getParent(), "Ingresa el nombre del jugador:", 
				"Datos del jugador", JOptionPane.INFORMATION_MESSAGE), "O");
		
		Object resp = JOptionPane.showInputDialog(null, "Selecciona la dificultad de la máquina",
                "Selección de dificultad", JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(getToolkit().getImage("res/gato_main.png").getScaledInstance(30, 30, Image.SCALE_SMOOTH)), 
                new Object[] {"Fácil", "Difícil"},
                "Fácil");

		if(resp.equals("Fácil"))
		{
			this.setDificultad(1);
		}
		else 
		{
			this.setDificultad(2);
		}
		
		// Creamos el objeto de la máquina con la dificultad seleccionada
		Maquina maquina = new Maquina("X", this.getDificultad());
		
		// Primero establecemos los valores de los nombres de los jugadores
		this.nombreJugador1.setText(jugador.getNombre());
		this.nombreJugador2.setText(maquina.getNombre());
		
		// Inicializamos el label con información del turno
		this.turnoJugador.setText("");
					
		this.add(panelJuego);
		this.add(panelInformacion);
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				// Empezamos añadiendo a cada casilla del frame un listener para saber cuando se pulsa
				this.casillas[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						JButton fuente = (JButton) e.getSource();
						
						// Si el botón que se presionó NO ha sido presionado antes, entonces
						// empezamos con la ejecución de la lógica del juego
						if(fuente.getText().equals(""))
						{
							
							// Como solo el jugador puede desencadenar el objeto de actionPerformed, 
							// no es necesario revisar quien está ejecutando la acción
							// Cambiamos el texto al del caracter del jugador,
							fuente.setText(jugador.getCaracter());
							fuente.setForeground(Color.WHITE);
							
							// la función regresará "1" si alguien ha ganado y "-1" es un empate
							// aunque no se evalua, se regresa un "0" si todavia sigue el juego
							int estado = verificarCasillas(jugador.getCaracter());
							if(estado == -1) 
							{
								// Mostramos mensaje de empate, el siguiente juego
								// lo iniciará la misma persona
								JOptionPane.showMessageDialog(getParent(), "Es un empate!");
								// Al final de cada partida se vacía el tablero
								reiniciarCasillas();
							} 
							else if(estado == 1) 
							{
								// Se le añade el punto al jugador si ha logrado ganar
								// y se muestra en el Label de puntuación.
								JOptionPane.showMessageDialog(getParent(), "Ha ganado el jugador: "+jugador.getNombre()+"!");
								jugador.setPuntuacion(jugador.getPuntuacion()+1);
								puntuacionJugador1.setText(jugador.getPuntuacion()+" puntos");
								
								JOptionPane.showMessageDialog(getParent(), "Cambio de turno! Empezará jugando la máquina!");
								
								// Al final de cada partida se vacía el tablero
								reiniciarCasillas();
								
								casillas = maquina.realizarJugada(casillas);
							}
							
							// Empezamos con la lógica de la máquina, que siempré tirará después del jugador
							// Verificamos que la partida todavía siga
							if(estado == 0) 
							{
								// Indicamos al programa que deberá realizar su movimiento
								casillas = maquina.realizarJugada(casillas);
								
								estado = verificarCasillas(maquina.getCaracter());
								if(estado == -1) 
								{
									// Mostramos mensaje de empate, el siguiente juego
									// lo iniciará la misma persona (o la máquina)
									JOptionPane.showMessageDialog(getParent(), "Es un empate!");
									// Al final de cada partida se vacía el tablero
									reiniciarCasillas();
								} 
								else if(estado == 1) 
								{
									// Se le añade el punto a la máquina
									// y se muestra en el Label de puntuación.
									JOptionPane.showMessageDialog(getParent(), "Ha ganado la máquina!");
									maquina.setPuntuacion(maquina.getPuntuacion()+1);
									puntuacionJugador2.setText(maquina.getPuntuacion()+" puntos");
									
									JOptionPane.showMessageDialog(getParent(), "Cambio de turno! Empezará jugando el jugador: "+jugador.getNombre()+"!");
									
									// Al final de cada partida se vacía el tablero
									reiniciarCasillas();
								}
							}
						}
					}
				});
			}
		}
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				if(jugador.getPuntuacion() > maquina.getPuntuacion())
				{
					if(getDificultad() == 1)
					{
						JOptionPane.showMessageDialog(getParent(), "Felicidades "+jugador.getNombre()+"! Le has ganado a la máquina en dificultad fácil!"
								+ "\n¿Por qué no lo intentamos en díficil ahora?");
					}
					else 
					{
						JOptionPane.showMessageDialog(getParent(), "Felicidades "+jugador.getNombre()+"! Le has ganado a la máquina en dificultad díficil!"
								+ "\nEres un (a) crack!");
					}
				}
				else if(jugador.getPuntuacion() < maquina.getPuntuacion())
				{
					JOptionPane.showMessageDialog(getParent(), "Oh no! Parece que no hemos podido vencer a la máquina esta vez..."
							+ "\nHay que esforzarnos para la siguiente!");
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Vaya! Parece que no hay ganadores esta vez...");
				}
				Principal.abrirFramePrincipal();
			}
		});
	}

	public int getDificultad() {
		return dificultad;
	}

	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}
}
