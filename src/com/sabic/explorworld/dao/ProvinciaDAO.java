package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sabic.explorworld.model.Provincia;
import com.sabic.explorworld.utils.DAOUtils;

public class ProvinciaDAO {

    private static final String BASE_QUERY =
            " SELECT id, name FROM province ";

    public ProvinciaDAO() {
    }

    /**
     * Busca una provincia por su id.
     * @param id El id de la provincia a buscar.
     * @return La provincia encontrada, o null si no existe.
     */
    public Provincia findById(Connection c, Long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder(BASE_QUERY);
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                return loadNext(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    /**
     * Busca una provincia por su nombre.
     * @param nombre El nombre de la provincia a buscar.
     * @return La provincia encontrada, o null si no existe.
     */
    public Provincia findByNombre(Connection c, String nombre) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder(BASE_QUERY);
            sqlBuilder.append(" WHERE UPPER (name) LIKE ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nombre.toUpperCase() + "%");

            rs = ps.executeQuery();

            if (rs.next()) {
                return loadNext(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    private Provincia loadNext(ResultSet rs) throws Exception {
        int col = 1;
        Provincia p = new Provincia();
        p.setId(rs.getLong(col++));
        p.setNombre(rs.getString(col++));
        return p;
    }
}
