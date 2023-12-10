package com.msaggik.thirdlessonanalysisofthepoem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import kotlin.jvm.internal.CollectionToArray;

public class MainActivity extends AppCompatActivity {

    // поля
    // стихотворение для анализа
    private String poem = "У лукоморья дуб зелёный;\nЗлатая цепь на дубе том:\nИ днём и ночью кот учёный\nВсё ходит по цепи кругом;\n" +
            "Идёт направо — песнь заводит,\nНалево — сказку говорит.\nТам чудеса: там леший бродит,\nРусалка на ветвях сидит;\n" +
            "Там на неведомых дорожках\nСледы невиданных зверей;\nИзбушка там на курьих ножках\nСтоит без окон, без дверей;\n" +
            "Там лес и дол видений полны;\nТам о заре прихлынут волны\nНа брег песчаный и пустой,\nИ тридцать витязей прекрасных\n" +
            "Чредой из вод выходят ясных,\nИ с ними дядька их морской;\nТам королевич мимоходом\nПленяет грозного царя;\n" +
            "Там в облаках перед народом\nЧерез леса, через моря\nКолдун несёт богатыря;\nВ темнице там царевна тужит,\n" +
            "А бурый волк ей верно служит;\nТам ступа с Бабою Ягой\nИдёт, бредёт сама собой,\nТам царь Кащей над златом чахнет;\n" +
            "Там русский дух… там Русью пахнет!\nИ там я был, и мёд я пил;\nУ моря видел дуб зелёный;\nПод ним сидел, и кот учёный\n" +
            "Свои мне сказки говорил.";
    private String[] poemArray; // массив для слов стихотворения

    private TextView output; // поле вывода результата на экран смартфона

    private TextView outputPoem; // поле вывода слов поемы

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка разметки к полям
        output = findViewById(R.id.output);
        outputPoem = findViewById(R.id.output_poem);

        // конвертирование строки в массив и его сортировка
        poemArray = stringToArray(poem);

        // вывод проанализированных данных на экран смартфона
        output.setText(maxWord(poemArray));
        output.append(minWord(poemArray));
        output.append(numberOfLetters(poemArray));

        printSortedWords();
    }

    // метод поиска самого длинного слова
    private String maxWord(String[] wordArray) {
        String worldResult = "Самое длинное слово \"";
        int maxLength = 0; // максимальная длина слова
        int indexMaxLength = 0; // индекс слова с максимальной длинной

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i].length() > maxLength) { // если длина слова больше предыдущего максимального значения, то
                maxLength = wordArray[i].length(); // присваиваем новую максимальную длину
                indexMaxLength = i; // записываем индекс найденного слова
            }
        }

        return worldResult + wordArray[indexMaxLength] + "\" длинной " + maxLength + " букв(а)\n";
    }

    // метод поиска самого короткого слова
    private String minWord(String[] wordArray) {
        String worldResult = "Самое короткое слово \"";
        int minLength = Integer.MAX_VALUE; // минимальная длина слова
        int indexMinLength = 0; // индекс слова с минимальной длинной

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[i].length() < minLength) { // если длина слова меньше предыдущего минимального значения, то
                minLength = wordArray[i].length(); // присваиваем новую минимальную длину
                indexMinLength = i; // записываем индекс найденного слова
            }
        }

        return worldResult + wordArray[indexMinLength] + "\" длинной " + minLength + " букв(а)\n";
    }

    // метод определения количества букв в стихотворении
    private String numberOfLetters(String[] wordArray) {
        String worldResult = "В стихотворении ";
        int count = 0; // счётчик количества букв

        for (int i = 0; i < wordArray.length; i++) {
            count = count + wordArray.length;
        }

        return worldResult + count + " букв(а)\n";
    }

    // метод конвертирования строки в массив
    private String[] stringToArray(String text) { //[^А-Яа-я,ёЁ]
        String textMod = text.replaceAll("[\\W]", " ").trim(); // замена всех символов кроме букв на пробелы и удаление начальных и конечных пробелов
        String[] textArray = textMod.split("\\s+"); // деление строки по всем пробелам (от 1 до большего количества пробелов)
        return textArray;
    }

    // метод вывода отсортированных слов, без дубликатов, в нижнем регистре
    private void printSortedWords() {
        Stream<String> stringStream = Stream.of(poemArray);
        String[] stringArray = stringStream.map(word -> word.toLowerCase()).sorted().distinct().toArray(size -> new String[size]);
        Arrays.stream(stringArray).forEach(word -> outputPoem.append(word + "\n"));
    }
}