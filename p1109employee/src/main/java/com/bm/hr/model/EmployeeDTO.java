package com.bm.hr.model;

public record EmployeeDTO(Long id, String firstName, String lastName, String email) {}
// record, introduced in Java 14 automatically generates the constructor, getters.
// Fields in a record are final by default, promoting immutability.