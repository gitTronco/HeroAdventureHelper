package com.troncodroide.heroadventurehelper.utils;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

/**
 * Created by redegal010 on 11/02/16.
 */
public class Encryptutils {


    public static String encryp( String password, String message){
        try {
            return AESCrypt.encrypt(password, message);
        }catch (GeneralSecurityException e){return ""; }
    }

    public static String desEncryp( String password, String encryptedMsg){
        try {
            return AESCrypt.decrypt(password, encryptedMsg);
        }catch (GeneralSecurityException e){
            return "";
        }
    }


}
