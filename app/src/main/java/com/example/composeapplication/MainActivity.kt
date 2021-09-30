package com.example.composeapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.ui.theme.Shapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeApplicationTheme {
//                MessageCard(message = Message("Friend", "Compose Preview"))
                CounterContent()
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
@Preview
fun PreviewConversation() {
    ComposeApplicationTheme {
        Conversation(messages = SampleData.conversationSample)
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(message: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Profile pic",
            modifier = Modifier
                .size(Dp(40.0F))
                .clip(CircleShape)
                .border(2.0.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(Dp(8f)))

        //Remember expanded state
        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        //Handle click
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(Dp(5f)))

            Surface(shape = Shapes.medium, elevation = 1.dp,
            color = surfaceColor,
            // Animate expansion/collapsing
            modifier = Modifier
                .animateContentSize()
                .padding(1.dp)) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body1
                )

            }

        }
    }

}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
showBackground = true,
name = "Dark mode")
fun PreviewMessage() {
    ComposeApplicationTheme {
        MessageCard(message = Message("Friend", "Compose Preview"))
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    name = "Light mode")
fun PreviewMessageLight() {
    ComposeApplicationTheme {
        MessageCard(message = Message("Friend", "Compose Preview"))
    }
}

@Composable
fun CounterContent() {
    var count by remember {
        mutableStateOf(0)
    }

    Counter(count) { updatedCount ->
        count = updatedCount
    }
}

@Composable
fun Counter(count : Int, updateCount : (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1)},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.Red
        )
    ) {
        Text(text = "I have been clicked $count times")
    }
}