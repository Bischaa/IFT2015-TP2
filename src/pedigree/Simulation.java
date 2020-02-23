package pedigree;

import pedigree.Sim.Sex; //On importe la classe Sim
import java.util.PriorityQueue; //Importe la classe de file de priorité pour les évènements
import java.util.Random;

public class Simulation {

    // Méthode qui exécute la simulation
    void simulate(int n, double Tmax) {
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

                        break;

                    case "reproduction": // Reproduction

                        break;

                    default: // Sélection (pour le père)
                        break;
                }
            }

            // Sinon on ne fait rien, car le Sim est mort
        }
    }

    // Méthode pour obtenir le sex d'un Sim au hasarc
    Sim.Sex randomSex() {
        Random rnd = new Random();

        // 50% de chance d'avoir un homme
        if (rnd.nextDouble() <= 0.5) {
            return Sim.Sex.F;
        } else {
            return Sim.Sex.M;
        }

    }

    // Méthode de traitement de naissance

    // Méthode de traitement de reproducton

    // Méthode de traitement de sélection

}
