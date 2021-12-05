package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final String cardNumber = ", ";

    public int getFirstCardBalance() {
        String text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        String text = cards.last().text();
        return extractBalance(text);
    }

    public String getFirstCard() {
        String text = cards.first().text();
        return numberCard(text);
    }

    public String getSecondCard() {
        String text = cards.last().text();
        return numberCard(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    private String numberCard(String text) {
        int finish = text.indexOf(cardNumber);
        String value = text.substring(balanceStart.length(), finish);
        return value;
    }
}

//  public int getCardBalance(String id) {
//    // TODO: перебрать все карты и найти по атрибуту data-test-id
//    return extractBalance(text);
//  }
//  public int getCardBalance(ElementsCollection cards) {
//    //     Таким образом, метод getFirstCardBalance умеет получать баланс карты. Не сложно его модифицировать под то, чтобы он назывался getCardBalance и умел получать баланс по индексу (см. документацию на ElementsCollection).
//  }
//  public int getCardBalance() {
//    int nCard = cards.size();
//    String text = cards.get(nCard).text();
//    return extractBalance(text);
//     }
