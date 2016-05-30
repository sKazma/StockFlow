import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StockW extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4, home;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Gestion du stock";
	private String labels[] = { "Stock", "Clients", "Ventes", "Param√®tres" };
	

	//Composants propres au panel
	private JLabel  searchtitle, addtitle, listtitle;
	private JButton addarticle;
	private JList listearticle;
	private JTextField search;

	
	//propre au stock
	private JTextField rechercher;
	private JButton ok,annuler;
	private JLabel nomArt;
	
	
	//Propre a l'article
	
	private JLabel nomA,ref;
	private JTextField quantite,prixV,prixA;
	private JButton supprimer,annulerModif,enregistrer;
	
	
	
	// constructeur 

	
	public StockW(String t) {
		this.setTitle(t);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWindow();
	}

	public void createWindow() {

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

		listtitle = new JLabel("Liste des articles");
		addtitle = new JLabel("Ajout un article");
		searchtitle = new JLabel("Rechercher un article");
		search = new JTextField("Entrez un nom ou une rÈfÈrence");
		addarticle = new JButton("Cr√©er un article");
		listearticle = new JList();


		center.setLayout(new GridLayout(3, 1 , 10, 10));
		center.setBorder(new EmptyBorder(10, 10, 10, 10));

		center.add(searchtitle);
		center.add(addtitle);
		center.add(listtitle);
		center.add(search);
		center.add(addarticle);
		center.add(listearticle);


		
		this.nomArt = new JLabel("Nom Article");
		this.rechercher = new JTextField();
		this.annuler= new JButton("Annuler");
		this.ok=new JButton("Rechercher");
		this.ok.addActionListener(new NextScreen() );
		center.add(nomArt);
		center.add(rechercher);
		center.add(ok);
		center.add(annuler);	
		
		

		// Ajout des panel au panel principal
		main.add(top, BorderLayout.NORTH);
		main.add(left, BorderLayout.WEST);
		main.add(center, BorderLayout.CENTER);

	}
	
	public void construireArticle(){
		JPanel nvArticle = new JPanel();
		
		this.nomA = new JLabel();
		this.ref=new JLabel();
		this.quantite = new JTextField();
		this.prixA = new JTextField();
		this.prixV= new JTextField();
		
		if (!Stock.existeArticleNom(rechercher.getText())){
			this.ref.setText(Integer.toString(Stock.mesArticle.size()));
			this.quantite.setText("QuantitÔøΩ : ");
			this.prixA.setText("Prix d'achat");
			this.prixV.setText("Prix de vente");
		}else{
			this.nomA.setText(rechercher.getText());
			this.ref.setText(Integer.toString(Stock.trouverArticleNom(rechercher.getText()).getReference()));
			this.quantite.setText("QuantitÔøΩ : " + Integer.toString(Stock.trouverArticleNom(rechercher.getText()).getQuantite()));
			this.prixA.setText("Prix d'achat" + Float.toString(Stock.trouverArticleNom(rechercher.getText()).getPrixA()));
			this.prixV.setText("Prix de vente" + Float.toString(Stock.trouverArticleNom(rechercher.getText()).getPrixV()));
		}
		center.removeAll();
		center.setLayout(new FlowLayout());
		
		nvArticle.setLayout(new BoxLayout(nvArticle,BoxLayout.Y_AXIS));
		nvArticle.add(nomA);
		nvArticle.add(Box.createVerticalBox());
		nvArticle.add(ref);
		nvArticle.add(Box.createVerticalBox());
		nvArticle.add(quantite);
		nvArticle.add(Box.createVerticalBox());
		nvArticle.add(prixA);
		nvArticle.add(Box.createVerticalBox());
		nvArticle.add(prixV);
		
		JPanel actionBouton = new JPanel();
		actionBouton.setLayout(new GridLayout(3,1));
		this.supprimer = new JButton("Supprimer Article");
		this.enregistrer = new JButton("Enregistrer modification");
		this.annulerModif = new JButton("Annuler les modification");
		
		actionBouton.add(supprimer);
		actionBouton.add(enregistrer);
		actionBouton.add(annulerModif);
		
		center.add(nvArticle);
		center.add(actionBouton);
		
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
//comentaire
	}

	public class BackHome implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen4());
		}
	}
	public class NextScreen implements ActionListener {
		public void actionPerformed(ActionEvent ei){
			construireArticle();
		}
	}
	
}
