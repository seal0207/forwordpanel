package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.dto.PortAddDTO;
import com.leeroy.forwordpanel.forwordpanel.model.Port;
import com.leeroy.forwordpanel.forwordpanel.model.SysConfig;
import com.leeroy.forwordpanel.forwordpanel.service.PortService;
import com.leeroy.forwordpanel.forwordpanel.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("sysConfig")
@Controller
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;


    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResponse saveUserPort(@RequestBody SysConfig sysConfig) {
        if (WebCurrentData.getUser().getUserType() > 0) {
            return ApiResponse.error("403", "您没有权限执行此操作");
        }
        return sysConfigService.save(sysConfig);
    }

    @ResponseBody
    @GetMapping("getSysConfig")
    public ApiResponse delete() {
        return sysConfigService.getConfig();
    }


}
