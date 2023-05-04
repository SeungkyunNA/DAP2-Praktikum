import java.util.Arrays;

public class MaxHeap {

    int capacity; // Attribut für Kapazität
    int size;     // Attribut für aktuellen Große
    int[] heap;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = -1;
        heap = new int[capacity];
    }

    /* gibt 'size' zurück */
    public int getSize() {
        return size+1;
    }
    /* gibt 'capacity' zurück */
    public int getCapacity() {
        return capacity;
    }
    /* Kopiere 0 bis 'size' von heap[] und gibt zurück */
    public int[] getValues() {
        int[] result = new int[size];
        for (int i=0;i<size;i++) {
            result[i] = heap[i];
        }
        return result;
    }
    
    public void add(int value) throws IllegalStateException {

        size++;
        

        /* es wird Exception geworfen wenn 'size' gleich 'capcity' ist. (Heap ist schon voll) */
        if (size == capacity) {
            throw new IllegalStateException();
        }

        /* Speiche 'value' erst im neuen 'Leaf node' */
        heap[size] = value;
        
        int i = size;
        int p = i/2;
        while(p > 0){
            /* Vertauschen, wenn 'parent node' kleiner als neues 'value' ist. */
            if (heap[p] < heap[size]) {
                int temp = heap[p];
                heap[p] = heap[size];
                heap[size] = temp;

                i = p; /* führe weiter aus */
            }
            
            /* Setze p an der position des 'Parent node' */
            p = i/2; 
        }

    }
    
    /* Hilfsfunktion. um zu ermöglichen, dass ein Array direkt im Heap speichern */
    public void add(int[] values) {
        for (int v : values){
            add(v);
        }
    }
    
    public int extractMax() throws IllegalStateException {
        /* es wird Exception geworfen wenn size gleich 0 ist. (Es gibt kein Knote in Heap) */
        if (size == -1) {
            throw new IllegalStateException();
        }

        /* Wenn im Heap nur ein node speichert, gibt sofort zurück */
        if (size == 0) {
            size--;
            return heap[0];    
        }
        
        /*  Speichere A[0](aktuelle Maxvalue) und setze in A[0] value von A[size](last Node).*/
        int result = heap[0];
        heap[0] = heap[size];
        size--; // Da extrahieren soll, 'size' wird um 1 verringert.
        
        maxHeapify(0);

        return result;

    }
    public int peekMax() throws IllegalStateException {
        /* es wird Exception geworfen wenn size gleich 0 ist. (Es gibt kein Knote in Heap) */
        if (size == -1) {
            throw new IllegalStateException();
        }

        /* Maxvalue steht immer an der Position 0 im Heap. */
        return heap[0];    
    }
    public void maxHeapify(int i) {
        
        /* setze positionen von Parent node  */
        int left = 2*i;
        int right = (2*i) + 1;

        
        if (left > size) { /* Falls A[i] ein Blatt ist */
            return;
        } else if (right > size) { /* Falls nur left child existiert */

            if (heap[left] > heap[i]){
                /* Swap i <> left */
                int temp = heap[left];
                heap[left] = heap[i];
                heap[i] = temp;

                maxHeapify(left);
            }

        } else {

            int bigger;
            if (heap[left] > heap[right]){ /* setze an 'bigger' der Position des größeren Kinds  */
                bigger = left;
            } else {
                bigger = right;
            }

            /* Swap i <> bigger */
            int temp = heap[bigger];
            heap[bigger] = heap[i];
            heap[i] = temp;

            maxHeapify(bigger);
        }
        return;
    }
}
