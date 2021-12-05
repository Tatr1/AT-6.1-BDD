package ru.netology.web.page;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {

    public TransferPage(int transfer) {
        $$(withText("Пополнить")).first().click();
        $("[data-test-id=amount] input").setValue(Integer.toString(transfer));
        $("[data-test-id=from] input").setValue("5559 0000 0000 0002");
        $(withText("Пополнить")).click();
    }
}
