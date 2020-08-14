package cl.onemarketer.model;

import cl.onemarketer.entities.EmpEmpresas;
import cl.onemarketer.entities.PerPersonas;

public class RelEmpresasPersonas {
	private Integer idEmpPersonas;
	private EmpEmpresas empEmpresas;
	private PerPersonas perPersonas;
	
	public Integer getIdEmpPersonas() {
		return idEmpPersonas;
	}
	public void setIdEmpPersonas(Integer idEmpPersonas) {
		this.idEmpPersonas = idEmpPersonas;
	}
	public EmpEmpresas getEmpEmpresas() {
		return empEmpresas;
	}
	public void setEmpEmpresas(EmpEmpresas empEmpresas) {
		this.empEmpresas = empEmpresas;
	}
	public PerPersonas getPerPersonas() {
		return perPersonas;
	}
	public void setPerPersonas(PerPersonas perPersonas) {
		this.perPersonas = perPersonas;
	}

}
