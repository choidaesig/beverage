package com.bmilk.bev.repository;

import com.bmilk.bev.domain.Beverage;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBevRepository implements BevRepository {
    private final DataSource dataSource;

    public JdbcBevRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public List<Beverage> findAll() {
        String sql = "select * from beverage";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Beverage> bevs = new ArrayList<>();
            while (rs.next()) {
                Beverage beverage = new Beverage();
                beverage.setId(rs.getLong("id"));
                beverage.setName(rs.getString("name"));
                beverage.setPrice(rs.getInt("price"));
                beverage.setAmount(rs.getInt("amount"));
                beverage.setImg(rs.getString("img"));
                bevs.add(beverage);
            }
            return bevs;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void buy(Beverage beverage) {
        String sql = "UPDATE beverage Set amount = "+(beverage.getAmount()-1)+ " WHERE id = "+ beverage.getId();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Beverage findBev(String id) {
        String sql = "select * from beverage where 1 ";
        if (id != null) {
            sql = sql + " AND id like " + id;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            Beverage bev = new Beverage();
            bev.setId(rs.getLong("id"));
            bev.setName(rs.getString("name"));
            bev.setPrice(rs.getInt("price"));
            bev.setAmount(rs.getInt("amount"));
            bev.setImg(rs.getString("img"));
            return bev;


        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }
}
