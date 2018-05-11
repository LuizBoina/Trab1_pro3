package trabalho;

import java.util.List;

public class Faculdade {
	private  List departamentos;
	
	public Faculdade() {
		departamentos = new ArrayList();
	}
	
	public void criaDepartamentos(int numDocentes, String[][] planilhaDocentes) {	//planilha[numDocentes][3]
		Docente[] docentes = new Docente[numDocentes];
			for (int i = 0; i < numDocentes; i++) {
				docentes[i] = new Docente();
				docentes[i].preencheDocente(planilhaDocentes[i]);
				if(departamentoExiste(docentes[i].getDepartamento())) {
					
				}
			}
		}
	public boolean departamentoExiste(String departamento) {
		for(int i = 0;i<this.departamentos.size();i++) {
			if(departamento.equals(departamentos.))
		}
		return false;
	}
}
