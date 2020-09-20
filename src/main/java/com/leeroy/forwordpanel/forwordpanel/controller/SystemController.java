package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.service.DataUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: forwordpanel
 * @description: 系统设置
 * @author: liruifeng
 * @created: 2020/09/20 13:38
 */
@RequestMapping("system")
@RestController
public class SystemController {

    @Autowired
    private DataUpgradeService upgradeService;

    /**
     * 导出数据库
     */
    @GetMapping("exportData")
    public ApiResponse exportData(){
        return upgradeService.exportData();
    }

    /**
     * 导入数据库
     * @param file
     * @return
     */
    @PostMapping("importData")
    public ApiResponse exportDB(MultipartFile file){
        return upgradeService.importData(file);
    }

}
