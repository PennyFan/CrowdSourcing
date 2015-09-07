package com.zhengshouzi.myweb.daoimpl;

import com.zhengshouzi.myweb.beans.User;
import com.zhengshouzi.myweb.dao.UserDao;
import com.zhengshouzi.myweb.tools.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by zhengshouzi on 2015/9/7.
 */
public class UserDaoImpl  implements UserDao{
    @Override
    public boolean addUser(User user) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        //����û�
        String sql = "INSERT INTO user (userName,password,email,activateCode,status,registerDate) VALUES(?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getActivateCode());
            ps.setString(5,user.getStatus());
            ps.setTimestamp(6,user.getRegisterDate());

            if (ps.executeUpdate() == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }

    @Override
    public User findUserByEmail(String email) {

        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        User user =new User();
        ResultSet rs = null;

        String sql = "SELECT  * from USER  WHERE email = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,email);

            rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setActivateCode(rs.getString("activateCode"));
                user.setStatus(rs.getString("status"));
                user.setRegisterDate(rs.getTimestamp("registerDate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return user;
    }

    @Override
    public boolean checkUser(User user) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User selectUser =new User();
        String sql = "select * from USER where email=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());

            rs = ps.executeQuery();
            while (rs.next()){
                selectUser.setPassword(rs.getString("password"));
            }
            if(selectUser.getPassword() !=null){
                if(user.getPassword().equals(selectUser.getPassword())){
b=true;
                }else{
                    throw new Exception("�������");
                }
            }else{
                throw new Exception("û������û�");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        boolean b = false;
        Connection connection = DBHelper.getMySqlConnection();
        PreparedStatement ps = null;
        //����û�
        String sql = "DELETE from USER  WHERE  email=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1,email);

            if (ps.executeUpdate() == 1)
                b = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection, ps, null);
        }
        return b;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }




    //�ر�����
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
