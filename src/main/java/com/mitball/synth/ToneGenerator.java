package com.mitball.synth;

public abstract class ToneGenerator implements SampleGenerator
{
    protected double frequency = 0;
    
    /**
     * 
     * @param frequency
     */
    public ToneGenerator()
    {
    }
    
    public double getFrequency() 
    {
        return frequency;
    }
    
    public void setFrequency(double frequency)
    {
        this.frequency = frequency;
    }
    
    @Override
    public void fill(int[] buffer)
    {
        int length = buffer.length;
        for (int i = 0; i < length; i++)
        {
            buffer[i] += tick();
        }
    }
}
