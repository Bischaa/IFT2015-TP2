package pedigree;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Coalescence {

	private Simulation simulC = new Simulation();
	private PriorityQueue<Sim> PA_m = new PriorityQueue<Sim>();
	protected HashMap<Double,Integer> PA_set_m = new HashMap<Double,Integer>();
	private PriorityQueue<Sim> PA_f = new PriorityQueue<Sim>();
	protected HashMap<Double,Integer> PA_set_f = new HashMap<Double,Integer>();
	
	public void init() {
		//On part la simulation pendant un certain temps
		simulC.simulate(30, 250); //Arbitraire
		
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
        
        //Tant qu'il reste plus d'un père
        while(populationH.size() > 1) {
        	//Si le père n'est pas encore dans l'ensemble PA, ajouter
        	if(!PA_m.contains(getYoungest(populationH).getFather())) {
        		PA_m.add(getYoungest(populationH));
        	//Si le père se retrouve déjà dans l'ensemble PA, créer un nouveau point de coalescence
        	} else {
        		PA_set_m.put(getYoungest(populationH).getBirthTime(), PA_m.size());
        	}
        	//À chaque itération, retirer l'individu le plus jeune
        	populationH.remove(populationH.indexOf(getYoungest(populationH)));
        }
        
      //Tant qu'il reste plus d'une mère
        while(populationF.size() > 1) {
        	//Si le père n'est pas encore dans l'ensemble PA, ajouter
        	if(!PA_f.contains(getYoungest(populationF).getMother())) {
        		PA_f.add(getYoungest(populationF));
        	//Si le père se retrouve déjà dans l'ensemble PA, créer un nouveau point de coalescence
        	} else {
        		PA_set_f.put(getYoungest(populationF).getBirthTime(), PA_f.size());
        	}
        	//À chaque itération, retirer l'individu le plus jeune
        	populationF.remove(populationF.indexOf(getYoungest(populationF)));
        }
	}
	
	//Méthode qui retourne l'index du plus jeune membre d'une population
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
