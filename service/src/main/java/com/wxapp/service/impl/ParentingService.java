package com.wxapp.service.impl;

import com.wxapp.frame.base.BaseService;
import com.wxapp.service.iface.ParentingIface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParentingService extends BaseService implements ParentingIface {

    @Override
    public List<Map<String, Object>> getList(String rowNo) {
        if (rowNo == null || rowNo.equals("")) {
            rowNo = "0";
        }
        return jdbcTemplate.queryForList("SELECT @rownum:=@rownum+1 rownum, t.* FROM\n" +
                "(SELECT @rownum:=0,t.id,t.img,t.create_time,t.synopsis,t.title FROM parenting_info t ORDER BY t.`create_time` DESC LIMIT "+rowNo+",10) t");
    }

    @Override
    public Map getObj(String id) {
        return jdbcTemplate.queryForMap("SELECT * FROM parenting_info t WHERE t.`id` = ?", id);
    }
}
