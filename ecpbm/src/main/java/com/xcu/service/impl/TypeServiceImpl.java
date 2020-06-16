package com.xcu.service.impl;

import com.xcu.dao.TypeDao;
import com.xcu.pojo.Type;
import com.xcu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;

    public List<Type> getAll() {
        return typeDao.selectAll();
    }

    public int addType(Type type) {
        return typeDao.add(type);
    }

    public void updateType(Type type) {
        typeDao.update(type);
    }
}
