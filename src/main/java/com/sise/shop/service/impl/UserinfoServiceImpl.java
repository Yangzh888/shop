package com.sise.shop.service.impl;

import com.sise.shop.entity.Userinfo;
import com.sise.shop.mapper.UserinfoMapper;
import com.sise.shop.service.IUserinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sise.shop.utilis.shopUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yangzhenhua
 * @since 2018-12-12
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Resource
    private UserinfoMapper userinfoMapper;

    @Override
    public List<Userinfo> login(String password, String userId) {
        List<Userinfo> list = userinfoMapper.checekLogin(userId, password);

        if (list == null && list.isEmpty()) {
            return null;
        } else {
            return list;
        }

    }

    @Override
    public List<Userinfo> checkUnqiue(String userId) {
        List<Userinfo> list=  userinfoMapper.checkUnqiue(userId);
        if (list == null && list.isEmpty()) {
            return null;
        } else {
            return list;
        }
    }

    @Override
    public boolean checkAnsToChangePassWord(Map map) {
        String userId = shopUtils.getUserId(map);

        Userinfo userinfo = userinfoMapper.selectById(userId);
        String forgetAns = userinfo.getForgetAns();             //数据库存放的原有答案
        String forgetAnsCheck = MapUtils.getString(map, "forgetAnsCheck");   //用户传输过来的答案
        if(forgetAns.equals(forgetAnsCheck)){
            String newPassWord = MapUtils.getString(map, "newPassWord");
            byte[] sb = newPassWord.getBytes();
            String password =DigestUtils.md5DigestAsHex(sb);
            userinfoMapper.updatePassword(password,userId);
            return true;
        }else{
       return false;
    }}
}
