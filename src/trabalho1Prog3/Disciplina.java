package trabalho1Prog3;

import java.io.Serializable;

public class Disciplina implements Comparable<Disciplina> , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private  String nome;
	private int codigoDocente;
	private int cHSemanal;
	private int cHSemestral;
	private Curso curso;

	public Disciplina(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
			this.codigoDocente = Integer.parseInt(celulas[2]);
			this.cHSemanal = Integer.parseInt(celulas[3]);
			this.cHSemestral = Integer.parseInt(celulas[4]);
			this.curso = new Curso(Integer.parseInt(celulas[5]));

		} catch (NumberFormatException e) {
			System.out.println("Numero com formato errado!");
		}
		this.nome = celulas[1];
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public int getCodigoCurso() {
		return this.curso.getCodigoCurso();
	}
	
	public int compareTo(Disciplina outraDisciplina) {
		return this.codigo-outraDisciplina.codigo;
	}
	
	public int getCodigoDocente() {
		return this.codigoDocente;
	}
	
	public int getcHSemanal() {
		return this.cHSemanal;
	}
	
	public int getcHSemestral() {
		return this.cHSemestral;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso2) {
		curso = curso2;
	}
}
