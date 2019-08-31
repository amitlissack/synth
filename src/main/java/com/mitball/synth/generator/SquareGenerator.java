package com.mitball.synth.generator;

public class SquareGenerator extends SinGenerator {
    private static final int OUT = (1 << 15) - 1;

    @Override
    public int tick() {
        return (super.tick() > 0) ? OUT : -OUT;
    }
}
