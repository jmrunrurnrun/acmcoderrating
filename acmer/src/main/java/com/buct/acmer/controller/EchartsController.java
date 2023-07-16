package com.buct.acmer.controller;

import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.mapper.CodeforcesMapper;
import com.buct.acmer.service.impl.CodeforcesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "Echarts")
@RestController
@RequestMapping("/acmer/echarts")
public class EchartsController {
    @Resource
    private CodeforcesMapper cfMapper;

    public EchartsController() {
    }

    @Autowired
    public EchartsController(CodeforcesMapper cfMapper) {
        this.cfMapper = cfMapper;
    }
    @ApiOperation("学生分布")
    @GetMapping("/numbers")
    public PublicProperty<List<Integer>> rank(){
        List<Integer> rlist = new ArrayList<>();
        rlist.add( cfMapper.count(1300,1400) );
        rlist.add( cfMapper.count(1400,1500) );
        rlist.add( cfMapper.count(1500,1600) );
        rlist.add( cfMapper.count(1600,1700) );
        rlist.add( cfMapper.count(1700,1800) );
        rlist.add( cfMapper.count(1800,1900) );
        rlist.add( cfMapper.count(1900,2000) );
        return new PublicProperty(200,"success",rlist);
    }
}

