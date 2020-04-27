package hw;

public class Word {
    String word_name;
    int count;

    public Word(String word_name, int count) {
        this.word_name = word_name;
        this.count = count;
    }

    public String getWord_name() {
        return word_name;
    }

    public int getCount() {
        return count;
    }
}
