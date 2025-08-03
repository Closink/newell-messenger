import { Server } from './core/server';
import { initModules } from './modules';

const server = new Server({
  port: 3000,
  dbOptions: {
    path: './data',
    encryptionKey: process.env.ENC_KEY
  }
});

// Инициализация всех модулей
initModules(server);

// Запуск
server.start().then(() => {
  console.log(`🚀 Server ready at ${server.url}`);
});