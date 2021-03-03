package com.lyf.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyf.base.entity.BaseEntity;
import com.lyf.exception.CustomizedException;

/**
 * Created by liyf on 2020-03-03
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {
    public T getById(Long id) throws CustomizedException;

}
