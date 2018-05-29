DROP DATABASE IF EXISTS NeigeSoleil;

CREATE DATABASE IF NOT EXISTS NeigeSoleil;
USE NeigeSoleil;
# -----------------------------------------------------------------------------
#       TABLE : USER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS USER
 (
   LOGIN VARCHAR(50),  
   MDP VARCHAR(100) NOT NULL  ,
   droits enum ("admin","user","autre") 
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : REGION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS REGION
 (
   IDREGION INTEGER(6) NOT NULL AUTO_INCREMENT ,
   NOMR VARCHAR(50) NULL  
   , PRIMARY KEY (IDREGION) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : SAISON
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SAISON
 (
   IDSAISON INTEGER(6) NOT NULL AUTO_INCREMENT ,
   NOMS VARCHAR(50) NULL  ,
   PERIODES VARCHAR(50) NULL  ,
   REDUCTION DECIMAL(4,2) NULL  
   , PRIMARY KEY (IDSAISON) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : MATERIEL
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS MATERIEL
 (
   IDMATERIEL INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDTIERS INTEGER(6) NOT NULL  ,
   TYPEM VARCHAR(50) NULL  ,
   ETAT VARCHAR(30) NULL  ,
   PRIX DECIMAL(10,2) NULL  ,
   IMAGE VARCHAR(50) NULL  
   , PRIMARY KEY (IDMATERIEL) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE MATERIEL
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_MATERIEL_PROPRIETAIRE
     ON MATERIEL (IDTIERS ASC);

# -----------------------------------------------------------------------------
#       TABLE : TIERS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS TIERS
 (
   IDTIERS INTEGER(6) NOT NULL 
   , PRIMARY KEY (IDTIERS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PROPRIETAIRE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PROPRIETAIRE
 (
   IDTIERS INTEGER(6) NOT NULL  ,
   CIVILITE VARCHAR(15) NULL  ,
   NOMP VARCHAR(50) NULL  ,
   PRENOMP VARCHAR(50) NULL  ,
   ADRESSEP VARCHAR(100) NULL  ,
   CODEPOSTAL CHAR(5) NULL  ,
   VILLE VARCHAR(50) NULL  ,
   TEL VARCHAR(20) NULL  ,
   RIB VARCHAR(50) NULL  ,
   EMAIL VARCHAR(128) NULL  ,
   MOT_PASSE VARCHAR(30) NULL  
   , PRIMARY KEY (IDTIERS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : INFO_VILLE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS INFO_VILLE
 (
   IDVILLE INTEGER(6) NOT NULL AUTO_INCREMENT ,
   NOMV VARCHAR(50) NULL  ,
   REGIONV VARCHAR(128) NULL  ,
   CODEPOSTAL CHAR(5) NULL  ,
   TEL_AGENCE_TOURISME VARCHAR(20) NULL  ,
   TEL_COMISSARIAT VARCHAR(20) NULL  ,
   TEL_MAIRIE VARCHAR(20) NULL  ,
   TEL_MEDECIN INTEGER(20) NULL  ,
   TEL_PHARMACIE VARCHAR(20) NULL  
   , PRIMARY KEY (IDVILLE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : APPARTEMENT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS APPARTEMENT
 (
   IDAPPARTEMENT INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDVILLE INTEGER(6) NOT NULL  ,
   IDREGION INTEGER(6) NOT NULL  ,
   NUM_IMMEUBLE VARCHAR(50) NULL  ,
   ADRESSEAPP VARCHAR(100) NULL  ,
   CODEPOSTAL CHAR(5) NULL  ,
   TYPEAPPART CHAR(5) NULL  ,
   SURFACE DECIMAL(6,2) NULL  ,
   EXPOSITION VARCHAR(20) NULL  ,
   CAPACITE_ACCUEIL INTEGER(4) NULL  ,
   DISTANCE_PISTE DECIMAL(10,2) NULL  ,
   PRIX_BASE DECIMAL(6,2) NULL  ,
   LIENPHOTO VARCHAR(128) NULL  
   , PRIMARY KEY (IDAPPARTEMENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE APPARTEMENT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_APPARTEMENT_INFO_VILLE
     ON APPARTEMENT (IDVILLE ASC);

# -----------------------------------------------------------------------------
#       TABLE : RESERVATION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS RESERVATION
 (
   IDR INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDAPPARTEMENT INTEGER(6) NULL  ,
   IDSAISON INTEGER(6) NOT NULL  ,
   IDAGENCE INTEGER(6) NOT NULL  ,
   IDTIERS INTEGER(6) NOT NULL  ,
   ETAT VARCHAR(20) NULL  ,
   DATERESERVATION DATE NULL  ,
   DATEDEBUT DATE NULL  ,
   DATEFIN DATE NULL  ,
   NBPERSONNES INTEGER(3) NULL  
   , PRIMARY KEY (IDR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE RESERVATION
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_RESERVATION_APPARTEMENT
     ON RESERVATION (IDAPPARTEMENT ASC);

CREATE  INDEX I_FK_RESERVATION_SAISON
     ON RESERVATION (IDSAISON ASC);

CREATE  INDEX I_FK_RESERVATION_LOCATAIRE
     ON RESERVATION (IDTIERS ASC);

CREATE  INDEX I_FK_RESERVATION_AGENCE
     ON RESERVATION (IDAGENCE ASC);

# -----------------------------------------------------------------------------
#       TABLE : CONTRAT_GESTION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CONTRAT_GESTION
 (
   IDC INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDAGENCE INTEGER(6) NOT NULL  ,
   IDAPPARTEMENT INTEGER(6) NOT NULL  ,
   IDTIERS INTEGER(6) NOT NULL  ,
   ETAT VARCHAR(20) NULL  ,
   DATESIGNATUREC DATE NULL  ,
   DATEDEBUT DATE NULL  ,
   DATEFIN DATE NULL  
   , PRIMARY KEY (IDC) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE CONTRAT_GESTION
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_CONTRAT_GESTION_PROPRIETAIRE
     ON CONTRAT_GESTION (IDTIERS ASC);

CREATE  INDEX I_FK_CONTRAT_GESTION_AGENCE
     ON CONTRAT_GESTION (IDAGENCE ASC);

CREATE  INDEX I_FK_CONTRAT_GESTION_APPARTEMENT
     ON CONTRAT_GESTION (IDAPPARTEMENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : EQUIPEMENT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS EQUIPEMENT
 (
   IDEQUIPEMENT INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDAPPARTEMENT INTEGER(6) NOT NULL  ,
   NOME VARCHAR(50) NULL  ,
   NOMBREE INTEGER(3) NULL  
   , PRIMARY KEY (IDEQUIPEMENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE EQUIPEMENT
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_EQUIPEMENT_APPARTEMENT
     ON EQUIPEMENT (IDAPPARTEMENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : AGENCE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS AGENCE
 (
   IDAGENCE INTEGER(6) NOT NULL AUTO_INCREMENT ,
   IDREGION INTEGER(6) NOT NULL  ,
   NOMAGENCE VARCHAR(50) NULL  ,
   ADRESSEA VARCHAR(100) NULL  ,
   VILLEA VARCHAR(50) NULL  ,
   CODEPOSTAL CHAR(5) NULL  ,
   TEL VARCHAR(20) NULL  
   , PRIMARY KEY (IDAGENCE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE AGENCE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_AGENCE_REGION
     ON AGENCE (IDREGION ASC);

# -----------------------------------------------------------------------------
#       TABLE : LOCATAIRE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LOCATAIRE
 (
   IDTIERS INTEGER(6) NOT NULL  ,
   CIVILITE VARCHAR(15) NULL  ,
   NOML VARCHAR(50) NULL  ,
   PRENOML VARCHAR(50) NULL  ,
   ADRESSEL VARCHAR(100) NULL  ,
   CODEPOSTAL CHAR(5) NULL  ,
   VILLE VARCHAR(50) NULL  ,
   TEL VARCHAR(20) NULL  ,
   EMAIL VARCHAR(128) NULL  ,
   NBRESERVATION INTEGER(2) NULL  ,
   MOT_PASSE VARCHAR(30) NULL  
   , PRIMARY KEY (IDTIERS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : LOUER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LOUER
 (
   IDMATERIEL INTEGER(6) NOT NULL  ,
   IDR INTEGER(6) NOT NULL  ,
   QUANTITE INTEGER(3) NULL  
   , PRIMARY KEY (IDMATERIEL,IDR) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LOUER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LOUER_MATERIEL
     ON LOUER (IDMATERIEL ASC);

CREATE  INDEX I_FK_LOUER_RESERVATION
     ON LOUER (IDR ASC);

# -----------------------------------------------------------------------------
#       TABLE : H_CONTRAT_GESTION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS H_CONTRAT_GESTION
 (
   IDC INTEGER(6) NOT NULL  ,
   DATE_HISTO DATE NOT NULL  ,
   ETAT VARCHAR(20) NULL  ,
   DATESIGNATUREC DATE NULL  ,
   DATEDEBUT DATE NULL  ,
   DATEFIN DATE NULL  
   , PRIMARY KEY (IDC,DATE_HISTO) 
 ) 
 comment = "Table d'historisation des modifications de la table CONTRAT_GESTION";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE H_CONTRAT_GESTION
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_H_CONTRAT_GESTION_CONTRAT_GESTION
     ON H_CONTRAT_GESTION (IDC ASC);
	 


	 
	 
# -----------------------------------------------------------------------------
#       TABLE : ArchiveReservations #--- une table qui contient toutes les reservations términées
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ArchiveReservations
As select * from reservation where 2 = 0;


# -----------------------------------------------------------------------------
#       TABLE : ArchivePropInactif #--une table qui contient tous les propriétaires ayant des contrats de gestion inactifs
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ArchivePropInactif
As select * from proprietaire where 2 = 0;


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE MATERIEL 
  ADD FOREIGN KEY FK_MATERIEL_PROPRIETAIRE (IDTIERS)
      REFERENCES PROPRIETAIRE (IDTIERS) ;


ALTER TABLE PROPRIETAIRE 
  ADD FOREIGN KEY FK_PROPRIETAIRE_TIERS (IDTIERS)
      REFERENCES TIERS (IDTIERS) ;


ALTER TABLE APPARTEMENT 
  ADD FOREIGN KEY FK_APPARTEMENT_INFO_VILLE (IDVILLE)
      REFERENCES INFO_VILLE (IDVILLE) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_APPARTEMENT (IDAPPARTEMENT)
      REFERENCES APPARTEMENT (IDAPPARTEMENT) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_SAISON (IDSAISON)
      REFERENCES SAISON (IDSAISON) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_LOCATAIRE (IDTIERS)
      REFERENCES LOCATAIRE (IDTIERS) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_AGENCE (IDAGENCE)
      REFERENCES AGENCE (IDAGENCE) ;


ALTER TABLE CONTRAT_GESTION 
  ADD FOREIGN KEY FK_CONTRAT_GESTION_PROPRIETAIRE (IDTIERS)
      REFERENCES PROPRIETAIRE (IDTIERS) ;


ALTER TABLE CONTRAT_GESTION 
  ADD FOREIGN KEY FK_CONTRAT_GESTION_AGENCE (IDAGENCE)
      REFERENCES AGENCE (IDAGENCE) ;


ALTER TABLE CONTRAT_GESTION 
  ADD FOREIGN KEY FK_CONTRAT_GESTION_APPARTEMENT (IDAPPARTEMENT)
      REFERENCES APPARTEMENT (IDAPPARTEMENT) ;


ALTER TABLE EQUIPEMENT 
  ADD FOREIGN KEY FK_EQUIPEMENT_APPARTEMENT (IDAPPARTEMENT)
      REFERENCES APPARTEMENT (IDAPPARTEMENT) ;


ALTER TABLE AGENCE 
  ADD FOREIGN KEY FK_AGENCE_REGION (IDREGION)
      REFERENCES REGION (IDREGION) ;


ALTER TABLE LOCATAIRE 
  ADD FOREIGN KEY FK_LOCATAIRE_TIERS (IDTIERS)
      REFERENCES TIERS (IDTIERS) ;


ALTER TABLE LOUER 
  ADD FOREIGN KEY FK_LOUER_MATERIEL (IDMATERIEL)
      REFERENCES MATERIEL (IDMATERIEL) ;


ALTER TABLE LOUER 
  ADD FOREIGN KEY FK_LOUER_RESERVATION (IDR)
      REFERENCES RESERVATION (IDR) ;


ALTER TABLE H_CONTRAT_GESTION 
  ADD FOREIGN KEY FK_H_CONTRAT_GESTION_CONTRAT_GESTION (IDC)
      REFERENCES CONTRAT_GESTION (IDC) ;

	  
	  
#-------------------------LES TRIGGERS-------------------------------------------	  

#------- pour que quand on insère dans proprietaire ça s'insère dans tiers
Drop  trigger if exists insertProp;
delimiter //

CREATE trigger insertProp 
after insert on Proprietaire
for each row
 begin
	Declare nbP int;
	select count(*) into nbP from Tiers where idtiers = new.idtiers;
	if nbP = 0 then
		insert into tiers values (new.idtiers);
	end if;
end//
delimiter ;

#------- pour que quand on insère dans locataire ça s'insère dans tiers
Drop  trigger if exists insertLoc;
delimiter //

CREATE trigger insertLoc
after insert on Locataire
for each row
 begin
	Declare nbL int;
	select count(*) into nbL from Tiers where idtiers = new.idtiers;
	if nbL = 0 then
		insert into tiers values (new.idtiers);
	end if;
end//
delimiter ; 


#------- pour que quand on SUPPRIME dans proprietaire ça supprime dans tiers
Drop  trigger if exists supprimerProp;
delimiter //

CREATE trigger supprimerProp 
after delete on Proprietaire
for each row
 begin
	delete from tiers where IDTIERS = old.IDTIERS;
end//
delimiter ; 

#------- pour que quand on supprime dans locataire ça supprime dans tiers
Drop  trigger if exists supprimerLoc;
delimiter //
CREATE trigger supprimerLoc 
after delete on LOCATAIRE
for each row
 begin
	delete from tiers where IDTIERS = old.IDTIERS;
end//
delimiter ; 


#------- pour que quand on insert dans reservation on va inserer dans archiveReservation toutes les reservations ayant un état términé
Drop  trigger if exists inserArchivRes;
delimiter //
CREATE trigger inserArchivRes
after insert on RESERVATION
for each row
 begin
	
	insert into ArchiveReservations select * from reservation where etat = 'terminee';
	#--delete from reservation where etat = 'terminee';
end//
delimiter ; 


Drop  trigger if exists ModifEtat;


#------- pour que quand on supprime dans reservation on vérifie si l'état est # de terminee on affiche impossible sinon on insère la ligne supprimée dans archiveReservation
Drop  trigger if exists supprimerRes;
delimiter //
CREATE trigger supprimerRes 
before delete on reservation
for each row
 begin
	declare nb int;
	if old.etat != 'terminee'
	then
		SIGNAL SQLState '42000'
		Set message_text  = 'impossible, vous ne pouvez pas supprimer une réservation non terminée' ;
	else 
		select count(idR) into nb from archiveReservations where idR = old.idR; #-- je vérifie d'abord si cette reservation n'existe pas dans archivereservations
		if nb = 0
		then
			insert into archiveReservations select * from reservation where idR = old.idR;
		end if;
	end if;
end//
delimiter ; 

#------- pour que quand on modéfie une reservation on vérifie si l'état est = terminee on l'insère dans la table archiveReservation
Drop  trigger if exists modifRes;
delimiter //
CREATE trigger modifRes 
after update on reservation
for each row
 begin
	if new.etat = 'terminee'
	then
		insert into archiveReservations values
		(new.idR, new.IDAPPARTEMENT, new.IDSAISON,new.IDAGENCE,new.IDTIERS,new.ETAT,new.DATERESERVATION,new.DATEDEBUT,new.DATEFIN,new.NBPERSONNES);
	end if;
end//
delimiter ; 

#------- pour incrémenter nbReservation de la table locataire à chaque insertion d'une reservation terminée
Drop  trigger if exists IncNbReserLoc;
delimiter //
CREATE trigger IncNbReserLoc 
after insert on reservation
for each row
 begin
	if new.etat = 'terminee'
	then
		update locataire set nbReservation = nbReservation + 1 WHERE new.idtiers = idtiers;
	end if;
end//
delimiter ; 


#------- pour verifier avant d'insérer dans la table louer que le locataire a déja loué un appartement ie qu'il a une reservation terminee( s'il n'a pas loué un appartement il ne pourra pas louer du materiel)
Drop  trigger if exists verifLocMateriel;
delimiter //
CREATE trigger verifLocMateriel 
before insert on louer
for each row
 begin
	declare etatRes varchar(20);
	select ETAT into etatRes from reservation where idR = new.idR;
	if etatRes != 'terminee' 
	then
		SIGNAL SQLState '42000'
		Set message_text  = 'impossible, il faut que la reservation soit terminee' ;
	end if;
end//
delimiter ; 


#-- un event qui supprime toute les minutes les reservations dont l'état est terminee
create event suppRes
on schedule every 1 minute
do delete from reservation where etat = 'terminee';
#---pour désactiver l'event : set global event_scheduler = 0; pour le réactiver on met = 1 non pas 0
 

#-----------------------LES VIEWS----------------------------



#------------une view pour le nombre de clients par année pour chaque région :--------------------------------------------------
CREATE VIEW VUENBLOCATAIRE(ANNEE, REGION,NBLOCATAIRE )
AS SELECT DISTINCT year(AR.DATERESERVATION),  RE.NOMR ,COUNT(distinct AR.IDTIERS)
FROM ARCHIVERESERVATIONS AR
LEFT JOIN APPARTEMENT A ON AR.IDAPPARTEMENT = A.IDAPPARTEMENT
LEFT JOIN REGION RE ON A.IDREGION = RE.IDREGION
GROUP BY year(AR.DATERESERVATION) , RE.NOMR ;



#------------une view pour le nombre du materiels loué chaque mois  :--------------------------------------------------
CREATE VIEW VUENBMATERIEL(MOIS, NBMATERIEL)
AS SELECT distinct month(R.DATERESERVATION) ,COUNT(distinct L.IDMATERIEL) 
FROM RESERVATION R, louer L
where L.idR = R.idR
GROUP BY month(R.DATERESERVATION) ;



#------------une view pour le contrat de gestion signés par chaque propriétaire chaque année :--------------------------- 
CREATE VIEW VUENBCONTRAT_GEST(ANNEE, PROPRIETAIRE, NBCONTRAT)
AS SELECT year(C.DATESIGNATUREC) , P.NOMP, COUNT(C.IDC)
FROM CONTRAT_GESTION C
	LEFT JOIN PROPRIETAIRE P ON C.IDTIERS = P.IDTIERS
GROUP BY year(C.DATESIGNATUREC) , P.NOMP;


#------- une view pour le nombre de reservation par année pour chaque appartement d'un propriétaire
CREATE VIEW VUENBRESAPPART(ANNEE, PROPRIETAIRE,APPARTEMENT, NBRESERVATIONS )
AS SELECT DISTINCT year(AR.DATERESERVATION),  CG.IDTIERS, A.IDAPPARTEMENT ,COUNT(AR.idR)
FROM ARCHIVERESERVATIONS AR
LEFT JOIN APPARTEMENT A ON AR.IDAPPARTEMENT = A.IDAPPARTEMENT
LEFT JOIN CONTRAT_GESTION CG ON A.IDAPPARTEMENT = CG.IDAPPARTEMENT
GROUP BY year(AR.DATERESERVATION) ,  CG.IDTIERS, A.IDAPPARTEMENT ;



	  
#----- les insertions ----------------------------------------------------------------------------------------------------------

#----------- table region -----------------
insert into region values(null,'Alpes');
insert into region values(null,'nouvelle-aquitaine');
insert into region values(null,'les pyrénées');
insert into region values(null,'les vosges');


#----------- table saison -----------------
 insert into saison values(null,'Basse','',2.0);
 insert into saison values(null,'Moyenne','',10.0);
 insert into saison values(null,'Haute','',20.0);
   
   
#----------- table materiel -----------------   
insert into materiel values(null,2,'ski de piste Pack atris','excellent',30.00,'atris');
insert into materiel values(null,2,'Guardian mnc 13','bon',60.00,'Guardianmnc13');
insert into materiel values(null,4,'Baton de ski Kaloo jr','bon',84.00,'BatondeskiKaloo');
insert into materiel values(null,3,'chaussure de ski rossignol','très bon',56.00,'rossignol');
insert into materiel values(null,4,'patinette de ski', 'bon',72.00,'patinette');

#----------- table proprietaire -----------------
 insert into proprietaire values(1,'Monsieur','louguillau','jean-louis','2 rue maréchal joffre','64000','pau','0605347822','FR3330002005500000157841Z25','jl@gmail.com','jl2018');
 insert into proprietaire values(2,'Madame','benhammed','louise','14 rue François de Sourdis','33000','bordeau','07653476292','FR3330004005500000187841R45','louise@gmail.com','louise2018');
 insert into proprietaire values(3,'Monsieur','ly','antoine','40 quai Bons Enfants','88026','EPINAL','0756432895','FR4530004005500000188541T29','antoine@gmail.com','antoine2018');
 insert into proprietaire values(4,'Monsieur','Roux','Dominique','place Charles de Gaulle','65000','TARBES','0685987256','FR4530004005500000188541J89','dominique@gmail.com','dominique2018');


 #----------- table info_ville ----------------- 
 insert into info_ville values(null,'Vars','Alpes','05560','0123456867','0167543987','0165487645','0187654398','0187654098');
 insert into info_ville values(null,'Pau','Nouvelle-aquitaine','64000','0123457667','0167598987','0195487645','0187654048','0187650298');
 insert into info_ville values(null,'Saint-Lary-Soulan','Les pyrénées','65170','0413456867','0167983987','0166453645','0197654398','0187698798');
 insert into info_ville values(null,'Gérardmer','Les vosges','88400','0123498767','0198543987','0165456745','0198754398','0105694098');
 
 
 #----------- table appartement -----------------
 insert into appartement values(null,1,1,null,'Cours Fontanarosa','05560','F3',50.3,'sud est',6,400,1200,'appartVar');
 insert into appartement values(null,2,2,null,'place royale','64000','F2',37.8,'nord ouest',2,400,850,'appartPau');
 insert into appartement values(null,3,3,null,'place de la mairie','65170','F4',60.4,'sud ouest',8,400,1500,'appartSaintLary');
 insert into appartement values(null,4,4,47,'Rue Charles de Gaulle','88400 ','F6',80.0,'sud est',12,400,1600,'appartGérardmer');
 
 
  #----------- table CONTRAT_GESTION -----------------
 insert into CONTRAT_GESTION values(null,1,1,1,'actif','2017-09-10','2018-11-01','2018-08-01');
 insert into CONTRAT_GESTION values(null,2,2,2,'actif','2017-09-15','2018-11-30','2018-08-01');

 
 
 
 #----------- table reservation -----------------
 insert into reservation values(null,1,1,1,5,'terminee','2017-10-24','2018-01-03','2018-01-10',6);
 insert into reservation values(null,2,1,2,6,'en attente','2017-10-28','2018-02-11','2018-01-18',2);
 insert into reservation values(null,1,1,1,7,'terminee','2017-12-11','2018-03-11','2018-03-18',6);
 insert into reservation values(null,3,1,2,2,'terminee','2017-12-24','2018-02-11','2018-01-18',5);
  
  
 #--------------table louer --------------------------------------------
 insert into louer values (1, 2, 3);
 insert into louer values (4, 4, 3);
  
 
 #----------- table equipement -----------------
 insert into equipement values(null,1,'machine à laver',1);
 insert into equipement values(null,4,'télévision',2);
 insert into equipement values(null,2,'assiètes',10);
 insert into equipement values(null,3,'cuillières à soupe',15);
 
 
 #----------- table agence -----------------
 insert into agence values(null,1,'N&S Alpes', '12 Rue de la Poste','Annecy', 74000,'0450450456');
 insert into agence values(null,2,'N&S Nouvelle-aquitaine', ' Lotissement Gaussens,Centre Routier','Agen Le Passage', 47520 ,'0553963635');
 
 
 #----------- table locataire -----------------
 insert into locataire values(5,'Monsieur','robert', 'martin','35 rue leon geffroy','94600','vitry sur seine','0786543823','martin76@outlook.fr',0,'martin123');
 insert into locataire values(6,'Monsier','Richard', 'Dupond','25 rue des fleurs','33500','Libourne','0665487329','Dupond1@outlook.fr',0,'Dupond123');
 insert into locataire values(7,'madame','Bernard', 'Rose','40 rue de l église','33380','Mios','0786549361','rose@outlook.fr',0,'rose123');
 insert into locataire values(8,'madame','Dubois', 'Valery','35 rue de paris','75019','Paris','0645389724','valery6@outlook.fr',0,'valery123');
 
 
 #----------- table user -----------------
 insert into user values('admin', '123','admin'); 

 
 
