package com.mitball.synth.filter;

import com.mitball.synth.AudioParams;

public class DynamicsCompressor implements Filter {
    private int threshold = AudioParams.RAW_MAX;
    private int ratio = 1;

    public DynamicsCompressor(int threshold, int ratio) {
        this.threshold = threshold;
        this.ratio = Math.max(1, ratio);
    }

    public int tick(int in) {
        if (in > threshold) {
            in = threshold + ((in - threshold) / ratio);
        } else if (in < -threshold) {
            in = -threshold + ((in + threshold) / ratio);
        }
        return in;
    }
}
