package ru.netology.web.test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
//----
        var balans = new DashboardPage();
        var balansFirstCardStartTest = balans.getFirstCardBalance();
        var balansSecondCardStartTest = balans.getSecondCardBalance();
        int transfer = 15000;
        new TransferPage(transfer);
        var balansFirstCardEndTest = balans.getFirstCardBalance();
        var balansSecondCardEndTest = balans.getSecondCardBalance();
        assertEquals(balansFirstCardStartTest + transfer, balansFirstCardEndTest);
        assertEquals(balansSecondCardStartTest - transfer, balansSecondCardEndTest);
        if (balansSecondCardEndTest < 0 || balansFirstCardEndTest < 0) {
            if (balansFirstCardEndTest < 0) {
                System.out.println("Сумма на первом счете отрицательная: " + balansFirstCardEndTest);
            } else {
                System.out.println("Сумма на втором счете отрицательная: " + balansSecondCardEndTest);
            }
        }
    }

    @Disabled
    @Test
    void shouldTransferMoneyBetweenOwnCardsV2() {
        open("http://localhost:9999");
        var loginPage = new LoginPageV2();
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        //---
    }

    @Disabled
    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        var loginPage = open("http://localhost:9999", LoginPageV3.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        //---
    }
}

