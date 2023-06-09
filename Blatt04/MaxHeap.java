public class MaxHeap {

    int capacity; // Attribut für Kapazität
    int size;     // Attribut für aktuellen Große
    int[] heap;

    public MaxHeap(int capacity) {
        this.capacity = capacity; // Index wird 0 bis c-1 
        this.size = 0;           // Index wird 0 bis s-1 
        heap = new int[capacity];
    }

    /* gibt 'size' zurück */
    public int getSize() {
        return size;
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
        if (size == capacity+1) {
            throw new IllegalStateException();
        }

        /* Speiche 'value' erst im neuen 'Leaf node' */
        heap[size-1] = value;
        
        int i = size - 1;
        int p = i/2;
        while(p >= 0){
            /* Vertauschen, wenn 'parent node' großer als neues 'value' ist. */
            if (heap[p] < heap[i]) {
                int temp = heap[p];
                heap[p] = heap[i];
                heap[i] = temp;

                i = p; /* führe weiter aus */
                /* Setze p an der position des 'Parent node' */
                p = i/2; 
            } else {
                break;
            }
        }

    }
    
    /* Hilfsfunktion. um zu ermöglichen, dass ein Array direkt im Heap speichern */
    public void buildMax(int[] values) {
        for (int v=0 ; v<values.length ; v++){
            heap[v] = values[v];
            size++;
        }
        for(int i = size/2;i>=0;i--){
            maxHeapify(i);
        }

    }
    
    public int extractMax() throws IllegalStateException {
        /* es wird Exception geworfen wenn size gleich 0 ist. (Es gibt kein Knote in Heap) */
        if (size == 0) {
            throw new IllegalStateException("Heap is empty. cannot extract");
        }

        /* Wenn im Heap nur ein node speichert, gibt sofort zurück */
        if (size == 1) {
            size--;
            return heap[0];    
        }
        
        /*  Speichere A[0](aktuelle Maxvalue) und setze in A[0] value von A[size](last Node).*/
        int result = heap[0];
        heap[0] = heap[size-1];
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

        if (i < 0 || i > size) {
            System.err.println("Maxheapify Error");
            return;
        }

        int parent = i;
        int left = (i*2)+1;
        int right = (i*2)+2;

        if (left < size && heap[left] > heap[parent]) {
            parent = left;
        }
        if (right < size && heap[right] > heap[parent]){
            parent = right;
        }
        if (parent != i) {
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;
            maxHeapify(parent);
        }

    }
}