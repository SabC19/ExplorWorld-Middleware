package com.sabic.explorworld.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sabic.explorworld.model.TicketDTO;
import com.sabic.explorworld.utils.DAOUtils;
import com.sabic.explorworld.utils.JDBCUtils;

public class TicketDAO {

    private static final String BASE_QUERY =
            " SELECT id, registration_date, ticket_number, price, activity_id, participant_id "
            + " FROM registration_ticket ";

    public TicketDAO() {
    }

    /**
     * Busca un ticket por su id.
     * @param id El id del ticket a buscar.
     * @return El ticket encontrado, o null si no existe.
     */
    public TicketDTO findById(Connection c, Long id) {
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
     * Crea un nuevo ticket.
     * @param ticket El ticket a crear
     * @return El ticket creado con su id asignado, o null si no se pudo crear
     */
    public TicketDTO create(Connection c, TicketDTO ticket) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" INSERT INTO registration_ticket (registration_date, ticket_number, price, activity_id, participant_id) ");
            sqlBuilder.append(" VALUES (?, ?, ?, ?, ?) ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 1;
            ps.setDate(i++, new java.sql.Date(ticket.getRegistroDia().getTime()));
            ps.setString(i++, ticket.getNumeroTicket());
            ps.setDouble(i++, ticket.getPrecio());
            ps.setLong(i++, ticket.getActividadId());
            ps.setLong(i++, ticket.getParticipanteId());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticket.setId(rs.getLong(1));
            }

            return ticket;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(rs, ps, c);
        }

        return null;
    }

    /**
     * Actualiza un ticket existente.
     * @param ticket El ticket con los datos actualizados (debe tener un id válido).
     * @return El ticket actualizado, o null si no se pudo actualizar
     */
    public TicketDTO update(Connection c, TicketDTO ticket) {

        PreparedStatement ps = null;

        try {


            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" UPDATE registration_ticket ");
            sqlBuilder.append(" SET registration_date = ?, ticket_number = ?, price = ?, activity_id = ?, participant_id = ? ");
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);

            int i = 1;
            ps.setDate(i++, new java.sql.Date(ticket.getRegistroDia().getTime()));
            ps.setString(i++, ticket.getNumeroTicket());
            ps.setDouble(i++, ticket.getPrecio());
            ps.setLong(i++, ticket.getActividadId());
            ps.setLong(i++, ticket.getParticipanteId());
            ps.setLong(i++, ticket.getId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return ticket;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(null, ps, c);
        }

        return null;
    }

    /**
     * Elimina un ticket por su id.
     * @param id El id del ticket a eliminar.
     * @return true si se eliminó el ticket, false si no se encontró o no se pudo eliminar
     */
    public boolean delete(Long id) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = JDBCUtils.getConnection();

            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append(" DELETE FROM registration_ticket ");
            sqlBuilder.append(" WHERE id = ? ");
            String sql = sqlBuilder.toString();

            ps = c.prepareStatement(sql);
            ps.setLong(1, id);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOUtils.close(null, ps, c);
        }

        return false;
    }

    private TicketDTO loadNext(ResultSet rs) throws Exception {
        int col = 1;
        TicketDTO t = new TicketDTO();
        t.setId(rs.getLong(col++));
        t.setRegistroDia(rs.getDate(col++));
        t.setNumeroTicket(rs.getString(col++));
        t.setPrecio(rs.getDouble(col++));
        t.setActividadId(rs.getLong(col++));
        t.setParticipanteId(rs.getLong(col++));
        return t;
    }


}
