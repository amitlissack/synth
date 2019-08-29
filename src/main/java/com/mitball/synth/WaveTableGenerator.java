package com.mitball.synth;

public class WaveTableGenerator extends ToneGenerator
{
    private int counter = 0;
    private int step = 0;

    private static final int STEP_DVIBITS = 8;
    private static final int STEP_DIV = 1 << STEP_DVIBITS; 
    
    private int [] table;
    
    public WaveTableGenerator(int [] table)
    {
        this.table = table;
    }

    @Override
    public void setFrequency(double frequency)
    {
        super.setFrequency(frequency);
        
        step = (int) ((table.length * frequency * STEP_DIV) / AudioParams.getSampleRate());
    }
    
    public int tick()
    {
        int preDivStep = (counter++ * step);
        int rawIndex = preDivStep >> STEP_DVIBITS;
        int remainder = preDivStep % STEP_DIV;
        
        //Index of present note in wave table
        int index = rawIndex % table.length;
        //Index of next entry in wave table
        int indexNext = (rawIndex  + 1) % table.length;
        
        //Value at index
        int value = table[index];
        //Value at next entry in wave table
        int valueNext = table[indexNext];
        
        //Interpolation offset. Difference between present and next * remainder.
        int interpolation = ((valueNext - value) * remainder) >> STEP_DVIBITS;

//        System.out.println(String.format("%d, %d, %d, %d, %d, %d", rawIndex, remainder, value, valueNext, interpolation, value+interpolation));
        
        return value+interpolation;

        //No interpolation
        //        return table[(((counter++ * step)/STEP_DIV) % table.length)];
    }
    
    @Override
    public void reset()
    {
        counter = 0;
    }
}
