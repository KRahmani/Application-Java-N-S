package controller;

import java.sql.Date;

public class Reservation {
	
	private int id, idAppart, idSaison, idAgence, idtiers,nbPersonnes;
	private String etat;
	private Date dateRes, dateDeb, dateFin;
	private float montant;
	public Reservation(int id, int idAppart, int idSaison, int idAgence, int idtiers,  String etat,
			Date dateRes, Date dateDeb, Date dateFin ,int nbPersonnes, float montant) {
	
		this.id = id;
		this.idAppart = idAppart;
		this.idSaison = idSaison;
		this.idAgence = idAgence;
		this.idtiers = idtiers;
		this.etat = etat;
		this.dateRes = dateRes;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.nbPersonnes = nbPersonnes;
		this.montant = montant;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAppart() {
		return idAppart;
	}
	public void setIdAppart(int idAppart) {
		this.idAppart = idAppart;
	}
	public int getIdSaison() {
		return idSaison;
	}
	public void setIdSaison(int idSaison) {
		this.idSaison = idSaison;
	}
	public int getIdAgence() {
		return idAgence;
	}
	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}
	public int getIdtiers() {
		return idtiers;
	}
	public void setIdtiers(int idtiers) {
		this.idtiers = idtiers;
	}
	public int getNbPersonnes() {
		return nbPersonnes;
	}
	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getDateRes() {
		return dateRes;
	}
	public void setDateRes(Date dateRes) {
		this.dateRes = dateRes;
	}
	public Date getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
	
	

}
