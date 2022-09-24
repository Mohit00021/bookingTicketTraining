package com.trainbooking.Trains;

import com.example.TrainBooking.dto.RouteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrainDto {
    private Integer trainId;
    private String trainName;
    private String trainFrom;
    private String trainTo;
    private LocalDate departure;
    private LocalDate arrival;
    private LocalDate departureTime;
    private LocalDate arrivalTime;
    private int capacity;
    private List<RouteDto> routeDto;
}