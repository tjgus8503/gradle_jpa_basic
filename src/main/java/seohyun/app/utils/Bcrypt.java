package seohyun.app.utils;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class Bcrypt {

    public String HashPassword(String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        return hash;
    }

    public Boolean CompareHash(String password, String DBPassword) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(password, DBPassword)) {
            return true;
        } else {
            return false;
        }
    }

}
