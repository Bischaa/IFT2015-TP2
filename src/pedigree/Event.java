package pedigree;

import pedigree.Sim;
import java.lang.Comparable;

public class Event implements Comparable<Event> {

    // Attributs
    protected double time; // Temps de l'évènement
    protected Sim subject; // Sujet de l'évènement
    protected String type; // Type de l'évènement

    // Constructeur
    protected Event(Sim subject, double time, String type) {
        this.subject = subject;
        this.time = time;
        this.type = type;
    }

    // Méthode de comparaison entre les évènements
    @Override
    public int compareTo(Event e) {
        return Double.compare(this.time, e.time);
    }

}