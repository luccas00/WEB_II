import { useEffect, useState } from 'react'
import api from '../../services/api'

interface Notification {
  id: string
  userId: string
  service: string
  notificationType: string
  subject: string
  content: string
}

const emptyForm = {
  userId: '',
  service: '',
  notificationType: 'MESSAGE',
  subject: '',
  content: ''
}

const NotificationCrud = () => {
  const [notifications, setNotifications] = useState<Notification[]>([])
  const [form, setForm] = useState({ ...emptyForm })

  const load = () => {
    api('/notifications').then(setNotifications)
  }

  useEffect(load, [])

  const create = async () => {
    await api('/notifications', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
    setForm({ ...emptyForm })
    load()
  }

  return (
    <section>
      <h2>Notificações</h2>
      <div>
        <input
          placeholder="id do usuário"
          value={form.userId}
          onChange={(e) => setForm({ ...form, userId: e.target.value })}
        />
        <input
          placeholder="serviço"
          value={form.service}
          onChange={(e) => setForm({ ...form, service: e.target.value })}
        />
        <input
          placeholder="tipo"
          value={form.notificationType}
          onChange={(e) => setForm({ ...form, notificationType: e.target.value })}
        />
        <input
          placeholder="assunto"
          value={form.subject}
          onChange={(e) => setForm({ ...form, subject: e.target.value })}
        />
        <input
          placeholder="conteúdo"
          value={form.content}
          onChange={(e) => setForm({ ...form, content: e.target.value })}
        />
        <button onClick={create}>Criar</button>
      </div>
      <ul>
        {notifications.map((n) => (
          <li key={n.id}>
            {n.subject} - {n.service}
          </li>
        ))}
      </ul>
    </section>
  )
}

export default NotificationCrud
