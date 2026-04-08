package com.sandyauto.data.repository

import com.sandyauto.data.Project

class ProjectRepository {
    suspend fun getAll(): List<Project> = emptyList()
    suspend fun save(project: Project) {}
    suspend fun delete(project: Project) {}
}