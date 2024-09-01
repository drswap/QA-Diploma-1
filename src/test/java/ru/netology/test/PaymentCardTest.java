package ru.netology.test;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.CardInfo;
import ru.netology.data.DBhelper;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.DBhelper.*;
import static ru.netology.page.PaymentPage.Field.*;

public class PaymentCardTest {
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

    @AfterEach
    void cleanBase(){
        DBhelper.cleanDB();
    }


    @Test
    @DisplayName("Accepted Card Payment")
    void shouldAcceptCardPayment() {
        cleanDB();
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        paymentPage.fillFields(dataHelper.getAcceptedCard());
        paymentPage.checkNotificationOk();
        assertEquals("APPROVED", DBhelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Denied Card Payment")
    void shouldDeniedCardPayment() {
        cleanDB();
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        paymentPage.fillFields(dataHelper.getDeniedCard());
        paymentPage.checkNotificationError();

    }

    @Test
    @DisplayName("Validation Valid Card Payment")
    void shouldValidCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        paymentPage.fillFields(dataHelper.getAcceptedCard());
        paymentPage.checkNotificationOk();
    }

    @Test
    @DisplayName("Invalid Card Number Card Payment with char")
    void shouldGetErrorNumberCardPaymentWithChar() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardNumberWithChar();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

       @Test
    @DisplayName("Invalid Card Number 13 digits Card Payment")
    void shouldGetErrorNumber13DigitCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardNumber13Digits();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Empty Card Number Card Payment")
    void shouldGetErrorEmptyNumberCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardNumberFieldEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();

    }

    @Test
    @DisplayName("Invalid Card month 13 Card Payment")
    void shouldGetErrorMonth13CardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardMonth13();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();

    }

    @Test
    @DisplayName("Invalid Card month 00 Card Payment")
    void shouldGetErrorMonth00CardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardMonth00();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();

    }

    @Test
    @DisplayName("Empty Card Month Card Payment")
    void shouldGetErrorEmptyMonthCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardMonthEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Invalid card last year Card Payment")
    void shouldGetErrorLastYearCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardYearPrevious();
        paymentPage.fillFields(info);
        paymentPage.getNotificationExpiredError();
    }

    @Test
    @DisplayName("Empty Card Year Card Payment")
    void shouldGetErrorEmptyYearCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardYearEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();
    }

    @Test
    @DisplayName("Invalid Card Holder")
    void shouldGetValidNameSymbolCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardHolderInCyrillic();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Invalid Card Holder With Special Symbol")
    void shouldGetOkNameWithSpecialSymbolsCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardHolderWithSpecialSymbol();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
        assertNull(DBhelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Invalid name with digit Card Payment")
    void shouldGetErrorNameWithDigitCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardHolderWithNumber();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();

    }


    @Test
    @DisplayName("Empty Card Name Card Payment")
    void shouldGetErrorEmptyNameCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardHolderEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();

    }


    @Test
    @DisplayName("Invalid cvv Card Payment")
    void shouldGetErrorCVVCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardCVVLessThan3();
        paymentPage.fillFields(info);
        paymentPage.checkFieldError();
    }

    @Test
    @DisplayName("Empty Card CVV Card Payment")
    void shouldGetErrorEmptyCvvCardPayment() {
        MainPage mainPage = new MainPage();
        DataHelper dataHelper = new DataHelper();
        PaymentPage paymentPage = mainPage.chooseCardPayment();
        CardInfo info = dataHelper.getCardCVVEmpty();
        paymentPage.fillFields(info);
        paymentPage.getNotificationRequiredFieldError();

    }
}
