package com.mitball.synth.filter;

import com.mitball.synth.AudioParams;

public class GainFilter implements Filter {
    int gain = 1;

    public GainFilter(int gain) {
        this.gain = gain;
    }

    public int tick(int in) {
        return (in * gain) >> AudioParams.RAW_MAX_BITS;
    }
}
