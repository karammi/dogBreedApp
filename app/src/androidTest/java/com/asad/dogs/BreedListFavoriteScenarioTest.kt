package com.asad.dogs

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.asad.dogs.app.DogBreedsApp
import com.asad.dogs.app.MainActivity
import com.asad.dogs.core.MockServerDispatcher
import com.asad.dogs.core.di.module.DatabaseModule
import com.asad.dogs.core.di.module.NetworkModule
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@UninstallModules(
    value = [NetworkModule::class, DatabaseModule::class],
)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class BreedListFavoriteScenarioTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var okHttpClient: OkHttpClient
    var mockWebServer: MockWebServer = MockWebServer()

    private val serviceMap: Map<String, String> = mapOf(
        Pair("breeds/list/all", "breed_list_success.json"),
        Pair("breed/akita/images", "breed_pictures_success.json"),
        Pair("breed/{breedName}/images", "breed_pictures_error.json"),
    )
    lateinit var idlingResources: OkHttp3IdlingResource

    @Before
    fun setup() {
        hiltRule.inject()
        mockWebServer.start(port = 8001)

        composeTestRule.mainClock.autoAdvance = true
        composeTestRule.activity.setContent {
//            MaterialTheme {
            DogBreedsApp()
//            }
        }

        idlingResources = OkHttp3IdlingResource.create("okhttp", okHttpClient)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun whenChooseBreedListItem_thenAddPictureToFavoriteScreen() {
        mockWebServer.dispatcher = MockServerDispatcher().successDispatcher(serviceMap)
        composeTestRule.apply {
            onNodeWithText("Dogs App")
                .assertIsDisplayed()

            onNodeWithContentDescription("favorite_icon")
                .assertIsDisplayed()

            waitUntil(3000) {
                onAllNodesWithContentDescription("breed_list_image")
                    .assertAny(hasContentDescription("breed_list_image"))
                    .fetchSemanticsNodes().size > 1
            }

            onAllNodesWithContentDescription("breed_model_title")
                .assertAny(hasContentDescription("breed_model_title"))
                .fetchSemanticsNodes().size > 1

            onAllNodesWithContentDescription("card_breed_model")
                .assertAny(hasContentDescription("card_breed_model"))
                .fetchSemanticsNodes().size > 1

            onNodeWithText("Akita")
                .performClick()

            onNodeWithText("Akita")
                .assertIsDisplayed()

            onNodeWithContentDescription("BreedPictureScreen")
                .assertExists()
                .assertIsDisplayed()

            waitUntil {
                onAllNodesWithContentDescription("breed_picture_1")
                    .fetchSemanticsNodes()
                    .size == 1
            }

            waitUntil {
                onAllNodesWithContentDescription("breed_picture_2")
                    .fetchSemanticsNodes()
                    .size == 1
            }

            onNodeWithContentDescription("breed_picture_1")
                .performClick()

            onNodeWithContentDescription("breed_picture_2")
                .performClick()

            onNodeWithContentDescription("CustomTouchableScale")
                .assertIsDisplayed()

            onNodeWithContentDescription("CustomTouchableScale")
                .performClick()

            // todo should navigate up and check it has selected items or not
        }
    }
}
