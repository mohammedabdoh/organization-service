package com.optimagrowth.organizationservice.controller;

import java.util.Locale;

import com.optimagrowth.organizationservice.model.Organization;
import com.optimagrowth.organizationservice.service.OrganizationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/organization")
public class OrganizationController
{
    @Autowired
    private OrganizationService service;

    @GetMapping(path = "/{organizationId}")
    public ResponseEntity<Organization> getOrganization(
        @PathVariable(name = "organizationId") String organizationId,
        @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        Organization organization = service.getOrganization(organizationId, locale);
        return ResponseEntity.ok(organization);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organizationBody) {
        return ResponseEntity.ok(service.createOrganization(organizationBody));
    }

    @PutMapping(path = "/{organizationId}/update")
    public ResponseEntity<Organization> updateOrganization(
        @RequestBody Organization organizationBody,
        @PathVariable(name = "organizationId") String organizationId
    ) {
        return ResponseEntity.ok(service.updateOrganization(organizationBody, organizationId));
    }

    @DeleteMapping(path = "/{organizationId}/delete")
    public ResponseEntity<String> deleteOrganization(
        @PathVariable(name = "organizationId") String organizationId,
        @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        return ResponseEntity.ok(service.deleteOrganization(organizationId, locale));
    }
}