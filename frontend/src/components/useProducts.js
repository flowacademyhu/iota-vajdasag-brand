import { useState, useEffect } from 'react'
import { getProducts } from '../communications/userApi'



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
