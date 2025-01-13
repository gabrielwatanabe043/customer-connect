package tech.gabrielwatanabe.customerconnect.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gabrielwatanabe.customerconnect.dto.CreateCustomer;
import tech.gabrielwatanabe.customerconnect.model.Customer;
import tech.gabrielwatanabe.customerconnect.service.CustomerService;

import java.net.URI;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getCustomerPageable(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(value = "pageSize", defaultValue = "10",required = false)Integer pageSize,
                                                              @RequestParam(value = "order", defaultValue = "desc", required = false)String order){
        return ResponseEntity.ok().body(this.customerService.getCustomers(page, pageSize, order));

    }

    @PostMapping
    public ResponseEntity<Void> criaCustomer(@RequestBody CreateCustomer createCustomer){
        var customer = this.customerService.criaCustomer(createCustomer);
        return ResponseEntity.created(URI.create("/customer/" + customer.getId())).build();
    }

}
