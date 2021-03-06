package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicComboBoxEditor;

import presenter.PAdmin;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUIAdmin extends GUIAutentificare implements IAdminView{

	protected JTextField txtNumeAngajat;
	protected JTextField txtVarstaAngajat;
	protected JButton btnAddAngajat;
	protected JComboBox<String> boxAngajat;
	protected JButton btnStergeAngajat;
	protected JButton btnLogOut;
	protected JButton btnActualizareAngajat;
	protected JLabel lblListaMagazine;
	protected JComboBox<String> boxMagazine;
	protected JTextField txtUsername;
	protected JTextField txtParola;

	public GUIAdmin() {

		this.getContentPane().removeAll();
		setBounds(100, 100, 1438, 779);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAdmin = new JLabel("Administrator");
		lblAdmin.setBounds(532, 10, 288, 60);
		panel.add(lblAdmin);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 49));
		
		JLabel lblNumeAngajat = new JLabel("Nume angajat:");
		lblNumeAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNumeAngajat.setBounds(242, 159, 260, 46);
		panel.add(lblNumeAngajat);
		
		txtNumeAngajat = new JTextField();
		txtNumeAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtNumeAngajat.setBounds(532, 163, 311, 37);
		panel.add(txtNumeAngajat);
		txtNumeAngajat.setColumns(10);
		
		JLabel lblVarstaAngajat = new JLabel("Varsta angajat:");
		lblVarstaAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblVarstaAngajat.setBounds(242, 332, 224, 46);
		panel.add(lblVarstaAngajat);
		
		txtVarstaAngajat = new JTextField();
		txtVarstaAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtVarstaAngajat.setColumns(10);
		txtVarstaAngajat.setBounds(532, 336, 311, 37);
		txtVarstaAngajat.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (!(Character.isDigit(c) ||
	                (c == KeyEvent.VK_BACK_SPACE) ||
	                (c == KeyEvent.VK_DELETE))) {
	                  e.consume();
	                }
	           }
	         });
		panel.add(txtVarstaAngajat);
		
		btnAddAngajat = new JButton("Adauga angajat");
		btnAddAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnAddAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAdmin.this.adaugareAngajat_Click())
				{
					JOptionPane.showMessageDialog(null, "Angajatul a fost adaugat cu succes");
					GUIAdmin.this.boxAngajat.setModel(new DefaultComboBoxModel(GUIAdmin.this.boxMagazin_Selected_Action().toArray()));
					GUIAdmin.this.clearTextFields();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eroare la adaugare","Error", JOptionPane.ERROR_MESSAGE);
					GUIAdmin.this.clearTextFields();
				}
			}
		});
		btnAddAngajat.setBounds(397, 388, 277, 37);
		panel.add(btnAddAngajat);
		
		
		boxAngajat = new JComboBox<String>();
		boxAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		boxAngajat.setBounds(532, 440, 311, 37);
		panel.add(boxAngajat);
		
		btnStergeAngajat = new JButton("Stergere angajat");
		btnStergeAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAdmin.this.stergereAngajat_Click())
				{
					JOptionPane.showMessageDialog(null, "Angajatul a fost sters cu succes");
					GUIAdmin.this.boxAngajat.setModel(new DefaultComboBoxModel(GUIAdmin.this.boxMagazin_Selected_Action().toArray()));
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eroare la stergere","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnStergeAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnStergeAngajat.setBounds(397, 502, 277, 37);
		panel.add(btnStergeAngajat);
		
		btnActualizareAngajat = new JButton("Actualizare angajat");
		btnActualizareAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnActualizareAngajat.setBounds(397, 549, 277, 37);
		btnActualizareAngajat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAdmin.this.actualizareAngajat_Click())
				{
					JOptionPane.showMessageDialog(null, "Angajatul a fost actualizat cu succes");
					GUIAdmin.this.boxAngajat.setModel(new DefaultComboBoxModel(GUIAdmin.this.boxMagazin_Selected_Action().toArray()));
					GUIAdmin.this.clearTextFields();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eroare la actualizare","Error", JOptionPane.ERROR_MESSAGE);
					GUIAdmin.this.clearTextFields();
				}
			}
		});
		panel.add(btnActualizareAngajat);
		
		JLabel lblListaAngajati = new JLabel("Lista angajati:");
		lblListaAngajati.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblListaAngajati.setBounds(242, 435, 224, 46);
		panel.add(lblListaAngajati);
		
		lblListaMagazine = new JLabel("Lista magazine:");
		lblListaMagazine.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblListaMagazine.setBounds(242, 103, 260, 46);
		panel.add(lblListaMagazine);
		
		String[] magazine = {"H&M","Bherska","New Yorker"};
		boxMagazine = new JComboBox<String>();
		boxMagazine.setModel(new DefaultComboBoxModel(magazine));
		boxMagazine.setFont(new Font("Tahoma", Font.PLAIN, 28));
		boxMagazine.setBounds(532, 109, 311, 37);
		JScrollPane scroller = new JScrollPane();
		scroller.setViewportView(new JTextField());
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		boxMagazine.setEditor(new BasicComboBoxEditor());
		boxMagazine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIAdmin.this.boxAngajat.setModel(new DefaultComboBoxModel(GUIAdmin.this.boxMagazin_Selected_Action().toArray()));
			}
		});
		panel.add(boxMagazine);
		
		JLabel lblUsernameAngajat = new JLabel("Username angajat:");
		lblUsernameAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblUsernameAngajat.setBounds(242, 215, 241, 46);
		panel.add(lblUsernameAngajat);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtUsername.setColumns(10);
		txtUsername.setBounds(532, 219, 311, 37);
		panel.add(txtUsername);
		
		JLabel lblParolaAngajat = new JLabel("Parola angajat:");
		lblParolaAngajat.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblParolaAngajat.setBounds(242, 271, 224, 46);
		panel.add(lblParolaAngajat);
		
		txtParola = new JTextField();
		txtParola.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtParola.setColumns(10);
		txtParola.setBounds(532, 280, 311, 37);
		panel.add(txtParola);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIAdmin.this.logout_Click();
				GUIAdmin.this.dispose();
			}
		});
		getContentPane().add(btnLogOut, BorderLayout.NORTH);
		this.setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public String numeText() {
		return txtNumeAngajat.getText();
	}
	public String usernameText()
	{
		return this.txtUsername.getText();
	}
	public String parolaText()
	{
		return this.txtParola.getText();
	}
	@Override
	public String varstaText() {
		return txtVarstaAngajat.getText();
	}
	private ArrayList<String> boxMagazin_Selected_Action()
	{
		PAdmin admin = new PAdmin(this);
		ArrayList<String> listaAngajati = admin.accesListaAngajati();
		if(listaAngajati.size()==0)
		{
			listaAngajati.add("");
		}
		return listaAngajati;
		
	}
	private boolean adaugareAngajat_Click()
	{
		PAdmin padmin = new PAdmin(this);
		return padmin.Adaugare();
		
	}
	
	private boolean stergereAngajat_Click()
	{
		PAdmin padmin = new PAdmin(this);
		return padmin.Stergere();
		
	}
	private boolean actualizareAngajat_Click()
	{
		PAdmin padmin = new PAdmin(this);
		return padmin.Actualizare();
		
	}
	public String boxMagazinText()
	{
		return this.boxMagazine.getSelectedItem().toString();
	}
	public String boxAngajatText()
	{
		return this.boxAngajat.getSelectedItem().toString();
	}
	private void logout_Click()
	{
		PAdmin admin = new PAdmin(this);
		admin.Logout();
	}
	private void clearTextFields()
	{
		this.txtNumeAngajat.setText("");
		this.txtParola.setText("");
		this.txtUsername.setText("");
		this.txtVarstaAngajat.setText("");
		
	}
}
