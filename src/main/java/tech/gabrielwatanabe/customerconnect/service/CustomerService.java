package tech.gabrielwatanabe.customerconnect.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tech.gabrielwatanabe.customerconnect.dto.CreateCustomer;
import tech.gabrielwatanabe.customerconnect.model.Customer;
import tech.gabrielwatanabe.customerconnect.repository.CustomerRepository;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getCustomers(Integer page, Integer pageSize, String order, String cpf, String email){
        var direction = Sort.Direction.DESC;

        if(order.equalsIgnoreCase("asc")){
            direction = Sort.Direction.ASC;
        }

        PageRequest pageable = PageRequest.of(page, pageSize, direction, "data_criacao");

        if(StringUtils.hasText(cpf) && StringUtils.hasText(email)){
            return this.customerRepository.getCustomerCpfAndEmail(cpf, email, pageable);
        }

        if(StringUtils.hasText(cpf)){
            return this.customerRepository.getCustomerCpf(cpf, pageable);
        }

        if(StringUtils.hasText(email)){
            return this.customerRepository.getCustomerEmail(email, pageable);
        }

        return this.customerRepository.getCustomerPageable(pageable);
    }

    public Customer criaCustomer(CreateCustomer createCustomer){
      return  this.customerRepository.save(new Customer(createCustomer));
    }
}
