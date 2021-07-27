package com.elize.countries.util

import android.content.Context
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DrawableUtilTest {
    @Mock
    lateinit var mockContext: Context

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `should return circular drawable`() {
        val progressDrawable = getProgressDrawable(mockContext)
        assertNotNull(progressDrawable)
    }

}