package com.wxapp.service.iface;

import java.util.List;
import java.util.Map;

public interface ParentingIface {
    public List<Map<String, Object>> getList(String rowNo);

    public Map getObj(String id);
}
