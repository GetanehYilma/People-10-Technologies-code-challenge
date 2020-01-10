package com.people10.datauploader.domain;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {

    private String signUpDate;
    private String first;
    private String last;
    private String email;
    private double latitude;
    private double longitude;
    private String ip;

}
