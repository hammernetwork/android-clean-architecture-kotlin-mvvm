package com.examples

import android.app.Activity
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
abstract class AcceptanceTest<T : Activity>(clazz: Class<T>) {

  @Rule
  @JvmField
  val testRule: ActivityTestRule<T> = IntentsTestRule(clazz)

  val checkThat: Matchers = Matchers()
  val events: Events = Events()
}

