package com.company;

import com.codeborne.selenide.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


public class Main {


    @Test
    public void test_01() throws InterruptedException {


        open("https://www.rt.com/");
        //Click on last title from top titles
        $$("a[class='main-promobox__link']").get(4).click();
        //Verify header appears
        $("h1[class='article__heading']").shouldBe(Condition.visible);

        //Switch to spot.im iframe
        SelenideElement iframe2 = $("div[class='sppre_frame-container'] iframe");
        Selenide.switchTo().frame(iframe2);
        //Scroll down to focus on iframe
        WebElement element = WebDriverRunner.getWebDriver().findElement(By.cssSelector("div[class='sppre_info']"));
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        //Add nickname
        SelenideElement commentBox = $("input[placeholder*='Your']");
        commentBox.doubleClick().sendKeys("Joni");
        Selenide.actions().click();
        //Add nickname
        $("div[class='ql-editor ql-blank']").sendKeys("This is a test comment");
        //Click post
        $("button[class='sppre_send-button brand-bg-color brand-bg-color-hover']").click();
        Thread.sleep(3000);
        //Verify nickname appears
        $("div[class='sppre_user-link']").shouldHave(text("Joni"));
        //Verify post appears
        $("div[class='sppre_entity sppre_text-entity']").shouldHave(text("This is a test comment"));

    }




    @BeforeClass
    public void setup() {

        Configuration.browser = "chrome";
        ChromeDriverManager.getInstance().setup();
    }
}
