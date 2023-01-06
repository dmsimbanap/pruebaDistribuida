package com.programacion.servicios;

import com.programacion.db.book;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ServicioBookImpl implements ServicioBook {

    @Inject
    private DataSource dataSource;

    private List<book> ret = new ArrayList<>();

    @Override
    public void create(book obj) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("insert into books(id, isbn, title, author, price) values(?,?,?, ?, ?)");
            pstmt.setInt(1, obj.getBookId());
            pstmt.setString(2, obj.getTitle());
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(Integer id, book obj) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("update books set  id = ?, isbn=?, title = ?, author = ?, price = ? where id = ? ");
            pstmt.setInt(1, obj.getBookId());
            pstmt.setString(2, obj.getIsbn());
            pstmt.setString(3, obj.getTitle());
            pstmt.setString(4, obj.getAuthor());
            pstmt.setDouble(5, obj.getPrice());
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("delete from books where id = ? ");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public book findById(Integer id) {
        book album = null;
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from books where id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                book obj = new book();
                obj.setId(rs.getInt("id"));
                obj.setIsbn(rs.getString("isbn"));
                obj.setTitle(rs.getString("title"));
                obj.setAuthor(rs.getString("author"));
                obj.setPrice(rs.getDouble("price"));
                album = obj;
            }
            rs.close();
            pstmt.close();
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return album;
    }

    @Override
    public List<book> findAll() {
        ret.clear();
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement("select * from books order by id");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                book obj = new book();
                obj.setId(rs.getInt("id"));
                obj.setIsbn(rs.getString("isbn"));
                obj.setTitle(rs.getString("title"));
                obj.setAuthor(rs.getString("author"));
                obj.setPrice(rs.getDouble("price"));
                ret.add(obj);
            }
            rs.close();
            pstmt.close();
        }catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return ret;
    }







}
