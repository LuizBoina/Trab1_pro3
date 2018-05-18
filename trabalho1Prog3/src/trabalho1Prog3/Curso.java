package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Curso {
	private List<Disciplina> disciplinas;
	private int codigo;
	private String nome;
	private boolean ehGrad;

	public Curso(String[] celulas) {
		disciplinas = new ArrayList<Disciplina>();
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
		try {
			if (celulas[2].equals("X"))
				this.ehGrad = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.ehGrad = false;
		}
	}

	public int getCodigoCurso() {
		return this.codigo;
	}

	public void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}

}
