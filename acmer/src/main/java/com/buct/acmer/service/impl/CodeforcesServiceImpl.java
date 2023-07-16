package com.buct.acmer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.mapper.CodeforcesMapper;
import com.buct.acmer.service.ICodeforcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Service
public class CodeforcesServiceImpl extends ServiceImpl<CodeforcesMapper, Codeforces> implements ICodeforcesService {
//    @Autowired
//    private CodeforcesMapper codeforcesMapper;

//    @Override
//    public List<Codeforces> getCodesByRatingRange(int minRating, int maxRating) {
//        return codeforcesMapper.getCodesByRatingRange(minRating, maxRating);
//    }
//
//    @Override
//    public Map<String, Integer> calculateRatingSumByContest(String cfContest) {
//        // 实现统计逻辑
//        List<Codeforces> codeforcesList = codeforcesMapper.selectList(new QueryWrapper<Codeforces>().eq("cf_contest", cfContest));
//        Map<String, Integer> ratingSumByContest = new HashMap<>();
//        for (Codeforces codeforces : codeforcesList) {
//            String cfId = codeforces.getCfId();
//            int cfNewRating = Integer.parseInt(codeforces.getCfNewRating());
//            ratingSumByContest.put(cfId, ratingSumByContest.getOrDefault(cfId, 0) + cfNewRating);
//        }
//        return ratingSumByContest;
//    }

    // 更多方法的实现...
}
