package com.feamor.spiritCloud.models.rights

enum class RightType(val intRepresentation: Int) {
    CREATE("1000".toInt(2)),
    READ("0100".toInt(2)),
    UPDATE("0010".toInt(2)),
    DELETE("0001".toInt(2))
}