package com.mylog.mapper;
import com.mylog.domain.Admin;
import java.util.List;

/**
 * 管理员的接口实现类
 */
public interface AdminMapper {
    /**
     * 根据主键删除
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入的一条的实现
     */
    int insert(Admin record);

    /**
     *  更新记录
     */
    int updateByPrimaryKey(Admin record);

       /*
   *   根据主键查询
   * */
    Admin selectByPrimaryKey(Integer id);

    /**
     * 查询所有的记录
     *
     */
    List<Admin> selectAll();


    /*根据用户名查询用户的信息*/
    Admin selectByUserName(String userName);
}