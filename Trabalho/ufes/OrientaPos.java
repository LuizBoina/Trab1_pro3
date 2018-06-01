package ufes;

import java.util.Date;
import java.util.Locale;

import exceptions.ErroDataNoFuturo;

import java.io.Serializable;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrientaPos extends Orientacao implements Comparable<OrientaPos>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dataIngresso;
	private String programa;

	public OrientaPos(String[] celulas, Discente dis) throws NumberFormatException, ParseException, ErroDataNoFuturo {
		super(celulas[0], celulas[4], dis);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.dataIngresso = formato.parse(celulas[2]);
		Date dataAtual = new Date();
		if (this.dataIngresso.after(dataAtual))
			throw new ErroDataNoFuturo(super.getNomeDiscente(), this.getDateString());
		this.programa = celulas[3];
	}

	public String getPrograma() {
		return this.programa;
	}

	public int getMatriculaDiscente() {
		return super.discente.getMatricula();
	}

	public String getNomeDiscente() {
		return super.discente.getNome();
	}

	public String getDateString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataIngresso);
	}

	public int compareTo(OrientaPos outraOrientaPos) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		int result = col.compare(this.getPrograma(), outraOrientaPos.getPrograma());
		if (result > 0)
			return 1;
		else if (result < 0)
			return -1;
		else {
			if (this.dataIngresso.compareTo(outraOrientaPos.dataIngresso) > 0)
				return 1;
			else if (this.dataIngresso.compareTo(outraOrientaPos.dataIngresso) < 0)
				return -1;
			else {
				int resultDiscente = col.compare(super.discente.getNome(), outraOrientaPos.discente.getNome());
				if (resultDiscente >= 0)
					return 1;
				else
					return -1;
			}
		}
	}
}
