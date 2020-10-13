package com.leeroy.forwordpanel.forwordpanel.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.dao.ClashDao;
import com.leeroy.forwordpanel.forwordpanel.dto.PageRequest;
import com.leeroy.forwordpanel.forwordpanel.model.Clash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ConfigService {

    @Autowired
    private ClashDao clashDao;


    /**
     * 保存clash
     */
    public ApiResponse save(Clash clash) {
        clash.setUserId(WebCurrentData.getUserId());
        if (StringUtils.isEmpty(clash.getId())) {
            clash.setDeleted(false);
            clash.setDisabled(false);
            clash.setCreateTime(new Date());
            clashDao.insert(clash);
        } else {
            Clash existClash = clashDao.selectById(clash.getId());
            BeanUtils.copyProperties(clash, existClash);
            existClash.setUpdateTime(new Date());
            clashDao.updateById(existClash);
        }
        return ApiResponse.ok();
    }

    public ApiResponse getNodeList(String id){
        Clash clash = clashDao.selectById(id);
        Yaml yaml = new Yaml();
        JSONObject jsonObject = yaml.loadAs(clash.getText(), JSONObject.class);
        JSONArray proxies = jsonObject.getJSONArray("proxies");
        return ApiResponse.ok(proxies);
    }

    /**
     * 保存节点列表
     * @param nodeList
     * @param id
     * @return
     */
    public ApiResponse saveNodeList(JSONArray nodeList, String id){
        Clash clash = clashDao.selectById(id);
        DumperOptions dumperOptions = new DumperOptions();

        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setIndicatorIndent(2);
        dumperOptions.setIndent(2);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        Yaml yaml = new Yaml(dumperOptions);
        JSONObject jsonObject = yaml.loadAs(clash.getText(), JSONObject.class);
        parseProxies(nodeList);
        jsonObject.put("proxies", nodeList);
        List<String> groupProxies = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            JSONObject item = nodeList.getJSONObject(i);
            groupProxies.add(item.getString("name"));
        }
        JSONArray proxyGroups = jsonObject.getJSONArray("proxy-groups");
        for (int i = 0; i < proxyGroups.size(); i++) {
            JSONObject item = proxyGroups.getJSONObject(i);
            JSONArray proxies = item.getJSONArray("proxies");
            Iterator iterator = proxies.iterator();
            while (iterator.hasNext()) {
                String name = (String) iterator.next();
                if(name.indexOf("DIRECT")>=0){
                    continue;
                }
                //排除分组j
               if (isGroup(proxyGroups, name)){
                   continue;
               }

                iterator.remove();
            }
            proxies.addAll(groupProxies);
            item.put("proxies", proxies);
        }
        StringWriter writer = new StringWriter();
        yaml.dump(jsonObject, writer);
        clash.setText(writer.toString());
        clashDao.updateById(clash);
        return ApiResponse.ok();
    }

    private void parseProxies(JSONArray nodeList){
        for (int i = 0; i < nodeList.size(); i++) {
            JSONObject item = nodeList.getJSONObject(i);
            item.put("name", item.getString("name"));
            item.put("server", item.getString("server"));
            item.put("port", item.getInteger("port"));
            item.put("password", item.getString("password"));
            item.put("sni", item.getString("sni"));
            item.put("type", item.getString("type"));
        }
    }

    private boolean isGroup(JSONArray proxyGroups, String name){
        //排除分组j
        for (int j = 0; j < proxyGroups.size(); j++) {
            JSONObject proxy = proxyGroups.getJSONObject(j);
            if( proxy.getString("name").equals(name)){
               return true;
            }
        }
        return false;
    }

    /**
     * 查询clash列表
     *
     * @return
     */
    public PageInfo findClashList(PageRequest pageRequest) {
        Integer userId = WebCurrentData.getUserId();
        LambdaQueryWrapper<Clash> queryWrapper = Wrappers.<Clash>lambdaQuery().eq(Clash::getUserId, userId).eq(Clash::getDeleted, false);
        Page page = PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Clash> clashList = clashDao.selectList(queryWrapper);
        PageInfo pageInfo = page.toPageInfo();
        pageInfo.setList(clashList);
        return pageInfo;
    }

    /**
     * 删除clash
     */
    public void delClash(String id) {
        Clash userPort = new Clash();
        userPort.setId(id);
        userPort.setDeleted(true);
        clashDao.updateById(userPort);
    }

    /**
     * 查询clash
     * @param id
     * @return
     */
    public Clash findClashById(String id){
        return clashDao.selectById(id);
    }

}
