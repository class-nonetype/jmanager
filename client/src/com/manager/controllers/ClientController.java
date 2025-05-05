package com.manager.controllers;

import com.manager.models.Client;
import java.util.UUID;

public class ClientController {

    public Client createClient(String rut, String fullName, String address) throws IllegalArgumentException {
        if (rut == null || rut.isEmpty() || fullName == null || fullName.isEmpty() || address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }

        UUID id = UUID.randomUUID();
        return new Client(id.toString(), rut.trim(), fullName.trim().toUpperCase(), address.trim().toUpperCase());
    }
}
