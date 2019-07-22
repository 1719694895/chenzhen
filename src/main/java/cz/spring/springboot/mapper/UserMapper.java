package cz.spring.springboot.mapper;


import cz.spring.springboot.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


/**
 * ClassName:UserMapper
 *
 * @Description: TODO
 * @Author:chenzhen Date:2019/7/21 20:18
 * Version 1.0
 **/
@Mapper
public interface UserMapper {


    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);

}
