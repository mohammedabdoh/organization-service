package com.optimagrowth.organizationservice.service;

import java.util.Locale;
import java.util.UUID;

import com.optimagrowth.organizationservice.model.Organization;
import com.optimagrowth.organizationservice.model.OrganizationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    
    @Autowired
    private MessageSource messages;

    @Autowired
    private OrganizationRepository repository;

    public Organization getOrganization(String organizationId, Locale locale)
    {
        Organization organization = repository.findById(organizationId).get();
        
        if(organization == null) {
            throw new IllegalArgumentException(
                String.format(
                    messages.getMessage(
                        "organization.search.error.message",
                        null,
                        locale
                    ),
                    organizationId
                )
            );
        }

        return organization;
    }

    public Organization createOrganization(Organization organization)
    {
        organization.setOrganizationId(UUID.randomUUID().toString());
        repository.save(organization);
        return organization;
    }

    public Organization updateOrganization(Organization organization, String organizationId)
    {
        organization.setOrganizationId(organizationId);
        repository.save(organization);
        return organization;
    }

    public String deleteOrganization(String organizationId, Locale locale)
    {
        String responseMessage = null;
        
        Organization organization = new Organization();
        organization.setOrganizationId(organizationId);
        repository.delete(organization);

        responseMessage = String.format(
            messages.getMessage("organization.delete.message", null, locale),
            organizationId
        );

        return responseMessage;
    }
}
