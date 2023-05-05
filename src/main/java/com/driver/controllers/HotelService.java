package com.driver.controllers;

import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.model.Booking;
import com.driver.model.Facility;

import java.util.List;
public class HotelService {
    HotelRepo repo=new HotelRepo();

    public String addHotel(Hotel hotel){
        String ans = repo.addHotel(hotel);
        return ans;
    }

    public int addUser(User user){
        int adhar = repo.addUser(user);
        return adhar;
    }

    public String getHotelWithMostFacility(){
        String hotelName = repo.getHotelWithMostFacilities();
        return hotelName;
    }

    public int bookARoom(Booking booking){
        return repo.bookARoom(booking);
    }

    public int getBooking(Integer adharCard){
        int count = repo.getBooking(adharCard);
        return count;
    }

    public Hotel updateFacility(List<Facility> newFacilities, String hotelName){
        return repo.updateFacility(newFacilities, hotelName);
    }
}

