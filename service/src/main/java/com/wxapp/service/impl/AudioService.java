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
    public List getList(int rowNo) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("" );
        return null;
    }
}
