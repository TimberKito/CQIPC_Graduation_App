package com.timberkito.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timberkito.server.mapper.MailLogMapper;
import com.timberkito.server.pojo.MailLog;
import com.timberkito.server.service.IMailLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Timber.Wang
 * @since 2021-12-10
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService{

}
