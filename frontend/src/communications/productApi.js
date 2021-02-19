import api from './apiInstance'
import './mockForProductApi'

export const getProducts = async () => {
  try {
    const response = await api.get('/products')
    
      console.log(response.data.products);
        
    return response.data.products
    
  } catch (error) {
    throw new Error('Failed to get products.')
  }
}
