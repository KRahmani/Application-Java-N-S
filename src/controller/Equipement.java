package controller;

public class Equipement {
	private int idE, nb, idAppar;
	private String NomE;
	
	public Equipement(int idE, int nb, int idAppar, String nomE) {
		
		this.idE = idE;
		this.idAppar = idAppar;
		NomE = nomE;
		this.nb = nb;
	}

	public int getIdE() {
		return idE;
	}

	public void setIdE(int idE) {
		this.idE = idE;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	public int getIdAppar() {
		return idAppar;
	}

	public void setIdAppar(int idAppar) {
		this.idAppar = idAppar;
	}

	public String getNomE() {
		return NomE;
	}

	public void setNomE(String nomE) {
		NomE = nomE;
	}
	
	

}
