package org.example.project.db.entitiy

import org.ktorm.ksp.annotation.PrimaryKey
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object Homes : Table<Nothing>("homes") {
    @PrimaryKey
    val id = int("id").primaryKey()
    val address = varchar("address")
    val district = varchar("district")
    val rating = int("rating")
}
