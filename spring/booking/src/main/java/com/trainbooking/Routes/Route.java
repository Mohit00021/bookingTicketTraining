package com.trainbooking.Routes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ROUTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String via;

    @Column
    private Integer distanceFromSource;

}