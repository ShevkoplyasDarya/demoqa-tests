package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class TestRegistrationForm {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920*1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void FillFormTest() {
        open("/automation-practice-form");

        //removes banners and footers
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Ann");
        $("#lastName").setValue("Smith");
        $("#userEmail").setValue("smith@test.com");
        $("#genterWrapper").$(byText("Female")).click();
        $("#userNumber").setValue("3816543210");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__year-select").selectOptionByValue("1990");
        $(".react-datepicker__day--016").click();
        $("#subjectsInput").setValue("a").pressEnter();
        $("#subjectsInput").setValue("b").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        //upload a picture
        $("#uploadPicture").uploadFromClasspath("img.png");

        $("#currentAddress").setValue("Elm Street 10").pressEnter();
        $("#stateCity-wrapper").$(byText("Select State")).scrollIntoView(true).click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#stateCity-wrapper").$(byText("Select City")).scrollIntoView(true).click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        //check the submitted form
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").
                $("tbody").shouldHave(text("Ann Smith"),
                        text("smith@test.com"),
                        text("3816543210"),
                        text("16 October,1990"),
                        text("Maths, Biology"),
                        text("img.png"),
                        text("Elm street 10"),
                        text("NCR Delhi")
                );
        $("#closeLargeModal").scrollIntoView(true).click();

    }

}
