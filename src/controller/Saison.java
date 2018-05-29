package controller;

public class Saison {
	
	private int id;
	private String nom;
	private float reduction;
	public Saison(int id, String nom, float reduction) {

		this.id = id;
		this.nom = nom;
		this.reduction = reduction;
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
	public float getReduction() {
		return reduction;
	}
	public void setReduction(float reduction) {
		this.reduction = reduction;
	}
	
	

}
