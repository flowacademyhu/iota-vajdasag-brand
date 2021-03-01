import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'

const useProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const getAllProducts = async () => {
    try {
      const fetchedProducts = await fetchProducts()
      setListOfAllProducts(fetchedProducts)
    } catch (error) {
      throw new Error('Failed to get products.')
    }
  }

  useEffect(() => {
    getAllProducts()
  }, [])

  return { listOfAllProducts }
}

export default useProducts
