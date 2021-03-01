import { useState, useEffect } from 'react'
import { fetchProducts } from '../communications/userApi'

const useProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const getAllProducts = async () => {
    const fetchedProducts = await fetchProducts()
    setListOfAllProducts(fetchedProducts)
  }

  useEffect(() => {
    getAllProducts()
  }, [])

  return { listOfAllProducts }
}

export default useProducts
