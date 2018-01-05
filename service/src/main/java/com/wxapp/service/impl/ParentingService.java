package com.wxapp.service.impl;

import com.wxapp.frame.base.BaseService;
import com.wxapp.service.iface.ParentingIface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParentingService extends BaseService implements ParentingIface {

    @Override
    public List getList(int rowNo) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT @rownum:=@rownum+1 rownum, t.* FROM\n" +
                "(SELECT @rownum:=0,t.* FROM parenting_info t ORDER BY t.`create_time` DESC LIMIT ?,10) t", rowNo);
        return null;
    }

    @Override
    public Map getObj(String id) {
        return jdbcTemplate.queryForMap("SELECT * FROM parenting_info t WHERE t.`id` = ?", id);
    }
}
