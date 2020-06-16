package com.xcu.dao;

import com.xcu.pojo.AdminInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInfoDao {
    @Select("select * from admin_info where name=#{name} and pwd = #{pwd}")
    AdminInfo selectByNameAndPwd(AdminInfo ai);
    @Select("select * from admin_info where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "pwd",column = "pwd"),
            @Result(property = "fs",column = "id", many = @Many(select = "com.xcu.dao.FunctionDao.selectByAdminId",fetchType = FetchType.EAGER))
    })
    AdminInfo selectById(int id);
}
