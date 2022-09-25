package com.trainbooking.Routes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
    private int id;
    private String via;
    private int distanceFromSource;
}