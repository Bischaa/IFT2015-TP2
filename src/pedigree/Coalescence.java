package pedigree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Coalescence {

	private Simulation simulC = new Simulation();
	private PriorityQueue<Sim> PA = new PriorityQueue<Sim>();
	private HashMap<Double,Integer> PA_set = new HashMap<Double,Integer>();
	
	public void init() {
		//On part la simulation pendant un certain temps
		simulC.simulate(50, 200); //Arbitraire
		
		//On copie la population finale pour chaque sexe de notre simulation
		ArrayList<Sim> populationH = new ArrayList<Sim>();
		ArrayList<Sim> populationF = new ArrayList<Sim>();
		
        for (Sim perso : simulC.population.heapMin) {
            if (perso.getSex() == pedigree.Sim.Sex.F) {
               populationF.add(perso);
            } else {
                populationH.add(perso);
            }
        }
        
        //Tant qu'il reste plus d'un p�re
        while(populationH.size() > 1) {
        	//Si le p�re n'est pas encore dans l'ensemble PA, ajouter
        	if(!PA.contains(getYoungest(populationH).getFather())) {
        		PA.add(getYoungest(populationH));
        	//Si le p�re se retrouve d�j� dans l'ensemble PA, cr�er un nouveau point de coalescence
        	} else {
        		PA_set.put(getYoungest(populationH).getBirthTime(), PA.size());
        	}
        	//� chaque it�ration, retirer l'individu le plus jeune
        	populationH.remove(populationH.indexOf(getYoungest(populationH)));
        }
	}
	
	//M�thode qui retourne l'index du plus jeune membre d'une population
	public Sim getYoungest(ArrayList<Sim> population) {
		Sim youngestM = population.get(0);

		for(int i = 0; i < population.size(); i++) {
			if(population.get(i).getBirthTime() < youngestM.getBirthTime()) {
				youngestM = population.get(i);
			}
		}
		return youngestM;
	}
	
	public static void main(String[] args) {
		Coalescence test = new Coalescence();
		test.init();
	}
	
}
