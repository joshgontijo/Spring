/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jta.embedded.dao;

import com.jta.embedded.entity.MyEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Josue
 */
@Stateless
public class MyEntityFacade extends AbstractFacade<MyEntity> {
    @PersistenceContext(unitName = "TEST-MYSQL-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MyEntityFacade() {
        super(MyEntity.class);
    }
    
}
