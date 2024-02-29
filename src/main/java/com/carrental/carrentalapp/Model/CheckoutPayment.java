package com.carrental.carrentalapp.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//Use for the stripe integration
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutPayment {
    private String name;
    private String currency;
    private String successUrl;
    private String cancelUrl;
    private long amount;
    private long quantity;

}