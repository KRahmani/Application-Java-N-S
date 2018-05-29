package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.synth.Region;

import controller.Appartement;
import controller.InfoVille;
import controller.Proprietaire;
import modele.Modele;


public class VueAppartement extends JPanel implements ActionListener{

	private static VueRegion uneRegion =null ;
	private static VueVille uneVille =null ;
	
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btModifier = new JButton("Modifier");
	private JButton btAjoutEquipement = new JButton("Ajout équipements");
	private JButton btNewVille = new JButton("Nouvelle ville");
	private JButton btNewRegion = new JButton("Nouvelle région");
	private JButton btphoto = new JButton("Choisir une photo");
	
	public static JComboBox<String> cbRegion = new JComboBox();
	public static JComboBox<String> cbVille = new JComboBox();
	
	private JTextField txtTypeAppart = new JTextField();
	private JTextField txtNumImmeuble = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCapaciteAccueil = new JTextField();
	private JTextField txtExposition = new JTextField();
	private JTextField txtPhoto = new JTextField();
	private JTextField txtPrixNuit = new JTextField();
	private JTextField txtDistancePiste = new JTextField();
	private JTextField txtSurface = new JTextField();
	
public VueAppartement(){
		
	this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 50, 1200, 550);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(8,6, 10, 40));//8 lignes et 6 colonnes
		
		unPanel.add(new JLabel("Région : "));
		unPanel.add(VueAppartement.cbRegion); 
		unPanel.add(this.btNewRegion);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel("Ville : "));
		unPanel.add(VueAppartement.cbVille);
		unPanel.add(this.btNewVille);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel("Num immeuble : "));
		unPanel.add(this.txtNumImmeuble);
		unPanel.add(new JLabel("Adresse : "));
		unPanel.add(this.txtAdresse);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
	
		
		unPanel.add(new JLabel("Type : "));
		unPanel.add(this.txtTypeAppart);
		unPanel.add(new JLabel("Surface : "));
		unPanel.add(this.txtSurface);
		unPanel.add(new JLabel("Capacité d'accueil : "));
		unPanel.add(this.txtCapaciteAccueil);
	
	
		unPanel.add(new JLabel("Exposition : "));
		unPanel.add(this.txtExposition);
		unPanel.add(new JLabel("Distance piste : "));
		unPanel.add(this.txtDistancePiste);
		unPanel.add(new JLabel("Prix par nuit : "));
		unPanel.add(this.txtPrixNuit);
	
		
		unPanel.add(this.btphoto);
		unPanel.add(this.txtPhoto);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(this.btAjouter);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(this.btModifier);
		unPanel.add(this.btAjoutEquipement);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
				

		
		this.btAjouter.addActionListener(this);
		this.btModifier.addActionListener(this);

		this.btAjoutEquipement.addActionListener(this);
		this.btphoto.addActionListener(this);

		this.btNewVille.addActionListener(this);
		this.btNewRegion.addActionListener(this);
		
		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
		btAjouter.setBackground(new Color(139, 169, 225));
		btModifier.setBackground(new Color(139, 169, 225));
	
		btAjoutEquipement.setBackground(new Color(139, 169, 225));
		btphoto.setBackground(new Color(139, 169, 225));
		
		btNewVille.setBackground(new Color(139, 169, 225));
		btNewRegion.setBackground(new Color(139, 169, 225));
		
		for (int i =0; i< unPanel.getComponentCount(); i++)
			{
			if (unPanel.getComponent(i) instanceof JLabel)
			{
				unPanel.getComponent(i).setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);	
			}
			else {
				unPanel.getComponent(i).setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				
			}
			unPanel.getComponent(i).setFont(uneFont);
			}
		
		this.add(unPanel);
		
		//remplir les comboBox ville et region
		actualiserCBXRegion();
		actualiserCBXVille();
		
		this.setVisible(false);
}


public void ajoutAppartement(){
	
	if (this.txtAdresse.getText().equals("") || this.txtTypeAppart.getText().equals("") 
			|| this.txtExposition.getText().equals("") || this.txtPhoto.getText().equals("")
			|| this.txtSurface.getText().equals("") 
			|| this.txtDistancePiste.getText().equals("")|| this.txtPrixNuit.getText().equals("")|| 
			this.txtNumImmeuble.getText().equals("")|| this.txtCapaciteAccueil.getText().equals("") 
			|| cbRegion.getSelectedItem().toString().equals("")|| this.txtPrixNuit.getText().equals("")|| 
			this.txtPrixNuit.getText().equals("")|| this.cbVille.getSelectedItem().toString().equals("")){
		JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
	}	
	//récupérer les valeurs des champs
	int idRegion, idVille, numImmeuble, capacite;
	float surface, distancePiste, prix;
	String adresse, type, exposition, lienPhoto;
	
	adresse = this.txtAdresse.getText();
	type = this.txtTypeAppart.getText();
	exposition = this.txtExposition.getText();
	lienPhoto = this.txtPhoto.getText();
	surface = Float.parseFloat(this.txtSurface.getText());
	distancePiste = Float.parseFloat(this.txtDistancePiste.getText());
	prix = Float.parseFloat(this.txtPrixNuit.getText());
	numImmeuble = Integer.parseInt(this.txtNumImmeuble.getText());
	capacite = Integer.parseInt(this.txtCapaciteAccueil.getText());
	idRegion = Modele.getIdRegion(cbRegion.getSelectedItem().toString());
	idVille =Modele.getIdVille(this.cbVille.getSelectedItem().toString());
	
	System.out.println(adresse +" "+ type +" "+ exposition +" "+ lienPhoto + " "+surface +" "+ distancePiste +" "+ prix +" "+ numImmeuble + " "+capacite + " "+idRegion +" "+ idVille);
	
	String cp = Modele.getCodePostalVille(this.cbVille.getSelectedItem().toString());
	
	Appartement unAppartement = new Appartement(0,idVille,idRegion,numImmeuble, capacite, adresse,
			 					cp, type, exposition, lienPhoto,  surface,  distancePiste,prix );
	Modele.insertAppartement(unAppartement);
	JOptionPane.showMessageDialog(this, "L'appartement a bien été ajouté!","Information", JOptionPane.INFORMATION_MESSAGE);	

}


public String ChoixPhoto(){	
	File fichier = null;
	JFileChooser dialogue = new JFileChooser();
	if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {	
		 fichier = dialogue.getSelectedFile();
	}
	this.txtPhoto.setText(fichier.getPath());
	return fichier.getPath();
}


public static VueRegion getUneRegion() {
	return uneRegion;
}


public static void setUneRegion(VueRegion uneRegion) {
	VueAppartement.uneRegion = uneRegion;
}



public static VueVille getUneVille() {
	return uneVille;
}


public static void setUneVille(VueVille uneVille) {
	VueAppartement.uneVille = uneVille;
}


public static void actualiserCBXRegion ()
{
	//remplir la combobox region avec toutes les regions de la table region
	
	 cbRegion.removeAllItems();
	ArrayList<controller.Region> lesRegions = Modele.getRegions();
			for(controller.Region uneR  : lesRegions){
				 cbRegion.addItem(uneR.getNom());
			}
}

public static void actualiserCBXVille ()
{
	 cbVille.removeAllItems();
		//remplir la combobox ville avec toutes les villes de la table info_ville
		ArrayList<InfoVille> lesVilles = Modele.getVilles();
		for(InfoVille uneVille : lesVilles){
			cbVille.addItem(uneVille.getNom());
		}
}

@Override
public void actionPerformed(ActionEvent e) {
	switch(e.getActionCommand()){
	case "Ajouter":
		ajoutAppartement();
		break;
	case "Nouvelle région":
		if (uneRegion == null) {
			
			uneRegion = new VueRegion(2); 
			
			}
		uneRegion.setVisible(true);
		 
		break;
	case "Modifier":
		
		break;
		
	case "Ajout équipements":
		VueEquipement unEquipement = new VueEquipement();
		break;
	case "Nouvelle ville":
		if (uneVille == null) {
			uneVille = new VueVille(); 
			}
		uneVille.setVisible(true);
		break;
	
		
	case "Choisir une photo":
		ChoixPhoto();
		break;
	
		
	}
	
}

}
