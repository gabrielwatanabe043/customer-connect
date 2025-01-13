package tech.gabrielwatanabe.customerconnect.service;

import org.springframework.stereotype.Service;
import tech.gabrielwatanabe.customerconnect.dto.CreateCustomer;
import tech.gabrielwatanabe.customerconnect.model.Customer;
import tech.gabrielwatanabe.customerconnect.repository.CustomerRepository;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer criaCustomer(CreateCustomer createCustomer){
      return   this.customerRepository.save(new Customer(createCustomer));
    }
}
