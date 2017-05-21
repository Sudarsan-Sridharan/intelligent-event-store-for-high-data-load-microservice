package com.deviceinsight.services.repository;


import com.deviceinsight.services.domain.HotelByLetter;
import com.deviceinsight.services.domain.HotelByLetterKey;

import java.util.List;

public interface HotelByLetterRepository {
    List<HotelByLetter> findByFirstLetter(String letter);
    HotelByLetter save(HotelByLetter hotelByLetter);
    void delete(HotelByLetterKey hotelByLetterKey);
}