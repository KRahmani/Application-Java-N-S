package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Main;
import modele.Modele;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{
	
	
	private static final long serialVersionUID = 1L;
	private JPanel unPanael = new JPanel();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JTextField txtLogin = new JTextField();//zone de saisie de texte
	private JPasswordField pwMpd = new JPasswordField();//zone de saisie de mdp (apparaitra en ***)
	
	public VueConnexion(){
		this.setTitle("Logiciel de gestion Neige&Soleil ");
		this.setBounds(600,180,700,550);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(58, 155, 228));
		
		//construction du panel
		this.unPanael.setBounds(90,150,520,300);
		this.unPanael.setLayout(new GridLayout(3,2));//on crée la zone de disposition, on crée un tableau(grid) de 3lignes et 2colonnes
		this.unPanael.setBackground(Color.yellow);
		
		Font uneFont = new Font("Times new roman", Font.BOLD, 22);

		//ajout des composants au panel
		JLabel lbEmail = new JLabel("Login : ");
		JLabel lbMdp = new JLabel("MDP: ");
		
		this.unPanael.add(lbEmail); //label login
		this.unPanael.add(this.txtLogin);// zone de saisie (edit) du login
		this.unPanael.add(lbMdp);//label MDP
		this.unPanael.add(this.pwMpd);//zone de saisie du mdp
		this.unPanael.add(btAnnuler);
		this.unPanael.add(btSeConnecter);
		for (int i= 0; i< this.unPanael.getComponentCount(); i++)
		{
		this.unPanael.getComponent(i).setFont(uneFont);
		}
		
		//rendre le panel visisble
		this.unPanael.setVisible(true);
		//ajouter le panel à la fenetre principale
		this.add(this.unPanael);
		
		//ajout du logo
		ImageIcon logo = new ImageIcon("src/images/logo.png"); //mettre notre image dans le composant imageIcon qu'on a appelé logo
		JLabel lbLogo = new JLabel(logo);//mettre logo dans un label pour pouvoir le visualiser
		lbLogo.setBounds(150,20,400,120);
		this.add(lbLogo);
		
		//changer le logo de l'application
		ImageIcon logoPetit = new ImageIcon("src/images/logoNSPetit.jpg");
		this.setIconImage(logoPetit.getImage());
		
		//rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//RENDRE LES zones de textes écoutables
		this.txtLogin.addKeyListener(this);
		this.pwMpd.addKeyListener(this);
		
		//rendre visible la fenetre principale
		this.setVisible(true);
	}

	public void traitement(){
		String login = this.txtLogin.getText();//récupérer le login
		String mdp = new String (this.pwMpd.getPassword());//récupérer le mot de passe(on a mis string pour construire la chaine car getPassword donne un tableau de char)
		if (login.equals("") || mdp.equals("")){
			JOptionPane.showMessageDialog(this,"Veuillez saisir vos identifiants");
		}else{
			//on vérifie dans la bdd la connexion
			String droits = Modele.verifConnexion(login,mdp);
			if (droits.equals("")){
				JOptionPane.showMessageDialog(this, "Erreur de connexion","Erreur",JOptionPane.ERROR_MESSAGE);
				this.txtLogin.setText("");
				this.pwMpd.setText("");
			}else{
				JOptionPane.showMessageDialog(this, "Bienvenue !", "Connexion réussie", JOptionPane.INFORMATION_MESSAGE);
				//lancement de la JFrame principale
				Main.rendreVisible(false);
				System.out.println("vous etes connectés");
				new VueGenerale();
			}
		}
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()){
		case "Annuler" :
			this.txtLogin.setText("");
			this.pwMpd.setText("");
			break;
		case "Se Connecter" :
			traitement();
			break;
		}
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER)//on teste si la touche entree est préssée
		{
			traitement();//on fait le meme traitement que quand on clique sur le bouton se connecter
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
