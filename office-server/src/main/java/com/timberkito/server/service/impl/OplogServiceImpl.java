package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.OplogMapper;
import com.timberkito.server.pojo.Oplog;
import com.timberkito.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
