package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.annotation.NoLogin;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.model.Clash;
import com.leeroy.forwordpanel.forwordpanel.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RequestMapping("config")
@Controller
public class ConfigController {

    @Autowired
    private ConfigService configService;


    @ResponseBody
    @PostMapping("getPage")
    public ApiResponse getPage(@RequestBody PageRequest pageRequest) {
        return ApiResponse.ok(configService.findClashList(pageRequest));
    }

    @ResponseBody
    @PostMapping("save")
    public ApiResponse save(@RequestBody Clash clash) {
        return configService.save(clash);
    }

    @ResponseBody
    @GetMapping("delete")
    public ApiResponse delete(String id) {
        configService.delClash(id);
        return ApiResponse.ok();
    }


    @NoLogin
    @ResponseBody
    @GetMapping("/{id}")
    public void getClashFileById(@PathVariable String id, HttpServletResponse response) {
        Clash clash = configService.findClashById(id);
        try {
            String text = clash.getText();
            byte[] bytes = text.getBytes("UTF-8");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + id + ".yml\"");
            response.addHeader("Content-Length", "" + bytes.length);
            OutputStream stream = null;
            stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
