package com.carrental.carrentalapp.Service;

import com.carrental.carrentalapp.Model.Car;
import com.carrental.carrentalapp.Model.Refund;
import com.carrental.carrentalapp.Repository.CarRepository;
import com.carrental.carrentalapp.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// At the moment the app does not handel refunds because i am integrating it with stripe but you can modify this service if you need refunds
@Service
public class RefundService {
    private final RefundRepository refundRepository;
    public RefundService(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    public List<Refund> getAllRefund(){
        return refundRepository.findAll();
    }
    public Refund getRefundById(Long id){
        return refundRepository.findById(id).orElseThrow(()-> new RuntimeException("Refund not found with id " +id));
    }
    public Refund saveRefund (Refund refund){
        return refundRepository.save(refund);
    }
    public void deleteRefund(Long id){
        refundRepository.deleteById(id);
    }

}
