package modele;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Proprietaire;
import controller.Region;
import controller.Reservation;
import controller.Saison;
import controller.InfoVille;
import controller.Locataire;
import controller.Materiel;
import controller.Agence;
import controller.Appartement;
import controller.Equipement;


public class Modele {
	
	//elle récupere le login et le mdp et nous dit si la personne a le droit d'acces ou pas
		public static String verifConnexion(String login, String mdp){
			String requete = "select count(*) as nb, droits "+
					"from user where login = '"+login+"' and mdp ='"+mdp + "'; ";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			String droits ="";
			
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					int nb = unRes.getInt("nb");
					if (nb != 0) droits = unRes.getString("droits");
				}
				uneBdd.seDeconnecter();
				unStat.close();
				unRes.close();
			}
			catch (SQLException exp){
				System.out.println("Erreur "+ requete);
			}
			return droits;
		}
		
		public static int getMaxIdTiers(){
			String requete="select max(IDTIERS) as maxId from TIERS ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			int max = 0;
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					max = unRes.getInt("maxId");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return max;
		}
		
		public static int getIdProp(String nom, String prenom){
			String requete="select IDTIERS from PROPRIETAIRE where NOMP='" +nom+"' and PRENOMP = '"+prenom+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			int idProp = 0;
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					idProp = unRes.getInt("IDTIERS");
				}
				else{
					idProp = 0;
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return idProp;
		}
		
		public static void insertProprietaire(Proprietaire unProprietaire){
			String requete="insert into PROPRIETAIRE values('"+
					unProprietaire.getId()+"', '" +
					unProprietaire.getCivilite()+"', '" +unProprietaire.getNom()+"', '" +
					unProprietaire.getPrenom()+"', '" + unProprietaire.getAdresse() +"', '" +
					unProprietaire.getCp()+"', '" +unProprietaire.getVille()+"', '" + 
					unProprietaire.getTel() +"', '" +unProprietaire.getRib()+"', '" +
					unProprietaire.getEmail()+"', '" + unProprietaire.getMdp()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static void updateProprietaire(Proprietaire unProprietaire){
			String requete="update PROPRIETAIRE set CIVILITE ='"+
					unProprietaire.getCivilite()+"', ADRESSEP = '" + unProprietaire.getAdresse() +"', CODEPOSTAL = '" +
					unProprietaire.getCp()+"', VILLE = '" +unProprietaire.getVille()+"', TEL = '" + 
					unProprietaire.getTel() +"', RIB = '" +unProprietaire.getRib()+"', EMAIL ='" +
					unProprietaire.getEmail()+"', MOT_PASSE ='" + unProprietaire.getMdp()+ "'where NOMP = '"+unProprietaire.getNom()+"' and PRENOMP = '"+unProprietaire.getPrenom()+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static ArrayList<InfoVille> getVilles(){
			ArrayList<InfoVille> lesVilles = new ArrayList<InfoVille>();
			String requete="select * from INFO_VILLE ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			;
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDVILLE");
					String nom = unRes.getString("NOMV");
					String cp = unRes.getString("CODEPOSTAL");
					String region = unRes.getString("REGIONV");
					String telAgenceTour = unRes.getString("TEL_AGENCE_TOURISME");
					String telCommiss = unRes.getString("TEL_COMISSARIAT");
					String telMairie = unRes.getString("TEL_MAIRIE");
					String telMedecin = unRes.getString("TEL_MEDECIN");
					String telPharmacie = unRes.getString("TEL_PHARMACIE");
					InfoVille uneVille = new InfoVille(id,nom, region, cp, telAgenceTour, telCommiss, telMairie, telMedecin, telPharmacie);
					lesVilles.add(uneVille);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesVilles;
		}
		
		public static ArrayList<Region> getRegions(){
			ArrayList<Region> lesRegion = new ArrayList<Region>();
			String requete="select * from REGION ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDREGION");
					String nom = unRes.getString("NOMR");
					
					Region uneRegion = new Region(id,nom);
					lesRegion.add(uneRegion);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesRegion;
		}
		
		public static void insertAppartement(Appartement unAppartement){
			String requete="insert into Appartement values(null,'"+
					unAppartement.getIdVille()+"', '" +
					unAppartement.getIdRegion()+"', '" +unAppartement.getNumImmeuble()+"', '" +
					unAppartement.getAdresse()+"', '" + unAppartement.getCp() +"', '" +
					unAppartement.getType()+"', '" +unAppartement.getSurface()+"', '" + 
					unAppartement.getExposition() +"', '" +unAppartement.getCapacite()+"', '" +
					unAppartement.getDistancePiste()+"', '" + unAppartement.getPrix()+"', '" +unAppartement.getLienphoto()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static int getIdRegion(String nom){
			String requete="select IDREGION from REGION where NOMR='" +nom+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			int idRegion = 0;
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					idRegion = unRes.getInt("IDREGION");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return idRegion;
		}
		
		public static int getIdVille(String nom){
			String requete="select IDVILLE from INFO_VILLE where NOMV='" +nom+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			int idVille = 0;
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					idVille = unRes.getInt("IDVILLE");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return idVille;
		}
		
		public static String getCodePostalVille(String nomVille){
			String requete="select CODEPOSTAL from INFO_VILLE where NOMV='" +nomVille+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			String cp ="";
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					cp = unRes.getString("CODEPOSTAL");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return cp;
		}
		
		public static void insertRegion(Region uneRegion){
			String requete="insert into REGION values(null,'"+
					uneRegion.getNom()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static void insertInfoVille(InfoVille uneVille){
			String requete="insert into INFO_VILLE values(null,'"+
					uneVille.getNom()+"', '" +
					uneVille.getRegion()+"', '" +uneVille.getCp()+"', '" +
					uneVille.getTelAgenceTourisme()+"', '" + uneVille.getTelCommissariat() +"', '" +
					uneVille.getTelMairie()+"', '" +uneVille.getTelMedecin()+"', '" + 
					uneVille.getTelPharmacie()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static ArrayList<Appartement> getAppartements(){
			ArrayList<Appartement> lesAppartements = new ArrayList<Appartement>();
			String requete="select * from APPARTEMENT ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDAPPARTEMENT");
					int idVille = unRes.getInt("IDVILLE");
					int idReg = unRes.getInt("IDREGION");
					int numImm = unRes.getInt("NUM_IMMEUBLE");
					String adresse = unRes.getString("ADRESSEAPP");
					String cp = unRes.getString("CODEPOSTAL");
					String type = unRes.getString("TYPEAPPART");
					float surface = unRes.getFloat("SURFACE");
					String exposition = unRes.getString("EXPOSITION");
					int capacite = unRes.getInt("CAPACITE_ACCUEIL");
					float distance = unRes.getFloat("DISTANCE_PISTE");
					float prix = unRes.getFloat("PRIX_BASE");
					String lien = unRes.getString("LIENPHOTO");
					
					Appartement unAppartement = new Appartement(id,idVille, idReg, numImm,capacite,adresse, cp, type,exposition,lien, surface,distance, prix);
					lesAppartements.add(unAppartement);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesAppartements;
		}
		
		public static void insertEquipement(Equipement unEquipement){
			String requete="insert into EQUIPEMENT values(null,'"+
					unEquipement.getIdAppar()+"', '" +unEquipement.getNomE()+"', '" +
					unEquipement.getNb()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static ArrayList<Proprietaire> getProprietaires(){
			ArrayList<Proprietaire> lesProprietaires = new ArrayList<Proprietaire>();
			String requete="select * from PROPRIETAIRE ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDTIERS");
					String civilite = unRes.getString("CIVILITE");
					String nom = unRes.getString("NOMP");
					String prenom = unRes.getString("PRENOMP");
					String adresse = unRes.getString("ADRESSEP");
					String cp = unRes.getString("CODEPOSTAL");
					String ville = unRes.getString("VILLE");
					String tel = unRes.getString("TEL");
					String rib = unRes.getString("RIB");
					String mail = unRes.getString("EMAIL");
					String mdp = unRes.getString("MOT_PASSE");
					
					Proprietaire unProprietaire = new Proprietaire(id,civilite, nom,prenom, adresse, cp, ville, tel,rib, mail, mdp);
					lesProprietaires.add(unProprietaire);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesProprietaires;
		}
		
		public static void insertMateriel(Materiel unMateriel){
			String requete="insert into MATERIEL values(null,'"+
					unMateriel.getIdProprio()+"', '" +unMateriel.getType()+"', '" +
					unMateriel.getEtat()+"', '" +unMateriel.getPrix()+"', '" +
					unMateriel.getImage()+"' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static ArrayList<Locataire> getLocataire(){
			ArrayList<Locataire> lesLocataires = new ArrayList<Locataire>();
			String requete="select * from Locataire ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDTIERS");
					String nom = unRes.getString("NOML");
					String prenom = unRes.getString("PRENOML");
					Locataire unLocataire = new Locataire(id,nom, prenom);
					lesLocataires.add(unLocataire);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesLocataires;
		}
		
		public static ArrayList<Saison> getLessaisons(){
			ArrayList<Saison> lesSaisons = new ArrayList<Saison>();
			String requete="select * from saison ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDSAISON");
					String nom = unRes.getString("NOMS");
					Float reduction = unRes.getFloat("REDUCTION");
					Saison uneSaison = new Saison(id,nom, reduction);
					lesSaisons.add(uneSaison);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesSaisons;
		}
		
		public static ArrayList<Agence> getLesagences(){
			ArrayList<Agence> lesAgences = new ArrayList<Agence>();
			String requete="select * from Agence ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDAGENCE");
					String nom = unRes.getString("NOMAGENCE");
			
					Agence uneAgence = new Agence(id,nom);
					lesAgences.add(uneAgence);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesAgences;
		}
		
		public static void insertReservation(Reservation uneReservation){
			String requete="insert into RESERVATION values(null,'" +
					uneReservation.getIdAppart()+"', '" +uneReservation.getIdSaison()+"', '" +
					uneReservation.getIdAgence()+"', '" + uneReservation.getIdtiers() +"', '" +
					uneReservation.getEtat()+"', '" +uneReservation.getDateRes()+"', '" + 
					uneReservation.getDateDeb()+"', '"+uneReservation.getDateFin()+"', '" +
					uneReservation.getNbPersonnes()+"', '"+uneReservation.getMontant()+ "' );";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
		public static ArrayList<Reservation> getReservations(){
			ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
			String requete="select * from RESERVATION ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				while (unRes.next()){
					int id = unRes.getInt("IDR");
					int idAppart = unRes.getInt("IDAPPARTEMENT");
					int idsais = unRes.getInt("IDSAISON");
					int idAgence = unRes.getInt("IDAGENCE");
					int idlocataire = unRes.getInt("IDTIERS");
					String etat = unRes.getString("ETAT");
					Date dateRes = unRes.getDate("DATERESERVATION");
					Date dateDeb = unRes.getDate("DATEDEBUT");
					Date dateFin = unRes.getDate("DATEFIN");
					int nbPersonnes = unRes.getInt("NBPERSONNES");
					int montant = unRes.getInt("MONTANT");
					
					Reservation uneReservation = new Reservation(id,idAppart, idsais, idAgence, idlocataire,etat ,dateRes ,dateDeb , dateFin, nbPersonnes,montant );
					lesReservations.add(uneReservation);  
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return lesReservations;
		}
		
		
		public static Reservation getReservation(int id){
			int idAppart,nbPersonnes, montant, idsais,  idAgence, idlocataire ;
			String etat; 
			Date dateRes,dateDeb,dateFin;
			Reservation uneRes =null;
			
			String requete="select * from RESERVATION where IDR='" +id+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
				
					 idAppart = unRes.getInt("IDAPPARTEMENT");
					 idsais = unRes.getInt("IDSAISON");
					 idAgence = unRes.getInt("IDAGENCE");
					 idlocataire = unRes.getInt("IDTIERS");
					 etat = unRes.getString("ETAT");
					 dateRes = unRes.getDate("DATERESERVATION");
					 dateDeb = unRes.getDate("DATEDEBUT");
					 dateFin = unRes.getDate("DATEFIN");
					 nbPersonnes = unRes.getInt("NBPERSONNES");
					 montant = unRes.getInt("MONTANT");
					
					 uneRes = new Reservation(id,idAppart, idsais, idAgence, idlocataire,etat ,dateRes ,dateDeb , dateFin, nbPersonnes,montant );	
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			
			return uneRes;
		}
		
		public static String getLocataire(int id){
			String requete="select NOML, PRENOML from LOCATAIRE where IDTIERS='" +id+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			String nomPrenom ="";
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					nomPrenom = unRes.getString("NOML")+" "+unRes.getString("PRENOML");
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return nomPrenom;
		}
		
		public static Agence getAgence(int id){
			Agence uneAgence =null;
			String requete="select * from AGENCE where IDAGENCE='" +id+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					String nom = unRes.getString("NOMAGENCE");
					uneAgence = new Agence(id, nom);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return uneAgence;
		}
		
		public static Saison getSaison(int id){
			Saison uneS =null;
			String requete="select * from SAISON where IDSAISON='" +id+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					String nom = unRes.getString("NOMS");
					Float red =unRes.getFloat("REDUCTION"); 
					uneS = new Saison(id, nom, red);
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return uneS;
		}
		
		public static Float getPrixAppart(int id){
			Float prix = (float) 0.0;
			String requete="select PRIX_BASE from APPARTEMENT where IDAPPARTEMENT='" +id+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				ResultSet unRes = unStat.executeQuery(requete);
				if(unRes.next()){
					 prix =unRes.getFloat("PRIX_BASE"); 
					
				}
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
			return prix;
		}
		
		
		
		public static void updateReservation(Reservation uneRreservation){
			String requete="update RESERVATION set IDAPPARTEMENT ='"+
					uneRreservation.getIdAppart()+"', IDSAISON = '" + uneRreservation.getIdSaison() +"', IDAGENCE = '" +
					uneRreservation.getIdAgence()+"', IDTIERS = '" +uneRreservation.getIdtiers()+"', ETAT = '" + 
					uneRreservation.getEtat() +"', DATERESERVATION = '" +uneRreservation.getDateRes()+"', DATEDEBUT ='" +
					uneRreservation.getDateDeb()+"', DATEFIN ='" + uneRreservation.getDateFin()+"', NBPERSONNES ='" +
					uneRreservation.getNbPersonnes()+"', MONTANT ='" + uneRreservation.getMontant()+"'where IDR = '"+uneRreservation.getId()+ "' ;";
			Bdd uneBdd = new Bdd("localhost","NeigeSoleil","root","root");
			try{
				uneBdd.seConnecter();
				Statement unStat = uneBdd.getMaConnexion().createStatement();
				unStat.execute(requete);
				unStat.close();
				uneBdd.seDeconnecter();
			}
			catch (SQLException exp){
				System.out.println("Erreur: "+requete);
			}
		}
		
	
		
}
