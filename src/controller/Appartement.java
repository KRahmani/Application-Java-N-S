package controller;

public class Appartement {
	
	private int idAppart, idVille, idRegion, numImmeuble, capacite;
	private String adresse, cp, type, exposition, lienphoto;
	private float surface, distancePiste, prix;
	public Appartement(int idAppart, int idVille, int idRegion, int numImmeuble, int capacite, String adresse,
			String cp, String type, String exposition, String lienphoto, float surface, float distancePiste,
			float prix) {
		
		this.idAppart = idAppart;
		this.idVille = idVille;
		this.idRegion = idRegion;
		this.numImmeuble = numImmeuble;
		this.capacite = capacite;
		this.adresse = adresse;
		this.cp = cp;
		this.type = type;
		this.exposition = exposition;
		this.lienphoto = lienphoto;
		this.surface = surface;
		this.distancePiste = distancePiste;
		this.prix = prix;
	}
	public int getIdAppart() {
		return idAppart;
	}
	public void setIdAppart(int idAppart) {
		this.idAppart = idAppart;
	}
	public int getIdVille() {
		return idVille;
	}
	public void setIdVille(int idVille) {
		this.idVille = idVille;
	}
	public int getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}
	public int getNumImmeuble() {
		return numImmeuble;
	}
	public void setNumImmeuble(int numImmeuble) {
		this.numImmeuble = numImmeuble;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExposition() {
		return exposition;
	}
	public void setExposition(String exposition) {
		this.exposition = exposition;
	}
	public String getLienphoto() {
		return lienphoto;
	}
	public void setLienphoto(String lienphoto) {
		this.lienphoto = lienphoto;
	}
	public float getSurface() {
		return surface;
	}
	public void setSurface(float surface) {
		this.surface = surface;
	}
	public float getDistancePiste() {
		return distancePiste;
	}
	public void setDistancePiste(float distancePiste) {
		this.distancePiste = distancePiste;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	
	

}
