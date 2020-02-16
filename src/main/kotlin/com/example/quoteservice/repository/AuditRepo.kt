package com.example.quoteservice.repository

import com.example.quoteservice.model.audit.Audit
import org.springframework.data.repository.CrudRepository

interface AuditRepo: CrudRepository<Audit, String>