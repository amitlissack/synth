package com.mitball.synth;

public class NoteNames {
    public static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public static String getNoteName(int n) {
        return NOTE_NAMES[n % 12];
    }

    public static String getNoteNameWithOctave(int n) {
        return NOTE_NAMES[n % 12] + Integer.toString((n / 12) - 1);
    }
}
