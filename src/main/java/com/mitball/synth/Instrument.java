package com.mitball.synth;

import java.util.HashMap;
import java.util.Vector;

public abstract class Instrument implements SampleGenerator
{
    private HashMap<Integer, Voice> noteToVoiceMap = new HashMap<Integer, Voice>();
    
    private Vector<Filter> filters = new Vector<Filter>();
    
    private static final int DEFAULT_NUM_VOICES = 8;
    
    private Voice [] voices;

    public Instrument(int numVoices)
    {
        voices = new Voice[numVoices];
        for (int i = 0; i < voices.length; i++)
        {
            voices[i] = createVoice();
        }
    }
    
    /**
     * 
     */
    public Instrument()
    {
        this(DEFAULT_NUM_VOICES);
    }
    
    /**
     * 
     * @param filter
     */
    public void addFilter(Filter filter)
    {
        filters.add(filter);
    }

    /**
     * 
     * @param noteNumber
     * @param velocity
     */
    public void sendNoteOn(int note, int velocity)
    {
        if (noteToVoiceMap.get(note) == null)
        {
            for(Voice voice : voices)
            {
                if (voice.isDone())
                {
                    System.out.println(System.currentTimeMillis() + " Adding Note on - " + note);
                    voice.start(note, velocity);
                    noteToVoiceMap.put(note, voice);
                    break;
                }
            }
        }
    }
    
    /**
     * 
     * @param noteNumber
     */
    public void sendNoteOff(int note)
    {
        Voice v = noteToVoiceMap.remove(note); 
        if (v != null)
        {
            System.out.println(System.currentTimeMillis() + " Removing Note on - " + v.getNote());
            v.stop();
        }
    }
    
    /**
     * 
     * @param controller
     * @param value
     */
    public void sendController(int controller, int value)
    {}
    
    /**
     * 
     * @return
     */
    abstract protected Voice createVoice();

    @Override
    public int tick()
    {
        int value = 0;
        
        for (Voice v : voices)
        {
            value += v.tick();
        }
        
        if (value != 0)
        {
            for (Filter f : filters)
            {
                value = f.tick(value);
            }
        }
        return value;
    }
    
    @Override
    public void fill(int[] buffer)
    {
        int length = buffer.length;
        
        for (Voice v : voices)
        {
            v.fill(buffer);
        }        
        
        for (Filter f : filters)
        {
            for (int i = 0; i < length; i++)
            {
                buffer[i] = f.tick(buffer[i]);
            }
        }
    }
}
