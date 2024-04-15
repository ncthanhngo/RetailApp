package org.thanhngo.retailapp.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.thanhngo.retailapp.dtos.OrderDTO;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO,
                                        BindingResult bindingResult ){
        try{
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = new ArrayList<>();
                for (FieldError error : bindingResult.getFieldErrors()) {
                    errorMessages.add(error.getDefaultMessage());
                }
                return ResponseEntity.badRequest().body(errorMessages);
            }
            return ResponseEntity.ok("Order scuccessfully");

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
