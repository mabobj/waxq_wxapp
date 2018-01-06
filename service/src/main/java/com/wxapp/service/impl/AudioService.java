package com.wxapp.service.impl;

import com.wxapp.frame.base.BaseService;
import com.wxapp.frame.dao.JdbcDaoMySql;
import com.wxapp.service.iface.AudioIface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AudioService extends BaseService implements AudioIface {
    @Override
    public List<Map<String, Object>> getList(String rowNo) {
        if (rowNo == null || rowNo.equals("")) {
            rowNo = "0";
        }
        return jdbcTemplate.queryForList("SELECT @rownum:=@rownum+1 rownum, t.* FROM " +
                "(SELECT @rownum:=0,t.* FROM audio_info t ORDER BY t.`create_time` DESC LIMIT 0,10) t" );
    }
}
