package it.unipv.sfw.findme.register;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import it.unipv.sfw.findme.email.GenerateRandom;
import it.unipv.sfw.findme.enums.UserType;
import it.unipv.sfw.findme.exceptions.ExceptionFrame;
import it.unipv.sfw.findme.login.LoginGUI;
import it.unipv.sfw.findme.login.PanelLogo;


public class RegistrationGUI extends JFrame {

	public RegistrationGUI() {
		openRegisterGUI();
	}

	public void openRegisterGUI() {
		Toolkit kit = Toolkit.getDefaultToolkit();

		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;//finestra delle dimensioni dello schermo
		int screenWidth = screenSize.width;

		ImageIcon Icon=new ImageIcon("Resources/Images/background_v2.jpeg");
		Image image = Icon.getImage();
		Image newimg = image.getScaledInstance(screenWidth, screenHeight, java.awt.Image.SCALE_SMOOTH);

		Icon = new ImageIcon(newimg);

		JLabel ll= new JLabel(Icon);
		add(ll);


		setSize(screenWidth,screenHeight);
		JLayeredPane pane = this.getLayeredPane();


		PanelLogo panellogo=new PanelLogo();
		panellogo.setBounds(0, 0, 200, 200);

		MainPanel p=new MainPanel(this);
		p.setBounds(10, 20, screenWidth, screenHeight);
		pane.add(panellogo, new Integer(1));
		pane.add(p, new Integer(2));

		//setSize(screenWidth,screenHeight);
		setTitle("Register");
		
		ImageIcon icon=new ImageIcon("Resources/Images/logo4.png");
		setIconImage(icon.getImage());

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

class MainPanel extends JPanel{
	private String[] emailTerminations= {"@universitadipavia.it", "@unipv.it"};//creo un vettore contenente gli unici domini accettati

	public MainPanel(RegistrationGUI frame) {


		setLayout (new GridBagLayout());//crea una griglia cartesiana, uso le coordinate per mettere i componenti
		GridBagConstraints c=new GridBagConstraints();
		setBackground(new Color (0,0,0,0));


		c.anchor = GridBagConstraints.CENTER;
		JLabel introLabel=new JLabel("Fill in below");
		introLabel.setFont(new Font("Comic Sans MS", Font.BOLD,20));
		introLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (0,0,50,0);
		c.gridx=1;
		c.gridy=0;
		add(introLabel, c);




		c.anchor = GridBagConstraints.EAST;
		JLabel nameLabel=new JLabel("Name: ");
		nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		nameLabel.setForeground(new Color(145,0,0));
		c.insets= new Insets (0,0,20,0);
		c.gridx=0;
		c.gridy=1;
		add(nameLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField nameField=new JTextField(20);
		c.gridx=1;
		c.gridy=1;
		add(nameField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel lastNameLabel=new JLabel("Last Name: ");
		lastNameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		lastNameLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=2;
		add(lastNameLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField lastNameField=new JTextField(20);
		c.gridx=1;
		c.gridy=2;
		add(lastNameField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel idNumber=new JLabel("Identification Number: ");
		idNumber.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		idNumber.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=3;
		add(idNumber, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField idNumberField=new JTextField(20);
		c.gridx=1;
		c.gridy=3;
		add(idNumberField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel emailLabel=new JLabel("E-mail: ");
		emailLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		emailLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=4;
		add(emailLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JTextField emailField=new JTextField(20);
		c.gridx=1;
		c.gridy=4;
		add(emailField, c);
		//
		c.anchor = GridBagConstraints.WEST;
		JComboBox<String> emailSelect=new JComboBox<String>(this.emailTerminations);
		emailSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.revalidate();
				frame.repaint();
			}
		});
		emailSelect.setBackground(Color.WHITE);
		emailSelect.setForeground(new Color(145,0,0));
		c.gridx=2;
		c.gridy=4;
		add(emailSelect, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel passwordLabel=new JLabel("Password: ");
		passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		passwordLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=5;
		add(passwordLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField passwordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=5;
		add(passwordField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel confirmPasswordLabel=new JLabel("Confirm Password: ");
		confirmPasswordLabel.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		confirmPasswordLabel.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=6;
		add(confirmPasswordLabel, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JPasswordField confirmPasswordField=new JPasswordField(20);
		c.gridx=1;
		c.gridy=6;
		add(confirmPasswordField, c);
		//
		c.anchor = GridBagConstraints.EAST;
		JLabel userTypeField=new JLabel("Select User Type: ");
		userTypeField.setFont(new Font("Comic Sans MS", Font.BOLD,15));
		userTypeField.setForeground(new Color(145,0,0));
		c.gridx=0;
		c.gridy=7;
		add(userTypeField, c);
		//
		c.anchor = GridBagConstraints.CENTER;
		JComboBox<UserType> userTypeSelect= new JComboBox<UserType>(UserType.values());//menu a tendina a cui passo la enum
		userTypeSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.revalidate();
				frame.repaint();

			}});
		userTypeSelect.setBackground(Color.WHITE);
		userTypeSelect.setFont(new Font("Comic Sans MS", Font.PLAIN,10));
		userTypeSelect.setForeground(new Color(145,0,0));
		c.gridx=1;
		c.gridy=7;
		add(userTypeSelect, c);
		//
		JButton registerButton=new JButton("Register");
		registerButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		registerButton.setForeground(Color.white);
		registerButton.setBackground(new Color(145,0,0));
		registerButton.setOpaque(true);
		registerButton.setBorderPainted(false);

		c.gridx=1;
		c.gridy=8;
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Registration registration=new Registration(nameField.getText(), lastNameField.getText(), idNumberField.getText(), emailField.getText(), (String)emailSelect.getSelectedItem(), passwordField.getPassword(), confirmPasswordField.getPassword(), userTypeSelect.getSelectedItem().toString());
				
				
				try {
					registration.emailCheck();
				} catch (Exception e1) {
					new ExceptionFrame("Email not Valid");
					return;
				}
				
				try {
					registration.passwordCheck();
				} catch (Exception e1) {
					new ExceptionFrame("Password not Valid!");
					return;
				}

				try {
					registration.checkDuplicate();
				} catch (Exception e1) {
					new ExceptionFrame("User already registered!");
					return;
				}
				
				try {
					registration.TypeCheck();
				} catch (Exception e1) {
					new ExceptionFrame("User Type not Valid!");
					return;
				}
				
				int number=new GenerateRandom().getRandom();
				
				registration.emailVerification(number);
				
				VerificationFrame v=new VerificationFrame(number, registration, frame);
				
			}

		});

		add(registerButton, c);
		//
		JButton backButton=new JButton("Back");
		c.gridx=1;
		c.gridy=9;
		backButton.setFont(new Font("Comic Sans MS", Font.PLAIN,15));
		backButton.setForeground(Color.white);
		backButton.setBackground(new Color(145,0,0));
		backButton.setOpaque(true);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI l=new LoginGUI();
				frame.dispose();
			}
		});
		add(backButton, c);

	}

}