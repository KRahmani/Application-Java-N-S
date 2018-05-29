package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Appartement;
import controller.InfoVille;
import modele.Modele;

public class VueVille extends JFrame implements ActionListener {
	
	private static VueRegion uneRegion =null ;

	private JButton btNewRegion = new JButton("Nouvelle région");
	private JButton btAjouter = new JButton("Ajouter");
	
	public static JComboBox<String> cbRegion = new JComboBox();
	
	private JTextField txtNom= new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtTelAgence = new JTextField();
	private JTextField txtTelCommis = new JTextField();
	private JTextField txtTelMairie = new JTextField();
	private JTextField txtTelPharma = new JTextField();
	private JTextField txtTelMedecin = new JTextField();
	
	public VueVille(){
		
		this.setBounds(10,10,1050,520);
			this.setBackground(new Color(222, 238, 254));
			this.setLayout(null);
			
			JPanel unPanel = new JPanel();
			unPanel.setBounds(20, 10, 1000, 450);
			unPanel.setBackground(new Color(222, 238, 254));
			unPanel.setLayout(new GridLayout(6,4, 10, 40));//6 lignes et 5 colonnes
			
			unPanel.add(new JLabel("Région : "));
			unPanel.add(cbRegion); 
			unPanel.add(this.btNewRegion);
			unPanel.add(new JLabel(""));
			
			
			unPanel.add(new JLabel("Nom : "));
			unPanel.add(this.txtNom);
			unPanel.add(new JLabel("CP : "));
			unPanel.add(this.txtCp);
		
			
			unPanel.add(new JLabel("Tel agence tourisme : "));
			unPanel.add(this.txtTelAgence);
			unPanel.add(new JLabel("Tel Mairie : "));
			unPanel.add(this.txtTelMairie);
			
			
			unPanel.add(new JLabel("Tel commissariat : "));
			unPanel.add(this.txtTelCommis);
			unPanel.add(new JLabel("Tel Medecin : "));
			unPanel.add(this.txtTelMedecin);
			
		
			unPanel.add(new JLabel("Tel pharmacie : "));
			unPanel.add(this.txtTelPharma);
			unPanel.add(new JLabel(""));
			unPanel.add(new JLabel(""));
			
		
			
			unPanel.add(new JLabel(""));
			unPanel.add(new JLabel(""));
			unPanel.add(new JLabel(""));
			unPanel.add(this.btAjouter);

			
			this.btAjouter.addActionListener(this);
			this.btNewRegion.addActionListener(this);
			
			Font uneFont = new Font("Times new roman", Font.BOLD, 18);
			btAjouter.setBackground(new Color(139, 169, 225));
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
			
			//remplir la combobox région
			actualiserCBXRegion();
			
			this.setVisible(true);
				
	}
	
	
	
	public static VueRegion getUneRegion() {
		return uneRegion;
	}



	public static void setUneRegion(VueRegion uneRegion) {
		VueVille.uneRegion = uneRegion;
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
	
	public void ajoutVille(){
		
		if ( cbRegion.getSelectedItem().toString().equals("") || this.txtNom.getText().equals("") 
				|| this.txtCp.getText().equals("") || this.txtTelAgence.getText().equals("")
				|| this.txtTelMairie.getText().equals("") 
				|| this.txtTelCommis.getText().equals("")|| this.txtTelMedecin.getText().equals("")|| 
				this.txtTelPharma.getText().equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
		}	
		//récupérer les valeurs des champs
		String region, nom, cp, telAgence, telMairie, telCommis, telMedecin, telPharma;
		
		region = cbRegion.getSelectedItem().toString();
		nom = this.txtNom.getText();
		cp = this.txtCp.getText();
		telAgence = this.txtTelAgence.getText();
		telMairie = this.txtTelMairie.getText();
		telCommis = this.txtTelCommis.getText();
		telMedecin = this.txtTelMedecin.getText();
		telPharma = this.txtTelPharma.getText();
		
		InfoVille uneVille = new InfoVille(0,nom,region,cp, telAgence, telCommis,telMairie, telMedecin, telPharma);
		Modele.insertInfoVille(uneVille);
		JOptionPane.showMessageDialog(this, "La ville "+nom+ " a bien été ajoutée!","Information", JOptionPane.INFORMATION_MESSAGE);	

	}

	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "Ajouter":
			ajoutVille();
			VueAppartement.getUneVille().setVisible(false);
			this.txtNom.setText("");
			this.txtCp.setText("");
			this.txtTelAgence.setText("");
			this.txtTelCommis.setText("");
			this.txtTelMairie.setText("");
			this.txtTelPharma.setText("");
			this.txtTelMedecin.setText("") ;
			VueAppartement.actualiserCBXVille();
			break;
		case "Nouvelle région":
			//VueRegion uneVueRegion = new VueRegion();
			if (uneRegion == null) {
				
				uneRegion = new VueRegion(1); 
				
				}
			uneRegion.setVisible(true);
			break;
		}
		
	}


}
