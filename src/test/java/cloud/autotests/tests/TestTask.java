package cloud.autotests.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestTask extends TestBase {
    private static final String BASE_URL = "https://www.google.com/ru";
    private static final String TEXT_TO_SEARCH = "СберЛизинг — официальный сайт лизинговой компании";

    @org.junit.jupiter.api.Test
    @Description("Тестовое задание для СберЛизинг")
    @DisplayName("Тестирование функции поиска автомобиля на сайте СберЛизинг")

    void generatedTest() {
        step("1) Перейти на сайт Google", () -> {
            open(BASE_URL);
            System.out.println("os.name: " + OS);
        });

        step("2) Найти в поиске СберЛизинг", () -> {
            $("[name=q]").setValue("СберЛизинг").pressEnter();
        });

        step("3) Перейти на сайт СберЛизинг", () -> {
            $$("h3").findBy(text(TEXT_TO_SEARCH)).click();
        });

        step("4) Подобрать любой автомобиль по параметрам, заполнив все параметры", () -> {
            $(".header-menu__link").shouldHave(Condition.text("Авто в наличии")).click();
            $(byId("marketplace-horizontal-filter-title")).scrollIntoView(true);
            $(byXpath("//span[contains(text(),'Город')]")).click();
            $(byXpath("//label[contains(text(),'Москва')]")).click();
            $(byXpath("//span[contains(text(),'Марка')]")).click();
            $(byXpath("//label[contains(text(),'Audi')]")).click();
            $(byXpath("//span[contains(text(),'Модель')]")).click();
            $(byXpath("//label[normalize-space()='A4']")).click();
            $(".horizontal-filter-block__property-title").
                    shouldHave(Condition.text("Стоимость автомобиля")).click();
            SelenideElement slider =  $(byXpath("//div[@aria-label='slider between 531900 and 30221800']"));
            Selenide.sleep(1000);

            $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                    click();

            if (isMac()){
                $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                        sendKeys(Keys.COMMAND + "a");
            } else {
                $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                        sendKeys(Keys.CONTROL + "a");
            }
            $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                    sendKeys(Keys.BACK_SPACE);

            $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                    setValue("3500000");
            $(byXpath("//div[@class='range-property mb-2 mb-lg-0 px-1']//div[@class='range-slider-values']")).click();

            SelenideElement slider2 = $(byXpath("//div[@aria-label='slider between 67 and 612']//div[@class='el-slider__bar']"));

            actions().dragAndDropBy(slider2, 3, 0).build().perform();
            $(byXpath("//label[contains(text(),'передний')]")).click();
            $(byXpath("//label[contains(text(),'автомат')]")).click();
            $(byXpath("//label[@title='седан']")).click();

            $(byXpath("//button[contains(text(),'Закрыть')]")).click();
            $(byXpath("//div[contains(text(),'Тип кузова')]")).scrollIntoView(true);
            $(byXpath("//label[@for='arrFilter_237_3706474592']")).click();
            $(byXpath("//div[@class='horizontal-filter-block__selector-current-value']//input[@type='text']"))
                    .click();
            $(byXpath("//label[contains(text(),'белый')]")).click();
            $(byXpath("//div[contains(text(),'Тип топлива')]")).click();
        });

        step("5)нажать на кнопку Показать все предложения", () -> {
            $(byXpath("//a[contains(text(),'Показать все предложения')]")).click();
        });

        step("6)выбрать любой из списка", () -> {
            $(byXpath("(//*[@class=\"sbl-btn sbl-btn_medium w-100\"] )[1]")).click();
        });

        step("7)сделать проверку того, что выбранная марка автомобиля соответствует марке в общем списке из п.6", () -> {
            SelenideElement elementWeCheck = $(byXpath("//h1[@class='h2']"));
            String textToCheck = elementWeCheck.getText();
            Assertions.assertTrue(textToCheck.contains("Audi A4"));
        });
    }



}