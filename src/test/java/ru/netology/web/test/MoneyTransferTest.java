package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var balansFirstCardStartTest = dashboardPage.getFirstCardBalance();
        var balansSecondCardStartTest = dashboardPage.getSecondCardBalance();
        int transfer = 1000;
        var transferPage = dashboardPage.getFirstCard();
        transferPage.makeTransfer(String.valueOf(transfer), DataHelper.getSecondCardInfo()); //п.7
        var balansFirstCardEndTest = dashboardPage.getFirstCardBalance();
        var balansSecondCardEndTest = dashboardPage.getSecondCardBalance();
        assertEquals(balansFirstCardStartTest + transfer, balansFirstCardEndTest);
        assertEquals(balansSecondCardStartTest - transfer, balansSecondCardEndTest);
    }

    @Test
    void shouldTransferOverMoneyBetweenOwnCards() {
        var loginPage = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var balansFirstCardStartTest = dashboardPage.getFirstCardBalance();
        var balansSecondCardStartTest = dashboardPage.getSecondCardBalance();
        int transfer = 20000;
        var transferPage = dashboardPage.getFirstCard();
        transferPage.makeTransfer(String.valueOf(transfer), DataHelper.getSecondCardInfo());
        var balansFirstCardEndTest = dashboardPage.getFirstCardBalance();
        var balansSecondCardEndTest = dashboardPage.getSecondCardBalance();
        assertEquals(balansFirstCardStartTest, balansFirstCardEndTest);
        assertEquals(balansSecondCardStartTest, balansSecondCardEndTest);
    }
}




