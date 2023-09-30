package com.qk.chat.server.controller;


import com.qk.chat.common.result.CommonResult;
import com.qk.chat.server.domain.param.NewQunParam;
import com.qk.chat.server.service.QunInfoService;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * qun 前端控制器
 * </p>
 *
 * @author young
 * @since 2023年09月30日
 */
@RestController
@RequestMapping("server/qun")
@Validated
public class QunInfoController {
    
    @Autowired
    QunInfoService qunInfoService;
    
    @ApiOperation("新建群")
    @RequestMapping(value = "/new-qun",method = RequestMethod.POST)
    public CommonResult<Boolean> newQunInfo(@Valid @RequestBody NewQunParam newQunParam){
        
        return null;
    }
}

