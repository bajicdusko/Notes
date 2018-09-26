package com.bajicdusko.notes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Dusko Bajic on 26.09.18.
 * GitHub @bajicdusko
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @JvmField
  @Rule
  val mainActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

  @Test
  fun mainActivity_givenDefaultState_shouldPopUpInputFormFromTheBottom(){

    onView(withId(R.id.fab)).perform(click())
    onView(withId(R.id.new_note_dialog_fragment_til_content)).check(matches(isDisplayed()))
  }

  @Test
  fun mainActivity_givenPopedUpInputForm_shouldDisplayErrorOnEmptyNote(){
    onView(withId(R.id.fab)).perform(click())
    onView(withId(R.id.new_note_dialog_fragment_til_content)).check(matches(isDisplayed()))
    onView(withId(R.id.new_note_dialog_fragment_btn_add)).perform(click())
    onView(withText(R.string.noteMustNotbeEmpty)).check(matches(isDisplayed()))
  }

  @Test
  fun mainActivity_givenFilledNote_shouldAddNoteIntoTheList(){
    onView(withId(R.id.fab)).perform(click())
    onView(withId(R.id.new_note_dialog_fragment_til_content)).check(matches(isDisplayed()))

    onView(withId(R.id.new_note_dialog_fragment_tiet_content))
        .perform(typeText("This is my new note"))
        .perform(pressBack())

    onView(withId(R.id.new_note_dialog_fragment_btn_add)).perform(click())

    onView(withText("This is my new note")).check(matches(isDisplayed()))
  }
}