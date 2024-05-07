import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KundenVerwaltung extends JFrame {
    private Kunde[] kunden;
    private ListSelectionModel kundeSelectionModel;
    private KundeTableModel kundeTableModel;
    private JMenuItem bearbeitenMenuItem;

    public KundenVerwaltung(Kunde[] kunden) {
        this.kunden = kunden;

        setTitle("KundenVerwaltung");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu menu;
        JMenuItem mi;

        menubar.add(menu = new JMenu("Datei"));

        bearbeitenMenuItem = new JMenuItem("Kunde bearbeiten");
        bearbeitenMenuItem.setEnabled(false); // Deaktiviere das Menüelement zu Beginn
        bearbeitenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editKunde();
            }
        });
        menu.add(bearbeitenMenuItem);
        menu.addSeparator();
        menu.add(mi = new JMenuItem("Beenden"));
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        kundeTableModel = new KundeTableModel(kunden);
        JTable table = new JTable(kundeTableModel);

        //Doppelklick der Listelemente ruft Kundebearbeitendialog auf
        kundeSelectionModel = table.getSelectionModel();
        kundeSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) editKunde();
            }
        });

        // Menuitem "Bearbeiten" kann nur ausgewählt werden, wenn auch ein Listelement markiert ist
        kundeSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (kundeSelectionModel.getMinSelectionIndex() != -1) {
                        bearbeitenMenuItem.setEnabled(true); // Aktiviere das Menüelement (Bearbeiten), wenn eine Zeile ausgewählt ist
                    } else {
                        bearbeitenMenuItem.setEnabled(false); // Deaktiviere das Menüelement (Bearbeiten), wenn keine Zeile ausgewählt ist
                    }
                }
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollpane = new JScrollPane(table);
        JPanel titlepane = new JPanel();
        titlepane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Tabellenansicht"));
        titlepane.setLayout(new BorderLayout());
        titlepane.add(scrollpane);

        add(titlepane);

        setVisible(true);

    }

    public static void main(String[] args) {
        Kunde[] kunden = new Kunde[3];
        kunden[0] = new Kunde("Meier", "Josef");
        kunden[1] = new Kunde("Huber", "Franziska");
        kunden[2] = new Kunde("Schmidt", "Helmut");
        new KundenVerwaltung(kunden);
    }

    private void editKunde() {
        int row = kundeSelectionModel.getMinSelectionIndex();
        if (row != -1) {
            new KundeBearbeitenDialog(this, kundeTableModel.getKunde(row));
            kundeTableModel.fireTableRowsUpdated(row, row);
        }
    }
}
