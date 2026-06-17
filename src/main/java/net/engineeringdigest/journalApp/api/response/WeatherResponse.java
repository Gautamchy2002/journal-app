package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse{

    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;

    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;



@Getter
@Setter
    public static class Main{
        private double temp;

        @JsonProperty("feels_like")
        private double feelsLike;
        @JsonProperty("temp_min")
        private double tempMin;
        @JsonProperty("temp_max")
        private double tempMax;
        private int pressure;
        private int humidity;

        @JsonProperty("sea_level")
        private int seaLevel;
        @JsonProperty("grnd_level")
        private int grndLevel;
    }
@Getter
@Setter
    public static class Sys{
        private String country;
        private int sunrise;
        private int sunset;
    }
@Getter
@Setter
    public static class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;
    }
@Getter
@Setter
    public static class Wind{
        private double speed;
        private int deg;
        private double gust;
    }


}




