import java.util.ArrayList;

public class SimpleHT{

    public int size;
    public ArrayList<ArrayList<Pair>> data;

    public SimpleHT(int capacity) {
        /*  Fehlerbehandlung, Kapazität muss großer als 1 sein, 
            k = 0 oder negative Zahl macht kein Sinn             */
        if (capacity < 2) {
            capacity=1;
        }

        /* Init für Data und Kapazität  */
        data = new ArrayList<ArrayList<Pair>>();
        this.size = 0;
        for (int i = 0 ; i < capacity ; i++) {
            ArrayList<Pair> p = new ArrayList<Pair>();
            data.add(p);
        }

    }

    public class Pair{
        Integer key;
        Integer value;
        }
    
    /* Einfache Hashfunktion. es wird für negative Zahl auch ein "Positive modulo" ergeben. */
    public int addressOf(Integer key) {
        return ((key % data.size()) + data.size()) % data.size();
    }

    /* ___________________Hash-Methode-Implement___________________ */

    /* SimpleHT : Insert. */
    public void insert(Integer key, Integer value) {
        Pair newPair = new Pair();
        newPair.key = key;
        newPair.value = value;

        ArrayList<Pair> list = data.get(addressOf(key));

        boolean keyAleadyExist = false; // Ist KEY schon vorhanden? 
        for (Pair p : list) {
            if (p.key.equals(key)) {
                p.value = value; // Überschreiben
                keyAleadyExist = true;
            }
        }

        if (!keyAleadyExist) { // KEY gibt es in DATA nicht. fügen wir ein neues Paar.
            list.add(newPair);
            size++;
        }
        

    }
    /* SimpleHT : Get */
    public Integer get(Integer key) {
        ArrayList<Pair> temp = data.get(addressOf(key));
        
        if (temp.size() == 0) { // Falls kein Element in List exsistiert
            return null;
        }

        for (Pair p : temp) {
            if (p.key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                return p.value;
            }
        }
  
        return null; // Es gibt kein Paar für gegebenes KEY
    }

    /* SimpleHT : Remove */
    public boolean remove(Integer key) {

        ArrayList<Pair> list = data.get(addressOf(key));

        for (int i = 0 ; i < list.size() ; i++) {
            if (list.get(i).key.equals(key)) { // Suche in verkettete List das Ziel-Pair
                list.remove(i);
                size--;
                return true; 
            }
        }
        return false; // Es gibt kein Paar für gegebenes KEY
    }
}