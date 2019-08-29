package com.mitball.synth;

public interface SampleGenerator
{
    public int tick();
    
    public void fill(int[] buffer);

    public void reset();
}
