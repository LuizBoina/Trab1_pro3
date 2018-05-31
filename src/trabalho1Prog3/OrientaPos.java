package trabalho1Prog3;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OrientaPos extends Orientacao implements Comparable<OrientaPos> {
	private Date dataIngresso;
	private String programa;

	public OrientaPos(String[] celulas, Discente dis) {
		super(celulas[0], celulas[4], dis);
		this.programa = celulas[3];
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dataIngresso = formato.parse(celulas[2]);
		} catch (ParseException e) {}
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
		if(this.programa.compareTo(outraOrientaPos.programa)>0)
			return 1;
		else if(this.programa.compareTo(outraOrientaPos.programa)<0)
			return -1;
		else {
			if(this.dataIngresso.compareTo(outraOrientaPos.dataIngresso)>0)
				return 1;
			else if(this.dataIngresso.compareTo(outraOrientaPos.dataIngresso)<0)
				return -1;
			else {
				if(super.discente.getNome().compareTo(outraOrientaPos.discente.getNome())>=0)
					return 1;
				else
					return -1;
			}
		}
	}
}
