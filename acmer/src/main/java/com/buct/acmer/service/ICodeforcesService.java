package com.buct.acmer.service;

import com.buct.acmer.entity.Codeforces;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
public interface ICodeforcesService extends IService<Codeforces> {
    List<Codeforces> getCodesByRatingRange(int minRating, int maxRating);
    Map<String, Integer> calculateRatingSumByContest(String cfContest);
    // 更多自定义方法...
}
