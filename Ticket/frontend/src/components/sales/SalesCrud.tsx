import { useEffect, useState } from 'react'
import api from '../../services/api'

interface Sale {
  id: string
  user_id: string
  event_id: string
  purchaseStatus: string
}

const emptyForm = {
  user_id: '',
  event_id: ''
}

const SalesCrud = () => {
  const [sales, setSales] = useState<Sale[]>([])
  const [form, setForm] = useState({ ...emptyForm })
  const [updateId, setUpdateId] = useState('')
  const [newStatus, setNewStatus] = useState('PAGO')

  const load = () => {
    api('/sales').then(setSales)
  }

  useEffect(load, [])

  const create = async () => {
    await api('/sales', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form, purchaseDate: new Date().toISOString() })
    })
    setForm({ ...emptyForm })
    load()
  }

  const update = async () => {
    await api('/sales/update/status', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id: updateId, purchaseStatus: newStatus })
    })
    setUpdateId('')
    load()
  }

  const remove = async (id: string) => {
    await api('/sales/remove', {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id })
    })
    load()
  }

  return (
    <section>
      <h2>Vendas</h2>
      <div>
        <input
          placeholder="id do usuário"
          value={form.user_id}
          onChange={(e) => setForm({ ...form, user_id: e.target.value })}
        />
        <input
          placeholder="id do evento"
          value={form.event_id}
          onChange={(e) => setForm({ ...form, event_id: e.target.value })}
        />
        <button onClick={create}>Criar</button>
      </div>
      <div>
        <input
          placeholder="id da venda"
          value={updateId}
          onChange={(e) => setUpdateId(e.target.value)}
        />
        <input
          placeholder="status"
          value={newStatus}
          onChange={(e) => setNewStatus(e.target.value)}
        />
        <button onClick={update}>Atualizar</button>
      </div>
      <ul>
        {sales.map((s) => (
          <li key={s.id}>
            {s.id} - {s.purchaseStatus}
            <button onClick={() => remove(s.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  )
}

export default SalesCrud
