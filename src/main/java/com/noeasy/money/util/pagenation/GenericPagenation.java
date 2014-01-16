/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.util.pagenation;

/**
 *
 * @author acer
 */
public class GenericPagenation implements IPagenation {

    private int totalRows;
    private int pageSize;
    private int totalPage;
    private int firstPage;
    private int lastPage;
    private int currentPage;
    private int[] pageArray;

    public GenericPagenation(int pageSize) {
        this.setPageSize(pageSize);
    }
    
    public int getTotalRows() {
        return this.totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        if (this.totalRows % this.pageSize == 0) {
            this.totalPage = this.totalRows / this.pageSize;
        } else {
            this.totalPage = this.totalRows / this.pageSize + 1;
        }
        this.firstPage = 0;
        if (this.totalPage > 1) {
            this.lastPage = this.totalPage - 1;
        } else {
            this.lastPage = this.totalPage;
        }
        this.currentPage = this.firstPage;
        this.pageArray = new int[this.totalPage];
        for (int count = 0; count < this.totalPage; count++) {
            this.pageArray[count] = count;
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page Size must greater than 0."); 
        }
        this.pageSize = pageSize;

    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getFirstPage() {
        return this.firstPage;
    }

    public int getLastPage() {
        return this.lastPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > this.lastPage || currentPage < this.firstPage) {
            this.currentPage = 0;
        } else {
            this.currentPage = currentPage;
        }
    }

    public int nextPage() {
        if (this.currentPage < this.lastPage) {
            this.currentPage++;
        } else {
            this.currentPage = this.lastPage;
        }
        return this.currentPage;
    }

    public int prePage() {
        if (this.currentPage > this.firstPage) {
            this.currentPage--;
        } else {
            this.currentPage = this.firstPage;
        }
        return this.currentPage;
    }

    public int[] getPageArray() {
        return this.pageArray;
    }

}
