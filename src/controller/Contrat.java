package controller;

public class Contrat {
	
	private int idc, idagence, adAppart, idTiers;
	private String  etat, dateSign, dateDeb, dateFin;
	public Contrat(int idc, int idagence, int adAppart, int idTiers, String etat, String dateSign, String dateDeb,
			String dateFin) {
		
		this.idc = idc;
		this.idagence = idagence;
		this.adAppart = adAppart;
		this.idTiers = idTiers;
		this.etat = etat;
		this.dateSign = dateSign;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
	}
	public int getIdc() {
		return idc;
	}
	public void setIdc(int idc) {
		this.idc = idc;
	}
	public int getIdagence() {
		return idagence;
	}
	public void setIdagence(int idagence) {
		this.idagence = idagence;
	}
	public int getAdAppart() {
		return adAppart;
	}
	public void setAdAppart(int adAppart) {
		this.adAppart = adAppart;
	}
	public int getIdTiers() {
		return idTiers;
	}
	public void setIdTiers(int idTiers) {
		this.idTiers = idTiers;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getDateSign() {
		return dateSign;
	}
	public void setDateSign(String dateSign) {
		this.dateSign = dateSign;
	}
	public String getDateDeb() {
		return dateDeb;
	}
	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	
	

}
