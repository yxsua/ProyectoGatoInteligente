package itq.gato.frames;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FrameGato extends JFrame {

	// Inicializamos los componentes que tendrán en común el Frame de
	// juego multijugador y juego de maquina vs Usuario
	protected Panel panelJuego, panelInformacion, 
			panelPuntuacion, panelNombres, panelTurno;
	protected Label nombreJugador1, nombreJugador2,
			puntuacionJugador1, puntuacionJugador2, turnoJugador;
	protected JLabel labelImagen;
	protected boolean turno;
	protected JButton[][] casillas = new JButton[3][3];
	
	public FrameGato()
	{
		this.setLayout(new GridLayout(2, 1));
		
		panelJuego = new Panel();
		panelJuego.setLayout(new GridLayout(3, 3));
		
		Font fuenteNombres = new Font("Monospace", Font.BOLD, 30);
		nombreJugador1 = new Label("nombre1");
		nombreJugador2 = new Label("nombre2");
		nombreJugador1.setFont(fuenteNombres);
		nombreJugador2.setFont(fuenteNombres);
		nombreJugador1.setForeground(Color.white);
		nombreJugador2.setForeground(Color.white);
		
		Font fuentePuntuacion = new Font("Monospace", Font.BOLD, 20);
		puntuacionJugador1 = new Label("0 puntos");
		puntuacionJugador2 = new Label("0 puntos");
		puntuacionJugador1.setFont(fuentePuntuacion);
		puntuacionJugador2.setFont(fuentePuntuacion);
		puntuacionJugador1.setForeground(new Color(102, 102, 102));
		puntuacionJugador2.setForeground(new Color(102, 102, 102));
		
		panelInformacion = new Panel();
		panelInformacion.setLayout(new BorderLayout());
		
		panelPuntuacion = new Panel();
		panelNombres = new Panel();
		panelPuntuacion.setLayout(new FlowLayout());
		panelInformacion.setLayout(new BorderLayout());
		
		panelPuntuacion.add(puntuacionJugador1);
		panelPuntuacion.add(puntuacionJugador2);
		
		panelNombres.add(nombreJugador1);
		panelNombres.add(nombreJugador2);
		panelNombres.setBackground(new Color(0, 138, 132));
		
		panelInformacion.add(panelPuntuacion, BorderLayout.CENTER);
		panelInformacion.add(panelNombres, BorderLayout.NORTH);
		
		this.setIconImage(getToolkit().getImage("res/gato_main.png"));
		ImageIcon imagen = new ImageIcon(getToolkit().getImage("res/gato_main.png").getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		labelImagen = new JLabel(imagen);
		
		panelTurno = new Panel(new BorderLayout());
		panelTurno.add(labelImagen, BorderLayout.SOUTH);
		turnoJugador = new Label();
		turnoJugador.setForeground(new Color(102, 102, 102));
		turnoJugador.setFont(fuentePuntuacion);
		Panel panel = new Panel(new FlowLayout());
		panel.add(turnoJugador);
		panelTurno.add(panel);
		
		panelInformacion.add(panelTurno, BorderLayout.SOUTH);
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				casillas[i][j] = new JButton();
				casillas[i][j].setBackground(new Color(30, 202, 184));
				casillas[i][j].setFont(new Font("Monospace", Font.BOLD, 60));
				casillas[i][j].setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3), new Color(7, 162, 145)));
				panelJuego.add(casillas[i][j]);
			}
		}
		
		this.add(panelJuego);
		this.add(panelInformacion);
		this.setResizable(false);
	}
	
	protected int verificarCasillas(String caracter) {
		// Verificamos si la persona que jugó el último turno pudo completar las 3 líneas
		for(int i = 0; i < 3; i++)
		{
			if(casillas[0][i].getText().equals(caracter) && casillas[1][i].getText().equals(caracter) && casillas[2][i].getText().equals(caracter))
			{
				return 1;
			}
			else if(casillas[i][0].getText().equals(caracter) && casillas[i][1].getText().equals(caracter) && casillas[i][2].getText().equals(caracter))
			{
				return 1;
			}
		}
		if(casillas[0][0].getText().equals(caracter) && casillas[1][1].getText().equals(caracter) && casillas[2][2].getText().equals(caracter))
		{
			return 1;
		} 
		else if(casillas[0][2].getText().equals(caracter) && casillas[1][1].getText().equals(caracter) && casillas[2][0].getText().equals(caracter))
		{
			return 1;
		}
		
		// Si todavía hay casillas sin seleccionar y nadie ha ganado, continuamos
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(casillas[i][j].getText().equals(""))
				{
					return 0;
				}
			}
		}
		
		return -1;
	}

	protected void reiniciarCasillas() 
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				casillas[i][j].setText("");
			}
		}
	}
}
