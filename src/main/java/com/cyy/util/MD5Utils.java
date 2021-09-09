package com.cyy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

        public static String code(String str){
                try {
                        MessageDigest md = getAvailableMessageDigest();
                        md.update(str.getBytes());
                        byte[]byteDigest = md.digest();
                        int i;
                        StringBuffer buf = new StringBuffer("");
                        for (int offset = 0; offset < byteDigest.length; offset++) {
                                i = byteDigest[offset];
                                if (i < 0)
                                        i +=256;
                                if (i < 16)
                                        buf.append("0");
                                buf.append(Integer.toHexString(i));
                        }
                        return buf.toString();


                } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        return null;
                }
        }

        public static void main(String[] args) {
                System.out.println(code("123456"));
        }

        public static boolean isMDAvailable(String s)
        {
                boolean success=true;
                try{MessageDigest.getInstance(s);}
                catch(NoSuchAlgorithmException x)
                {
                        success=false;
                }
                return success;
        }
        public static MessageDigest getAvailableMessageDigest() throws NoSuchAlgorithmException {
                if(isMDAvailable("MD5")==true)return MessageDigest.getInstance("MD5");
                else if(isMDAvailable("MD2")==true)return MessageDigest.getInstance("MD2");
                else if(isMDAvailable("SHA-512")==true)return MessageDigest.getInstance("SHA-512");
                else return null;
        }

}
