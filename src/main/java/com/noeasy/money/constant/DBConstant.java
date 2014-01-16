/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.constant;

/**
 *
 * @author acer
 */
public interface DBConstant {
    enum CategorySKU {
        PRODUCT("PRO"), MACHINE_TOOL("MTL");
        private String sku;
        CategorySKU(String sku) {
            this.sku = sku;
        }
        
        public String getSKU() {
            return this.sku;
        }
    }
}
