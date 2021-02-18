import axios from 'axios'
import './mockForProductApi'

export const getProducts = async () => {
  try {
    const response = await axios.get('http://localhost:3000/api/products')
    return response.data.products
  } catch (error) {
    console.log(error)
    throw new Error('Failed to get products.')
  }
}
