import java.io.IOException;
import java.util.ArrayList;

public class Client {
	
	// --------- VARIABLES (classe et instance) --------- //
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private String civilite;
	private int maReference;
	static private int ref;
	
	// ------------------- CONSTRUCTEUR n1 --------------------------- //
	
	public Client(String nom, String prenom, String adresse, String tel, String civilite) throws IOException {	
		Client.ref++;
		this.maReference = ref;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;		
		this.civilite = civilite;
		ArrayList<Vente1> mesAchat = new ArrayList<Vente1>();
	}
	
	// ------------------- CONSTRUCTEUR n2 --------------------------- //
	
	public Client() throws IOException {
		Client.ref++;
		this.maReference = ref;
		System.out.println("Entrez le nom du client");
		this.nom = Saisir.chaine();
		System.out.println("Entrez le prénom du client");
		this.prenom = Saisir.chaine();
		System.out.println("Entrez l'adresse de ce client");
		this.adresse = Saisir.chaine();	
		System.out.println("Quel le numéro de téléphone de ce client ?");
		this.tel = Saisir.chaine();
		System.out.println("Quel est sa civilité (homme/femme) ?");
		this.civilite = Saisir.chaine();
	//	}
	}
	
	// ------------------- CONSTRUCTEUR n3 --------------------------- //
	
	public Client(String nom) throws IOException{ // TOUS LES "SYSTEMS OUT" SERONT A ENLEVER POUR L'IHM !!!
		this.nom = nom;
		Client.ref++;
		this.maReference = ref;
		System.out.println("Entrez le prénom du client : ");
		this.prenom = Saisir.chaine();
		System.out.println("Entrez son l'adresse : ");
		this.adresse = Saisir.chaine();
		System.out.println("Entrez le numéro de téléphone : ");
		this.tel = Saisir.chaine();		
		System.out.println("Entrez sa civilité (homme/femme) : ");
		this.civilite = Saisir.chaine();
		
	}
	
	/*  ------------------------ GETTERS ET SETTERS   -----------------------------  */
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public int getMaReference() {
		return maReference;
	}

	public void setMaReference(int maReference) {
		this.maReference = maReference;
	}
	

	/*  ------------------------ TO STRING --------------------------------------------  */

	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", civilite="
				+ civilite + ", maReference=" + maReference + "]";
	}
	
	/*  ------------------------ AJOUTER / LISTER LES ACHATS D'UN CLIENT  --------------------------------- */
	
	
	/*public static void main (String[] args) throws IOException {
		
		Client c = new Client();
	}*/
}
