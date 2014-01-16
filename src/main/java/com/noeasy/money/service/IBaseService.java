/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.service;

import java.io.Serializable;
import java.util.List;

import com.noeasy.money.repository.IBaseRepository;

/**
 *
 * @author acer
 */
public interface IBaseService<T, ID extends Serializable> {

    Class<T> getEntityClass();

    List<T> findByIDs(List<ID> ids);

    T findByID(ID id);


    ID save(T entity);

    T update(T entity);

    void delete(T entity);

    void deleteByID(ID id);


    IBaseRepository getRepository();

}
