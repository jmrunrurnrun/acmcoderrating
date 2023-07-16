package com.buct.acmer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.buct.acmer.entity.Atcoder;
import com.buct.acmer.entity.ContestInfo;
import com.buct.acmer.entity.PublicProperty;
import com.buct.acmer.mapper.AtcoderMapper;
import com.buct.acmer.mapper.CodeforcesMapper;
import com.buct.acmer.service.impl.AtcoderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author BUCT
 * @since 2022-06-14
 */
@Api(tags = "AtCoder")
@RestController
@RequestMapping("/acmer/atcoder")
public class AtcoderController {

    @Resource
    private AtcoderServiceImpl atcoderService;

    @ApiOperation("查询全部学生AtCoder信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页数",required = true),
            @ApiImplicitParam(name = "pageSize",value = "页面大小",required = true)
    })
    @GetMapping("/all/{currentPage}/{pageSize}")
    public PublicProperty<Page<Atcoder>> selectAll(@PathVariable("currentPage") Integer currentPage,
                                                   @PathVariable("pageSize") Integer pageSize){

        Page<Atcoder> page = new Page<>(currentPage,pageSize);
        return new PublicProperty(200,"success",atcoderService.page(page));
    }


    //    @Transactional
    @ApiOperation("按ID查询学生AtCoder信息")
    //@ApiImplicitParam(name = "acId",value = "ac账号id",required = true)
    @GetMapping("/all/{acId}")
    public PublicProperty<Atcoder> selectname(@PathVariable("acId") String acId) {
        System.out.println("要开始啦");
        Atcoder result = atcoderService.getById(acId);
        if (result!=null) {
            System.out.println(result);
            return new PublicProperty<>(200, "success", result);
        } else {
            System.out.println("else");
            return new PublicProperty<>(400, "user does not exist", null);
        }
    }

    private AtcoderMapper atMapper;
    @Autowired
    public AtcoderController(AtcoderMapper atMapper) {
        this.atMapper = atMapper;
    }
    @ApiOperation("升序排序")
    @GetMapping("/rank/asc")
    public PublicProperty<List<Atcoder>> asc(){
        List<Atcoder> rlist = new ArrayList<>();
        rlist = atMapper.asc();
        return new PublicProperty(200,"success",rlist);
    }

    @ApiOperation("降序排序")
    @GetMapping("/rank/desc")
    public PublicProperty<List<Atcoder>> desc(){
        List<Atcoder> rlist = new ArrayList<>();
        rlist = atMapper.desc();
        return new PublicProperty(200,"success",rlist);
    }

}
