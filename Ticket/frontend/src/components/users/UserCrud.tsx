import { useEffect, useState } from 'react'
import api from '../../services/api'

interface User {
  id: string
  name: string
  email: string
}

const emptyForm = {
  name: '',
  status: 'ACTIVE',
  userType: 'CUSTOMER',
  email: '',
  password: '',
  cpf: '',
  cep: '',
  phone: '',
  dateOfBirth: ''
}

const UserCrud = () => {
  const [users, setUsers] = useState<User[]>([])
  const [form, setForm] = useState({ ...emptyForm })
  const [updateId, setUpdateId] = useState('')

  const load = () => {
    api('/users').then(setUsers)
  }

  useEffect(load, [])

  const create = async () => {
    await api('/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(form)
    })
    setForm({ ...emptyForm })
    load()
  }

  const update = async () => {
    await api('/users/update/name', {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id: updateId, name: form.name })
    })
    setUpdateId('')
    load()
  }

  const remove = async (id: string) => {
    await api('/users/remove', {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ id })
    })
    load()
  }

  return (
    <section>
      <h2>Usuários</h2>
      <div>
        <input
          placeholder="nome"
          value={form.name}
          onChange={(e) => setForm({ ...form, name: e.target.value })}
        />
        <input
          placeholder="email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />
        <input
          placeholder="senha"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
          type="password"
        />
        <input
          placeholder="cpf"
          value={form.cpf}
          onChange={(e) => setForm({ ...form, cpf: e.target.value })}
        />
        <input
          placeholder="cep"
          value={form.cep}
          onChange={(e) => setForm({ ...form, cep: e.target.value })}
        />
        <input
          placeholder="telefone"
          value={form.phone}
          onChange={(e) => setForm({ ...form, phone: e.target.value })}
        />
        <input
          placeholder="data de nascimento"
          value={form.dateOfBirth}
          onChange={(e) => setForm({ ...form, dateOfBirth: e.target.value })}
        />
        <button onClick={create}>Criar</button>
      </div>
      <div>
        <input
          placeholder="id para atualizar"
          value={updateId}
          onChange={(e) => setUpdateId(e.target.value)}
        />
        <button onClick={update}>Atualizar Nome</button>
      </div>
      <ul>
        {users.map((u) => (
          <li key={u.id}>
            {u.name} ({u.email})
            <button onClick={() => remove(u.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </section>
  )
}

export default UserCrud
