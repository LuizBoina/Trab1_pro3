package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Curso {
	private List<Disciplina> disciplinas;
	private int codigo;
	private String nome;
	private boolean grad;
	private boolean posGrad;

	public Curso(String celulas[]) {
		disciplinas = new ArrayList<Disciplina>();
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
		if (celulas[2].length() == 0)
			this.grad = false;
		else
			this.grad = true;
		if (celulas[3].length() == 0)
			this.posGrad = false;
		else
			this.posGrad = true;
	}
	public int getCodigoCurso() {
		return this.codigo;
	}

}
