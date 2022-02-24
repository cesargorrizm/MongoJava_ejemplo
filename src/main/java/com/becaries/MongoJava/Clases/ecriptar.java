package com.becaries.MongoJava.Clases;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import org.apache.commons.codec.binary.Base64;

// Clase que se encarga de encriptar la contraseña para ayor seguridad del usuario
public class Ecriptar {
    public static String secretKey = "pr@//oyectoDeD@{{iselo~~De##Inte´ñ-´-.`+``+.´-.+´ñ-+`32_·$445324·2rf&&&&aces||M?¿?¿?¿uyM@aloEnSegurid?¿?¿?¨´¿a-´-´.+.-d";

    // metodo que se encarga de enctriptar la contraseña con el codigo de
    // encripacion personalizados
    public static String ecnode(String cadena) {
        String encriptacion = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llavePassword = md5.digest(secretKey.getBytes("utf-8"));
            byte[] BytesKey = Arrays.copyOf(llavePassword, 24);
            SecretKey key = new SecretKeySpec(BytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = cadena.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encriptacion = new String(base64Bytes);
        } catch (Exception ex) {
            // Si hay fallo informas al usuario
            JOptionPane.showMessageDialog(null, "Error al encriptar");
        }
        return encriptacion;
    }


}
