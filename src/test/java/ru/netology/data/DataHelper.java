package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    public static CardInfo getValidCard() {
        Faker faker = new Faker();
        String number = faker.number().digits(16);
        String cardHolder = faker.name().firstName() + ' ' + faker.name().lastName();
        String month = "12";
        String year = "24";
        String cvv = faker.number().digits(3);
        return new CardInfo(number, month, year, cardHolder, cvv);
    }

    //public CardInfo getValidCard() { //Перегрузка метода со значениями  от +1 месяца до +5 лет по умолчанию
     //   return getValidCard(1 + (int) (Math.random() * 12 * 5));}

    public static CardInfo getValidCardNameLength() {
        Faker faker = new Faker(new Locale("en"));
        String cardHolder = faker.name().firstName() + " " + faker.name().lastName();
        String month = "12";
        String year = "24";
        String cvv = faker.number().digits(3);
        return new CardInfo("4444444444444441", month, year, cardHolder, cvv);
    }

    public static CardInfo getAcceptedCard() {
        Faker faker = new Faker();
        return new CardInfo("4444444444444441", "11", "25", faker.name().firstName() + " " + faker.name().lastName(), faker.number().digits(3));

    }

    public static CardInfo getDeniedCard() {
        Faker faker = new Faker();
        return new CardInfo("4444444444444442", "12", "26", faker.name().firstName() + ' ' + faker.name().lastName(), faker.number().digits(3));
    }


}