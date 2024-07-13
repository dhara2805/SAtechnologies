package com.example.satechnologies.model

data class Inspection(
    val area: Area,
    val id: Int,
    val inspectionType: InspectionType,
    val survey: Survey
)