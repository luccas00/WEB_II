import { useState } from 'react'
import { setApiPort, getBaseUrl } from '../services/api'

const ServerConfig = () => {
  const [port, setPort] = useState(
    window.localStorage.getItem('apiPort') || import.meta.env.VITE_API_PORT || '8080'
  )

  const save = () => {
    setApiPort(port)
    alert(`Porta definida para ${port}`)
  }

  return (
    <section>
      <h2>Configuração</h2>
      <div>
        <input value={port} onChange={(e) => setPort(e.target.value)} placeholder="8080" />
        <button onClick={save}>Salvar</button>
      </div>
      <p>Base URL: {getBaseUrl()}</p>
    </section>
  )
}

export default ServerConfig
