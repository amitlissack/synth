package com.mitball.synth.filter;

import com.mitball.synth.AudioParams;

public class Envelope implements Filter {
    private int attackTime = 0;
    private int decayTime = 0;
    private int sustainLevel = AudioParams.RAW_MAX;
    private int releaseTime = 0;

    private int releaseLevel = 0;
    private int lastLevel = 0;

    private int counter = 0;

    private int state = IDLE;

    private static final int IDLE = 0;
    private static final int ATTACK = 1;
    private static final int DECAY = 2;
    private static final int SUSTAIN = 3;
    private static final int BEGIN_RELEASE = 4;
    private static final int RELEASE = 5;

    public Envelope() {
    }

    public Envelope(int attackTime, int decayTime, int sustainLevel, int releaseTime) {
        this.attackTime = Math.max((attackTime * AudioParams.getSampleRate()) / 1000, 1);
        this.decayTime = Math.max((decayTime * AudioParams.getSampleRate()) / 1000, 1);
        this.sustainLevel = sustainLevel;
        this.releaseTime = Math.max((releaseTime * AudioParams.getSampleRate()) / 1000, 1);
    }

    public void reset() {
        state = IDLE;
        counter = 0;
    }

    public void start() {
        state = ATTACK;
        counter = 0;
    }

    public void release() {
        state = BEGIN_RELEASE;
    }

    public boolean isDone() {
        return state == IDLE;
    }

    public int tick(int in) {
        int newLevel = 0;
        switch (state) {
            case IDLE:
                break;
            case ATTACK: {
                counter++;
                newLevel = (counter * AudioParams.RAW_MAX) / attackTime;
                if (counter >= attackTime) {
                    state = DECAY;
                    counter = 0;
                }
                break;
            }
            case DECAY: {
                counter++;
                if (counter >= decayTime) {
                    newLevel = sustainLevel;
                    state = SUSTAIN;
                    counter = 0;
                } else {
                    newLevel = AudioParams.RAW_MAX - (((AudioParams.RAW_MAX - sustainLevel) * counter) / decayTime);
                }
                break;
            }
            case SUSTAIN: {
                newLevel = sustainLevel;
                break;
            }
            case BEGIN_RELEASE:
                counter = 0;
                newLevel = lastLevel;
                releaseLevel = lastLevel;
                state = RELEASE;
                break;
            case RELEASE: {
                counter++;
                if (counter >= releaseTime) {
                    newLevel = 0;
                    state = IDLE;
                    counter = 0;
                } else {
                    newLevel = releaseLevel - ((releaseLevel * counter) / releaseTime);
                }
                break;
            }
            default:
                break;
        }
        lastLevel = newLevel;
        return (in * newLevel) >> AudioParams.RAW_MAX_BITS;
    }
}
