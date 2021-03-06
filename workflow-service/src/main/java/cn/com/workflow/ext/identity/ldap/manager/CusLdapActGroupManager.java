package cn.com.workflow.ext.identity.ldap.manager;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.workflow.mybatis.client.TbSysSecurityRelUserPosMapper;
import cn.com.workflow.mybatis.model.TbSysSecurityRelUserPos;

/**
 * activiti用户组扩展实现Manager 
 * Package : cn.com.workflow.ext.identity.database.factory
 * 
 * @author wangzhiyin
 *         2017年9月27日 上午11:23:57
 *
 */
@Service("cusLdapActGroupManager")
public class CusLdapActGroupManager extends GroupEntityManager {
    @Autowired
    private TbSysSecurityRelUserPosMapper tbSysSecurityRelUserPosMapperImpl;

    static Logger logger = LogManager.getLogger();

    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Group> list = new ArrayList<Group>();
        List<TbSysSecurityRelUserPos> ps = tbSysSecurityRelUserPosMapperImpl.selectPositionForUserId(userId);
        for (TbSysSecurityRelUserPos post : ps) {
            GroupEntity group = new GroupEntity();
            group.setId(post.getPositionCd());
            group.setName(post.getPositionName());
            list.add(group);
        }
        return list;
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        logger.debug("------------findGroupByQueryCriteria--------query:{}-----", query);
        List<Group> list = new ArrayList<Group>();
        List<TbSysSecurityRelUserPos> ps = tbSysSecurityRelUserPosMapperImpl.selectPositionForUserId(query.getUserId());
        for (TbSysSecurityRelUserPos post : ps) {
            GroupEntity group = new GroupEntity();
            group.setId(post.getPositionCd());
            group.setName(post.getPositionName());
            list.add(group);
        }
        return list;
    }
}
