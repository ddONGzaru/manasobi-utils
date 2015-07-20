package io.manasobi.utils;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.AbstractFileFilter;

/**
 * DESC : 
 * 
 * @Company ePapyrus, Inc.
 * @author taewook.jang
 * @Date 2013. 9. 9. 오전 10:11:17
 */
public class WildcardExcludeFileFilter extends AbstractFileFilter implements Serializable {

	private static final long serialVersionUID = 8206674584073808869L;

	private final String[] wildcards;

    private final IOCase caseSensitivity;
    
    public WildcardExcludeFileFilter(String wildcard) {
        this(wildcard, null);
    }
    
    public WildcardExcludeFileFilter(String wildcard, IOCase caseSensitivity) {
        if (wildcard == null) {
            throw new IllegalArgumentException("The wildcard must not be null");
        }
        this.wildcards = new String[] { wildcard };
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }
    
    public WildcardExcludeFileFilter(String[] wildcards) {
        this(wildcards, null);
    }

    public WildcardExcludeFileFilter(String[] wildcards, IOCase caseSensitivity) {
        if (wildcards == null) {
            throw new IllegalArgumentException("The wildcard array must not be null");
        }
        this.wildcards = new String[wildcards.length];
        System.arraycopy(wildcards, 0, this.wildcards, 0, wildcards.length);
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    public WildcardExcludeFileFilter(List<String> wildcards) {
        this(wildcards, null);
    }

    public WildcardExcludeFileFilter(List<String> wildcards, IOCase caseSensitivity) {
        if (wildcards == null) {
            throw new IllegalArgumentException("The wildcard list must not be null");
        }
        this.wildcards = wildcards.toArray(new String[wildcards.size()]);
        this.caseSensitivity = caseSensitivity == null ? IOCase.SENSITIVE : caseSensitivity;
    }

    @Override
    public boolean accept(File dir, String name) {
        for (String wildcard : wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcard, caseSensitivity)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        for (String wildcard : wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcard, caseSensitivity)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (wildcards != null) {
            for (int i = 0; i < wildcards.length; i++) {
                if (i > 0) {
                    buffer.append(",");
                }
                buffer.append(wildcards[i]);
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

}