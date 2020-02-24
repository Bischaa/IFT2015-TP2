package pedigree;

import java.util.PriorityQueue; //Importe la classe de file de priorité pour les évènements
import java.util.Random;

public class Simulation {

    // Méthode qui exécute la simulation
    public void simulate(int n, double Tmax) {
        TasBinaire population = new TasBinaire();
        PriorityQueue<Event> eventQ = new PriorityQueue<Event>(); // File de priorité des évènements

        for (int i = 0; i < n; i++) {
            Sim fondateur = new Sim(randomSex()); // Sim fondateur
            Event E = new Event(fondateur, 0.0, "naissance"); // Naissance fondateur
            eventQ.add(E); // Insertion dans la file de priorité
        }

        while (!eventQ.isEmpty()) {
            Event E = eventQ.poll(); // Équivalent de deleteMin
            if (E.time > Tmax) {
                break; // Arrêt à Tmax
            }
            if (E.subject.getDeathTime() > E.time) { // L'évènement peut avoir lieu
                // Traiter les évènements

                switch (E.type) {
                    case "naissance": // Naissance

                        // Durée de vie du nouveau Sim
                        Random RND = new Random();
                        AgeModel vieSim = new AgeModel(); // Pour déterminer la durée de vie du nouveau Sim
                        double dureeVie = vieSim.randomAge(RND);
                        double deathtime = E.time + dureeVie;
                        eventQ.add(new Event(E.subject, deathtime, "mort")); // Ajout de la mort du Sim

                        // Attente de reproduction
                        if (E.subject.getSex() == Sim.Sex.F) {
                            double parentHood = vieSim.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F,
                                    Sim.MAX_MATING_AGE_F);
                            double waitingTime = AgeModel.randomWaitingTime(RND, 2.0 / parentHood); // Temps avant
                                                                                                    // reproduction
                            eventQ.add(new Event(E.subject, E.time + waitingTime, "reproduction")); // Nouvel évènement
                        }

                        // Enregistrement du nouveau Sim dans la population
                        population.insert(E.subject);
                        break;

                    case "reproduction": // Reproduction

                        break;

                    case "selection": // Sélection (pour le père)

                        break;

                    default: // Mort du sim
                        /*
                         * On doit seulement faire deleteMin, car la population est triée par temps de
                         * mort. Donc le min est celui qui décède
                         */
                        population.deleteMin();
                        break;
                }
            }

            // Sinon on ne fait rien, car le Sim est mort
        }
    }

    // Méthode pour obtenir le sex d'un Sim au hasarc
    public Sim.Sex randomSex() {
        Random rnd = new Random();

        // 50% de chance d'avoir un homme
        if (rnd.nextDouble() <= 0.5) {
            return Sim.Sex.F;
        } else {
            return Sim.Sex.M;
        }

    }

}
