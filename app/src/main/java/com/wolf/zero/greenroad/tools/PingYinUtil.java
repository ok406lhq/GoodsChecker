package com.wolf.zero.greenroad.tools;

import android.os.Build;

import com.wolf.zero.greenroad.bean.SerializableGoods;
import com.wolf.zero.greenroad.bean.SerializableNumber;
import com.wolf.zero.greenroad.bean.SerializableStation;
import com.wolf.zero.greenroad.smartsearch.CharacterParser;
import com.wolf.zero.greenroad.smartsearch.SortToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/7/12.
 */

public class PingYinUtil {

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private static PingYinUtil sPingYinUtil;

    public PingYinUtil() {
        if (characterParser == null) {
            characterParser = CharacterParser.getInstance();
        }
    }


    public static PingYinUtil getInstance() {
        if (sPingYinUtil == null) {
            sPingYinUtil = new PingYinUtil();
        }
        return sPingYinUtil;
    }

    /**
     * 名字转拼音,取首字母
     *
     * @param name
     * @return
     */
    public String getSortLetter(String name) {
        String letter = "#";
        if (name == null) {
            return letter;
        }
        //汉字转换成拼音
        String pinyin = characterParser.getSelling(name);
        String sortString = pinyin.substring(0, 1).toUpperCase(Locale.CHINESE);

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        }
        return letter;
    }

    /**
     * 取sort_key的首字母
     *
     * @param sortKey
     * @return
     */
    public String getSortLetterBySortKey(String sortKey) {
        if (sortKey == null || "".equals(sortKey.trim())) {
            return null;
        }
        String letter = "#";
        //汉字转换成拼音
        String sortString = sortKey.trim().substring(0, 1).toUpperCase(Locale.CHINESE);
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {// 5.0以上需要判断汉字
            if (sortString.matches("^[\u4E00-\u9FFF]+$"))// 正则表达式，判断是否为汉字
                letter = getSortLetter(sortString.toUpperCase(Locale.CHINESE));
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (sortString.matches("^[\u4E00-\u9FFF]+$"))// 正则表达式，判断是否为汉字
                letter = getSortLetter(sortString.toUpperCase(Locale.CHINESE));
        }
        return letter;
    }

    /**
     * 模糊查询goods
     *
     * @param
     * @param str
     * @return
     */
    public List<SerializableGoods> search_goods(List<SerializableGoods> mAllContactsList, String str) {
        List<SerializableGoods> filterList = new ArrayList<SerializableGoods>();// 过滤后的list
        //if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            // String simpleStr = str.replaceAll("\\-|\\s", "");
            for (SerializableGoods contact : mAllContactsList) {
                if (contact.getAlias() != null && contact.getScientific_name() != null) {
                    if (contact.getScientific_name().contains(str) || contact.getAlias().contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        } else {
            for (SerializableGoods contact : mAllContactsList) {
              //  if (contact.getAlias() != null && contact.getScientific_name() != null) {
                if (contact.getScientific_name() != null) {
                    //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    boolean isNameContains = contact.getScientific_name().toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

               /*     boolean isAliasContains = contact.getAlias().toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));
*/
                    boolean isSimpleSpellContains = contact.simpleSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isWholeSpellContains = contact.wholeSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    if (isNameContains || isSimpleSpellContains || isWholeSpellContains) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }
    /**
     * 模糊查询numbers
     *
     * @param
     * @param str
     * @return
     */
    public List<SerializableNumber> search_numbers(List<SerializableNumber> mAllContactsList, String str) {
        List<SerializableNumber> filterList = new ArrayList<SerializableNumber>();// 过滤后的list
        //if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            // String simpleStr = str.replaceAll("\\-|\\s", "");
            for (SerializableNumber contact : mAllContactsList) {
                if (contact.getName() != null ) {
                    if (contact.getName().contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        } else {
            for (SerializableNumber contact : mAllContactsList) {
                if ( contact.getName() != null) {
                    //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    boolean isNameContains = contact.getName().toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isSimpleSpellContains = contact.simpleSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isWholeSpellContains = contact.wholeSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    if (isNameContains || isSimpleSpellContains || isWholeSpellContains) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }/**
     * 模糊查询station
     *
     * @param
     * @param str
     * @return
     */
    public List<SerializableStation> search_station(List<SerializableStation> mAllContactsList, String str) {
        List<SerializableStation> filterList = new ArrayList<SerializableStation>();// 过滤后的list
        //if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            // String simpleStr = str.replaceAll("\\-|\\s", "");
            for (SerializableStation contact : mAllContactsList) {
                if (contact.getStationName() != null ) {
                    if (contact.getStationName().contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        } else {
            for (SerializableStation contact : mAllContactsList) {
                if ( contact.getStationName() != null) {
                    //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    boolean isNameContains = contact.getStationName().toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isSimpleSpellContains = contact.simpleSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isWholeSpellContains = contact.wholeSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    if (isNameContains || isSimpleSpellContains || isWholeSpellContains) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }

    /**
     * 中文字符串匹配
     */
    String chReg = "[\\u4E00-\\u9FA5]+";

    /**
     * 解析sort_key,封装简拼,全拼。
     * Android 5.0 以上使用
     *
     * @param sortKey
     * @return
     */
    public String parseSortKeySimpleSpell(String sortKey) {

        if (sortKey != null && sortKey.length() > 0) {
            boolean isChinese = sortKey.matches(chReg);
            // 分割条件：中文不分割，英文以大写和空格分割
            String regularExpression = isChinese ? "" : "(?=[A-Z])|\\s";

            String[] enStrs = sortKey.split(regularExpression);
            String simpleSpell = "";

            for (int i = 0, length = enStrs.length; i < length; i++) {

                if (enStrs[i].length() > 0) {
                    //拼接简拼
                    simpleSpell += getSortLetter(String.valueOf(enStrs[i].charAt(0)));
                }
            }
            return simpleSpell;
        }
        return null;
    }

    public String parseSortKeyWholeSpell(String sortKey) {
        SortToken token = new SortToken();
        if (sortKey != null && sortKey.length() > 0) {
            boolean isChinese = sortKey.matches(chReg);
            // 分割条件：中文不分割，英文以大写和空格分割
            String regularExpression = isChinese ? "" : "(?=[A-Z])|\\s";

            String[] enStrs = sortKey.split(regularExpression);

            String wholeSpell = "";

            for (int i = 0, length = enStrs.length; i < length; i++) {
                if (enStrs[i].length() > 0) {
                    //拼接简拼
                    wholeSpell += characterParser.getSelling(enStrs[i]);
                }
            }
            return wholeSpell;
        }
        return null;
    }


    /**
     * 过滤掉特殊符号
     *
     * @param s
     * @return
     */
    public static String format(String s) {
        String str = s.replaceAll("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        return str;
    }

}
