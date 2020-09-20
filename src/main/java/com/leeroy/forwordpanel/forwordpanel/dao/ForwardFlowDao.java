package com.leeroy.forwordpanel.forwordpanel.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leeroy.forwordpanel.forwordpanel.model.ForwardFlow;
import com.leeroy.forwordpanel.forwordpanel.model.UserPortForward;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ForwardFlowDao extends BaseMapper<ForwardFlow> {


    @Update("update forward_flow set disabled=#{disabled} where id=#{id}")
    void updateDisable(Boolean disabled, Integer id);

}
