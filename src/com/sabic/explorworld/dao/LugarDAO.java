package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sabic.explorworld.model.LugarDTO;
import com.sabic.explorworld.utils.JDBCUtils;

public class LugarDAO {

	private static final String BASE_QUERY =
			" SELECT p.id, p.name, p.address, p.latitude, p.longitude, p.locality_id "
					+ " FROM place p "
					+ " INNER JOIN locality l ON p.locality_id = l.id "
					+ " INNER JOIN province pr ON pr.id = l.province_id ";


	public LugarDAO() {
	}

	/**
	 * Búsqueda de un lugar por su Id.
	 * @param id El ID del lugar a buscar.
	 * @return El lugar encontrado, o null si no existe.
	 */
	public LugarDTO findById(Connection c, Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" WHERE p.id = ? ");

			ps = c.prepareStatement(sql.toString());
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
	 * Recupera todos los lugares ordenados por ID.
	 * @param c Conexión a la base de datos.
	 * @return Lista de todos los lugares encontrados.
	 */
	public List<LugarDTO> findAll(Connection c) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<LugarDTO> lista = new ArrayList<>();

		try {

			StringBuilder sql = new StringBuilder(BASE_QUERY);
			sql.append(" ORDER BY p.id ");

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

		return lista;
	}



	/**
	 * Crea un nuevo lugar.
	 * @param lugar El lugar a crear (sin ID).
	 * @return El lugar creado (con ID asignado), o null si no se pudo crear.
	 */
	public LugarDTO create(Connection c, LugarDTO lugar) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO place ");
			sql.append(" (name, address, latitude, longitude, locality_id) ");
			sql.append(" VALUES (?, ?, ?, ?, ?) ");

			ps = c.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			ps.setString(i++, lugar.getNombre());
			ps.setString(i++, lugar.getDireccion());
			ps.setDouble(i++, lugar.getLatitud());
			ps.setDouble(i++, lugar.getLongitud());
			ps.setLong(i++, lugar.getLocalidadId());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				lugar.setId(rs.getLong(1));
			}

			return lugar;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	/**
	 * Actualiza un lugar existente.
	 * @param lugar El lugar con los datos actualizados (debe tener un ID válido).
	 * @return El lugar actualizado, o null si no se pudo actualizar (por ejemplo, si no existe el ID).
	 */
	public LugarDTO update(Connection c, LugarDTO lugar) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE place ");
			sql.append(" SET name = ?, address = ?, latitude = ?, longitude = ?, ");
			sql.append(" locality_id = ? ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());

			int i = 1;
			ps.setString(i++, lugar.getNombre());
			ps.setString(i++, lugar.getDireccion());
			ps.setDouble(i++, lugar.getLatitud());
			ps.setDouble(i++, lugar.getLongitud());
			ps.setLong(i++, lugar.getLocalidadId());
			ps.setLong(i++, lugar.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return lugar;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, ps);
		}
		return null;
	}

	/**
	 * Elimina un lugar por su Id.
	 * @param id El ID del lugar a eliminar.
	 * @return true si se eliminó correctamente, false si no se pudo eliminar (por ejemplo, si no existe el ID).
	 */
	public boolean delete(Connection c, Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" DELETE FROM place ");
			sql.append(" WHERE id = ? ");

			ps = c.prepareStatement(sql.toString());
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


	/**
	 * Mapea un LugarDTO desde el ResultSet.
	 */
	private LugarDTO loadNext(ResultSet rs) throws Exception {
		int i = 1;
		LugarDTO l = new LugarDTO();
		l.setId(rs.getLong(i++));
		l.setNombre(rs.getString(i++));
		l.setDireccion(rs.getString(i++));
		l.setLatitud(rs.getDouble(i++));
		l.setLongitud(rs.getDouble(i++));
		l.setLocalidadId(rs.getLong(i++));
		return l;
	}
}
