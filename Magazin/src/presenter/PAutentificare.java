package presenter;

import javax.swing.JOptionPane;

import model.AngajatPersistenta;
import view.GUIAdmin;
import view.GUIAngajat;
import view.GUIAutentificare;
import view.IAutentificareView;

public class PAutentificare {
	private IAutentificareView autentificare;
	

	public PAutentificare(GUIAutentificare autentificare) {
		this.autentificare = autentificare;
	}

	public boolean Autentificare() {
		AngajatPersistenta angajatP = new AngajatPersistenta();
		String username = autentificare.usernameText();
		String parola = autentificare.parolaText();
		String magazin = autentificare.boxMagazinSelected();
		if (username.contentEquals("admin") && parola.contentEquals("admin")) {

			new GUIAdmin();
			return true;
		} else {
			if (magazin.contentEquals("")) {
				JOptionPane.showMessageDialog(null, "Eroare la autentificare, slectati un magazin", "Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
			if (angajatP.accesDateAutentificare(username, parola, magazin)) {
				new GUIAngajat();
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Eroare la autentificare, username sau parola gresita","Error", JOptionPane.ERROR_MESSAGE);
				return false;

			}
		}
	}
}
