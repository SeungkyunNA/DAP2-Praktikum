import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TestClass {
    public static void main(String[] args) {
        simpleTest();
        genTest();
        paramTest1();
        paramTest2();
        collosionCount1();
        collosionCount2();
        wieHashForType();


    }

    private static void wieHashForType(){
        System.out.println("________________________________________"); 
        System.out.println("⎢    wie funktioniert hashCode für die  ⎢"); 
        System.out.println("⎢ Wrapper-Typen der primitiven Klassen? ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 
        String rst = "⎢Integer(245) : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        Integer i = 245;
        System.out.println(i.hashCode() + "  |");
        System.out.println("⎢_______________________________________⎢");

        rst = "⎢Long(12345L) : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        Long l = 12345L;
        System.out.println(l.hashCode() + "|");
        System.out.println("⎢_______________________________________⎢"); 

        rst = "⎢Double(0.12) : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 30-rst.length() ; rp++) {
            System.out.print(" ");
        }
        Double d = 0.12;
        System.out.println(d.hashCode() + "|");
        System.out.println("⎢_______________________________________⎢"); 

        rst = "⎢Booleans TRUE : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        Boolean t = true;
        System.out.println(t.hashCode() + " |");
        System.out.println("⎢_______________________________________⎢"); 
        rst = "⎢Booleans False : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        Boolean f = false;
        System.out.println(f.hashCode() + " |");
        System.out.println("⎢_______________________________________⎢"); 


    }

    private static void simpleTest() {
        System.out.println("________________________________________"); 
        System.out.println("⎢          AUFGABE 8.1 SimpleHT         ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 

        /* Class erstellen. Es gibt die Schlüsselmenge größe 3. (0,1,2)  */
        SimpleHT s = new SimpleHT(3);
        resultPrint(1, true);

        /* Erwartet : Es wird einfach -30 bis 30 als Key und selbe Value geschpeichert */
        for (int i = -10000 ; i < 10000 ; i++) {
            s.insert(i, i);
        }
        
        /* Test für Insert */
        boolean flag = true;
        for (int i = -10000 ; i < 10000 ; i++) {
            if(s.get(i) != i) {
                flag = false;
            }
        }
        resultPrint(2, flag);


        /* Erwartet : Alle Wert werden x2. (weil mit gleichen KEYs wieder gescpeichert. (Überschreiben)) */
        for (int i = -10000 ; i < 10000 ; i++) {
            s.insert(i, i*2);
        }
        flag = true;

        /* Test für Insert (Überschreiben) */
        for (int i = -10000 ; i < 10000 ; i++) {
            if(s.get(i) != i*2) {
                flag = false;
            }
        }
        resultPrint(3, flag);


         /* Test für Remove */
        flag = s.remove(1);        // Erwartet : TRUE, weil Key(1) existieren muss.
        resultPrint(4,flag);
        flag = (s.get(1) == null);   // Erwartet : null, weil Key(1) schon gelöscht werden muss.
        resultPrint(5,flag);
        flag = !s.remove(1);       // Erwartet : FALSE, weil Key(1) schon gelöscht werden muss.
        resultPrint(6, flag);
        
        System.out.println("⎢_______________________________________⎢"); 
    }

    private static void genTest() {
        System.out.println("________________________________________"); 
        System.out.println("⎢           AUFGABE 8.2 GenHT           ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 

        /* Class erstellen. Es gibt die Schlüsselmenge größe 3. (0,..,6)  */
        GenHT<String , Boolean> s = new GenHT<String , Boolean>(14);
        resultPrint(1, true);


        /* Erwartet : Es wird String als KEY, Boolean(GradeZahl : True) als Value geschpeichert */
        for (int i = -10000 ; i < 10000 ; i++) {
            String k = "KEY" + i;
            boolean v = (i%2 == 0);
            s.insert(k, v);
        }
        /* Test für Insert */
        boolean flag = true;
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            if(s.get("KEY"+i) != tempB) {
                flag = false;
            }
        }
        resultPrint(2, flag);


        /* Erwartet : Alle Wert werden Boolean(Ungeradezahl : True).(Überschreiben)) */
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            s.insert("KEY"+i, !tempB); // reversed Result (Ungeradezahl : True)
        }

        flag = true;
        /* Test für Insert (Überschreiben) */
        for (int i = -10000 ; i < 10000 ; i++) {
            if(s.get("KEY"+i) != (i%2 != 0)) {
                flag = false;
            }
        }
        resultPrint(3, flag);

        /* Test für Remove */
        flag = s.remove("KEY"+1);        // Erwartet : TRUE, weil Key(1) existieren muss.
        resultPrint(4,flag);
        flag = (s.get("KEY"+1) == null);   // Erwartet : null, weil Key(1) schon gelöscht werden muss.
        resultPrint(5,flag);
        flag = !s.remove("KEY"+1);       // Erwartet : FALSE, weil Key(1) schon gelöscht werden muss.
        resultPrint(6, flag);
        
        System.out.println("⎢_______________________________________⎢"); 
    }

    private static void paramTest1() {
        System.out.println("________________________________________"); 
        System.out.println("⎢          AUFGABE 8.3 ParamHT          ⎢"); 
        System.out.println("⎢          Hashtable size < 31          ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 

        /* Class erstellen. Es gibt die Schlüsselmenge größe 14.  */
        
        HashFunction<String> hf = new HashFunction<String>();
        ParamHT<String , Boolean> s = new ParamHT<String , Boolean>(29, hf);
        resultPrint(1, true);

        /* Erwartet : Es wird String als KEY, Boolean(GradeZahl : True) als Value geschpeichert */
        for (int i = -10000 ; i < 10000 ; i++) {
            String k = "KEY" + i;
            boolean v = (i%2 == 0);
            s.insert(k, v);
        }
        /* Test für Insert */
        boolean flag = true;
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            if(s.get("KEY"+i) != tempB) {
                flag = false;
            }
        }
        resultPrint(2, flag);


        /* Erwartet : Alle Wert werden x2. (weil mit gleichen KEYs wieder gescpeichert. (Überschreiben)) */
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            s.insert("KEY"+i, !tempB); // reversed Result (Ungeradezahl : True)
        }

        flag = true;
        /* Test für Insert (Überschreiben) */
        for (int i = -10000 ; i < 10000 ; i++) {
            if(s.get("KEY"+i) != (i%2 != 0)) {
                flag = false;
            }
        }
        resultPrint(3, flag);

        /* Test für Remove */
        flag = s.remove("KEY"+1);        // Erwartet : TRUE, weil Key(1) existieren muss.
        resultPrint(4,flag);
        flag = (s.get("KEY"+1) == null);   // Erwartet : null, weil Key(1) schon gelöscht werden muss.
        resultPrint(5,flag);
        flag = !s.remove("KEY"+1);       // Erwartet : FALSE, weil Key(1) schon gelöscht werden muss.
        resultPrint(6, flag);
        
        System.out.println("⎢_______________________________________⎢"); 
    }

    private static void paramTest2() {
        System.out.println("________________________________________"); 
        System.out.println("⎢          AUFGABE 8.3 ParamHT          ⎢"); 
        System.out.println("⎢          Hashtable size > 31          ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 



        /* Class erstellen. Es gibt die Schlüsselmenge größe 14.  */
        
        HashFunction<String> hf = new HashFunction<String>();
        ParamHT<String , Boolean> s = new ParamHT<String , Boolean>(102, hf);
        resultPrint(1, true);

        /* Erwartet : Es wird String als KEY, Boolean(GradeZahl : True) als Value geschpeichert */
        for (int i = -10000 ; i < 10000 ; i++) {
            String k = "KEY" + i;
            boolean v = (i%2 == 0);
            s.insert(k, v);
        }
        /* Test für Insert */
        boolean flag = true;
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            if(s.get("KEY"+i) != tempB) {
                flag = false;
            }
        }
        resultPrint(2, flag);


        /* Erwartet : Alle Wert werden Boolean(Ungeradezahl : True).(Überschreiben)) */
        for (int i = -10000 ; i < 10000 ; i++) {
            boolean tempB = i%2 == 0;
            s.insert("KEY"+i, !tempB); // reversed Result (Ungeradezahl : True)
        }

        flag = true;
        /* Test für Insert (Überschreiben) */
        for (int i = -10000 ; i < 10000 ; i++) {
            if(s.get("KEY"+i) != (i%2 != 0)) {
                flag = false;
            }
        }
        resultPrint(3, flag);

        /* Test für Remove */
        flag = s.remove("KEY"+1);        // Erwartet : TRUE, weil Key(1) existieren muss.
        resultPrint(4,flag);
        flag = (s.get("KEY"+1) == null);   // Erwartet : null, weil Key(1) schon gelöscht werden muss.
        resultPrint(5,flag);
        flag = !s.remove("KEY"+1);       // Erwartet : FALSE, weil Key(1) schon gelöscht werden muss.
        resultPrint(6, flag);
        
        System.out.println("⎢_______________________________________⎢"); 
    }

    private static void collosionCount1() {
        System.out.println("________________________________________"); 
        System.out.println("⎢            Collosion TEST1            ⎢"); 
        System.out.println("⎢            Input : 20,000 Paar        ⎢"); 
        System.out.println("⎢           (RandomNR 1 ~ 20,000)       ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 

        SimpleHT s = new SimpleHT(32);
        GenHT<Integer,Integer> g = new GenHT<Integer,Integer>(32);

        HashFunction<String> hf = new HashFunction<String>();
        ParamHT<String,Integer> p = new ParamHT<String,Integer>(32, hf);
        
        Random r = new Random();
        /* Add all Pairs */
        for (int i = 0 ; i <= 20000 ; i++) {
            int nr = r.nextInt(20000);
            String key = (Integer.toString(nr)); // KEY : 1 , 2 , 3 .... 200000
            Integer val = nr;
            s.insert(nr, nr);
            g.insert(nr, val);
            p.insert(key, val);
        }

        /* TEST Simple */
        int[] setOfListsize = new int[32];
        int i = 0;
        for (ArrayList<Pair<Integer,Integer>> s_List : s.data) {
            setOfListsize[i] = s_List.size();
            i++;
        } 

        String rst = "⎢SimpleHT  Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }

        int result1 = (int)(evaluateDistribution(setOfListsize));

        if (result1 < 10) {
            System.out.println("\033[0;31m0"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }


        /* TEST Gen */
        setOfListsize = new int[32];
        i = 0;
        for (ArrayList<Pair<Integer,Integer>> g_List : g.data) {
            setOfListsize[i] = g_List.size();
            i++;
        } 

        rst = "⎢GenHT  Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        result1 = (int)(evaluateDistribution(setOfListsize));

        if (result1 < 10) {
            System.out.println("\033[0;31m"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }


        /* TEST Param mit Mul.Methode */
        setOfListsize = new int[32];
        i = 0;
        for (ArrayList<Pair<String,Integer>> p_List : p.data) {
            setOfListsize[i] = p_List.size();
            i++;
        } 

        rst = "⎢ParamHT Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        result1 = (int)(evaluateDistribution(setOfListsize));

        if (result1 < 10) {
            System.out.println("\033[0;31m"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }
        System.out.println("⎢_______________________________________⎢"); 


    }

    private static void collosionCount2() {

        System.out.println("________________________________________"); 
        System.out.println("⎢            Collosion TEST2            ⎢"); 
        System.out.println("⎢            Input : 20,000 Paar        ⎢"); 
        System.out.println("⎢           (Input ist alle 13*k)       ⎢"); 
        System.out.println("⎢_______________________________________⎢"); 

        SimpleHT s = new SimpleHT(26);
        GenHT<Integer,Integer> g = new GenHT<Integer,Integer>(26);

        HashFunction<String> hf = new HashFunction<String>();
        ParamHT<String,Integer> p = new ParamHT<String,Integer>(26, hf);
        
        Random r = new Random();
        /* Add all Pairs */
        for (int i = 0 ; i <= 20000 ; i++) {
            int nr = r.nextInt(20000)*13;
            String key = (Integer.toString(nr)); // KEY : 13 , 26 , 39 ....
            Integer val = nr;
            s.insert(nr, nr);
            g.insert(nr, val);
            p.insert(key, val);
        }

        /* TEST Simple */
        int[] setOfListsize = new int[26];
        int i = 0;
        for (ArrayList<Pair<Integer,Integer>> s_List : s.data) {
            setOfListsize[i] = s_List.size();
            i++;
        } 

        String rst = "⎢SimpleHT  Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }

        int result1 = (int)(evaluateDistribution(setOfListsize));

        if (result1 < 10) {
            System.out.println("\033[0;31m0"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }
        


        /* TEST Gen */
        setOfListsize = new int[26];
        i = 0;
        for (ArrayList<Pair<Integer,Integer>> g_List : g.data) {
            setOfListsize[i] = g_List.size();
            i++;
        } 

        rst = "⎢GenHT  Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        result1 = (int)(evaluateDistribution(setOfListsize));


        if (result1 < 10) {
            System.out.println("\033[0;31m0"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }


        /* TEST Param mit Mul.Methode */
        setOfListsize = new int[26];
        i = 0;
        for (ArrayList<Pair<String,Integer>> p_List : p.data) {
            setOfListsize[i] = p_List.size();
            i++;
        } 

        rst = "⎢ParamHT Deviation : ";
        System.out.print(rst);
        for (int rp = 0 ; rp < 35-rst.length() ; rp++) {
            System.out.print(" ");
        }
        result1 = (int)(evaluateDistribution(setOfListsize));


        if (result1 < 10) {
            System.out.println("\033[0;31m0"+result1+"%"+"\033[0m  ⎢");
        } else {
            System.out.println("\033[0;32m"+result1+"%"+"\033[0m  ⎢");
        }
        System.out.println("⎢_______________________________________⎢"); 
    }



    private static void resultPrint(int n, boolean pass) {

        String s = "⎢TEST #"+n;
        switch(n) {
            case 1 : s += " (Class create)"; break;
            case 2 : s += " (Insert & Get test)"; break;
            case 3 : s += " (Overwrite test)"; break;
            case 4 : s += " (Remove test)"; break;
            case 5 : s += " (Get by Empty test)"; break;
            case 6 : s += " (Remove for Empty test)"; break;
            default : s += " ";
        }

        System.out.print(s);
        for (int i = 0 ; i < 35-s.length() ; i++) {
            System.out.print(" ");
        }
        if (pass) {
            System.out.println("\033[0;32mPASS\033[0m ⎢");
        } else {
            System.out.println("\033[0;31mFAIL\033[0m ⎢");
        }
        

    }

    public static double evaluateDistribution(int[] numbers) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        

        for(int num : numbers){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if(min == max) {
            return 100.0; // All numbers are same, distribution is perfect
        }

        int sum = 0;
        for(int num : numbers){
            sum += num;
        }
        int avg = sum / numbers.length;

        double drc = 0;
        for(int num : numbers){
            int temp = (num - avg);
            temp = temp*temp;
            drc += temp;
        }

        double ZeroPercent = ((max-avg)*(max-avg)) + ((numbers.length-1)*(avg*avg));
        double result = ((1.0-(drc / ZeroPercent)) * 100);;
        if(result < 0) {
            return 0;
        }
        return result;
        
    }
}

    

