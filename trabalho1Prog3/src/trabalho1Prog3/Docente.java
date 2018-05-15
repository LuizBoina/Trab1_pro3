package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Docente {
	List<ProducaoCientifica> prodCientificas;
	private String nome;
	private int codigo;
	private String departamento;

	public Docente(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
		this.departamento = celulas[2];
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
	}

	public String getDepartamento() {
		return this.departamento;
	}
}
