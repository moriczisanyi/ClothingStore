package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import presenter.PAutentificare;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class GUIAutentificare extends JFrame implements IAutentificareView{

	protected JPanel contentPane;
	protected JTextField txtUsername;
	protected JPasswordField txtParola;
	protected JButton btnAutentificare;
	protected JComboBox boxMagazin;
	protected static JLabel lblMagazin = new JLabel("");
	public GUIAutentificare() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1438, 779);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Bine ai venit !");
		lblWelcome.setFont(new Font("Dialog", Font.PLAIN, 55));
		lblWelcome.setBounds(532, 78, 342, 124);
		contentPane.add(lblWelcome);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblUsername.setBounds(438, 190, 157, 124);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtUsername.setBounds(605, 232, 321, 38);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblParola.setBounds(438, 278, 157, 124);
		contentPane.add(lblParola);
		
		
		txtParola = new JPasswordField();
		txtParola.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtParola.setColumns(10);
		txtParola.setBounds(605, 320, 321, 38);
		contentPane.add(txtParola);
		
		btnAutentificare = new JButton("Autentificare");
		btnAutentificare.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAutentificare.setBounds(605, 478, 288, 56);
		btnAutentificare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAutentificare.this.autentificare_Click())
				{
					GUIAutentificare.this.dispose();
				}
			}
		});
		contentPane.add(btnAutentificare);
		
		JLabel lblSelectareMagazinPentru = new JLabel("Selectare magazin pentru angajat:");
		lblSelectareMagazinPentru.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblSelectareMagazinPentru.setBounds(306, 344, 488, 124);
		contentPane.add(lblSelectareMagazinPentru);
		
		String[] magazine = {"H&M","Bherska","New Yorker"};
		boxMagazin = new JComboBox<String>();
		boxMagazin.setModel(new DefaultComboBoxModel(magazine));
		boxMagazin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		boxMagazin.setBounds(778, 387, 321, 38);
		boxMagazin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMagazin.setText(boxMagazin.getSelectedItem().toString());
			}
		});
		contentPane.add(boxMagazin);
		
		
		lblMagazin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMagazin.setBounds(1216, 23, 170, 31);
		contentPane.add(lblMagazin);

		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public String usernameText()
	{
		return txtUsername.getText();
	}
	public String parolaText()
	{
		return txtParola.getText();
	}
	public String boxMagazinSelected()
	{
		return GUIAutentificare.lblMagazin.getText();
	}
	private boolean autentificare_Click()
	{
		PAutentificare pa = new PAutentificare(this);
		return pa.Autentificare();
	}
}
