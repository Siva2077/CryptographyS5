import java.security.*;
import java.util.Base64;

public class DigitalSignatureDemo {
    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // key size
        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        String message = "Hi this is siva";
        System.out.println("Original Message: " + message);

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(privateKey);
        sign.update(message.getBytes());
        byte[] signatureBytes = sign.sign();
        String signature = Base64.getEncoder().encodeToString(signatureBytes);
        System.out.println("Digital Signature: " + signature);


        Signature verifySign = Signature.getInstance("SHA256withRSA");
        verifySign.initVerify(publicKey);
        verifySign.update(message.getBytes());
        boolean isCorrect = verifySign.verify(Base64.getDecoder().decode(signature));
        System.out.println("Signature Verified: " + isCorrect);
    }
}
