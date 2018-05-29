package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	private String serveur, bdd, user, mdp;
	private Connection maConnexion; // on �crit conne puis contrl espace on choisit la connection java,sql
	
	public Bdd (String serveur, String bdd, String user, String mdp){
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnexion = null;
	}
	//v�rification de la pr�sence du pilote JDBC (qu'on a t�l�charg� et inclut en cliquant sur src puis proprieties puis libraries puis addExternal puis on a choisit .jar dans le dossier d�compress�)
	public void changerPilote (){
		try
		{
			Class.forName("com.mysql;jdbc.Driver");
		}
		catch (ClassNotFoundException exp){
			System.out.println("Absence du pilote jdbc");
		}
	}
	//m�thode de connexion � la bdd
	public void seConnecter(){
		String url = "jdbc:mysql://"+this.serveur+"/"+this.bdd;
		try
		{
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
			
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de connexion �: "+url);
		}
		
	}
	//m�thode de d�connexion de la bdd
	public void seDeconnecter(){
		
		try{
			if (this.maConnexion != null ){
				this.maConnexion.close();
			}
		}
		catch (SQLException exp){
			System.out.println("Erreur de fermeture de la connexion");
		}
	}
	//getter sur la variable maConnexion
	public  Connection getMaConnexion(){
		return this.maConnexion;
	}
	public String getServeur() {
		return serveur;
	}
	public void setServeur(String serveur) {
		this.serveur = serveur;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	

}
