import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Parametres extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel main, top, center, left;
	private JButton btnleft1, btnleft2, btnleft3, btnleft4, home;
	private JLabel title, welcome, piclabel;
	private String titlewindow = "Paramètres";
	private String labels[] = { "Stock", "Clients", "Ventes", "Paramètres" };

	// Propres � la fenetre Param�tres
	private JPanel sub1, sub2, sub3, sub4, bottom;
	private JLabel cn, n, s, hint1, hint2, pic1, pic2;
	private JTextField tfcn, tfn, tfsn;
	private JButton ok, cancel, explore1, delete1, explore2, delete2;
	private JFileChooser jfc = new JFileChooser();
	private File f1 = new File("null");
	private File f2 = new File("null");

	public Parametres(String t) {
		this.setTitle(t);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWindow();
	}

	public void createWindow() {

		// Contener princip

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
		btnleft3 = new JButton(labels[2]);
		btnleft4 = new JButton("> " + labels[3] + " <");
		btnleft4.setBackground(Color.ORANGE);
		btnleft4.setForeground(Color.BLACK);
		btnleft4.setOpaque(true);

		// Listeners des boutons de gauche
		home.addActionListener(new BackHome());
		btnleft3.addActionListener(new OpenVentes());
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

		// Sous panel 1
		sub1 = new JPanel();
		sub1.setLayout(new GridLayout(4, 1, 10, 10));
		hint1 = new JLabel("Modifier les informations de l'entreprise");
		hint1.setFont(new Font("Calibri", Font.BOLD, 18));
		cn = new JLabel("Nom de l'entreprise :");
		tfcn = new JTextField(Logiciel.getCompanyname());
		tfcn.setColumns(15);
		sub1.add(hint1);
		sub1.add(cn);
		sub1.add(tfcn);

		// Sous panel 2
		sub2 = new JPanel();
		try {
			pic1 = new JLabel(new ImageIcon(Logiciel.getUser().getScaledInstance(100, 100, 300)));
			pic1.setPreferredSize(new Dimension(300, 100));
			pic1.setAlignmentX(Component.CENTER_ALIGNMENT);
		} catch (Exception e) {
			pic1 = new JLabel(" ");
		}
		explore1 = new JButton("Parcourir...");
		delete1 = new JButton("Supprimer");
		explore1.addActionListener(new SetLogo());
		delete1.addActionListener(new RemoveLogo());
		sub2.add(pic1);
		sub2.add(explore1);
		sub2.add(delete1);

		// Sous panel 3
		sub3 = new JPanel();
		sub3.setLayout(new GridLayout(6, 1, 10, 10));
		hint2 = new JLabel("Modifier les informations du g�rant");
		hint2.setFont(new Font("Calibri", Font.BOLD, 18));
		n = new JLabel("NOM : ");
		s = new JLabel("Pr�nom : ");
		tfn = new JTextField(Logiciel.getName());
		tfsn = new JTextField(Logiciel.getSurname());
		sub3.add(hint2);

		sub3.add(s);
		sub3.add(tfn);
		sub3.add(n);
		sub3.add(tfsn);

		// Sous panel 4
		sub4 = new JPanel();
		try {
			pic2 = new JLabel(new ImageIcon(Logiciel.getLogo().getScaledInstance(100, 100, 300)));
			pic2.setPreferredSize(new Dimension(300, 100));
			pic2.setAlignmentX(Component.CENTER_ALIGNMENT);
		} catch (Exception e) {
			pic2 = new JLabel(" ");
		}
		explore2 = new JButton("Parcourir...");
		delete2 = new JButton("Supprimer");
		explore2.addActionListener(new SetFace());
		delete2.addActionListener(new RemoveFace());
		sub4.add(pic2);
		sub4.add(explore2);
		sub4.add(delete2);

		// Panel du bas
		bottom = new JPanel();
		ok = new JButton("Enregistrer les modifications");
		cancel = new JButton("Annuler les modifications");

		ok.addActionListener(new SaveChanges());
		cancel.addActionListener(new BackHome());
		bottom.add(cancel);
		bottom.add(ok);

		center.setLayout(new GridLayout(2, 2, 10, 10));
		center.add(sub1);
		center.add(sub2);
		center.add(sub3);
		center.add(sub4);

		// Ajout des panel au panel principal
		main.add(top, BorderLayout.NORTH);
		main.add(left, BorderLayout.WEST);
		main.add(center, BorderLayout.CENTER);
		main.add(bottom, BorderLayout.SOUTH);

	}

	public class SetFace implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			try {
				jfc.showOpenDialog(null);
				f1 = jfc.getSelectedFile();
				pic2.setIcon(new ImageIcon(ImageIO.read(f1).getScaledInstance(100, 100, 300)));
			} catch (Exception e) {
				//
			}
		}

	}

	// Supprimer un logo charg�
	public class RemoveFace implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			pic2.setIcon(null);
		}
	}

	public class SetLogo implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			try {
				jfc.showOpenDialog(null);
				f2 = jfc.getSelectedFile();
				pic1.setIcon(new ImageIcon(ImageIO.read(f2).getScaledInstance(100, 100, 300)));
			} catch (Exception e) {
			}
		}

	}

	// Supprimer un logo charg�
	public class RemoveLogo implements ActionListener {
		public void actionPerformed(ActionEvent ei) {
			pic1.setIcon(null);
		}
	}

	public class SaveChanges implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// try{
			try {
				if (f1!=null) {
					Logiciel.setUser(ImageIO.read(f1));
				}
				if (f2!=null) {
				Logiciel.setLogo(ImageIO.read(f2));
				}
				if(pic1.getIcon().equals(null)){
					Logiciel.setUser(null);
				}
				if(pic2.getIcon().equals(null)){
					Logiciel.setLogo(null);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Logiciel.setCompanyname(tfcn.getText());
			Logiciel.setName(tfn.getText());
			Logiciel.setSurname(tfsn.getText());
			JOptionPane.showMessageDialog(null, "Modifications apportées avec succès !");
			// }catch(Exception em){
			//
			// }
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
