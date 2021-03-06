package ru.progwards.java1.lessons.maps;

import java.io.*;
import java.util.*;

//Реализовать класс, подсчитывающий частоту использования слов и букв в словах на основе текстов. Методы:
//2.4 Протестировать на файле wiki.train.tokens (во вложении), для отладки можно использовать wiki.test.tokens
public class UsageFrequency {
    public static void main(String[] args) {
        //            String fileName = "G:\\Java\\Progwards\\Homework 10.01.2020\\src\\ru\\progwards\\java1\\lessons\\maps\\Censor_text1.txt";
                    String fileName = "C:\\Java\\Progwards\\HW 11.01.2021 задания до 15\\src\\ru\\progwards\\java1\\lessons\\maps\\Censor_text1.txt";
//        String fileName = "G:\\Java\\Progwards\\Homework 10.01.2020\\src\\ru\\progwards\\java1\\lessons\\maps\\wiki.test.tokens";
        UsageFrequency usageFrequency = new UsageFrequency();
        usageFrequency.processFile(fileName);
//            System.out.println(Arrays.toString(usageFrequency.charsFromFile));
        usageFrequency.getLetters();
        usageFrequency.getWords();

    }

    //Переменные класса
    private char[] charsFromFile;       //Массив символов из файла
//    String textFromFile;

    // 2.1 загрузить содержимое файла
    public void processFile(String fileName) {
//        File file = new File(fileName);
////        byte[] bytes = new byte[(int)file.length()];
////        int i = 0;
//        StringBuilder strB = new StringBuilder();
//        try(Scanner scanner = new Scanner(file)){
//            while (scanner.hasNext()) {
//                strB.append(scanner.next());
//                strB.append(" ");
////                i++;
//             }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        charsFromFile = strB.toString().toCharArray();

        try(RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {  //Поток для чтения файла
            byte[] bytes = new byte[(int) raf.length()];      //Массив байт для чтения из файла
            raf.read(bytes);
            charsFromFile = new String(bytes).toCharArray();   //Создание массива символов из файла на основе строки, созданной из массива байт
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // 2.2 вернуть Map, который содержит все найденные буквы и цифры, и количество раз,
    // которое встретился каждый искомый символ. Знаки препинания, такие как “.,!? @” и др не учитывать.
    public Map<Character, Integer> getLetters(){
        Map<Character, Integer> lettersMap = new HashMap<>();
            for(Character character:charsFromFile){
                if(Character.isAlphabetic(character) || Character.isDigit(character)){ //Перебираю массив символов по признаку "это буква" или " это цифра"
                    if(lettersMap.containsKey(character)){
                        lettersMap.put(character, lettersMap.get(character) + 1);
                    }else {
                        lettersMap.put(character, 1);
                    }
                }
            }
        return lettersMap;
    }

//    2.3  - вернуть Map, который содержит все найденные слова и количество раз,
//    которое каждое слово встретилось. Знаки препинания, такие как “.,!? @” и др являются разделителями.
    public Map<String, Integer> getWords() {
        Map<String, Integer> wordsMap = new HashMap<>();
        StringBuilder stringSymbols = new StringBuilder();

        if(charsFromFile != null){
            for(Character character:charsFromFile){
                if(Character.isAlphabetic(character) || Character.isDigit(character)){ //Перебираю массив символов по признаку "это буква" или " это цифра"
                    stringSymbols.append(character);
                }else stringSymbols.append(" "); //Получаю строку слов или цифр, разделенную только пробелами (возможно несколькими)
            }

            Scanner scanner = new Scanner(stringSymbols.toString());
            while (scanner.hasNext()) {         //Прохожу по строке и отделяю слова
                String word = scanner.next();
                if(wordsMap.containsKey(word)){
                    wordsMap.put(word, wordsMap.get(word) + 1);
                }else {
                    wordsMap.put(word, 1);
                }
            }
        }
        return wordsMap;
    }
}



