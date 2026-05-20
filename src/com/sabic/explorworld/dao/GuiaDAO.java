package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sabic.explorworld.dao.criteria.GuiaCriteria;
import com.sabic.explorworld.model.GuiaDTO;
import com.sabic.explorworld.utils.DAOUtils;
import com.sabic.explorworld.utils.JDBCUtils;
import com.sabic.explorworld.utils.SQLUtils;

public class GuiaDAO {

    private static final String BASE_QUERY =
        " SELECT id, name, lastname, nie, email, phone_number, birth_date, password, gender_id FROM guide ";

    public GuiaDAO() {}

    public GuiaDTO findById(Connection c, Long id) {
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
            JDBCUtils.close(rs, ps);
        }
        return null;
    }

    public GuiaDTO findByEmail(Connection c, String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = BASE_QUERY + " WHERE UPPER(email) = UPPER(?) ";
            ps = c.prepareStatement(sql);
            ps.setString(1, email);
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

    public List<GuiaDTO> findByActividad(Connection c, Long actividadId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GuiaDTO> guias = new ArrayList<>();

        try {

            String sql = "SELECT g.id, g.name, g.lastname, g.nie, g.email, g.phone_number, g.birth_date, g.password, g.gender_id " +
                         "FROM guide g " +
                         "JOIN activity_guide ag ON g.id = ag.guide_id " +
                         "WHERE ag.activity_id = ? ORDER BY g.name ";
            ps = c.prepareStatement(sql);
            ps.setLong(1, actividadId);
            rs = ps.executeQuery();

            while (rs.next()) {
                guias.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return guias;
    }

    public List<GuiaDTO> findBy(Connection c, GuiaCriteria criteria) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GuiaDTO> guias = new ArrayList<>();

        try {
            StringBuilder sql = new StringBuilder(BASE_QUERY);

            List<String> condiciones = new ArrayList<>();
            List<Object> parametros = new ArrayList<>();

            SQLUtils.addClause(criteria.getId(), condiciones, "id = ?", parametros, criteria.getId());
            SQLUtils.addClause(criteria.getNombre(), condiciones, "UPPER(name) LIKE UPPER(?)", parametros, "%" + criteria.getNombre() + "%");
            SQLUtils.addClause(criteria.getPrimerApellido(), condiciones, "UPPER(lastname) LIKE UPPER(?)", parametros, "%" + criteria.getPrimerApellido() + "%");
            SQLUtils.addClause(criteria.getNie(), condiciones, "UPPER(nie) = UPPER(?)", parametros, criteria.getNie());
            SQLUtils.addClause(criteria.getEmail(), condiciones, "UPPER(email) = UPPER(?)", parametros, criteria.getEmail());
            SQLUtils.addClause(criteria.getTelefono(), condiciones, "phone_number = ?", parametros, criteria.getTelefono());
            SQLUtils.addClause(criteria.getFechaNacimiento(), condiciones, "birth_date = ?", parametros, criteria.getFechaNacimiento());
            SQLUtils.addClause(criteria.getGeneroId(), condiciones, "gender_id = ?", parametros, criteria.getGeneroId());
            
            if (!condiciones.isEmpty()) {
                sql.append(" WHERE ").append(String.join(" AND ", condiciones));
            }
            
            sql.append(" ORDER BY name ");
            
          

            ps = c.prepareStatement(sql.toString());
            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                guias.add(loadNext(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return guias;
    }

    public GuiaDTO create(Connection c, GuiaDTO guia) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "INSERT INTO guide (name, lastname, nie, email, phone_number, birth_date, password, gender_id) " +
                         "VALUES (?,?,?,?,?,?,?,?)";
            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            ps.setString(i++, guia.getNombre());
            ps.setString(i++, guia.getPrimerApellido());
            ps.setString(i++, guia.getNie());
            ps.setString(i++, guia.getEmail());
            ps.setString(i++, guia.getTelefono());
            ps.setDate(i++, new java.sql.Date(guia.getFechaNacimiento().getTime()));
            ps.setString(i++, guia.getPassword());
            ps.setLong(i++, guia.getGeneroId());

            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                guia.setId(rs.getLong(1));
            }

            return guia;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return null;
    }

    public boolean update(Connection c, GuiaDTO guia) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE guide SET name = ?, lastname = ?, nie = ?, ");
            sql.append("email = ?, phone_number = ?, birth_date = ?, password = ?, ");
            sql.append("gender_id = ? WHERE id = ?");
            
            ps = c.prepareStatement(sql.toString());
            DAOUtils.setParameters(ps,
            		guia.getNombre(),
            		guia.getPrimerApellido(),
            		guia.getNie(),
            		guia.getEmail(),
            		guia.getTelefono(),
            		new java.sql.Date(guia.getFechaNacimiento().getTime()),
            		guia.getPassword(),
            		guia.getGeneroId(),
            		guia.getId()
            		);
            int rows = ps.executeUpdate();
            return rows > 0;
         } catch (Exception e) {
        	 e.printStackTrace();
         } finally {
        	 JDBCUtils.close(rs, ps);
		 }
        return false;
	}

    public boolean delete(Connection c, Long id) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "DELETE FROM guide WHERE id = ?";
            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	JDBCUtils.close(rs, ps);
        }

        return false;
    }

    private GuiaDTO loadNext(ResultSet rs) throws Exception {
        int i = 1;
        GuiaDTO g = new GuiaDTO();
        g.setId(rs.getLong(i++));
        g.setNombre(rs.getString(i++));
        g.setPrimerApellido(rs.getString(i++));
        g.setNie(rs.getString(i++));
        g.setEmail(rs.getString(i++));
        g.setTelefono(rs.getString(i++));
        g.setFechaNacimiento(rs.getDate(i++));
        g.setPassword(rs.getString(i++));
        g.setGeneroId(rs.getLong(i++));
        return g;
    }
}
