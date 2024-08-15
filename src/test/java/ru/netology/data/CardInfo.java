package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CardInfo {
     String number;
    String month;
    String year;
    String name;
    String cvv;
}