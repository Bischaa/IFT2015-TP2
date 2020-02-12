package pedigree;

import java.util.Comparator;

public class TasBinaire implements Comparator<Sim>{
	
	//On compare 2 sims selon leur temps de mort
	public int compare(Sim o1, Sim o2) {
		return (int) Math.abs(o1.getDeathTime() - o2.getDeathTime());
	}
	
	
	//M�thode pour trier le tas binaire
	public void sortHeap() {
		
	}
}
