package com.driver.controllers;

import java.util.*;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.model.Booking;
import com.driver.model.Facility;


public class HotelRepo {
    private HashMap<String, Hotel> hotelD=new HashMap<>();
    private HashMap<Integer,User> userD=new HashMap<>();
    private HashMap<String,Booking> bookingD=new HashMap<>();
    private HashMap<Integer,Integer> countBooking=new HashMap<>();

    public String addHotel(Hotel hotel){
        if(hotel== null || hotel.getHotelName()==null || hotelD.containsKey(hotel.getHotelName())){
            return "Fail";
        } //Alpha Trion.

        hotelD.put(hotel.getHotelName(),hotel);
        return "Pass";
    }

    public Integer addUser(User user){

        userD.put(user.getaadharCardNo(), user);

        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities(){
        int facilities= 0;

        String hotelName = "";

        for(Hotel hotel:hotelD.values()){

            if(hotel.getFacilities().size()>facilities){
                facilities = hotel.getFacilities().size();
                hotelName = hotel.getHotelName();
            } // Alpha Trion
            else if(hotel.getFacilities().size()==facilities && hotel.getHotelName().compareTo(hotelName)<0){
                    hotelName = hotel.getHotelName();
            }
        }
        return hotelName;
    }

    public int bookARoom(Booking booking){
        String key = UUID.randomUUID().toString();

        booking.setBookingId(key);

        String hotelName = booking.getHotelName();

        Hotel hotel = hotelD.get(hotelName);

        int availableRooms = hotel.getAvailableRooms();

        if(availableRooms<booking.getNoOfRooms()){
            return -1;
        }

        int amountToBePaid = hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(amountToBePaid);

        //Make sure we check this part of code as well
        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());

        bookingD.put(key,booking);

        hotelD.put(hotelName,hotel);

        int aadharCard = booking.getBookingAadharCard();
        Integer currentBookings = countBooking.get(aadharCard);
        countBooking.put(aadharCard, Objects.nonNull(currentBookings)?1+currentBookings:1);
        return amountToBePaid;
    }

    public int getBooking(Integer adharCard){
        return countBooking.get(adharCard);
    }

    public Hotel updateFacility(List<Facility> newFacilities, String hotelName){
        List<Facility> oldFacilities = hotelD.get(hotelName).getFacilities();

        for(Facility facility: newFacilities){

            if(oldFacilities.contains(facility)){
                continue;
            }else{
                oldFacilities.add(facility);
            }
        }

        Hotel hotel = hotelD.get(hotelName);
        hotel.setFacilities(oldFacilities);

        hotelD.put(hotelName,hotel);

        return hotel;
    }

}
