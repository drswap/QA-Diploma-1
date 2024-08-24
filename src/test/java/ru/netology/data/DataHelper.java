package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    public static CardInfo getAcceptedCard() {
        Faker faker = new Faker();
        return new CardInfo("4444444444444441", "11", "25", faker.name().firstName() + " " + faker.name().lastName(), faker.number().digits(3));

    }

    public static CardInfo getDeniedCard() {
        Faker faker = new Faker();
        return new CardInfo("4444444444444442", "12", "26", faker.name().firstName() + " " + faker.name().lastName(), faker.number().digits(3));
    }
    public static CardInfo getCardNumberFieldEmpty() {
        Faker faker = new Faker();
        return new CardInfo(null,"12","26",faker.name().firstName() + " " + faker.name().lastName(), faker.number().digits(3));
    }
    public static CardInfo getCardNumberWithChar() {
        Faker faker = new Faker();
        return new CardInfo("444444444444444A", "11", "25", faker.name().firstName() +  " " + faker.name().lastName(), faker.number().digits(3));
    }
    public static CardInfo getCardNumber13Digits(){
        Faker faker = new Faker();
        String number = faker.number().digits(13);
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo(number, month, year, cardHolder, cvv);
    }
    public static CardInfo getCardMonth13(){
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = "13";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardMonth00(){
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = "00";
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardMonthEmpty(){
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() +" " + faker.name().lastName();
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", null, year, cardHolder, cvv);
    }
    public static CardInfo getCardYearPrevious(){
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = "22";
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardYearEmpty(){
        Faker faker = new Faker();
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, null, cardHolder, cvv);
    }
    public static CardInfo getCardHolderInCyrillic() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardHolderWithSpecialSymbol() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName() + "*";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardHolderWithNumber() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName() + faker.number().digits(1);
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardHolderEmpty() {
        Faker faker = new Faker(new Locale("ru"));
        String cardHolder = " ";
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardCVVLessThan3() {
        Faker faker = new Faker(new Locale("en"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = "54";
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }
    public static CardInfo getCardCVVEmpty() {
        Faker faker = new Faker(new Locale("en"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        String cvv = " ";
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }

}