package com.mitball.synth.filter;

import com.mitball.synth.AudioParams;
import com.mitball.synth.generator.SinGenerator;

public class GainFilter implements Filter
{
    int gain = 1;
    
    public GainFilter(int gain)
    {
        this.gain = gain;
    }
    
    public int tick(int in)
    {
        return (in * gain) >> AudioParams.RAW_MAX_BITS;
    }
    
    public static void main(String[] args) 
    {
        SinGenerator s = new SinGenerator();
        s.setFrequency(440);
        GainFilter d = new GainFilter(AudioParams.RAW_MAX / 2);
        for (int i = 0; i < 1000; i++)
        {
            int a = s.tick();
            int p = d.tick(a);
            System.out.println(a + "," + p);
        }        
    }
}
