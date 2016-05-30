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
	static private int ref = 0;
	private ArrayList<Vente1> mesAchat = new ArrayList<Vente1>();

	// ------------------- CONSTRUCTEUR n1 --------------------------- //

	public Client(String nom, String prenom, String adresse, String tel, String civilite){
	
		Client.ref++;
		this.maReference = ref;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.civilite = civilite;
	}

	/*
	 * ------------------------ GETTERS ET SETTERS -----------------------------
	 */

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

	/*
	 * ------------------------ TO STRING -------------------------------------
	 */

	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", civilite="
				+ civilite + ", maReference=" + maReference + "]";
	}

	/*
	 * ------------------------ AJOUTER VENTE ---------------------------------
	 */

	public void ajoutVente(Vente1 v) {
		if (v != null) {
			this.mesAchat.add(v);
		}
	}

	public void ajouterVente(Vente1 v) {
		if (v != null) {
			int i = 0;
			boolean trouve = false;
			while (i < this.mesAchat.size() && !trouve) {
				if (v.getRef() != this.mesAchat.get(i).getRef()) {
					trouve = true;
					this.ajoutVente(v);
				}
			}
		}
	}

	public void listerVente() {
		for (int i = 0; i < this.mesAchat.size(); i++) {
			System.out.println(this.mesAchat.get(i).toString());
		}
	}

	/*
	 * public static void main (String[] args) throws IOException {
	 * 
	 * Client c = new Client(); }
	 */
}
