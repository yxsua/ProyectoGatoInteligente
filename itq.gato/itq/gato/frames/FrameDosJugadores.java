package itq.gato.frames;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import itq.gato.clases.*;
import itq.gato.main.Principal;

@SuppressWarnings("serial")
public class FrameDosJugadores extends FrameGato {
		
		public FrameDosJugadores ()
		{
			super();
			this.setTitle("Partida multijugador");
			
			// Creamos los jugadores (2 porque es multijugador).
			Jugador jugador1 = new Jugador(JOptionPane.showInputDialog(getParent(), "Ingresa el nombre del jugador 1:", "Datos del jugador", JOptionPane.INFORMATION_MESSAGE), "O");
			Jugador jugador2 = new Jugador(JOptionPane.showInputDialog(getParent(), "Ingresa el nombre del jugador 2:", "Datos del jugador", JOptionPane.INFORMATION_MESSAGE), "X");
			
			// Primero establecemos los valores de los nombres de los jugadores
			this.nombreJugador1.setText(jugador1.getNombre());
			this.nombreJugador2.setText(jugador2.getNombre());
			
			// Inicializamos el label con información del turno
			this.turnoJugador.setText("Turno del jugador: " + jugador1.getNombre());
			
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
								
								// Se revisa de quien es turno para empezar a jugar
								// es posible cambiar los caracteres con los que se juega solo mediante el código
								if(turno)
								{
									// Cambiamos el texto al del caracter del jugador,
									fuente.setText(jugador1.getCaracter());
									fuente.setForeground(Color.WHITE);
									// Cambiamos la información del label con la información del turno
									turnoJugador.setText("Turno del jugador: " + jugador1.getNombre());
								}
								else
								{
									fuente.setText(jugador2.getCaracter());
									fuente.setForeground(new Color(102, 102, 102));
									turnoJugador.setText("Turno del jugador: " + jugador2.getNombre());
								}
								// Cuando un jugador tira, se cambia el turno al siguiente jugador
								turno = !turno;
								
								// la función regresará "1" si alguien ha ganado y "-1" es un empate
								// aunque no se evalua, se regresa un "0" si todavia sigue el juego
								int estado = verificarCasillas(fuente.getText());
								if(estado == -1) 
								{
									// Mostramos mensaje de empate, el siguiente juego
									// lo iniciará la misma persona
									JOptionPane.showMessageDialog(getParent(), "Es un empate!");
									// Al final de cada partida se vacía el tablero
									reiniciarCasillas();
									// Es necesario cambiar el turno para que siga la 
									// misma persona en el primer turno
									turno = !turno;
								} 
								else if(estado == 1) 
								{
									if(jugador1.getCaracter().equals(fuente.getText()))
									{
										// Si ha ganado el jugador 1, se le aumenta la puntuación
										// y se muestra en el Label de puntuación.
										JOptionPane.showMessageDialog(getParent(), "Ha ganado el jugador: "+jugador1.getNombre()+"!");
										jugador1.setPuntuacion(jugador1.getPuntuacion()+1);
										puntuacionJugador1.setText(jugador1.getPuntuacion()+" puntos");
									}
									else if(jugador2.getCaracter().equals(fuente.getText()))
									{
										// Si ha ganado el jugador 2, se le aumenta la puntuación
										// y se muestra en el Label de puntuación.
										JOptionPane.showMessageDialog(getParent(), "Ha ganado el jugador: "+jugador2.getNombre()+"!");
										jugador2.setPuntuacion(jugador2.getPuntuacion()+1);
										puntuacionJugador2.setText(jugador2.getPuntuacion()+" puntos");
									}
									// Dependiendo de quien haya sido el turno de quien ganó, la siguiente partida
									// la inicia el jugador contrario
									if(turno) {
										JOptionPane.showMessageDialog(getParent(), "Cambio de turno! Empieza el jugador "+jugador1.getNombre());
										turnoJugador.setText("Turno del jugador: " + jugador1.getNombre());
									} else {
										JOptionPane.showMessageDialog(getParent(), "Cambio de turno! Empieza el jugador "+jugador2.getNombre());
										turnoJugador.setText("Turno del jugador: " + jugador2.getNombre());
									}
									// Al final de cada partida se vacía el tablero
									reiniciarCasillas();
								}
							}
						}
					});
				}
			}
			
			this.add(panelJuego);
			this.add(panelInformacion);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					if(jugador1.getPuntuacion() > jugador2.getPuntuacion())
					{
						JOptionPane.showMessageDialog(getParent(), "Felicidades "+jugador1.getNombre()+"! Has vencido a "+jugador2.getNombre()+"!");
					}
					else if(jugador1.getPuntuacion() < jugador2.getPuntuacion())
					{
						JOptionPane.showMessageDialog(getParent(), "Felicidades "+jugador2.getNombre()+"! Has vencido a "+jugador1.getNombre()+"!");
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Vaya! Parece que no hay ganadores esta vez...");
					}
					Principal.abrirFramePrincipal();
				}
			});
		}
		
}
