package class101.cpuboundapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
public class HashController {

    public static final int LOOP_COUNT = 100_000;
    public static final int KEY_SIZE = 256;

    @GetMapping("/hash/{value}")
    public String hashValue(@PathVariable String value) throws NoSuchAlgorithmException {
        wasteCpu();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        SecretKey key = keyGenerator.generateKey();
        return Arrays.toString(key.getEncoded());
    }

    private void wasteCpu() throws NoSuchAlgorithmException {
        for(int count = 0;count<LOOP_COUNT;count++){
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_SIZE);
            keyGenerator.generateKey();
        }
    }
}
