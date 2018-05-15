package trabalho1Prog3;

public class ProducaoCientifica {
	final int codigoDocente;
	private String titulo;
	final boolean qualificada;
	
	public ProducoesCientificas(String celulas) {
		try {
			this.codigoDocente = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.titulo = celulas[1];
		if(celulas[3] == null)
			this.qualificada = false;
		else
			this.qualificada = true;
	}
	
	public int getCodigoDocente() {
		return this.codigoDocente;
	}
}
