import java.io.IOException;
import java.util.ArrayList;

public class Clients {
	
	// ------------------ VARIABLE ----------------- // 
	
	static ArrayList<Client> mesClients = new ArrayList<Client>();
	
	
		/*  -----------------------  AJOUTER (préalablement) UN CLIENT  ---------------  */
		
		static public void ajouterClient(Client c){
			if (c!=null){
				if (!existeClient(c)){
					Clients.mesClients.add(c);
				}
			}
			
		}
		
		/* -------------------------  SUPPRIMER UN CLIENT  ----------------------------------- */
		
		 static void supprimerClient() throws IOException {
				System.out.println("Entrez le nom du client");
				
				int i=0;
				boolean trouve = false;
				String cli = Saisir.chaine();
				Client cl;
					while (i<Clients.mesClients.size() && !trouve){
						if(cli.equals(Clients.mesClients.get(i).getNom())){
							trouve=true;
							cl=Clients.mesClients.get(i);
							Clients.mesClients.remove(cl);
							System.out.println("le client a bien été effacé !");
						}	
						i++;
					}
					if (!trouve){
						System.out.println("Ce client n'existe pas");
					}
			
		}
		
		
		/*  ------  Algo de recherche pour remplacer "mesClients.contains(c)"  --------  */

		static public boolean existeClient(Client c){
			int i = 0;
			boolean trouve = false;
			if (c != null){
				while (i<mesClients.size() && !trouve){
					if(c.getNom().equals(mesClients.get(i).getNom())){
						trouve=true;
					}
					i++;
				}
			}
			return trouve;
		}
		
		// Rechercher = booléen  |   Trouver = type de l'objet ("client" ici)
		/*  --------  Rechercher si un client existe ou pas en fonction du nom  --------  */

		static public boolean existeClientNom(String nom){
			int i = 0;
			boolean trouve = false;
			while (i<mesClients.size() && !trouve){
				if(nom.equals(mesClients.get(i).getNom())){
					trouve=true;
				}
				i++;
			}
			return trouve;
		}
		

		
		
		/*  ----------------------- CREER UN CLIENT ---------------------------------------  */
		
		static public void creerUnClient() throws IOException{
			Client c;
			System.out.println("Entrez le nom du client : ");
			String nom = Saisir.chaine();
				if (!Clients.existeClientNom(nom)){
					c = new Client(nom);
					Clients.ajouterClient(c);
					System.out.println("Le client a été créé");
				}else{
					System.out.println("Ce client existe déjà!");
				}
		}
		
		

		
		
		/* -----------------------  LISTER CLIENTS DU TABLEAU ---------------------------  */
		
		static void listerClients(){
			for(int i=0;i<mesClients.size();i++){
				System.out.println(mesClients.get(i).toString());
			}
		}
		
		
		
		/*  ---------------  Trouver un client en fonction du nom  ---------------  */

		static public Client trouverClientNom(String r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getNom()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
		/*  ---------------  Trouver un client en fonction du prénom  ---------------  */

		static public Client trouverClientPrenom(String r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getPrenom()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
		/*  ---------------  Trouver un client en fonction de l'adresse  ---------------  */

		static public Client trouverClientAdresse(String r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getAdresse()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
		/*  ---------------  Trouver un client en fonction du téléphone  ---------------  */

		static public Client trouverClientTel(String r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getTel()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
		/*  ---------------  Trouver un client en fonction de la civilité  ---------------  */

		static public Client trouverClientCivilite(String r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getCivilite()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
		/*  ---------------  Trouver un client en fonction de la reference  ---------------  */

		static public Client trouverClientRef(int r){
			int i = 0;
			boolean trouve = false;
			Client cli = null;
			while (i<mesClients.size() && !trouve){
				if(r==mesClients.get(i).getMaReference()){
					trouve=true;
					cli=mesClients.get(i);
				}
				i++;
			}
			return cli;
		}
		
}