package com.xcu.dao;

import com.xcu.pojo.Functions;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionDao {
    @Select("select * from functions where id in (select fid from powers where aid = #{aid})")
    List<Functions> selectByAdminId(int id);

}
