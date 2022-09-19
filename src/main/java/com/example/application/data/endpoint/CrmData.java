package com.example.application.data.endpoint;

import java.util.Collections;
import java.util.List;

import com.example.application.data.entity.Company;
import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Status;

import dev.hilla.Nonnull;

public class CrmData {

  @Nonnull
  public List<@Nonnull Contact> contacts = Collections.emptyList();
  @Nonnull
  public List<@Nonnull Company> companies = Collections.emptyList();
  @Nonnull
  public List<@Nonnull Status> statuses = Collections.emptyList();

}