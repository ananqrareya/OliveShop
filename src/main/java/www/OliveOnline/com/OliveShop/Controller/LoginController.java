package www.OliveOnline.com.OliveShop.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import www.OliveOnline.com.OliveShop.Dao.RoleRepository;
import www.OliveOnline.com.OliveShop.Dao.UsersRepository;
import www.OliveOnline.com.OliveShop.Dto.UserDto;
import www.OliveOnline.com.OliveShop.entity.Roles;
import www.OliveOnline.com.OliveShop.entity.Users;
import www.OliveOnline.com.OliveShop.service.UserService;

@Controller
public class LoginController {

    @Autowired
  private  UserService userService;
    @Autowired
   private RoleRepository roleRepository;

@GetMapping("/login")
    public String login(){
    return "login";
}
@GetMapping("/register")
    public String registerGet(Model model)
{
    model.addAttribute("userDto",new UserDto());
    return "register";
}
@PostMapping("register")
    public String registerPost(@ModelAttribute("userDto")UserDto userDto , HttpServletRequest request)throws ServletException{
   String roleName=userDto.getRoleName();
    userService.addUser(userDto,roleName);
    request.login(userDto.getUserName(),userDto.getPassword());
    return "redirect:/";
}
}
