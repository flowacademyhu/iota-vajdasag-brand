import axios from 'axios'

const productApi = axios.create({
  baseURL: `/api/`,
})

productApi.interceptors.request.use(
  (request) => {
    const token = sessionStorage.getItem('token')
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

export default productApi
