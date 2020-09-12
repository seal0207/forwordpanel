package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.model.Port;
import com.leeroy.forwordpanel.forwordpanel.model.Server;
import com.leeroy.forwordpanel.forwordpanel.service.PortService;
import com.leeroy.forwordpanel.forwordpanel.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("server")
@Controller
public class ServerController {

    @Autowired
    private ServerService serverService;

    /**
     * 页面
     *
     * @return
     */
    @RequestMapping("/manage")
    public String portManage() {
        return "server/index";
    }


    @ResponseBody
    @PostMapping("getPage")
    public ApiResponse getPage(@RequestBody PageRequest pageRequest) {
        return ApiResponse.ok(serverService.getServerPage(pageRequest));
    }


    @ResponseBody
    @GetMapping("getList")
    public ApiResponse getList() {
        return ApiResponse.ok(serverService.findList());
    }

    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ApiResponse saveUserPort(@RequestBody Server server) {
        return serverService.save(server);
    }

    @ResponseBody
    @GetMapping("delete")
    public ApiResponse delete(Integer id) {
        serverService.delete(id);
        return ApiResponse.ok();
    }


}
