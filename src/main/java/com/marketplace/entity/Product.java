package com.marketplace.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Entity
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="category_id")
    @Cascade({CascadeType.SAVE_UPDATE})
    @JsonBackReference
    private Category category;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="series")
    private String series;

    @Column(name="model")
    private String model;

    @Column(name="use_type")
    private String use_type;

    @Column(name="application")
    private String application;

    @Column(name="mounting_location")
    private String mounting_location;

    @Column(name="accessories")
    private String accessories;

    @Column(name="model_year")
    private String model_year;

    @Column(name="air_flow")
    private String air_flow;

    @Column(name="power")
    private String power;

    @Column(name="voltage")
    private String voltage;

    @Column(name="fan_speed")
    private String fan_speed;

    @Column(name="numbers_of_fan_speed")
    private String numbers_of_fan_speed;

    @Column(name="sound_at_max_speed")
    private String sound_at_max_speed;

    @Column(name="fan_sweep_diameter")
    private String fan_sweep_diameter;

    @Column(name="height")
    private String height;

    @Column(name="weight")
    private String weight;

    @Column(name="firm")
    private String firm;

    @Column(name="global")
    private String global;

    @Column(name="details")
    private String details;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="web")
    private String web;

    @Column(name="manufacturer_department")
    private String manufacturer_department;

    @Column(name="manufacturer_phone")
    private String manufacturer_phone;

    @Column(name="manufacturer_email")
    private String manufacturer_email;

    @Column(name="manufacturer_web")
    private String manufacturer_web;

    @Column(name="image_url")
    private String image_url;

    public Product() {
    }

    public Product(String manufacturer, String series, String model, String use_type, String application, String mounting_location, String accessories, String model_year, String air_flow, String power, String voltage, String fan_speed, String numbers_of_fan_speed, String sound_at_max_speed, String fan_sweep_diameter, String height, String weight, String firm, String global, String details, String name, String phone, String email, String web, String manufacturer_department, String manufacturer_phone, String manufacturer_email, String manufacturer_web, String image_url) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.model = model;
        this.use_type = use_type;
        this.application = application;
        this.mounting_location = mounting_location;
        this.accessories = accessories;
        this.model_year = model_year;
        this.air_flow = air_flow;
        this.power = power;
        this.voltage = voltage;
        this.fan_speed = fan_speed;
        this.numbers_of_fan_speed = numbers_of_fan_speed;
        this.sound_at_max_speed = sound_at_max_speed;
        this.fan_sweep_diameter = fan_sweep_diameter;
        this.height = height;
        this.weight = weight;
        this.firm = firm;
        this.global = global;
        this.details = details;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.web = web;
        this.manufacturer_department = manufacturer_department;
        this.manufacturer_phone = manufacturer_phone;
        this.manufacturer_email = manufacturer_email;
        this.manufacturer_web = manufacturer_web;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUse_type() {
        return use_type;
    }

    public void setUse_type(String use_type) {
        this.use_type = use_type;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getMounting_location() {
        return mounting_location;
    }

    public void setMounting_location(String mounting_location) {
        this.mounting_location = mounting_location;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getModel_year() {
        return model_year;
    }

    public void setModel_year(String model_year) {
        this.model_year = model_year;
    }

    public String getAir_flow() {
        return air_flow;
    }

    public void setAir_flow(String air_flow) {
        this.air_flow = air_flow;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getFan_speed() {
        return fan_speed;
    }

    public void setFan_speed(String fan_speed) {
        this.fan_speed = fan_speed;
    }

    public String getNumbers_of_fan_speed() {
        return numbers_of_fan_speed;
    }

    public void setNumbers_of_fan_speed(String numbers_of_fan_speed) {
        this.numbers_of_fan_speed = numbers_of_fan_speed;
    }

    public String getSound_at_max_speed() {
        return sound_at_max_speed;
    }

    public void setSound_at_max_speed(String sound_at_max_speed) {
        this.sound_at_max_speed = sound_at_max_speed;
    }

    public String getFan_sweep_diameter() {
        return fan_sweep_diameter;
    }

    public void setFan_sweep_diameter(String fan_sweep_diameter) {
        this.fan_sweep_diameter = fan_sweep_diameter;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getGlobal() {
        return global;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getManufacturer_department() {
        return manufacturer_department;
    }

    public void setManufacturer_department(String manufacturer_department) {
        this.manufacturer_department = manufacturer_department;
    }

    public String getManufacturer_phone() {
        return manufacturer_phone;
    }

    public void setManufacturer_phone(String manufacturer_phone) {
        this.manufacturer_phone = manufacturer_phone;
    }

    public String getManufacturer_email() {
        return manufacturer_email;
    }

    public void setManufacturer_email(String manufacturer_email) {
        this.manufacturer_email = manufacturer_email;
    }

    public String getManufacturer_web() {
        return manufacturer_web;
    }

    public void setManufacturer_web(String manufacturer_web) {
        this.manufacturer_web = manufacturer_web;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", manufacturer='" + manufacturer + '\'' +
                ", series='" + series + '\'' +
                ", model='" + model + '\'' +
                ", use_type='" + use_type + '\'' +
                ", application='" + application + '\'' +
                ", mounting_location='" + mounting_location + '\'' +
                ", accessories='" + accessories + '\'' +
                ", model_year='" + model_year + '\'' +
                ", air_flow='" + air_flow + '\'' +
                ", power='" + power + '\'' +
                ", voltage='" + voltage + '\'' +
                ", fan_speed='" + fan_speed + '\'' +
                ", numbers_of_fan_speed='" + numbers_of_fan_speed + '\'' +
                ", sound_at_max_speed='" + sound_at_max_speed + '\'' +
                ", fan_sweep_diameter='" + fan_sweep_diameter + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", firm='" + firm + '\'' +
                ", global='" + global + '\'' +
                ", details='" + details + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                ", manufacturer_department='" + manufacturer_department + '\'' +
                ", manufacturer_phone='" + manufacturer_phone + '\'' +
                ", manufacturer_email='" + manufacturer_email + '\'' +
                ", manufacturer_web='" + manufacturer_web + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
