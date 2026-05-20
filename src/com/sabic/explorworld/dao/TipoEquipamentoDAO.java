package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sabic.explorworld.model.TipoEquipamento;
import com.sabic.explorworld.utils.JDBCUtils;

public class TipoEquipamentoDAO {

    private static final String BASE_QUERY =
            " SELECT id, model "
            + " FROM equipment_type ";

    public TipoEquipamentoDAO() {
    }

    /**
     * Busca un tipo de equipamento por su id.
     * @param id
     * @return El tipo de equipamento encontrado, o null si no existe.
     */
    public TipoEquipamento findById(Connection c, Long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder(BASE_QUERY);
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            TipoEquipamento te = null;
            if (rs.next()) {
                te = loadNext(rs);
            }

            return te;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }
        return null;
    }

    /**
     * Crea un nuevo tipo de equipamento.
     * @param tipoEquipamento
     * @return El tipo de equipamento creado con su id.
     */
    public TipoEquipamento create(Connection c, TipoEquipamento tipoEquipamento) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" INSERT INTO equipment_type (model) ");
            sqlBuilder.append(" VALUES (?) ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tipoEquipamento.getModel());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tipoEquipamento.setId(rs.getLong(1));
            }

            return tipoEquipamento;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }
        return null;
    }

    /**
     * Actualiza un tipo de equipamento.
     * @param tipoEquipamento
     * @return El tipo de equipamento actualizado.
     */
    public TipoEquipamento update(Connection c, TipoEquipamento tipoEquipamento) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" UPDATE equipment_type ");
            sqlBuilder.append(" SET model = ? ");
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, tipoEquipamento.getModel());
            ps.setLong(i++, tipoEquipamento.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return tipoEquipamento;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }
        return null;
    }

    /**
     * Elimina un tipo de equipamento por su id.
     * @param id
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean delete(Connection c, Long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" DELETE FROM equipment_type ");
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return false;
    }

 
    private TipoEquipamento loadNext(ResultSet rs) throws Exception {
        int col = 1;
        TipoEquipamento te = new TipoEquipamento();
        te.setId(rs.getLong(col++));
        te.setModel(rs.getString(col++));
        return te;
    }
}
