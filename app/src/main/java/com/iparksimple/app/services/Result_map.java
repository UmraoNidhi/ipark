package com.iparksimple.app.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result_map {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Data {

        @SerializedName("lots")
        @Expose
        private ArrayList<Lot> lots = null;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("park_type")
        @Expose
        private Object parkType;
        @SerializedName("price")
        @Expose
        private Object price;
        @SerializedName("price_park_type")
        @Expose
        private Object priceParkType;
        @SerializedName("distance")
        @Expose
        private Integer distance;
        @SerializedName("lang")
        @Expose
        private String lang;
        @SerializedName("location")
        @Expose
        private String location;

        public ArrayList<Lot> getLots() {
            return lots;
        }

        public void setLots(ArrayList<Lot> lots) {
            this.lots = lots;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public Object getParkType() {
            return parkType;
        }

        public void setParkType(Object parkType) {
            this.parkType = parkType;
        }

        public Object getPrice() {
            return price;
        }

        public void setPrice(Object price) {
            this.price = price;
        }

        public Object getPriceParkType() {
            return priceParkType;
        }

        public void setPriceParkType(Object priceParkType) {
            this.priceParkType = priceParkType;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public class Lot {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("lot_type")
            @Expose
            private String lotType;
            @SerializedName("airport_id")
            @Expose
            private String airportId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("emails")
            @Expose
            private String emails;
            @SerializedName("lot_image")
            @Expose
            private String lotImage;
            @SerializedName("lot_number")
            @Expose
            private String lotNumber;
            @SerializedName("vendor")
            @Expose
            private String vendor;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("latitude")
            @Expose
            private String latitude;
            @SerializedName("longitude")
            @Expose
            private String longitude;
            @SerializedName("hourly_price")
            @Expose
            private String hourlyPrice;
            @SerializedName("daily_price")
            @Expose
            private String dailyPrice;
            @SerializedName("over_night_price")
            @Expose
            private String overNightPrice;
            @SerializedName("over_night_hourly_price")
            @Expose
            private String overNightHourlyPrice;
            @SerializedName("weekly_price")
            @Expose
            private Object weeklyPrice;
            @SerializedName("monthly_price")
            @Expose
            private String monthlyPrice;
            @SerializedName("convenience_fee")
            @Expose
            private String convenienceFee;
            @SerializedName("popular")
            @Expose
            private String popular;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("capacity")
            @Expose
            private String capacity;
            @SerializedName("commission")
            @Expose
            private String commission;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("opening_time")
            @Expose
            private Object openingTime;
            @SerializedName("closing_time")
            @Expose
            private Object closingTime;
            @SerializedName("working_days")
            @Expose
            private String workingDays;
            @SerializedName("is_full_day")
            @Expose
            private String isFullDay;
            @SerializedName("free_hour")
            @Expose
            private String freeHour;
            @SerializedName("no_of_times")
            @Expose
            private String noOfTimes;
            @SerializedName("free_hour_per")
            @Expose
            private String freeHourPer;
            @SerializedName("fixed_month")
            @Expose
            private String fixedMonth;
            @SerializedName("url_slug")
            @Expose
            private String urlSlug;
            @SerializedName("extra_field_name")
            @Expose
            private Object extraFieldName;
            @SerializedName("is_required")
            @Expose
            private String isRequired;
            @SerializedName("distance")
            @Expose
            private String distance;
            @SerializedName("address")
            @Expose
            private String address;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("state")
            @Expose
            private String state;
            @SerializedName("zipcode")
            @Expose
            private String zipcode;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLotType() {
                return lotType;
            }

            public void setLotType(String lotType) {
                this.lotType = lotType;
            }

            public String getAirportId() {
                return airportId;
            }

            public void setAirportId(String airportId) {
                this.airportId = airportId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmails() {
                return emails;
            }

            public void setEmails(String emails) {
                this.emails = emails;
            }

            public String getLotImage() {
                return lotImage;
            }

            public void setLotImage(String lotImage) {
                this.lotImage = lotImage;
            }

            public String getLotNumber() {
                return lotNumber;
            }

            public void setLotNumber(String lotNumber) {
                this.lotNumber = lotNumber;
            }

            public String getVendor() {
                return vendor;
            }

            public void setVendor(String vendor) {
                this.vendor = vendor;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getHourlyPrice() {
                return hourlyPrice;
            }

            public void setHourlyPrice(String hourlyPrice) {
                this.hourlyPrice = hourlyPrice;
            }

            public String getDailyPrice() {
                return dailyPrice;
            }

            public void setDailyPrice(String dailyPrice) {
                this.dailyPrice = dailyPrice;
            }

            public String getOverNightPrice() {
                return overNightPrice;
            }

            public void setOverNightPrice(String overNightPrice) {
                this.overNightPrice = overNightPrice;
            }

            public String getOverNightHourlyPrice() {
                return overNightHourlyPrice;
            }

            public void setOverNightHourlyPrice(String overNightHourlyPrice) {
                this.overNightHourlyPrice = overNightHourlyPrice;
            }

            public Object getWeeklyPrice() {
                return weeklyPrice;
            }

            public void setWeeklyPrice(Object weeklyPrice) {
                this.weeklyPrice = weeklyPrice;
            }

            public String getMonthlyPrice() {
                return monthlyPrice;
            }

            public void setMonthlyPrice(String monthlyPrice) {
                this.monthlyPrice = monthlyPrice;
            }

            public String getConvenienceFee() {
                return convenienceFee;
            }

            public void setConvenienceFee(String convenienceFee) {
                this.convenienceFee = convenienceFee;
            }

            public String getPopular() {
                return popular;
            }

            public void setPopular(String popular) {
                this.popular = popular;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCapacity() {
                return capacity;
            }

            public void setCapacity(String capacity) {
                this.capacity = capacity;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Object getOpeningTime() {
                return openingTime;
            }

            public void setOpeningTime(Object openingTime) {
                this.openingTime = openingTime;
            }

            public Object getClosingTime() {
                return closingTime;
            }

            public void setClosingTime(Object closingTime) {
                this.closingTime = closingTime;
            }

            public String getWorkingDays() {
                return workingDays;
            }

            public void setWorkingDays(String workingDays) {
                this.workingDays = workingDays;
            }

            public String getIsFullDay() {
                return isFullDay;
            }

            public void setIsFullDay(String isFullDay) {
                this.isFullDay = isFullDay;
            }

            public String getFreeHour() {
                return freeHour;
            }

            public void setFreeHour(String freeHour) {
                this.freeHour = freeHour;
            }

            public String getNoOfTimes() {
                return noOfTimes;
            }

            public void setNoOfTimes(String noOfTimes) {
                this.noOfTimes = noOfTimes;
            }

            public String getFreeHourPer() {
                return freeHourPer;
            }

            public void setFreeHourPer(String freeHourPer) {
                this.freeHourPer = freeHourPer;
            }

            public String getFixedMonth() {
                return fixedMonth;
            }

            public void setFixedMonth(String fixedMonth) {
                this.fixedMonth = fixedMonth;
            }

            public String getUrlSlug() {
                return urlSlug;
            }

            public void setUrlSlug(String urlSlug) {
                this.urlSlug = urlSlug;
            }

            public Object getExtraFieldName() {
                return extraFieldName;
            }

            public void setExtraFieldName(Object extraFieldName) {
                this.extraFieldName = extraFieldName;
            }

            public String getIsRequired() {
                return isRequired;
            }

            public void setIsRequired(String isRequired) {
                this.isRequired = isRequired;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getZipcode() {
                return zipcode;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

        }


    }


}
