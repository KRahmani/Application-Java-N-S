package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controller.Appartement;
import controller.DateLabelFormatter;
import modele.Modele;


public class VueContrat extends JPanel implements ActionListener{
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btModifier = new JButton("Modifier");
	
	public static JComboBox<String> cbAgence = new JComboBox();
	public static JComboBox<String> cbProprietaire = new JComboBox();
	public static JComboBox<String> cbAppartement = new JComboBox();
	
	private JTextField txtEtat = new JTextField();
	
	public VueContrat(){
		
		this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 10, 700, 270);
		unPanel.setBackground(new Color(221, 238, 254));
		unPanel.setLayout(new GridLayout(5,4, 10, 40));//5 lignes et 4 colonnes
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		//création des trois calendriers
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1,p);
		JDatePickerImpl dateSignature = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		
		UtilDateModel model2 = new UtilDateModel();
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2,p);
		JDatePickerImpl dateDebut = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		 
		UtilDateModel model3 = new UtilDateModel();
		JDatePanelImpl datePanel3 = new JDatePanelImpl(model3,p);
		JDatePickerImpl dateFin = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
			
		unPanel.add(new JLabel("Agence : "));
		unPanel.add(VueContrat.cbAgence); 
		unPanel.add(new JLabel("Propriétaire : "));
		unPanel.add(VueContrat.cbProprietaire);
			
			
		unPanel.add(new JLabel("Appartement : "));
		unPanel.add(VueContrat.cbAppartement);
		unPanel.add(new JLabel("Date Signature : "));
		unPanel.add(dateSignature);
		
			
		unPanel.add(new JLabel("Début contrat : "));
		unPanel.add(dateDebut);
		unPanel.add(new JLabel("Fin contrat : "));
		unPanel.add(dateFin);
		
		unPanel.add(new JLabel("Etat contrat : "));
		unPanel.add(this.txtEtat);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(this.btAjouter);
		unPanel.add(this.btModifier);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
	
			
		this.btAjouter.addActionListener(this);
		this.btModifier.addActionListener(this);
			
		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
		btAjouter.setBackground(new Color(139, 169, 225));
		btModifier.setBackground(new Color(139, 169, 225));
			
			
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
			
			//remplir la combobox region avec toutes les regions de la table region
		
		//	ArrayList<Appartement> lesAppartements = Modele.getAppartements();
		//	for(Appartement unAppartement : lesAppartements){
			//	this.cbAppart.addItem(unAppartement.getIdAppart()+" - "+unAppartement.getAdresse()+" - "+unAppartement.getCp());
			//}

		this.add(unPanel);
		
		//this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
