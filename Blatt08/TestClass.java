public class TestClass {
    public static void main(String[] args) {
        simpleTest();
        genTest();
        paramTest1();
        paramTest2();

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
    
}
