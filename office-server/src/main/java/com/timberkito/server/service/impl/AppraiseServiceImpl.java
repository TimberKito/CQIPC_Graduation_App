package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.AppraiseMapper;
import com.timberkito.server.pojo.Appraise;
import com.timberkito.server.service.IAppraiseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class AppraiseServiceImpl extends ServiceImpl<AppraiseMapper, Appraise>
        implements IAppraiseService {

}
