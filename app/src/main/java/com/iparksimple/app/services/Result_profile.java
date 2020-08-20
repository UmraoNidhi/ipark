package com.iparksimple.app.services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Result_profile {
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

        @SerializedName("customer")
        @Expose
        private Customer customer;
        @SerializedName("vehicles")
        @Expose
        private ArrayList<Vehicle> vehicles = null;
        @SerializedName("cards")
        @Expose
        private ArrayList<Object> cards = null;
        @SerializedName("previous_bookings")
        @Expose
        private ArrayList<Object> previousBookings = null;
        @SerializedName("active_bookings")
        @Expose
        private ArrayList<ActiveBooking> activeBookings = null;
        @SerializedName("upcoming_bookings")
        @Expose
        private ArrayList<Object> upcomingBookings = null;

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public ArrayList<Vehicle> getVehicles() {
            return vehicles;
        }

        public void setVehicles(ArrayList<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }

        public ArrayList<Object> getCards() {
            return cards;
        }

        public void setCards(ArrayList<Object> cards) {
            this.cards = cards;
        }

        public ArrayList<Object> getPreviousBookings() {
            return previousBookings;
        }

        public void setPreviousBookings(ArrayList<Object> previousBookings) {
            this.previousBookings = previousBookings;
        }

        public ArrayList<ActiveBooking> getActiveBookings() {
            return activeBookings;
        }

        public void setActiveBookings(ArrayList<ActiveBooking> activeBookings) {
            this.activeBookings = activeBookings;
        }

        public ArrayList<Object> getUpcomingBookings() {
            return upcomingBookings;
        }

        public void setUpcomingBookings(ArrayList<Object> upcomingBookings) {
            this.upcomingBookings = upcomingBookings;
        }

        public class Customer {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("phone")
            @Expose
            private String phone;

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

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

        }
        public class ActiveBooking {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("booking_number")
            @Expose
            private String bookingNumber;
            @SerializedName("extend_booking_id")
            @Expose
            private Object extendBookingId;
            @SerializedName("customer_id")
            @Expose
            private Object customerId;
            @SerializedName("lot_id")
            @Expose
            private String lotId;
            @SerializedName("vehicle_id")
            @Expose
            private Object vehicleId;
            @SerializedName("vendor_id")
            @Expose
            private String vendorId;
            @SerializedName("manager_id")
            @Expose
            private String managerId;
            @SerializedName("first_name")
            @Expose
            private Object firstName;
            @SerializedName("last_name")
            @Expose
            private Object lastName;
            @SerializedName("email")
            @Expose
            private Object email;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("model")
            @Expose
            private Object model;
            @SerializedName("color")
            @Expose
            private Object color;
            @SerializedName("plate_number")
            @Expose
            private String plateNumber;
            @SerializedName("state")
            @Expose
            private Object state;
            @SerializedName("amt")
            @Expose
            private String amt;
            @SerializedName("book_date")
            @Expose
            private String bookDate;
            @SerializedName("book_time")
            @Expose
            private String bookTime;
            @SerializedName("book_type")
            @Expose
            private Object bookType;
            @SerializedName("number_of")
            @Expose
            private String numberOf;
            @SerializedName("payment_status")
            @Expose
            private String paymentStatus;
            @SerializedName("start_time")
            @Expose
            private String startTime;
            @SerializedName("end_time")
            @Expose
            private String endTime;
            @SerializedName("cancel_status")
            @Expose
            private String cancelStatus;
            @SerializedName("cancelled_by")
            @Expose
            private Object cancelledBy;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("parking_start_alert")
            @Expose
            private String parkingStartAlert;
            @SerializedName("parking_start_alert_count")
            @Expose
            private Object parkingStartAlertCount;
            @SerializedName("parking_end_alert")
            @Expose
            private String parkingEndAlert;
            @SerializedName("parking_end_alert_count")
            @Expose
            private Object parkingEndAlertCount;
            @SerializedName("booking_type")
            @Expose
            private Object bookingType;
            @SerializedName("promotion_id")
            @Expose
            private Object promotionId;
            @SerializedName("convenience_fee")
            @Expose
            private Object convenienceFee;
            @SerializedName("commission")
            @Expose
            private Object commission;
            @SerializedName("recurring_payment")
            @Expose
            private String recurringPayment;
            @SerializedName("is_overnight_included")
            @Expose
            private String isOvernightIncluded;
            @SerializedName("vehicle_pricing_id")
            @Expose
            private Object vehiclePricingId;
            @SerializedName("is_free_hour_used")
            @Expose
            private String isFreeHourUsed;
            @SerializedName("flat_number")
            @Expose
            private Object flatNumber;
            @SerializedName("used_discount_code")
            @Expose
            private Object usedDiscountCode;
            @SerializedName("discount")
            @Expose
            private Object discount;
            @SerializedName("server_time")
            @Expose
            private Object serverTime;
            @SerializedName("name")
            @Expose
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBookingNumber() {
                return bookingNumber;
            }

            public void setBookingNumber(String bookingNumber) {
                this.bookingNumber = bookingNumber;
            }

            public Object getExtendBookingId() {
                return extendBookingId;
            }

            public void setExtendBookingId(Object extendBookingId) {
                this.extendBookingId = extendBookingId;
            }

            public Object getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Object customerId) {
                this.customerId = customerId;
            }

            public String getLotId() {
                return lotId;
            }

            public void setLotId(String lotId) {
                this.lotId = lotId;
            }

            public Object getVehicleId() {
                return vehicleId;
            }

            public void setVehicleId(Object vehicleId) {
                this.vehicleId = vehicleId;
            }

            public String getVendorId() {
                return vendorId;
            }

            public void setVendorId(String vendorId) {
                this.vendorId = vendorId;
            }

            public String getManagerId() {
                return managerId;
            }

            public void setManagerId(String managerId) {
                this.managerId = managerId;
            }

            public Object getFirstName() {
                return firstName;
            }

            public void setFirstName(Object firstName) {
                this.firstName = firstName;
            }

            public Object getLastName() {
                return lastName;
            }

            public void setLastName(Object lastName) {
                this.lastName = lastName;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getModel() {
                return model;
            }

            public void setModel(Object model) {
                this.model = model;
            }

            public Object getColor() {
                return color;
            }

            public void setColor(Object color) {
                this.color = color;
            }

            public String getPlateNumber() {
                return plateNumber;
            }

            public void setPlateNumber(String plateNumber) {
                this.plateNumber = plateNumber;
            }

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }

            public String getAmt() {
                return amt;
            }

            public void setAmt(String amt) {
                this.amt = amt;
            }

            public String getBookDate() {
                return bookDate;
            }

            public void setBookDate(String bookDate) {
                this.bookDate = bookDate;
            }

            public String getBookTime() {
                return bookTime;
            }

            public void setBookTime(String bookTime) {
                this.bookTime = bookTime;
            }

            public Object getBookType() {
                return bookType;
            }

            public void setBookType(Object bookType) {
                this.bookType = bookType;
            }

            public String getNumberOf() {
                return numberOf;
            }

            public void setNumberOf(String numberOf) {
                this.numberOf = numberOf;
            }

            public String getPaymentStatus() {
                return paymentStatus;
            }

            public void setPaymentStatus(String paymentStatus) {
                this.paymentStatus = paymentStatus;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getCancelStatus() {
                return cancelStatus;
            }

            public void setCancelStatus(String cancelStatus) {
                this.cancelStatus = cancelStatus;
            }

            public Object getCancelledBy() {
                return cancelledBy;
            }

            public void setCancelledBy(Object cancelledBy) {
                this.cancelledBy = cancelledBy;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getParkingStartAlert() {
                return parkingStartAlert;
            }

            public void setParkingStartAlert(String parkingStartAlert) {
                this.parkingStartAlert = parkingStartAlert;
            }

            public Object getParkingStartAlertCount() {
                return parkingStartAlertCount;
            }

            public void setParkingStartAlertCount(Object parkingStartAlertCount) {
                this.parkingStartAlertCount = parkingStartAlertCount;
            }

            public String getParkingEndAlert() {
                return parkingEndAlert;
            }

            public void setParkingEndAlert(String parkingEndAlert) {
                this.parkingEndAlert = parkingEndAlert;
            }

            public Object getParkingEndAlertCount() {
                return parkingEndAlertCount;
            }

            public void setParkingEndAlertCount(Object parkingEndAlertCount) {
                this.parkingEndAlertCount = parkingEndAlertCount;
            }

            public Object getBookingType() {
                return bookingType;
            }

            public void setBookingType(Object bookingType) {
                this.bookingType = bookingType;
            }

            public Object getPromotionId() {
                return promotionId;
            }

            public void setPromotionId(Object promotionId) {
                this.promotionId = promotionId;
            }

            public Object getConvenienceFee() {
                return convenienceFee;
            }

            public void setConvenienceFee(Object convenienceFee) {
                this.convenienceFee = convenienceFee;
            }

            public Object getCommission() {
                return commission;
            }

            public void setCommission(Object commission) {
                this.commission = commission;
            }

            public String getRecurringPayment() {
                return recurringPayment;
            }

            public void setRecurringPayment(String recurringPayment) {
                this.recurringPayment = recurringPayment;
            }

            public String getIsOvernightIncluded() {
                return isOvernightIncluded;
            }

            public void setIsOvernightIncluded(String isOvernightIncluded) {
                this.isOvernightIncluded = isOvernightIncluded;
            }

            public Object getVehiclePricingId() {
                return vehiclePricingId;
            }

            public void setVehiclePricingId(Object vehiclePricingId) {
                this.vehiclePricingId = vehiclePricingId;
            }

            public String getIsFreeHourUsed() {
                return isFreeHourUsed;
            }

            public void setIsFreeHourUsed(String isFreeHourUsed) {
                this.isFreeHourUsed = isFreeHourUsed;
            }

            public Object getFlatNumber() {
                return flatNumber;
            }

            public void setFlatNumber(Object flatNumber) {
                this.flatNumber = flatNumber;
            }

            public Object getUsedDiscountCode() {
                return usedDiscountCode;
            }

            public void setUsedDiscountCode(Object usedDiscountCode) {
                this.usedDiscountCode = usedDiscountCode;
            }

            public Object getDiscount() {
                return discount;
            }

            public void setDiscount(Object discount) {
                this.discount = discount;
            }

            public Object getServerTime() {
                return serverTime;
            }

            public void setServerTime(Object serverTime) {
                this.serverTime = serverTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
        public class Vehicle {

            @SerializedName("id")
            @Expose
            private String id;
            @SerializedName("customer_id")
            @Expose
            private String customerId;
            @SerializedName("model")
            @Expose
            private String model;
            @SerializedName("color")
            @Expose
            private String color;
            @SerializedName("plate_number")
            @Expose
            private String plateNumber;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getPlateNumber() {
                return plateNumber;
            }

            public void setPlateNumber(String plateNumber) {
                this.plateNumber = plateNumber;
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

        }

    }

}
