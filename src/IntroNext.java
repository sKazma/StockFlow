import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IntroNext extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Status Variable
	// private static boolean Done=false;
	// Panels
	private JPanel top, center, bottom, main;
	// Panels of the middle
	private JPanel left, right, sub_right1, sub_right2, sub_left1, sub_left2, line1, line2;

	// Jbuttons( Premiére page)
	private JButton cancel, ok, explore, delete;

	// Jlabel
	private JLabel title, piclabel, name, surname, hint;
	// Others
	private JFileChooser jfc = new JFileChooser();

	// TextFields
	private JTextField tfname, tfsurname;

	// Constructor

	public IntroNext(String t) {

		this.setTitle(t);
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createWindow();
	}

	// Méthode qui crée la fenétre 1
	public void createWindow() {

		/*-----------------------INITIALISATION DES ELEMENTS--------------------------*/

		// Panels
		main = new JPanel();
		top = new JPanel();
		center = new JPanel();
		bottom = new JPanel();
		left = new JPanel();
		right = new JPanel();
		sub_right1 = new JPanel();
		sub_right2 = new JPanel();
		sub_left1 = new JPanel();
		sub_left2 = new JPanel();
		line1 = new JPanel();
		line2 = new JPanel();

		// Buttuons
		ok = new JButton("Suivant -- >");
		cancel = new JButton("< -- Précédent");
		explore = new JButton("Parcourir...");
		delete = new JButton("Supprimer");

		// Labels
		title = new JLabel("Etape 2 :  Création de l'utilisateur principal");
		String s = "A présent, entrez les informations de l'utilisateur principal en indiquant son nom, son prénom et pourquoi ne pas y ajouter une photo ? ;)";
		String html1 = "<html><body style='width: ";
		String html2 = "px'>";
		hint = new JLabel(html1 + "200" + html2 + s);
		surname = new JLabel("Nom :");
		name = new JLabel("Prénom :");
		tfsurname = new JTextField("");
		tfname = new JTextField("");
		piclabel = new JLabel();

		/* -------------------------------------- */

		// Définition du panel principal
		this.setContentPane(main);
		// Application d'un Layout au panel principal
		main.setLayout(new BorderLayout());
		// Ajout des trois panels qui composent la fenétre
		main.add(top, BorderLayout.NORTH);
		main.add(center, BorderLayout.CENTER);
		main.add(bottom, BorderLayout.SOUTH);

		// Panel du haut
		top.add(title);
		top.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 30));
		// Mise en forme du titre
		title.setForeground(Color.orange);
		title.setFont(new Font("Calibri", Font.BOLD, 28));

		// Panel du milieu
		center.setLayout(new GridLayout(0, 2));
		// Le panel du milieu est composé de deux autres panels
		center.add(left);
		center.add(right);
		right.add(sub_right1);
		right.add(sub_right2);
		left.add(sub_left1);
		left.add(sub_left2);
		sub_left2.add(line1);
		sub_left2.add(line2);

		// Panel de gauche
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		sub_left1.add(hint);
		line1.add(name);
		line1.add(tfname);
		line2.add(surname);
		line2.add(tfsurname);
		tfname.setColumns(13);
		tfsurname.setColumns(15);

		sub_left2.setLayout(new BoxLayout(sub_left2, BoxLayout.Y_AXIS));
		name.setAlignmentX(Component.LEFT_ALIGNMENT);

		// Panel de droite
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		sub_right1.add(piclabel);
		piclabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		// forcer la taille du panel pour que l'image ai toujours la méme place
		sub_right1.setPreferredSize(new Dimension(100, 100));
		sub_right2.add(explore);
		sub_right2.add(delete);

		// Listeners
		explore.addActionListener(new SetPicture());
		delete.addActionListener(new RemovePicture());

		// Panel du bas
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bottom.add(Box.createGlue());
		bottom.add(cancel);
		bottom.add(Box.createGlue());
		bottom.add(ok);
		bottom.add(Box.createGlue());
		ok.addActionListener(new NextScreen());
		cancel.addActionListener(new PreviousScreen());
	}

	// -----------------classes internes-----------------------

	// Charger un logo

	public class SetPicture implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			try {
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				String[] extensions = new String[] { "png", "jpg", "bmp", "gif", "jpeg" };
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file", extensions);
				jfc.setFileFilter(filter);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				Logiciel.setUser(ImageIO.read(f));
				piclabel.setIcon(new ImageIcon(Logiciel.getUser().getScaledInstance(100, 100, 300)));
			} catch (Exception e) {
				System.err.println("Impossible de charger la photo");
			}
		}
	}

	// Supprimer un logo chargé
	public class RemovePicture implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.setUser(null);
			piclabel.setIcon(null);
		}
	}

	// Fenétre suivante
	public class NextScreen implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.setName(tfname.getText());
			Logiciel.setSurname(tfsurname.getText());
			Logiciel.Show(Logiciel.getFen3());

		}
	}

	// fermer la fenetre
	public class PreviousScreen implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			Logiciel.show(Logiciel.getFen1(), true);
			Logiciel.show(Logiciel.getFen2(), false);
		}
	}

}
