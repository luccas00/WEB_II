import { useEffect, useState } from 'react'
import api from '../../services/api'

interface Event {
  id: string
  name: string
  price: number
}

const emptyForm = {
  name: '',
  description: '',
  type: 'PALESTRA',
  date: '',
  startSales: '',
  endSales: '',
  price: 0
}

const EventCrud = () => {
  const [events, setEvents] = useState<Event[]>([])
  const [form, setForm] = useState({ ...emptyForm })
  const [updateId, setUpdateId] = useState('')
  const [newPrice, setNewPrice] = useState('')

  const load = () => {
    api('/events').then(setEvents)
  }

  useEffect(load, [])

  const create = async () => {
    await api('/events', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
    setForm({ ...emptyForm })
    load()
  }

  const updatePrice = async () => {
    await api('/events/update/price', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id: updateId, price: parseFloat(newPrice) })
    })
    setUpdateId('')
    setNewPrice('')
    load()
  }

  const remove = async (id: string) => {
    await api('/events/remove', {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id })
    })
    load()
  }

  return (
    <section>
      <h2>Eventos</h2>
      <div>
        <input
          placeholder="nome"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        />
        <input
          placeholder="descrição"
          value={form.description}
          onChange={(e) => setForm({ ...form, description: e.target.value })}
        />
        <input
          placeholder="tipo"
          value={form.type}
          onChange={(e) => setForm({ ...form, type: e.target.value })}
        />
        <input
          placeholder="data"
          value={form.date}
          onChange={(e) => setForm({ ...form, date: e.target.value })}
        />
        <input
          placeholder="início das vendas"
          value={form.startSales}
          onChange={(e) => setForm({ ...form, startSales: e.target.value })}
        />
        <input
          placeholder="fim das vendas"
          value={form.endSales}
          onChange={(e) => setForm({ ...form, endSales: e.target.value })}
        />
        <input
          placeholder="preço"
          value={form.price}
          type="number"
          onChange={(e) => setForm({ ...form, price: parseFloat(e.target.value) })}
        />
        <button onClick={create}>Criar</button>
      </div>
      <div>
        <input
          placeholder="id do evento"
          value={updateId}
          onChange={(e) => setUpdateId(e.target.value)}
        />
        <input
          placeholder="novo preço"
          value={newPrice}
          onChange={(e) => setNewPrice(e.target.value)}
        />
        <button onClick={updatePrice}>Atualizar Preço</button>
      </div>
      <ul>
        {events.map((ev) => (
          <li key={ev.id}>
            {ev.name} - R$ {ev.price}
            <button onClick={() => remove(ev.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  )
}

export default EventCrud
