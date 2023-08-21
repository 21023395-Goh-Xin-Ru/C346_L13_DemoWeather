package sg.edu.rp.c346.id21023395.demoweatherdemoweather;

public class Weather {

    private String area;
    private String forecast;

    public Weather(String area, String forecast) {
        this.area = area;
        this.forecast = forecast;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "WEATHER" +
                "\nArea: " + area +
                "\nForecast: " + forecast;
    }
}
