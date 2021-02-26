import { useState, useEffect } from 'react'
import { getAllProducts } from '../communications/userApi'

const useProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const fetchProducts = async () => {
    try {
      const fetchedProducts = await getAllProducts()
      setListOfAllProducts(fetchedProducts)
    } catch (error) {
      throw new Error('Failed to get products.')
    }
  }

  useEffect(() => {
    fetchProducts()
  }, [])

  return { listOfAllProducts }
}

export default useProducts