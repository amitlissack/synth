package com.mitball.synth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sequence implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Sequence.class.getName());

    private ArrayList<Event> events = new ArrayList<Event>();
    private Thread thread = null;
    private boolean stop = false;
    private static final int SLEEP_TIME = 5;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public Sequence() {
    }

    public void add(Event e) {
        synchronized (events) {
            events.add(e);
        }
    }

    public void start() {
        synchronized (events) {
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        }
    }

    public void stop() {
        stop = true;
    }

    public void addListener(Sequence.Listener l) {
        synchronized (listeners) {
            listeners.add(l);
        }
    }

    public void run() {
        int index = 0;

        Collections.sort(events, new Comparator<Event>() {
            public int compare(Event arg0, Event arg1) {
                return arg0.getTime() - arg1.getTime();
            }
        });

        final long startTime = System.currentTimeMillis();

        while (!stop) {
            try {
                Event e = events.get(index);
                long now = System.currentTimeMillis() - startTime;
                while (e.getTime() <= now) {
                    e.fire();
                    index++;
                    e = events.get(index);
                }

                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "Error", e);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.log(Level.SEVERE, "Error", e);
                break;
            }
        }
        thread = null;
        synchronized (this) {
            this.notifyAll();
        }
        synchronized (listeners) {
            for (Listener l : listeners)
                l.onSequenceComplete(this);
        }
    }

    /**
     * @author amitlissack
     */
    public abstract class Event {
        private int time;

        public Event(int t) {
            time = t;
        }

        public int getTime() {
            return time;
        }

        abstract public void fire();
    }

    /**
     * @author amitlissack
     */
    public class NoteOnEvent extends Event {
        private int note;
        private int velocity;
        private Instrument instrument;

        public NoteOnEvent(int t, int note, int vel, Instrument i) {
            super(t);
            this.note = note;
            this.velocity = vel;
            this.instrument = i;
        }

        @Override
        public void fire() {
            instrument.sendNoteOn(note, velocity);
        }
    }

    /**
     * @author amitlissack
     */
    public class NoteOffEvent extends Event {
        private int note;
        private Instrument instrument;

        public NoteOffEvent(int t, int note, Instrument i) {
            super(t);
            this.note = note;
            this.instrument = i;
        }

        @Override
        public void fire() {
            instrument.sendNoteOff(note);
        }
    }

    public interface Listener {
        public void onSequenceComplete(Sequence s);
    }
}
