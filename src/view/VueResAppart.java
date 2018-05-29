package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import controller.Appartement;
import controller.DateLabelFormatter;
import controller.InfoVille;
import controller.Locataire;
import controller.Reservation;
import controller.Saison;
import modele.Modele;

public class VueResAppart extends JPanel implements ActionListener, ItemListener{
	
	
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btModifier = new JButton("Modifier");
	
	public static JComboBox<String> cbSaison = new JComboBox();
	public  static JComboBox<String> cbLocataire = new JComboBox();
	public  JComboBox<String> cbEtat = new JComboBox();
	public static JComboBox<String> cbAgence = new JComboBox();
	public static JComboBox<String> cbReservations = new JComboBox();
	
	private JTextField txtAppart = new JTextField();
	private JTextField txtNbPersonnes = new JTextField();
	private JTextField txtMontantTotal = new JTextField();
	//création des trois calendriers pour les dates de res, de debut et de fin 
	
	private Properties p = new Properties();//pour définir la structure de la date
	
	private SqlDateModel model1 = new SqlDateModel();
	private JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p);
	private JDatePickerImpl dateRes = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
	
	private SqlDateModel model2 = new SqlDateModel();
	private JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p);
	private JDatePickerImpl dateDebut = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
	 
	private SqlDateModel model3 = new SqlDateModel();
	private JDatePanelImpl datePanel3 = new JDatePanelImpl(model3,p);
	private JDatePickerImpl dateFin = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
	
	public VueResAppart(){
		this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 50, 1200, 550);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(7,4, 10, 40));
		
		this.p.put("text.today", "Today");
		this.p.put("text.month", "Month");
		this.p.put("text.year", "Year");
		
		unPanel.add(new JLabel("Les réservations : "));
		unPanel.add(VueResAppart.cbReservations); 
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel("Agence : "));
		unPanel.add(this.cbAgence); 
		unPanel.add(new JLabel("Réf appartement : "));
		unPanel.add(this.txtAppart);
		
		unPanel.add(new JLabel("Locataire : "));
		unPanel.add(this.cbLocataire);
		unPanel.add(new JLabel("Date res : "));
		unPanel.add(dateRes);
		
		unPanel.add(new JLabel("Date début : "));
		unPanel.add(dateDebut);
		unPanel.add(new JLabel("Date fin : "));
		unPanel.add(dateFin);
		
		unPanel.add(new JLabel("Etat réservation : "));
		unPanel.add(this.cbEtat);
		unPanel.add(new JLabel("Saison : "));
		unPanel.add(this.cbSaison);
	
		
		unPanel.add(new JLabel("NB Personnes : "));
		unPanel.add(this.txtNbPersonnes);
		unPanel.add(new JLabel("Prix/nuit : "));
		unPanel.add(this.txtMontantTotal);
		
	
		unPanel.add(this.btAjouter);
		unPanel.add(this.btModifier);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
	
		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
		btAjouter.setBackground(new Color(139, 169, 225));
		btModifier.setBackground(new Color(139, 169, 225));
		
		this.btAjouter.addActionListener(this);
		this.btModifier.addActionListener(this);
		VueResAppart.cbReservations.addItemListener(this);
		

	
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
		
		//remplir combo ETAT
		this.cbEtat.addItem("terminee");
		this.cbEtat.addItem("en attente");
		this.cbEtat.addItem("annulee");
		
		//remplir combo LOCATAIRE
		remplirCBXLocataire();
		//remplir combo Agence
		remplirCBXAgence();
		remplirCBXSaison();
		
		//remplir la combo Reservation
		actualiserCBXeservations();
		
	this.add(unPanel);
		
	this.setVisible(false);
		
	}
	
	public static void remplirCBXAgence ()
	{
		 cbAgence.removeAllItems();
		ArrayList<controller.Agence> lesAgences = Modele.getLesagences();
				for(controller.Agence uneA  : lesAgences){
					 cbAgence.addItem(uneA.getId()+" - "+uneA.getNom());
				}
	}

	public static void remplirCBXLocataire ()
	{
			ArrayList<Locataire> lesLocataires = Modele.getLocataire();
			for(Locataire unLocataire : lesLocataires){
				cbLocataire.addItem(unLocataire.getId()+" - "+unLocataire.getNoml()+" "+unLocataire.getPrenoml());
			}
	}
	
	public static void remplirCBXSaison ()
	{
			ArrayList<Saison> lesSaisons = Modele.getLessaisons();
			for(Saison uneSaison : lesSaisons){
				cbSaison.addItem(uneSaison.getId()+" - "+uneSaison.getNom());
			}
	}
	
	public static void actualiserCBXeservations ()
	{
		//remplir la combobox region avec toutes les regions de la table region
		
		 cbReservations.removeAllItems();
		 cbReservations.addItem("Choisissez une réservation ");
		ArrayList<controller.Reservation> lesReservations = Modele.getReservations();
				for(controller.Reservation uneR  : lesReservations){
					 cbReservations.addItem(uneR.getId()+" - "+"(Locataire "+Modele.getLocataire(uneR.getIdtiers())+")");
				}
	}
	
	

	
	public void ajoutReservation(){
		
		java.sql.Date selectedDateRes = (java.sql.Date) this.dateRes.getModel().getValue();
		java.sql.Date selectedDateDeb = (java.sql.Date) this.dateDebut.getModel().getValue();
		java.sql.Date selectedDateFin = (java.sql.Date) this.dateFin.getModel().getValue();
	
		
		if (this.txtAppart.getText().equals("") || this.txtNbPersonnes.getText().equals("") 
				|| this.txtMontantTotal.getText().equals("") || selectedDateRes.toString().equals("")
				||  selectedDateDeb.toString().equals("") 
				||  selectedDateFin.toString().equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
		}	
		
		//récupérer les valeurs des champs
		int idAppart, idSaison, idAgence, idtiers,nbPersonnes;
		float montant;
		String etat;
	
		idAppart = Integer.parseInt(this.txtAppart.getText().toString());
		idSaison = Integer.parseInt(cbSaison.getSelectedItem().toString().substring(0, cbSaison.getSelectedItem().toString().indexOf(" - ")));
		idAgence = Integer.parseInt(cbAgence.getSelectedItem().toString().substring(0, cbAgence.getSelectedItem().toString().indexOf(" - ")));
		idtiers = Integer.parseInt(cbLocataire.getSelectedItem().toString().substring(0, cbLocataire.getSelectedItem().toString().indexOf(" - ")));
		nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText().toString());
		montant = Float.parseFloat(this.txtMontantTotal.getText().toString());
		etat = cbEtat.getSelectedItem().toString();

		Reservation uneReservation = new Reservation(0,idAppart,idSaison,idAgence, idtiers, etat,
				selectedDateRes, selectedDateDeb, selectedDateFin, nbPersonnes,  montant);
		Modele.insertReservation(uneReservation);
		JOptionPane.showMessageDialog(this, "La réservation a bien été ajoutée!","Information", JOptionPane.INFORMATION_MESSAGE);	
	

	}

public void ModifRes(){
		
		java.sql.Date selectedDateRes = (java.sql.Date) this.dateRes.getModel().getValue();
		java.sql.Date selectedDateDeb = (java.sql.Date) this.dateDebut.getModel().getValue();
		java.sql.Date selectedDateFin = (java.sql.Date) this.dateFin.getModel().getValue();
	
		System.out.println(String.valueOf(selectedDateRes));
		
		if (this.txtAppart.getText().equals("") || this.txtNbPersonnes.getText().equals("") 
				|| this.txtMontantTotal.getText().equals("") || selectedDateRes.toString().equals("")
				||  selectedDateDeb.toString().equals("") 
				||  selectedDateFin.toString().equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
		}	
		
		//récupérer les valeurs des champs
		int idAppart, idSaison, idAgence, idtiers,nbPersonnes;
		float montant;
		String etat;
	
		idAppart = Integer.parseInt(this.txtAppart.getText().toString());
		idSaison = Integer.parseInt(cbSaison.getSelectedItem().toString().substring(0, cbSaison.getSelectedItem().toString().indexOf(" - ")));
		idAgence = Integer.parseInt(cbAgence.getSelectedItem().toString().substring(0, cbAgence.getSelectedItem().toString().indexOf(" - ")));
		idtiers = Integer.parseInt(cbLocataire.getSelectedItem().toString().substring(0, cbLocataire.getSelectedItem().toString().indexOf(" - ")));
		nbPersonnes = Integer.parseInt(this.txtNbPersonnes.getText().toString());
		montant = Float.parseFloat(this.txtMontantTotal.getText().toString());
		etat = cbEtat.getSelectedItem().toString();

		Reservation uneReservation = new Reservation(0,idAppart,idSaison,idAgence, idtiers, etat,
				selectedDateRes, selectedDateDeb, selectedDateFin, nbPersonnes,  montant);
		Modele.updateReservation(uneReservation);
		JOptionPane.showMessageDialog(this, "La réservation a bien été modifiée!","Information", JOptionPane.INFORMATION_MESSAGE);	
	

	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "Ajouter"){
			ajoutReservation();
			//actualiser la combobox reservation en ajoutant la réservation actuelle
			actualiserCBXeservations ();
		}
		else{
			//modification avec le bouton modifier
			ModifRes();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	
		String selection;
		int idRes;

		Object select = e.getItem();
		selection = (String)select;
		if(e.getItem().toString() != "Choisissez une réservation "){
			System.out.println(selection);
			idRes = Integer.parseInt(selection.substring(0, selection.indexOf(" - ")));	
			
			System.out.println(String.valueOf(idRes));
			Reservation uneRes = Modele.getReservation(idRes);
			this.cbAgence.setSelectedItem(uneRes.getIdAgence()+" - "+Modele.getAgence(uneRes.getIdAgence()).getNom());
			this.cbSaison.setSelectedItem(uneRes.getIdSaison()+" - "+Modele.getSaison(uneRes.getIdSaison()));
			this.cbLocataire.setSelectedItem(uneRes.getIdtiers()+" - "+Modele.getLocataire(uneRes.getIdtiers()));
		
			this.txtAppart.setText(String.valueOf(uneRes.getIdAppart()));
			this.txtNbPersonnes.setText(String.valueOf(uneRes.getNbPersonnes()));
			this.txtMontantTotal.setText(String.valueOf(Modele.getPrixAppart(uneRes.getIdAppart())));
			this.cbEtat.setSelectedItem(uneRes.getEtat());
			
			// System.out.println(String.valueOf(dateT));
		
			this.model1.setDate(uneRes.getDateRes().getYear(), uneRes.getDateRes().getMonth(),uneRes.getDateRes().getDay());
			//System.out.println(String.valueOf(date.getYear()));
			model1.setSelected(true);
			this.model2.setDate(uneRes.getDateDeb().getYear(), uneRes.getDateDeb().getMonth(), uneRes.getDateDeb().getDay());
			model2.setSelected(true);
			this.model3.setDate(uneRes.getDateFin().getYear(), uneRes.getDateFin().getMonth(), uneRes.getDateFin().getDay());
			model3.setSelected(true);
		}
		
		
	}



}
