export class ChatManager {
  constructor(server) {
    this.server = server;
    this.initHandlers();
  }

  initHandlers() {
    this.server.io.on('connection', (socket) => {
      // Отправка сообщения
      socket.on('message:send', async (data) => {
        const msg = await this.createMessage(data);
        this.server.db.saveMessage(msg);
        
        // Рассылка получателям
        socket.to(msg.chatId).emit('message:new', msg);
        
        // Уведомления
        this.server.notify(msg.recipientId, {
          title: msg.senderName,
          body: msg.text
        });
      });

      // Редактирование
      socket.on('message:edit', (msgId, newText) => {
        const msg = this.server.db.editMessage(msgId, newText);
        socket.to(msg.chatId).emit('message:updated', msg);
      });
    });
  }
}