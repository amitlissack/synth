package com.mitball.synth.generator;

import com.mitball.synth.AudioParams;

public class SinGenerator extends ToneGenerator
{
    private static final int QUAD_TABLE_MAX_INDEX = 127;
    private static final int QUAD_TABLE_SIZE = 128;
    private static final int TABLE_SIZE = 512;
    private int counter = 0;
    private int step = 0;
    private static final int STEP_DVIBITS = 8;
    private static final int STEP_DIV = 1 << STEP_DVIBITS; 
    
    public SinGenerator()
    {
        super();
    }
    
    @Override
    public void setFrequency(double frequency)
    {
        super.setFrequency(frequency);
        
        step = (int) ((TABLE_SIZE * frequency * STEP_DIV) / AudioParams.getSampleRate());
    }

    public int tick()
    {
        return getSample(((counter++ * step)/STEP_DIV) % TABLE_SIZE);
    }
    
    private int getSample(int index)
    {
        int quad = index / QUAD_TABLE_SIZE;
        int val = 0;
        
        switch (quad)
        {
        case 0: 
            val = quadSinTable[index % QUAD_TABLE_SIZE];
            break;
        case 1:
            val = quadSinTable[QUAD_TABLE_MAX_INDEX - (index % QUAD_TABLE_SIZE)];
            break;
        case 2:
            val = -quadSinTable[index % QUAD_TABLE_SIZE];
            break;
        case 3:
            val = -quadSinTable[QUAD_TABLE_MAX_INDEX - (index % QUAD_TABLE_SIZE)];
            break;
        }
        return val;
    }
    
    public void reset()
    {
        counter = 0;
    }
 
    private final static int quadSinTable[] = new int[] { 0, 402, 804, 1206,
            1607, 2009, 2410, 2811, 3211, 3611, 4011, 4409, 4808, 5205, 5602,
            5997, 6392, 6786, 7179, 7571, 7961, 8351, 8739, 9126, 9512, 9896,
            10278, 10659, 11039, 11416, 11793, 12167, 12539, 12910, 13278,
            13645, 14010, 14372, 14732, 15090, 15446, 15800, 16151, 16499,
            16846, 17189, 17530, 17869, 18204, 18537, 18868, 19195, 19519,
            19841, 20159, 20475, 20787, 21097, 21403, 21706, 22005, 22301,
            22594, 22884, 23170, 23453, 23732, 24007, 24279, 24547, 24812,
            25073, 25330, 25583, 25832, 26077, 26319, 26557, 26790, 27020,
            27245, 27466, 27684, 27897, 28106, 28310, 28511, 28707, 28898,
            29086, 29269, 29447, 29621, 29791, 29956, 30117, 30273, 30425,
            30572, 30714, 30852, 30985, 31114, 31237, 31357, 31471, 31581,
            31685, 31785, 31881, 31971, 32057, 32138, 32214, 32285, 32351,
            32413, 32469, 32521, 32568, 32610, 32647, 32679, 32706, 32728,
            32745, 32758, 32765 };   
    
    public static void main(String[] args) 
    {
        SinGenerator s = new SinGenerator();
        s.setFrequency(440);
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(s.tick());
        }
    }
}
