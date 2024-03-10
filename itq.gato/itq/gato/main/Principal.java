package itq.gato.main;

import javax.swing.JFrame;
import itq.gato.frames.*;

public class Principal {

	public static void main(String[] args)
	{
		abrirFramePrincipal();
	}
	
	public static void abrirFramePrincipal()
	{
		System.out.println("Abriendo frame principal");
		JFrame mainFrame = new FramePrincipal();
		mainFrame.setVisible(true);
	}
	
	public static void abrirFrameMultijugador()
	{
		System.out.println("Abriendo frame de dos jugadores");
		JFrame frameDosJugadores = new FrameDosJugadores();
		frameDosJugadores.setBounds(650, 200, 550, 700);
		frameDosJugadores.setVisible(true);
	}
	
	public static void abrirFrameUnJugador()
	{
		System.out.println("Abriendo frame de un jugador");
		JFrame frameJugadorMaquina = new FrameJugadorMaquina();
		frameJugadorMaquina.setBounds(650, 200, 550, 700);
		frameJugadorMaquina.setVisible(true);
	}
}
