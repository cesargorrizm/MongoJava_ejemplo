package com.becaries.MongoJava.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pelicula {

@SerializedName("name")
@Expose
private String name;
@SerializedName("Theme")
@Expose
private String theme;
@SerializedName("YearOpening")
@Expose
private String yearOpening;
@SerializedName("Description")
@Expose
private String description;
@SerializedName("Puntuation")
@Expose
private String puntuation;
@SerializedName("YearsMin")
@Expose
private String yearsMin;
@SerializedName("dislikes")
@Expose
private String dislikes;
@SerializedName("duration")
@Expose
private String duration;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getTheme() {
return theme;
}

public void setTheme(String theme) {
this.theme = theme;
}

public String getYearOpening() {
return yearOpening;
}

public void setYearOpening(String yearOpening) {
this.yearOpening = yearOpening;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getPuntuation() {
return puntuation;
}

public void setPuntuation(String puntuation) {
this.puntuation = puntuation;
}

public String getYearsMin() {
return yearsMin;
}

public void setYearsMin(String yearsMin) {
this.yearsMin = yearsMin;
}

public String getDislikes() {
return dislikes;
}

public void setDislikes(String dislikes) {
this.dislikes = dislikes;
}

public String getDuration() {
return duration;
}

public void setDuration(String duration) {
this.duration = duration;
}

}