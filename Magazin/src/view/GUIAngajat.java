package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileSystemView;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import presenter.PAngajat;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class GUIAngajat extends GUIAutentificare implements IAngajatView{


	protected JTextField txtNumeProdus;
	protected JTextField txtNumeProducator;
	protected JTextField txtDisponibilitate;
	protected JTextField txtPret;
	protected JTextField txtCautareProdus;
	
	protected JComboBox<String> boxProducator;
	protected JComboBox<String> boxProduse;
	protected JComboBox<String> boxPret;
	protected JComboBox<String> boxDisponibilitate;
	
	protected JList list;
	protected DefaultListModel<String> model;

	

	protected JButton btnLogOut;
	protected JButton btnAdaugaImagine;
	protected JButton btnAdaugareProdus;
	protected JButton btnStergeProdus;
	protected JButton btnActualizareProdus;
	
	protected JButton btnStatisticaProducator;
	protected JButton btnStatisticaPret;
	protected JButton btnStatisticaDisponibilitate;

	protected JButton btnGenerareRaportJson;
	protected JButton btnGenerareRaportCSV;
	
	protected File caleImagini = new File("F:\\Facultate_AN3SEM2\\PS\\Eclipse Work\\Magazin\\Images");
	protected String numeFisierImagine;
	protected JLabel lblImage = new JLabel("");

	public GUIAngajat() {

		
		this.getContentPane().removeAll();
		setBounds(100, 100, 1438, 779);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		//panel.add(lblMagazin);
		
		JLabel lblAngajat = new JLabel("Angajat");
		lblAngajat.setBounds(614, 10, 288, 60);
		panel.add(lblAngajat);
		lblAngajat.setFont(new Font("Tahoma", Font.PLAIN, 49));
		
		JLabel lblNumeProdus = new JLabel("Nume produs:");
		lblNumeProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumeProdus.setBounds(42, 146, 120, 46);
		panel.add(lblNumeProdus);
		
		txtNumeProdus = new JTextField();
		txtNumeProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNumeProdus.setBounds(192, 159, 162, 28);
		panel.add(txtNumeProdus);
		txtNumeProdus.setColumns(10);
		
		JLabel lblNumeProducator = new JLabel("Nume producator:");
		lblNumeProducator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumeProducator.setBounds(42, 202, 169, 46);
		panel.add(lblNumeProducator);
		
		txtNumeProducator = new JTextField();
		txtNumeProducator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNumeProducator.setColumns(10);
		txtNumeProducator.setBounds(192, 215, 162, 28);
		panel.add(txtNumeProducator);
		
		btnAdaugareProdus = new JButton("Adauga produs");
		btnAdaugareProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAngajat.this.adaugareProdus_click())
				{
					JOptionPane.showMessageDialog(null, "Produsul a fost adaugat cu succes");
					GUIAngajat.this.boxProduse.setModel(new DefaultComboBoxModel(GUIAngajat.this.updateProduseBox().toArray()));
					GUIAngajat.this.boxProducator.setModel(new DefaultComboBoxModel(GUIAngajat.this.accesListaProducatori().toArray()));
					model.removeAllElements();
					model.addAll(GUIAngajat.this.accesListaProduse());
					list = new JList(model);
					GUIAngajat.this.clearTextFields();
					numeFisierImagine=null;
				}
				else
				{
					GUIAngajat.this.clearTextFields();
					JOptionPane.showMessageDialog(null, "Eroare la adaugare","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdaugareProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdaugareProdus.setBounds(42, 437, 209, 28);
		panel.add(btnAdaugareProdus);
		
		boxProducator = new JComboBox<String>();
		boxProducator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxProducator.addItem("fara filtru");
		boxProducator.setModel(new DefaultComboBoxModel(GUIAngajat.this.accesListaProducatori().toArray()));
		boxProducator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				model.addAll(GUIAngajat.this.accesListaProduse());
				list = new JList(model);			
			}
		});
		boxProducator.setBounds(639, 114, 241, 21);
		panel.add(boxProducator);
		
		btnStergeProdus = new JButton("Stergere produs");
		btnStergeProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAngajat.this.stergereProdus_click())
				{
					JOptionPane.showMessageDialog(null, "Produsul a fost sters cu succes");
					GUIAngajat.this.boxProduse.setModel(new DefaultComboBoxModel(GUIAngajat.this.updateProduseBox().toArray()));
					GUIAngajat.this.boxProducator.setModel(new DefaultComboBoxModel(GUIAngajat.this.accesListaProducatori().toArray()));
					model.removeAllElements();
					model.addAll(GUIAngajat.this.accesListaProduse());
					list = new JList(model);
					GUIAngajat.this.clearTextFields();
				}
				else
				{
					GUIAngajat.this.clearTextFields();
					JOptionPane.showMessageDialog(null, "Eroare la stergere","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnStergeProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStergeProdus.setBounds(42, 531, 209, 28);
		panel.add(btnStergeProdus);
		
		btnActualizareProdus = new JButton("Actualizare produs");
		btnActualizareProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizareProdus.setBounds(42, 569, 209, 28);
		btnActualizareProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAngajat.this.actualizareProdus_click())
				{
					JOptionPane.showMessageDialog(null, "Produsul a fost actualizat cu succes");
					GUIAngajat.this.boxProduse.setModel(new DefaultComboBoxModel(GUIAngajat.this.updateProduseBox().toArray()));
					GUIAngajat.this.boxProducator.setModel(new DefaultComboBoxModel(GUIAngajat.this.accesListaProducatori().toArray()));
					model.removeAllElements();
					model.addAll(GUIAngajat.this.accesListaProduse());
					list = new JList(model);
					GUIAngajat.this.clearTextFields();
				}
				else
				{
					GUIAngajat.this.clearTextFields();
					JOptionPane.showMessageDialog(null, "Eroare la actualizare","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnActualizareProdus);
		
		JLabel lblProducatorFiltru = new JLabel("Producator:");
		lblProducatorFiltru.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProducatorFiltru.setBounds(509, 101, 120, 46);
		panel.add(lblProducatorFiltru);
		
		JLabel lblDisponibilitate = new JLabel("Disponibilitate:");
		lblDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDisponibilitate.setBounds(42, 258, 141, 46);
		panel.add(lblDisponibilitate);
		
		txtDisponibilitate = new JTextField();
		txtDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDisponibilitate.setColumns(10);
		txtDisponibilitate.setBounds(192, 271, 162, 28);
		txtDisponibilitate.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (!(Character.isDigit(c) ||
	                (c == KeyEvent.VK_BACK_SPACE) ||
	                (c == KeyEvent.VK_DELETE))) {
	                  e.consume();
	                }
	           }
	         });
		panel.add(txtDisponibilitate);
		
		JLabel lblPret = new JLabel("Pret:");
		lblPret.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPret.setBounds(42, 314, 141, 46);
		panel.add(lblPret);
		
		txtPret = new JTextField();
		txtPret.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPret.setColumns(10);
		txtPret.setBounds(192, 327, 162, 28);
		txtPret.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	             char c = e.getKeyChar();
	             if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_PERIOD)) ) {
	                  e.consume();
	                }
	             
	           }
	         });
		panel.add(txtPret);
		
		JLabel lblListaAngajati_1 = new JLabel("Lista produse:");
		lblListaAngajati_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListaAngajati_1.setBounds(42, 475, 120, 46);
		panel.add(lblListaAngajati_1);
		
		boxProduse = new JComboBox<String>();
		boxProduse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxProduse.setBounds(192, 488, 241, 21);
		boxProduse.setModel(new DefaultComboBoxModel(GUIAngajat.this.updateProduseBox().toArray()));
		boxProduse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					String fisierImg=GUIAngajat.this.accesNumeFisierImagine();
					BufferedImage img=null;
					try {
						img = ImageIO.read(new File(fisierImg));
						ImageIcon icon = new ImageIcon(img);
						lblImage.setIcon(icon);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					catch (NullPointerException e1) {					
					}
				    
					
				    
			}
		});
		panel.add(boxProduse);
		
		JLabel lblPretFiltru = new JLabel("Pret:");
		lblPretFiltru.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPretFiltru.setBounds(509, 157, 120, 46);
		panel.add(lblPretFiltru);
		
		boxPret = new JComboBox<String>();
		String[] filtruPret= {"fara filtru","0.00-9.99","10.00-19.99","20.00-29.99","30.00-39.99","40.00-49.99","50.00-9999.99"};
		boxPret.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxPret.setModel(new DefaultComboBoxModel(filtruPret));
		boxPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				model.addAll(GUIAngajat.this.accesListaProduse());
				list = new JList(model);

		}
		});
		boxPret.setBounds(639, 170, 241, 21);
		panel.add(boxPret);
		
		JLabel lblDisponibilitateFiltru = new JLabel("Disponibilitate:");
		lblDisponibilitateFiltru.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDisponibilitateFiltru.setBounds(509, 213, 120, 46);
		panel.add(lblDisponibilitateFiltru);
		
		String[] filtruDisponibilitate= {"fara filtru","0-9","10-19","20-29","30-39","40-49","50-9999"};
		boxDisponibilitate = new JComboBox<String>();
		boxDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		boxDisponibilitate.setModel(new DefaultComboBoxModel(filtruDisponibilitate));
		boxDisponibilitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				model.addAll(GUIAngajat.this.accesListaProduse());
				list = new JList(model);			
		}
		});
		boxDisponibilitate.setBounds(639, 226, 241, 21);
		panel.add(boxDisponibilitate);
		
		JLabel lblProduse = new JLabel("Produse");
		lblProduse.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProduse.setBounds(1104, 114, 120, 22);
		panel.add(lblProduse);
		
		JScrollPane scrollPane = new JScrollPane();
		model = new DefaultListModel<String>();
		model.addAll(this.accesListaProduse());
		list = new JList(model);
		list.setBounds(965, 146, 371, 550);
		scrollPane.setBounds(965, 146, 371, 550);
		scrollPane.setViewportView(list);
		panel.add(scrollPane);
		
		JLabel lblCautareProdus = new JLabel("Cautare produs:");
		lblCautareProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCautareProdus.setBounds(509, 269, 141, 46);
		panel.add(lblCautareProdus);
		
		txtCautareProdus = new JTextField();
		txtCautareProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCautareProdus.setColumns(10);
		txtCautareProdus.setBounds(639, 282, 241, 28);
		txtCautareProdus.getDocument().addDocumentListener((DocumentListener) new DocumentListener(){
            @Override public void insertUpdate(DocumentEvent e) { filter(); }
            @Override public void removeUpdate(DocumentEvent e) { filter(); }
            @Override public void changedUpdate(DocumentEvent e) {}
            private void filter() {
                String filter = txtCautareProdus.getText();
                filterModel((DefaultListModel<String>)list.getModel(), filter);
            }
        });
		panel.add(txtCautareProdus);
		
		btnGenerareRaportCSV = new JButton("Generare raport CSV");
		btnGenerareRaportCSV.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGenerareRaportCSV.setBounds(42, 607, 209, 28);
		btnGenerareRaportCSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAngajat.this.generareRaportCSV_click())
				{
					JOptionPane.showMessageDialog(null, "Raportul a fost generat cu succes");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eroare la generare raport","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnGenerareRaportCSV);
		
		
		
		
		JLabel lblImagineProdus = new JLabel("Imagine produs:");
		lblImagineProdus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblImagineProdus.setBounds(42, 379, 141, 37);
		panel.add(lblImagineProdus);
		
		btnAdaugaImagine = new JButton("Adauga imagine");
		btnAdaugaImagine.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdaugaImagine.setBounds(192, 386, 195, 28);
		btnAdaugaImagine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser j = new JFileChooser(caleImagini, FileSystemView.getFileSystemView());
				j.showSaveDialog(null);
				File f2=j.getSelectedFile().getAbsoluteFile();
				boolean check=true;
				try {
					
					while(check)
					{
					BufferedImage img = ImageIO.read(f2);
					int width = img.getWidth();
					int height= img.getHeight();
					if(width > 360 || height > 270)
					{
						JOptionPane.showMessageDialog(null, "Rezolutia imaginii este "+width+"x"+height+", rezolutie maxima: 360x270","Error", JOptionPane.ERROR_MESSAGE);
						j = new JFileChooser(caleImagini, FileSystemView.getFileSystemView());
						j.showSaveDialog(null);
						f2=j.getSelectedFile().getAbsoluteFile();
					}
					else
						check=false;
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Eroare la citirea fisierului","Error", JOptionPane.ERROR_MESSAGE);
				}
				numeFisierImagine=f2.toString();
		}
		});
		panel.add(btnAdaugaImagine);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIAngajat.this.logout_Click();
				GUIAngajat.this.dispose();
			}
		});
		getContentPane().add(btnLogOut, BorderLayout.NORTH);		
		BufferedImage img=null;
		try {
			img = ImageIO.read(new File(GUIAngajat.this.accesNumeFisierImagine()));
			ImageIcon icon = new ImageIcon(img);		
		    lblImage.setIcon(icon);
		    
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2)
		{
			
		}
		
	    
		lblImage.setBounds(481, 408, 360, 270);
		panel.add(lblImage);
		
		btnStatisticaProducator = new JButton("Statistica producator");
		btnStatisticaProducator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStatisticaProducator.setBounds(714, 332, 229, 28);
		btnStatisticaProducator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,Long> statistica=GUIAngajat.this.statisticaProducatori_click();
				DefaultPieDataset pie = new DefaultPieDataset();
				statistica.forEach( (nume,valoare)->pie.setValue(nume, valoare));
				JFreeChart chart= ChartFactory.createPieChart("Statistica producatori", pie, true, true, true);
			    ChartFrame frame2= new ChartFrame("Statistica producatori",chart);
			    frame2.setVisible(true);
			    frame2.setLocationRelativeTo(GUIAngajat.this.boxPret);
			    frame2.setSize(500,500);
			}
		});
		panel.add(btnStatisticaProducator);
		
		btnStatisticaPret = new JButton("Statistica pret");
		btnStatisticaPret.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStatisticaPret.setBounds(509, 332, 195, 28);
		btnStatisticaPret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,Long> statistica=GUIAngajat.this.statisticaPret_click();
				DefaultPieDataset pie = new DefaultPieDataset();
				statistica.forEach( (nume,valoare)->pie.setValue(nume, valoare));
				JFreeChart chart= ChartFactory.createPieChart("Statistica pret", pie, true, true, true);
			    ChartFrame frame2= new ChartFrame("Statistica pret",chart);
			    frame2.setVisible(true);
			    frame2.setLocationRelativeTo(GUIAngajat.this.boxPret);
			    frame2.setSize(500,500);
			}
		});
		panel.add(btnStatisticaPret);
		
		btnStatisticaDisponibilitate = new JButton("Statistica disponibilitate");
		btnStatisticaDisponibilitate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStatisticaDisponibilitate.setBounds(509, 370, 229, 28);
		btnStatisticaDisponibilitate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map<String,Long> statistica=GUIAngajat.this.statisticaDisponibilitate_click();
				DefaultPieDataset pie = new DefaultPieDataset();
				statistica.forEach( (nume,valoare)->pie.setValue(nume, valoare));
				JFreeChart chart= ChartFactory.createPieChart("Statistica disponibilitate", pie, true, true, true);
			    ChartFrame frame2= new ChartFrame("Statistica disponibilitate",chart);
			    frame2.setVisible(true);
			    frame2.setLocationRelativeTo(GUIAngajat.this.boxPret);
			    frame2.setSize(500,500);
			}
		});
		panel.add(btnStatisticaDisponibilitate);
		
		btnGenerareRaportJson = new JButton("Generare raport Json");
		btnGenerareRaportJson.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGenerareRaportJson.setBounds(42, 645, 209, 28);
		btnGenerareRaportJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GUIAngajat.this.generareRaportJson_click())
				{
					JOptionPane.showMessageDialog(null, "Raportul a fost generat cu succes");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Eroare la generare raport","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnGenerareRaportJson);
		
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void logout_Click()
	{
		PAngajat angajat = new PAngajat(this);
		angajat.Logout();
	}
	

	@Override
	public String numeText() {
		return this.txtNumeProdus.getText();
		
	}

	@Override
	public String producatorText() {
		
		return this.txtNumeProducator.getText();
	}

	@Override
	public String pretText() {
		
		return this.txtPret.getText();
	}

	@Override
	public String disponibilitateText() {
	
		return this.txtDisponibilitate.getText();
	}
	public String numeMagazinText()
	{
		return GUIAutentificare.lblMagazin.getText();
	}
	
	public String boxProdusText()
	{
		return this.boxProduse.getSelectedItem().toString();
	}
	
	public String boxPretText()
	{
		return this.boxPret.getSelectedItem().toString();
	}
	
	public String boxProducatorText()
	{
		return this.boxProducator.getSelectedItem().toString();
	}
	
	public String boxDisponibilitateText()
	{
		return this.boxDisponibilitate.getSelectedItem().toString();
	}
	
	
	public String adaugaImagineText()
	{
		return this.numeFisierImagine;
	}
	
	private boolean generareRaportCSV_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.generareRaportCSV();
	}
	
	private boolean generareRaportJson_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.generareRaportJson();
	}
	
	private Map<String,Long> statisticaProducatori_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.statisticaProducatori();
	}
	
	private Map<String,Long> statisticaDisponibilitate_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.statisticaDisponibilitate();
	}
	
	private Map<String,Long> statisticaPret_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.statisticaPret();
	}
	
	private boolean adaugareProdus_click()
	{
		PAngajat pang= new PAngajat(this);
		return pang.adaugare();
	}
	private boolean stergereProdus_click()
	{
		PAngajat pang = new PAngajat(this);
		return pang.stergere();
	}
	
	private boolean actualizareProdus_click()
	{
		PAngajat pang = new PAngajat(this);
		return pang.actualizare();
	}
	
	private ArrayList<String> accesListaProducatori()
	{
		PAngajat pang = new PAngajat(this);
		return pang.accesListaProducatori();
	}
	
	private String accesNumeFisierImagine()
	{
		PAngajat pang = new PAngajat(this);
		return pang.accesImagine();
	}
	
	private ArrayList<String> updateProduseBox()
	{
		PAngajat ang = new PAngajat(this);
		ArrayList<String> listaProduse = ang.accesListaProduse();
		if(listaProduse.size()==0)
		{
			listaProduse.add("");
		}
		return listaProduse;
	}
	
	private ArrayList<String> accesListaProduse()
	{
		PAngajat ang = new PAngajat(this);
		ArrayList<String> listaProduse = ang.accesListaProduseListare();
		return listaProduse;
	}
	private void clearTextFields()
	{
		this.txtNumeProducator.setText("");
		this.txtNumeProdus.setText("");
		this.txtDisponibilitate.setText("");
		this.txtPret.setText("");
	}
	private void filterModel(DefaultListModel<String> model, String filter) {
	    for (String s : this.accesListaProduse()) {
	        if (!s.contains(filter)) {
	            if (model.contains(s)) {
	                model.removeElement(s);
	            }
	        } else {
	            if (!model.contains(s)) {
	                model.addElement(s);
	            }
	        }
	    }
	}
}
