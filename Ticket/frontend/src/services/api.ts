const SERVER = 'http://localhost:3000'

const api = async (endpoint : string,
                   config? : RequestInit) => {

    console.log('Endpoint: ' + SERVER + endpoint)
    console.log(`Endpoint: (TL) ${SERVER}${endpoint}`)

    const result = await fetch(SERVER + endpoint, config)
    return await result.json()

}

export default api // import api from ...
// export { api } // import { api } from ...