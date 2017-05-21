package com.deviceinsight.services;

import com.deviceinsight.services.repository.HotelByLetterRepository;
import com.deviceinsight.services.repository.HotelRepository;
import com.deviceinsight.services.service.HotelService;
import com.deviceinsight.services.service.HotelServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfiguration {

    @Bean
    public HotelService hotelService(HotelRepository hotelRepository,
                                     HotelByLetterRepository hotelByLetterRepository) {
        return new HotelServiceImpl(hotelRepository, hotelByLetterRepository);
    }

}