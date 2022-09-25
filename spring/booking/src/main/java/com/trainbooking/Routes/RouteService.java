package com.trainbooking.Routes;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> saveTrainRoutes(List<RouteDto> routeDto){
        List<Route> routeList = new ArrayList<>();
        for (RouteDto routDetails:routeDto) {
            Route route = new Route();
            route.setVia(routDetails.getVia());
            route.setDistanceFromSource(routDetails.getDistanceFromSource());
            routeList.add(route);
            this.routeRepository.save(route);
        }
        return routeList;
    }

}