package com.mitball.synth;

public class AudioParams
{
    private static int sampleRate = 22050;
    private static int bitsPerSample = 8;
    private static int numChannels = 1;
    
    public static final int RAW_MAX_BITS = 15;
    public static final int RAW_MAX = (1 << (RAW_MAX_BITS)) - 1;
    
    public static void setSampleRate(int val)
    {
        sampleRate = val;
    }
    
    public static int getSampleRate()
    {
        return sampleRate;
    }

    public static void setBitsPerSample(int val)
    {
        bitsPerSample = val;
    }

    public static int getBitsPerSample()
    {
        return bitsPerSample;
    }

    public static void setNumChannels(int val)
    {
        numChannels = val;
    }

    public static int getNumChannels()
    {
        return numChannels;
    }
}
