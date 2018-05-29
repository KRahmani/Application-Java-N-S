package controller;

public class InfoVille {
	private int id;
	private String nom, region, cp, telAgenceTourisme, telCommissariat, telMairie, telMedecin, telPharmacie;
	public InfoVille(int id, String nom, String region, String cp, String telAgenceTourisme, String telCommissariat,
			String telMairie, String telMedecin, String telPharmacie) {
		
		this.id = id;
		this.nom = nom;
		this.region = region;
		this.cp = cp;
		this.telAgenceTourisme = telAgenceTourisme;
		this.telCommissariat = telCommissariat;
		this.telMairie = telMairie;
		this.telMedecin = telMedecin;
		this.telPharmacie = telPharmacie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getTelAgenceTourisme() {
		return telAgenceTourisme;
	}
	public void setTelAgenceTourisme(String telAgenceTourisme) {
		this.telAgenceTourisme = telAgenceTourisme;
	}
	public String getTelCommissariat() {
		return telCommissariat;
	}
	public void setTelCommissariat(String telCommissariat) {
		this.telCommissariat = telCommissariat;
	}
	public String getTelMairie() {
		return telMairie;
	}
	public void setTelMairie(String telMairie) {
		this.telMairie = telMairie;
	}
	public String getTelMedecin() {
		return telMedecin;
	}
	public void setTelMedecin(String telMedecin) {
		this.telMedecin = telMedecin;
	}
	public String getTelPharmacie() {
		return telPharmacie;
	}
	public void setTelPharmacie(String telPharmacie) {
		this.telPharmacie = telPharmacie;
	}
	
	

}
