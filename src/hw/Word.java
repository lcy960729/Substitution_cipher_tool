package hw;

public class Word {
    private String word_name;
    private String plain_word;
    private int count;

    public Word(String word_name, int count) {
        this.word_name = word_name;
        this.count = count;
        this.plain_word = word_name;
    }

    public String getWord_name() {
        return word_name;
    }

    public int getCount() {
        return count;
    }

    public String getPlain_word() {
        return plain_word;
    }

    public void setPlain_word(String plain_word) {
        this.plain_word = plain_word;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
