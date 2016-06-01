import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class StockW extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4, home;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Gestion du stock";
	private String labels[] = { "Stock", "Clients", "Ventes", "Paramètres" };

	// composants propres au menu principal
	private JPanel line1, line2, line3;
	private JButton add, modify, search, remove;
	private JLabel searchtitle;
	private JTextField tfsearch;
	private JTable listeArticles;
	private String[] entete = { "Référence", "Désignation", "Quantité en stock", "Prix d'achat", "Prix de vente"};
	private DefaultTableModel tableModel = new DefaultTableModel(entete,0);		
	//composant confirmer annuler
	private JDialog confirmer;
	private JLabel attention;
	private JButton oui,non;
	private JPanel central2;
	// composants en rapport avec la JDialog
			private JDialog nvArticle;
			private JPanel central;
			private TextPrompt gnom, gquantite, gprixA, gprixV, greference;
			private JTextField tfnom, tfquantite, tfprixA, tfprixV, tfreference;
			private JButton valider, annuler;
			private JLabel erreur;
	
	// constructeur

	public StockW(String t) {
		this.setTitle(t);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWindow();
	}
//ajout article au JTable
	public void ajouerLesArticle(){
		listeArticles = new JTable(this.tableModel);
		this.tableModel.setRowCount(0);
		listeArticles.setModel(tableModel);
		for(int i = 0 ; i < Stock.mesArticle.size();i++){
			Object[] donnees={Stock.mesArticle.get(i).getReference(), Stock.mesArticle.get(i).getNom(), Stock.mesArticle.get(i).getQuantite(), Stock.mesArticle.get(i).getPrixA(), Stock.mesArticle.get(i).getPrixV()};
			this.tableModel.addRow(donnees);
		}
	}
	public void createWindow() {
		// creation du tableau d'article
		
		this.ajouerLesArticle();

		// Contener principal
		main = new JPanel();
		this.setContentPane(main);
		main.setLayout(new BorderLayout());

		// Panel du haut
		top = new JPanel();
		title = new JLabel(titlewindow);
		welcome = new JLabel("Bonjour, " + Logiciel.getName());
		try {
			piclabel = new JLabel(new ImageIcon(Logiciel.getLogo().getScaledInstance(100, 100, 300)));
		} catch (Exception e) {
			piclabel = new JLabel("Erreur Logo");
		}
		top.setLayout(new BoxLayout(top, BoxLayout.LINE_AXIS));
		top.setBackground(Color.decode("#E5E5E5"));
		title.setForeground(Color.decode("#E56302"));
		title.setFont(new Font("Calibri", Font.PLAIN, 20));
		welcome.setFont(new Font("Calibri", Font.PLAIN, 20));
		welcome.setForeground(Color.decode("#E56302"));

		// Ajout des composants au panel
		top.add(Box.createRigidArea(new Dimension(50, 100)));
		top.add(title);
		top.add(Box.createHorizontalGlue());
		top.add(welcome);
		top.add(Box.createRigidArea(new Dimension(40, 100)));
		top.add(piclabel);
		top.add(Box.createRigidArea(new Dimension(60, 100)));

		// Panel de Gauche
		left = new JPanel();
		left.setLayout(new GridLayout(6, 1));
		left.setPreferredSize(new Dimension(150, 0));
		left.setBackground(Color.decode("#E5E5E5"));
		home = new JButton("<-- Accueil");
		btnleft1 = new JButton("> " + labels[0] + " <");
		btnleft1.setBackground(Color.ORANGE);
		btnleft1.setForeground(Color.BLACK);
		btnleft1.setOpaque(true);
		btnleft2 = new JButton(labels[1]);
		btnleft3 = new JButton(labels[2]);
		btnleft4 = new JButton(labels[3]);

		// Listeners des boutons de gauche
		btnleft4.addActionListener(new OpenParametres());
		btnleft3.addActionListener(new OpenVentes());
		btnleft2.addActionListener(new OpenClients());
		home.addActionListener(new BackHome());

		// Ajout des composants au Panel
		left.add(home);
		left.add(btnleft1);
		left.add(btnleft2);
		left.add(btnleft3);
		left.add(btnleft4);

		// Panel central
		center = new JPanel();
		line1 = new JPanel();
		line2 = new JPanel();
		line3 = new JPanel();
		
		searchtitle = new JLabel("Rechercher un article : ");
		tfsearch = new JTextField("Entrez un nom ou une référence");
		add = new JButton ("Créer un article...");
		remove = new JButton ("Supprimer...");
		modify = new JButton ("Modifer...");
		modify.setEnabled(false);
		remove.setEnabled(false);
		search = new JButton ("Rechercher");
		listeArticles.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		listeArticles.getSelectionModel().addListSelectionListener(new selectionliste());
		
		//Première ligne de "center"
		line1.add(add);
		add.addActionListener(new NewArticle());
		line1.add(modify);
		line1.add(remove);
		
		//Deuxième ligne de "center"
		line2.add(searchtitle);
		line2.add(tfsearch);
		line2.add(search);
		
		//troisième ligne de "center"
		line3.add(new JScrollPane(listeArticles));
		
		center.setLayout(new GridLayout(3, 1, 10, 10));
		center.setBorder(new EmptyBorder(10, 10, 10, 10));
		center.add(line1);
		center.add(line2);
		center.add(line3);
		

		// Ajout des panel au panel principal
		main.add(top, BorderLayout.NORTH);
		main.add(left, BorderLayout.WEST);
		main.add(center, BorderLayout.CENTER);

	}

//	public void construireArticle() {
//		JPanel nvArticle = new JPanel();
//
//		this.nomA = new JLabel();
//		this.ref = new JLabel();
//		this.quantite = new JTextField();
//		this.prixA = new JTextField();
//		this.prixV = new JTextField();
//
//		if (!Stock.existeArticleNom(rechercher.getText())) {
//			this.ref.setText(Integer.toString(Stock.mesArticle.size()));
//			this.quantite.setText("Quantit� : ");
//			this.prixA.setText("Prix d'achat");
//			this.prixV.setText("Prix de vente");
//		} else {
//			this.nomA.setText(rechercher.getText());
//			this.ref.setText(Integer.toString(Stock.trouverArticleNom(rechercher.getText()).getReference()));
//			this.quantite.setText(
//					"Quantit� : " + Integer.toString(Stock.trouverArticleNom(rechercher.getText()).getQuantite()));
//			this.prixA
//					.setText("Prix d'achat" + Float.toString(Stock.trouverArticleNom(rechercher.getText()).getPrixA()));
//			this.prixV.setText(
//					"Prix de vente" + Float.toString(Stock.trouverArticleNom(rechercher.getText()).getPrixV()));
//		}
//		center.removeAll();
//		center.setLayout(new FlowLayout());
//
//		nvArticle.setLayout(new BoxLayout(nvArticle, BoxLayout.Y_AXIS));
//		nvArticle.add(nomA);
//		nvArticle.add(Box.createVerticalBox());
//		nvArticle.add(ref);
//		nvArticle.add(Box.createVerticalBox());
//		nvArticle.add(quantite);
//		nvArticle.add(Box.createVerticalBox());
//		nvArticle.add(prixA);
//		nvArticle.add(Box.createVerticalBox());
//		nvArticle.add(prixV);
//
//		JPanel actionBouton = new JPanel();
//		actionBouton.setLayout(new GridLayout(3, 1));
//		this.supprimer = new JButton("Supprimer Article");
//		this.enregistrer = new JButton("Enregistrer modification");
//		this.annulerModif = new JButton("Annuler les modification");
//
//		actionBouton.add(supprimer);
//		actionBouton.add(enregistrer);
//		actionBouton.add(annulerModif);
//
//		center.add(nvArticle);
//		center.add(actionBouton);
//
//	}

	
	// boite de dialogue pour confirmation 
	
	public void confirmerAnnuler(String message){
		this.confirmer = new JDialog(Logiciel.getFen7(), "Attention");
		this.confirmer.setSize(400,130);
		this.confirmer.setLocationRelativeTo(null);
		this.confirmer.setContentPane(central2 = new JPanel());
		
		// message de confirmation
		
		this.attention = new JLabel(message);
		this.oui = new JButton("OUI");
		oui.addActionListener(new confirmerOui());
		this.non = new JButton("NON");
		non.addActionListener(new confirmerNon());
		confirmer.add(attention);
		confirmer.add(oui);
		confirmer.add(non);
		confirmer.setVisible(true);
	}
	//Nouvel article
	
	public void nouveauClient() {
		nvArticle = new JDialog(Logiciel.getFen7(), "StockFlow - Nouvel Article");
		nvArticle.setSize(450, 200);
		nvArticle.setLocationRelativeTo(null);
		nvArticle.setContentPane(central = new JPanel());
		

		// référence
		tfreference = new JTextField();
		tfreference.setColumns(6);
		greference = new TextPrompt(Integer.toString(Stock.mesArticle.size()+1), tfreference);

		// nom
		tfnom = new JTextField();
		tfnom.setColumns(20);
		gnom = new TextPrompt("Nom", tfnom);

		// quantite
		tfquantite = new JTextField();
		tfquantite.setColumns(8);
		gquantite = new TextPrompt("Quantit�", tfquantite);

		// prix Achat
		tfprixA = new JTextField();
		tfprixA.setColumns(10);
		gprixA = new TextPrompt("Prix d'achats", tfprixA);

		// prix Vente
		tfprixV = new JTextField();
		tfprixV.setColumns(10);
		gprixV = new TextPrompt("Prix de Ventes", tfprixV);

		//message d'erreur
		this.erreur = new JLabel();

		// Boutons
		valider = new JButton("Enregistrer");
		valider.addActionListener(new EnregistrerArticle());
		annuler = new JButton("Annuler");
		annuler.addActionListener(new Annuler());
		// ajout à la fenetre
		nvArticle.add(tfreference);
		nvArticle.add(tfnom);
		nvArticle.add(tfquantite);
		nvArticle.add(tfprixA);
		nvArticle.add(tfprixV);
		nvArticle.add(valider);
		nvArticle.add(annuler);
		nvArticle.add(Box.createVerticalGlue());
		nvArticle.add(erreur);
		nvArticle.setVisible(true);
	}
	public class NewArticle implements ActionListener {
		public void actionPerformed(ActionEvent i) {
			nouveauClient();
	}
}
	
	
	public class OpenParametres implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen5());
		}

	}

	public class OpenClients implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen6());

		}

	}

	public class OpenStock implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen7());

		}

	}

	public class OpenVentes implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen8());
		}
		// comentaire
	}

	public class BackHome implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen4());
		}
	}

	public class NextScreen implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			//construireArticle();
		}
	}
	
	public class EnregistrerArticle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Article art;
			try {					
				art = new Article(tfnom.getText() ,Integer.parseInt(tfquantite.getText()),Float.parseFloat(tfprixA.getText()),Float.parseFloat(tfprixV.getText()));
				Stock.ajouterArticle(art);
				ajouerLesArticle();
				erreur.setText("L'Article a �t� creer");
				nvArticle.dispose();
			} catch (NumberFormatException | IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Vous devez entrez des nombre des les case quantite, prix d'achat et de vente ou le fichier n'existe pas");
				erreur.setText("Veuillez entrez des chiffre");
			}	
		} 	
	}
	
	public class Annuler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (tfnom.getText().isEmpty() && tfquantite.getText().isEmpty() && tfprixA.getText().isEmpty() && tfprixV.getText().isEmpty()){
				nvArticle.dispose();
			}else{
				confirmerAnnuler("Etes Vous sur de vouloir annuler toutes les donn�es seront perdu");
			}
		}
		
		
	}
	public class confirmerOui implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			confirmer.dispose();
			nvArticle.dispose();
		}
		
	}
	public class confirmerNon implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			confirmer.dispose();
		}
		
	}
	
	public class selectionliste implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
	        if (listeArticles.getSelectedRow() > -1) {
	        	modify.setEnabled(true);
	    		remove.setEnabled(true);
	        }
			
		}
		
	}
	
	public class modifierArticle implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

