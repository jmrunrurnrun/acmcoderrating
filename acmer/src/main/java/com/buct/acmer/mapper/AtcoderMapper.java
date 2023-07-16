package com.buct.acmer.mapper;

import com.buct.acmer.entity.Atcoder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Mapper
public interface AtcoderMapper extends BaseMapper<Atcoder> {
    @Select("SELECT * FROM atcoder ORDER BY ac_rank ASC")
    List<Atcoder> asc();

    @Select("SELECT * FROM atcoder ORDER BY ac_rank DESC")
    List<Atcoder> desc();
}
