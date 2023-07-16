package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.Codeforces;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.mapper.CodeforcesMapper;
import com.buct.acmer.service.impl.CodeforcesServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BUCT
 * @since 2022-06-16
 */
@Api(tags = "Codeforces")
@RestController
@RequestMapping("/acmer/codeforces")
public class CodeforcesController {

    @Resource
    private CodeforcesServiceImpl codeforcesService;

    @ApiOperation("查询全部学生Codeforces信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页数",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",required = true)
    })
    @GetMapping("/all/{currentPage}/{pageSize}")
    public PublicProperty<Page<Codeforces>> selectAll(@PathVariable("currentPage") Integer currentPage,
                                                      @PathVariable("pageSize") Integer pageSize){

        Page<Codeforces> page = new Page<>(currentPage,pageSize);
        return new PublicProperty(200,"success",codeforcesService.page(page));
    }
    // 更多请求处理方法...
    @ApiOperation("按ID查询学生Codeforces信息")
    @GetMapping("/all/{cfId}")
    public PublicProperty<Codeforces> selectname(@PathVariable("cfId") String cfId) {
        Codeforces result = codeforcesService.getById(cfId);
        if (result!=null) {
            return new PublicProperty<>(200, "success", result);
        } else {
            return new PublicProperty<>(400, "user does not exist", null);
        }
    }

    private CodeforcesMapper cfMapper;
    @Autowired
    public CodeforcesController (CodeforcesMapper cfMapper) {
        this.cfMapper = cfMapper;
    }

    @ApiOperation("升序排序")
    @GetMapping("/rank/asc")
    public PublicProperty<List<Codeforces>> asc(){
        List<Codeforces> rlist = new ArrayList<>();
        rlist = cfMapper.asc();
        return new PublicProperty(200,"success",rlist);
    }

    @ApiOperation("降序排序")
    @GetMapping("/rank/desc")
    public PublicProperty<List<Codeforces>> desc(){
        List<Codeforces> rlist = new ArrayList<>();
        rlist = cfMapper.desc();
        return new PublicProperty(200,"success",rlist);
    }

}
