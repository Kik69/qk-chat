package com.qk.chat.common.utils;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * {@code @ClassName} TextUtils
 * {@code @Description} TODO
 * {@code @Author} ZYL
 * {@code @Date} 2023/10/25 10:23
 */

public class TextUtil {
    private static long UIDCounter = System.currentTimeMillis();

    public TextUtil() {
    }

    public static String processChinese(String chinese) {
        try {
            byte[] byteArray = chinese.getBytes("GBK");
            chinese = new String(byteArray, "ISO8859_1");
        } catch (Exception var2) {
        }

        return chinese;
    }

    public static String processChineseOther(String chinese) {
        try {
            byte[] byteArray = chinese.getBytes("ISO-8859-1");
            chinese = new String(byteArray, "GB2312");
        } catch (Exception var2) {
        }

        return chinese;
    }

    public static String processGB2312ToUTF8(String chinese) {
        try {
            byte[] byteArray = chinese.getBytes("GB2312");
            chinese = new String(byteArray, "UTF-8");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return chinese;
    }

    public static String processUTF8ToGB2312(String chinese) {
        try {
            byte[] byteArray = chinese.getBytes("UTF-8");
            chinese = new String(byteArray, "GB2312");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        return chinese;
    }

    public static String transNull(String input) {
        return input == null ? "" : input;
    }

    public static String processISO(String chinese) {
        try {
            byte[] byteArray = chinese.getBytes("GB2312");
            chinese = new String(byteArray, "ISO-8859-1");
        } catch (Exception var2) {
        }

        return chinese;
    }

    public static boolean isNotNull(String sstring) {
        boolean bolret = true;
        if (isNull(sstring)) {
            bolret = false;
        }

        return bolret;
    }

    public static boolean isNotNull(int iint) {
        boolean bolret = true;
        if (iint <= 0) {
            bolret = false;
        }

        return bolret;
    }

    public static boolean isNotNull(Integer iint) {
        boolean bolret = true;
        if (iint == null) {
            bolret = false;
        }

        return bolret;
    }

    public static boolean isNotNull(Long iint) {
        boolean bolret = true;
        if (iint == null) {
            bolret = false;
        } else if (iint.intValue() == 0) {
            bolret = false;
        }

        return bolret;
    }

    public static boolean isNull(Integer iint) {
        boolean ret = false;
        if (iint == null) {
            ret = true;
        }

        return ret;
    }

    public static boolean isNull(Object obj) {
        boolean ret = false;
        if (obj == null) {
            ret = true;
        }

        return ret;
    }

    public static boolean isNull(String sstring) {
        boolean bolret = false;
        if (sstring == null || sstring.trim().equals("null") || sstring.trim().equals("\"null\"") || sstring.trim().equals("")) {
            bolret = true;
        }

        return bolret;
    }

    public static boolean isNull(List lst) {
        boolean bolret = false;
        if (lst == null) {
            bolret = true;
        }

        if (lst != null && lst.size() == 0) {
            bolret = true;
        }

        return bolret;
    }

    public static String isStrNotNull(String str) {
        return str != null && !"null".equals(str) && !"".equals(str.trim()) ? str.trim() : "";
    }

    public static boolean isNotNull(List lst) {
        boolean bolret = false;
        if (lst != null && lst.size() > 0) {
            bolret = true;
        }

        return bolret;
    }

    public static boolean isNull(int iint) {
        boolean bolret = false;
        if (iint > 0) {
            bolret = true;
        }

        return bolret;
    }

    public static String replaceNullWith(String sstring, String sdefault) {
        String sret = null;
        if (isNotNull(sstring)) {
            sret = sstring;
        } else {
            sret = sdefault;
        }

        return sret;
    }

    public static String replace(String content, String oldWord, String newWord) {
        String tempString = new String(content);

        for(int position = tempString.indexOf(oldWord); position > -1; position = tempString.indexOf(oldWord, position + newWord.length())) {
            tempString = tempString.substring(0, position) + newWord + tempString.substring(position + oldWord.length());
        }

        return tempString;
    }

    public static String escapeHtmlTag(String ls_content) {
        if (ls_content != null && ls_content.length() != 0) {
            int li_len = ls_content.length();
            String ls_newcon = "";
            String ls_token = "";
            int theTable = 0;

            for(int i = 0; i < li_len; ++i) {
                ls_token = "";
                char lc = ls_content.charAt(i);
                if (lc == '<') {
                    String ls_temp = ls_content.substring(i, i + 6);
                    if (ls_temp.equalsIgnoreCase("<TABLE")) {
                        ++theTable;
                    }

                    if (ls_temp.equalsIgnoreCase("</TABL")) {
                        --theTable;
                    }
                }

                if (theTable > 0) {
                    ls_newcon = ls_newcon + ls_content.charAt(i);
                } else {
                    if (lc == ' ') {
                        ls_token = ls_token + "&nbsp;";
                    } else if (lc == '\r') {
                        ls_token = ls_token + "<br>";
                    } else if (lc == '\t') {
                        ls_token = ls_token + "&nbsp;&nbsp;";
                    } else if (lc != '\n') {
                        ls_token = ls_token + lc;
                    }

                    ls_newcon = ls_newcon + ls_token;
                }
            }

            return ls_newcon;
        } else {
            return ls_content;
        }
    }

    public static synchronized String generateUID() {
        ++UIDCounter;
        return String.valueOf(System.currentTimeMillis()) + String.valueOf(UIDCounter);
    }

    /** @deprecated */
    public static String addZero(int id, int leng) {
        String sid = String.valueOf(id);
        if (sid.length() == leng) {
            return sid;
        } else {
            int pack = leng - sid.length();

            for(int i = 0; i < pack; ++i) {
                sid = "0" + sid;
            }

            return sid;
        }
    }

    public static ArrayList xmlEncode(String text) {
        if (text == null) {
            return null;
        } else {
            StringBuffer results = new StringBuffer();
            ArrayList list = new ArrayList();
            int len = text.length();

            for(int i = 0; i < len; ++i) {
                char c = text.charAt(i);
                switch (c) {
                    case '\n':
                        list.add(results.toString());
                        results.delete(0, results.capacity());
                        break;
                    case ' ':
                        results.append("&#9;");
                        break;
                    case '"':
                        results.append("&quot;");
                        break;
                    case '&':
                        results.append("&amp;");
                        break;
                    case '<':
                        results.append("&lt;");
                        break;
                    case '>':
                        results.append("&gt;");
                        break;
                    default:
                        results.append(String.valueOf(c));
                }
            }

            if (results == null) {
                return list;
            } else {
                list.add(results.toString());
                return list;
            }
        }
    }

    public static String formatTime(String timestamp) {
        return isNotNull(timestamp) && timestamp.length() > 19 ? timestamp.substring(0, timestamp.length() - 2) : timestamp;
    }

    public static String compress(String inputstr, String delimiter) {
        StringBuffer outstr = new StringBuffer();
        String[] arrtemp = inputstr.split(delimiter);
        int length = arrtemp.length;

        for(int i = 0; i < length; ++i) {
            if (arrtemp[i] != "") {
                outstr.append(arrtemp[i] + delimiter);

                for(int j = i + 1; j < length; ++j) {
                    if (arrtemp[i].equals(arrtemp[j])) {
                        arrtemp[j] = "";
                    }
                }
            }
        }

        if (!inputstr.endsWith(delimiter)) {
            return outstr.substring(0, outstr.length() - 1);
        } else {
            return outstr.toString();
        }
    }

    public static String encode(String pwd) {
        byte[] buf = pwd.getBytes();
        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);
            byte[] digest = algorithm.digest();

            for(int i = 0; i < digest.length; ++i) {
                hexString.append(pad(Integer.toHexString(255 & digest[i]), 2));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return hexString.toString();
    }

    public static String encodeWithKey(String userid, String pwd) {
        byte[] buf = pwd.getBytes();
        byte[] key = userid.getBytes();
        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(buf);
            byte[] digest = algorithm.digest(key);

            for(int i = 0; i < digest.length; ++i) {
                hexString.append(pad(Integer.toHexString(255 & digest[i]), 2));
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return hexString.toString();
    }

    public static String pad(String i, int l) {
        while(i.length() < l) {
            i = '0' + i;
        }

        return i;
    }

    public static int binstrToInt(String binstr) {
        String temp = binstr;
        if (binstr.indexOf(44) != -1) {
            temp = binstr.replaceAll(",", "");
        }

        return Integer.valueOf(temp, 2);
    }

    public static String intToBinstr(int i) {
        return pad(Integer.toBinaryString(i), 7);
    }

    public static String stringToCSS(String csv) {
        StringBuffer sboutstr = new StringBuffer();
        char[] tempca = csv.toCharArray();
        int length = tempca.length;
        if (length > 0) {
            sboutstr.append(tempca[0]);
        }

        for(int i = 1; i < length; ++i) {
            sboutstr.append("," + tempca[i]);
        }

        return sboutstr.toString();
    }

    public static boolean compareObj(Object fromObj, Object toObj) {
        boolean result = false;
        if (fromObj == null) {
            if (toObj == null) {
                result = true;
            }
        } else if (toObj != null) {
            result = fromObj.equals(toObj);
        }

        return result;
    }

    public static String formatHtml(String in) {
        if (in == null) {
            return null;
        } else {
            char[] c = new char[]{'\r', '\n'};
            String strOut = "";
            String brstr = new String(c);
            String[] strs = in.split(brstr);

            for(int i = 0; i < strs.length; ++i) {
                strOut = strOut + strs[i];
                if (i < strs.length - 1) {
                    strOut = strOut + "<br>";
                }
            }

            strOut = strOut.replaceAll("\\s+$", "");
            strOut = strOut.replaceAll("\\s", "&nbsp;");
            return strOut;
        }
    }

    public static boolean createDir(String dir) {
        boolean isSuccess = false;
        File dirFile = new File(dir);

        try {
            if (!dirFile.exists()) {
                isSuccess = dirFile.mkdirs();
            } else {
                isSuccess = true;
            }
        } catch (Exception var4) {
            System.out.println("create dir is failure!!!");
        }

        return isSuccess;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }

        return dest;
    }
}
