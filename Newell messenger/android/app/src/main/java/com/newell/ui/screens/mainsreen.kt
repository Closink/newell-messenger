@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent { scope.launch { drawerState.close() } }
        }
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Newell") }) },
            bottomBar = { BottomNavBar() },
            floatingActionButton = {
                FloatingActionButton(onClick = { /* Новый чат */ }) {
                    Icon(Icons.Default.Add, "")
                }
            }
        ) { padding ->
            // Навигация между экранами
            NavHost(
                navController = navController,
                startDestination = "chats",
                modifier = Modifier.padding(padding)
            ) {
                composable("chats") { ChatsScreen() }
                composable("calls") { CallsScreen() }
                composable("contacts") { ContactsScreen() }
            }
        }
    }
}