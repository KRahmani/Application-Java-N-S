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
import controller.Equipement;
import controller.InfoVille;
import modele.Modele;

public class VueEquipement extends JFrame implements ActionListener {
	
	private JButton btAjouter = new JButton("Ajouter");
	private JComboBox<String> cbAppart = new JComboBox();
	
	private JTextField txtNom= new JTextField();
	private JTextField txtNb = new JTextField();
	
public VueEquipement(){
		
		this.setBounds(10,10,750,350);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
			
		JPanel unPanel = new JPanel();
		unPanel.setBounds(20, 10, 700, 270);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(3,4, 10, 40));
			
		unPanel.add(new JLabel("Appartement : "));
		unPanel.add(this.cbAppart); 
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
			
			
		unPanel.add(new JLabel("Nom : "));
		unPanel.add(this.txtNom);
		unPanel.add(new JLabel("Nombre : "));
		unPanel.add(this.txtNb);
		
			
		unPanel.add(this.btAjouter);
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
		unPanel.add(new JLabel(""));
			

			
			this.btAjouter.addActionListener(this);
			this.cbAppart.addActionListener(this);
	
			
			Font uneFont = new Font("Times new roman", Font.BOLD, 18);
			btAjouter.setBackground(new Color(139, 169, 225));
			
			
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
		
			ArrayList<Appartement> lesAppartements = Modele.getAppartements();
			for(Appartement unAppartement : lesAppartements){
				this.cbAppart.addItem(unAppartement.getIdAppart()+" - "+unAppartement.getAdresse()+" - "+unAppartement.getCp());
			}
			
			this.add(unPanel);
			this.setVisible(true);
	
}
	
public void ajoutEquipement(){
	
	if ( this.cbAppart.getSelectedItem().toString().equals("") || this.txtNom.getText().equals("") 
			|| this.txtNb.getText().equals("")){
		JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
	}	
	//récupérer les valeurs des champs
	String nom, appart;
	int idAppart, nb;
	
	appart = this.cbAppart.getSelectedItem().toString();
	appart = appart.substring(0, appart.indexOf(" - "));
	idAppart = Integer.parseInt(appart);
	nom = this.txtNom.getText();
	nb = Integer.parseInt(this.txtNb.getText());
	
	Equipement unEquipement = new Equipement(0,nb,idAppart,nom);
	Modele.insertEquipement(unEquipement);
	JOptionPane.showMessageDialog(this, "L'équipement <"+nom+ "> a bien été ajouté à l'appartement <"+idAppart+"> !","Information", JOptionPane.INFORMATION_MESSAGE);	

}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "Ajouter"){
			ajoutEquipement();
		}
		
	}

}
