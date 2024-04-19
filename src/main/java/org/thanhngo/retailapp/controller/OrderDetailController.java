package org.thanhngo.retailapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thanhngo.retailapp.dtos.OrderDetailDTO;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    //add 1 order detail
    @PostMapping("")
    public ResponseEntity<?> addOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("This is addOrderDetail: " + orderDetailDTO);

    }
    //Get an order detail by id
    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderDetailById(@Valid @PathVariable("id") Long orderDetailId) {
        return ResponseEntity.ok(String.format("OrderDetail with id = %s",orderDetailId));
    }
    //Get list of order detail of order id
    @GetMapping("/order/{orderId}")
    public ResponseEntity<String> getOrderDetailByOrderId(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(String.format("OrderDetail with orderId = %s",orderId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetailById(
            @Valid @PathVariable("id") Long id,
            @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok(String.format(
                "UpdateOrderDetail with id = "+ id +"and" + orderDetailDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetailById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(String.format("OrderDetail with id = %s was deleted successfully! ",id));
    }

}
