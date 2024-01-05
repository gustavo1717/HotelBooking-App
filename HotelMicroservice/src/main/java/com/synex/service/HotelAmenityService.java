package com.synex.service;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.synex.domain.Amenities;

import javax.sql.DataSource;
import java.util.List;

@Service
public class HotelAmenityService {

    private final JdbcTemplate jdbcTemplate;

    public HotelAmenityService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Amenities> getHotelAmenities(int hotelId) {
        String sql = "SELECT a.name AS amenityName " +
                     "FROM hotels_amenities ha " +
                     "JOIN amenities a ON ha.amenities_a_id = a.a_id " +
                     "WHERE ha.Hotel_hotelId = ?";

        return jdbcTemplate.query(sql, new Object[]{hotelId}, (rs, rowNum) -> {
            String amenityName = rs.getString("amenityName");
            return new Amenities();
        });
    }
}
