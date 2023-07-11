package com.buct.acmer.service.impl;

import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.mapper.AtcoderMapper;
import com.buct.acmer.service.IAtcoderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Transactional
@Service
public class AtcoderServiceImpl extends ServiceImpl<AtcoderMapper, Atcoder> implements IAtcoderService {

}
