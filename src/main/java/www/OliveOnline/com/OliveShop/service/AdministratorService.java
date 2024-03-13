package www.OliveOnline.com.OliveShop.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import www.OliveOnline.com.OliveShop.Dao.AdministratorRepository;
import www.OliveOnline.com.OliveShop.entity.Administrator;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    @Transactional
    public void deleteAdmin(int adminID){
        Administrator administrator=administratorRepository.findById(adminID).orElseThrow(()-> new RuntimeException("NOT FOUND ADMIN"+adminID));
        administratorRepository.delete(administrator);
    }
}
