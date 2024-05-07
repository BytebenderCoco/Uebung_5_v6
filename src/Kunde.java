public class Kunde {
private String nachname;
private String vorname;

    public Kunde(String nachname, String vorname){
        this.nachname = nachname;
        this.vorname = vorname;
    }

    public String getNachname(){
        return nachname;
    }
    public void setNachname(String nachname){
        this.nachname = nachname;
    }

    public String getVorname(){
        return vorname;
    }

    public void setVorname(String vorname){
        this.vorname = vorname;
    }
}
