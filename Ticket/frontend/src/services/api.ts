/**
 * Resolve base URL for backend gateway. The default port is taken from the
 * `VITE_API_PORT` environment variable and can be overridden at runtime by
 * the user through the configuration screen which stores the value in
 * `localStorage`.
 */
const getBaseUrl = () => {
  const port =
    window.localStorage.getItem('apiPort') ||
    import.meta.env.VITE_API_PORT ||
    '8080'
  return `http://localhost:${port}`
}

export const setApiPort = (port: string) => {
  window.localStorage.setItem('apiPort', port)
}

const api = async (endpoint: string, config?: RequestInit) => {
  const base = getBaseUrl()
  const result = await fetch(`${base}${endpoint}`, config)
  if (result.status === 204) return null
  return await result.json()
}

export default api
export { getBaseUrl }