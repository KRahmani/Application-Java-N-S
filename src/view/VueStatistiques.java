package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VueStatistiques extends JPanel implements ActionListener{
	
	
	
	public VueStatistiques(){
		
		this.setBounds(10,10,1270,630);
		this.setBackground(new Color(222, 238, 254));
		this.setLayout(null);
		
		
		
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
