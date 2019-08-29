package com.mitball.synth.generator;

public class SquareGenerator extends SinGenerator
{
    private static final int OUT = (1 << 15) - 1;
    
    @Override
    public int tick()
    {
        return (super.tick() > 0) ? OUT : -OUT;
    }
    
    public static void main(String[] args) 
    {
        SquareGenerator s = new SquareGenerator();
        s.setFrequency(440);
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(s.tick());
        }
    }
}
