package pedigree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TasBinaire implements Comparator<Sim>{
	
	//ArrayList qui gardera en mémoire nos sims
	ArrayList<Sim> heapMin = new ArrayList<Sim>();
	
	//On compare 2 sims selon leur temps de mort
	public int compare(Sim o1, Sim o2) {
		if(o1.getDeathTime() == o2.getDeathTime()) {
			return 0;
		}
		if(o1.getDeathTime() < o2.getDeathTime()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	//Méthode pour trouver les enfants d'un certain sim
	public Sim[] getChild(Sim o) {
		Sim[] children = new Sim[2];
		for(int i = 0; i < heapMin.size(); i++) {
			if (o == heapMin.get(i)) {
				children[0] = heapMin.get(i*2+1);
				children[1] = heapMin.get(i*2+2);
			}
		}
		return children;
	}
	
	//Méthode pour trouver le parent d'un certain sim
	public Sim getParent(Sim o) {
		Sim parent = null;
		for(int i = 0; i < heapMin.size(); i++) {
			if(o == heapMin.get(i)) {
				parent = heapMin.get((i-1)/2);
			}
		}
		return parent;
	}
	
	//Méthode insert
	public void insert(Sim o) {
		heapMin.add(0, o);
		sink();
	}
	
	//Méthode peek
	public Sim peek() {
		return heapMin.get(0);
	}
	
	//Méthode deleteMin()
	public void deleteMin() {
		heapMin.remove(0);
		//Incomplet
	}
	
	//Méthode pour swim dans le tas, basé sur le modèle vu lors de la Démo 3
	public void swim(int i) {
		int indexParent;
		while(i > 0) {
			indexParent = ((i-1)/2);
			if(this.compare(heapMin.get(i), heapMin.get(indexParent)) == -1) {
				Collections.swap(heapMin, i, indexParent);
				i = indexParent;
			}
			else {
				break;
			}
		}
	}
	
	//Méthode pour sink dans le tas à partir de la tête, basé sur le modèle vu lors de la Démo 3
	public void sink() {
		int childIndex;
		int i = 0; //Tête du tas
		while(i < heapMin.size()) {
			i = i*2;
			childIndex = (this.compare(heapMin.get((i*2)+1), heapMin.get((i*2)+2)) == -1) ? i*2+1 : i*2+2;
			if(this.compare(heapMin.get(childIndex), heapMin.get(i)) == -1) {
				Collections.swap(heapMin, childIndex, i);
			}
			else {
				break;
			}
		}
	}
	
}
