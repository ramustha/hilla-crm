package com.example.application.data.endpoint;

import java.util.UUID;

import javax.annotation.security.PermitAll;

import com.example.application.data.entity.Contact;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.StatusRepository;

import dev.hilla.Endpoint;
import dev.hilla.Nonnull;

@Endpoint
@PermitAll
public class CrmEndpoint {

  private ContactRepository contactRepository;
  private CompanyRepository companyRepository;
  private StatusRepository statusRepository;

  public CrmEndpoint(ContactRepository contactRepository,
      CompanyRepository companyRepository,
      StatusRepository statusRepository) {
    this.contactRepository = contactRepository;
    this.companyRepository = companyRepository;
    this.statusRepository = statusRepository;
  }

  @Nonnull
  public CrmData getCrmData() {
    CrmData crmData = new CrmData();
    crmData.contacts = contactRepository.findAll();
    crmData.companies = companyRepository.findAll();
    crmData.statuses = statusRepository.findAll();
    return crmData;
  }

  @Nonnull
  public Contact saveContact(Contact contact) {
    contact.setCompany(companyRepository.findById(contact.getCompany().getId())
        .orElseThrow(() -> new RuntimeException("Could not find Company with ID " + contact.getCompany().getId())));
    contact.setStatus(statusRepository.findById(contact.getStatus().getId())
        .orElseThrow(() -> new RuntimeException("Could not find Status with ID " + contact.getStatus().getId())));

    return contactRepository.save(contact);
  }

  public void deleteContact(UUID contactId) {
    contactRepository.deleteById(contactId);
  }
}