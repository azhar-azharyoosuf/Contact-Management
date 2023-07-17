package com.example.contactsmanagement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final JdbcTemplate jdbcTemplate;

    public ContactService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Contact contact = new Contact();
            contact.setContactId(rs.getInt("contactId"));
            contact.setFirstName(rs.getString("firstName"));
            contact.setLastName(rs.getString("lastName"));
            contact.setPhoneNo(rs.getString("phoneNo"));
            contact.setImageUrl(rs.getString("imageUrl"));
            return contact;
        });
    }

    public Contact getContactById(int contactId) {
        String sql = "SELECT * FROM contacts WHERE contactId = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{contactId}, (rs, rowNum) -> {
            Contact contact = new Contact();
            contact.setContactId(rs.getInt("contactId"));
            contact.setFirstName(rs.getString("firstName"));
            contact.setLastName(rs.getString("lastName"));
            contact.setPhoneNo(rs.getString("phoneNo"));
            contact.setImageUrl(rs.getString("imageUrl"));
            return contact;
        });
    }

    public Contact createContact(Contact contact) {
        String sql = "INSERT INTO contacts (firstName, lastName, phoneNo, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhoneNo(), contact.getImageUrl());
        return contact;
    }

    public Contact updateContact(int contactId, Contact contact) {
        String sql = "UPDATE contacts SET firstName = ?, lastName = ?, phoneNo = ?, imageUrl = ? WHERE contactId = ?";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhoneNo(), contact.getImageUrl(), contactId);
        return contact;
    }

    public void deleteContact(int contactId) {
        String sql = "DELETE FROM contacts WHERE contactId = ?";
        jdbcTemplate.update(sql, contactId);
    }
}
