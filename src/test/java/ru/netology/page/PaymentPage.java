package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    public static SelenideElement notificationOk = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    public static SelenideElement notificationError = $(byText("Ошибка! Банк отказал в проведении операции")).parent().$("[class=\"notification__content\"]");
    private final SelenideElement payMethod = $$("h3").find(exactText("Оплата по карте"));
    private final SelenideElement numberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private final SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private final SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private final SelenideElement nameField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private final SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private final SelenideElement fieldError = $(byText("Неверный формат"));
    private final SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement cardExpiredDateError = $(byText("Истёк срок действия карты"));
    private final SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    private final SelenideElement cancelField = $$("[class=\"icon-button__text\"]").first();
    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public enum Field {
        NUMBER,
        MONTH,
        YEAR,
        NAME,
        CVV
    }



    public void fillFields(CardInfo info) {
        numberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        nameField.setValue(info.getName());
        cvvField.setValue(info.getCvv());
        continueButton.click();
    }

    public void checkNotificationOk() {
        notificationOk.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void checkNotificationError() {
        notificationError.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }


    public void checkFieldError() {
        fieldError.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void getNotificationExpirationDateError() {
        cardExpirationDateError.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void getNotificationExpiredError() {
        cardExpiredDateError.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

    public void getNotificationRequiredFieldError() {
        requiredFieldError.shouldBe(visible, Duration.ofSeconds(15));
        cancelField.click();
    }

}