package tech.gabrielwatanabe.customerconnect.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.gabrielwatanabe.customerconnect.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM tb_customer", countQuery = "SELECT * FROM tb_customer", nativeQuery = true)
    Page<Customer> getCustomerPageable(PageRequest pageRequest);

    @Query(value = "SELECT * FROM tb_customer WHERE cpf = :cpf and email = :email", countQuery = "SELECT * FROM tb_customer WHERE cpf = :cpf and email = :email", nativeQuery = true)
    Page<Customer> getCustomerCpfAndEmail(@Param("cpf") String cpf, @Param("email") String email, PageRequest pageRequest);

    @Query(value = "SELECT * FROM tb_customer WHERE cpf = :cpf", countQuery = "SELECT * FROM tb_customer WHERE cpf = :cpf", nativeQuery = true)
    Page<Customer> getCustomerCpf(@Param("cpf") String cpf, PageRequest pageRequest);

    @Query(value = "SELECT * FROM tb_customer WHERE email = :email", countQuery = "SELECT * FROM tb_customer WHERE email = :email", nativeQuery = true)
    Page<Customer> getCustomerEmail(@Param("email") String email, PageRequest pageRequest);
}
