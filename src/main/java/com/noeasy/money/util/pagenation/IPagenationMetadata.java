/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.pagenation;

/**
 *
 * @author acer
 */
public interface IPagenationMetadata {

    String DEFAULT_FIRST_PAGE = "First";
    String DEFAULT_LAST_PAGE = "Last";
    String DEFAULT_PREVIOUS_PAGE = "Prev";
    String DEFAULT_NEXT_PAGE = "Next";
    String DEFAULT_SUSPENSION_POINT = "...";
    
    String getFirstPage();
    String getLastPage();
    String getPreviousPage();
    String getNextPage();
    String getSuspensionPoint();
}
