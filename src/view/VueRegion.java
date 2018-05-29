package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Proprietaire;
import controller.Region;
import modele.Modele;
import controller.Appartement;

public class VueRegion extends JFrame implements ActionListener{
	private JTextField txtNomR = new JTextField();
	private JButton btCreer = new JButton("Créer");
	
	private int choix;
	public VueRegion(int choix) {
		
		this.choix=choix;
		this.setBounds(300,100,400,300);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);//null 
		
		JPanel unPanel = new JPanel();
		unPanel.setBounds(10, 20, 350, 220);
		unPanel.setBackground(new Color(222, 238, 254));
		unPanel.setLayout(new GridLayout(2,2, 10, 40));//les quatres arguments: le nombres de lignes, le nombre de colonnes, l'espacement extérieur en longueur et en largeur avec les autres composants 
		
		unPanel.add(new JLabel("Nom région : ")); 
		unPanel.add(this.txtNomR);
		
		unPanel.add(this.btCreer);
		unPanel.add(new JLabel(""));
		
		this.btCreer.addActionListener(this);
		this.btCreer.setBackground(new Color(139, 169, 225));
		
		Font uneFont = new Font("Times new roman", Font.BOLD, 18);
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
		this.setVisible(true);
		
	}
	
	public void AjoutRegion(){
		
		//récupérer les valeur des champs
		String nomR = this.txtNomR.getText();
	
		if (nomR.equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs!");
			
		}
		
		else{
			
			Region uneRegion = new Region(0, nomR);
			Modele.insertRegion(uneRegion);
			JOptionPane.showMessageDialog(this, "La région: " +uneRegion.getNom()+" a bien été ajoutée!","Information", JOptionPane.INFORMATION_MESSAGE);
		
		}		
}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand() == "Créer" ){
			AjoutRegion();
			if(choix==1)
			{
				VueVille.getUneRegion().setVisible(false);
				VueVille.actualiserCBXRegion();
		
			}else if (this.choix==2)
			{
				
				VueAppartement.getUneRegion().setVisible(false);
				this.txtNomR.setText("");
				VueAppartement.actualiserCBXRegion();
			}
			
		}
			
	}
	
}
