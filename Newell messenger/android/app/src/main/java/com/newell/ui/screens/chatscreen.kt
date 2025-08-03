@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ChatScreen(chatId: String) {
    val viewModel: ChatViewModel = hiltViewModel()
    val messages by viewModel.messages.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Хедер с анимацией
        AnimatedVisibility(visible = true) {
            ChatHeader(chatId = chatId)
        }

        // Список сообщений
        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(messages, key = { it.id }) { msg ->
                AnimatedMessageItem(message = msg)
            }
        }

        // Ввод сообщения
        MessageInput(
            onSend = { text -> viewModel.sendMessage(text) },
            onAttach = { type -> viewModel.handleAttachment(type) }
        )
    }
}