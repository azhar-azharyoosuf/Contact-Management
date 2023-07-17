package com.example.contactsmanagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> getContactById(@PathVariable int contactId) {
        Contact contact = contactService.getContactById(contactId);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<Contact> updateContact(@PathVariable int contactId, @RequestBody Contact contact) {
        Contact updatedContact = contactService.updateContact(contactId, contact);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable int contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.noContent().build();
    }
}
