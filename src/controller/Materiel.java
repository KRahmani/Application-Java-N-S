package controller;

public class Materiel {
	
	private int idM, idProprio;
	private String type, etat, image;
	private float prix;
	public Materiel(int idM, int idProprio, String type, String etat, String image, float prix) {
	
		this.idM = idM;
		this.idProprio = idProprio;
		this.type = type;
		this.etat = etat;
		this.image = image;
		this.prix = prix;
	}
	public int getIdM() {
		return idM;
	}
	public void setIdM(int idM) {
		this.idM = idM;
	}
	public int getIdProprio() {
		return idProprio;
	}
	public void setIdProprio(int idProprio) {
		this.idProprio = idProprio;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	
	

}
