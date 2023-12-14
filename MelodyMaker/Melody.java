import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody {
    private Queue<Note> notes;

    public Melody(Queue<Note> song) {
        this.notes = song;
    }

    public double getTotalDuration() {
        int size = this.notes.size();
        boolean isFirstRep = true;
        double duration = 0.0;
        Queue<Note> repNotes = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            Note currentNote = this.notes.poll();
            duration += currentNote.getDuration();
            if (!isFirstRep) {
                repNotes.offer(currentNote);
            }
            if (currentNote.isRepeat()) {
                if (isFirstRep) {
                    isFirstRep = false;
                    duration += currentNote.getDuration();
                } else {
                    while (!repNotes.isEmpty()) {
                        duration += repNotes.poll().getDuration();
                    }
                    
                }
            }
            this.notes.offer(currentNote);
        }
        return duration;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < this.notes.size(); i++) {
            Note currentNote = this.notes.poll();
            output += currentNote.toString() + "\n";
            this.notes.offer(currentNote);
        }
        return output;
    }

    public void changeTempo(double tempo) {
        Queue<Note> temp = this.notes;
        int size = temp.size();
        for (int i = 0; i < size; i++) {
            Note currentNote = temp.poll();
            currentNote.setDuration(currentNote.getDuration() / tempo);
            temp.offer(currentNote);
        }
    }

    public void reverse() {
        Stack<Note> notesStack = new Stack<Note>();
        while (!notes.isEmpty()) {
            notesStack.push(notes.poll());
        }
        while (!notesStack.isEmpty()) {
            notes.offer(notesStack.pop());
        }
    }

    public void append(Melody other) {
        Queue<Note> otherNotes = other.getNotes();
        for (int i = 0; i < otherNotes.size(); i++) {
            notes.offer(otherNotes.peek());
            otherNotes.offer(otherNotes.poll());
        }
    }

    public void play() {
        boolean isFirstRep = true;
        Queue<Note> repNotes = new LinkedList<>();
        for (int i = 0; i < this.notes.size(); i++) {
            Note currentNote = this.notes.poll();
            currentNote.play();
            if (!isFirstRep) {
                repNotes.offer(currentNote);
            }
            if (currentNote.isRepeat()) {
                if (isFirstRep) {
                    isFirstRep = false;
                    currentNote.play();
                } else {
                    while (!repNotes.isEmpty()) {
                        repNotes.poll().play();
                    }
                    
                }
            }
            this.notes.offer(currentNote);
        }
    }

    public Queue<Note> getNotes() {
        return this.notes;
    }
    
}
