package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sabic.explorworld.dao.criteria.ParticipanteCriteria;
import com.sabic.explorworld.model.Participante;
import com.sabic.explorworld.utils.DAOUtils;
import com.sabic.explorworld.utils.SQLUtils;

public class ParticipanteDAO {

	private static Logger logger = LogManager.getLogger(ParticipanteDAO.class.getName());
	
    private static final String BASE_QUERY =
            " SELECT id, name, last_name, email, phone_number, birth_date, password, gender_id "
            + " FROM participant ";

    public ParticipanteDAO() {}
    
    /**
     * Busca un participante por su ID.
     * @param id
     * @return El participante encontrado, o null si no existe.
     */

    public Participante findById(Connection c, Long id) {
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
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }
    
    /**
	 * Busca participantes por su email (parcial, case-insensitive).
	 * @param email El email a buscar.
	 * @return Lista de participantes que coinciden con el email.
	 */

    public List<Participante> findByEmail(Connection c, String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Participante> participantes = new ArrayList<>();

        try {

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            sql.append(" WHERE UPPER(email) LIKE UPPER(?) "); 
            ps = c.prepareStatement(sql.toString());
            ps.setString(1, "%" + email + "%");

            rs = ps.executeQuery();
            while (rs.next()) {
                participantes.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return participantes;
    }
    
    /**
     * Busca participantes que coincidan con los criterios especificados.
     * @param criteria
     * @return Lista de participantes que cumplen los criterios.
     */

    public List<Participante> findByCriteria(Connection c, ParticipanteCriteria criteria) {
    	
    	// trace
    	// debug
    	// info
    	// warn
    	// error
    	// fatal
    	
    	logger.info("Criteria: {}", criteria);
    	
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder(BASE_QUERY);
            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            SQLUtils.addClause(criteria.getId(), condiciones, "id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getNombre(), condiciones, "UPPER(name) LIKE UPPER(?)", parametros, "%" + criteria.getNombre() + "%");
            SQLUtils.addClause(criteria.getPrimerApellido(), condiciones, "UPPER(last_name) LIKE UPPER(?)", parametros, "%" + criteria.getPrimerApellido() + "%");
            SQLUtils.addClause(criteria.getTelefono(), condiciones, "phone_number = ?", parametros, criteria.getTelefono());
            SQLUtils.addClause(criteria.getEmail(), condiciones, "UPPER(email) LIKE UPPER(?)", parametros, "%" + criteria.getEmail() + "%");
            SQLUtils.addClause(criteria.getFechaNacimiento(), condiciones, "birth_date = ?", parametros, criteria.getFechaNacimiento());
            SQLUtils.addClause(criteria.getGeneroId(), condiciones, "gender_id = ?", parametros, criteria.getGeneroId());

            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }
            
            logger.debug(sql);

            ps = c.prepareStatement(sql.toString());
            int i = 1;
            for (Object param : parametros) {
                ps.setObject(i++, param);
            }

            List<Participante> participantes = new ArrayList<>();
            rs = ps.executeQuery();
            while (rs.next()) {
                participantes.add(loadNext(rs));
            }
            return participantes;

        } catch (Exception e) {
            logger.error(e.getMessage()+": "+criteria, e);
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }
    
    /**
     * Crea un nuevo participante.
     * @param participante
     * @return El participante creado con su ID asignado, o null si ocurrió un error.
     */

    public Participante create(Connection c, Participante participante) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO participant (name, lastname, email, number_phone, birth_date, password, gender_id) ");
            sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?) ");

            ps = c.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

            int i = 1;
            ps.setString(i++, participante.getNombre());
            ps.setString(i++, participante.getPrimerApellido());
            ps.setString(i++, participante.getEmail());
            ps.setString(i++, participante.getTelefono());
            ps.setDate(i++, new java.sql.Date(participante.getFechaNacimiento().getTime()));
            ps.setString(i++, participante.getPassword());
            ps.setLong(i++, participante.getGeneroId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                participante.setId(rs.getLong(1));
            }

            return participante;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }
    
    /**
     * Actualiza un participante existente. El participante debe tener un ID válido.
     * @param participante
     * @return El participante actualizado, o null si no se pudo actualizar.
     */

    public Participante update(Connection c, Participante participante) {
    	
        PreparedStatement ps = null;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" UPDATE participant SET name = ?, lastname = ?, number_phone = ?, email = ?, birth_date = ?, password = ?, gender_id = ? ");
            sql.append(" WHERE id = ? ");

            ps = c.prepareStatement(sql.toString());
            int i = 1;
            ps.setString(i++, participante.getNombre());
            ps.setString(i++, participante.getPrimerApellido());
            ps.setString(i++, participante.getTelefono());
            ps.setString(i++, participante.getEmail());
            ps.setDate(i++, new java.sql.Date(participante.getFechaNacimiento().getTime()));
            ps.setString(i++, participante.getPassword());
            ps.setLong(i++, participante.getGeneroId());
            ps.setLong(i++, participante.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) return participante;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(null, ps, c);
        }

        return null;
    }
    
    /**
     * Elimina un participante por su ID.
     * @param id
     * @return true si el participante fue eliminado, false si no se encontró o ocurrió un error.
     */

    public boolean delete(Connection c, Long id) {
        PreparedStatement ps = null;

        try {

            StringBuilder sql = new StringBuilder();
            sql.append(" DELETE FROM participant WHERE id = ? ");

            ps = c.prepareStatement(sql.toString());
            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    private Participante loadNext(ResultSet rs) throws Exception {
        int col = 1;
        Participante p = new Participante();
        p.setId(rs.getLong(col++));
        p.setNombre(rs.getString(col++));
        p.setPrimerApellido(rs.getString(col++));
        p.setEmail(rs.getString(col++));
        p.setTelefono(rs.getString(col++));
        p.setFechaNacimiento(rs.getDate(col++));
        p.setPassword(rs.getString(col++));
        p.setGeneroId(rs.getLong(col++));
        return p;
    }
}
