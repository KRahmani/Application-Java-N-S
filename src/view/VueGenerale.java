package view;

import view.VueAppartement;
import view.VueContrat;
import view.VueMateriel;
import view.VueProprietaire;
import view.VueResAppart;
import view.VueResMateriel;
import view.VueStatistiques;

import controller.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Main;

public class VueGenerale extends JFrame implements ActionListener{
	
	private JButton btQuitter = new JButton("Quitter");
	//la barre de menu
	private JMenuBar Menu = new JMenuBar();
	//les menus
	private JMenu gestionContrat = new JMenu("Gestion contrats");
	private JMenu gestionReservation = new JMenu("Gestion réservations");
	private JMenu autre = new JMenu("Autre");
	//les items du menu gestion contrat
	private JMenuItem proprietaire = new JMenuItem("Propriétaire");
	private JMenuItem appartement = new JMenuItem("Appartement");
	private JMenuItem contrat = new JMenuItem("Contrat");
	private JMenuItem materiel = new JMenuItem("Matériel");
	//les items du menu gestion réservation
	private JMenuItem resAppartement = new JMenuItem("Réservation appartement");
	private JMenuItem resMateriel = new JMenuItem("Réservation matériel");
	//l'item du menu autre
	private JMenuItem statistique = new JMenuItem("Statistiques");
	
	
	//création des panels
	private VueProprietaire uneVueProprietaire = new VueProprietaire();
	private VueAppartement uneVueAppartement = new VueAppartement();
	private VueContrat uneVueContrat = new VueContrat();
	private VueMateriel uneVueMateriel = new VueMateriel();
	private VueResAppart uneVueResAppart = new VueResAppart();
	private VueResMateriel uneVueResMateriel = new VueResMateriel();
	private VueStatistiques uneVueStatistiques = new VueStatistiques();
	
	
	public VueGenerale(){
		this.setTitle("Logiciel de gestion N&S");
		this.setLayout(null);
		this.setBounds(400,100,1300,800);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		this.getContentPane().setBackground(new Color(246, 249, 252));
		//placer le bouton quitter
		this.btQuitter.setBounds(590, 650, 120, 50);
		this.btQuitter.setBackground(new Color(139, 169, 225));
		this.add(this.btQuitter);
		this.btQuitter.addActionListener(this);
		
		ImageIcon logoPetit = new ImageIcon("src/images/logoNSPetit.jpg");
		this.setIconImage(logoPetit.getImage());
		
		Font uneFont = new Font("Times new roman", Font.BOLD, 24);
		
		//Menu.setBackground(new Color(139, 169, 225));
		//Menu.setFont(uneFont);
		
		//ajout des menus dans l'ordre
		Menu.add(gestionContrat);
		Menu.add(gestionReservation);
		Menu.add(autre);
		
		//ajout des listner sur les menus
		gestionContrat.addActionListener(this);		
		gestionReservation.addActionListener(this);
		autre.addActionListener(this);
		
		//ajout des menuItem aux menus
		gestionContrat.add(proprietaire);
		gestionContrat.add(appartement);
		gestionContrat.add(contrat);
		gestionContrat.add(materiel);
		gestionReservation.add(resAppartement);
		gestionReservation.add(resMateriel);
		autre.add(statistique);
		
		//rendre les menuitems cliquables
		proprietaire.addActionListener(this);
		appartement.addActionListener(this);
		contrat.addActionListener(this);
		materiel.addActionListener(this);
		resAppartement.addActionListener(this);
		resMateriel.addActionListener(this);
		statistique.addActionListener(this);
		
		//ajout des panels à la vue
		this.add(this.uneVueProprietaire);
		this.add(this.uneVueAppartement);
		this.add(this.uneVueMateriel);
		this.add(this.uneVueContrat);
		this.add(this.uneVueResAppart);
		this.add(this.uneVueResMateriel);
		this.add(this.uneVueStatistiques);
				
		
		//ajout du menubar à la fenetre
		setJMenuBar(Menu);
		//Changement de couleur du menubar et agrandissemnt de la police des menu et menuitem
		this.Menu.setFont(uneFont);
		this.Menu.setBackground(new Color(139, 169, 225));
		this.gestionContrat.setFont(uneFont);
		this.gestionReservation.setFont(uneFont);
		this.autre.setFont(uneFont);
		this.proprietaire.setFont(uneFont);
		this.appartement.setFont(uneFont);
		this.contrat.setFont(uneFont);
		this.materiel.setFont(uneFont);
		this.resAppartement.setFont(uneFont);
		this.resMateriel.setFont(uneFont);
		this.statistique.setFont(uneFont);
		
			
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "Quitter" : this.dispose();//tuer le processus de la fenetre
						Main.rendreVisible(true);
						break;
		case "Propriétaire":
			this.uneVueProprietaire.setVisible(true);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(false);
			break;
		case "Appartement":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(true);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(false);
			break;
		case "contrat":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(true);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(false);
			break;
		case "Matériel":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(true);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(false);
			break;
		case "statistiques":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(true);
			break;
		case "Réservation appartement":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(true);
			this.uneVueResMateriel.setVisible(false);
			this.uneVueStatistiques.setVisible(false);
			break;
		case "Réservation matériel":
			this.uneVueProprietaire.setVisible(false);
			this.uneVueAppartement.setVisible(false);
			this.uneVueMateriel.setVisible(false);
			this.uneVueContrat.setVisible(false);
			this.uneVueResAppart.setVisible(false);
			this.uneVueResMateriel.setVisible(true);
			this.uneVueStatistiques.setVisible(false);
			break;
			default:
				this.uneVueProprietaire.setVisible(false);
				this.uneVueAppartement.setVisible(false);
				this.uneVueMateriel.setVisible(false);
				this.uneVueContrat.setVisible(false);
				this.uneVueResAppart.setVisible(false);
				this.uneVueResMateriel.setVisible(false);
				this.uneVueStatistiques.setVisible(true);
			break;
		
		}
		
	}

}
