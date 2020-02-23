package pedigree;

import pedigree.Sim;

public class Event {

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

}