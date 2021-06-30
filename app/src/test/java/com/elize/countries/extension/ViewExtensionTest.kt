package com.elize.countries.extension

import android.view.View
import junit.framework.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mockito.mock

class ViewExtensionTest {

    @Test
    @Ignore
    fun `when isVisible is true, should return visible`() {
        val view: View = mock(View::class.java)

        view.changeVisibility(true)

        assertEquals(View.VISIBLE, view.visibility)
    }

    @Test
    @Ignore
    fun `when isVisible is false, should return gone`() {
        val view: View = mock(View::class.java)

        view.changeVisibility(false)

        assertEquals(View.GONE, view.visibility)
    }
}