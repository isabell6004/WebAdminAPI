package net.fashiongo.webadmin.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.fashiongo.webadmin.exception.HtmlException;
import net.fashiongo.webadmin.model.pojo.utility.HtmlEntity;



public class HtmlUtility {
    private static List<HtmlEntity> entities;
    private static Map<Character, String> encodeEntities;
    
    static {
        entities = new ArrayList<>();
        entities.add(new HtmlEntity(';', "&semi;"));
        entities.add(new HtmlEntity('<', "&lt;"));
        entities.add(new HtmlEntity('>', "&gt;"));
        entities.add(new HtmlEntity('(', "&lpar;"));
        entities.add(new HtmlEntity(')', "&rpar;"));
        entities.add(new HtmlEntity('*', "&ast;"));
        entities.add(new HtmlEntity('#', "&num;"));
        entities.add(new HtmlEntity('&', "&amp;"));
        entities.add(new HtmlEntity('/', "&sol;"));
        entities.add(new HtmlEntity('\\', "&bsol;"));
        entities.add(new HtmlEntity('\'', "&apos;"));
        entities.add(new HtmlEntity('"', "&quot;"));
        entities.add(new HtmlEntity('=', "&equals;"));
        entities.add(new HtmlEntity('+', "&plus;"));
        entities.add(new HtmlEntity('-', "&minus;"));
        entities.add(new HtmlEntity('@', "&commat;"));
        entities.add(new HtmlEntity('|', "&vert;"));
        entities.add(new HtmlEntity('[', "&lbrack;"));
        entities.add(new HtmlEntity(']', "&rbrack;"));
        entities.add(new HtmlEntity(']', "&rbrack;"));

        encodeEntities = new HashMap<>();
        for (HtmlEntity entity : entities) encodeEntities.put(entity.getLeft(), entity.getRight());
    }

    public static String escape(String s) {
        if (StringUtils.isEmpty(s)) return s;
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; ) {
            boolean isMatched = false;
            for (HtmlEntity entity : entities) {
                String right = entity.getRight();
                if (((i + right.length()) <= length)) {
                    String s2 = s.substring(i, i + right.length());
                    if (s2.equals(right)) {
                        sb.append(right);
                        i += right.length();
                        isMatched = true;
                        break;
                    }
                }
                char left = entity.getLeft();
                if (s.charAt(i) == left) {
                    sb.append(right);
                    i++;
                    isMatched = true;
                    break;
                }
            }
            if (!isMatched) {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    public static void containsEscapeThrowRewardException(String s) {
        if (StringUtils.isEmpty(s)) return;
        for (char c : s.toCharArray()) {
            if (encodeEntities.containsKey(c)) {
                throw new HtmlException("Input value " + s + " contains " + c + " which is invalid.");
            }
        }
    }
    
    public static String tagValidation(String data, Boolean whitelist) {
    	Pattern tagPattern = Pattern.compile("<(?!!)(?!/)\\s*([a-zA-Z0-9]+)(.*?)>");
    	Matcher matcher = tagPattern.matcher(data);
    	while (matcher.find()) {
    	    String tagName = matcher.group(1);
    	    String attributes = matcher.group(2);
    	    
    	    String tagValidator = (whitelist) ? "/\\bjavascript|\\bobject|\\bbody|\\biframe|\\bscript|\\bembed|\\baudio|\\bstyle" : "/\\bjavascript|\\bobject|\\bbody|\\biframe|\\bscript|\\bimg|\\bdiv|\\bembed|\\baudio|\\bvideo|\\btable|\\bstyle";
    	    Pattern invalidTagPattern = Pattern.compile(tagValidator, Pattern.CASE_INSENSITIVE);
    	    Matcher tagMatcher = invalidTagPattern.matcher(tagName);
    	    if(tagMatcher.matches()) {
    	    	throw new HtmlException(tagName + " tag is not allowed.");
    	    }
    	    
    	    Pattern attributePattern = Pattern.compile("(\\S+)=['\"]{1}([^>]*?)['\"]{1}", Pattern.CASE_INSENSITIVE);
    	    Matcher attributeMatcher = attributePattern.matcher(attributes);
    	    while(attributeMatcher.find()) {
    	        String attributeName = attributeMatcher.group(1);
    	        String attributeValue = attributeMatcher.group(2);
    	        
    	        Pattern invalidAttributePattern = Pattern.compile("/\\bstyle|\\blowsrc|\\bdynsrc|\\bdata|\\bvalue|\\bbackground|\\bonerror|\\bonload|\\bonclick|\\bon[a-zA-Z]*", Pattern.CASE_INSENSITIVE);
    	        Matcher attributeNameMatcher = invalidAttributePattern.matcher(attributeName);
    	        if(attributeNameMatcher.matches()) {
    	        	throw new HtmlException(attributeName + " attribute is not allowed.");
    	        }
    	    }
    	}
    	return data;
    }
}
