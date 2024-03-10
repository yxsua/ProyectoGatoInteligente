package itq.gato.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import itq.gato.main.*;

@SuppressWarnings("serial")
public class FramePrincipal extends JFrame {
	
	private JPanel panelModos, panelInformacion, panelLabels;
	private JLabel labelJuego, labelImagen, labelModos;
	private JButton btnMultijugador, btnMaquinaJugador;
	
	public FramePrincipal() 
	{
		this.setTitle("Juego del gato");
		this.setLayout(new BorderLayout());
		
		ImageIcon imagen = new ImageIcon(getToolkit().getImage("res/gato_main.png").getScaledInstance(250, 250, Image.SCALE_SMOOTH));
		labelImagen = new JLabel(imagen);
		this.setIconImage(getToolkit().getImage("res/gato_main.png"));
		
		labelJuego = new JLabel("Bienvenido al juego del gato!");
		labelModos = new JLabel("Escoge un modo de juego:");
		labelJuego.setFont(new Font("Monospace", Font.BOLD, 30));
		labelModos.setFont(new Font("Monospace", Font.ITALIC, 25));
		
		panelInformacion = new JPanel(new BorderLayout());
		panelLabels = new JPanel(new FlowLayout());
		panelLabels.add(labelJuego);
		panelLabels.add(labelModos);
		panelInformacion.add(labelImagen, BorderLayout.NORTH);
		panelInformacion.add(panelLabels, BorderLayout.CENTER);
		panelInformacion.setBackground(new Color(7, 162, 145));
		
		btnMultijugador = new JButton("  Partida multijugador  ");
		btnMaquinaJugador = new JButton("  Partida de un jugador  ");
		btnMultijugador.setFont(new Font("Monospace", Font.BOLD, 20));
		btnMaquinaJugador.setFont(new Font("Monospace", Font.BOLD, 20));
		btnMultijugador.setBackground(new Color(96, 238, 223));
		btnMaquinaJugador.setBackground(new Color(96, 238, 223));
		btnMultijugador.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3), new Color(7, 162, 145)));
		btnMaquinaJugador.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3), new Color(7, 162, 145)));
		
		panelModos = new JPanel(new FlowLayout());
		panelModos.add(btnMultijugador);
		panelModos.add(btnMaquinaJugador);
		panelModos.setBackground(new Color(201, 248, 239));
		
		btnMultijugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Principal.abrirFrameMultijugador();
			}
			
		});
		btnMaquinaJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				Principal.abrirFrameUnJugador();
			}
			
		});
		
		this.add(panelInformacion, BorderLayout.CENTER);
		this.add(panelModos, BorderLayout.SOUTH);
		this.setBounds(600, 300, 650, 450);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

}
