package ua.polodarb.ram.data.database.entity

import ua.polodarb.ram.data.database.entity.base.BaseDatabaseModel

enum class StatusEntity : BaseDatabaseModel {
    ALIVE, DEAD, UNKNOWN
}