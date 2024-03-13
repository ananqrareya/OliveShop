package www.OliveOnline.com.OliveShop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.OliveOnline.com.OliveShop.Dao.AddressRepository;
import www.OliveOnline.com.OliveShop.Dao.CustomerRepository;
import www.OliveOnline.com.OliveShop.entity.Address;
import www.OliveOnline.com.OliveShop.entity.Customer;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository ;
    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public void deleteCustomer(int customerId){
        Customer customer=customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Customer Not Found :"+customerId) );
        for(Address address:customer.getAddresses()){
            address.getCustomers().remove(customer);
            if(address.getCustomers().isEmpty()){
                addressRepository.delete(address);
            }else {
                addressRepository.save(address);
            }
        }
        customerRepository.delete(customer);
    }
}
