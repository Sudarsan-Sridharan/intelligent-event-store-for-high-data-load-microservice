package com.deviceinsight.services.service;




import com.deviceinsight.services.domain.Hotel;
import com.deviceinsight.services.domain.HotelByLetter;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    Hotel save(Hotel hotel);
    Hotel update(Hotel hotel);
    Hotel findOne(UUID uuid);
    void delete(UUID uuid);

    List<HotelByLetter> findHotelsStartingWith(String letter);
    List<Hotel> findHotelsInState(String state);
}