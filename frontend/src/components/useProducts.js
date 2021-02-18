import { useState, useEffect, useCallback } from 'react'
import { getProducts } from '../communications/productApi'



const useProducts = () => {
  const [listOfAllProducts, setListOfAllProducts] = useState([])

  const fetchProducts = async () => {
    const fetchedProducts = await getProducts()
    console.log(fetchedProducts);
    setListOfAllProducts(fetchedProducts)
  }

  useEffect(() => {
    fetchProducts()
  }, [])

  return { listOfAllProducts }
}

export default useProducts
