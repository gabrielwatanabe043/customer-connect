package tech.gabrielwatanabe.customerconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.gabrielwatanabe.customerconnect.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM tb_customer", countQuery = "SELECT * FROM tb_customer", nativeQuery = true)
    Page<Customer> getCustomerPageable(PageRequest pageRequest);
}
