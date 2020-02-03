package com.qsq.ego.search.service.impl;

import com.qsq.ego.rpc.pojo.TbItemParamItem;
import com.qsq.ego.rpc.service.ParamItemService;
import com.qsq.ego.search.service.SearchItemParamService;
import com.qsq.ego.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchItemParamServiceImpl implements SearchItemParamService {
    @Autowired
    private ParamItemService paramItemServiceProxy;
    @Override
    public String loadItemParamService(Long id) {
        TbItemParamItem tbItemParamItem = paramItemServiceProxy.loadTbItemParamItemService(id);
        String paramData = tbItemParamItem.getParamData();
        return getItemParamData(paramData);
    }
    private String getItemParamData(String paramData) {
        // 瑙ｆ瀽json瀛楃涓?
        List<Map> listMap = JsonUtils.jsonToList(paramData, Map.class);

        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m1 : listMap) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">" + m1.get("group") + "</th>\n");
            sb.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for (Map m2 : list2) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">" + m2.get("k") + "</td>\n");
                sb.append("            <td>" + m2.get("v") + "</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        // 杩斿洖html鐗囨
        return sb.toString();

    }

}
