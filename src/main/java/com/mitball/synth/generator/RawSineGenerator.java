package com.mitball.synth.generator;

import com.mitball.synth.AudioParams;

public class RawSineGenerator extends ToneGenerator {
    int counter = 0;

    double a = 0;

    @Override
    public void setFrequency(double frequency) {
        super.setFrequency(frequency);

        a = (2 * Math.PI * frequency) / AudioParams.getSampleRate();
    }

    public int tick() {
        return (int) (32768.0 * Math.sin(a * counter++));
    }

    public void reset() {
        counter = 0;
    }
}
