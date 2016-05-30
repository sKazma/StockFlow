import java.util.ArrayList;

public class Clients {

	// ------------------ VARIABLE ----------------- //

	static ArrayList<Client> mesClients = new ArrayList<Client>();

	/*
	 * ----------------------- AJOUTER (pr�alablement) UN CLIENT ---------------
	 */

	static public void ajoutClient(Client c) {
		if (c != null) {
			Clients.mesClients.add(c);
		}
	}

	/*
	 * ----------------------- ENLEVER (pr�alablement) UN CLIENT
	 * ----------------
	 */

	static public void enleveClient(Client c) {
		if (c != null) {
			Clients.mesClients.remove(c);
		}
	}

	/*
	 * ------ Algo de recherche pour remplacer "mesClients.contains(a)" --------
	 */

	static public boolean existeClient(Client c) {
		int i = 0;
		boolean trouve = false;
		if (c != null) {
			while (i < mesClients.size() && !trouve) {
				if (c.getNom().equals(mesClients.get(i).getNom())) {
					trouve = true;
				}
				i++;
			}
		}
		return trouve;
	}

	// Rechercher = bool�en | Trouver = type de l'objet ("client" ici)
	/*
	 * -------- Rechercher si un client existe ou pas en fonction du nom
	 * --------
	 */

	static public boolean existeClientNom(String nom) {
		int i = 0;
		boolean trouve = false;
		while (i < mesClients.size() && !trouve) {
			if (nom.equals(mesClients.get(i).getNom())) {
				trouve = true;
			}
			i++;
		}
		return trouve;
	}

	/*
	 * ------------------------ AJOUTER UN CLIENT
	 * ----------------------------------
	 */

	static public void ajouterClient(Client c) {
		if (!existeClient(c)) {
			ajoutClient(c);
		}
	}

	/*
	 * ----------------------- CREER UN CLIENT
	 * ---------------------------------------
	 */
	// TODO A revoir ca dépendance avec la class Saisir

	// static public void creerUnClient() throws IOException{
	// Client c;
	// System.out.println("Entrez le nom du client");
	// String nom = Saisir.chaine();
	// if (!Clients.existeClientNom(nom)){
	// c = new Client(nom);
	// Clients.ajouterClient(c);
	// System.out.println("Le client a �t� cr��");
	// }else{
	// System.out.println("Ce client existe d�j�!");
	// }
	// }
	//

	/*
	 * ------------------------ ENLEVER CLIENT AU TABLEAU
	 * ---------------------------
	 */

	static public void enleverClient(Client c) {
		if (c != null) {
			if (existeClient(c)) {
				enleveClient(c);
			} else {
				System.out
						.println("Il n'existe pas de client poss�dant un tel nom affreusement horrible !! beeuuuhh !!");
			}
		}
	}

	/*
	 * ----------------------- LISTER CLIENTS DU TABLEAU
	 * ---------------------------
	 */

	static void listerClients() {
		for (int i = 0; i < mesClients.size(); i++) {
			System.out.println(mesClients.get(i).toString());
		}
	}

	/*
	 * ---------------------- RECHERCHER CLIENT DANS LE TABLEAU
	 * ---------------------
	 */

	static Client rechercheClient(String c) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && trouve) {
			if (c.equals(mesClients.get(i).getNom())) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;

	}

	/* --------------- Trouver un client en fonction du nom --------------- */

	static public Client trouverClientNom(String r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getNom()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

	/*
	 * --------------- Trouver un client en fonction du pr�nom ---------------
	 */

	static public Client trouverClientPrenom(String r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getPrenom()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

	/*
	 * --------------- Trouver un client en fonction de l'adresse
	 * ---------------
	 */

	static public Client trouverClientAdresse(String r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getAdresse()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

	/*
	 * --------------- Trouver un client en fonction du t�l�phone
	 * ---------------
	 */

	static public Client trouverClientTel(String r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getTel()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

	/*
	 * --------------- Trouver un client en fonction de la civilit�
	 * ---------------
	 */

	static public Client trouverClientCivilite(String r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getCivilite()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

	/*
	 * --------------- Trouver un client en fonction de la reference
	 * ---------------
	 */

	static public Client trouverClientRef(int r) {
		int i = 0;
		boolean trouve = false;
		Client cli = null;
		while (i < mesClients.size() && !trouve) {
			if (r == mesClients.get(i).getMaReference()) {
				trouve = true;
				cli = mesClients.get(i);
			}
			i++;
		}
		return cli;
	}

}