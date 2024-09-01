package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private SelenideElement payMethod = $$("h2").findBy(text("Путешествие дня"));
    private SelenideElement cardButton = $(byText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));


    public MainPage() {
        payMethod.shouldBe(visible);
    }

    public PaymentPage chooseCardPayment() {
        cardButton.click();
        return new PaymentPage();
    }

    public PaymentPage chooseCreditPayment() {
        creditButton.click();
        return new PaymentPage();
    }
}
