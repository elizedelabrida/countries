package com.elize.countries.extension

import android.view.View
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

class ViewExtensionTest {

    @Test
    @Ignore
    fun `when isVisible is true, should return visible`() {
        val view: View = mock(View::class.java)
        view.changeVisibility(true)

        Assert.assertEquals(view.visibility, View.VISIBLE)
    }

    @Test
    @Ignore
    fun `when isVisible is false, should return gone`() {
        val view: View = mock(View::class.java)
        view.changeVisibility(false)

        Assert.assertEquals(view.visibility, View.GONE)
    }
}