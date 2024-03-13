package www.OliveOnline.com.OliveShop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.OliveOnline.com.OliveShop.Dao.RoleRepository;
import www.OliveOnline.com.OliveShop.Dao.UsersRepository;
import www.OliveOnline.com.OliveShop.entity.Roles;
import www.OliveOnline.com.OliveShop.entity.Users;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository rolesRepository;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void deleteRole(String roleName){
        Roles roles =rolesRepository.findByRoleName(roleName).orElseThrow(()->new RuntimeException("Not Found Roles"+roleName));
        List<Users> users=userRepository.findByRole(roles);
        for(Users user:users){
            userService.deletedUser(user.getUserID());
        }
        rolesRepository.delete(roles);
    }
}
