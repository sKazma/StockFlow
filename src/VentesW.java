import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

public class VentesW<ventes> extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4, home;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Gestion des ventes";
	private String labels[] = { "Stock", "Clients", "Ventes", "Paramètres" };
	
	private String[] entete = { "Référence Ventes", "Clients",  "Articles", "Total"};
	private DefaultTableModel tableModel = new DefaultTableModel(entete,0);

	// composants propres au menu principal
	private JPanel line1, line2, line3;
	private JButton add, modify, remove;
	private JLabel searchtitle;
	private JTextField tfsearch;
	private JTable listeVentes;
	
	//composant confirmer annuler
		private JDialog confirmer;
		private JLabel attention;
		private JButton oui,non;
		private JPanel central2;
		// composants en rapport avec la JDialog
				private JFrame nvVente;
				private JPanel central;
				private TextPrompt gnom, gquantite, greference;
				private JTextField tfnom, tfquantite, tfreference;
				private JButton ajouter;
				private JLabel erreur;

	public VentesW(String t) {
		this.setTitle(t);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWindow();
	}
	
	public void ajouterLesVentes(){
		listeVentes = new JTable(this.tableModel);
		this.tableModel.setRowCount(0);
		listeVentes.setModel(tableModel);
		for(int i = 0 ; i < Ventes.ventes.size();i++){
			Object[] contenus={Ventes.ventes.get(i).getRef(), Clients.mesClients.get(i).getNom(), Stock.mesArticle.get(i).getNom()/* ,total */};
			this.tableModel.addRow(contenus);
		}
	}

	public void createWindow() {

		this.ajouterLesVentes();
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
		btnleft1 = new JButton(labels[0]);
		btnleft2 = new JButton(labels[1]);
		btnleft3 = new JButton("> " + labels[2] + " <");
		btnleft3.setBackground(Color.ORANGE);
		btnleft3.setForeground(Color.BLACK);
		btnleft3.setOpaque(true);
		btnleft4 = new JButton(labels[3]);

		// Listeners des boutons de gauche
		btnleft4.addActionListener(new OpenParametres());
		home.addActionListener(new BackHome());
		btnleft2.addActionListener(new OpenClients());
		btnleft1.addActionListener(new OpenStock());

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

		searchtitle = new JLabel("Rechercher une vente : ");
		tfsearch = new JTextField("Entrez un nom ou une référence");
		add = new JButton("Créer une Vente...");
		add.addActionListener(new NouvVente());
		remove = new JButton("Supprimer...");
		modify = new JButton("Modifer...");
		modify.setEnabled(false);
		remove.setEnabled(false);		
			
		// PremiÃ¨re ligne de "center"
		line1.add(add);
		line1.add(modify);
		line1.add(remove);

		// DeuxiÃ¨me ligne de "center"
		line2.add(searchtitle);
		line2.add(tfsearch);
		
		// troisiÃ¨me ligne de "center"
		line3.add(new JScrollPane(listeVentes));

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

	}

	public class BackHome implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen4());
		}
	}
	
	public class NouvVente implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			nouvelleVente();
		}
	}
	
	public void nouvelleVente(){
		nvVente = new JFrame();
		nvVente.setTitle("StockFlow - Nouvelle Vente");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createWindowV();
	}
	public void createWindowV() {
		this.ajouterLesVentes();
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
		btnleft1 = new JButton(labels[0]);
		btnleft2 = new JButton(labels[1]);
		btnleft3 = new JButton("> " + labels[2] + " <");
		btnleft3.setBackground(Color.ORANGE);
		btnleft3.setForeground(Color.BLACK);
		btnleft3.setOpaque(true);
		btnleft4 = new JButton(labels[3]);

		// Listeners des boutons de gauche
		btnleft4.addActionListener(new OpenParametres());
		home.addActionListener(new BackHome());
		btnleft2.addActionListener(new OpenClients());
		btnleft1.addActionListener(new OpenStock());

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
		
		// IMPLEMENTATION DES ZONES DE TEXTES,BOUTONS, ETC		
		//nom Client
		JTextField tfclient = new JTextField();
		tfclient.setColumns(20);
		TextPrompt gclient = new TextPrompt("Nom du Client: ", tfclient);
		line1.add(tfclient);
		
		//ref client
			JLabel Client= new JLabel("Client: ");
			JTextField tfrefclient = new JTextField();
			tfrefclient.setColumns(16);
			TextPrompt grefclient = new TextPrompt("Ref du client: "+Integer.toString(Clients.mesClients.size()+1), tfrefclient);
			line1.add(tfrefclient);
		
		// rÃ©fÃ©rence
			JLabel Article= new JLabel("Article: ");
			tfreference = new JTextField();
			tfreference.setColumns(10);
			greference = new TextPrompt("Ref de l'article: "+Integer.toString(Stock.mesArticle.size()+1), tfreference);
			line2.add(tfreference);
		// nom
			tfnom = new JTextField();
			tfnom.setColumns(15);
			gnom = new TextPrompt("Nom de l'article", tfnom);
			line2.add(tfnom);
				
		// quantite
			tfquantite = new JTextField();
			tfquantite.setColumns(9);
			gquantite = new TextPrompt("Quantité", tfquantite);
			line2.add(tfquantite);
				
		//message d'erreur
			this.erreur = new JLabel();

		// Boutons
			ajouter = new JButton("Ajouter");
			//ajouter.addActionListener(new EnregistrerVente());
			line2.add(ajouter);				
				
		// ajout Ã  la fenetre
			/*nvVente.add(Client);
			nvVente.add(tfrefclient);
			nvVente.add(tfclient);
			nvVente.add(Article);
			nvVente.add(tfreference);
			nvVente.add(tfnom);
			nvVente.add(tfquantite);
			nvVente.add(ajouter);
			nvVente.add(annuler);
			nvVente.add(Box.createVerticalGlue());
			nvVente.add(erreur);
			nvVente.setVisible(true);*/
				
		// troisiÃ¨me ligne de "center"
			line3.add(new JScrollPane(listeVentes));
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
	
	static public void ouvrirFichier() throws FileNotFoundException, IOException, ClassNotFoundException{
		File fichierVente = new File("ventes.txt");

		ObjectInputStream ouverture = new ObjectInputStream(new FileInputStream(fichierVente)); // attention, tu peux avoir des erreurs s'il ne trouve pas le fichier,
	 //donc fais attention au nom que tu lui donne et le chemin aussi, si tu vas dans 
	 // d'autres dossier. 
		@SuppressWarnings("unchecked")
		ArrayList<Vente1> tabFichier= (ArrayList<Vente1>) ouverture.readObject(); // On est oblig� de caster ce qu'il retourne
		Ventes.ventes=tabFichier;	// 	Je sais pas pourquoi mais c'est comme �a ;)
		ouverture.close();
	}

	
	static public void enregistrer() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		File fichierVente = new File("ventes.txt");
		ObjectOutputStream enregistreVente = new ObjectOutputStream(new FileOutputStream(fichierVente));
		ArrayList<Vente1> tabFichier = Ventes.ventes;
		enregistreVente.writeObject(tabFichier);
		enregistreVente.close();
	}
}
