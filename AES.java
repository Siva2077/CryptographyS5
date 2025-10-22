package rt;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class AES {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter text: ");
            String originalText = sc.nextLine();

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // 128-bit AES key
            SecretKey secretKey = keyGen.generateKey();

            Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] textBytes = originalText.getBytes();
            byte[] encryptedBytes = aesCipher.doFinal(textBytes);
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

            System.out.println("Encrypted Text: " + encryptedBase64);

            aesCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = aesCipher.doFinal(Base64.getDecoder().decode(encryptedBase64));
            String decryptedText = new String(decryptedBytes);

            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
