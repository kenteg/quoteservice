package com.example.quoteservice.repository

import com.example.quoteservice.model.audit.History
import org.springframework.data.repository.CrudRepository

interface HistoryRepo: CrudRepository<History, String>