package trabalho1Prog3;

public class ProducaoCientifica {
	private int codigoDocente;
	private String titulo;
	private boolean qualificada;

	public ProducaoCientifica(String[] celulas) {
		
		try {
			this.codigoDocente = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.titulo = celulas[1];
		try {
			if (celulas[2].equals("X"))
				this.qualificada = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.qualificada = false;
		}

	}

	public int getCodigoDocente() {
		return this.codigoDocente;
	}
}
