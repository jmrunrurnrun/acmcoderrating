package com.buct.acmer.mapper;

import com.buct.acmer.entity.Codeforces;
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
 * @since 2022-06-16
 */
@Mapper
public interface CodeforcesMapper extends BaseMapper<Codeforces> {
    //自定义统计方法
    //List<Codeforces> getCodesByRatingRange(int minRating, int maxRating);
    // 更多自定义方法...
    @Select("SELECT COUNT(CASE WHEN cf_new_rating > #{begin} AND cf_new_rating < #{end} THEN 1 END) FROM codeforces")
    int count(@Param("begin") int begin, @Param("end") int end);

    @Select("SELECT * FROM codeforces ORDER BY cf_rank ASC")
    List<Codeforces> asc();

    @Select("SELECT * FROM codeforces ORDER BY cf_rank DESC")
    List<Codeforces> desc();
}
