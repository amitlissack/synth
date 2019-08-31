package com.mitball.synth.generator;

public interface SampleGenerator {
    int tick();

    void fill(int[] buffer);

    void reset();
}
