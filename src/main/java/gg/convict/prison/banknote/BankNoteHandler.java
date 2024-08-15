package gg.convict.prison.banknote;

import lombok.Getter;
import lombok.Setter;
import gg.convict.prison.util.configuration.StaticConfiguration;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class BankNoteHandler implements StaticConfiguration {

    private final List<BankNote> notes = new ArrayList<>();
    private int announceThreshold = 25000000;

    public void addNote(BankNote note) {
        notes.add(note);
    }

    public void removeNote(BankNote note) {
        notes.remove(note);
    }

}