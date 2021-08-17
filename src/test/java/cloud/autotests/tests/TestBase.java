package cloud.autotests.tests;

import cloud.autotests.config.Project;
import cloud.autotests.helpers.AllureAttachments;
import cloud.autotests.helpers.DriverSettings;
import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static java.time.Duration.ofSeconds;

@ExtendWith({AllureJunit5.class})
public class TestBase {
    public static String OS = System.getProperty("os.name").toLowerCase();

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = DriverUtils.getSessionId();

        AllureAttachments.addScreenshotAs("Last screenshot");
        AllureAttachments.addPageSource();
//        AllureAttachments.attachNetwork(); // todo
        AllureAttachments.addBrowserConsoleLogs();

        Selenide.closeWebDriver();

        if (Project.isVideoOn()) {
            AllureAttachments.addVideo(sessionId);
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0
                || OS.indexOf("nux") >= 0
                || OS.indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    protected void killCookiesPopUpBanner() {
        if ($x("//div[contains(@class, 'cookie-warning__container my-30 p-30 shadow rounded')]").isDisplayed()) {
            $x("//button[contains(text(),'Закрыть')]").click();
        }
    }

    protected void killAnniversaryPopUpBanner() {
        $x("//div[contains(@class, 'main-order-form__fields row')]").shouldBe(visible, ofSeconds(15)).click();
        $(".modal-present__close").shouldBe(visible, ofSeconds(15)).click();
    }

    protected void selectMenuItem(String menuItemName) {
        $(".header-menu__link").shouldHave(Condition.text(menuItemName)).click();
    }
}
