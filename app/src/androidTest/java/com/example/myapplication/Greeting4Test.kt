import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.Greeting4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Greeting4Test {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testButtonClickWithBlankInput() {
        composeTestRule.setContent {
            Greeting4(onClick = {}, modifier = Modifier)
        }

        // Check if text field and button exist
        composeTestRule.onNodeWithText("You can Write Room10").assertExists()
        composeTestRule.onNodeWithText("Open").assertExists()

        // Try clicking the button without entering text
        composeTestRule.onNodeWithText("Open").performClick()

        // Assertion for no input can be added if onClick handles blank inputs
    }
    @Test
    fun testButtonClickWithInput() {
        var namePassed = ""
        composeTestRule.setContent {
            Greeting4(
                onClick = { namePassed = it },
                modifier = Modifier
            )
        }

        // Verify text field exists
        composeTestRule.onNodeWithTag("nameField").assertExists()

        // Perform text input
        composeTestRule.onNodeWithTag("nameField").performTextInput("Room10")

        // Click the button
        composeTestRule.onNodeWithText("Open").performClick()

        // Assert the value passed to onClick
        assert(namePassed == "Room10") { "Expected 'Test Room', got '$namePassed'" }
    }

}
