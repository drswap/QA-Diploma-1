package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CardInfo {
    private String number;
    private String month;
    private String year;
    private String name;
    private String cvv;
}