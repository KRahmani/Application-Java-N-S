package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Main;
import controller.Proprietaire;
import modele.Modele;

public class VueProprietaire extends JPanel implements ActionListener{
	
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btModifier = new JButton("Modifier");
	
	private JComboBox<String> civilite = new JComboBox();
	
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtCp = new JTextField();
	private JTextField txtVille = new JTextField();
	private JTextField txtTel = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtRib = new JTextField();
	
	public VueProprietaire(){
		
		this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);//null c'est à dire qu'il n y a aucun cadrillage, c'est une seule interface
		
		civilite.addItem("Monsieur");
		civilite.addItem("Madame");
		
		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
		btAjouter.setBackground(new Color(139, 169, 225));
		btModifier.setBackground(new Color(139, 169, 225));
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 50, 1200, 550);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(6,6, 10, 40));//6 lignes et 6 colonnes
		
		unPanel.add(new JLabel("Civilité : "));
		unPanel.add(this.civilite); 
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel("Nom : "));
		unPanel.add(this.txtNom);
		unPanel.add(new JLabel("Prénom : "));
		unPanel.add(this.txtPrenom);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel("Adresse : "));
		unPanel.add(this.txtAdresse);
		unPanel.add(new JLabel("Cp  : "));
		unPanel.add(this.txtCp);
		unPanel.add(new JLabel("Ville : "));
		unPanel.add(this.txtVille);
		
		unPanel.add(new JLabel("Tél  : "));
		unPanel.add(this.txtTel);
		unPanel.add(new JLabel("Email : "));
		unPanel.add(this.txtEmail);
		unPanel.add(new JLabel("Mdp : "));
		unPanel.add(this.txtMdp);
	
		unPanel.add(new JLabel("Rib : "));
		unPanel.add(this.txtRib);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(this.btModifier);
		unPanel.add(this.btAjouter);
		
		this.btAjouter.addActionListener(this);
		this.btModifier.addActionListener(this);
		
		
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
		this.setVisible(false);
	}
		
		public void AjoutProprietaire(){
			
			//récupérer les valeur des champs
			String civilite, nom, prenom, adresse, cp, ville, tel, email, mdp, rib;
			civilite = this.civilite.getSelectedItem().toString();
			nom = this.txtNom.getText();
			prenom = this.txtPrenom.getText();
			adresse = this.txtAdresse.getText();
			cp = this.txtCp.getText();
			ville = this.txtVille.getText();
			tel = this.txtTel.getText();
			email = this.txtEmail.getText();
			mdp = this.txtMdp.getText();
			rib = this.txtRib.getText();
			if (nom.equals("") || prenom.equals("") || adresse.equals("") || cp.equals("")|| ville.equals("") || tel.equals("")|| email.equals("")|| mdp.equals("")|| rib.equals("")){
				JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
				
			}
			else if (cp.length() > 5 || ! verifContenuChamp(cp)){
				JOptionPane.showMessageDialog(this,"Le code postal doit être composé de 5 chiffres!");
			}
			else if ( ! verifContenuChamp(tel)){
				JOptionPane.showMessageDialog(this,"Le numéro de téléphone doit être composé de chiffres uniquement!");
			}
			else{
				int id = Modele.getMaxIdTiers() + 1;
				Proprietaire unProprietaire = new Proprietaire(id, civilite, nom, prenom, adresse, cp, ville, tel, email, mdp, rib );
				Modele.insertProprietaire(unProprietaire);
				JOptionPane.showMessageDialog(this, "Le propriétaire: " +unProprietaire.getNom()+" "+unProprietaire.getPrenom()+" a bien été ajouté!","Information", JOptionPane.INFORMATION_MESSAGE);
			}		
	}
		
public void ModifProprietaire(){
			
			//récupérer les valeur des champs
			String civilite, nom, prenom, adresse, cp, ville, tel, email, mdp, rib;
			civilite = this.civilite.getSelectedItem().toString();
			nom = this.txtNom.getText();
			prenom = this.txtPrenom.getText();
			adresse = this.txtAdresse.getText();
			cp = this.txtCp.getText();
			ville = this.txtVille.getText();
			tel = this.txtTel.getText();
			email = this.txtEmail.getText();
			mdp = this.txtMdp.getText();
			rib = this.txtRib.getText();
			if (nom.equals("") || prenom.equals("") || adresse.equals("") || cp.equals("")|| ville.equals("") || tel.equals("")|| email.equals("")|| mdp.equals("")|| rib.equals("")){
				JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
				
			}
			else if (cp.length() > 5 || ! verifContenuChamp(cp)){
				JOptionPane.showMessageDialog(this,"Le code postal doit être composé de 5 chiffres!");
			}
			else if ( ! verifContenuChamp(tel)){
				JOptionPane.showMessageDialog(this,"Le numéro de téléphone doit être composé de chiffres uniquement!");
			}
			else{
				int id = Modele.getIdProp(nom, prenom);
				if (id == 0){
					JOptionPane.showMessageDialog(this,"Le propriétaire saisi n'existe pas, veuillez vérifier le nom et le prénom que vous avez entrés et réessayer!");
				}
				else{
				Proprietaire unProprietaire = new Proprietaire(id, civilite, nom, prenom, adresse, cp, ville, tel, email, mdp, rib );
				Modele.updateProprietaire(unProprietaire);
				JOptionPane.showMessageDialog(this, "Les informations du propriétaire: " +unProprietaire.getNom()+" "+unProprietaire.getPrenom()+" ont bien été modifiées!","Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}		
	}
		
		public boolean verifContenuChamp(String valeurChamp){
			String maChaine = "0123456789";
			
			for(int i=0; i<valeurChamp.length();i++){
				int diff = 0;
				for(int j=0; j<maChaine.length();j++){
					if (valeurChamp.charAt(i) != maChaine.charAt(j))
						diff ++;
				}
				if (diff == 10){
					return false;
				}
			}
			return true;
		}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(e.getActionCommand()){
		case "Ajouter":
			AjoutProprietaire();
			
			break;
		case "Modifier":
			ModifProprietaire();
			break;
		}
	}



}
