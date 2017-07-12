/*
    CryptLib is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    CryptLib is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CryptLib.  If not, see <http://www.gnu.org/licenses/>.
 */
package cryptlib;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author dmusiolik
 */
public class CryptLib {
 private String permPW;
    
    public CryptLib() {}
    
    public CryptLib(String permPW) {
        this.permPW = permPW;
    }
    
    public SecretKeySpec genKey(String schluessel) {
        SecretKeySpec returnable = null;
        try {
            // byte-Array erzeugen
            byte[] key = (schluessel).getBytes("UTF-8");
            // aus dem Array einen Hash-Wert erzeugen mit MD5 oder SHA
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            // nur die ersten 128 bit nutzen
            key = Arrays.copyOf(key, 16); 
            // der fertige Schluessel
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            returnable = secretKeySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnable;
    }
    
    public String encrypt(String text, String schluessel) {
        return encrypt_intern(text, schluessel);
    }
    
    public String encrypt(String text) {
        return encrypt_intern(text, permPW);
    }
    
    private String encrypt_intern(String text, String schluessel) {
        String returnable = null;
        try {
            // Verschluesseln
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, genKey(schluessel));
            byte[] encrypted = cipher.doFinal(text.getBytes());
            // bytes zu Base64-String konvertieren (dient der Lesbarkeit)
            BASE64Encoder myEncoder = new BASE64Encoder();
            returnable = myEncoder.encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            returnable = "CRYPTOGRAPHIC ERROR! THIS IS NOT WHAT YOU WANT TO SEE!";
        }
        return returnable;
    }
    
    public String decrypt(String verschluesselter_text, String passwort) {
        return decrypt_intern(verschluesselter_text, passwort);
    }
    
    public String decrypt(String verschluesselter_text) {
        return decrypt_intern(verschluesselter_text, permPW);
    }
    
    private String decrypt_intern(String verschluesselter_text, String passwort) {
        String returnable = null;
        try {
            // BASE64 String zu Byte-Array konvertieren
		BASE64Decoder myDecoder2 = new BASE64Decoder();
		byte[] crypted2 = myDecoder2.decodeBuffer(verschluesselter_text);
            // Entschluesseln
            Cipher cipher2 = Cipher.getInstance("AES");
            cipher2.init(Cipher.DECRYPT_MODE, genKey(passwort));
            byte[] cipherData2 = cipher2.doFinal(crypted2);
            returnable = new String(cipherData2);
        } catch (Exception e) {
            e.printStackTrace();
            returnable = "CRYPTOGRAPHIC ERROR! THIS IS NOT WHAT YOU WANT TO SEE!";
        }
        return returnable;
    }
    
}
