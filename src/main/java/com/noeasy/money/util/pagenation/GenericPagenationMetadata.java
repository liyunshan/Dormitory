/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.pagenation;

/**
 *
 * @author acer
 */
public class GenericPagenationMetadata implements IPagenationMetadata {
    public static final GenericPagenationMetadata metadata = new GenericPagenationMetadata();
    
    private GenericPagenationMetadata() {
        
    }
    
    public static GenericPagenationMetadata singleInstance() {
        return metadata;
    }

    public String getFirstPage() {
        return IPagenationMetadata.DEFAULT_FIRST_PAGE;
    }

    public String getLastPage() {
        return IPagenationMetadata.DEFAULT_LAST_PAGE;
    }

    public String getPreviousPage() {
        return IPagenationMetadata.DEFAULT_PREVIOUS_PAGE;
    }

    public String getNextPage() {
        return IPagenationMetadata.DEFAULT_NEXT_PAGE;
    }

    public String getSuspensionPoint() {
        return IPagenationMetadata.DEFAULT_SUSPENSION_POINT;
    }
}
