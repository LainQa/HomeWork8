package qa.lain;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class Lesson8Tests {

    @ValueSource(strings = {"PS5", "Xbox 360"})
    @Tag("High")
    @ParameterizedTest(name = "Поиск в магазине ДНС {0}")
    void commonDnsSearchTest(String searchQuery) {
        open("https://www.dns-shop.ru/");
        $("#header-search [name=q]")
                .setValue(searchQuery).pressEnter();
        $$(".catalog-products")
                .find(Condition.text(searchQuery))
                .shouldBe(Condition.visible);
    }

//test
    @CsvSource(value = {
            "PS5| Игровая консоль PlayStation 5 [825 ГБ SSD, геймпад - 1 шт, Bluetooth, Wi-Fi, белый]",
            "Xbox 360| Ключ активации Grand Theft Auto IV (Xbox ONE, Xbox 360) " +
                    "[18+, приключения, экшен, дата выхода: 06.07.2010]"
    },
            delimiter = '|')
    @Tag("High")
    @ParameterizedTest(name = "Поиск в магазине ДНС {0} и проверка отображения текста {1} ")
    void dnsSearchFirstResult(String searchQuery, String expectedResult) {

        open("https://www.dns-shop.ru/");
        $("#header-search [name=q]")
                .setValue(searchQuery).pressEnter();
        $$(".catalog-products")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }

}
