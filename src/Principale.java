import java.io.IOException;

public class Principale {

	public static void main(String[] args) throws IOException {
		
//		Article ps4=new Article("ps4","Console",10,270,300);
//		Article xbox=new Article("xbox","Console",18,350,400);
//		Article pomme=new Article("pomme","fruit",124,3,7);
//		Article ordinateur=new Article("ordinateur","ordinateur",5,750,1000);
//		Article iphone=new Article("iphone","telephone",50,600,620);
////		
//		Stock.ajouterArticle(ps4);
//		Stock.ajouterArticle(xbox);
//		Stock.ajouterArticle(pomme);
//		Stock.ajouterArticle(ordinateur);
//		Stock.ajouterArticle(iphone);
//		
		System.out.println("Les articles ps4,xbox,pomme,ordinateur et iphone ont été créé");
		
		/* ---------------------------- MENU PRINCIPAL --------------------------------- */
		
		System.out.println("Choissez dans le Menu suivant");
		System.out.println("1 -- Article");
		System.out.println("2 -- Client ");
		System.out.println("3 -- Ventes ");
		System.out.println("0 -- Quitter ");
		
		int choix2,choix = Saisir.entier();
			choix2=0;
			
		while(choix!=0){
		
		switch (choix){
		
		/* ------------------------- ARTICLE SWITCH --------------------------- */
		
		case 1 :
			System.out.println("Choissez dans le Menu suivant");
			System.out.println("1 -- Creer un article");
			System.out.println("2 -- Augmenter la quantité d'un article");
			System.out.println("3 -- Diminuer la quantité d'un article");
			System.out.println("4 -- Supprimer un article");
			System.out.println("5 -- Lister Article");
			
			choix2= Saisir.entier();
			
			switch (choix2) {
			
			// ---------------------- CREER UN ARTICLE ----------------------------
			
//			case 1 : Article a = new Article();
//				Stock.ajouterArticle(a);
//			break;
//			
			// ---------------------- AUGMENTER LA QTE  ---------------------------
			
			case 2 : 
				System.out.println("Entrez le nom de L'article");
				
				int i=0;
				boolean trouve = false;
				String art=Saisir.chaine();
				while (i<Stock.mesArticle.size() && !trouve){
					if(art.equals(Stock.mesArticle.get(i).getNom())){
						trouve=true;
						System.out.println("Combien d'article voulez-vous ajouter");
						Stock.mesArticle.get(i).ajouterQuantiteStock(Saisir.entier());
					}	
					i++;
				}
				if (!trouve){
					System.out.println("Cet aritlce n'existe pas");
				}
				break;
				
			// ---------------------- DIMINUER LA QTE ---------------------------
				
			case 3 : 
				System.out.println("Entrez le nom de L'article");
				
				i=0;
				trouve = false;
				art=Saisir.chaine();
				while (i<Stock.mesArticle.size() && !trouve){
					if(art.equals(Stock.mesArticle.get(i).getNom())){
						trouve=true;
						System.out.println("Combien d'article voulez-vous enlever");
						Stock.mesArticle.get(i).enleverQuantite(Saisir.entier());
					}	
					i++;
				}
				if (!trouve){
					System.out.println("Cet aritlce n'existe pas");
				}
				break;
				
				// ---------------------- SUPPRIMER UN ARTICLE -----------------
				
			case 4 : 
				System.out.println("Entrez le nom de L'article");
				
				i=0;
				trouve = false;
				art=Saisir.chaine();
				Article at;
				while (i<Stock.mesArticle.size() && !trouve){
					if(art.equals(Stock.mesArticle.get(i).getNom())){
						trouve=true;
						at=Stock.mesArticle.get(i);
						Stock.enleverArticle(at);
					}	
					i++;
				}
				if (!trouve){
					System.out.println("Cet aritlce n'existe pas");
				}
				break;
				
				// ---------------------- LISTER LES ARTICLES -----------------
				
			case 5 : Stock.listerArticle();
				break;
			default : System.out.println("Erreur !! ");
			break;
			}
			
		break;
		
		/* ------------------------- CLIENT SWITCH --------------------------- */
		
		case 2 : 
			System.out.println("Choissez dans le Menu suivant");
			System.out.println("1 -- Creer un Client");
			System.out.println("2 -- Modifier information sur un client");
			System.out.println("3 -- Supprimer un client");
			System.out.println("4 -- Lister client");
			
			choix2= Saisir.entier();
			
			switch (choix2){
			
			// ---------------------- CREER UN CLIENT --------------------
			
			case 1 :
				//Client c = new Client();
				//Clients.ajouterClient(c);
				Clients.creerUnClient();
			break;
			
			// ---------------------- SUPPRIMER UN CLIENT -----------------
			
			case 3 : Clients.supprimerClient();
			break;
				
			// ---------------------- LISTER LES CLIENTS -----------------
				
			case 4 : Clients.listerClients();
				break;
			default : System.out.println("Erreur !! ");
			break;
			}
			
			break;
		
		/* ------------------------- VENTES SWITCH --------------------------- */
			
		case 3 : 
				System.out.println("Choissez dans le Menu suivant");
				System.out.println("1 -- Effectuer une vente");
				System.out.println("2 -- Lister les ventes");
				choix2= Saisir.entier();
				
			break;
			default : System.out.println("Erreur");
		}
	}
}
}