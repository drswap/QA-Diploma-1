package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DBhelper;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.data.DBhelper.*;

public class PaymentCreditTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    @DisplayName("Accepted Credit Payment")
    void shouldAcceptCreditPayment() {
        cleanDB();
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        paymentPage.fillFields(dataHelper.getAcceptedCard());
        paymentPage.checkNotificationOk();
        assertEquals("APPROVED", DBhelper.getCreditStatus());
    }

    @Test
    @DisplayName("Denied Credit Payment")
    void shouldDeniedCreditPayment() {
        cleanDB();
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        paymentPage.fillFields(dataHelper.getDeniedCard());
        paymentPage.checkNotificationError();

    }

    @Test
    @DisplayName("Validation Valid Credit Payment")
    void shouldValidCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        paymentPage.fillFields(dataHelper.getAcceptedCard());
        paymentPage.checkNotificationOk();

    }

    @Test
    @DisplayName("Invalid Card Number Credit PAYMENT with char")
    void shouldGetErrorNumberCreditPaymentWithChar() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardNumberWithChar();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Invalid Card Number 13 digits Credit PAYMENT")
    void shouldGetErrorNumber13DigitsCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardNumber13Digits();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Empty Card Number Credit PAYMENT")
    void shouldGetErrorEmptyNumberCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardNumberFieldEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();

    }

    @Test
    @DisplayName("Invalid Card month 13 Credit PAYMENT")
    void shouldGetErrorMonth13CreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardMonth13();
        paymentPage.fillFields(info);
        paymentPage.getNotificationExpirationDateError();
    }

    @Test
    @DisplayName("Invalid Card month 00 Credit PAYMENT")
    void shouldGetErrorMonth00CreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardMonth00();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();

    }

    @Test
    @DisplayName("Empty Card Month Credit PAYMENT")
    void shouldGetErrorEmptyMonthCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardMonthEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Invalid card last year Credit PAYMENT")
    void shouldGetErrorLastYearCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardYearPrevious();
        paymentPage.fillFields(info);
        paymentPage.getNotificationExpiredError();
    }

    @Test
    @DisplayName("Empty Card Year Credit PAYMENT")
    void shouldGetErrorEmptyYearCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardYearEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Invalid Card Holder")
    void shouldGetValidNameSymbolCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardHolderInCyrillic();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Invalid Card Holder With Special Symbol")
    void shouldGetOkNameWithSpecialSymbolsCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardHolderWithSpecialSymbol();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Invalid name with digit Credit PAYMENT")
    void shouldGetErrorNameWithDigitCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardHolderWithNumber();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Empty Card Name Credit PAYMENT")
    void shouldGetErrorEmptyNameCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardHolderEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldErrorWithName();
    }


    @Test
    @DisplayName("Invalid cvv Credit PAYMENT")
    void shouldGetErrorCVVCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardCVVLessThan3();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Empty Card CVV Credit PAYMENT")
    void shouldGetErrorEmptyCvvCreditPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCreditPayment();
        CardInfo info = dataHelper.getCardCVVEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }
}
