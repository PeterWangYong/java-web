package com.damu.dao;

import com.damu.entity.Users;
import com.damu.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UsersDao {
    private SqlSession sqlSession;
    private List<Users> list;
    private Users user;

    private SqlSession getSqlSession() {
        sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        return sqlSession;
    }

    /**
     * 查询所有用户
     * @return
     */
    public List<Users> findAll() {
        try {
            list = getSqlSession().selectList("findUsers");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return list;
    }

    /**
     * 查询单个用户编号
     * @param id
     * @return
     */
    public Users findById(int id) {
        try {
            user = getSqlSession().selectOne("findUsers", new Users(id));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    /**
     * 增加一个新用户数据到数据库的方法
     * @param user
     * @return
     */
    public Users addUser(Users user) {
        try {
            // 返回值：是insert执行过程中影响的行数
            getSqlSession().insert("addUser", user);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    /**
     * 用于修改用户资料的方法
     * @param user
     * @return
     */
    public Users updateUser(Users user) {
        try {
            getSqlSession().update("updateUser", user);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return user;
    }

    /**
     * 用于删除用户资料的方法
     */
    public void deleteUser(Integer id) {
        try {
            getSqlSession().delete("delUser", id);
            sqlSession.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
