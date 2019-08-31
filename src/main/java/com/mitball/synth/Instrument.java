package com.mitball.synth;

import com.mitball.synth.filter.Filter;
import com.mitball.synth.generator.SampleGenerator;

import java.util.HashMap;
import java.util.Vector;

public abstract class Instrument implements SampleGenerator {
    private HashMap<Integer, Voice> noteToVoiceMap = new HashMap<Integer, Voice>();

    private Vector<Filter> filters = new Vector<Filter>();

    private static final int DEFAULT_NUM_VOICES = 8;

    private Voice[] voices;

    public Instrument(int numVoices) {
        voices = new Voice[numVoices];
        for (int i = 0; i < voices.length; i++) {
            voices[i] = createVoice();
        }
    }

    /**
     *
     */
    public Instrument() {
        this(DEFAULT_NUM_VOICES);
    }

    /**
     * @param filter
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void sendNoteOn(int note, int velocity) {
        if (noteToVoiceMap.get(note) == null) {
            for (Voice voice : voices) {
                if (voice.isDone()) {
                    voice.start(note, velocity);
                    noteToVoiceMap.put(note, voice);
                    break;
                }
            }
        }
    }

    public void sendNoteOff(int note) {
        Voice v = noteToVoiceMap.remove(note);
        if (v != null) {
            v.stop();
        }
    }

    public void sendController(int controller, int value) {

    }

    abstract protected Voice createVoice();

    public int tick() {
        int value = 0;

        for (Voice v : voices) {
            value += v.tick();
        }

        if (value != 0) {
            for (Filter f : filters) {
                value = f.tick(value);
            }
        }
        return value;
    }

    public void fill(int[] buffer) {
        int length = buffer.length;

        for (Voice v : voices) {
            v.fill(buffer);
        }

        for (Filter f : filters) {
            for (int i = 0; i < length; i++) {
                buffer[i] = f.tick(buffer[i]);
            }
        }
    }
}
