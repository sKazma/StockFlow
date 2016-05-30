import java.io.IOException;
import java.util.ArrayList;

public class Stock {
	static public ArrayList<Article> mesArticle = new ArrayList<Article>();

	public Stock(){

	}


	//-----------------------Creer un Article---------------------------
	
	//TODO Dépendance avec la classe saisir
//		static public void creerUnArticle() throws IOException{
//				Article a;
//				System.out.println("Entrez le nom de L'article");
//				String nom = Saisir.chaine();
//					if (!Stock.existeArticleNom(nom)){
//						a = new Article(nom);
//						Stock.ajouterArticle(a);						
//						System.out.println("L'article a �t� cr�� ");
//					}else{
//						System.out.println("Cette article existe deja!");
//					}
//			}





  /*---------------Ajouter Article---------------*/



	static public void ajoutArticle(Article a){
		if (a!=null){
			mesArticle.add(a);
		}
	}

	/*------------------enlever article--------------------*/

	static public void enleveArticle(Article a){
		if (a!=null){
			mesArticle.remove(a);
		}
	}

	//ajouter un article

	static public void ajouterArticle(Article a){
		if (!existeArticle(a)){
			ajoutArticle(a);
		}else{
			System.out.println("le Stock contient deja cette article combien voulez vous en rajouter");
			try {
				a.ajouterQuantiteStock(Saisir.entier());
			} catch (IOException e){
				System.out.println("Vous devez entrer un Nombre svp !!! c'est pas compliquer");
				ajouterArticle(a);
			}
		}
	}


	// enlever un article

	static public void enleverArticle(Article a){
		if (a!=null){
			if(existeArticle(a)){
				enleveArticle(a);
			}else{
				System.out.println("Le Stock ne contient pas cette d'article");
			}
		}
	}


	/**----------algo de recherche pour remplacer le mesArticle.contains(a) -----------*/

	static public boolean existeArticle(Article a){
		int i=0;
		boolean trouve = false;
		if (a!=null){
			while (i<mesArticle.size() && !trouve){
				if(a.getNom().equals(mesArticle.get(i).getNom())){
					trouve=true;
				}
				i++;
			}
		}
		return trouve;
	}

	/*---------------Algo de recherche qui retourne un article ------------*/
	static public Article rechercherArticle2(Article a){
		int i=0;
		boolean trouve = false;
		Article art;
		art=null;
		while (i<mesArticle.size() && !trouve){
			if(a.equals(mesArticle.get(i))){
				trouve=true;
				art=mesArticle.get(i);
			}
			i++;
		}
		return art;
	}
	/**----------rechercher si un article existe ou pas en focntion du nom -----------*/

	static public boolean existeArticleNom(String nom){
		int i=0;
		boolean trouve = false;
		while (i<mesArticle.size() && !trouve){
			if(nom.equals(mesArticle.get(i).getNom())){
				trouve=true;
			}
			i++;
		}
		return trouve;
	}

	/* ----------trouver un article en fonction du nom ------------*/


	static public Article trouverArticleNom(String nom){
		int i=0;
		boolean trouve = false;
		Article art=null;
		while (i<mesArticle.size() && !trouve){
			if(nom.equals(mesArticle.get(i).getNom())){
				trouve=true;
				art=mesArticle.get(i);
			}
			i++;
		}
		return art;
	}

	/**---------------Trouver un article en fonction de la reference-----------------*/

	static public Article trouverArticleRef(int r){
		int i=0;
		boolean trouve = false;
		Article art=null;
		while (i<mesArticle.size() && !trouve){
			if(r==mesArticle.get(i).getReference()){
				trouve=true;
				art=mesArticle.get(i);
			}
			i++;
		}
		return art;
	}

	//---------------------Verification de la quantite dans le stock d'article correspondant : EXEMPLE si on veut acheter 140 pomme alors qu'il y en a que 100 c'est inpossible --------------

	static public boolean verificationQuantite(String nom, int nombre){
		boolean trouve=false;
		if(Stock.existeArticleNom(nom)){
			Article art= Stock.trouverArticleNom(nom);
			if(art.getQuantite()-nombre>=0){
				trouve=true;
			}else{
				System.out.println("il n'y a pas assez de "+nom+" dans le stock");
			}
		}
		return trouve;
	}



	/*** ---------tu sais lire !!! ------------*/
	static public void listerArticle(){
		for(int i=0;i<mesArticle.size();i++){
			System.out.println(mesArticle.get(i));
		}
	}
	
	//---------------Trouver la reference ------------------
	
	static public int trouverReference(){
		int x=0;
		for(int i=0;i<mesArticle.size();i++){
			if(mesArticle.get(i).getReference()>x){
				x=mesArticle.get(i).getReference();
			}
		}
		return x;
	}
	
	public class Erreur extends Exception{
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public Erreur(){
			super();
		}
	}
	
	



}
