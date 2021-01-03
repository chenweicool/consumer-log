package com.mylog.mapper;

import java.util.HashMap;
import java.util.List;

import com.mylog.domain.LogsDau;
import org.apache.ibatis.annotations.Param;

public interface LogsDauMapper {
    /**
     * 删除用户的信息
     */
    int deleteByPrimaryKey(@Param("mid") String mid, @Param("logdate") String logdate);

    /**
     * 插入一条数据
     * @mbg.generated
     */
    int insert(LogsDau record);

    /**
     * 根据主键来查询所有的记录
     */
    LogsDau selectByPrimaryKey(@Param("mid") String mid, @Param("logdate") String logdate);

    /**
     * 查询所有的信息
     * @mbg.generated
     */
    List<LogsDau> selectAll();

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(LogsDau record);

    /**
     * 根据用户的设备返回相应的统计信息
     * @return
     */
   List<HashMap<String,Object>> selectByOs();

    /**
     * 统计地域的信息
     * @return
     */
    List<HashMap<String,Object>> selectByArea();

    /**
     * 统计用户的下载来源
     * @return
     */
    List<HashMap<String,Object>> selectByOrigin();

    /**
     * 根据用户的在线时长进行统计
     * @return
     */
    List<HashMap<String,Object>> selectByHour();


}