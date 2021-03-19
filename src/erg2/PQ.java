package erg2;


public class PQ {
	
	private int size;
	private City heap[];
	private int ids[];
	
	public PQ(int maxN,int maxID) {
		
		heap=new City[maxN+1];
		ids=new int[maxID];
		size=0;
		
	}
	
	private void swim(int k) {
		
		while (k > 1 && heap[k/2].compareTo(heap[k])<0) {
		exch(k, k/2); k = k/2; } 
		
	}
	
	private void sink(int k) {
		
		while (2*k <= size) {
		int j = 2*k;
		if (j < size && heap[j].compareTo(heap[j+1])<0) j++;
		if (heap[k].compareTo(heap[j])>=0) break;
		exch(k, j); k = j; }
		
		} 
	
	void exch(int i, int j) {
		int l = ids[heap[i].getID()-1];
		ids[heap[i].getID()-1] = ids[heap[j].getID()-1];
		ids[heap[j].getID()-1] = l;
		City t = heap[i];
		heap[i] = heap[j];
		heap[j] = t;
	}

	
	boolean isEmpty() {
		
		return size==0;
	
	}
	
	int size() {
		
		return size;
		
	}
	
	void resize() {
		
		City[] newHeap = new City[heap.length*2-1];
		for (int i = 0; i <= size; i++) {
			newHeap[i] = heap[i];
		}
		heap = newHeap;		
	}
	
	void insert(City x) {
		
		if (size >= (3/4)*(heap.length - 1))
            resize();
		heap[++size] = x;
		ids[x.getID()-1] = size;
		swim(size);
		
	}
	
	City max() {
		
		return heap[1];
		
	}
	
	City getmax() {
		
		City max = heap[1];
        heap[1] = heap[size];
        ids[heap[size].getID()-1] = 1;
        ids[max.getID()-1] = 0;
        size--;
        sink(1);
        return max;
		
	}
	
	
	City remove(int id) {
		
		City city = heap[ids[id-1]];
        heap[ids[id-1]] = heap[size];
        ids[heap[size].getID()-1] = ids[id-1];
        int index =ids[id-1];
        ids[id-1] = 0;
        size--;
        sink(index);
        return city;
        
	}
	
	void DynamicInsert(City o, int k) {
		if (k<=size) {
			for (int i=size;i>0;i--) {
				if(o.compareTo(heap[i]) > 0) {
					remove(heap[i].getID()-1);
					insert(o);
					break;
				}
			}
		}
		else insert(o);
		}
}
