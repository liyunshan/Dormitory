/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.repository;

import java.io.Serializable;

/**
 *
 * @author acer
 */
public interface IBaseRepository<T, ID extends Serializable> {

    Class<T> getEntityClass();

    T findByID(ID id);

    ID save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteByID(ID id);
}
