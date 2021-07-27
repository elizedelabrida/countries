package com.elize.countries.extension

import android.view.View
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Spy
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ViewExtensionTest {
    @Spy
    lateinit var view: View

    @Before
    fun setup() {
        val app = RuntimeEnvironment.application
        view = View(app)
    }

    @Test
    fun `when isVisible is true, should return visible`() {
        val v1 = view.visibility

        view.changeVisibility(true)

        val v2 = view.visibility

        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    fun `when isVisible is false, should return gone`() {
        val v1 = view.visibility

        view.changeVisibility(false)

        val v2 = view.visibility

        assertEquals(View.GONE, view.visibility)
    }
}