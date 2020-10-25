package com.feamor.spiritCloud.tests.utils

import com.feamor.spiritCloud.models.rights.RightType
import com.feamor.spiritCloud.models.utils.RightsUtils
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RightUtilsTests {

    @Test
    fun testCheckRights() {
        val firstSrc = "0110".toInt(2)
        assertFalse {
            RightsUtils.checkRight(firstSrc, RightType.CREATE)
        }
        assertTrue {
            RightsUtils.checkRight(firstSrc, RightType.READ)
        }
        assertTrue {
            RightsUtils.checkRight(firstSrc, RightType.UPDATE)
        }
        assertFalse {
            RightsUtils.checkRight(firstSrc, RightType.DELETE)
        }
        val secondSrc = "1001".toInt(2)
        assertTrue {
            RightsUtils.checkRight(secondSrc, RightType.CREATE)
        }
        assertFalse {
            RightsUtils.checkRight(secondSrc, RightType.READ)
        }
        assertFalse {
            RightsUtils.checkRight(secondSrc, RightType.UPDATE)
        }
        assertTrue {
            RightsUtils.checkRight(secondSrc, RightType.DELETE)
        }
    }

    @Test
    fun testAddRight() {
        val firstSrc = "0110".toInt(2)
        val firstResult = RightsUtils.addRight(firstSrc, RightType.CREATE)
        assertEquals("1110", firstResult.toString(2))
        val secondResult = RightsUtils.addRight(firstSrc, RightType.DELETE)
        assertEquals("0111", secondResult.toString(2).padStart(4, '0'))
        val thirdResult = RightsUtils.addRight(firstSrc, RightType.READ)
        assertEquals("0110", thirdResult.toString(2).padStart(4, '0'))
    }

    @Test
    fun removeRight() {
        val firstSrc = "0110".toInt(2)
        val firstResult = RightsUtils.removeRight(firstSrc, RightType.READ)
        assertEquals("0010", firstResult.toString(2).padStart(4, '0'))
        val secondResult = RightsUtils.removeRight(firstSrc, RightType.UPDATE)
        assertEquals("0100", secondResult.toString(2).padStart(4, '0'))
        val thirdResult = RightsUtils.removeRight(firstSrc, RightType.CREATE)
        assertEquals("0110", thirdResult.toString(2).padStart(4, '0'))
    }
}