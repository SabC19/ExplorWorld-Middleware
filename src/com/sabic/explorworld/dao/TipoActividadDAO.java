package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sabic.explorworld.model.TipoActividadDTO;
import com.sabic.explorworld.utils.JDBCUtils;

public class TipoActividadDAO {

    private static final String BASE_QUERY =
            " SELECT id, name, equipment_type_id "
            + " FROM activity_type ";

    public TipoActividadDAO() {
    }

    /**
     * Obtiene un tipo de actividad por su id.
     * @param id
     * @return El tipo de actividad encontrado, o null si no existe.
     */
    public TipoActividadDTO findById(Connection c, Long id) {
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
        	JDBCUtils.close(rs, ps);
        }
        return null;
    }
    /**
     * Obtiene todos los tipos de actividad.
     * @param id 
     * @return Una lista con todos los tipos de actividad encontrados, o null si no existe ninguno.
     */
    public List<TipoActividadDTO> findAll(Connection c) {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<TipoActividadDTO> lista = new ArrayList<>();
    	
    	try {
			
			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY name ");
			
			ps = c.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			
			while (rs.next()) {
				lista.add(loadNext(rs));
			}
			
			return lista;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
    	return null;
    }

    /**
     * Crea un nuevo tipo de actividad.
     * @param tipoActividad El tipo de actividad a crear.
     * @return El tipo de actividad creado con su id asignado.
     */
    public TipoActividadDTO create(Connection c, TipoActividadDTO tipoActividad) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" INSERT INTO activity_type (name, equipment_type_id) ");
            sqlBuilder.append(" VALUES (?, ?) ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++, tipoActividad.getNombre());
            ps.setLong(i++, tipoActividad.getTipoEquipamentoId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                tipoActividad.setId(rs.getLong(1));
            }

            return tipoActividad;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return null;
    }

    /**
     * Actualiza un tipo de actividad existente.
     * @param tipoActividad El tipo de actividad con los datos actualizados.
     * @return El tipo de actividad actualizado, o null si no se pudo actualizar.
     */
    public TipoActividadDTO update(Connection c, TipoActividadDTO tipoActividad) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" UPDATE activity_type ");
            sqlBuilder.append(" SET name = ?, equipment_type_id = ? ");
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, tipoActividad.getNombre());
            ps.setLong(i++, tipoActividad.getTipoEquipamentoId());
            ps.setLong(i++, tipoActividad.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return tipoActividad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return null;
    }

    /**
     * Elimina un tipo de actividad por su id.
     * @param id El id del tipo de actividad a eliminar.
     * @return true si se eliminó correctamente, false si no se pudo eliminar o si el id es null.
     */
    public boolean delete(Connection c, Long id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" DELETE FROM activity_type ");
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
   
    private TipoActividadDTO loadNext(ResultSet rs) throws Exception {
        int col = 1;
        TipoActividadDTO ta = new TipoActividadDTO();
        ta.setId(rs.getLong(col++));
        ta.setNombre(rs.getString(col++));
        ta.setTipoEquipamentoId(rs.getLong(col++));
        return ta;
    }
}
