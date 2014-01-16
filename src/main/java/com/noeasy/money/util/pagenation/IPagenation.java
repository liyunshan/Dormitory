/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.pagenation;

/**
 *
 * @author acer
 */
public interface IPagenation {

    int getTotalRows();

    void setTotalRows(int totalRows);

    int getPageSize();

    int getTotalPage();

    int getFirstPage();

    int getLastPage();

    int getCurrentPage();

    void setCurrentPage(int currentPage);

    int nextPage();

    int prePage();
    
    int[] getPageArray();
}
