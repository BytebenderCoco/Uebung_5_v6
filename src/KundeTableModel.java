import javax.swing.table.AbstractTableModel;

public class KundeTableModel extends AbstractTableModel {
    private Kunde[] kunden;

    public KundeTableModel(Kunde[] kunden) {
        this.kunden = kunden;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Nachname";
            case 1:
                return "Vorname";
            default:
                return null;
        }
    }

    public int getRowCount() {
        return kunden.length;
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int row, int col) {
        Kunde kunde = kunden[row];
        switch (col) {
            case 0:
                return kunde.getNachname();
            case 1:
                return kunde.getVorname();
            default:
                return null;
        }
    }

    public Kunde getKunde(int row) {
        return kunden[row];
    }
}