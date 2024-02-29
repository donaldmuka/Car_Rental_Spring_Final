package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Model.CreatePaymentResponse;
import com.carrental.carrentalapp.Model.PaymentRequest;
import com.carrental.carrentalapp.Model.Reservation;
import com.carrental.carrentalapp.Repository.CarRepository;
import com.carrental.carrentalapp.Repository.ReservationRepository;
import com.carrental.carrentalapp.Repository.UserRepository;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.net.Webhook;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionListLineItemsParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
//Update with your Api keys and fields for the payment intent and session that you need for the stripe payment
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class StripeController {
    @Value("${stripe.secretKey}")
    private String secretKey;
    private String sessionID="test";
    private Session tochecksession;
    String endpointSecret = "whsec_ab437ec515173d70d0b5e2142c3306374a3389f78b2f1ef9ba16c482f58ef733";
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    public StripeController(CarRepository carRepository,UserRepository userRepository,
                            ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }
    @PostMapping("/create-payment-intent1")
    public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("amount", paymentRequest.getAmount());
//        params.put("currency", "usd");
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setAmount((Long) paymentRequest.amount)
                .setCurrency("usd")
                .build();
        PaymentIntent paymentIntent = null;
        try {
            paymentIntent = PaymentIntent.create(createParams);
            Map<String, Object> clientSecretResponse = new HashMap<>();
            clientSecretResponse.put("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            return ResponseEntity.ok(paymentIntent.getClientSecret());
        }
    }
    @GetMapping("/confirm-payment1")
    public Session confirmPayment() throws StripeException {
        Stripe.apiKey = secretKey;
        System.out.println(tochecksession.getStatus());
        tochecksession.getStatus();
        return tochecksession;
    }
    @PostMapping("/create-checkout-session-stripe1")
    public ResponseEntity<Map<String, Object>> createCheckoutSession(@RequestBody PaymentRequest paymentRequest) {
        Stripe.apiKey = secretKey;
        Map<String, Object> customField = new HashMap<>();
        customField.put("reservationId", "12345");
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", Arrays.asList("card"));
        params.put("line_items", Collections.singletonList(
                new HashMap<String, Object>() {{
                    put("price_data", new HashMap<String, Object>() {{
                        put("currency", "usd");
                        put("product_data", new HashMap<String, Object>() {{
                            put("name", paymentRequest.name);
                        }});
                        put("unit_amount", paymentRequest.amount);
                    }});
                    put("quantity", 1);
                }}
        ));
        params.put("mode", "payment");
        params.put("success_url", "http://localhost:4200/success");
        params.put("cancel_url", "http://localhost:4200/cancel");
        params.put("customer_email",paymentRequest.customer_email);
        params.put("client_reference_id",userRepository.findUserByEmail((String)paymentRequest.customer_email));
        try {
            Session session = Session.create(params);
            Map<String, Object> sessionResponse = new HashMap<>();
            sessionResponse.put("sessionId", session.getId());
            sessionID=session.getId();
            tochecksession=session;
            return ResponseEntity.ok(sessionResponse);
        } catch (StripeException e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Failed to create Checkout Session"));
        }
    }
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload) throws StripeException {
        Event event = null;
        try {
            event = ApiResource.GSON.fromJson(payload, Event.class);
        } catch (JsonSyntaxException e) {
            // Invalid payload
            return new ResponseEntity<>("Invalid payload", HttpStatus.BAD_REQUEST);
        }
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = dataObjectDeserializer.getObject().orElse(null);
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                Session resource =
                        Session.retrieve(sessionID);
                SessionListLineItemsParams params = SessionListLineItemsParams.builder().build();
                LineItemCollection lineItems = resource.listLineItems(params);
                System.out.println(resource);
                Reservation reservation= new Reservation();
                reservation.setAmount(resource.getAmountTotal());
                reservation.setDateOfBooking(LocalDateTime.now());
                reservation.setClient(userRepository.findUserByEmail(resource.getCustomerEmail()));
                reservation.setCar(carRepository.findById(1L).get()) ;
                reservationRepository.save(reservation);
                break;
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                break;
            //  Handle other event types as needed
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
