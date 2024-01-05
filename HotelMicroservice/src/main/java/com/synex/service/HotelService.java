package com.synex.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.synex.domain.Hotel;
import com.synex.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired HotelRepository hotelRepository;
        
    
	public List <Hotel> findHotel(String searchString){
		searchString = "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrCityLikeOrStateLike(searchString, searchString, searchString);
	}
	
	public List<Hotel> findHotelByName(String searchString) {
        searchString = "%" + searchString + "%";
        return hotelRepository.findByHotelNameLike(searchString);
    }
	
	
}
