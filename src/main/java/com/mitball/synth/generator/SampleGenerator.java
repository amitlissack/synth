package com.mitball.synth.generator;

public interface SampleGenerator
{
    public int tick();
    
    public void fill(int[] buffer);

    public void reset();
}
