package www.OliveOnline.com.OliveShop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import www.OliveOnline.com.OliveShop.Dao.*;
import www.OliveOnline.com.OliveShop.Dto.AddressDto;
import www.OliveOnline.com.OliveShop.Dto.UserDto;
import www.OliveOnline.com.OliveShop.entity.*;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private  UsersRepository userRepository ;
    @Autowired
    private RoleRepository rolesRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AdministratorRepository administratorRepositor;
    @Autowired
    AdministratorService administratorService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public Users addUser(UserDto userDto, String roleName){
        Users user=new Users() ;
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Roles role = rolesRepository.findByRoleName(roleName)
                .orElseGet(() -> {

                    if (!rolesRepository.existsByRoleName(roleName)) {
                        Roles newRole = new Roles();
                        newRole.setRoleName(roleName);
                        return rolesRepository.save(newRole);
                    }
                    return null;
                });

        if (role == null) {
            throw new UsernameNotFoundException("Role name already exists: " + roleName);
        }

        user.setRole(role);
        if(role.getRoleName().equalsIgnoreCase("Customer")) {
            if (userDto.getAddresses() == null || userDto.getAddresses().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer must have at least one address.");
            } else {
                Customer customer = new Customer();
                customer.setUser(user);
                customer.setBalance(userDto.getBalance());
                for (AddressDto addressDto : userDto.getAddresses()) {
                    Address address = new Address();
                    address.setCity(addressDto.getCity());
                    address.setRegion(addressDto.getRegion());
                    address.setStreet(addressDto.getStreet());
                    address.setBuildingNumber(addressDto.getBuildingNumber());
                    address = addressRepository.save(address);
                    customer.getAddresses().add(address);
                }
                customer = customerRepository.save(customer);
            }
        } else if (role.getRoleName().toLowerCase().startsWith("admin")) {
            Administrator administrator=new Administrator();
            administrator.setUser(user);
            administrator.setPowerDescription(userDto.getPowerDescription());
            administrator=administratorRepositor.save(administrator);
        }

        return userRepository.save(user);
    }


    public List<Users> findAll(){
        return userRepository.findAll();
    }
    public void deletedUser(int  userID){
        Users user=userRepository.findById(userID).orElseThrow(()-> new UsernameNotFoundException("Not Found User the :"+userID));
        if("Customer".equalsIgnoreCase(user.getRole().getRoleName())){
            Customer customer=customerRepository.findByUser(user);
            if(customer!=null){
                customerService.deleteCustomer(customer.getId());
            }
        } else if (user.getRole().getRoleName().toLowerCase().startsWith("admin")) {
            Administrator administrator=administratorRepositor.findByUser(user);
            if(administrator!=null)
                administratorService.deleteAdmin(administrator.getId());
        }
        userRepository.delete(user);
    }
}
