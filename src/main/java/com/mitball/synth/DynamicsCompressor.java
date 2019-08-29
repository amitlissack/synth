package com.mitball.synth;

public class DynamicsCompressor implements Filter
{
    private int threshold = AudioParams.RAW_MAX;
    private int ratio = 1;
    
    public DynamicsCompressor(int threshold, int ratio)
    {
        this.threshold = threshold;
        this.ratio = Math.max(1, ratio);
    }

    public int tick(int in)
    {
        if (in > threshold)
        {
            in = threshold + ((in - threshold) / ratio);
        }
        else if (in < -threshold)
        {
            in = -threshold + ((in + threshold) / ratio);
        }
        return in;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Instrument instrument = new Instrument() 
        {
            @Override
            protected Voice createVoice()
            {
                return new Voice(new SinGenerator(), new Envelope(50, 0, AudioParams.RAW_MAX, 250));
            }
            @Override
            public void reset() {}
        };
//        instrument.addFilter(new GainFilter(AudioParams.RAW_MAX / 3));
//        instrument.addFilter(new DynamicsCompressor((AudioParams.RAW_MAX * 7 / 8), 5));
        
        instrument.sendNoteOn(60, 100);
        instrument.sendNoteOn(64, 100);
        instrument.sendNoteOn(67, 100);
        instrument.sendNoteOn(71, 100);
        
        for (int i = 0; i < 5000; i++)
        {
            int a = instrument.tick();
            System.out.println(a);
        }
    }

}
