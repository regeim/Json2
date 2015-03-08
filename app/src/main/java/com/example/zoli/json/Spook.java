package com.example.zoli.json; /**
 * Created by Zoli on 2015.03.08..
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Spook {
    @Expose
    private String name = null;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    //don't expose
    private String race = null;
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    //change
    @Expose
    @SerializedName("gender")
    private String sex = null;
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}
