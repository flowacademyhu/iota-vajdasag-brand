import axios from 'axios'

const mockable = axios.create({
  baseURL: `/api`,
})

export default mockable