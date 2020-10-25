package com.feamor.spiritCloud.models.utils

import com.feamor.spiritCloud.models.rights.RightType

object RightsUtils {
    fun checkRight(src: Int, right: RightType): Boolean {
        val shift = right.intRepresentation.countTrailingZeroBits()
        return (src shr shift) and 1 == 1
    }

    fun addRight(src: Int, right: RightType): Int = src or right.intRepresentation

    fun removeRight(src: Int, right: RightType): Int = src and (right.intRepresentation.inv())
}