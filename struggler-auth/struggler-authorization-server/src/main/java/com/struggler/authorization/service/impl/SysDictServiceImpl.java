package com.struggler.authorization.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.struggler.authorization.mapper.SysDictMapper;
import com.struggler.authorization.model.BaseUserDetail;
import com.struggler.authorization.service.SysDictService;
import com.struggler.common.model.SysDict;
import com.struggler.common.utils.PageUtils;
import com.struggler.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据字典实现类
 *
 * @author wanglp
 * @date 2019/7/5
 **/
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {
    @Autowired
    private SysDictService sysDictService;

    @Override
    public PageUtils selectSysDictByPage(Query<SysDict> params) {
        Page<SysDict> pageParams = params.getPage();
        List<SysDict> sysDicts = baseMapper.queryPage(pageParams, params);
        pageParams.setRecords(sysDicts);
        return new PageUtils(pageParams);
    }

    @Override
    public List<SysDict> findByDicCodes(String dicCode) {
        List<SysDict> result = baseMapper.findByDicCode(dicCode);
        return result;
    }

    @Override
    public void add(SysDict dict) {
        List<Map<String, Object>> itemBean = dict.getItemBean();

        dict.setModelType("1");
        dict.setUpdatedBy(1);
        this.insert(dict);
        for (int i = 0; i < itemBean.size(); i++) {
            Map<String, Object> item = itemBean.get(i);
            dict.setDicCode("");
            dict.setDicName("");
            dict.setDicFlag("");
            dict.setItemCode((String) item.get("itemCode"));
            dict.setItemValue((String) item.get("itemCode"));
            dict.setItemSeq((Integer) item.get("itemSeq"));
            dict.setItemFlag((String) item.get("itemCode"));
            dict.setItemFlag((String) item.get("itemFlag"));
            dict.setModelType("2");
            dict.setUpdatedBy(1);
            sysDictService.insert(dict);
        }


    }
}
