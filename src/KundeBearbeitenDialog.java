import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KundeBearbeitenDialog extends JDialog{

    private Kunde kunde;
    private JTextField txtNachname;
    private JTextField txtVorname;

    public KundeBearbeitenDialog(KundenVerwaltung parent, Kunde kunde){

        super (parent);

        setModal(true);
        setTitle(("Kunde bearbeiten"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.kunde = kunde;

        setLayout(new GridLayout(3, 2));

        add (new JLabel("Nachname"));
        add(txtNachname = new JTextField(kunde.getNachname()));

        add (new JLabel("Vorname"));
        add(txtVorname = new JTextField(kunde.getVorname()));

        JButton cmdok = new JButton("OK");
        add(cmdok);

        JButton cmdcancel = new JButton("Abbrechen");
        add(cmdcancel);

        class MyOkHandler implements ActionListener{
            public void actionPerformed(ActionEvent e){
                kunde.setNachname(txtNachname.getText());
                kunde.setVorname(txtVorname.getText());
                dispose();
            }
        }

        class MyCancelHandler implements ActionListener{
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        }

        cmdok.addActionListener(new MyOkHandler());
        cmdcancel.addActionListener(new MyCancelHandler());

        pack();
        setVisible(true);
    }
}
