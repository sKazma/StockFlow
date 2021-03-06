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
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Tableaudebord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Tableau de Bord";
	private String labels[] = { "Stock", "Clients", "Ventes", "Paramètres" };

	// Elements propres à la fenetre tableau de bord

	private JPanel sub1, sub2, sub3, sub4;
	private JLabel labelsub1, labelsub2, labelsub3, labelsub4, money;
	private JButton Stockadd, Stocklist, Stockedit, Clientsadd, Clientslist, Clientsedit, Ventesadd, Venteslist,
			Ventesedit;
	//

	public Tableaudebord(String t) {
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
		btnleft1 = new JButton(labels[0]);
		btnleft2 = new JButton(labels[1]);
		btnleft3 = new JButton(labels[2]);
		btnleft4 = new JButton(labels[3]);

		// Listeners des boutons de gauche
		btnleft4.addActionListener(new OpenParametres());
		btnleft3.addActionListener(new OpenVentes());
		btnleft2.addActionListener(new OpenClients());
		btnleft1.addActionListener(new OpenStock());
		// Ajout des composants au Panel
		left.add(btnleft1);
		left.add(btnleft2);
		left.add(btnleft3);
		left.add(btnleft4);

		// Panel central
		center = new JPanel();

		/*
		 * Le panel central est organisé en une grille de 4*4, dans laquelle
		 * chaque case s'appelle"sub". Tous les éléments qui composent chaque
		 * case contienne dans leur nom :"sub". Cette sytaxe permet de
		 * différencier plus facilement les éléments.
		 */

		// Sub stock
		sub1 = new JPanel();
		labelsub1 = new JLabel("Action rapide - " + labels[0]);
		Stockadd = new JButton("Ajouter un article...");
		Stocklist = new JButton("Afficher tous les articles...");
		Stockedit = new JButton("Modifier un article...");
		// Sub clients
		sub2 = new JPanel();
		labelsub2 = new JLabel("Action rapide - " + labels[1]);
		Clientsadd = new JButton("Ajouter un client...");
		Clientslist = new JButton("Afficher tous les cliens...");
		Clientsedit = new JButton("Modifier un client...");
		// Sub Ventes
		sub3 = new JPanel();
		labelsub3 = new JLabel("Action rapide - " + labels[2]);
		Ventesadd = new JButton("Nouvelle vente...");
		Venteslist = new JButton("Afficher toutes les ventes...");
		Ventesedit = new JButton("Modifier une vente...");
		// Sub statistiques
		sub4 = new JPanel();
		labelsub4 = new JLabel("Statistiques");
		money = new JLabel("Bénéfice(mois): 12€");

		center.setLayout(new GridLayout(2, 2, 10, 10));
		center.setBorder(new EmptyBorder(10, 10, 10, 10));
		EmptyBorder emptyBorder = new EmptyBorder(15, 15, 15, 15);
		EtchedBorder etchedBorder = new EtchedBorder();
		CompoundBorder inner = new CompoundBorder(emptyBorder, etchedBorder);
		sub1.setBorder(new CompoundBorder(inner, emptyBorder));
		sub2.setBorder(new CompoundBorder(inner, emptyBorder));
		sub3.setBorder(new CompoundBorder(inner, emptyBorder));
		sub4.setBorder(new CompoundBorder(inner, emptyBorder));

		// Panel sub-central1
		sub1.setLayout(new FlowLayout(FlowLayout.CENTER));
		sub1.add(labelsub1);
		labelsub1.setFont(new Font("Calibri", Font.PLAIN, 20));
		sub1.add(Stockadd);
		sub1.add(Stockedit);
		sub1.add(Stocklist);
		// Panel sub-central2
		sub2.setLayout(new FlowLayout(FlowLayout.CENTER));
		sub2.add(labelsub2);
		labelsub2.setFont(new Font("Calibri", Font.PLAIN, 20));
		sub2.add(Clientsadd);
		sub2.add(Clientsedit);
		sub2.add(Clientslist);
		// Panel sub-central3
		sub3.setLayout(new FlowLayout(FlowLayout.CENTER));
		sub3.add(labelsub3);
		labelsub3.setFont(new Font("Calibri", Font.PLAIN, 20));
		sub3.add(Ventesadd);
		sub3.add(Ventesedit);
		sub3.add(Venteslist);
		// Panel sub-central4
		sub4.setLayout(new FlowLayout(FlowLayout.CENTER));
		sub4.add(labelsub4);
		sub4.add(money);
		labelsub4.setFont(new Font("Calibri", Font.PLAIN, 20));
		money.setFont(new Font("Calibri", Font.BOLD, 30));

		center.add(sub1);
		center.add(sub2);
		center.add(sub3);
		center.add(sub4);

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

}