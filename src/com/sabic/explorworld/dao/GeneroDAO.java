package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sabic.explorworld.model.Genero;
import com.sabic.explorworld.utils.JDBCUtils;

public class GeneroDAO {
	
    private static final Logger logger = LogManager.getLogger(GeneroDAO.class);

    private static final String BASE_QUERY =
        " SELECT id, name FROM gender ";

    public GeneroDAO() {}

    /**
     * Busca un genero por su ID.
     * @param id
     * @return El genero encontrado, o null si no existe.
     */
    public Genero findById(Connection c, Long id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            sql.append(" WHERE id = ? ");

            ps = c.prepareStatement(sql.toString());
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return loadNext(rs);
            }

        } catch (Exception e) {
            logger.error("Error al buscar actividad: {}", id, e);
            throw e;
        }
		return null;

    }

    /**
     * Busca todos los generos.
     * @param id
     * @return Una lista con todos los generos encontrados, o null si no existe ninguno.
     */
    public List<Genero> findAll(Connection c) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Genero> lista = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            sql.append(" ORDER BY id ");

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

 
    private Genero loadNext(ResultSet rs) throws Exception {
        int i = 1;
        Genero ge = new Genero();
        ge.setId(rs.getLong(i++));
        ge.setNombre(rs.getString(i++));
        return ge;
    }
}
