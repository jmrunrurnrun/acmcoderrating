package com.buct.acmer.mapper;

import com.buct.acmer.entity.Codeforces;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Mapper
public interface CodeforcesMapper extends BaseMapper<Codeforces> {
    //自定义统计方法
    List<Codeforces> getCodesByRatingRange(int minRating, int maxRating);
    // 更多自定义方法...
}
