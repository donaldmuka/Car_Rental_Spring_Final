package com.carrental.carrentalapp.Model;
//Payment request object fot the stripe payment
//Add fields as per your needs after confirming them with the stripe Doc
public class PaymentRequest {
    public Object name;
    public Long amount;
    public Object customer_email;

    Long carid = 1L;
    String user = "";
    public Long getCarid(){return carid;
    }
}
