package com.elize.countries.util

import android.content.Context
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class DrawableUtilTest {
    @Mock
    lateinit var mockContext: Context

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    @Ignore
    fun `should return circular drawable`() {
        val progressDrawable = getProgressDrawable(mockContext)
        assertNotNull(progressDrawable)
    }

}