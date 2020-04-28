package hw;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;
import java.util.function.Predicate;


public class Controller implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private TableView<Word> word_tableView;
    @FXML
    private TableColumn<Word, String> wc_1, wc_2, wc_3;
    private ObservableList<Word> wordList;
    private FilteredList<Word> wordFilteredList;

    @FXML
    private TextArea cipher_textArea, plain_textArea;

    @FXML
    private TextField filterWordLength_tf, filterPlainWord_tf;

    private Hashtable<Character, String> key_table;
    private Hashtable<String, Integer> frequency_words_table;
    private Hashtable<Character, Integer> frequency_alphabet_table;

    private TextField[] textFields;
    private Label[] alphabet_labels;
    private Label[] frequency_labels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        key_table = new Hashtable<>();
        frequency_words_table = new Hashtable<>();
        frequency_alphabet_table = new Hashtable<>();

        init_layout();

        String cipherText = "jcty jyevdsoq tyefmrsjtj bsjoyl sr ofy ndyhscvj jyeoscr emr zy stnbytyroyl vjsra edqnocadmnfq. edqnocadmnfq, m wcdl wsof adyyx cdsasr, tymrj “jyedyo wdsosra”. fcwyhyd, wy vjy ofy oydt oc dypyd oc ofy jesyrey mrl mdo cp odmrjpcdtsra tyjjmayj oc tmxy ofyt jyevdy mrl sttvry oc moomexj. mbofcvaf sr ofy nmjo edqnocadmnfq dyppydyl crbq oc ofy yredqoscr mrl lyedqnoscr cp tyjjmayj vjsra jyeydo xyqj, oclmq so sj lypsryl mj srhcbhsra ofdyy lsjosreo tyefmrsjtj: jqttyodse  xyq yredsnfydtyro, mjqttyodse xyq yresnfydtyro, mrl fmjfsra. wy wsbb zdsypbq lsjevjj ofyjy ofdyy tyefmrsjtj fydy. sr jqttyodse yresnfydtyro, mr yrosoq, jmq mbsey, emr jyrl m tyjjmay oc cofyd yrosoq, jmq zcz, chyd mr srjyevdy efmrryb wsof ofy mjjvtnoscr ofmo mr mlhydjmdq, jmq yhy, emrrco vrlydjomrl ofy ecroyroj cp ofy tyjjmay zq jstnbq ymhyjldcnnsra chyd ofy efmrryb. mbsey yredqnoj ofy tyjjmay vjsra mr yredqnoscr mbacdsoft. zcz lyedqnoj ofy tyjjmay vjsra m lyedqnoscr mbacdsoft. jqttyodse xyq yresnfydtyro vjyj m jsraby jyedyo xyq pcd zcof yredqnoscr mrl lyedqnoscr. yredqnoscr/lyedqnoscr emr zy ofcvafo cp mj ybyeodcrse bcexsra jqjoyt. sr jqttyodse xyq yresnfydsra, mbsey nvoj ofy tyjjmay sr m zcg mrl bcexj ofy zcg vjsra ofy jfmdyl jyedyo xyq; zcz vrbcexj ofy zcg wsof ofy jmty xyq mrl omxyj cvo ofy tyjjmayj. sr mjqttyodse yresnfydtyro, wy fmhy ofy jmty jsovmoscr mmj ofy jqttyodse xyq yresnfydtyro, wsof m pyw ygeynoscrj. psdjo, ofydy mdy owc xyqj srjoyml cp cry; cry nvzbse xyq mrl cry ndshmoy xyq. oc jyrl m jyevdy tyjjmay oc zcz, mbsey psdjoj yredqnoj ofy tyjjmay vjsra zcz‟j nvzbse xyq. oc lyedqnoj ofy tyjjmay, zcz vjyj fsj cwr ndshmoy xyq";
        cipher_textArea.setText(cipherText);

        insert_correct_answer_key_at_textField();

        print_sorted_KeyTable();
    }

    private void insert_correct_answer_key_at_textField() {
        String correct_Answer_key = "etsniarochmypgdulbkfwvx";

        for (int i = 0; i < correct_Answer_key.length(); i++) {
            textFields[i].setText(String.valueOf(correct_Answer_key.charAt(i)));
        }
    }

    private void print_sorted_KeyTable() {
        Iterator<Character> iterator = key_table.keySet().stream().sorted(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1.compareTo(o2);
            }
        }).iterator();


        while (iterator.hasNext()) {
            char key = iterator.next();
            System.out.println(key + " : " + key_table.get(key));
        }
    }

    private void decryption() {
        char[] plainText_chrArray = cipher_textArea.getText().toCharArray();

        for (int i = 0; i < plainText_chrArray.length; i++) {
            if (key_table.containsKey(plainText_chrArray[i]))
                plainText_chrArray[i] = key_table.get(plainText_chrArray[i]).charAt(0);
        }

        plain_textArea.setText(String.valueOf(plainText_chrArray));
    }

    private void decryption_word() {
        wordList.forEach(word -> {
            char[] plainText_word = word.getWord_name().toCharArray();

            for (int i = 0; i < plainText_word.length; i++) {
                if (key_table.containsKey(plainText_word[i]))
                    plainText_word[i] = key_table.get(plainText_word[i]).charAt(0);
                else
                    plainText_word[i] ='_';
            }

            word.setPlain_word(String.valueOf(plainText_word));
        });
        word_tableView.refresh();
    }

    private void init_layout() {
        init_AlphabetLayout();

        init_tableView();
        init_textArea();
        init_textFiled();
    }

    private void init_AlphabetLayout() {
        textFields = new TextField[26];
        alphabet_labels = new Label[26];
        frequency_labels = new Label[26];

        for (int i = 0; i < 26; i++) {
            alphabet_labels[i] = new Label();
            alphabet_labels[i].setPrefWidth(50);
            alphabet_labels[i].setAlignment(Pos.CENTER);

            gridPane.addColumn(i, alphabet_labels[i]);

            frequency_labels[i] = new Label();
            frequency_labels[i].setPrefWidth(50);
            frequency_labels[i].setAlignment(Pos.CENTER);
            frequency_labels[i].setTextFill(Color.GREEN);

            final int index = i;

            textFields[i] = new TextField();
            textFields[i].setVisible(false);
            textFields[i].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    char key = alphabet_labels[index].getText().charAt(0);

                    String value = newValue;

                    if (newValue.equals("")) {
                        key_table.remove(key);
                    } else {
                        if (key_table.containsValue(value)) {
                            textFields[index].setStyle("-fx-text-fill: white; -fx-background-color: red;");
                        } else {
                            textFields[index].setStyle("-fx-text-fill: black;");
                        }
                        key_table.put(key, value);
                    }

                    decryption();
                    decryption_word();
                }
            });
        }

        gridPane.setVgap(5);

        gridPane.addRow(1, frequency_labels);
        gridPane.addRow(2, textFields);
    }

    private void init_tableView() {
        wc_1.setCellValueFactory(new PropertyValueFactory<>("word_name"));
        wc_2.setCellValueFactory(new PropertyValueFactory<>("count"));
        wc_3.setCellValueFactory(new PropertyValueFactory<>("plain_word"));

        wordList = word_tableView.getItems();
    }

    private void init_textArea() {
        cipher_textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                plain_textArea.setText(newValue);

                key_table.clear();

                set_labelsText();
                print_frequency_words_at_tableView();
                wordFilteredList = wordList.filtered(word -> true);
            }
        });
    }

    private void init_textFiled() {
        filterWordLength_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    filterWordLength_tf.setText(newValue.replaceAll("[^\\d]", ""));
                    return;
                }

                wordFilteredList.setPredicate(filterByWordLength.and(filterByContainsWord));

                word_tableView.setItems(wordFilteredList);
            }
        });

        filterPlainWord_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                wordFilteredList.setPredicate(filterByWordLength.and(filterByContainsWord));

                word_tableView.setItems(wordFilteredList);
            }
        });
    }

    Predicate<Word> filterByWordLength = new Predicate<Word>() {
        @Override
        public boolean test(Word word) {
            if (filterWordLength_tf.getText().equals(""))
                return true;

            if (word.getWord_name().length() == Integer.parseInt(filterWordLength_tf.getText()))
                return true;
            else
                return false;
        }
    };

    Predicate<Word> filterByContainsWord = new Predicate<Word>() {
        @Override
        public boolean test(Word word) {
            if (filterPlainWord_tf.getText().equals(""))
                return true;

            if (word.getPlain_word().contains(filterPlainWord_tf.getText()))
                return true;
            else
                return false;
        }
    };

    private void set_labelsText() {
        for (int i = 0; i < 26; i++) {
            alphabet_labels[i].setText("");
            frequency_labels[i].setText("");
            textFields[i].setText("");
            textFields[i].setVisible(false);
        }

        print_frequency_alphabets_at_Labels();
    }

    private void print_frequency_alphabets_at_Labels() {
        frequency_alphabet_table.clear();

        char[] cipherText_charArray = cipher_textArea.getText().toCharArray();

        for (char c : cipherText_charArray) {
            if (c < 'a' || c > 'z')
                continue;

            if (frequency_alphabet_table.containsKey(c)) {
                int old_alphabetFrequency = frequency_alphabet_table.get(c);

                frequency_alphabet_table.put(c, old_alphabetFrequency + 1);
            } else {
                frequency_alphabet_table.put(c, 1);
            }
        }

        Iterator<Character> iterator_sorted_frequency_alphabet = frequency_alphabet_table.keySet().stream().sorted(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                Integer o1_frequency_alphabet = frequency_alphabet_table.get(o1);
                Integer o2_frequency_alphabet = frequency_alphabet_table.get(o2);

                return o2_frequency_alphabet.compareTo(o1_frequency_alphabet);
            }
        }).iterator();

        int i = 0;
        while (iterator_sorted_frequency_alphabet.hasNext()) {
            char alphabet_Index = iterator_sorted_frequency_alphabet.next();

            alphabet_labels[i].setText(String.valueOf(alphabet_Index));
            frequency_labels[i].setText(String.valueOf(frequency_alphabet_table.get(alphabet_Index)));
            textFields[i].setVisible(true);

            i++;
        }
    }

    private void print_frequency_words_at_tableView() {
        frequency_words_table.clear();

        Scanner sc = new Scanner(cipher_textArea.getText());
        while (sc.hasNext()) {
            String word = sc.next();

            int i = 0;
            while (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                word = word.replace(word.charAt(i), ' ');
                i++;
            }

            i = word.length() - 1;
            while (word.charAt(i) < 'a' || word.charAt(i) > 'z') {
                word = word.replace(word.charAt(i), ' ');
                i--;
            }

            word = word.trim();

            if (frequency_words_table.containsKey(word)) {
                int old_wordFrequency = frequency_words_table.get(word);

                frequency_words_table.put(word, old_wordFrequency + 1);
            } else {
                frequency_words_table.put(word, 1);
            }
        }

        Iterator<String> iterator_sorted_frequency_word = frequency_words_table.keySet().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Integer o1_frequency_alphabet = frequency_words_table.get(o1);
                Integer o2_frequency_alphabet = frequency_words_table.get(o2);

                return o2_frequency_alphabet.compareTo(o1_frequency_alphabet);
            }
        }).iterator();

        wordList.clear();
        while (iterator_sorted_frequency_word.hasNext()) {
            String word_index = iterator_sorted_frequency_word.next();

            Word word = new Word(word_index, frequency_words_table.get(word_index));

            wordList.add(word);
        }
    }
}
