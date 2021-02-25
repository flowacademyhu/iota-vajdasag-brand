import axios from 'axios'

const api = axios.create({
  baseURL: `/api/`,
})

api.interceptors.request.use(
  (request) => {
    console.log('request', request)
    const token = localStorage.getItem('token')
    if (token) {
      request.headers = {
        ...request.headers,
        Authorization: 'Bearer ' + token,
      }
    }
    return request
  },
  (error) => {
    return Promise.reject(error)
  }
)

export default api
