package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static org.hamcrest.Matchers.*;

public class WeatherSearchSteps {




    private String  BASE_URI = "https://api.openweathermap.org/data/2.5/weather?q=";
    private String  API_KEY= "&appid=a505941d7a012f3233a4b424e6746269";
    private String  UNIT= "&units=imperial";

    private Response response;



    @Step("I try to search the city by {0} ")
    public void searchByCityName(String city){
        response = SerenityRest.when().get(BASE_URI + city + API_KEY + UNIT);
    }

    @Step
    public void searchIsExecutedSuccesfully(){
        response.then().statusCode(200);
    }

    @Step
    public void searchIsNotExecutedSuccesfully(){
        response.then().statusCode(404);
    }

    @Step("I should find {0} in response")
    public ValidatableResponse iShouldFindCity(String city){
       return response.then()
                .assertThat()
                .body("name", equalToIgnoringCase(city));
    }


    @Step
    public ValidatableResponse thereShouldBeMinMaxTemperature(){
        return response.then()
                .assertThat()
                .body("main.temp_min", notNullValue())
                .body("main.temp_max", notNullValue())
                .log()
                .all();
    }

    @Step
    public ValidatableResponse thereShouldBeErrorHandlingResponse(){
        return response.then()
                .assertThat()
                .body("cod", equalTo("404"))
                .body("message", is("city not found"))
                .log()
                .all();
    }
}
