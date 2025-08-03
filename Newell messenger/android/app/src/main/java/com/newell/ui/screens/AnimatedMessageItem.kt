@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedMessageItem(message: Message) {
    AnimatedContent(
        targetState = message,
        transitionSpec = {
            fadeIn() + slideInVertically { -40 } with
            fadeOut() + slideOutVertically { 40 }
        }
    ) { msg ->
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = if (msg.isOutgoing) Purple500 else Gray200,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(msg.text, color = if (msg.isOutgoing) Color.White else Color.Black)
                
                // Реакции
                if (msg.reactions.isNotEmpty()) {
                    ReactionBar(reactions = msg.reactions)
                }
            }
        }
    }
}