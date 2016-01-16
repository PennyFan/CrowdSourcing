package com.sysu.crowdsourcing.dao.impl.jdbc;

import com.sysu.crowdsourcing.dao.UserDao;
import com.sysu.crowdsourcing.tools.DBHelper;
import com.sysu.workflow.service.indentityservice.UserEntity;
import org.hibernate.Criteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by zhengshouzi on 2015/9/7.
 */
public class UserDaoImpl implements UserDao {


    public UserEntity findUserByEmail(String email) {

        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        UserEntity user = new UserEntity();
        ResultSet rs = null;

        String sql = "SELECT  * from USER  WHERE email = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            while (rs.next()) {
                user.setUserId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("password"));
                user.setUserEmail(rs.getString("email"));
                user.setUserActivateCode(rs.getString("activateCode"));
                user.setUserStatus(rs.getString("status"));
                user.setUserRegisterDate(rs.getTimestamp("registerDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return user;
    }


    public UserEntity findUserById(String user_id) {
        return null;
    }


    public List<UserEntity> findAllUser() {
        return null;
    }


    public List<UserEntity> findUserByCriteria(Criteria criteria) {
        return null;
    }


    public boolean checkUser(UserEntity userEntity) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserEntity selectUser = new UserEntity();
        String sql = "select * from USER where email=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userEntity.getUserEmail());

            rs = ps.executeQuery();
            while (rs.next()) {
                selectUser.setUserPassword(rs.getString("password"));
            }
            if (selectUser.getUserPassword() != null) {
                if (userEntity.getUserPassword().equals(selectUser.getUserPassword())) {
                    b = true;
                } else {
                    throw new Exception("密码错误");
                }
            } else {
                throw new Exception("没有这个用户");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }


    public boolean addUser(UserEntity userEntity) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        //添加用户
        String sql = "INSERT INTO user (userName,password,email,activateCode,status,registerDate) VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userEntity.getUserName());
            ps.setString(2, userEntity.getUserPassword());
            ps.setString(3, userEntity.getUserEmail());
            ps.setString(4, userEntity.getUserActivateCode());
            ps.setString(5, userEntity.getUserStatus());
            //ps.setTimestamp(6, userEntity.getRegisterDate());

            if (ps.executeUpdate() == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }


    public boolean deleteUserByEmail(String email) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        //添加用户
        String sql = "DELETE from USER  WHERE  email=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            if (ps.executeUpdate() == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }


    public boolean deleteUserById(String user_id) {
        return false;
    }


    public boolean updateUser(UserEntity userEntity) {
        return false;
    }


    public boolean updateRegisterStatus(String email, String status) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user_register_table SET status=? where email=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, email);
            if (ps.executeUpdate() == 1) {
                b = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }


    //关闭连接
    public void close(Connection cn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
