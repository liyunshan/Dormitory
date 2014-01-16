/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.noeasy.money.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.noeasy.money.repository.IBaseRepository;
import com.noeasy.money.service.IBaseService;

/**
 *
 * @author acer
 */
public abstract class BaseService<T, ID extends Serializable> implements IBaseService<T, ID> {

    protected static Log logger;
    private final Class<T> persistentClass;

    public BaseService() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        logger = LogFactory.getLog(this.persistentClass);
    }

    public Class<T> getEntityClass() {
        return this.persistentClass;
    }
    

    public ID save(T entity) {
        return (ID) this.getRepository().save(entity);
    }

    public T update(T entity) {
        return (T) this.getRepository().update(entity);
    }

    public void delete(T entity) {
        this.getRepository().delete(entity);;
    }

    public void deleteByID(ID id) {
        this.getRepository().deleteByID(id);
    }


    public abstract IBaseRepository getRepository();
}
