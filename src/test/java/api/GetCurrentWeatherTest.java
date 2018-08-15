package api;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag("API")
})
public class GetCurrentWeatherTest {

    @Steps
    WeatherSearchSteps weatherSearchSteps;

    @Test
    public void verifyUserCanSearchWeatherForCity(){
        weatherSearchSteps.searchByCityName("new york");
        weatherSearchSteps.searchIsExecutedSuccesfully();
        weatherSearchSteps.iShouldFindCity("new york");
        weatherSearchSteps.thereShouldBeMinMaxTemperature();


    }

    @Test
    public void verify_Error_Is_Handled_When_City_Doesnt_Exist(){
        weatherSearchSteps.searchByCityName("Namek");
        weatherSearchSteps.searchIsNotExecutedSuccesfully();
        weatherSearchSteps.thereShouldBeErrorHandlingResponse();



    }
    }
