package com.example.healthassistant.data.experiences

import com.example.healthassistant.data.experiences.entities.Experience
import com.example.healthassistant.data.experiences.entities.ExperienceWithIngestions
import com.example.healthassistant.data.experiences.entities.Ingestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExperienceRepository @Inject constructor(private val experienceDao: ExperienceDao) {
    suspend fun addExperience(experience: Experience) = experienceDao.insert(experience)
    suspend fun addIngestion(ingestion: Ingestion) = experienceDao.insert(ingestion)
    suspend fun updateExperience(experience: Experience) = experienceDao.update(experience)

    suspend fun deleteIngestion(ingestion: Ingestion) = experienceDao.deleteIngestion(ingestion)

    suspend fun deleteExperienceWithIngestions(experience: Experience) =
        experienceDao.deleteExperienceWithIngestions(experience)

    fun getAllExperiencesWithIngestions(): Flow<List<ExperienceWithIngestions>> =
        experienceDao.getExperiencesWithIngestions()
            .flowOn(Dispatchers.IO)
            .conflate()

    fun getLastUsedSubstanceNames(limit: Int): Flow<List<String>> =
        experienceDao.getLastUsedSubstanceNames(limit).flowOn(Dispatchers.IO).conflate()

    suspend fun getLastExperiences(limit: Int) = experienceDao.getLastExperiences(limit)
    suspend fun getExperience(id: Int): Experience? = experienceDao.getExperienceByID(id)
    fun getExperienceWithIngestions(experienceId: Int) =
        experienceDao.getExperienceWithIngestions(experienceId).flowOn(Dispatchers.IO)
            .conflate()

    suspend fun getLastIngestion(substanceName: String) =
        experienceDao.getLastIngestion(substanceName)

}