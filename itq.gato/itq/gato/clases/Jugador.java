package itq.gato.clases;

public class Jugador {
	
	private String nombre;
	private int puntuacion;
	private String caracter;
	
	public Jugador(String nombre, String caracter)
	{
		this.setNombre(nombre);
		this.setPuntuacion(0);
		this.setCaracter(caracter);
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	
}
