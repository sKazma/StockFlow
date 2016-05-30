import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IntroFinal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel top, center, bottom, main, left, right;
	private JLabel title, hint, name, surname, companyname, piclabel, logo;
	private JButton done, back;

	public IntroFinal(String t) {
		this.setTitle(t);
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWindow();
		// TODO Auto-generated constructor stub
	}

	public void createWindow() {

		main = new JPanel();
		top = new JPanel();
		center = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		right = new JPanel();
		done = new JButton("Terminé !");
		back = new JButton("< -- Précédent");
		title = new JLabel("Etape 3 :  Résumé de la configuration");
		String s = "Voilé, maintenant vous pouvez vérifier les informations entrées précédemment et les rectifier si besoin en cliquant sur précédent.";
		String html1 = "<html><body style='width: ";
		String html2 = "px'>";
		hint = new JLabel(html1 + "200" + html2 + s);
		surname = new JLabel("Prenom : " + Logiciel.getName());
		name = new JLabel("Nom : " + Logiciel.getSurname());
		companyname = new JLabel("Raison sociale : " + Logiciel.getCompanyname());

		// Listeners
		done.addActionListener(new Finished());
		back.addActionListener(new PreviousScreen());

		// Récupération des images à partir de la Classe "Logiciel"
		try {
			piclabel = new JLabel(new ImageIcon(Logiciel.getUser().getScaledInstance(100, 100, 300)));
			piclabel.setPreferredSize(new Dimension(300, 100));
			piclabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			logo = new JLabel(new ImageIcon(Logiciel.getLogo().getScaledInstance(100, 100, 300)));
			logo.setPreferredSize(new Dimension(300, 100));
			logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		} catch (Exception e) {
			piclabel = new JLabel("Vous n'avez pas choisi de logo.");
			logo = new JLabel("Vous n'avez pas choisi de photo pour l'utilisateur.");
		}

		// Définition du panel "main" comme étant le panel principal
		this.setContentPane(main);
		// Application d'un Layout au panel principal
		main.setLayout(new BorderLayout());

		// Panel du haut
		top.add(title);
		top.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
		// Mise en forme du titre
		title.setForeground(Color.orange);
		title.setFont(new Font("Calibri", Font.BOLD, 28));

		// Panel du milieu
		center.setLayout(new GridLayout(0, 2));
		right.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
		left.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 30));
		left.add(hint);
		left.add(logo);
		left.add(companyname);
		right.add(piclabel);
		right.add(name);
		right.add(surname);
		bottom.add(back);
		bottom.add(done);
		center.add(left);
		center.add(right);
		center.add(bottom);

		// Ajout des trois panels qui composent la fenétre
		main.add(top, BorderLayout.NORTH);
		main.add(center, BorderLayout.CENTER);
		main.add(bottom, BorderLayout.SOUTH);

	}

	// Classes internes pour les listeners
	public class Finished implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.Show(Logiciel.getFen4());
		}
	}

	// fermer la fenetre
	public class PreviousScreen implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.show(Logiciel.getFen2(), true);
			Logiciel.show(Logiciel.getFen3(), false);
		}
	}

}
