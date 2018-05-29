package controller;

public class Locataire {
	
	private int id;
	private String noml, prenoml;
	public Locataire(int id, String noml, String prenoml) {

		this.id = id;
		this.noml = noml;
		this.prenoml = prenoml;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNoml() {
		return noml;
	}
	public void setNoml(String noml) {
		this.noml = noml;
	}
	public String getPrenoml() {
		return prenoml;
	}
	public void setPrenoml(String prenoml) {
		this.prenoml = prenoml;
	}
	
	

}
