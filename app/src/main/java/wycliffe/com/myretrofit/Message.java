package wycliffe.com.myretrofit;

/**
 * Created by Wycliffe on 6/28/2017.
 */
// POJO made using Jsonschema2pojo

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("yos")
    @Expose
    private String yos;
    @SerializedName("regno")
    @Expose
    private String regno;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("baptismal_status")
    @Expose
    private String baptismalStatus;
    @SerializedName("residence")
    @Expose
    private String residence;
    @SerializedName("date_signed_up")
    @Expose
    private String dateSignedUp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYos() {
        return yos;
    }

    public void setYos(String yos) {
        this.yos = yos;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBaptismalStatus() {
        return baptismalStatus;
    }

    public void setBaptismalStatus(String baptismalStatus) {
        this.baptismalStatus = baptismalStatus;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getDateSignedUp() {
        return dateSignedUp;
    }

    public void setDateSignedUp(String dateSignedUp) {
        this.dateSignedUp = dateSignedUp;
    }

}
