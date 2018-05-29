package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Equipement;
import controller.InfoVille;
import controller.Materiel;
import controller.Proprietaire;
import modele.Modele;

public class VueMateriel extends JPanel implements ActionListener{
	
	private JButton btChoixPhoto = new JButton("Choisir une photo");
	private JButton btAjouter = new JButton("Ajouter");
	
	public JComboBox<String> cbProprietaire = new JComboBox();
	
	private JTextField txtNom= new JTextField();
	private JTextField txtEtat = new JTextField();
	private JTextField txtPrix = new JTextField();
	private JTextField txtLienPhoto = new JTextField();
	
	public VueMateriel(){
		
		this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 50, 1200, 550);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(6,2, 10, 40));
		
		unPanel.add(new JLabel("Propriétaire : "));
		unPanel.add(this.cbProprietaire); 
		
		unPanel.add(new JLabel("Nom : "));
		unPanel.add(this.txtNom);
		
		unPanel.add(new JLabel("Etat : "));
		unPanel.add(this.txtEtat);
	
		
		unPanel.add(new JLabel("Prix par jour : "));
		unPanel.add(this.txtPrix);
		
	
		unPanel.add(this.btChoixPhoto);
		unPanel.add(this.txtLienPhoto);
		
		unPanel.add(new JLabel(""));
		unPanel.add(this.btAjouter);
	
		this.btAjouter.addActionListener(this);
		this.btChoixPhoto.addActionListener(this);

		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
		btAjouter.setBackground(new Color(139, 169, 225));
		btChoixPhoto.setBackground(new Color(139, 169, 225));

		
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
		
		
		//remplir la combobox proprietaire 
		ArrayList<Proprietaire> lesProprietaires = Modele.getProprietaires();
		for(Proprietaire unProprietaire : lesProprietaires){
			this.cbProprietaire.addItem(unProprietaire.getId()+" - "+unProprietaire.getNom()+" - "+unProprietaire.getPrenom());
		}
			
		this.setVisible(false);
		
	}
	
	public String ChoixPhoto(){	
		File fichier = null;
		JFileChooser dialogue = new JFileChooser();
		if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {	
			 fichier = dialogue.getSelectedFile();
		}
		this.txtLienPhoto.setText(fichier.getPath());
		return fichier.getPath();
	}
	
	public void ajoutMateriel(){
		
		if ( this.cbProprietaire.getSelectedItem().toString().equals("") || this.txtNom.getText().equals("") 
				|| this.txtPrix.getText().equals("") || this.txtLienPhoto.getText().equals("") 
				|| this.txtEtat.getText().equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
		}	
		//récupérer les valeurs des champs
		String nom, proprio, etat,lienphoto;
		int idproprio;
		float prix;
		
		proprio = this.cbProprietaire.getSelectedItem().toString();
		proprio = proprio.substring(0, proprio.indexOf(" - "));
		idproprio = Integer.parseInt(proprio);
		nom = this.txtNom.getText();
		etat = this.txtEtat.getText();
		lienphoto = this.txtLienPhoto.getText();
		prix = Float.parseFloat(this.txtPrix.getText());
		
		Materiel unMateriel = new Materiel(0,idproprio,nom,etat, lienphoto, prix);
		Modele.insertMateriel(unMateriel);
		JOptionPane.showMessageDialog(this, "Le matériel <"+nom+ "> a bien été ajouté !","Information", JOptionPane.INFORMATION_MESSAGE);	

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()){
		case "Ajouter":
			ajoutMateriel();
			break;
			
		case "Choisir une photo":
			ChoixPhoto();
			break;
		
			
		}
	}

	

}
