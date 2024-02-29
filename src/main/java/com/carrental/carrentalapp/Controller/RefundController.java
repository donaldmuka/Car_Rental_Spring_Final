package com.carrental.carrentalapp.Controller;

import com.carrental.carrentalapp.Model.Refund;
import com.carrental.carrentalapp.Service.RefundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RefundController {
    private final RefundService refundService;
    public RefundController(RefundService refundService) {
        this.refundService = refundService;
    }
    @PostMapping("/refund")
    public Refund addRefund(@RequestBody Refund refund) { return refundService.saveRefund(refund);
    }
    @GetMapping("/refund/{refund_id}")
    public Refund getRefundById(@PathVariable Long refund_id){
        return refundService.getRefundById(refund_id);
    }
    @GetMapping("/refund")
    public List<Refund> getRefund() {
        return refundService.getAllRefund();
    }
    @DeleteMapping("refund/{refund_id}")
    public void deleteRefundById(@PathVariable Long refund_id){
        refundService.deleteRefund(refund_id);
    }
}
