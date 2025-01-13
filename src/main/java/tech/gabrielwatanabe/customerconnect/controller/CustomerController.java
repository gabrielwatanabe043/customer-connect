package tech.gabrielwatanabe.customerconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.gabrielwatanabe.customerconnect.dto.CreateCustomer;
import tech.gabrielwatanabe.customerconnect.service.CustomerService;

import java.net.URI;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> criaCustomer(@RequestBody CreateCustomer createCustomer){
        var customer = this.customerService.criaCustomer(createCustomer);
        return ResponseEntity.created(URI.create("/customer/" + customer.getId())).build();
    }

}
