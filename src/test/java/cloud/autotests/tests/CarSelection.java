package cloud.autotests.tests;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CarSelection {

    protected void getFilterSelection(String filterChoice) {
        $x("//label[contains(text(),'" + filterChoice + "')]").click();
    }

    protected void getFilterCategoryList(String categoryChoice) {
        $(byXpath("//span[contains(text(),'" + categoryChoice + "')]")).click();
    }

    protected void selectCarTypeConfiguration(String categoryChoice) {
        $x("//label[contains(text(),'" + categoryChoice + "')]").click();
    }

}
