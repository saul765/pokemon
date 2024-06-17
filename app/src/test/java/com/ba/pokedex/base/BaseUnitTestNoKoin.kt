package com.ba.pokedex.base

import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
abstract class BaseUnitTestNoKoin : AutoCloseKoinTest() {

    val context: Context by lazy { InstrumentationRegistry.getInstrumentation().targetContext }

    fun <F : Fragment> fragmentWithMockNavController(fragment: F): F = fragment.also {
        it.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
            if (viewLifecycleOwner != null) {
                Navigation.setViewNavController(it.requireView(), Mockito.mock())
            }
        }
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }


}