package tech.gabrielwatanabe.customerconnect.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.gabrielwatanabe.customerconnect.dto.CreateCustomer;
import tech.gabrielwatanabe.customerconnect.dto.UpdateCustomer;
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
                                                              @RequestParam(value = "order", defaultValue = "desc", required = false)String order,
                                                              @RequestParam(value = "cpf", required = false)String cpf,
                                                              @RequestParam(value = "email",required = false)String email){
        return ResponseEntity.ok().body(this.customerService.getCustomers(page, pageSize, order, cpf, email));

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomerPorId(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(this.customerService.buscarCustomerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criaCustomer(@RequestBody CreateCustomer createCustomer){
        var customer = this.customerService.criaCustomer(createCustomer);
        return ResponseEntity.created(URI.create("/customer/" + customer.getId())).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable(value = "id") Long id, @RequestBody UpdateCustomer updateCustomer){
        this.customerService.atualizaCustomer(id, updateCustomer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> updateCustomer(@PathVariable(value = "id") Long id){
        this.customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
