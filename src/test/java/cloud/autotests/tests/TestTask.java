package cloud.autotests.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.time.Duration.ofSeconds;

public class TestTask extends TestBase {
    private static final String BASE_URL = "https://www.google.com/ru";
    private static final String TEXT_TO_SEARCH = "СберЛизинг — официальный сайт лизинговой компании";
    CarSelection carSelection = new CarSelection();
    SelenideElement elementWeCheck = $(byXpath("//h1[@class='h2']"));


    @org.junit.jupiter.api.Test
    @Description("Тестовое задание для СберЛизинг")
    @DisplayName("Тестирование функции поиска автомобиля на сайте СберЛизинг")
    void generatedTest() {
        step("1) Перейти на сайт Google", () -> {
            open(BASE_URL);
        });

        step("2) Найти в поиске СберЛизинг", () -> {
            if ($x("//div[text()=\"Прежде чем перейти к Google Поиску\"]").isDisplayed()) {
                $x("//div[@role='none'][contains(text(),'Принимаю')]").click();
            } else if ($x("Bevor Sie zur Google Suche weitergehen").isDisplayed()) {
                $x("//div[@role='none'][contains(text(),'Ich stimme zu')]").click();
            }
            $("[name=q]").setValue("СберЛизинг").pressEnter();
        });

        step("3) Перейти на сайт СберЛизинг", () -> {
            $$("h3").findBy(text(TEXT_TO_SEARCH)).click();
        });

        step("4) Подобрать любой автомобиль по параметрам, заполнив все параметры", () -> {
            killCookiesPopUpBanner();
            selectMenuItem("Авто в наличии");
            killAnniversaryPopUpBanner();
            $(byId("marketplace-horizontal-filter-title")).scrollIntoView(true);
            $x("//span[contains(text(),'Город')]").click();

            carSelection
                    .getFilterSelection("Москва");
            carSelection
                    .getFilterCategoryList("Марка");
            carSelection
                    .getFilterSelection("Audi");
            carSelection
                    .getFilterCategoryList("Модель");

            $(byXpath("//label[normalize-space()='A4']")).click();
            $(".horizontal-filter-block__property-title").
                    shouldHave(Condition.text("Стоимость автомобиля")).click();
            Selenide.sleep(2000);

            $(byXpath("//div[@class='range-slider-values__right']//input[@type='number']")).
                    click();

            if (isMac()) {
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



            SelenideElement slider2 = $(byXpath("//div[@aria-label='slider between 67 and 612']//div[@class='el-slider__bar']"));

            if ($x("//div[@class='col-lg-7 ml-lg-0 ml-md-20 pb-4 pr-lg-90 pt-40 pt-lg-60 px-30 px-lg-30 px-sm-30 px-sm-60 py-30']").
                    isDisplayed()) {
                $x("//div[@class='modal-present__close']").click();
            }
            actions().dragAndDropBy(slider2, 3, 0).build().perform();



            if ($x("//div[contains(@class, 'main-order-form')]").
                    isDisplayed()) {
                $x("//div[@class='modal-present__close']").click();
            }
            $(byXpath("//label[contains(text(),'передний')]")).click();
            $(byXpath("//label[contains(text(),'автомат')]")).click();
            $(byXpath("//label[@title='седан']")).click();

            //$(byXpath("//button[contains(text(),'Закрыть')]")).click();
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
            String textToCheck = elementWeCheck.getText();
            Assertions.assertTrue(textToCheck.contains("Audi A4"));
        });
    }


}