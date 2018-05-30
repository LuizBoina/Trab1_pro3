package trabalho1Prog3;

import java.util.List;
import java.util.Locale;
import java.text.Collator;
import java.util.ArrayList;

public class Curso implements Comparable<Curso> {
	private List<Disciplina> disciplinas;
	private int codigo;
	private String nome;
	private boolean ehGrad;
	private List<Discente> discentes;

	public Curso(String[] celulas) throws NumberFormatException {
		this.codigo = Integer.parseInt(celulas[0]);
		discentes = new ArrayList<Discente>();
		disciplinas = new ArrayList<Disciplina>();
		this.nome = celulas[1];
		try {//melhorar isso talvez
			if (celulas[2].equals("X"))
				this.ehGrad = true;
		}catch(ArrayIndexOutOfBoundsException err) {
			this.ehGrad = false;
		}
	}

	public String getNome() {
		return this.nome;
	}

	public int TotalHorasDeDocente(int codDocente) {
		int qtd = 0;
		for (Disciplina disci : disciplinas) {
			if (disci.getCodigoDocente() == codDocente)
				qtd += disci.getcHSemestral();
		}
		return qtd;
	}

	public List<Discente> getDiscentes() {
		return this.discentes;
	}

	public int getCodigoCurso() {
		return this.codigo;
	}

	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	public void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}

	public void adicionaDiscenteNoCurso(Discente discente) {
		discentes.add(discente);
	}

	public int compareTo(Curso outroCurso) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroCurso.getNome());
	}

}
