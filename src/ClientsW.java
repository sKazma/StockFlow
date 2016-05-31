import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ClientsW extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4, home;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Gestion des clients";
	private String labels[] = { "Stock", "Clients", "Ventes", "Paramètres" };

	// composants propres au menu principal
	private JPanel line1, line2, line3;
	private JButton add, modify, search, remove;
	private JLabel searchtitle;
	private JTextField tfsearch;
	private JTable listeClients;
	private String[] entete = { "Référence", "Nom", "Prénom", "Ville", "Adresse", "Téléphone" };
	private String[] [] donnees = {{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"},
								{"00001", "MICHEL", "Berger", "Auray", "Centre-ville", "0297240088"}
								};

	// composants en rapport avec la JDialog
		private JDialog newclient;
		private JPanel central;
		private TextPrompt gnom, gprenom, gadresse, gville, gcodepostal, gtelephone, greference;
		private JTextField tfnom, tfprenom, tfadresse, tfville, tfcodepostal, tftelephone, tfreference;
		private JButton valider, annuler;


	public ClientsW(String t) {
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
		btnleft1 = new JButton(labels[0]);
		btnleft2 = new JButton(">" + labels[1] + "<");
		btnleft2.setBackground(Color.ORANGE);
		btnleft2.setForeground(Color.BLACK);
		btnleft2.setOpaque(true);
		btnleft3 = new JButton(labels[2]);
		btnleft4 = new JButton(labels[3]);

		// Listeners des boutons de gauche
		btnleft4.addActionListener(new OpenParametres());
		btnleft3.addActionListener(new OpenVentes());
		home.addActionListener(new BackHome());
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

		searchtitle = new JLabel("Rechercher un article : ");
		tfsearch = new JTextField("Entrez un nom ou une référence");
		add = new JButton("Créer un article...");
		remove = new JButton("Supprimer...");
		modify = new JButton("Modifer...");
		modify.setEnabled(false);
		remove.setEnabled(false);
		search = new JButton("Rechercher");
		listeClients = new JTable(donnees, entete);

		// Première ligne de "center"
		line1.add(add);
		line1.add(modify);
		line1.add(remove);

		// Deuxième ligne de "center"
		line2.add(searchtitle);
		line2.add(tfsearch);
		line2.add(search);

		// troisième ligne de "center"
		line3.add(new JScrollPane(listeClients));

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

	
	//JDialog
	
	public void nouveauClient() {
		newclient = new JDialog(Logiciel.getFen7(), "StockFlow - Nouvel Article");
		newclient.setSize(450, 200);
		newclient.setLocationRelativeTo(null);
		newclient.setContentPane(central = new JPanel());

		// référence
		tfreference = new JTextField(Article.getRef());
		tfreference.setColumns(6);
		greference = new TextPrompt("Référence", tfreference);

		// nom
		tfnom = new JTextField();
		tfnom.setColumns(15);
		gnom = new TextPrompt("Nom", tfnom);

		// prenom
		tfprenom = new JTextField();
		tfprenom.setColumns(15);
		gprenom = new TextPrompt("Prénom", tfprenom);

		// adresse
		tfadresse = new JTextField();
		tfadresse.setColumns(35);
		gadresse = new TextPrompt("Adresse", tfadresse);

		// ville
		tfville = new JTextField();
		tfville.setColumns(15);
		gville = new TextPrompt("Ville", tfville);

		// codepostal
		tfcodepostal = new JTextField();
		tfcodepostal.setColumns(5);
		gcodepostal = new TextPrompt("Code Postal", tfcodepostal);

		// telephone
		tftelephone = new JTextField();
		tftelephone.setColumns(10);
		gtelephone = new TextPrompt("Téléphone", tftelephone);

		// Boutons
		valider = new JButton("Enregistrer");
		annuler = new JButton("Annuler");
		// ajout à la fenetre
		newclient.add(tfreference);
		newclient.add(tfnom);
		newclient.add(tfprenom);
		newclient.add(tfadresse);
		newclient.add(tfville);
		newclient.add(tfcodepostal);
		newclient.add(tftelephone);
		newclient.add(valider);
		newclient.add(annuler);
		newclient.setVisible(true);
	}
	// Nouvel article
		public class NewClient implements ActionListener {
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

	}

	public class BackHome implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen4());
		}
	}
}
