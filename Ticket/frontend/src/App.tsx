import './App.css'
import { useState } from 'react'
import UserCrud from './components/users/UserCrud'
import EventCrud from './components/events/EventCrud'
import SalesCrud from './components/sales/SalesCrud'
import NotificationCrud from './components/notifications/NotificationCrud'
import ServerConfig from './components/ServerConfig'

function App() {
  const [view, setView] = useState('users')

  const renderView = () => {
    switch (view) {
      case 'users':
        return <UserCrud />
      case 'events':
        return <EventCrud />
      case 'sales':
        return <SalesCrud />
      case 'notifications':
        return <NotificationCrud />
      case 'config':
        return <ServerConfig />
      default:
        return null
    }
  }

  return (
    <>
      <h1>Sistema de Vendas de Tickets</h1>
      <nav>
        <button onClick={() => setView('users')}>Usuários</button>
        <button onClick={() => setView('events')}>Eventos</button>
        <button onClick={() => setView('sales')}>Vendas</button>
        <button onClick={() => setView('notifications')}>Notificações</button>
        <button onClick={() => setView('config')}>Configuração</button>
      </nav>
      {renderView()}
    </>
  )
}

export default App
