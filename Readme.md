### Jetpack Compose

## Why it is needed?
Manipulating views manually increases the likelihood of errors. If a piece of data is rendered in multiple places, it’s easy to forget to update one of 
the views that shows it. It’s also easy to create illegal states, when two updates conflict in an unexpected way. 
For example, an update might try to set a value of a node that was just removed from the UI. 
In general, the software maintenance complexity grows with the number of views that require updating.

Over the last several years, the entire industry has started shifting to a declarative UI model, which greatly simplifies the engineering associated with 
building and updating user interfaces. The technique works by conceptually regenerating the entire screen from scratch, then applying only the necessary changes. 
This approach avoids the complexity of manually updating a stateful view hierarchy. 

Compose is a declarative UI framework.

### Advantages

* The function doesn't return anything. Compose functions that emit UI do not need to return anything, because they describe the desired screen state instead of constructing UI widgets.

* This function is fast, idempotent, and free of side-effects.

* The function behaves the same way when called multiple times with the same argument, and it does not use other values such as global variables or calls to random().
* The function describes the UI without any side-effects, such as modifying properties or global variables.


### How to use
* Add compose libraries:
* 
```
    implementation "androidx.compose.ui:ui:1.0.3"
    implementation "androidx.compose.material:material:1.0.3"
    implementation "androidx.compose.ui:ui-tooling-preview:1.0.3"
```

* Add `@Composable` on top of function to make it composable
* Add `@Preview` to see preview
* Use `Column` to arrange `Views` vertically

```
@Composable
fun MessageCard(msg: Message) {
    Column {
        Text(text = msg.author)
        Text(text = msg.body)
    }
}

```
* Add `Row` to arrange things horizontally

```
Row {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Profile pic",
            modifier = Modifier
                .size(Dp(40.0F))
                .clip(CircleShape)
                .border(2.0.dp, MaterialTheme.colors.secondary, CircleShape)
        )
   }     
```
* `Modifier` allows to change a view's properties
* Add a `theme` using `ApplicationNameTheme`. The theme name can be checked in `ComposeSample/app/src/main/java/com/example/composeapplication/ui/theme/Theme.kt`
* Automatically handles `Dark mode`. You can create a preview like this

```
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
```
`Theme.kt` contains colors used for the themes

* Use `LazyColumn` to add a list column

```
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}
```

* Use `remember` to remember local state.Composables (and its children) using this state will get 
  redrawn automatically when the value is updated. This is called `recomposition`.

```
   var isExpanded by remember {
            mutableStateOf(false)
        }
```
* Use `LazyColumn` for scrollable List. `Column` is not scrollable by default

### Environment
Android Studio 2021.1.1 Canary 12

### REFERENCES

<https://developer.android.com/courses/pathways/compose>
<https://developer.android.com/jetpack/compose/mental-model?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%23article-https%3A%2F%2Fdeveloper.android.com%2Fjetpack%2Fcompose%2Fmental-model>